package `1931`

import scala.collection.mutable

object Solution {
  def colorTheGrid(m: Int, n: Int): Int = {
    
    val M = 1000000007

    val adjacentCols = mutable.Map.empty[Int, Seq[Int]]

    def getAdjacentColumns(prevCol: Int): Seq[Int] = {
      adjacentCols.getOrElseUpdate(prevCol, computeAdjacentColumns(prevCol))
    }

    def computeAdjacentColumns(prevCol: Int): Seq[Int] = {
      val curCols = mutable.Buffer.empty[Int]
      fillCol(0, prevCol, 0, curCols)
      curCols.toSeq
    }

    def fillCol(
        r: Int,
        prevCol: Int,
        curCol: Int,
        curCols: mutable.Buffer[Int]
    ): Unit = {
      if (r == m) {
        curCols += curCol
      } else {
        for (curCell <- 0 to 2) {
          if (
            !(isSameColorWithPrevCol(
              r,
              prevCol,
              curCell
            ) || isSameColorWithLastCell(r, curCol, curCell))
          ) {
            fillCol(r + 1, prevCol, curCol + (curCell << (r * 2)), curCols)
          }
        }
      }
    }

    def isSameColorWithPrevCol(r: Int, prevCol: Int, curCell: Int): Boolean = {
      ((prevCol >> (r * 2)) & 3) == curCell
    }

    def isSameColorWithLastCell(r: Int, curCol: Int, curCell: Int): Boolean = {
      r > 0 && (curCol >> ((r - 1) * 2)) == curCell
    }

    val waysOfColoring = mutable.Buffer.fill(n + 1)(mutable.Map.empty[Int, Int])

    def getWaysOfColoring(c: Int, lastCol: Int): Int = {
      waysOfColoring(c).getOrElseUpdate(
        lastCol,
        computeWaysOfColoring(c, lastCol)
      )
    }

    def computeWaysOfColoring(c: Int, lastCol: Int): Int = {
      if (c == 0) {
        1
      } else {
        var ans = 0
        for (prevCol <- getAdjacentColumns(lastCol)) {
          ans += getWaysOfColoring(c - 1, prevCol)
          ans %= M
        }
        ans
      }
    }

    getWaysOfColoring(n, (1 << (m * 2)) - 1)
  }
}
