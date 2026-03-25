package maximumnumberoftasksyoucanassign

import scala.util.Sorting
import scala.collection.mutable.ArrayDeque

object Solution {
  def maxTaskAssign(
      tasks: Array[Int],
      workers: Array[Int],
      pills: Int,
      strength: Int
  ): Int = {
    Sorting.quickSort(tasks)
    Sorting.quickSort(workers)

    val nTasks = tasks.length
    val nWorkers = workers.length

    def canAssign(k: Int): Boolean = {
      var j = nWorkers - 1
      val viableWorkers = ArrayDeque[Int]()
      var remainingPills = pills

      Range(0, k).foldRight(true)((i, validity) => {
        if (validity) {
          while (j >= nWorkers - k && workers(j) + strength >= tasks(i)) {
            viableWorkers += j
            j -= 1
          }

          viableWorkers.headOption match {
            case None => false
            case Some(head) =>
              if (workers(head) >= tasks(i)) {
                viableWorkers.removeHead()
                validity
              } else {
                viableWorkers.removeLast()
                remainingPills -= 1
                if (remainingPills < 0) {
                  false
                } else {
                  validity
                }
              }
          }
        } else {
          false
        }
      })
    }

    var lo = 0
    var hi = Math.min(nTasks, nWorkers)

    while (lo < hi) {
      val med = lo + (hi - lo) / 2 + 1
      if (canAssign(med)) {
        lo = med
      } else {
        hi = med - 1
      }
    }

    lo
  }
}
