#include<bits/stdc++.h>
using namespace std;
const int MOD = 1e9 + 7;
int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    int n, x;
    cin >> n >> x;
    vector<int> coins(n);
    for (auto &it : coins) {
        cin >> it;
    }
    vector<int> next(x + 1);
    next[0] = 1;
    for (int pos = n - 1; pos >= 0; pos--) {
        vector<int> curr(x + 1);
        for (int sum = 0; sum <= x; sum++) {
            int pick = 0;
            if (sum - coins[pos] >= 0)
                pick = curr[sum - coins[pos]];
            int skip = next[sum];
            curr[sum] = (pick + skip) % MOD;
        }
        next = curr;
    }
    cout << next[x] << endl;
    






}