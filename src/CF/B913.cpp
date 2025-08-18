#include<bits/stdc++.h>
using namespace std;
vector<int> sub;
vector<vector<int>> g;
bool dfs(int u) {
    sub[u] = 1;
    int count = 0;
    for (int v : g[u]) {
        if (!dfs(v))
            return false;
        sub[u] += sub[v];
        if (sub[v] == 1)
            count++;
    }
    if (sub[u] == 1)
        return true;
    return count >= 3;
}
int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int n;
    cin >> n;
    sub.resize(n);
    g.resize(n);
    for (int i = 0; i < n - 1; i++) {
        int x;
        cin >> x;
        x--;
        g[x].push_back(i + 1);
    }
    string ans = dfs(0) ? "Yes" : "No";
    cout << ans << endl;

}