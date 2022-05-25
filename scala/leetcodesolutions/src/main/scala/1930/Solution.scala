package `1930`

import scala.collection.mutable

object Solution {
  def countPalindromicSubsequence(s: String): Int = {
    val m = s.length()
    val firstLocation = mutable.Map.empty[Char, Int]
    val prefixCharNumSum = mutable.Buffer.fill(26, m + 1)(0)

    s.zipWithIndex.foreach {
      case (c, i) => {
        if (!firstLocation.contains(c)) {
          firstLocation.put(c, i)
        }

        for (k <- 0 to 25) {
          prefixCharNumSum(k)(i + 1) = prefixCharNumSum(k)(i)
        }

        prefixCharNumSum(c - 'a')(i + 1) += 1
      }
    }

    var ans = 0
    val black = mutable.Buffer.fill(26)(false)
    for (i <- m - 1 to 0 by -1) {
      val j = firstLocation(s(i))
      if (j < i && !black(s(i) - 'a')) {
        black(s(i) - 'a') = true
        for (k <- 0 to 25) {
          if (prefixCharNumSum(k)(i) - prefixCharNumSum(k)(j + 1) > 0) {
            ans += 1
          }
        }
      }
    }

    ans
  }
}
