class Solution:
    def removeInvalidParentheses(self, s: str) -> list:
        n = len(s)
        left = 0
        right = 0
        b = 0
        for c in s:
            if c == "(":
                b += 1
            elif c == ")":
                b -= 1
            if b < 0:
                right += 1
                b = 0
        left = b

        res = set()
        alt = []

        def construct_string(start, left, right, left_open):
            if start == n:
                if left == 0 and right == 0:
                    res.add("".join(alt))
            elif s[start] == "(":
                alt.append(s[start])
                construct_string(start + 1, left, right, left_open + 1)
                alt.pop()
                if left > 0:
                    construct_string(start + 1, left - 1, right, left_open)
            elif s[start] == ")":
                if left_open > 0:
                    alt.append(s[start])
                    construct_string(start + 1, left, right, left_open - 1)
                    alt.pop()
                if right > 0:
                    construct_string(start + 1, left, right - 1, left_open)
            else:
                alt.append(s[start])
                construct_string(start + 1, left, right, left_open)
                alt.pop()

        construct_string(0, left, right, 0)
        return res


def run():
    s = "(((()(()"
    solution = Solution()
    res = solution.removeInvalidParentheses(s)
    print(res)


if __name__ == "__main__":
    run()
