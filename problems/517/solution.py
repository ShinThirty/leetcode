class Solution:
    def findMinMoves(self, machines: list) -> int:
        """
        1. The goal is to calculate how many steps required to make every
        machine balanced.
        2. Each machine can be processed in parallel, so we only need the
        maximum steps required to balance a machine across all machines.
        3. Therefore we need 2 arrays storing how many clothes machines to
        the left and to the right can provide (positive value) or require
        (negative value).
        4. The arrays can be constructed as:
        [0, i)
        reservoir_l[i] = reservoir[i - 1] + machines[i - 1] - each
        reservoir_l[0] = 0
        (i, n - 1]
        reservoir_r[i] = reservoir[i + 1] + machines[i + 1] - each
        reservoir_r[n - 1] = 0
        5. Let rl = reservoir_l[i], rr = reservoir_r[i]
        if rl < 0 and rr < 0, it means both left side and right side require
        clothes. In this case we need to do it sequentially: step = abs(rl) + abs(rr).
        Under other cases, at least one of the sides can provide clothes and we
        can adjust the clothes in parallel: step = max(abs(rl), abs(rr))
        6. We can get the maximum value of step within time complexity linear to
        the input array size, aka O(n).
        7. We don't actually need to store all the values in the side arrays. Instead,
        we can update the array value in use on the go, reducing the space complexity
        to constant, aka O(1).
        """
        n = len(machines)
        total = 0
        for m in machines:
            total += m

        if total % n != 0:
            return -1

        each = total // n

        rl = 0
        rr = 0
        for i in range(n - 1):
            rr += machines[i + 1] - each

        step = 0
        for i in range(n):
            rla = abs(rl)
            rra = abs(rr)
            if rl < 0 and rr < 0:
                step = max(step, rla + rra)
            else:
                step = max(step, max(rla, rra))

            if i < n - 1:
                rl += machines[i] - each
                rr -= machines[i + 1] - each

        return step


def run():
    s = Solution()
    machines = [9,1,8,8,9]
    print(s.findMinMoves(machines))


if __name__ == "__main__":
    run()
