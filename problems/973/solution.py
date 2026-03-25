import random


class Solution:
    def kClosest(self, points: list, K: int) -> list:
        distances = []
        for x, y in points:
            distances.append(x * x + y * y)

        def swap(i, j):
            distances[i], distances[j] = distances[j], distances[i]
            points[i], points[j] = points[j], points[i]

        def random_partition(start: int, end: int) -> int:
            p = random.randint(start, end)
            pivot = distances[p]
            swap(p, end)

            i, j = start, end - 1
            while i <= j:
                while i < end and distances[i] <= pivot:
                    i += 1
                while j >= start and distances[j] > pivot:
                    j -= 1

                if i < j:
                    swap(i, j)
            swap(i, end)
            return i

        start, end = 0, len(distances) - 1
        while start < end:
            i = random_partition(start, end)

            if i == K - 1:
                break
            elif i < K - 1:
                start = i + 1
            else:
                end = i - 1

        return points[:K]


def run():
    points = [[6,10],[-3,3],[-2,5],[0,2]]
    K = 3
    s = Solution()
    print(s.kClosest(points, K))


if __name__ == "__main__":
    run()
