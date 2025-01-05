#include <vector>
using namespace std;

#include <vector>

class Solution {
public:
  bool check(vector<int> &nums) {
    int decreases = 0;
    int last = nums[0];

    for (int i = 1; i <= nums.size(); i++) {
      if (nums[i % nums.size()] < last) {
        decreases += 1;
      }
      last = nums[i];
    }
    return decreases < 2;
  }
};
