#include<bits/stdc++.h>
using namespace std;
#define ll long long
void solve() {
    int n;
    cin >> n;
    vector<int> x(n), y(n);
    multiset<int> X, Y;
    for (int i = 0; i < n; i++) {
        cin >> x[i] >> y[i];
        X.insert(x[i]);
        Y.insert(y[i]);
    }
    if (n <= 2) {
        cout << n << endl;
        return;
    }
    ll res = 1e18;
    for (int i = 0; i < n; i++) {
        X.erase(X.find(x[i]));
        Y.erase(Y.find(y[i]));
        ll lx = 1ll * (*X.rbegin() - *X.begin() + 1);
        ll ly = 1ll * (*Y.rbegin() - *Y.begin() + 1);
        if (lx * ly == n - 1)
            res = min({res, (lx + 1) * ly, lx * (ly + 1)});
        else    
            res = min(res, lx * ly);
        X.insert(x[i]);
        Y.insert(y[i]);

    }
    cout << res << endl;

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