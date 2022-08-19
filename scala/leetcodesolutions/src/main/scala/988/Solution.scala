package `988`

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
  def smallestFromLeaf(root: TreeNode): String = {
    var ans = Seq.empty[Int]

    def traverse(node: TreeNode, charSeq: mutable.ArrayDeque[Int]): Unit = {
      charSeq.prepend(node.value)
      if (node.left == null && node.right == null) {
        if (ans.isEmpty) {
          ans = charSeq.toSeq
        } else {
          var i = 0
          var j = 0
          var finished = false
          var isSmaller = false
          while (!finished && i < charSeq.length && j < ans.length) {
            if (charSeq(i) < ans(j)) {
              isSmaller = true
              finished = true
            } else if (charSeq(i) > ans(j)) {
              finished = true
            } else {
              i += 1
              j += 1
            }
          }

          if (i == charSeq.length || isSmaller) {
            ans = charSeq.toSeq
          }
        }
      } else {
        if (node.left != null) {
          traverse(node.left, charSeq)
        }
        if (node.right != null) {
          traverse(node.right, charSeq)
        }
      }
      charSeq.removeHead()
    }

    traverse(root, mutable.ArrayDeque.empty[Int])

    ans.map(n => (n + 'a').toChar).mkString
  }
}
