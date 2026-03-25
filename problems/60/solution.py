class Solution:
    def getPermutation(self, n: int, k: int) -> str:
        fac = 1
        numbers = []
        for i in range(1, n + 1):
            fac *= i
            numbers.append(str(i))

        seq = []
        o = k - 1
        for c in range(n, 0, -1):
            fac //= c
            d = numbers[o // fac]
            numbers.remove(d)
            seq.append(d)
            o %= fac

        return "".join(seq)


def run():
    n, k = 3, 3
    s = Solution()
    print(s.getPermutation(n, k))


if __name__ == "__main__":
    run()
