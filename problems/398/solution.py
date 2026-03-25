import random


class Solution:

    def __init__(self, nums: list):
        self.data = nums

    def pick(self, target: int) -> int:
        """Reservoir sampling.
        """
        cnt = 0
        res = None
        for i, num in enumerate(self.data):
            if num == target:
                cnt += 1
                r = random.randrange(cnt)
                if r == 0:
                    res = i

        return res
