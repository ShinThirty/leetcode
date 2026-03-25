import collections


class Solution(object):
    def boxDelivering(self, boxes, portsCount, maxBoxes, maxWeight):
        """
        :type boxes: List[List[int]]
        :type portsCount: int
        :type maxBoxes: int
        :type maxWeight: int
        :rtype: int
        """

        # The minimum number of trips is achieved by grouping all boxes in the same ship
        # Adding a ship will increase the number of trips by 1 or 2, depending on whether
        # the split locates between boxes with different ports or the same port
        # Record (index, increase_of_trips) and calculate the sliding window minimum
        # Queue head would be the starting point of current ship
        # Then we are able to calculate the increase of trips with the new ship ends at
        # current index
        n = len(boxes)
        q = collections.deque([(-1, 0)])  # dummy value for marking the end of "no" previous ship
        p = [0] * (n + 1)
        for i in range(n):
            p[i + 1] = p[i] + boxes[i][1]

        weight = 0
        base_trips = 0
        for i in range(n):
            weight += boxes[i][1]
            while i - q[0][0] > maxBoxes or weight > maxWeight:
                prev_end, _ = q.popleft()
                weight -= p[q[0][0] + 1] - p[prev_end + 1]

            # it might be that the actual prev_end is larger or equals to the prev_end
            trip_increase = q[0][1] + (1 if i == n - 1 or boxes[i][0] != boxes[i+1][0] else 2)

            while q[-1][1] >= trip_increase:
                q.pop()

            q.append((i, trip_increase))

            base_trips += 1 if i == 0 or boxes[i-1][0] != boxes[i][0] else 0

        return base_trips + q[-1][1]
