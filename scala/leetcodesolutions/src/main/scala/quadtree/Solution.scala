package quadtree

class Node(var _value: Boolean, var _isLeaf: Boolean) {
  var value: Boolean = _value
  var isLeaf: Boolean = _isLeaf
  var topLeft: Node = null
  var topRight: Node = null
  var bottomLeft: Node = null
  var bottomRight: Node = null
}

object Solution {
  def intersect(quadTree1: Node, quadTree2: Node): Node = {
    def mergeable: Node => Node => Node => Node => Boolean = n1 => n2 => n3 => n4 => {
      val v = n1.value
      n1.isLeaf && n2.isLeaf && n3.isLeaf && n4.isLeaf && n2.value == v && n3.value == v && n4.value == v
    }

    def logicalOr: Node => Node => Node = n1 => n2 => {
      if (n1.isLeaf && n2.isLeaf) {
        n1.value |= n2.value
        n1
      } else if (n1.isLeaf || n2.isLeaf) {
        val leaf = if (n1.isLeaf) n1 else n2
        val other = if (n1.isLeaf) n2 else n1
        if (leaf.value) leaf else other
      } else {
        val topLeft = logicalOr(n1.topLeft)(n2.topLeft)
        val topRight = logicalOr(n1.topRight)(n2.topRight)
        val bottomLeft = logicalOr(n1.bottomLeft)(n2.bottomLeft)
        val bottomRight = logicalOr(n1.bottomRight)(n2.bottomRight)
        if (mergeable(topLeft)(topRight)(bottomLeft)(bottomRight)) {
          n1.isLeaf = true
          n1.value = topLeft.value
          n1.topLeft = null
          n1.topRight = null
          n1.bottomLeft = null
          n1.bottomRight = null
        } else {
          n1.topLeft = topLeft
          n1.topRight = topRight
          n1.bottomLeft = bottomLeft
          n1.bottomRight = bottomRight
        }
        n1
      }
    }

    logicalOr(quadTree1)(quadTree2)
  }
}
