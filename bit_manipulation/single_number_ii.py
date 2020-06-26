class Solution:
    def singleNumber(self, nums):
        """
        Use the thoughts from Finite State Machine (Boolean Algebra) to implement a logic and eliminate 1s that appeared `3n` times.

        a b c1  c2  b
        0 0  0   0  0
        0 0  1   1  0
        0 1  0   0  1
        1 0  0   1  0
        1 0  1   0  1
        1 1  0   0  0

        `c2 = ~a * ~b * c1 + a * ~b * ~c1 = ~b * (~a * c1 + a * ~c1) = ~b * (a ^ c1)`
        `b = ~a * b * ~c2 + a * ~b * ~c2 = ~c2 * (~a * b + a * ~b) = ~c2 * (a ^ b)`

        This method can be generalized to the following problem:
        In an array, every element appears `k` times except for one which appears `p` times, giving that `p % k != 0`. Find the element.

        After processing, the outstanding element will stay in the `p % k`th variable.
        """
        c = 0
        b = 0

        for a in nums:
            c = (a ^ c) & ~b
            b = (a ^ b) & ~c

        return c
