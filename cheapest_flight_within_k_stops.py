from collections import defaultdict, deque
import heapq


class Solution:
    def findCheapestPrice(self, n: int, flights: list, src: int, dst: int, K: int) -> int:
        g = defaultdict(list)
        for u, v, price in flights:
            g[u].append((v, price))

        q = []
        q.append((0, src, K))

        while q:
            p, u, k = heapq.heappop(q)
            if u == dst:
                return p
            else:
                for v, price in g[u]:
                    if v == dst:
                        heapq.heappush(q, (p + price, v, k))
                    elif k > 0:
                        heapq.heappush(q, (p + price, v, k - 1))

        return -1
