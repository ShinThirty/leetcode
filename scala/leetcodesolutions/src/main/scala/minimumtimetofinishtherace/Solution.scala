package minimumtimetofinishtherace

import scala.collection.mutable.Buffer

object Solution {
  def minimumFinishTime(
      tires: Array[Array[Int]],
      changeTime: Int,
      numLaps: Int
  ): Int = {
    val minimumTimeNoChange = Buffer[Int]()
    for (Array(f, r) <- tires) {
      var currentTime = f
      if (0 == minimumTimeNoChange.length) {
        minimumTimeNoChange += currentTime
      } else {
        val alt = Math.min(minimumTimeNoChange(0), currentTime)
        minimumTimeNoChange.update(0, alt)
      }
      var i = 1
      var acc = currentTime
      currentTime = currentTime * r
      while (currentTime < changeTime + f) {
        if (i == minimumTimeNoChange.length) {
          minimumTimeNoChange += acc + currentTime
        } else {
          val alt = Math.min(minimumTimeNoChange(i), acc + currentTime)
          minimumTimeNoChange.update(i, alt)
        }
        i += 1
        acc += currentTime
        currentTime = currentTime * r
      }
    }

    val minimumTime = Buffer[Int]()
    minimumTime += minimumTimeNoChange(0)
    var i = 1
    while (i < numLaps) {
      minimumTime += minimumTime(i - 1) + changeTime + minimumTimeNoChange(0)
      var j = i - 2
      while (j >= 0 && i - 1 - j < minimumTimeNoChange.length) {
        val alt = minimumTime(j) + changeTime + minimumTimeNoChange(i - 1 - j)
        minimumTime.update(i, Math.min(minimumTime(i), alt))
        j -= 1
      }
      if (i < minimumTimeNoChange.length) {
        minimumTime.update(i, Math.min(minimumTime(i), minimumTimeNoChange(i)))
      }
      i += 1
    }

    minimumTime.last
  }
}
