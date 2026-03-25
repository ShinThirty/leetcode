from collections import defaultdict


class Solution:
    def countOfAtoms(self, formula: str) -> str:
        elements = [[]]
        section = []
        count = 0

        for c in formula + " ":
            if c.isdigit():
                if section:
                    elements[-1].append("".join(section))
                    section = []
                count = count * 10 + int(c)
            elif c.islower():
                section.append(c)
            else:
                if section:
                    elements[-1].append("".join(section))
                    section = []
                    elements[-1].append(1)
                else:
                    if elements[-1] and isinstance(elements[-1][-1], list):
                        le = elements[-1].pop()
                        i = 0
                        if count == 0:
                            count = 1
                        while i < len(le):
                            elements[-1].append(le[i])
                            elements[-1].append(count * le[i + 1])
                            i += 2
                    elif count > 0:
                        elements[-1].append(count)
                count = 0
                if c == "(":
                    elements.append([])
                elif c == ")":
                    le = elements.pop()
                    elements[-1].append(le)
                elif c.upper():
                    section.append(c)

        element_map = defaultdict(int)
        i = 0
        while i < len(elements[-1]):
            element_map[elements[-1][i]] += elements[-1][i + 1]
            i += 2

        output = []
        keys = []
        for e in element_map:
            keys.append(e)

        for e in sorted(keys):
            output.append(e)
            if element_map[e] > 1:
                output.append(str(element_map[e]))

        return "".join(output)


def run():
    formula = "(H)"
    s = Solution()
    print(s.countOfAtoms(formula))


if __name__ == "__main__":
    run()
