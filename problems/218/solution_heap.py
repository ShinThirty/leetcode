from operator import itemgetter
import heapq


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

        START = 0
        END = 1
        events = []
        for i, building in enumerate(buildings):
            lb, rb, h = building
            events.append((bi[lb], i, START))
            events.append((bi[rb], i, END))

        events.sort(key=itemgetter(0, 2))
        n = len(events)
        height_heap = []
        deleted = set()
        prev_height = 0
        critical_points = []
        j = 0
        while j < n:
            b, i, e = events[j]
            if e == START:
                heapq.heappush(height_heap, (-buildings[i][2], i))
                j += 1
                while j < n and events[j][0] == b and events[j][2] == e:
                    b, i, e = events[j]
                    heapq.heappush(height_heap, (-buildings[i][2], i))
                    j += 1
            else:
                deleted.add(i)
                j += 1
                while j < n and events[j][0] == b and events[j][2] == e:
                    b, i, e = events[j]
                    deleted.add(i)
                    j += 1

            while height_heap and height_heap[0][1] in deleted:
                heapq.heappop(height_heap)

            if not height_heap:
                critical_points.append([bounds[b], 0])
                prev_height = 0
            elif prev_height != height_heap[0][0]:
                critical_points.append([bounds[b], -height_heap[0][0]])
                prev_height = height_heap[0][0]

        return critical_points


def run():
    buildings = [[0,2,3],[2,5,3]]
    sol = Solution()
    print(sol.getSkyline(buildings))


if __name__ == "__main__":
    run()
