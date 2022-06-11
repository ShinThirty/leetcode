package `633`

import scala.collection.mutable

object Solution {
  def judgeSquareSum(c: Int): Boolean = {
    var a = 0
    var ans = false
    val squares = mutable.HashSet.empty[Int]
    while (!ans && a * a <= c) {
      val a2 = a * a
      val b2 = c - a2
      if (squares.contains(b2) || a2 * 2 == c) {
        ans = true
      }
      a += 1
      squares.add(a2)
    }

    ans
  }
}
