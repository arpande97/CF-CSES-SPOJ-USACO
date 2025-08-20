#include<bits/stdc++.h>
using namespace std;
int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    int n;
    cin >> n;
    vector<vector<int>> g(n);
    for (int i = 0; i < n - 1; i++) {
        int u;
        cin >> u;
        u--;
        g[u].push_back(i + 1);
    }
    queue<int> q;
    q.push(0);
    int res = 0;
    while (!q.empty()) {
        int sz = q.size();
        if (sz % 2 != 0)
            res++;
        for (int i = 0; i < sz; i++) {
            int u = q.front();
            q.pop();
            for (int v : g[u]) {
                q.push(v);
            }
        }
    }
    cout << res << endl;
}