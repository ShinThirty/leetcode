#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <utility>

using namespace std;

int valid_palindrome(int& n, int& k, string& s) {
    vector<int> prev(n + 1, 0);
    vector<int> cur(n + 1, 0);
    vector<int>& f = prev;
    vector<int>& g = cur;

    for (int i = n - 1; i >= 0; i--) {
        g[i] = 0;
        g[i + 1] = 1;
        for (int j = i + 2; j <= n; j++) {
            if (s[i] == s[j - 1]) {
                g[j] = f[j - 1] + 2;
            } else {
                g[j] = max(f[j], g[j - 1]);
            }
        }
        swap(f, g);
    }

    cout << "f[n]: " << f[n] << "\n";
    if (f[n] + k >= n) {
        return 1;
    } else {
        return 0;
    }
}

int main()
{
    int t;
    cin >> t;
    for (int i = 0; i < t; i++) {
        int n, k;
        string s;
        cin >> n >> k;
        cin >> s;
        int result = valid_palindrome(n, k, s);
        cout << result << "\n";
    }
}
