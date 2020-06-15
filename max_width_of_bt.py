import collections


class Solution:
    def widthOfBinaryTree(self, root) -> int:
        Q = collections.deque()
        Q.append((root, 1))

        width = -float('inf')
        while Q:
            min_i = float('inf')
            max_i = -float('inf')
            for _ in range(len(Q)):
                # Same level
                node, i = Q.popleft()
                min_i = min(min_i, i)
                max_i = max(max_i, i)
                if node.left:
                    Q.append((node.left, 2 * i))
                if node.right:
                    Q.append((node.right, 2 * i + 1))

            width = max(width, max_i - min_i + 1)

        return width
