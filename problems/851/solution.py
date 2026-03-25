class Solution(object):
    def loudAndRich(self, richer, quiet):
        """
        :type richer: List[List[int]]
        :type quiet: List[int]
        :rtype: List[int]
        """

        n = len(quiet)

        g = [[]] * n
        for pair in richer:
            a = pair[0]
            b = pair[1]
            g[b].append(a)

        m = [-1000] * n

        def visit(i):
            if m[i] != -1000:
                return

            m[i] = i
            for j in g[i]:
                visit(j)
                if quiet[m[i]] > quiet[m[j]]:
                    m[i] = m[j]

        for i in range(n):
            visit(i)

        return m
