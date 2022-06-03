package `769`

object Solution {
  def maxChunksToSorted(arr: Array[Int]): Int = {
    var partitions = 0

    var max = 0

    arr.zipWithIndex.foreach {
      case (num, idx) => {
        max = Math.max(max, num)

        if (max == idx) {
          partitions += 1
        }
      }
    }

    partitions
  }
}
