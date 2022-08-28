package `992`

import scala.collection.mutable

object Solution {
  def subarraysWithKDistinct(nums: Array[Int], k: Int): Int = {
    val countOfDigit = mutable.Map.empty[Int, Int]
    var start = 0
    var end = 0
    var ans = 0

    while (end < nums.length) {
      if (countOfDigit.size <= k) {
        if (countOfDigit.size == k) {
          ans += 1
        }
        countOfDigit()
      }
    }
  }
}
