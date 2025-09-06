#include<bits/stdc++.h>
using namespace std;
vector<int> del;
void dfs(int u, int par, vector<int>& c, vector<vector<int>>& g) {
    bool to_del = par != -1 && c[u] == 1;
    for (int v : g[u]) {
        if (v == par)
            continue;
        if (c[v] == 0)
            to_del = false;
        dfs(v, u, c, g);
    }
    if (to_del)
        del.push_back(u + 1);
}
int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    int n; 
    cin >> n;
    int root;
    vector<vector<int>> g(n);
    vector<int> c;
    for (int i = 0; i < n; i++) {
        int p, resp;
        cin >> p >> resp;
        c.push_back(resp);
        if (p != -1) {
            p--;
            g[i].push_back(p);
            g[p].push_back(i);
        }
        else    
            root = i;
    }
    dfs(root, -1, c, g);
    if (del.size() == 0) {
        cout << -1 << endl;
        return 0;
    }
    sort(del.begin(), del.end());
    for (int d : del) {
        cout << d << " ";
    }
    cout << endl;

}