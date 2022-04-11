package numberofpairsofinterchangeablerectangles

case class Fraction(numerator: Int, denominator: Int)

object Fraction {
  def apply(numerator: Int, denominator: Int): Fraction = {
    val gcd = greatestCommonDivisor(numerator, denominator)
    new Fraction(numerator / gcd, denominator / gcd)
  }

  private def greatestCommonDivisor(a: Int, b: Int): Int = {
    if (a == b) {
      a
    } else {
      var one = a
      var two = b
      var t = one
      if (one > two) {
        one = two
        two = t
      }

      // one < two
      while (one > 0) {
        t = two % one
        two = one
        one = t
      }
      two
    }
  }
}

object Solution {
  def interchangeableRectangles(rectangles: Array[Array[Int]]): Long = {
    rectangles.groupBy((rectangle) => Fraction(rectangle(0), rectangle(1)))

    0L
  }
}
