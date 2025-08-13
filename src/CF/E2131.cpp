#include<bits/stdc++.h>
using namespace std;
int solve() {
    int n;
    cin >> n;
    vector<int> a(n), b(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    for (int i = 0; i < n; i++) {
        cin >> b[i];
    }
    if (a[n - 1] != b[n - 1]) {
        cout << "no" << endl;
        return 0;
    }
    bool ok = true;
    for (int i = 0; i < n - 1; i++) {
        if (a[i] == b[i] || (a[i] ^ a[i + 1]) == b[i] || (a[i] ^ b[i + 1]) == b[i])
            continue;
        ok = false;
        break;
    }
    cout << (ok ? "yes" : "no") << endl;
}
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}