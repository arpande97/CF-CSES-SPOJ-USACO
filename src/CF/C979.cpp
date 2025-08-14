#include<bits/stdc++.h>
using namespace std;
vector<vector<int>> g;
vector<int> subtree_size;
int global_timer = 0;
vector<int> in, out;
//nice problem
//calculate all the paths that can exist -> nc2 * 2 -> (* 2 because (u, v) is not same as (v, u))
//then subtract invalid paths -> root the tree at x -> all invalid paths start from nodes in subtree of x
//which doesn't have y 
//they have to end at nodes in subtree of y for it to be invalid
//but paths starting from x can end at nodes (they are valid) which end before y tree
//so invalid subtree[bee] * (subtree[flower] - subtree[z]) where z is the direct child of x and ancestor of y
//for this, use in-out trick
void dfs(int u, int parent) {
    in[u] = global_timer;
    global_timer++;
    subtree_size[u] = 1;
    for (int v : g[u]) {
        if (v == parent)
            continue;
        dfs(v, u);
        subtree_size[u] += subtree_size[v];
    }
    out[u] = global_timer;
    global_timer++;
}
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n, x, y;
    cin >> n >> x >> y;
    x--;
    y--;
    g.resize(n);
    in.resize(n);
    out.resize(n);
    subtree_size.resize(n);
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;
        u--;
        v--;
        g[u].push_back(v);
        g[v].push_back(u);
    }
    //z is ancestor of y and direct child of x
    dfs(x, -1);
    int z;
    for (int v : g[x]) {
        if (in[v] <= in[y] && out[y] <= out[v]) {
            z = v;
            break;
        }
    }
    long long total = (1ll * n * (n - 1));
    long long invalid = 1ll * (subtree_size[x] - subtree_size[z]) * subtree_size[y];
    cout << total - invalid << endl;


}