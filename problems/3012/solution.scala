package `3012`

object Solution {
  def minimumArrayLength(nums: Array[Int]): Int = {
    val minElem = nums.min
    val minOccurrences = nums.count(_ == minElem)
    val elemNotDivisibleByMinExists =
      nums.view.filter(x => x > minElem && x % minElem != 0).nonEmpty

    if (elemNotDivisibleByMinExists) 1 else (minOccurrences + 1) / 2
  }
}
