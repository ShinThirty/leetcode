# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right


class Solution:
    def distributeCoins(self, root) -> int:
        if not root:
            return 0

        def get_reservoir_step(node):
            left_reservoir, left_step, right_reservoir, right_step = 0, 0, 0, 0
            if node.left:
                left_reservoir, left_step = get_reservoir_step(node.left)
            if node.right:
                right_reservoir, right_step = get_reservoir_step(node.right)

            reservoir = node.val + left_reservoir + right_reservoir - 1
            step = abs(reservoir) + left_step + right_step
            return reservoir, step

        return get_reservoir_step(root)[1]
