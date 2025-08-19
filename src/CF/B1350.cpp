#include<bits/stdc++.h>
using namespace std;
void solve() {
    int n;
    cin >> n;
    vector<int> sizes(n);
    for (auto &it : sizes) {
        cin >> it;
    }
    vector<vector<int>> dp(n + 2, vector<int>(2, 0));
    for (int i = n; i >= 1; i--) {
        for (int start = 0; start < 2; start++) {
            int skip = 0;
            if (start == 0)
                skip = dp[i + 1][0];
            int pick = 0;
            for (int inc = 2 * i; inc <= n; inc += i) {
                if (sizes[inc - 1] > sizes[i - 1])
                    pick = max(pick, dp[inc][1]);
            }
            dp[i][start] = max(skip, pick + 1);
        }
    }
   cout << dp[1][0] << endl;

}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}