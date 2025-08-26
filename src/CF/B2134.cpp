#include<bits/stdc++.h>
#define ll long long
using namespace std;
void solve() {
    int n, k;
    cin >> n >> k;
    vector<ll> arr(n);
    for (auto &it : arr) {
        cin >> it;
    }
    for (int i = 0; i < n; i++) {
        ll a = arr[i];
        ll a_new = a + (a % (k + 1)) * k;
        arr[i] = a_new;

    }
    for (int i = 0; i < n; i++) {
        cout << arr[i] << " ";
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