package numberofwaystoreorderarraytogetsamebst

object Solution {
  def numOfWays(nums: Array[Int]): Int = {
    val p = 1000000007
    val n = nums.length

    // Calculate nCr % 1000000007.
    // nCr = (n-1)C(r-1) + (n-1)Cr
    val c = Array.fill(n + 1, n / 2 + 1)(0)
    c(0)(0) = 1
    for (i <- 1 to n) {
      c(i)(0) = 1
      for (j <- 1 to Math.min(i, n / 2)) {
        c(i)(j) = (c(i - 1)(j - 1) + c(i - 1)(j)) % p
      }
    }

    def f(nums: Array[Int]): Long = {
      if (nums.length <= 2) {
        1
      } else {
        val left = nums.filter(_ > nums.head)
        val right = nums.filter(_ < nums.head)

        val n = nums.length - 1
        var r = left.length
        if (r > n / 2) {
          r = right.length
        }

        val intermediate = c(n)(r) * f(left) % p
        intermediate * f(right) % p
      }
    }

    f(nums).toInt - 1
  }
}
