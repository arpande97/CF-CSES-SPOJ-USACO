#include<bits/stdc++.h>
using namespace std;
#define ll long long 
void solve() {
    ll n, a, b;
    cin >> n >> a >> b;
    if ((n % 2) != (b % 2)) {
        cout << "NO" << endl;
        return;
    }
    if (a > b) {
        if ((n % 2) != (a % 2)) {
            cout << "NO" << endl;
            return;
        }
    }
    cout << "YES" << endl;
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