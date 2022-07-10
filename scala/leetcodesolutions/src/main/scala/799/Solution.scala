package `799`

import scala.collection.mutable

object Solution {
  def champagneTower(poured: Int, query_row: Int, query_glass: Int): Double = {

    val c = mutable.Buffer.empty[Double]
    c.append(poured.toDouble)

    for (row <- 1 to query_row) {
      var p0 = 0.0
      var p1 = (c(0) - 1) / 2
      if (p1 < 0) {
        p1 = 0
      }
      c(0) = p0 + p1
      p0 = p1

      for (glass <- 1 to row - 1) {
        p1 = (c(glass) - 1) / 2
        if (p1 < 0) {
          p1 = 0
        }
        c(glass) = p0 + p1
        p0 = p1
      }

      c.append(p0)
    }

    if (c(query_glass) > 1) {
      1.0
    } else {
      (c(query_glass))
    }
  }
}
