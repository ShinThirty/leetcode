from collections import defaultdict, deque


class Solution:
    def calcEquation(self, equations, values, queries):
        g = defaultdict(set)
        n = len(equations)
        for i in range(n):
            a, b = equations[i]
            q = values[i]
            g[a].add((b, q))
            g[b].add((a, 1 / q))

        output = []
        for a, b in queries:
            if a not in g or b not in g:
                output.append(-1)
            else:
                Q = deque()
                res = 1
                Q.append((a, res))
                black = {a}
                found = False
                while Q:
                    cur, res = Q.popleft()
                    if cur == b:
                        found = True
                        break
                    else:
                        for c, q in g[cur]:
                            if c not in black:
                                Q.append((c, res * q))
                                black.add(c)
                if found:
                    output.append(res)
                else:
                    output.append(-1)

        return output
