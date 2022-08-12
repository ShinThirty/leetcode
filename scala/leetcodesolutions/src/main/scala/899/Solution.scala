package `899`

object Solution {
  def orderlyQueue(s: String, k: Int): String = {
    if (k > 1) {
      s.sorted
    } else {
      val ss = s * 2
      val n = s.size
      ss.sliding(n).min
    }
  }
}
