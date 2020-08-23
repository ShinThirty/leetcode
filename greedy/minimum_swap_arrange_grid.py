class Solution:
    def minSwaps(self, grid) -> int:
        n = len(grid)
        lsb = []
        for row in grid:
            for j in range(n - 1, -1, -1):
                if row[j] == 1:
                    lsb.append(j)
                    break
            else:
                lsb.append(-1)

        count = 0
        for i in range(n - 1):
            if lsb[i] > i:
                j = i + 1
                while j < n:
                    if lsb[j] <= i:
                        break
                    j += 1
                if j == n:
                    return -1
                num = lsb.pop(j)
                lsb.insert(i, num)
                count += j - i

        return count


def run():
    grid = [[1,0,0,0,0,0],[0,0,0,1,0,0],[0,0,0,1,0,0],[0,1,0,0,0,0],[0,0,1,0,0,0],[0,0,0,0,0,1]]
    sol = Solution()
    sol.minSwaps(grid)


if __name__ == "__main__":
    run()
