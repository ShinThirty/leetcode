package `992`

import scala.collection.mutable

object Solution {
  def subarraysWithKDistinct(nums: Array[Int], k: Int): Int = {
    val countOfDigit = mutable.Map.empty[Int, Int]
    var start = 0
    var end = 0
    var ans = 0

    while (end < nums.length && countOfDigit.size < k) {
      countOfDigit.updateWith(nums(end)) {
        case Some(count) => Some(count + 1)
        case None        => Some(1)
      }
      end += 1
    }
    if (countOfDigit.size < k) {
      0
    } else {
      while (end <= nums.length) {
        var num = 1
        while (countOfDigit(nums(start)) > 1) {
          countOfDigit(nums(start)) -= 1
          num += 1
          start += 1
        }
        ans += num

        while (end < nums.length && countOfDigit.contains(nums(end))) {
          countOfDigit(nums(end)) += 1
          end += 1
          while (countOfDigit(nums(start)) > 1) {
            countOfDigit(nums(start)) -= 1
            num += 1
            start += 1
          }
          ans += num
        }

        if (end < nums.length) {
          countOfDigit(nums(end)) = 1
          end += 1
          countOfDigit.remove(nums(start))
          start += 1
        } else {
          end += 1
        }
      }
      ans
    }
  }
}
