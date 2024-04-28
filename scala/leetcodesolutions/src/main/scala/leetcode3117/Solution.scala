package leetcode3117

import scala.collection.mutable

object Solution {

  def minimumValueSum(nums: Array[Int], andValues: Array[Int]): Int = {
    val cache: mutable.Map[Int, mutable.Map[Int, mutable.Map[Int, Int]]] =
      mutable.Map.empty[Int, mutable.Map[Int, mutable.Map[Int, Int]]]

    def read(i: Int, j: Int, p: Int): Option[Int] = {
      cache get i flatMap (_ get j flatMap (_ get p))
    }

    def write(i: Int, j: Int, p: Int, v: Int): Unit = {
      val row =
        cache getOrElseUpdate (i, mutable.Map.empty[Int, mutable.Map[Int, Int]])
      val column = row getOrElseUpdate (j, mutable.Map.empty[Int, Int])
      column += p -> v
    }

    def addWithBound(a: Int)(b: Int): Int = {
      val c = a + b
      if (c < a || c < b) {
        Int.MaxValue
      } else {
        c
      }
    }

    def f(i: Int, j: Int, p: Int): Int = {
      if (i == nums.length && j == andValues.length) {
        p match {
          case -1 => 0
          case _  => Int.MaxValue
        }
      } else if (i == nums.length) {
        Int.MaxValue
      } else {
        read(i, j, p) match {
          case Some(value) => value
          case None =>
            val v = c(i, j, p)
            write(i, j, p, v)
            v
        }
      }
    }

    def c(i: Int, j: Int, p: Int): Int = {
      val andValue = p & nums(i)
      if (j == andValues.length || andValue < andValues(j)) {
        Int.MaxValue
      } else if (andValue == andValues(j)) {
        val alt1 = addWithBound(nums(i))(f(i + 1, j + 1, -1))
        val alt2 = f(i + 1, j, andValue)
        Math.min(alt1, alt2)
      } else {
        f(i + 1, j, andValue)
      }
    }

    f(0, 0, -1) match {
      case Int.MaxValue => -1
      case v            => v
    }
  }

}
