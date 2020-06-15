class Solution:
    def mirrorReflection(self, p: int, q: int) -> int:
        a, b = p, q
        while b > 0:
            a, b = b, a % b

        n1 = (p // a) % 2
        n2 = (q // a) % 2

        if n2 == 0:
            return 0
        elif n1 == 0:
            return 2
        else:
            return 1


def run():
    p, q = 6, 4
    s = Solution()
    print(s.mirrorReflection(p, q))


if __name__ == "__main__":
    run()
