package `738`

object Solution {
  def monotoneIncreasingDigits(n: Int): Int = {
    var r = 0
    var m = 1000000000
    var i = n
    var needReverse = false
    while (m > 0 && !needReverse) {
      val d = i / m
      i %= m
      val p = r % 10
      if (p <= d) {
        r *= 10
        r += d
        m /= 10
      } else {
        needReverse = true
      }
    }
    if (m == 0) {
      r
    } else {
      var p = r % 10
      var d = i / m
      while (p > d) {
        d = p - 1
        m *= 10
        r /= 10
        p = r % 10
      }

      m * (10 * r + d + 1) - 1
    }
  }
}
