package removecoveredintervals

object Solution {
  def removeCoveredIntervals(intervals: Array[Array[Int]]): Int = {
    val sortedIntervals = intervals.sorted(Ordering.by[Array[Int], Int](interval => interval(0)).orElseBy(interval => -interval(1)))
    val (covered, _) = sortedIntervals.view.foldLeft((0, 0)) { case ((covered, mRight), interval) => if (interval(1) <= mRight) (covered + 1, mRight) else (covered, interval(1)) }
    intervals.length - covered
  }
}
