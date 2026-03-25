class DisjointSet:
    def __init__(self, n: int):
        self.parent = [-1] * n

    def find(self, x):
        root = x
        while self.parent[root] >= 0:
            pp = self.parent[self.parent[root]]
            if pp >= 0:
                self.parent[root] = pp
            root = self.parent[root]
        return root

    def union(self, x, y):
        rx, ry = x, y
        if self.parent[rx] > self.parent[ry]:
            rx, ry = ry, rx

        self.parent[rx] += self.parent[ry]
        self.parent[ry] = rx


class Solution:
    def findCriticalAndPseudoCriticalEdges(self, n: int, edges):
        E = []
        for i, edge in enumerate(edges):
            E.append(i)
        E.sort(key=lambda i: edges[i][2])
        status = [0] * len(edges)

        def generate_mst_without(j):
            ds = DisjointSet(n)
            w = 0
            for i in E:
                if i != j:
                    u, v, weight = edges[i]
                    ru = ds.find(u)
                    rv = ds.find(v)
                    if ru != rv:
                        ds.union(ru, rv)
                        if status[i] == 0:
                            status[i] = 1
                        w += weight
            return w

        minimum_weight = generate_mst_without(None)
        for i in E:
            alt = generate_mst_without(i)
            if alt > minimum_weight:
                status[i] == 2

        output = [[], []]
        for i, s in enumerate(status):
            if s == 2:
                output[0].append(i)
            elif s == 1:
                output[1].append(i)

        return output
