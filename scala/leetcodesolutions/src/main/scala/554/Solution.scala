package `554`

import scala.collection.mutable

object Solution {
  def leastBricks(wall: List[List[Int]]): Int = {
    val breakPoints = mutable.Map.empty[Int, Int]

    wall.foreach {
      case (row) => {
        var breakPoint = 0
        for (i <- 0 until row.length - 1) {
          val width = row(i)
          breakPoint += width
          val numberOfBreakPoint =
            breakPoints.getOrElseUpdate(breakPoint, 0) + 1
          breakPoints.put(breakPoint, numberOfBreakPoint)
        }
      }
    }

    if (breakPoints.isEmpty) {
      wall.length
    } else {
      wall.length - breakPoints.values.max
    }
  }
}
