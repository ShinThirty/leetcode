package `1184`

object Solution {
  def distanceBetweenBusStops(
      distance: Array[Int],
      start: Int,
      destination: Int
  ): Int = {
    val n = distance.size
    var distance1 = 0
    var cur = start
    while (cur != destination) {
      distance1 += distance(cur)
      cur = (cur + 1) % n
    }

    var distance2 = 0
    cur = destination
    while (cur != start) {
      distance2 += distance(cur)
      cur = (cur + 1) % n
    }

    Math.min(distance1, distance2)
  }
}
