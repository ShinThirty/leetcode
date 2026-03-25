package `436`

import scala.collection.mutable

object Solution {
  def findRightInterval(intervals: Array[Array[Int]]): Array[Int] = {
    val n = intervals.length
    val startToIndex = mutable.Map.empty[Int, Int]
    intervals.view.zipWithIndex.foreach { case (interval, idx) =>
      startToIndex.put(interval(0), idx)
    }
    intervals.sortInPlaceBy((interval) => interval(0))

    val ans = Array.fill(n)(-1)
    val workingQueue = mutable.PriorityQueue.empty[Array[Int]](
      Ordering.by[Array[Int], Int]((interval) => interval(1)).reverse
    )

    intervals.foreach((interval) => {
      val curIndex = startToIndex(interval(0))
      workingQueue.enqueue(interval)
      while (!workingQueue.isEmpty && workingQueue.head(1) <= interval(0)) {
        val queueHead = workingQueue.dequeue()
        val queueHeadIndex = startToIndex(queueHead(0))
        ans(queueHeadIndex) = curIndex
      }
    })

    ans
  }
}
