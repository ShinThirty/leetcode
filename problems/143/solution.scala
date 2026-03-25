package ReorderList

import scala.annotation.tailrec

class ListNode(_x: Int = 0, _next: ListNode = null) {
  var next: ListNode = _next
  var x: Int = _x
}

object Solution {
  def reorderList(head: ListNode): Unit = {
    def next: ListNode => ListNode = {
      case null => null
      case a    => a.next
    }

    @tailrec
    def midPoint(slow: ListNode, fast: ListNode): ListNode = {
      fast match {
        case null => slow
        case a    => midPoint(next(slow), next(next(a)))
      }
    }

    @tailrec
    def reverseList(head: ListNode, res: ListNode): ListNode = {
      head match {
        case null => res
        case h    => val tail = h.next; h.next = res; reverseList(tail, h)
      }
    }

    @tailrec
    def interleave(head: ListNode, rh: ListNode, res: ListNode): ListNode = {
      (head, rh) match {
        case (h, null) => reverseList(h, null)
        case (h, r) =>
          val th = h.next; val tr = r.next; r.next = h; h.next = res;
          interleave(th, tr, r)
      }
    }

    val mid: ListNode = midPoint(head, head)
    val rh: ListNode = reverseList(mid, null)

    interleave(head, rh, null)
  }
}
