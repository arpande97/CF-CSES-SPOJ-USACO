#include<bits/stdc++.h>
using namespace std;
#define ll long long
void solve() {
    int n, k;
    cin >> n >> k;
    vector<int> a(n), b(n);
    for (auto &it : a) {
        cin >> it;
    }
    for (auto &it : b) {
        cin >> it;
    }
    ll init = 0;
    vector<pair<int, int>> intervals;
    for (int i = 0; i < n; i++) {
        if (a[i] > b[i])
            swap(a[i], b[i]);
        init += b[i] - a[i];
        intervals.push_back({a[i], b[i]});
    }
    sort(intervals.begin(), intervals.end());
    ll min_seg = LONG_MAX;
    for (int i = 0; i < n - 1; i++) {
        if (intervals[i].second >= intervals[i + 1].first) {
            cout << init << endl;
            return;
        }
        min_seg = min(min_seg, 2ll * (intervals[i + 1].first - intervals[i].second));
    }
    cout << min_seg + init << endl;


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