package `1420`

object Solution {
  def numOfArrays(n: Int, m: Int, k: Int): Int = {
    val memo = Array.fill(50, 51, 101)(-1)
    val MOD = 1000000007

    def ways(idx: Int)(cost: Int)(maxSoFar: Int): Int = {
      if (idx == n) {
        if (cost == k) {
          1
        } else {
          0
        }
      } else {
        if (memo(idx)(cost)(maxSoFar) != -1) {
          memo(idx)(cost)(maxSoFar)
        } else {
          var result = 0

          for (e <- 1 to m) {
            if (e > maxSoFar && cost < k) {
              result = (result + ways(idx + 1)(cost + 1)(e)) % MOD
            } else if (e <= maxSoFar) {
              result = (result + ways(idx + 1)(cost)(maxSoFar)) % MOD
            }
          }

          memo(idx)(cost)(maxSoFar) = result
          result
        }
      }
    }

    ways(0)(0)(0)
  }
}
