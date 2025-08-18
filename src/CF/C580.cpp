#include<bits/stdc++.h>
using namespace std;
vector<vector<int>> g;
vector<int> cats;
int dfs(int u, int consec, int parent, int m) {
    consec = cats[u] == 1 ? consec + 1 : 0;
    if (consec > m)
        return 0;
    if (g[u].size() == 1 && g[u][0] == parent)
        return 1;
    int paths = 0;
    for (int v : g[u]) {
        if (v == parent)
            continue;
        paths += dfs(v, consec, u, m);
    }
    return paths;

}
int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int n, m;
    cin >> n >> m;
    cats.resize(n);
    for (auto &it : cats) {
        cin >> it;
    }
    g.resize(n);
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;
        u--;
        v--;
        g[u].push_back(v);
        g[v].push_back(u);
    }
    int res = dfs(0, 0, -1, m);
    cout << res << endl;

}