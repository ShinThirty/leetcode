class Solution:
    def serialize(self, root):
        if not root:
            return ""

        ans = []

        def s(node):
            ans.append("(")
            ans.append(str(node.label))
            for nn in node.neighbors:
                s(nn)
            ans.append(")")

        s(root[0])
        return "".join(ans)

    def deserialize(self, data):
        s = []
        root = None
        cnt, sign = None, 1
        for c in data:
            if c.isdigit():
                if cnt is None:
                    cnt = 0
                cnt = cnt * 10 + int(c)
            elif c == "-":
                sign = -1
            else:
                if cnt is not None:
                    node = DirectedGraphNode(sign * cnt)
                    cnt, sign = None, 1
                    if s:
                        s[-1].neighbors.append(node)
                    if c == "(":
                        s.append(node)
                elif c == ")":
                    root = s.pop()

        return root
