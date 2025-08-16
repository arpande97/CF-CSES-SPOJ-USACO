#include<bits/stdc++.h>
using namespace std;
void solve() {
     int n;
     cin >> n;
     vector<int> a(n);
     vector<int> count(n + 1, 0);
     for (int i = 0; i < n; i++) {
        cin >> a[i];
        if (a[i] <= n)
            count[a[i]]++;
     }
     vector<int> multiples(n + 1);
     for (int i = 1; i <= n; i++) {
        for (int j = i; j <= n; j += i) {
            multiples[j] += count[i];
        }
     }
     int res = 0;
     for (int i = 1; i <= n; i++) {
        res = max(res, multiples[i]);
     }
     cout << res << endl;
}
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}   