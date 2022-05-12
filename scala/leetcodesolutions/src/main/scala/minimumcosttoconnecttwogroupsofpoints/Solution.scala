package minimumcosttoconnecttwogroupsofpoints

import scala.collection.mutable

// Process group 1 sequentially and all comibnations of group 2
// Use bitmask to represent which point in group 2 has been connected
// For point k in group 1:
// point 0...k-1 are already connected
// point k can be connected to 1 already connected point in group 2
// point k can be connected to 1 or more disconnected points in group 2
// Reuse subproblem (k, mask)

case class Config(val k: Int, val mask: Int)

object Solution {
  def connectTwoGroups(cost: List[List[Int]]): Int = {
    val solutions = mutable.Map[Config, Int]()
    val maxAns = cost.map(row => row.sum).sum

    def solve(k: Int, mask: Int): Int = {
      val config = Config(k, mask)
      solutions.getOrElseUpdate(config, traverse(config))
    }

    def traverse(config: Config): Int = {
      val k = config.k
      val mask = config.mask

      // Alternative: connected to 1 already connected point in group 2
      if (k > 0) {
        var ans = maxAns
        val last = solve(k - 1, mask)
        var init = mask
        var i = 0
        while (init > 0) {
          if ((init & 1) == 1) {
            ans = Math.min(ans, last + cost(k)(i))
          }
          i += 1
          init >>= 1
        }

        // Alternative: connected to multiple disconnected points in group 2
        def partition(
            mask: Int,
            i: Int,
            probe: Int,
            accu: Int,
            n: Int
        ): Unit = {
          if (i < n) {
            if ((mask & probe) != 0 && (mask & ~probe) != 0) {
              ans =
                Math.min(ans, accu + cost(k)(i) + solve(k - 1, mask & ~probe))
              partition(
                mask & ~probe,
                i + 1,
                probe << 1,
                accu + cost(k)(i),
                n
              )
            }
            partition(mask, i + 1, probe << 1, accu, n)
          }
        }

        partition(mask, 0, 1, 0, cost(0).length)

        ans
      } else {
        var probe = 1
        var ans = 0
        for (i <- 0 until cost(0).length) {
          if ((mask & probe) != 0) {
            ans += cost(k)(i)
          }
          probe <<= 1
        }

        ans
      }
    }

    solve(cost.length - 1, (1 << cost(0).length) - 1)
  }
}
