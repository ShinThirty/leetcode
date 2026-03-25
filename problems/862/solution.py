from collections import deque


class Solution:
    def shortestSubarray(self, nums: list[int], k: int) -> int:
        p = [0]
        for num in nums:
            p.append(p[-1] + num)

        # p[j] - p[i] = sum(i...j - 1)
        dq: deque[int] = deque()
        dq.append(0)

        res = len(nums) + 1
        for j in range(1, len(p)):
            while p[j] - p[dq[0]] >= k:
                res = min(res, j - dq[0])
                _ = dq.popleft()

            while dq and p[dq[-1]] >= p[j]:
                _ = dq.pop()
            dq.append(j)

        return res if res <= len(nums) else -1
