package twobestnonoverlappingevents

import scala.collection.mutable

object Solution {
  case class Event(startTime: Int, endTime: Int, value: Int)

  def maxTwoEvents(events: Array[Array[Int]]): Int = {
    var maxValueFromPreviousNonOverlappingEvent = 0
    var maxSumValueFromTwoEvents = 0
    val eventsByStartTime = mutable.PriorityQueue.empty[Event](
      Ordering.by[Event, Int](_.startTime).reverse
    )
    val eventsByEndTime = mutable.PriorityQueue.empty[Event](
      Ordering.by[Event, Int](_.endTime).reverse
    )
    events.view
      .map(rawEvent => Event(rawEvent(0), rawEvent(1), rawEvent(2)))
      .foreach(eventsByStartTime.enqueue(_))

    while (eventsByStartTime.nonEmpty) {
      val second = eventsByStartTime.dequeue()
      while (
        eventsByEndTime.nonEmpty && eventsByEndTime.head.endTime < second.startTime
      ) {
        val first = eventsByEndTime.dequeue()
        maxValueFromPreviousNonOverlappingEvent =
          Math.max(maxValueFromPreviousNonOverlappingEvent, first.value)
      }
      maxSumValueFromTwoEvents = Math.max(
        maxSumValueFromTwoEvents,
        maxValueFromPreviousNonOverlappingEvent + second.value
      )
      eventsByEndTime.enqueue(second)
    }

    while (eventsByEndTime.nonEmpty) {
      maxValueFromPreviousNonOverlappingEvent = Math.max(
        maxValueFromPreviousNonOverlappingEvent,
        eventsByEndTime.dequeue().value
      )
    }

    Math.max(maxValueFromPreviousNonOverlappingEvent, maxSumValueFromTwoEvents)
  }
}
