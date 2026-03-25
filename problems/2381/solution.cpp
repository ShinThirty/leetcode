using namespace std;

#include <string>
#include <vector>

class Solution {
public:
  string shiftingLetters(string s, vector<vector<int>> &shifts) {
    const int l = s.length();
    int *acc = new int[l + 1];
    for (int i = 0; i < l + 1; i++) {
      acc[i] = 0;
    }

    for (auto it = shifts.begin(); it != shifts.end(); ++it) {
      acc[start(*it)] += direction(*it);
      acc[end(*it) + 1] -= direction(*it);
    }

    int cur = 0;
    char *text = new char[l + 1];
    for (int i = 0; i < l; i++) {
      cur += acc[i];
      text[i] = shift(s[i], cur);
    }
    text[l] = '\0';

    delete[] acc;
    string res(text);
    delete[] text;

    return res;
  }

private:
  int start(vector<int> &shift) { return shift[0]; }
  int end(vector<int> &shift) { return shift[1]; }
  int direction(vector<int> &shift) {
    if (shift[2] == 0) {
      return -1;
    } else {
      return 1;
    }
  }
  char shift(char c, int n) {
    int offset = ((n % 26) + 26) % 26;
    return 'a' + (c - 'a' + offset) % 26;
  }
};
