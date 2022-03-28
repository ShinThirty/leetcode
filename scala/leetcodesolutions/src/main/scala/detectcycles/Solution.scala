package detectcycles

object Solution {
  def containsCycle(grid: Array[Array[Char]]): Boolean = {
    val rows = grid.length
    val cols = grid(0).length
    val visited = Array.fill(rows, cols)(false)
    val directions = Seq((-1, 0), (1, 0), (0, -1), (0, 1))

    def traverse(x1: Int, y1: Int, x0: Int, y0: Int): Boolean = {
      var hasCycle = false
      if (!visited(x1)(y1)) {
        visited(x1)(y1) = true
        for ((dx, dy) <- directions) {
          val x2 = x1 + dx
          val y2 = y1 + dy
          if (0 <= x2 && x2 < rows && 0 <= y2 && y2 < cols) {
            if (x2 != x0 || y2 != y0) {
              if (grid(x2)(y2) == grid(x1)(y1)) {
                if (traverse(x2, y2, x1, y1)) {
                  hasCycle = true
                }
              }
            }
          }
        }
      } else {
        hasCycle = true
      }

      hasCycle
    }

    var hasCycle = false
    for (i <- 0 until rows) {
      for (j <- 0 until cols) {
        if (!visited(i)(j)) {
          if (traverse(i, j, -1, -1)) {
            hasCycle = true
          }
        }
      }
    }
    hasCycle
  }
}
