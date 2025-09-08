#include<bits/stdc++.h>
using namespace std;
void solve() {
    int n;
    cin >> n;
    vector<int> p(n);
    for (auto &it : p) {
        cin >> it;
    }
    vector<int> q(n);
    for (int i = 0; i < n; i++) {
        q[i] = n + 1 - p[i];
    }
    for (int i = 0; i < n; i++) {
        cout << q[i] << " ";
    }
    cout << endl;
}
int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    int tc;
    cin >> tc;
    while (tc--) {
        solve();
    }
}