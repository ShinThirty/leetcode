class Solution:
    def wiggleSort(self, nums) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        nums.sort()
        half = (len(nums) - 1) // 2 + 1
        nums[::2], nums[1::2] = nums[:half][::-1], nums[half:][::-1]


def run():
    nums = [2,2,1,3,1,3]
    sol = Solution()
    sol.wiggleSort(nums)
    print(nums)


if __name__ == "__main__":
    run()
