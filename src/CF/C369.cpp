#include<bits/stdc++.h>
using namespace std;
vector<int> d;
void dfs(int u, int parent, vector<vector<int>>& g, vector<int>& bad) {
    d[u] = 0;
    if (bad[u] == 1)
        d[u] = 1;
    for (int v : g[u]) {
        if (v == parent)
            continue;
        dfs(v, u, g, bad);
        d[u] += d[v];
    }
    
}
int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    int n;
    cin >> n;
    vector<vector<int>> g(n);
    vector<int> bad(n);
    for (int i = 0; i < n - 1; i++) {
        int u, v, p;
        cin >> u >> v >> p;
        u--;
        v--;
        if (p == 2) {
            bad[u] = 1;
            bad[v] = 1;
        }
        g[u].push_back(v);
        g[v].push_back(u);
    }
    d.resize(n);
    dfs(0, -1, g, bad);
    vector<int> cand;
    for (int i = 0; i < n; i++) {
        if (d[i] == 1) {
            cand.push_back(i + 1);
        }
    }
    cout << cand.size() << endl;
    for (int candi : cand) {
        cout << candi << " ";
    }
    cout << endl;


}