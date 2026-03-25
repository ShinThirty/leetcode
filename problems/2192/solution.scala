package ancestorsofanodeindag

import scala.collection.mutable.Buffer
import scala.collection.mutable.Map
import scala.collection.mutable.SortedSet

object Solution {
  def getAncestors(n: Int, edges: Array[Array[Int]]): List[List[Int]] = {
    val ancestors: Buffer[Buffer[Int]] = Buffer.fill(n)(Buffer())

    val al: Buffer[Buffer[Int]] = Buffer.fill(n)(Buffer())
    for (Array(u, v) <- edges) {
      al(u) += v
    }

    lazy val traverse: (Int, Int) => Unit = (u: Int, ancestor: Int) => {
      if (u != ancestor) {
        ancestors(u).lastOption match {
          case Some(last) => {
            if (last < ancestor) {
              ancestors(u) += ancestor
            }
          }
          case None => {
            ancestors(u) += ancestor
          }
        }
      }
      for (v <- al(u)) {
        traverse(v, ancestor)
      }
    }

    for (i <- 0 until n) {
      traverse(i, i)
    }

    val result: Buffer[List[Int]] = Buffer()
    for (i <- 0 until n) {
      result += ancestors(i).toList
    }

    result.toList
  }
}
