import sys


row = sys.stdin.readline()
Ns, ks = row.split()
N, k = int(Ns), int(ks)
rooms = [[0] * (N + 1), [0] * (N + 1)]
ss = 0
for i in range(N + 1):
    row = sys.stdin.readline()
    value0, value1 = row.split()
    rooms[0][i] = int(value0)
    rooms[1][i] = int(value1)
    ss += rooms[0][i]
    ss += rooms[1][i]

prev = [[float("inf")] * 3 for _ in range(k + 1)]
cur = [[float("inf")] * 3 for _ in range(k + 1)]
prev[0][0] = 0
prev[0][1] = 0
prev[0][2] = 0
for i_n in range(1, N + 2):
    cur[0][0] = 0
    cur[0][1] = 0
    cur[0][2] = 0

    for i_k in range(1, min(k + 1, i_n + 1)):
        cur[i_k][0] = min(prev[i_k][0], prev[i_k][1], prev[i_k][2])
        cur[i_k][1] = min(prev[i_k - 1][0], prev[i_k - 1][1]) + rooms[0][i_n - 1]
        cur[i_k][2] = min(prev[i_k - 1][0], prev[i_k - 1][2]) + rooms[1][i_n - 1]

    cur, prev = prev, cur

print(ss - prev[k][0])
