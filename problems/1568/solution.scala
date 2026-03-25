package minimumnumberofdaystodisconnectisland

import scala.collection.mutable.Queue

case class Cell(x: Int, y: Int) {
  def +(delta: Cell): Cell = {
    Cell(x + delta.x, y + delta.y)
  }

  def isValid(rows: Int, cols: Int): Boolean = {
    0 <= x && x < rows && 0 <= y && y < cols
  }
}

object Solution {
  def minDays(grid: Array[Array[Int]]): Int = {
    def traverse(
        grid: Array[Array[Int]],
        black: Array[Array[Boolean]],
        start: Cell
    ): Unit = {
      val n = grid.length
      val m = grid(0).length
      val q = Queue[Cell]()
      q.enqueue(start)
      black(start.x)(start.y) = true
      val deltas = Seq(Cell(-1, 0), Cell(1, 0), Cell(0, -1), Cell(0, 1))

      while (!q.isEmpty) {
        val cur = q.dequeue()
        deltas.view
          .map(_ + cur)
          .filter(_.isValid(n, m))
          .filter(nn => !black(nn.x)(nn.y))
          .filter(nn => grid(nn.x)(nn.y) == 1)
          .foreach(nn => {
            black(nn.x)(nn.y) = true
            q.enqueue(nn)
          })
      }
    }

    def countIslands(grid: Array[Array[Int]]): Int = {
      val n = grid.length
      val m = grid(0).length
      val black = Array.fill(n, m)(false)

      var count = 0
      for (i <- 0 until n) {
        for (j <- 0 until m) {
          if (grid(i)(j) == 1 && !black(i)(j)) {
            traverse(grid, black, Cell(i, j))
            count += 1
          }
        }
      }

      count
    }

    val initialIslands = countIslands(grid)
    var withOne = false
    if (initialIslands > 1) {
      0
    } else {
      val n = grid.length
      val m = grid(0).length
      for (i <- 0 until n) {
        for (j <- 0 until m) {
          if (grid(i)(j) == 1) {
            grid(i)(j) = 0
            val islands = countIslands(grid)
            grid(i)(j) = 1
            if (islands == 0 || islands > 1) {
              withOne = true
            }
          }
        }
      }

      if (withOne) {
        1
      } else {
        2
      }
    }
  }
}
