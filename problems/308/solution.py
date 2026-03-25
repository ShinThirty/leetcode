class NumMatrix(object):

    def __init__(self, matrix):
        """
        :type matrix: List[List[int]]
        """
        self.data = matrix
        self.t = [[0] * (len(matrix[0]) + 1) for _ in range(len(matrix) + 1)]
        i = 1
        for row in matrix:
            j = 1
            for num in row:
                self.t[i][j] += num
                nj = j + (j & -j)
                if nj <= len(matrix[0]):
                    self.t[i][nj] += self.t[i][j]
                j += 1
            i += 1

        for i in range(1, len(self.t)):
            ni = i + (i & -i)
            if ni < len(self.t):
                for j in range(1, len(self.t[0])):
                    self.t[ni][j] += self.t[i][j]

    def update(self, row, col, val):
        """
        :type row: int
        :type col: int
        :type val: int
        :rtype: void
        """
        delta = val - self.data[row][col]
        self.data[row][col] = val
        i = row + 1
        while i < len(self.t):
            j = col + 1
            while j < len(self.t[0]):
                self.t[i][j] += delta
                j += (j & -j)
            i += (i & -i)

    def sumRegion(self, row1, col1, row2, col2):
        """
        :type row1: int
        :type col1: int
        :type row2: int
        :type col2: int
        :rtype: int
        """
        def prefix_sum(rb, cb):
            psum = 0
            i = rb
            while i > 0:
                j = cb
                while j > 0:
                    psum += self.t[i][j]
                    j -= (j & -j)
                i -= (i & -i)
            return psum

        return prefix_sum(row2 + 1, col2 + 1) + prefix_sum(row1, col1) \
            - prefix_sum(row2 + 1, col1) - prefix_sum(row1, col2 + 1)


def run():
    matrix = [[3,0,1,4,2],
            [5,6,3,2,1],
            [1,2,0,1,5],
            [4,1,0,1,7],
            [1,0,3,0,5]]

    nm = NumMatrix(matrix)
    nm.sumRegion(2, 1, 4, 3)
    nm.update(3, 2, 2)
    nm.sumRegion(2, 1, 4, 3)


if __name__ == "__main__":
    run()
