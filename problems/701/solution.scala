package `701`

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
  def insertIntoBST(root: TreeNode, v: Int): TreeNode = {
    if (root == null) {
      new TreeNode(v, null, null)
    } else {
      var cur = root
      var inserted = false
      while (!inserted) {
        if (v < cur.value) {
          if (cur.left == null) {
            cur.left = new TreeNode(v, null, null)
            inserted = true
          } else {
            cur = cur.left
          }
        } else {
          if (cur.right == null) {
            cur.right = new TreeNode(v, null, null)
            inserted = true
          } else {
            cur = cur.right
          }
        }
      }
      root
    }
  }
}
