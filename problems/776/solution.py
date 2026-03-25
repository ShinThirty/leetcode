# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


def cutTree(root, k):

    def split_bst(node):
        if not node:
            return [None, None]

        if node.val <= k:
            A, B = split_bst(node.right)
            node.right = A
            return [node, B]
        else:
            A, B = split_bst(node.left)
            node.left = B
            return [A, node]

    return split_bst(root)
