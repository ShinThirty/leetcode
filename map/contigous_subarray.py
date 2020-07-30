class Solution:
    def checkSubarraySum(self, nums, k):
        n = len(nums)
        if n <= 1:
            return False

        if k == 0:
            for i in range(1, n):
                if nums[i] == nums[i - 1] == 0:
                    return True
            return False

        if k < 0:
            k = -k

        mods = {0: -1}
        premod = 0
        for i, num in enumerate(nums):
            premod = (premod + num) % k
            if premod in mods:
                if mods[premod] < i - 1:
                    return True
            else:
                mods[premod] = i

        return False
