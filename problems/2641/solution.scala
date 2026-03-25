package cousinsinbinarytreeii

import scala.collection.mutable

class TreeNode(
    _value: Int = 0,
    _left: TreeNode = null,
    _right: TreeNode = null
) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}

object Solution {
  def replaceValueInTree(root: TreeNode): TreeNode = {
    val queue: mutable.Queue[TreeNode] = mutable.Queue.empty[TreeNode]
    val stack: mutable.Stack[(TreeNode, Int)] =
      mutable.Stack.empty[(TreeNode, Int)]
    val layerSums: mutable.Buffer[Int] = mutable.Buffer.empty[Int]

    queue.enqueue(root)

    var lvl = 0
    while (queue.nonEmpty) {
      val l = queue.length
      var layerTotal = 0
      for (_ <- 1 to l) {
        val node = queue.dequeue()
        stack.push((node, lvl))

        node.value = 0
        if (node.left != null) {
          queue.enqueue(node.left)
          node.value += node.left.value
        }
        if (node.right != null) {
          queue.enqueue(node.right)
          node.value += node.right.value
        }
        layerTotal += node.value
      }
      layerSums += layerTotal
      lvl += 1
    }

    while (stack.nonEmpty) {
      val (node, lvl) = stack.pop()
      if (node.left != null || node.right != null) {
        val layerSum = layerSums(lvl)
        if (node.left != null) {
          node.left.value = layerSum - node.value
        }
        if (node.right != null) {
          node.right.value = layerSum - node.value
        }
      }
    }

    root.value = 0
    root
  }
}
