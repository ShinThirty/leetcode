class NumArray:

    def __init__(self, nums):
        self.data = nums
        self.t = [0] * (len(nums) + 1)
        i = 1
        for num in nums:
            self.t[i] += num
            j = i + (i & -i)
            if j <= len(nums):
                self.t[j] += self.t[i]
            i += 1

    def update(self, i: int, val: int) -> None:
        delta = val - self.data[i]
        self.data[i] = val
        j = i + 1
        while j < len(self.t):
            self.t[j] += delta
            j += (j & -j)

    def sumRange(self, i: int, j: int) -> int:
        def prefix_sum(i):
            psum = 0
            j = i
            while j > 0:
                psum += self.t[j]
                j -= (j & -j)
            return psum

        return prefix_sum(j + 1) - prefix_sum(i)
