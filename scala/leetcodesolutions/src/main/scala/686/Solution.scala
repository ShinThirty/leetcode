package `686`

object Solution {
  def repeatedStringMatch(a: String, b: String): Int = {
    val lb = b.length()
    val la = a.length()
    var q = lb / la
    val aa = a * q
    val aaa = a * (q + 1)
    val aaaa = a * (q + 2)
    if (aa.contains(b)) {
      q
    } else if (aaa.contains(b)) {
      q + 1
    } else if (aaaa.contains(b)) {
      q + 2
    } else {
      -1
    }
  }
}
