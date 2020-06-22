class Solution:
    def superEggDrop(self, K: int, N: int) -> int:
        """f(t, k) => floors explored after throwing t times with k eggs
        f(1, k) = 2
        f(t, 1) = 1 + t
        f(t, k) = f(t - 1, k - 1) + f(t - 1, k)
        """
        f = [2] * (K + 1)
        g = [None] * (K + 1)
        t = 1
        while f[K] < N + 1:
            t += 1
            g[1] = 1 + t
            for k in range(2, K + 1):
                g[k] = f[k - 1] + f[k]
            f, g = g, f

        return t
