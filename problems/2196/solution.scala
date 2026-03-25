package createbinarytreefromdescriptions

import scala.collection.mutable.Map
import scala.collection.mutable.Set

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
  def createBinaryTree(descriptions: Array[Array[Int]]): TreeNode = {
    val value2Node = Map[Int, TreeNode]()
    val notRoot = Set[Int]()

    for (Array(pv, cv, isLeft) <- descriptions) {
      val pnode: TreeNode = value2Node.get(pv) match {
        case Some(node) =>
          node
        case None => {
          val t = new TreeNode(pv)
          value2Node.put(pv, t)
          t
        }
      }
      val cnode: TreeNode = value2Node.get(cv) match {
        case Some(node) =>
          node
        case None => {
          val t = new TreeNode(cv)
          value2Node.put(cv, t)
          t
        }
      }
      if (isLeft == 1) {
        pnode.left = cnode
      } else {
        pnode.right = cnode
      }

      notRoot.add(cv)
    }

    var root = new TreeNode(12)

    for (Array(pv, cv, isLeft) <- descriptions) {
      if (!notRoot.contains(pv)) {
        root = value2Node(pv)
      }
    }

    root
  }
}
