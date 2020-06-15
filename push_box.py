import collections

DIRECTIONS = [(-1, 0), (1, 0), (0, -1), (0, 1)]


class Board:
    def __init__(self, grid):
        super().__init__()
        self.config, self.target, self.box, self.player = self.parse_grid(grid)
        self.rows = len(self.config)
        self.cols = len(self.config[0])

    def parse_grid(self, grid):
        config = []
        target = None
        box = None
        player = None
        for r in range(len(grid)):
            config.append([])
            for c in range(len(grid[0])):
                if grid[r][c] == ".":
                    config[r].append(0)
                elif grid[r][c] == "#":
                    config[r].append(1)
                elif grid[r][c] == "B":
                    config[r].append(0)
                    box = (r, c)
                elif grid[r][c] == "T":
                    config[r].append(0)
                    target = (r, c)
                elif grid[r][c] == "S":
                    config[r].append(0)
                    player = (r, c)

        return config, target, box, player

    def can_move(self, box_pos, player_pos, d):
        x, y = box_pos
        dx, dy = d
        nx = x + dx
        ny = y + dy
        ox = x - dx
        oy = y - dy
        px, py = player_pos
        if 0 <= nx < self.rows and 0 <= ny < self.cols and 0 <= ox < self.rows and 0 <= oy < self.cols \
                and self.config[nx][ny] == 0 and self.config[ox][oy] == 0:
            q = collections.deque()
            q.append(player_pos)
            seen = {player_pos}
            while q:
                pos = q.popleft()
                if pos == (ox, oy):
                    return True
                else:
                    px, py = pos
                    for dpx, dpy in DIRECTIONS:
                        npx = px + dpx
                        npy = py + dpy
                        if 0 <= npx < self.rows and 0 <= npy < self.cols and (npx, npy) != box_pos and self.config[npx][npy] == 0 and (npx, npy) not in seen:
                            seen.add((npx, npy))
                            q.append((npx, npy))
            return False
        else:
            return False


class Solution:
    def minPushBox(self, grid):
        board = Board(grid)
        q = collections.deque()
        q.append((board.box, board.player, 0))
        black = {(board.box[0], board.box[1], board.player[0], board.player[1])}

        while q:
            pos, player, dist = q.popleft()
            if pos == board.target:
                return dist
            else:
                x, y = pos
                for d in DIRECTIONS:
                    dx, dy = d
                    nx = x + dx
                    ny = y + dy
                    if board.can_move(pos, player, d) and (nx, ny, x, y) not in black:
                        black.add((nx, ny, x, y))
                        q.append(((nx, ny), pos, dist + 1))

        return -1


def run():
    grid = [["#",".",".","#","T","#","#","#","#"],["#",".",".","#",".","#",".",".","#"],["#",".",".","#",".","#","B",".","#"],["#",".",".",".",".",".",".",".","#"],["#",".",".",".",".","#",".","S","#"],["#",".",".","#",".","#","#","#","#"]]
    for row in grid:
        print(row)
    s = Solution()
    print(s.minPushBox(grid))


if __name__ == "__main__":
    run()
