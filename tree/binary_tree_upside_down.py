"""
Definition of TreeNode:
class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left, self.right = None, None
"""


class Solution:
    """
    @param root: the root of binary tree
    @return: new root
    """
    def upsideDownBinaryTree(self, root):
        if not root:
            return None

        node = root
        lc = node.left
        rc = node.right
        node.left = None
        node.right = None
        while lc:
            llc = lc.left
            lrc = lc.right
            lc.left = rc
            lc.right = node
            node = lc
            lc = llc
            rc = lrc

        return node
