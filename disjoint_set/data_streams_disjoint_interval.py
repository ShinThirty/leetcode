class Node:
    def __init__(self, val):
        self.val = val
        self.parent = None
        self.max = val
        self.min = val
        self.size = 1


class DisjointSet:
    def __init__(self):
        self.nodes = {}

    def add(self, x):
        node = Node(x)
        self.nodes[x] = node
        if x + 1 in self.nodes:
            self.union(x, x + 1)
        if x - 1 in self.nodes:
            self.union(x, x - 1)

    def find(self, x):
        node = self.nodes[x]
        while node.parent:
            if node.parent.parent:
                node.parent = node.parent.parent
            node = node.parent
        return node

    def union(self, x, y):
        x_root = self.find(x)
        y_root = self.find(y)
        if x_root != y_root:
            if x_root.size < y_root.size:
                x_root, y_root = y_root, x_root
            x_root.max = max(x_root.max, y_root.max)
            x_root.min = min(x_root.min, y_root.min)
            x_root.size += y_root.size
            y_root.parent = x_root


class SummaryRanges:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.ds = DisjointSet()

    def addNum(self, val: int) -> None:
        self.ds.add(val)

    def getIntervals(self):
        output = []
        for node in self.ds.nodes:
            if not node.parent:
                output.append([node.min, node.max])
        return output
