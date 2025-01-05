#include <set>
using namespace std;

#include <map>
#include <vector>

class Solution {
public:
  long long numberOfSubsequences(vector<int> &nums) {
    int n = nums.size();
    map<Ratio, int> pq;

    for (int i = 0; i < n / 2; i++) {
      // p
      int p = nums[i];

      for (int j = i + 1; i < n / 2; i++) {
        // q
        int q = nums[j];
      }
    }
  }

private:
  struct Ratio {
    int a;
    int b;

    bool operator<(const Ratio &other) { return a / b; }
  };

  int gcd(int p, int q) {
    if (p == q)
      return p;
    if (p > q) {
      int t = p;
      p = q;
      q = t;
    }
  }
};
