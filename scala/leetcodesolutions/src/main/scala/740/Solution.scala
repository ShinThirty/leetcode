package `740`

import scala.collection.mutable

object Solution {
  def deleteAndEarn(nums: Array[Int]): Int = {
    val freq = nums.groupMapReduce(num => num)(value => 1)(_ + _)
    val individualNums = freq.keySet.toArray.sorted
    val n = individualNums.length
    var prev2 = 0
    var prev1 = individualNums(0) * freq(individualNums(0))
    var cur = 0

    for (i <- 2 to n) {
      val pickCurrent = individualNums(i - 1) * freq(individualNums(i - 1))
      if (individualNums(i - 2) + 1 == individualNums(i - 1)) {
        cur = Math.max(prev1, prev2 + pickCurrent)
      } else {
        cur = prev1 + pickCurrent
      }

      prev2 = prev1
      prev1 = cur
      cur = 0
    }

    prev1
  }
}
