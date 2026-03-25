package linkedlistinbinarytree

import scala.collection.mutable

object SolutionKMP {
  def isSubPath(head: ListNode, root: TreeNode): Boolean = {
    val p = mutable.Buffer.empty[Int]
    var cur = head
    p += cur.x
    cur = cur.next
    while (cur != null) {
      p += cur.x
      cur = cur.next
    }

    val m = p.length
    val pi = mutable.Buffer.fill(m)(0)
    var k = 0 // always the value of pi(q - 1)
    for (q <- 1 to m - 1) {
      while (p(q) != p(k) && k > 0) {
        k = pi(k - 1)
      }
      if (p(q) == p(k)) {
        k = k + 1
      }
      pi(q) = k
    }

    def search(root: TreeNode, q: Int): Boolean = {
      if (root == null) {
        false
      } else {
        var qq = q
        while (qq > 0 && root.value != p(qq)) {
          qq = pi(qq - 1)
        }
        if (root.value == p(qq)) {
          qq = qq + 1
        }
        if (qq == m) {
          true
        } else {
          search(root.left, qq) || search(root.right, qq)
        }
      }
    }

    search(root, 0)
  }
}
