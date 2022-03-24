package distinctsubsequences

import scala.collection.mutable.Buffer

object Solution {
  def numDistinct(s: String, t: String): Int = {
    val m = s.length()
    val n = t.length()
    var cur: Buffer[Int] = Buffer.fill(n + 1)(0)
    cur.update(0, 1)

    var i = 1
    while (i <= m) {
      var j = n
      while (j > i) {
        cur.update(j, 0)
        j -= 1
      }
      while (j > 0) {
        if (s(i - 1) == t(j - 1)) {
          cur.update(j, cur(j - 1) + cur(j))
        }
        j -= 1
      }
      i += 1
    }

    cur.last
  }
}
