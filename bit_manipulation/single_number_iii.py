class Solution:
    def singleNumber(self, nums):
        a = 0
        for num in nums:
            a ^= num

        lsb = a & (-a)

        b, c = 0, 0
        for num in nums:
            if num & lsb == 0:
                b ^= num
            else:
                c ^= num

        return [b, c]
