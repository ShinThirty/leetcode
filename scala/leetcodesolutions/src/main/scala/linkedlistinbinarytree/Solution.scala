package linkedlistinbinarytree

class ListNode(_x: Int = 0, _next: ListNode = null) {
  var next: ListNode = _next
  var x: Int = _x
}

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
  def isSubPath(head: ListNode, root: TreeNode): Boolean = {
    def search(head: ListNode, root: TreeNode): Boolean = {
      if (head == null) {
        true
      } else if (root == null) {
        false
      } else if (head.x != root.value) {
        false
      } else {
        search(head.next, root.left) || search(head.next, root.right)
      }
    }

    def check(head: ListNode, root: TreeNode): Boolean = {
      if (root == null) {
        false
      } else if (search(head, root)) {
        true
      } else {
        check(head, root.left) || check(head, root.right)
      }
    }

    check(head, root)
  }
}
