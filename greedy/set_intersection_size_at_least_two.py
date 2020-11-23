from operator import itemgetter


class Solution:
    def intersectionSizeTwo(self, intervals) -> int:
        sorted_intervals = sorted(intervals, key=itemgetter(1))

        size = 0
        left = -float('inf')
        right = -float('inf')
        for a, b in sorted_intervals:
            if a > right:
                size += 2
                left = b - 1
                right = b
            elif left < a <= right:
                size += 1
                left = right
                right = b

        return size
