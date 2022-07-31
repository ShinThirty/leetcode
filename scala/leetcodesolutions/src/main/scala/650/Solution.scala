package `650`

object Solution {
  def minSteps(n: Int): Int = {
    var ans = 0

    var m = n
    var f = 2
    while (f * f <= n) {
      if (m % f == 0) {
        m /= f
        ans += f
      } else {
        f += 1
      }
    }

    if (m == 1) {
      ans
    } else {
      ans + m
    }
  }
}
