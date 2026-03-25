package `2050`

import scala.collection.mutable

object Solution {
  def minimumTime(
      n: Int,
      relations: Array[Array[Int]],
      time: Array[Int]
  ): Int = {
    val minTimeNeeded = Array.fill(n)(-1)
    val nextCourses = Array.fill(n)(mutable.Set.empty[Int])
    relations.foreach(r => {
      nextCourses(r(0) - 1) += r(1) - 1
    })

    def computeMinTimeNeeded(i: Int): Int = {
      if (minTimeNeeded(i) == -1) {
        var ans = 0
        nextCourses(i).foreach(ni => {
          val alt = computeMinTimeNeeded(ni)
          if (alt > ans) {
            ans = alt
          }
        })

        minTimeNeeded(i) = time(i) + ans
      }

      minTimeNeeded(i)
    }

    var ans = -1
    for (i <- 0 until n) {
      val alt = computeMinTimeNeeded(i)
      if (ans == -1 || alt > ans) {
        ans = alt
      }
    }

    ans
  }
}
