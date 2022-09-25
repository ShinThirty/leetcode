package edgelengthlimitedpaths

class DisjointSet(_n: Int) {
  private val parents = Array.fill(_n)(-1)

  def union(x: Int)(y: Int): Unit = {
    val xr = find(x)
    val yr = find(y)
    if (xr != yr) {
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

  def find(x: Int): Int = {
    var result = x
    while (parents(result) >= 0) {
      if (parents(parents(result)) >= 0) {
        parents(result) = parents(parents(result))
      }
      result = parents(result)
    }
    result
  }
}

object Solution {
  def distanceLimitedPathsExist(n: Int, edgeList: Array[Array[Int]], queries: Array[Array[Int]]): Array[Boolean] = {
    val ds = new DisjointSet(n)
    val sortedEdgeList = edgeList.sorted(Ordering.by[Array[Int], Int](_(2)))
    val sortedQueriesAndIndexes = queries.zipWithIndex.sorted(Ordering.by[(Array[Int], Int), Int](_._1(2)))
    val ans = Array.fill(queries.length)(false)
    var ei = 0

    for ((q, i) <- sortedQueriesAndIndexes) {
      while (ei < sortedEdgeList.length && sortedEdgeList(ei)(2) < q(2)) {
        val e = sortedEdgeList(ei)
        ei += 1
        ds.union(e(0))(e(1))
      }
      ans(i) = ds.find(q(0)) == ds.find(q(1))
    }

    ans
  }
}