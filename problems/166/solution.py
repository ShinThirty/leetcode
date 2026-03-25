class Solution:
    def fractionToDecimal(self, numerator: int, denominator: int) -> str:
        a = numerator
        b = denominator
        i, a = divmod(a, b)

        a *= 10
        q = a // b
        p = (a, q)
        initial = p
        a %= b
        next_state = {}
        while a > 0:
            a *= 10
            q = a // b
            cur = (a, q)
            if cur in next_state:
                break
            else:
                next_state[p] = cur
                p = cur

        ls = cur
        if a == 0:
            decimal = []
            cur = initial
            while cur in next_state:
                decimal.append(str(cur[1]))
                cur = next_state[cur]
            d = "".join(decimal)
            if int(d) == 0:
                return f"{i}"
            else:
                return f"{i}.{d}"
        else:
            decimal = []
            cur = initial
            while cur != ls:
                decimal.append(str(cur[1]))
                cur = next_state[cur]
            rep = []
            rep.append(str(ls[1]))
            cur = next_state[ls]
            while cur != ls:
                rep.append(str(cur[1]))
                cur = next_state[cur]

            return f"{i}.{''.join(decimal)}({''.join(rep)})"
