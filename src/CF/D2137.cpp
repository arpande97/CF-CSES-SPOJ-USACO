#include<bits/stdc++.h>
using namespace std;
void solve() {
    int n;
    cin >> n;
    vector<int> b(n);
    unordered_map<int, int> map;
    unordered_map<int, vector<int>> idx;
    for (int i = 0; i < n; i++) {
        cin >> b[i];
        map[b[i]]++;
        idx[b[i]].push_back(i);
    }
    for (int i = 0; i < n; i++) {
        int j = map[b[i]];
        if (j % b[i]) {
            cout << -1 << endl;
            return;
        }
    }
    vector<int> a(n);
    int num = 1;
    for (auto &entry : idx) {
        int key = entry.first, j = 0;
        vector<int>& indices = entry.second;
        for (int i = 0; i < indices.size()/key; i++) {
            int cnt = key;
            while (cnt--) {
                a[indices[j++]] = num;
            }
            num++;
        }
    }
    for (int i = 0; i < n; i++) {
        cout << a[i] << " ";
    }
    cout << endl;
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