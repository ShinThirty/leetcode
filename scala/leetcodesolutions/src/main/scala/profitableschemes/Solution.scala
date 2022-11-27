package profitablescheme

object Solution {
  def profitableSchemes(
      n: Int,
      minProfit: Int,
      group: Array[Int],
      profit: Array[Int]
  ): Int = {
    val M = 1000000007
    val totalCrimes = group.length

    // Index = totalCrimes
    val validSchemes = Array.fill(n + 1, minProfit + 1)(0)
    for (i <- 0 to n) {
      validSchemes(i)(0) = 1
    }

    def getValidSchemes: Int => Int => Int = n =>
      p => {
        if (n < 0) {
          0
        } else {
          validSchemes(n)(p)
        }
      }

    for (
      index <- totalCrimes - 1 to 0 by -1;
      N <- n to 0 by -1;
      P <- minProfit to 0 by -1
    ) {
      validSchemes(N)(P) += getValidSchemes(N - group(index))(Math.max(0, P - profit(index)))
      validSchemes(N)(P) %= M
    }

    validSchemes(n)(minProfit)
  }
}
