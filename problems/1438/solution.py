from collections import deque


class Solution:
    def longestSubarray(self, nums: list[int], limit: int) -> int:
        min_q: deque[int] = deque()
        max_q: deque[int] = deque()
        l = 0
        for r, num in enumerate(nums):
            while min_q and nums[min_q[-1]] >= num:
                _ = min_q.pop()
            min_q.append(r)
            while max_q and nums[max_q[-1]] >= num:
                _ = max_q.pop()
            max_q.append(r)
            if max_q[0] - min_q[0] > limit:
                l += 1
                if min_q[0] < l:
                    _ = min_q.popleft()
                if max_q[0] < l:
                    _ = max_q.popleft()

        return len(nums) - l
