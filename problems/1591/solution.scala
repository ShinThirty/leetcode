package strangeprinterii

import scala.collection.mutable
import scala.util.Sorting

case class Rectangle(
    var r0: Byte,
    var c0: Byte,
    var r1: Byte,
    var c1: Byte,
    val color: Byte
) {
  def update(r: Int, c: Int): Unit = {
    val rb = r.toByte
    val cb = c.toByte

    if (rb < r0) r0 = rb
    if (rb > r1) r1 = rb
    if (cb < c0) c0 = cb
    if (cb > c1) c1 = cb
  }

  def getOverlays(grid: Array[Array[Int]]): Set[Byte] = {
    val overlays = mutable.Set[Byte]()
    for (i <- r0 to r1) {
      for (j <- c0 to c1) {
        if (grid(i)(j) != color) {
          overlays += grid(i)(j).toByte
        }
      }
    }
    overlays.toSet
  }
}

object Rectangle {
  def apply(r: Int, c: Int, color: Byte): Rectangle = {
    val rb = r.toByte
    val cb = c.toByte
    new Rectangle(rb, cb, rb, cb, color)
  }
}

object Solution {
  def isPrintable(targetGrid: Array[Array[Int]]): Boolean = {
    val rectangles = mutable.Map[Byte, Rectangle]()
    val nr = targetGrid.length
    val nc = targetGrid(0).length

    for (i <- 0 until nr) {
      for (j <- 0 until nc) {
        val color = targetGrid(i)(j).toByte
        rectangles.get(color) match {
          case Some(rectangle) => rectangle.update(i, j)
          case None => rectangles += ((color, Rectangle(i, j, color)))
        }
      }
    }

    val colors = rectangles.keySet.toArray
    Sorting.quickSort(colors)
    val graph = mutable.Map[Byte, Set[Byte]]()
    colors.foreach((color) => {
      val overlays = rectangles(color).getOverlays(targetGrid)
      graph += ((color, overlays))
    })

    val states = mutable.Map[Byte, Byte]()
    val NEW = 0.toByte
    val IN_PROGRESS = 1.toByte
    val COMPLETED = 2.toByte
    val EMPTY_SET = Set[Byte]()
    var ans = true

    def traverse(u: Byte): Unit = {
      val ustate = states.getOrElse(u, NEW)

      if (ustate == IN_PROGRESS) {
        ans = false
      } else if (ustate == NEW) {
        states.update(u, IN_PROGRESS)
        graph
          .getOrElse(u, EMPTY_SET)
          .foreach(traverse(_))
        states.update(u, COMPLETED)
      }
    }

    colors.foreach(traverse(_))

    ans
  }
}
