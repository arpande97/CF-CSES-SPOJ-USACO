#include<bits/stdc++.h>
using namespace std;
#define ll long long
void solve() {
    ll n;
    cin >> n;
    vector<ll> og;
    for (ll d = 10; d <= n; d *= 10) {
        if (n % (d + 1) == 0)
            og.push_back(n/(d + 1));
    }
    if (og.size() == 0) {
        cout << 0 << endl;
        return;
    }
    cout << og.size() << endl;
    for (int i = og.size() - 1; i >= 0; i--) {
        cout << og[i] << " ";
    }
    cout << endl;

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