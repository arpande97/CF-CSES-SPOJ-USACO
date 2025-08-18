#include<bits/stdc++.h>
using namespace std;
void solve() {
    int n, k;
    cin >> n >> k;
    vector<int> in(n);
    vector<vector<int>> g(n);
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;
        u--;
        v--;
        in[u]++;
        in[v]++;
        g[u].push_back(v);
        g[v].push_back(u);
    }
    if (n == 1) {
        cout << 0 << endl;
        return;
    }
    queue<int> q;
    for (int i = 0; i < n; i++) {
        if (in[i] == 1)
            q.push(i);
    }
    int removed = 0;
    while (k-- && !q.empty()) {
        removed += q.size();
        int sz = q.size();
        for (int i = 0; i < sz; i++) {
            int u = q.front();
            q.pop();
            for (int v : g[u]) {
                in[v]--;
                if (in[v] == 1)
                    q.push(v);
            }
        }
    }
    cout << n - removed << endl;
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