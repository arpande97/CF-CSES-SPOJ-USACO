#include<bits/stdc++.h>
using namespace std;
vector<int> dp;
int rec(int pos, vector<int>& arr, vector<int>& next) {
    if (pos >= arr.size())
        return 0;
    if (dp[pos] != -1)
        return dp[pos];
    int op2 = 0;
    int op1 = rec(pos + 1, arr, next);
    int block = arr[pos];
    if (next[pos] != arr.size())
        op2 = block + rec(next[pos] + 1, arr, next);
    
    return dp[pos] = max(op1, op2);
}
void solve() {
    int n;
    cin >> n;
    vector<int> arr(n);
    for (auto &it : arr) {
        cin >> it;
    }
    dp.assign(n, -1);
    unordered_map<int, vector<int>> map;
    for (int i = 0; i < n; i++) {
        map[arr[i]].push_back(i);
    }
    vector<int> next(n);
    for (const auto it : map) {
        int key = it.first;
        vector<int> indices = it.second;
        int sz = indices.size();
        for (int i = 0; i < sz; i++) {
            int idx = indices[i];
            if (i + key - 1 >= sz)
                next[idx] = n;
            else
                next[idx] = indices[i + key - 1];
        }
    }
    int best = rec(0, arr, next);
    cout << best << endl;
}
int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}