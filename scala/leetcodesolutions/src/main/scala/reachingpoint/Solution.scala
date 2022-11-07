package reachingpoint

object Solution {
  def reachingPoints(sx: Int, sy: Int, tx: Int, ty: Int): Boolean = {
    def isPossible: Tuple2[Int, Int] => Boolean = { case (x, y) =>
      if ((x == sx && y == sy % sx) || (y == sy && x == sx % sy)) true
      else if (x < sx || y < sy) false
      else if (x > y) isPossible(x % y, y)
      else isPossible(x, y % x)
    }
    isPossible((tx, ty))
  }
}
