class Solution:
    def minDeletionSize(self, A: list) -> int:
        n = len(A)
        m = len(A[0])

        count = 0
        pre_group = [0] * n

        for j in range(m):
            cur_group = [0]
            stop = True
            for i in range(1, n):
                if pre_group[i] != pre_group[i - 1]:
                    cur_group.append(cur_group[-1] + 1)
                else:
                    if A[i][j] > A[i - 1][j]:
                        cur_group.append(cur_group[-1] + 1)
                    elif A[i][j] == A[i - 1][j]:
                        cur_group.append(cur_group[-1])
                        stop = False
                    else:
                        count += 1
                        break
            else:
                if stop:
                    break
                else:
                    pre_group = cur_group
        return count


def run():
    A = ["zyx","wvu","tsr"]
    s = Solution()
    print(s.minDeletionSize(A))


if __name__ == "__main__":
    run()
