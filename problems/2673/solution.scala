package leetcode2673

import scala.annotation.tailrec

object Solution {
  def minIncrements(n: Int, cost: Array[Int]): Int = {
    @tailrec
    def check(cost: Array[Int])(i: Int)(res: Int): (Array[Int], Int, Int) = {
      i match {
        case 0 => (cost, i, res)
        case j =>
          val p = j / 2
          cost(p - 1) += Math.max(cost(j - 1), cost(j - 2))
          val d = Math.abs(cost(j - 1) - cost(j - 2))
          check(cost)(j - 2)(res + d)
      }
    }

    check(cost)(n)(0)._3
  }
}
