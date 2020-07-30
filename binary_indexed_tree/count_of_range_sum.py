import bisect


class FrequencyTable:
    def __init__(self, n):
        self.t = [0] * (n + 1)

    def addone(self, i):
        j = i + 1
        while j < len(self.t):
            self.t[j] += 1
            j += (j & -j)

    def query(self, i, j):
        start = i + 1
        end = j + 1
        freq = 0
        while start > 0 and end > 0:
            freq += self.t[end]
            freq -= self.t[start]
            end &= end - 1
            start &= start - 1

        while end > 0:
            freq += self.t[end]
            end &= end - 1

        return freq

class Solution:
    """
    @param nums: a list of integers
    @param lower: a integer
    @param upper: a integer
    @return: return a integer
    """
    def countRangeSum(self, nums, lower, upper):
        p = [0]
        for num in nums:
            p.append(p[-1] + num)

        sorted_p = sorted(set(p))
        dc = {num: i for i, num in enumerate(sorted_p)}

        ft = FrequencyTable(len(dc))
        res = 0
        ft.addone(dc[p[0]])
        for j in range(1, len(p)):
            lo = bisect.bisect_left(sorted_p, p[j] - upper) - 1
            hi = bisect.bisect(sorted_p, p[j] - lower) - 1
            res += ft.query(lo, hi)
            ft.addone(dc[p[j]])

        return res
