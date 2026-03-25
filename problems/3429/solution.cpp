#include <climits>
#include <vector>
using namespace std;

class Solution {
public:
  long long minCost(int n, vector<vector<int>> &cost) {
    int l = cost.size() / 2;
    using solution = long long[3][3];

    solution f;
    solution nf;
    solution *p = &f;
    solution *np = &nf;
    for (int c1 = 0; c1 < 3; c1++) {
      for (int c2 = 0; c2 < 3; c2++) {
        if (c1 == c2) {
          (*np)[c1][c2] = LONG_LONG_MAX;
        } else {
          (*np)[c1][c2] = cost[0][c1] + cost[n - 1][c2];
        }
      }
    }

    for (int h = 1; h < l; h++) {
      solution *t = p;
      p = np;
      np = t;

      for (int c1 = 0; c1 < 3; c1++) {
        for (int c2 = 0; c2 < 3; c2++) {
          if (c1 == c2) {
            (*np)[c1][c2] = LONG_LONG_MAX;
          } else {
            long long prev_min_cost = LONG_LONG_MAX;
            for (int cp1 = 0; cp1 < 3; cp1++) {
              for (int cp2 = 0; cp2 < 3; cp2++) {
                if (cp1 != cp2 && cp1 != c1 && cp2 != c2) {
                  prev_min_cost = min(prev_min_cost, (*p)[cp1][cp2]);
                }
              }
            }

            (*np)[c1][c2] = prev_min_cost + cost[h][c1] + cost[n - 1 - h][c2];
          }
        }
      }
    }

    long long min_cost = LONG_LONG_MAX;
    for (int c1 = 0; c1 < 3; c1++) {
      for (int c2 = 0; c2 < 3; c2++) {
        min_cost = min(min_cost, (*np)[c1][c2]);
      }
    }

    return min_cost;
  }
};
