# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def distanceK(self, root, target, K):
        res = []
        parents = []

        def dfs_visit(node):
            if not node:
                return False
            elif node == target:
                parents.append(node)
                return True
            else:
                parents.append(node)
                fl = dfs_visit(node.left)
                fr = dfs_visit(node.right)
                if not fl and not fr:
                    parents.pop()

        def distance(node, d):
            if node:
                if d == 0:
                    res.append(node)
                else:
                    distance(node.left, d - 1)
                    distance(node.right, d - 1)

        dfs_visit(root)
        pre = parents.pop()
        distance(pre, K)
        d = K - 1

        while d > 0 and parents:
            node = parents.pop()
            if node.left == pre:
                distance(node.right, d - 1)
            else:
                distance(node.left, d - 1)
            d -= 1
            pre = node

        return res
