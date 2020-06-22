class Solution:
    def singleNonDuplicate(self, nums: list) -> int:
        lo = 0
        hi = len(nums) - 1

        while lo < hi:
            i = lo + (hi - lo) // 2

            if nums[i - 1] != nums[i] != nums[i + 1]:
                return nums[i]

            if nums[i - 1] == nums[i]:
                if i % 2 == 0:
                    hi = i - 2
                else:
                    lo = i + 1
            else:
                if i % 2 == 0:
                    lo = i + 2
                else:
                    hi = i - 1

        return nums[lo]
