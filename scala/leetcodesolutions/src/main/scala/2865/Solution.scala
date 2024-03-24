package `2865`

import scala.collection.mutable

object Solution {
  def maximumSumOfHeights(maxHeights: List[Int]): Long = {
    val n = maxHeights.length

    val leftMax: Array[Long] = Array.fill(n)(0)
    val ascendingMaxHeightIndexes: mutable.ArrayDeque[Int] =
      mutable.ArrayDeque.empty
    maxHeights.zipWithIndex.foreach { case (maxHeight, index) =>
      while (
        ascendingMaxHeightIndexes.nonEmpty && maxHeights(
          ascendingMaxHeightIndexes.last
        ) > maxHeight
      ) {
        ascendingMaxHeightIndexes.removeLast()
      }
      if (ascendingMaxHeightIndexes.nonEmpty) {
        leftMax(index) = leftMax(ascendingMaxHeightIndexes.last)
        leftMax(
          index
        ) += maxHeight.toLong * (index - ascendingMaxHeightIndexes.last)
      } else {
        leftMax(index) = maxHeight.toLong * (index + 1)
      }
      ascendingMaxHeightIndexes.append(index)
    }

    val rightMax: Array[Long] = Array.fill(n)(0)
    val descendingMaxHeightIndexes: mutable.ArrayDeque[Int] =
      mutable.ArrayDeque.empty
    maxHeights.zipWithIndex.reverse.foreach { case (maxHeight, index) =>
      while (
        descendingMaxHeightIndexes.nonEmpty && maxHeights(
          descendingMaxHeightIndexes.head
        ) > maxHeight
      ) {
        descendingMaxHeightIndexes.removeHead()
      }
      if (descendingMaxHeightIndexes.nonEmpty) {
        rightMax(index) = rightMax(descendingMaxHeightIndexes.head)
        rightMax(
          index
        ) += maxHeight.toLong * (descendingMaxHeightIndexes.head - index)
      } else {
        rightMax(index) = maxHeight.toLong * (n - index)
      }
      descendingMaxHeightIndexes.prepend(index)
    }

    leftMax
      .zip(rightMax)
      .zipWithIndex
      .map({ case ((l, r), i) => l + r - maxHeights(i) })
      .max
  }
}
