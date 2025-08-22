#include<bits/stdc++.h>
using namespace std;
#define ll long long

void solve() {
    int n;
    cin >> n;
    
    vector<ll> pow3(21), cost(20);
    pow3[0] = 1ll;
    cost[0] = 3ll;
    for (int i = 1; i <= 20; i++) {
        pow3[i] = 3ll * pow3[i - 1];
    }
    for (int i = 1; i <= 19; i++) {
        cost[i] = pow3[i + 1] + 1ll * i * pow3[i - 1];
    }
    ll coins = 0;
    for (int i = 19; i >= 0; i--) {
        if (n >= pow3[i]) {
            ll d = n/pow3[i];
            coins += (d * cost[i]);
            n %= pow3[i];
        }
    }
    cout << coins << endl;
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