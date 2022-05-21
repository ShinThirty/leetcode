package `1905`

import scala.collection.mutable

object Solution {
  def countSubIslands(
      grid1: Array[Array[Int]],
      grid2: Array[Array[Int]]
  ): Int = {
    val m = grid1.length
    val n = grid1(0).length

    val q = mutable.ArrayDeque.empty[(Int, Int)]
    val black = Array.fill(m, n)(false)
    val delta = Array((-1, 0), (1, 0), (0, -1), (0, 1))

    def traverse(x: Int, y: Int): Int = {
      var ans = 1

      q.append((x, y))
      black(x)(y) = true

      while (!q.isEmpty) {
        val (cx, cy) = q.removeHead()
        if (grid1(cx)(cy) == 0) {
          ans = 0
        }

        for ((dx, dy) <- delta) {
          val nx = cx + dx
          val ny = cy + dy

          if (
            0 <= nx && nx < m && 0 <= ny && ny < n && !black(nx)(ny) && grid2(
              nx
            )(ny) == 1
          ) {
            black(nx)(ny) = true
            q.append((nx, ny))
          }
        }
      }

      ans
    }

    var ans = 0

    for (i <- 0 until m) {
      for (j <- 0 until n) {
        if (!black(i)(j) && grid2(i)(j) == 1) {
          ans += traverse(i, j)
        }
      }
    }

    ans
  }
}
