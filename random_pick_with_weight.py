import random


class Solution:

    def __init__(self, w: list):
        self.dis = []
        accu = 0
        for weight in w:
            accu += weight
            self.dis.append(accu)

    def pickIndex(self) -> int:
        pick = random.randrange(self.dis[-1])
        lo = 0
        hi = len(self.dis) - 1
        while lo < hi:
            guess = lo + (hi - lo) // 2
            if self.dis[guess] <= pick:
                lo = guess + 1
            else:
                hi = guess
        return lo


# Your Solution object will be instantiated and called as such:
# obj = Solution(w)
# param_1 = obj.pickIndex()
