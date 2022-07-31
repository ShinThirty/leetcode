package `731`

import scala.collection.mutable

class MyCalendarTwo() {

  private val calendar = mutable.Buffer.empty[Tuple2[Int, Int]]
  private val overlap = mutable.Buffer.empty[Tuple2[Int, Int]]

  def book(start: Int, end: Int): Boolean = {
    if (
      overlap.foldLeft(false) { case (res, (s, e)) =>
        res || (start < e && end > s)
      }
    ) {
      false
    } else {
      calendar.view
        .filter({ case (s, e) => start < e && end > s })
        .map({ case (s, e) => Tuple2(Math.max(s, start), Math.min(e, end)) })
        .foreach({ case (s, e) => overlap += ((s, e)) })

      calendar += ((start, end))
      true
    }
  }

}
