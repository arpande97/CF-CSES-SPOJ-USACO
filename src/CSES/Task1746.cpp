#include<bits/stdc++.h>
using namespace std;
#define ll long long
const ll MOD = (ll)1e9 + 7;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int n, m;
    cin >> n >> m;
    vector<int> arr(n);
    for (auto &it : arr) {
        cin >> it;
    }
    vector<vector<ll>> dp(n + 1, vector<ll>(m + 1));
    fill(dp[n].begin(), dp[n].end(), 1ll);
    for (int i = n - 1; i >= 0; i--) {
        for (int last = 0; last <= m; last++) {
            ll res = 0;
            if (i == 0 && arr[0] == 0) {
                for (int j = 1; j <= m; j++) {
                    res = (res + dp[i + 1][j]) % MOD;
                }
                dp[i][last] = res;
            }
            else {
                if (arr[i] != 0) {
                    if (last != 0 && abs(arr[i] - last) > 1)
                        dp[i][last] = 0;
                    else
                        dp[i][last] = dp[i + 1][arr[i]];
                }
                else {
                    if (last - 1 >= 1) 
                        res = (res + dp[i + 1][last - 1]) % MOD;
                    res = (res + dp[i + 1][last]) % MOD;
                    if (last + 1 <= m)
                        res = (res + dp[i + 1][last + 1]) % MOD;
                    dp[i][last] = res;
                }
            }
        }
    }
    cout << dp[0][0] << endl;


}
// ll solve(int pos, int last, vector<int>& arr, int m) {
//     if (pos == arr.size())
//         return 1ll;
//     if (dp[pos][last] != -1)
//         return dp[pos][last];
//     ll res = 0;
//     if (pos == 0 && arr[pos] == 0) {
//         for (int i = 1; i <= m; i++) {
//             res = (res + solve(pos + 1, i, arr, m)) % MOD;
//         }
//         return dp[pos][last] = res;
//     }
//     if (arr[pos] != 0) {
//         if (last != 0 && abs(arr[pos] - last) > 1)
//             return 0ll;
//         else
//             return dp[pos][last] = solve(pos + 1, arr[pos], arr, m);
//     }
//     else {
//         if (last - 1 >= 1)
//             res = (res + solve(pos + 1, last - 1, arr, m)) % MOD;
//         res = (res + solve(pos + 1, last, arr, m)) % MOD;
//         if (last + 1 <= m)
//             res = (res + solve(pos + 1, last + 1, arr, m)) % MOD;
//         return dp[pos][last] = res;
//     }

    
    
// }