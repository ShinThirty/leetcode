package `834`

import scala.collection.mutable

object Solution {
  def sumOfDistancesInTree(n: Int, edges: Array[Array[Int]]): Array[Int] = {
    // Create adjacency list
    val children = Array.fill(n)(mutable.Set.empty[Int])
    edges.foreach((edge) => {
      children(edge(0)) += edge(1)
      children(edge(1)) += edge(0)
    })

    val count = Array.fill(n)(1)
    val dist = Array.fill(n)(0)

    // First process the root node (any node will do, here we use node 0)
    // Calculate the count and dist of root node
    // At the same time we have count of every child and part of dist of every child
    def initialize(node: Int, parent: Int): Unit = {
      children(node).foreach((child) => {
        if (child != parent) {
          initialize(child, node)
          count(node) += count(child)
          dist(node) += dist(child) + count(child)
        }
      })
    }
    initialize(0, -1)

    // Update the dist of every child
    // Prerequiste: dist(node) has been updated
    // Process: cPrime = number of nodes except the nodes in the tree rooted at child
    // Process: dPrime = dist of nodes except the nodes in the tree rooted at child
    // Process: dist(child) = dist(child) + dPrime + cPrime
    // Process: We can simplify the calculation by cancelling out the dist(child) term
    // Process: We are now good to update children of child
    def update(node: Int, parent: Int): Unit = {
      children(node).foreach((child) => {
        if (child != parent) {
          dist(child) = dist(node) - count(child) + n - count(child)
          update(child, node)
        }
      })
    }
    update(0, -1)

    dist
  }
}
