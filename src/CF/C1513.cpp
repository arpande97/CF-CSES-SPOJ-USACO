#include<bits/stdc++.h>
using namespace std;
#define ll long long
int MOD = 1e9 + 7;
int maxM = 200000;
void solve(vector<vector<int>>& dp) {
    int n, m;
    cin >> n >> m;
    
    int res = 0;
    while (n) {
        res = (res + dp[n % 10][m]) % MOD;
        n /= 10;
    }
    cout << res << endl;
    
}
int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    int tc;
    vector<vector<int>> dp(10, vector<int>(maxM + 1, 0));
    for (int i = 0; i <= 9; i++) {
        dp[i][0] = 1;
    }
    for (int op = 1; op <= maxM; op++) {
        for (int d = 0; d <= 9; d++) {
            if (d == 9) {
                dp[d][op] = (dp[0][op - 1] + dp[1][op - 1]) % MOD;
            }
            else
                dp[d][op] = dp[d + 1][op - 1];
        }
    }
    cin >> tc;
    while (tc--) {
        solve(dp);
    }
}