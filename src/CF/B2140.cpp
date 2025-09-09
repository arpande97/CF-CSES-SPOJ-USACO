#include<bits/stdc++.h>
using namespace std;
#define ll long long
void solve() {
    int x;
    cin >> x;
    vector<ll> pow(11);
    pow[0] = 1;
    for (int i = 1; i <= 10; i++) {
        pow[i] = 10 * pow[i - 1];
    }
    for (int d = 1; d <= 9; d++) {
        ll val = (pow[d] - 1);
        if (val <= x)
            continue;
        ll y = val - x;
        int dig_y = floor(log10(y)) + 1;
        int dig_val = floor(log10(val)) + 1;
        if (dig_y == dig_val) {
            cout << y << endl;
            return;
        }

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