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
    @return: the length of the longest consecutive sequence path
    """
    def longestConsecutive2(self, root):
        if not root:
            return 0

        ans = 1

        def longest_inc_dec(node):
            nonlocal ans
            inc, dec = 1, 1
            left_inc, left_dec, right_inc, right_dec = 0, 0, 0, 0
            if node.left:
                left_inc, left_dec = longest_inc_dec(node.left)
            if node.right:
                right_inc, right_dec = longest_inc_dec(node.right)

            if node.left and node.left.val == node.val + 1:
                inc = max(inc, left_inc + 1)
            if node.left and node.left.val == node.val - 1:
                dec = max(dec, left_dec + 1)
            if node.right and node.right.val == node.val + 1:
                inc = max(inc, right_inc + 1)
            if node.right and node.right.val == node.val - 1:
                dec = max(dec, right_dec + 1)

            ans = max(ans, inc, dec)
            if node.left and node.right:
                if node.left.val == node.val - 1 and node.right.val == node.val + 1:
                    ans = max(ans, 1 + left_dec + right_inc)
                elif node.left.val == node.val + 1 and node.right.val == node.val - 1:
                    ans = max(ans, 1 + left_inc + right_dec)

            return inc, dec

        longest_inc_dec(root)
        return ans
