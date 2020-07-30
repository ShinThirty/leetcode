class Solution:
    def threeSum(self, nums):
        n = len(nums)
        sorted_nums = sorted(nums)
        rs = []
        for k in range(n - 1, -1, -1):
            c = sorted_nums[k]
            if k < n - 1 and c == sorted_nums[k + 1]:
                continue
            i = 0
            j = k - 1
            while i < j:
                a = sorted_nums[i]
                b = sorted_nums[j]
                s = a + b + c
                if s == 0:
                    rs.append([a, b, c])
                    while i < j:
                        i += 1
                        j -= 1
                        if sorted_nums[i] > sorted_nums[i - 1] or sorted_nums[j] < sorted_nums[j + 1]:
                            break
                elif s < 0:
                    i += 1
                else:
                    j -= 1
        return rs
