package maxproductofsplittedbt
class TreeNode(
    _value: Int = 0,
    _left: TreeNode = null,
    _right: TreeNode = null
) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}

case class Result(sum: Int, maxProduct: Long)
object Solution {
  def maxProduct(root: TreeNode): Int = {
    def sumOfBinaryTree(node: TreeNode): Int = if (node == null) 0
    else sumOfBinaryTree(node.left) + node.value + sumOfBinaryTree(node.right)
    val sum = sumOfBinaryTree(root)

    def findMaximumProduct(node: TreeNode): Result =
      if (node == null) {
        Result(0, 0)
      } else {
        val rl = findMaximumProduct(node.left)
        val rr = findMaximumProduct(node.right)
        val s = rl.sum + node.value + rr.sum
        val m = Math.max(rl.maxProduct, rr.maxProduct)
        val product = s.toLong * (sum - s).toLong
        Result(s, Math.max(product, m))
      }
    (findMaximumProduct(root).maxProduct % 1000000007).toInt
  }
}
