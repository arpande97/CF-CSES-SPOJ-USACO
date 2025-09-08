#include<bits/stdc++.h>
using namespace std;
#define ll long long
void solve() {
    ll a, b;
    cin >> a >> b;
    if ((a & 1) && (b & 1)) {
        cout << (a * b) + 1 << endl;
        return;
    }
    if (!(a & 1) && !(b & 1)) {
        cout << ((a * b)/2 + 2) << endl;
        return;
    }
    if ((a & 1) && !(b % 4)) {
        cout << ((a * b)/2 + 2) << endl;
        return;
    }
    if (!(a & 1) && (b & 1)) {
        cout << -1 << endl;
        return;
    }
    if ((a & 1) && !(b & 1)) {
        cout << -1 << endl;
        return;
    }


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