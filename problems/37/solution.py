import sys


class Solution:
    def solveSudoku(self, board):
        """
        Do not return anything, modify board in-place instead.
        """
        def to_string(board):
            print("===================")
            for row in board:
                for cell in row:
                    sys.stdout.write(f"{cell} ")
                sys.stdout.write("\n")

        def sbox(i, j):
            return (i // 3) * 3 + j // 3

        rows = [[False] * 9 for _ in range(9)]
        cols = [[False] * 9 for _ in range(9)]
        boxes = [[False] * 9 for _ in range(9)]
        for i in range(9):
            for j in range(9):
                if board[i][j].isdigit():
                    c = int(board[i][j]) - 1
                    rows[i][c] = True
                    cols[j][c] = True
                    boxes[sbox(i, j)][c] = True

        def fill_number(i, j):
            if i == 9:
                return True
            elif j == 9:
                return fill_number(i + 1, 0)
            elif board[i][j] != ".":
                return fill_number(i, j + 1)
            else:
                for c in range(9):
                    if not rows[i][c] and not cols[j][c] and not boxes[sbox(i, j)][c]:
                        board[i][j] = str(c + 1)
                        rows[i][c] = True
                        cols[j][c] = True
                        boxes[sbox(i, j)][c] = True

                        if fill_number(i, j + 1):
                            return True

                        boxes[sbox(i, j)][c] = False
                        cols[j][c] = False
                        rows[i][c] = False
                        board[i][j] = "."
                return False

        fill_number(0, 0)
        to_string(board)


def run():
    board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
    s = Solution()
    s.solveSudoku(board)


if __name__ == "__main__":
    run()
