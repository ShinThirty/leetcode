package removeduplicatesfromsortedarrayii

object Solution {
  def removeDuplicates(nums: Array[Int]): Int = {
    nums.foldLeft(0) { case (nextInsertPos, currentNum) =>
      if (
        nextInsertPos < 2 ||
        currentNum != nums(nextInsertPos - 2)
      ) {
        nums(nextInsertPos) = currentNum
        nextInsertPos + 1
      } else {
        nextInsertPos
      }
    }
  }
}
