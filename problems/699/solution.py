class SegmentTree:
    def __init__(self, n):
        self.t = [0] * (4 * n)
        self.marked = [False] * (4 * n)

    def push(self, v):
        if self.marked[v]:
            self.t[v * 2] = self.t[v]
            self.marked[v * 2] = True
            self.t[v * 2 + 1] = self.t[v]
            self.marked[v * 2 + 1] = True
            self.marked[v] = False

    def update(self, v, tl, tr, l, r, new_value):
        if l > r:
            return
        elif tl == l and tr == r:
            self.t[v] = new_value
            self.marked[v] = True
        else:
            self.push(v)
            tm = tl + (tr - tl) // 2
            self.update(v * 2, tl, tm, l, min(r, tm), new_value)
            self.update(v * 2 + 1, tm + 1, tr, max(l, tm + 1), r, new_value)
            self.t[v] = max(self.t[v * 2], self.t[v * 2 + 1])

    def query(self, v, tl, tr, l, r):
        if l > r:
            return -1
        elif (tl == l and tr == r) or self.marked[v]:
            return self.t[v]
        else:
            tm = tl + (tr - tl) // 2
            return max(self.query(v * 2, tl, tm, l, min(r, tm)), self.query(v * 2 + 1, tm + 1, tr, max(l, tm + 1), r))


class Solution:
    def fallingSquares(self, positions):
        if not positions:
            return 0

        bounds = set()
        for left, side in positions:
            bounds.add(left)
            bounds.add(left + side)

        bounds = sorted(list(bounds))
        bi = {b: i for i, b in enumerate(bounds)}

        n = len(bounds)
        st = SegmentTree(n)
        heights = []
        for left, side in positions:
            lb = left
            rb = left + side
            old_height = st.query(1, 0, n - 1, bi[lb], bi[rb] - 1)
            st.update(1, 0, n - 1, bi[lb], bi[rb] - 1, old_height + side)
            heights.append(st.t[1])

        return heights


def run():
    positions = [[1,5],[2,2],[7,5]]
    sol = Solution()
    print(sol.fallingSquares(positions))


if __name__ == "__main__":
    run()
