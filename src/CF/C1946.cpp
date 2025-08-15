#include<bits/stdc++.h>
using namespace std;
vector<vector<int>> g;
vector<int> sub;
int e;
void dfs(int u, int parent, int x) {
    sub[u] = 1;
    for (int v : g[u]) {
        if (v == parent)
            continue;
        dfs(v, u, x);
        sub[u] += sub[v];
    }
    if (e != 0 && sub[u] >= x) {
        sub[u] = 0;
        e--;
    }
}
bool valid(int x, int k, int n) {
    sub.assign(n, 0);
    e = k;
    dfs(0, -1, x);
    return e == 0 && sub[0] >= x;
}
void solve() {
    int n, k;
    cin >> n >> k;
    g.assign(n, {});
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;
        u--;
        v--;
        g[u].push_back(v);
        g[v].push_back(u);
    }
    int l = 1, r = n - 1, res = 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (valid(mid, k, n)) {
            res = mid;
            l = mid + 1;
        }
        else 
            r = mid - 1;
    }
    cout << res << endl;

}
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}