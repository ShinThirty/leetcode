package `945`

object Solution {
  def minIncrementForUnique(nums: Array[Int]): Int = {
    var nextAvailable = 0
    val sortedNums = nums.sorted

    sortedNums.foldLeft(0)((count, num) => {
      if (nextAvailable <= num) {
        nextAvailable = num + 1
      } else {
        nextAvailable += 1
      }
      count + nextAvailable - num - 1
    })
  }
}
