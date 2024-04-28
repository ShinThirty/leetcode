package leetcode2424

class LUPrefix(_n: Int) {

  private val fenwick: Array[Int] = Array.fill(_n + 1)(0)
  private var maxM = 0

  def upload(m: Int): Unit = {
    var j = m
    if (m > maxM) {
      maxM = m
    }
    while (j <= _n) {
      fenwick(j) += 1
      j = j + (j & -j)
    }
  }

  private def query(m: Int): Int = {
    var j = m
    var res = 0
    while (j > 0) {
      res += fenwick(j)
      j = j - (j & -j)
    }
    res
  }

  def longest(): Int = {
    var lo = 1
    var hi = maxM
    while (lo < hi) {
      val med = lo + (hi - lo) / 2 + 1
      if (query(med) == med) {
        lo = med
      } else {
        hi = med - 1
      }
    }
    lo
  }

}
