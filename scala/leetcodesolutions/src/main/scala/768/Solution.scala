package `768`

import scala.collection.mutable

object Solution {
  def maxChunksToSorted(arr: Array[Int]): Int = {
    val valueToIdx = mutable.Map.empty[Int, Int]
    val sortedArr = arr.sorted.zipWithIndex.foreach { case (num, idx) =>
      valueToIdx.getOrElseUpdate(num, idx)
    }

    val seenValue = mutable.Map.empty[Int, Int]
    var maxIdx = 0

    arr.zipWithIndex.foldLeft(0) {
      case (chunks, (num, idx)) => {
        val seenTimes = seenValue.getOrElseUpdate(num, 0)
        maxIdx = Math.max(maxIdx, valueToIdx(num) + seenTimes)
        seenValue.put(num, seenTimes + 1)

        if (maxIdx == idx) {
          chunks + 1
        } else {
          chunks
        }
      }
    }
  }
}
