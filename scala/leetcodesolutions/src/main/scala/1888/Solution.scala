package `1888`

object Solution {
  def minFlips(s: String): Int = {
    val n = s.length()
    var flips1 = 0 // 0101
    var flips2 = 0 // 1010
    var ans = Int.MaxValue
    for (i <- 0 to n - 1) {
      if (i % 2 == 0) {
        if (s(i) == '0') {
          flips2 += 1
        } else {
          flips1 += 1
        }
      } else {
        if (s(i) == '0') {
          flips1 += 1
        } else {
          flips2 += 1
        }
      }
    }

    ans = Math.min(flips1, flips2)

    for (shift <- 1 to n - 1) {
      if (s(shift - 1) == '0') {
        flips2 -= 1
      } else {
        flips1 -= 1
      }

      val t = flips1
      flips1 = flips2
      flips2 = t

      if (n % 2 == 0) {
        if (s((n - 1 + shift) % n) == '0') {
          flips1 += 1
        } else {
          flips2 += 1
        }
      } else {
        if (s((n - 1 + shift) % n) == '0') {
          flips2 += 1
        } else {
          flips1 += 1
        }
      }

      val alt = Math.min(flips1, flips2)

      if (alt < ans) {
        ans = alt
      }
    }

    ans
  }
}
