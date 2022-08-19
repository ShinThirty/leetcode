package `538`

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
  def convertBST(root: TreeNode): TreeNode = {
    def setGreaterSum(node: TreeNode, greaterSum: Int): Int = {
      Option(node)
        .map(n => {
          val rightSum = setGreaterSum(n.right, greaterSum)
          n.value += rightSum
          setGreaterSum(n.left, node.value)
        })
        .getOrElse(greaterSum)
    }

    setGreaterSum(root, 0)
    root
  }
}
