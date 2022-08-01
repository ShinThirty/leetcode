package `675`

import scala.collection.mutable

object Solution {
  def cutOffTree(forest: List[List[Int]]): Int = {
    // next trees map: height -> (x, y)
    val nextTrees = mutable.SortedMap.empty[Int, Tuple2[Int, Int]]
    val m = forest.length
    val n = forest(0).length

    for (i <- 0 until m) {
      for (j <- 0 until n) {
        if (forest(i)(j) > 1) {
          val h = forest(i)(j)
          nextTrees.put(h, (i, j))
        }
      }
    }

    val delta = Array((-1, 0), (1, 0), (0, -1), (0, 1))

    def dist(start: Tuple2[Int, Int], end: Tuple2[Int, Int]): Int = {
      val black = Array.fill(m, n)(false)
      val frontier = mutable.Queue.empty[Tuple3[Int, Int, Int]]
      frontier.enqueue((start._1, start._2, 0))
      while (!frontier.isEmpty) {
        val (x, y, d) = frontier.dequeue()
        if (end == (x, y)) {
          return d
        } else {
          delta.foreach {
            case (dx, dy) => {
              val nx = x + dx
              val ny = y + dy
              if (
                0 <= nx && nx < m && 0 <= ny && ny < n && forest(nx)(
                  ny
                ) != 0 && !black(nx)(ny)
              ) {
                black(nx)(ny) = true
                frontier.enqueue((nx, ny, d + 1))
              }
            }
          }
        }
      }

      return -1
    }

    var ans = 0
    var start = (0, 0)
    nextTrees.foreach {
      case (h, (r, c)) => {
        var end = (r, c)
        val sec = dist(start, end)
        if (sec == -1) {
          return -1
        } else {
          ans += sec
        }
        start = (r, c)
      }
    }
    ans
  }
}
