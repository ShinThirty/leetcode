package `2018`

object Solution {
  def placeWordInCrossword(board: Array[Array[Char]], word: String): Boolean = {
    val m = board.length
    val n = board(0).length
    val blocked: Int => Int => Boolean = x => y => x < 0 || x >= m || y < 0 || y >= n || board(x)(y) == '#'

    def canWordBePlaced(x: Int)(y: Int)(dx: Int)(dy: Int): Boolean = {
      if (!blocked(x - dx)(y - dy)) {
        false
      } else {
        var cx = x
        var cy = y
        var i = 0
        var canBePlaced = true
        while (canBePlaced && i < word.length) {
          val c = word(i)
          canBePlaced &= !blocked(cx)(cy) && (board(cx)(cy) == ' ' || board(cx)(cy) == c)
          cx += dx
          cy += dy
          i += 1
        }
        canBePlaced && blocked(cx)(cy)
      }
    }

    var canBePlaced = false
    for (x <- Range(0, m); y <- Range(0, n); delta <- List((-1, 0), (1, 0), (0, -1), (0, 1))) {
      canBePlaced |= canWordBePlaced(x)(y)(delta._1)(delta._2)
    }
    canBePlaced
  }
}
