import sys


class Solution:
    def tilingRectangle(self, n: int, m: int) -> int:
        if n == m:
            return 1

        ans = None
        covered = [[False] * m for _ in range(n)]
        cnt = 0
        searched = 0

        def to_string(covered):
            print("======================")
            for row in covered:
                for cell in row:
                    if cell:
                        sys.stdout.write("# ")
                    else:
                        sys.stdout.write(". ")
                sys.stdout.write("\n")

        def cover(r, c, k):
            for i in range(r, r + k):
                for j in range(c, c + k):
                    covered[i][j] = True

        def uncover(r, c, k):
            for i in range(r, r + k):
                for j in range(c, c + k):
                    covered[i][j] = False

        def available(r, c, k):
            for i in range(r, r + k):
                for j in range(c, c + k):
                    if covered[i][j]:
                        return False
            return True

        def square(r, c):
            nonlocal ans, cnt, searched
            searched += 1
            to_string(covered)
            print(f"ans = {ans}, cnt = {cnt}, r = {r}, c = {c}, searched = {searched}")
            if ans is not None and cnt >= ans:
                return
            elif r == n:
                ans = cnt
            elif c == m:
                square(r + 1, 0)
            elif covered[r][c]:
                square(r, c + 1)
            else:
                s = min(n - r, m - c)
                for k in range(s, 0, -1):
                    if available(r, c, k):
                        cnt += 1
                        cover(r, c, k)
                        square(r, c + k)
                        uncover(r, c, k)
                        cnt -= 1

        square(0, 0)
        return ans


def run():
    s = Solution()
    n, m = 5, 8
    s.tilingRectangle(n, m)


if __name__ == "__main__":
    run()
