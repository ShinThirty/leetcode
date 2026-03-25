package similarstringgroups

class DisjointSet(_n: Int) {
  private val parents = Array.fill(_n)(-1)
  private var _nSets = _n

  def union(x: Int)(y: Int): Unit = {
    val xr = find(x)
    val yr = find(y)
    if (xr != yr) {
      _nSets -= 1
      var s1 = xr
      var s2 = yr
      if (parents(xr) > parents(yr)) {
        s1 = yr
        s2 = xr
      }
      parents(s1) += parents(s2)
      parents(s2) = s1
    }
  }

  private def find(x: Int): Int = {
    var result = x
    while (parents(result) >= 0) {
      if (parents(parents(result)) >= 0) {
        parents(result) = parents(parents(result))
      }
      result = parents(result)
    }
    result
  }

  def nSets: Int = _nSets
}

object Solution {
  def numSimilarGroups(strs: Array[String]): Int = {
    val isSimilar: String => String => Boolean = str1 => str2 =>
      (str1.view zip str2.view).foldLeft(0) {
        case (swaps, (c1, c2)) => if (c1 == c2) swaps else swaps + 1
      } <= 2

    val n = strs.length
    val ds = new DisjointSet(n)
    for (i <- 0 until n - 1; j <- i + 1 until n) {
      val str1 = strs(i)
      val str2 = strs(j)
      if (isSimilar(str1)(str2)) {
        ds.union(i)(j)
      }
    }
    ds.nSets
  }
}