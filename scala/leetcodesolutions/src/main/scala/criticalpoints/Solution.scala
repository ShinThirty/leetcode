package criticalpoints

object Solution {
  def nodesBetweenCriticalPoints(head: ListNode): Array[Int] = {
    var first = -1
    var prev = -2
    var cur = -1
    var minDistance = 100001

    val isCritical: ListNode => ListNode => ListNode => Boolean = p =>
      c => n => (p.x < c.x && c.x > n.x) || (p.x > c.x && c.x < n.x)
    var n = head
    var p: ListNode = null
    var c: ListNode = null
    while (n != null) {

      if (p != null && isCritical(p)(c)(n)) {
        // c is critical
        if (prev >= 0 && cur - prev < minDistance) {
          minDistance = cur - prev
        }
        if (first == -1) {
          first = cur
        }
        prev = cur
      }

      p = c
      c = n
      n = n.next
      cur += 1
    }

    val maxDistance = prev - first
    Array(
      if (minDistance < 100001) minDistance else -1,
      if (maxDistance > 0) maxDistance else -1
    )
  }
}

class ListNode(_x: Int = 0, _next: ListNode = null) {
  var next: ListNode = _next
  var x: Int = _x
}
