package `2013`

import scala.collection.mutable

class DetectSquares() {

  val pointsByX = new mutable.HashMap[Int, mutable.Map[Int, Int]]
  val pointsByY = new mutable.HashMap[Int, mutable.Map[Int, Int]]

  def add(point: Array[Int]): Unit = {
    val x = point(0)
    val y = point(1)

    val ys = pointsByX.getOrElseUpdate(x, new mutable.HashMap[Int, Int])
    val oldY = ys.getOrElseUpdate(y, 0)
    ys.put(y, oldY + 1)

    val xs = pointsByY.getOrElseUpdate(y, new mutable.HashMap[Int, Int])
    val oldX = xs.getOrElseUpdate(x, 0)
    xs.put(x, oldX + 1)
  }

  def count(point: Array[Int]): Int = {
    val x = point(0)
    val y = point(1)

    var ans = 0

    val xs = pointsByY.get(y)
    xs.foreach(xs => {
      for ((x_prime, n) <- xs) {
        if (x_prime != x) {
          val d = x - x_prime
          val n11 = getNumberOfPoints(x, y - d)
          val n21 = getNumberOfPoints(x_prime, y - d)
          ans += n * n11 * n21
          val n12 = getNumberOfPoints(x, y + d)
          val n22 = getNumberOfPoints(x_prime, y + d)
          ans += n * n12 * n22
        }
      }
    })

    ans
  }

  private def getNumberOfPoints(x: Int, y: Int): Int = {
    pointsByX.get(x).flatMap(ys => ys.get(y)).getOrElse(0)
  }

}

/**
  * Your DetectSquares object will be instantiated and called as such:
  * var obj = new DetectSquares()
  * obj.add(point)
  * var param_2 = obj.count(point)
  */