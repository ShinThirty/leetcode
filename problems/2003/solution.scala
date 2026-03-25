package smallestmissinggeneticvalueineachsubtree

import scala.collection.mutable.Buffer

class TreeNode(val id: Int, val value: Int, val children: Buffer[TreeNode]) {
  def addChild(node: TreeNode): Unit = {
    children += node
  }
}

object TreeNode {
  def apply(id: Int, value: Int): TreeNode = {
    new TreeNode(id, value, Buffer[TreeNode]())
  }
}

object Solution {
  def smallestMissingValueSubtree(
      parents: Array[Int],
      nums: Array[Int]
  ): Array[Int] = {
    val n = nums.length
    val treeNodes = nums.view.zipWithIndex
      .map((tuple) => TreeNode(tuple._2, tuple._1))
      .toIndexedSeq
    treeNodes.foreach((treeNode) => {
      val id = treeNode.id
      val parent_id = parents(id)
      if (parent_id != -1) {
        val parentNode = treeNodes(parent_id)
        parentNode.addChild(treeNode)
      }
    })

    val black = Array.fill(100000)(false)
    var nextMissing = 0
    val ans = Array.fill(n)(1)

    def getParent(node: Option[TreeNode]): Option[TreeNode] = {
      node.flatMap((treeNode) => {
        val parent_id = parents(treeNode.id)
        if (parent_id != -1) {
          Some(treeNodes(parent_id))
        } else {
          None
        }
      })
    }

    def traverseAndBlacken(node: TreeNode, excludeChildId: Int): Unit = {
      val value = node.value
      black(value - 1) = true
      while (nextMissing < black.length && black(nextMissing)) {
        nextMissing += 1
      }

      node.children.view
        .filter(_.id != excludeChildId)
        .foreach(traverseAndBlacken(_, excludeChildId))
    }

    def populateAnswer(startNode: TreeNode): Unit = {
      traverseAndBlacken(startNode, -1)
      var currentNode = Option(startNode)
      while (currentNode.isDefined) {
        val prev_id = currentNode.get.id
        ans(prev_id) = nextMissing + 1
        currentNode = getParent(currentNode)
        currentNode.foreach(traverseAndBlacken(_, prev_id))
      }
    }

    treeNodes
      .find(_.value == 1)
      .foreach(populateAnswer(_))
    ans
  }
}
