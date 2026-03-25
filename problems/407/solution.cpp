#include <algorithm>
#include <queue>
#include <vector>
using namespace std;

class Solution {
public:
  int trapRainWater(vector<vector<int>> &heightMap) {
    unsigned int m = heightMap.size();
    unsigned int n = heightMap[0].size();
    vector<Tile> initialFrontier;
    bool black[m][n];

    for (unsigned int r = 0; r < m; r++) {
      for (unsigned int c = 0; c < n; c++) {
        if (r == 0 || r == m - 1 || c == 0 || c == n - 1) {
          initialFrontier.push_back(tile(r, c, heightMap[r][c]));
          black[r][c] = true;
        } else {
          black[r][c] = false;
        }
      }
    }

    priority_queue<Tile, vector<Tile>, TileComparator> frontier(
        initialFrontier.begin(), initialFrontier.end());

    int water = 0;
    int ds[4][2] = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    while (!frontier.empty()) {
      Tile lowest = frontier.top();
      frontier.pop();
      water += lowest.h - heightMap[lowest.r][lowest.c];

      for (auto &d : ds) {
        int dr = d[0];
        int dc = d[1];
        int nr = lowest.r + dr;
        int nc = lowest.c + dc;

        if (nr >= 0 && nr < m && nc >= 0 && nc < n && !black[nr][nc]) {
          int nh = max(heightMap[nr][nc], lowest.h);
          frontier.push(tile(nr, nc, nh));
          black[nr][nc] = true;
        }
      }
    }
    return water;
  }

private:
  struct Tile {
    int r;
    int c;
    int h;
  };

  Tile tile(int r, int c, int h) {
    Tile tile;
    tile.r = r;
    tile.c = c;
    tile.h = h;
    return tile;
  }

  class TileComparator {
  public:
    bool operator()(const Tile &lhs, const Tile &rhs) const {
      return lhs.h > rhs.h;
    }
  };
};
