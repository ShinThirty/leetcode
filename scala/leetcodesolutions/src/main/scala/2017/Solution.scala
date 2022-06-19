package `2017`

object Solution {
  def gridGame(grid: Array[Array[Int]]): Long = {
    val n = grid(0).length
    val prefixPointSum = Array.fill(2, n + 1)(0L)
    for (i <- 1 to n) {
      prefixPointSum(0)(i) = prefixPointSum(0)(i - 1) + grid(0)(i - 1)
      prefixPointSum(1)(i) = prefixPointSum(1)(i - 1) + grid(1)(i - 1)
    }

    Range.inclusive(0, n - 1).map(i => Math.max(prefixPointSum(0)(n) - prefixPointSum(0)(i + 1), prefixPointSum(1)(i) - prefixPointSum(1)(0))).min
  }
}