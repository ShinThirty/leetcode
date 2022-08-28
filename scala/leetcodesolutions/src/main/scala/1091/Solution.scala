package `1091`

import scala.collection.mutable

object Solution {
  def shortestPathBinaryMatrix(grid: Array[Array[Int]]): Int = {
    val n = grid.length
    val shortestLength = Array.fill(n, n)(-1)
    val delta = Array(
      (-1, -1),
      (-1, 0),
      (-1, 1),
      (0, -1),
      (0, 1),
      (1, -1),
      (1, 0),
      (1, 1)
    )
    val frontier = mutable.ArrayDeque.empty[Tuple2[Int, Int]]
    if (grid(0)(0) == 0) {
      shortestLength(0)(0) = 1
      frontier.append((0, 0))
    }

    while (!frontier.isEmpty) {
      val (x, y) = frontier.removeHead()
      delta.view
        .map(d => (x + d._1, y + d._2))
        .filter(nc =>
          nc._1 >= 0 && nc._2 >= 0 && nc._1 < n && nc._2 < n && grid(nc._1)(
            nc._2
          ) == 0
        )
        .foreach {
          case (nx, ny) => {
            if (shortestLength(nx)(ny) == -1) {
              shortestLength(nx)(ny) = shortestLength(x)(y) + 1
              frontier.append((nx, ny))
            } else {
              shortestLength(nx)(ny) =
                Math.min(shortestLength(nx)(ny), shortestLength(x)(y) + 1)
            }
          }
        }
    }

    shortestLength(n - 1)(n - 1)
  }
}
