from collections import defaultdict


class Solution:
    def evaluate(self, expression: str) -> int:
        variables = defaultdict(list)
        ops = []
        operands = []
        updated = []
        variable_name = False
        section = []
        sign = 1
        count = []

        func = [
            lambda x, y: x + y,
            lambda x, y: x * y
        ]

        for c in expression:
            if c == "(":
                pass
            elif c == ")":
                op = ops.pop()
                if section:
                    s = "".join(section)
                    if variable_name:
                        operands.append(variables[s][-1])
                    else:
                        operands.append(sign * int(s))
                    section = []
                    variable_name = False
                    sign = 1
                if op == 2:
                    updated_variables = updated.pop()
                    count.pop()
                    while updated_variables:
                        v = updated_variables.pop()
                        variables[v].pop()
                else:
                    op2 = operands.pop()
                    op1 = operands.pop()
                    operands.append(func[op](op1, op2))
            elif c == " ":
                if section:
                    s = "".join(section)
                    if s == "add":
                        ops.append(0)
                    elif s == "mult":
                        ops.append(1)
                    elif s == "let":
                        ops.append(2)
                        updated.append([])
                        count.append(0)
                    else:
                        if ops[-1] == 2:
                            if count[-1] == 0:
                                operands.append(s)
                                updated[-1].append(s)
                                count[-1] = 1
                            else:
                                v = operands.pop()
                                if variable_name:
                                    value = variables[s][-1]
                                else:
                                    value = sign * int(s)
                                variables[v].append(value)
                                count[-1] = 0
                        else:
                            value = None
                            if variable_name:
                                value = variables[s][-1]
                            else:
                                value = sign * int(s)
                            operands.append(value)
                    section = []
                    variable_name = False
                    sign = 1
                else:
                    if ops[-1] == 2:
                        value = operands.pop()
                        v = operands.pop()
                        variables[v].append(value)
                        count[-1] = 0
            elif c == "-":
                sign = -1
            else:
                section.append(c)
                if not c.isdigit():
                    variable_name = True

        return operands[0]


def run():
    expression = "(let a (add 1 2) b (mult a 3) c 4 d (add a b) (mult d d))"
    sol = Solution()
    print(sol.evaluate(expression))


if __name__ == "__main__":
    run()
