package `581`

object Solution {
  def findUnsortedSubarray(nums: Array[Int]): Int = {
    var left = -1

    def getValueByIndex(idx: Int): Int = {
      if (idx < 0) {
        -100001
      } else if (idx >= nums.length) {
        100001
      } else {
        nums(idx)
      }
    }

    for (idx <- 0 until nums.length) {
      if (nums(idx) >= getValueByIndex(left)) {
        if (left == idx - 1) {
          left = idx
        }
      } else {
        while (left > -1 && getValueByIndex(left) > nums(idx)) {
          left -= 1
        }
      }
    }

    var right = nums.length
    for (idx <- nums.length - 1 to 0 by -1) {
      if (nums(idx) <= getValueByIndex(right)) {
        if (right == idx + 1) {
          right = idx
        }
      } else {
        while (right < nums.length && getValueByIndex(right) < nums(idx)) {
          right += 1
        }
      }
    }

    val length = right - left - 1
    if (length < 0) {
      0
    } else {
      length
    }
  }
}
