package maximumsubsequencescore

import scala.collection.mutable.PriorityQueue

object Solution {
  def maxScore(nums1: Array[Int], nums2: Array[Int], k: Int): Long = {
    val nums = nums1.map(a => -a) zip nums2
    nums.sortInPlaceBy { case (_, n2) =>
      n2
    }
    val reverseIterator = nums.reverseIterator
    val pq = PriorityQueue.empty[Int]
    var s1 = 0L
    var answer = -1L

    while (reverseIterator.hasNext) {
      val (n1, n2) = reverseIterator.next()
      if (pq.size == k) {
        s1 -= pq.dequeue()
      }
      pq.enqueue(n1)
      s1 += n1

      if (pq.size == k) {
        answer = Math.max(answer, -s1 * n2)
      }
    }

    answer
  }
}
