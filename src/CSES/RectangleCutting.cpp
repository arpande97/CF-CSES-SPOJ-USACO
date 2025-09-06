#include<bits/stdc++.h>
using namespace std;
int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    int a, b;
    cin >> a >> b;
    vector<vector<int>> dp(a + 1, vector<int>(b + 1, INT_MAX));
    for (int i = 0; i <= a; i++) {
        if (i > b)
            break;
        dp[i][i] = 0;
    }
    for (int x = 1; x <= a; x++) {
        for (int y = 1; y <= b; y++) {
            if (x == y)
                continue;
            for (int cut = 1; cut <= y/2; cut++) {
                    dp[x][y] = min(dp[x][y], 1 + dp[x][cut] + dp[x][y - cut]);
            }
            for (int cut = 1; cut <= x/2; cut++) {
                    dp[x][y] = min(dp[x][y], 1 + dp[cut][y] + dp[x - cut][y]);
            }
        }
    }
    cout << dp[a][b] << endl;

}