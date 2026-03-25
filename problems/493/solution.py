import bisect


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
        freq = 0
        while j > 0:
            freq += self.t[j]
            j &= j - 1
        return freq


class Solution:
    def reversePairs(self, nums):
        sorted_nums = sorted(set(nums))
        dc = {num: i for i, num in enumerate(sorted_nums)}

        ft = FrequencyTable(len(dc))
        res = 0
        for j, num in enumerate(nums):
            i = bisect.bisect(sorted_nums, num * 2) - 1
            res += j - ft.query(i)
            ft.addone(dc[num])

        return res


def run():
    nums = [1,3,2,3,1]
    sol = Solution()
    print(sol.reversePairs(nums))


if __name__ == "__main__":
    run()
