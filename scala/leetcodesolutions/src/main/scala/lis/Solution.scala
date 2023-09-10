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
}
