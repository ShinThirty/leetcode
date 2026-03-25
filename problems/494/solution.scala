package `494`

import scala.collection.mutable

object Solution {
  def findTargetSumWays(nums: Array[Int], target: Int): Int = {
    val n = nums.length
    val results = Array.fill(n)(mutable.Map.empty[Int, Int])

    def numberOfWays(i: Int)(t: Int): Int = {
      if (i == 0) {
        if (t == 0) {
          1
        } else {
          0
        }
      } else if (results(i).contains(t)) {
        results(i)(t)
      } else {
        results(i)(t) = numberOfWays(i - 1)(t - nums(i - 1)) + numberOfWays(
          i - 1
        )(t + nums(i - 1))
        results(i)(t)
      }
    }

    numberOfWays(n - 1)(target - nums(n - 1)) + numberOfWays(n - 1)(
      target + nums(n - 1)
    )
  }
}
