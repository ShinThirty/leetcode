class Solution:
    def partitionLabels(self, S: str) -> list:
        last = {}
        for i, c in enumerate(S):
            last[c] = i

        s = []
        for i, c in enumerate(S):
            l = last[c]
            if not s or i >= s[-1]:
                s.append(l + 1)
            else:
                s[-1] = max(s[-1], l + 1)

        for i in range(len(s) - 1, 0, -1):
            s[i] -= s[i - 1]

        return s


def run():
    s = Solution()
    S = "ababcbacadefegdehijhklij"
    print(s.partitionLabels(S))


if __name__ == "__main__":
    run()
