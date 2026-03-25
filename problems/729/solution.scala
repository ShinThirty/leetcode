package `729`

import scala.collection.mutable

class MyCalendar() {

  val events = mutable.TreeMap.empty[Int, Int]

  def book(start: Int, end: Int): Boolean = {
    val noOverlap = events.maxBefore(start).map(_._2 <= start).getOrElse(true) && events
      .minAfter(start)
      .map(_._1 >= end)
      .getOrElse(true)

    if (noOverlap) {
      events.put(start, end)
    }
    noOverlap
  }
}
