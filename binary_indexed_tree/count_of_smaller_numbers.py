class FrequencyTable:
    def __init__(self, n):
        self.t = [0] * (n + 1)

    def addone(self, i):
        j = i + 1
        while j < len(self.t):
            self.t[j] += 1
            j += (j & -j)

    def query(self, i):
        j = i + 1
        frequency = 0
        while j > 0:
            frequency += self.t[j]
            j &= j - 1
        return frequency


class Solution:
    def countSmaller(self, nums):
        dc = {num: i for i, num in enumerate(sorted(set(nums)))}

        ft = FrequencyTable(len(dc))
        res = []
        for num in reversed(nums):
            ft.addone(dc[num])
            res.append(ft.query(dc[num] - 1))
        return res[::-1]
