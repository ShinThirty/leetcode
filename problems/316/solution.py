class Solution:
    def removeDuplicates(self, s: str, k: int) -> str:
        stack = []

        for c in s:
            if not stack:
                stack.append((c, 1))
            else:
                tchar, tcount = stack[-1]
                if tchar == c:
                    stack.pop()
                    if tcount < k - 1:
                        stack.append((c, tcount + 1))
                else:
                    stack.append((c, 1))

        res = []
        for tc, tcount in stack:
            for _ in range(tcount):
                res.append(tc)
        return "".join(res)


def run():
    s = "deeedbbcccbdaa"
    k = 3
    sol = Solution()
    print(sol.removeDuplicates(s, k))


if __name__ == "__main__":
    run()
