#include<bits/stdc++.h>
using namespace std;
#define ll long long
void solve() {
    int n;
    cin >> n;
    vector<int> g(n), parent(n), rank(n);
    for (auto &it : g) {
        cin >> it;
    }
    for (int i = 0; i < n; i++) {
        parent[i] = i;
    }
    sort(g.begin(), g.end());
    ll res = 0;
    for (int i = n - 1; i >= 0; i -= 2) {
        res += g[i];
    }
    cout << res << endl;

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