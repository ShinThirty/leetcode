package decreaseelementstomakearrayzigzag

object Solution {
  def movesToMakeZigzag(nums: Array[Int]): Int = {
    def moves: Array[Int] => Int => Int = nums => res => {
      nums match {
        case Array() => res
        case Array(_) => res
        case Array(x1, x2) =>
          val change = if (x2 < x1) 0 else x2 - x1 + 1
          res + change
        case Array(x1, x2, x3, rest@_*) =>
          val bound = Math.min(x1, x3)
          val change = if (x2 < bound) 0 else x2 - bound + 1
          moves(x3 +: rest.toArray)(res + change)
      }
    }

    Math.min(moves(nums)(0), moves(Int.MaxValue +: nums)(0))
  }
}
