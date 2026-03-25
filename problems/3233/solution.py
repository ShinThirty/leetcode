class Solution(object):
    def nonSpecialCount(self, l, r):
        """
        :type l: int
        :type r: int
        :rtype: int
        """
        def primes(n):
            out = []
            numbers = [True] * n
            for p in range(2, n + 1):
                if numbers[p - 1]:
                    out.append(p)
                else:
                    for mult in range(p + p, n + 1, p):
                        numbers[mult - 1] = False
            return out

        # Newton's method
        def isqrt(n):
            x = n
            y = (x + 1) // 2
            while y < x:
                x = y
                y = (x + n // x) // 2
            return x

        sps = 0
        ps = primes(isqrt(r))
        for p in ps:
            if p * p >= l:
                sps += 1
        return r - l + 1 - sps
