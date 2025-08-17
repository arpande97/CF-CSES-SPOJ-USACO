#include<bits/stdc++.h>
using namespace std;
const int max_val = INT_MAX;
void solve() {
    int n;
    cin >> n;
    vector<int> arr(n);
    for (auto &it : arr) {
        cin >> it;
    }
    vector<int> dp(n + 1);
    for (int i = n - 1; i >= 0; i--) {
        int pick = max_val, skip = max_val;
        if (arr[i] + i < n)
            pick = dp[arr[i] + i + 1];
        skip = 1 + dp[i + 1];
        dp[i] = min(pick, skip);
    }
    cout << dp[0] << endl;
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