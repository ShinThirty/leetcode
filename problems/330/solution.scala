package `330`

object Solution {
  def minPatches(nums: Array[Int], n: Int): Int = {
    var maxNum = 0 // upper bound of the generation, initially at 0
    var i = 0

    var cnt = 0
    // maxNum can be overflown
    while (0 <= maxNum && maxNum < n) {
      if (i < nums.size && nums(i) <= maxNum + 1) {
        // maxNum + 1 can be formed, grow the upper bound to maxNum + nums(i)
        maxNum += nums(i)
        i += 1
      } else {
        // maxNum + 1 cannot be formed, add maxNum + 1 to the array and grow the upper bound to 2 * maxNum + 1
        maxNum += maxNum + 1
        cnt += 1
      }
    }

    cnt
  }
}
