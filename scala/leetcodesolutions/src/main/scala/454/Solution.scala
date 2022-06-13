package `454`

import scala.collection.mutable

object Solution {
  def fourSumCount(
      nums1: Array[Int],
      nums2: Array[Int],
      nums3: Array[Int],
      nums4: Array[Int]
  ): Int = {
    val n = nums1.length
    val resultCountTable = mutable.Map.empty[Int, Int]

    for (i <- 0 until n) {
      for (j <- 0 until n) {
        val partialSum = nums1(i) + nums2(j)
        val existingCount = resultCountTable.getOrElseUpdate(partialSum, 0)
        resultCountTable.put(partialSum, existingCount + 1)
      }
    }

    var ans = 0
    for (i <- 0 until n) {
      for (j <- 0 until n) {
        val partialSum = nums3(i) + nums4(j)
        val existingCount = resultCountTable.getOrElseUpdate(-partialSum, 0)
        ans += existingCount
      }
    }

    ans
  }
}
