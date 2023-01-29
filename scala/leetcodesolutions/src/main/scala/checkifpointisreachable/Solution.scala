package checkifpointisreachable

object Solution {
  def isReachable(targetX: Int, targetY: Int): Boolean = {
    def euclid: Int => Int => Int = a =>
      b => if (b == 0) a else euclid(b)(a % b)

    var a = targetX
    var b = targetY
    if (a < b) {
      a = targetY
      b = targetX
    }

    val gcd = euclid(a)(b)
    (gcd & (gcd - 1)) == 0
  }
}
