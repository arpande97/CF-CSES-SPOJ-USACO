#include<bits/stdc++.h>
using namespace std;
#define ll long long
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int n, m;
    cin >> n >> m;
    vector<ll> a(n), b(m);
    for (auto &it : a) {
        cin >> it;
    }
    for (auto &it : b) {
        cin >> it;
    }
    ll G = 0;
    for (int i = 1; i < n; i++) {
        G = gcd(G, a[i] - a[0]);
    }
    vector<ll> res(m);
    for (int i = 0; i < m; i++) {
        cout << gcd(G, a[0] + b[i]) << " ";
    }
    cout << endl;
    return 0;
}