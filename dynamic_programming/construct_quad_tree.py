class Node:
    def __init__(self, val, isLeaf, topLeft, topRight, bottomLeft, bottomRight):
        self.val = val
        self.isLeaf = isLeaf
        self.topLeft = topLeft
        self.topRight = topRight
        self.bottomLeft = bottomLeft
        self.bottomRight = bottomRight


class Solution:
    def construct(self, grid) -> Node:

        def build(x, y, length):
            if length == 1:
                return Node(grid[x][y], True, None, None, None, None)
            else:
                l2 = length // 2
                node0 = build(x, y, l2)
                node1 = build(x, y + l2, l2)
                node2 = build(x + l2, y, l2)
                node3 = build(x + l2, y + l2, l2)

                if node0.isLeaf and node1.isLeaf and node2.isLeaf and node3.isLeaf and (node0.val == node1.val == node2.val == node3.val):
                    return Node(node0.val, True, None, None, None, None)
                else:
                    return Node(node0.val, False, node0, node1, node2, node3)

        return build(0, 0, len(grid))
