class Solution:
    def totalHammingDistance(self, nums):
        integers = nums[:]
        n = len(integers)
        count = 0
        for _ in range(30):
            ones = 0
            for i in range(n):
                ones += integers[i] % 2
                integers[i] >>= 1
            count += ones * (n - ones)

        return count
