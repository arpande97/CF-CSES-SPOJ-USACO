#include<bits/stdc++.h>
using namespace std;
int global_timer = 0;
bool ok = false;
int a, b, c;
void dfs(int u, int parent, int d, vector<vector<int>>& g, vector<int>& dist) {
    dist[u] = d;
    for (int v : g[u]) {
        if (v != parent) {
            dfs(v, u, d + 1, g, dist);
        }
    }
}
void dfs2(int u, int parent, int d, vector<vector<int>>& g, vector<int>& dist, vector<int>& in, vector<int>& out) {
    in[u] = global_timer;
    global_timer++;
    dist[u] = d;
    for (int v : g[u]) {
        if (v == parent)
            continue;
        dfs2(v, u, d + 1, g, dist, in, out);
    }
    out[u] = global_timer;
    global_timer++;
}
void dfs3(int u, int parent, int endpoint_b, vector<vector<int>>& g, vector<int>& in, vector<int>& out) {
    if (g[u].size() > 2 && !ok) {
        ok = true;
        b = u;
        a = parent;
        for (int v : g[u]) {
            if (v != parent && !(in[v] <= in[endpoint_b] && out[endpoint_b] <= out[v])) {
                c = v;
                break;
            }
        }
    }
    for (int v : g[u]) {
        if (v != parent)
            dfs3(v, u, endpoint_b, g, in, out);
    }
}
void solve() {
    int n;
    cin >> n;
    
    vector<int> indegree(n);
    vector<vector<int>> g(n);     
    vector<int> dist(n), in(n), out(n);
    ok = false;
    a = b = c = 0;
    bool path_graph = true;
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;
        u--;
        v--;
        indegree[u]++;
        indegree[v]++;
        if (indegree[u] > 2 || indegree[v] > 2) {
            path_graph = false;
        }
        g[u].push_back(v);
        g[v].push_back(u);
    }
    
    if (path_graph) {
        cout << -1 << endl;
        return;
    }
    /*
    - the idea here is that diameter is the longest path in a tree
    - in each operation you can try to increase the diameter by 1
    - is there a way to increase the path length by more than 1? No
    - so root the tree in one of the diameter endpoints
    - chose a node u on diameter path as b and v not in diameter path as c and a will be the parent of u
    - 
    */

    dfs(0, -1, 0, g, dist);
    
    int endpoint = 0, max_dist = 0;
    for (int i = 0; i < n; i++) {
        if (dist[i] > max_dist) {
            max_dist = dist[i];
            endpoint = i;
        }
    }
    
    fill(dist.begin(), dist.end(), 0);
    
    dfs2(endpoint, -1, 0, g, dist, in, out);
    int endpoint_b = 0;
    max_dist = 0;
    for (int i = 0; i < n; i++) {
        if (dist[i] > max_dist) {
            max_dist = dist[i];
            endpoint_b = i;
        }
    }
    dfs3(endpoint, -1, endpoint_b, g, in, out);
    cout << a + 1 << " " << b + 1 << " " << c + 1 << endl;


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