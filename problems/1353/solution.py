from operator import itemgetter
import heapq


class Solution:
    def maxEvents(self, events) -> int:
        sorted_events = sorted(events, key=itemgetter(0))
        event_heap = []

        day = 1
        i = 0
        n = len(sorted_events)
        num = 0
        while i < n or event_heap:
            if not event_heap:
                day = sorted_events[i][0]

            while i < n and sorted_events[i][0] == day:
                heapq.heappush(event_heap, sorted_events[i][1])
                i += 1
                if i == n:
                    break

            heapq.heappop(event_heap)
            num += 1

            while event_heap and event_heap[0] == day:
                heapq.heappop(event_heap)

            day += 1

        return num


def run():
    events = [[1,2],[1,2],[3,3],[1,5],[1,5]]
    sol = Solution()
    print(sol.maxEvents(events))


if __name__ == "__main__":
    run()