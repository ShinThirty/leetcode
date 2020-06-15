from collections import deque

"""
Definition of TreeNode:
class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left, self.right = None, None
"""


class Solution:
    """
    @param root: the given BST
    @param target: the given target
    @param k: the given k
    @return: k values in the BST that are closest to the target
    """
    def closestKValues(self, root, target, k):
        if not root:
            return 0

        ans = deque()
        s = []
        node = root
        while node:
            s.append(node)
            node = node.left

        def next():
            if not s:
                return None

            num = s[-1].val

