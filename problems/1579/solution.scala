package removemaxnumberofedgestokeepgraphfullytraversable

import scala.collection.mutable
import scala.util.Sorting

case class Edge(val t: Int, val u: Int, val v: Int) extends Ordered[Edge] {

  override def compare(that: Edge): Int = {
    val dt = that.t - this.t
    if (dt != 0) {
      dt
    } else {
      val du = this.u - that.u
      if (du != 0) {
        du
      } else {
        this.v - that.v
      }
    }
  }

}

class DisjointSet(parents: Array[Int]) {

  var nSets = parents.size

  def find(u: Int): Int = {
    var root = u - 1
    while (parents(root) >= 0) {
      if (parents(parents(root)) >= 0) {
        parents(root) = parents(parents(root))
      }
      root = parents(root)
    }
    root
  }

  def union(u: Int, v: Int): Boolean = {
    val rootu = find(u)
    val rootv = find(v)

    if (rootu != rootv) {
      if (parents(rootu) < parents(rootv)) {
        performUnion(rootu, rootv)
      } else {
        performUnion(rootv, rootu)
      }

      nSets -= 1
      true
    } else {
      false
    }
  }

  def fullyMerged: Boolean = nSets == 1

  private def performUnion(rootu: Int, rootv: Int): Unit = {
    val sizeu = parents(rootu) + parents(rootv)
    parents(rootv) = rootu
    parents(rootu) = sizeu
  }
}

object DisjointSet {
  def apply(n: Int): DisjointSet = {
    val parents = Array.fill(n)(-1)
    new DisjointSet(parents)
  }
}

object Solution {
  def maxNumEdgesToRemove(n: Int, edges: Array[Array[Int]]): Int = {
    val nNodes = n
    val nEdges = edges.length

    // Split the given edges into two: edges of A and edges of B
    val edgesABuffer = mutable.Buffer[Edge]()
    val edgesBBuffer = mutable.Buffer[Edge]()

    edges.foreach { (edge) =>
      {
        val t = edge(0)
        val u = edge(1)
        val v = edge(2)
        if (t == 1 || t == 3) {
          edgesABuffer += Edge(t, u, v)
        }

        if (t == 2 || t == 3) {
          edgesBBuffer += Edge(t, u, v)
        }
      }
    }

    val edgesA = edgesABuffer.toArray
    val edgesB = edgesBBuffer.toArray

    Sorting.quickSort(edgesA)
    Sorting.quickSort(edgesB)

    val remainingEdges = mutable.Set[Edge]()
    val dsA = DisjointSet(n)
    edgesA.foreach((edge) => {
      val succeeded = dsA.union(edge.u, edge.v)
      if (succeeded) {
        remainingEdges += edge
      }
    })
    val dsB = DisjointSet(n)
    edgesB.foreach((edge) => {
      val succeeded = dsB.union(edge.u, edge.v)
      if (succeeded) {
        remainingEdges += edge
      }
    })

    if (dsA.fullyMerged && dsB.fullyMerged) {
      nEdges - remainingEdges.size
    } else {
      -1
    }
  }
}
