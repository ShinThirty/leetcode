class Solution:
    def getAllElements(self, root1, root2):
        stack1 = []
        stack2 = []

        node1, node2 = root1, root2
        while node1:
            stack1.append(node1)
            node1 = node1.left
        while node2:
            stack2.append(node2)
            node2 = node2.left

        def nxt(stack):
            if not stack:
                return None

            node = stack.pop()
            num = node.val
            if node.right:
                node = node.right
                while node:
                    stack.append(node)
                    node = node.left

            return num

        res = []
        n1 = nxt(stack1)
        n2 = nxt(stack2)
        while n1 is not None and n2 is not None:
            if n1 <= n2:
                res.append(n1)
                n1 = nxt(stack1)
            else:
                res.append(n2)
                n2 = nxt(stack2)

        while n1 is not None:
            res.append(n1)
            n1 = nxt(stack1)

        while n2 is not None:
            res.append(n2)
            n2 = nxt(stack2)

        return res
