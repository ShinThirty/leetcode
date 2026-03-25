import collections


class Solution:
    def customSortString(self, S: str, T: str) -> str:
        count_t = collections.defaultdict(int)
        for t in T:
            count_t[t] += 1

        res = []
        for c in S:
            if c in count_t:
                for _ in range(count_t[c]):
                    res.append(c)
                del count_t[c]

        for c in count_t:
            for _ in range(count_t[c]):
                res.append(c)

        return "".join(res)
