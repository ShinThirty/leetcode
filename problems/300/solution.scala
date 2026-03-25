package lis

object Solution {
  def lengthOfLIS(num: Array[Int]): Int = {
    val length = num.length
    // index: length of increasing subsequence, value: last value in the increasing subsequence
    val iss = Array.fill(length + 1)(10001)

    def accu: (Int, Int) => Int = (currentMax, currentElem) => {
      // find the longest is whose last element is smaller than currentElem
      var lo = 0
      var hi = currentMax
      while (lo < hi) {
        val me = lo + (hi - lo) / 2 + 1
        if (iss(me) < currentElem) {
          lo = me
        } else {
          hi = me - 1
        }
      }

      if (iss(lo + 1) > currentElem) {
        iss(lo + 1) = currentElem
      }

      Math.max(currentMax, lo + 1)
    }

    num.foldLeft(0)(accu)
  }

  def minSum(nums1: Array[Int], nums2: Array[Int]): Long = {
    val zeroes1 = nums1.count(_ == 0)
    val zeroes2 = nums2.count(_ == 0)

    val minSum1 = nums1.foldLeft(0L)((b, a) => b + a) + zeroes1
    val minSum2 = nums2.foldLeft(0L)((b, a) => b + a) + zeroes2

    if (
      (zeroes1 == 0 && minSum1 < minSum2) || (zeroes2 == 0 && minSum2 < minSum1)
    ) {
      -1L
    } else {
      Math.max(minSum1, minSum2)
    }
  }
}
