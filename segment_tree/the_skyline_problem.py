class SegmentTree:
    def __init__(self, n):
        self.flat = [True] * (4 * n)
        self.height = [0] * (4 * n)

    def push(self, v):
        if self.flat[v]:
            self.flat[v * 2] = True
            self.flat[v * 2 + 1] = True
            self.height[v * 2] = self.height[v]
            self.height[v * 2 + 1] = self.height[v]
            self.flat[v] = False

    def update(self, v, tl, tr, l, r, new_height):
        if l > r:
            return
        elif tl == l and tr == r and self.flat[v]:
            if new_height > self.height[v]:
                self.height[v] = new_height
        else:
            self.push(v)
            tm = tl + (tr - tl) // 2
            self.update(v * 2, tl, tm, l, min(r, tm), new_height)
            self.update(v * 2 + 1, tm + 1, tr, max(l, tm + 1), r, new_height)
            self.flat[v] = False


class Solution:
    def getSkyline(self, buildings):
        if not buildings:
            return []

        bounds = set()
        for lb, rb, h in buildings:
            bounds.add(lb)
            bounds.add(rb)

        bounds = sorted(list(bounds))
        bi = {b: i for i, b in enumerate(bounds)}

        n = len(bounds)
        st = SegmentTree(n)
        for lb, rb, h in buildings:
            st.update(1, 0, n - 1, bi[lb], bi[rb] - 1, h)

        critical_points = []

        def populate_critical_points(v, tl, tr):
            if st.flat[v]:
                if not critical_points or critical_points[-1][1] != st.height[v]:
                    critical_points.append([bounds[tl], st.height[v]])
            else:
                tm = tl + (tr - tl) // 2
                populate_critical_points(v * 2, tl, tm)
                populate_critical_points(v * 2 + 1, tm + 1, tr)

        populate_critical_points(1, 0, n - 1)
        return critical_points


def run():
    buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
    sol = Solution()
    print(sol.getSkyline(buildings))


if __name__ == "__main__":
    run()
