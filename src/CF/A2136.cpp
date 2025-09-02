#include<bits/stdc++.h>
using namespace std;
void solve() {
    int a, b, c, d;
    cin >> a >> b >> c >> d;
    c -= a;
    d -= b;
    if (a > b) {
        if (2 * (b + 1) < a) {
            cout << "No" << endl;
            return;
        }
    }
    if (b > a) {
        if (2 * (a + 1) < b) {
            cout << "No" << endl;
            return;
        }
    }
    if (c > d) {
        if (2 * (d + 1) < c) {
            cout << "No" << endl;
            return;
        }
    }
    if (d > c) {
        if (2 * (c + 1) < d) {
            cout << "No" << endl;
            return;
        }
    }
    cout << "Yes" << endl;
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