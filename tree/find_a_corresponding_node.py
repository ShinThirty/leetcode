# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def getTargetCopy(self, original, cloned, target):

        def find_target(n1, n2):
            if n1 == target:
                return n2
            elif n1 is None:
                return n2
            else:
                l = find_target(n1.left, n2.left)
                if l is not None:
                    return l
                return find_target(n1.right, n2.right)

        return find_target(original, cloned)
