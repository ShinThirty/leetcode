package pathwithminimumeffort

import scala.collection.mutable

object Solution {
  def minimumEffortPath(heights: Array[Array[Int]]): Int = {
    val m = heights.length
    val n = heights(0).length
    val delta = Array((-1, 0), (1, 0), (0, -1), (0, 1))
    val dist: Int => Int => Int => Int => Int = cx => cy => nx => ny => Math.abs(heights(cx)(cy) - heights(nx)(ny))

    def validRoute(effort: Int): Boolean = {
      val frontier = mutable.ArrayDeque.empty[(Int, Int)]
      val black = mutable.Set.empty[(Int, Int)]
      frontier.append((0, 0))
      var targetReachable = m == 1 && n == 1
      val validNextCell: Int => Int => Boolean = x => y => 0 <= x && x < m && 0 <= y && y < n && !black.contains((x, y))

      while (!targetReachable && frontier.nonEmpty) {
        val (cx, cy) = frontier.removeHead()
        delta.view.map(d => (cx + d._1, cy + d._2)).filter(c => validNextCell(c._1)(c._2)).foreach {
          case (x, y) =>
            if (dist(x)(y)(cx)(cy) <= effort) {
              black.add((x, y))
              if (x == m - 1 && y == n - 1) {
                targetReachable = true
              }
              frontier.append((x, y))
            }
        }
      }

      targetReachable
    }

    var lo = 0
    var hi = heights.view.map(_.max).max
    while (lo < hi) {
      val med = lo + (hi - lo) / 2
      if (validRoute(med)) {
        hi = med
      } else {
        lo = med + 1
      }
    }

    lo
  }
}