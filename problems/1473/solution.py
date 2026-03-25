class Solution:
    def minCost(self, houses, cost, m, n, target):
        F = [[float('inf')] * (n) for _ in range(target + 1)]
        G = [[float('inf')] * (n) for _ in range(target + 1)]
        H = [[float('inf')] * (n) for _ in range(target + 1)]

        def update_h(k):
            s = [(None, float('inf'))]
            for j in range(n - 1):
                s.append((F[k][j], min(F[k][j], s[-1][1])))

            right_min = float('inf')
            for j in range(n - 1, -1, -1):
                e, left_min = s.pop()
                H[k][j] = min(left_min, right_min)
                right_min = min(F[k][j], right_min)

        if houses[0] == 0:
            for j in range(n):
                F[1][j] = cost[0][j]
        else:
            for j in range(n):
                if houses[0] == j + 1:
                    F[1][j] = 0

        update_h(1)

        for i in range(1, m):
            for k in range(1, target + 1):
                for j in range(n):
                    G[k][j] = float('inf')
                    if houses[i] > 0 and houses[i] != j + 1:
                        continue

                    price = cost[i][j] * (not houses[i])
                    G[k][j] = price + min(F[k][j], H[k - 1][j])

            F, G = G, F

            for k in range(1, target + 1):
                update_h(k)

        res = min(F[target])
        if res == float('inf'):
            return -1
        else:
            return res
