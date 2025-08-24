#include<bits/stdc++.h>
using namespace std;
#define ll long long 
void solve() {
    int n;
    cin >> n;
    vector<int> arr(n);
    for (auto &it : arr) {
        cin >> it;
    }
    //x number of teeth of curr
    //y = number of teeth gear to left
    //z = speed of gear to left
    //speed of curr = z * (y/x)
    // first geear speed z = 1
    vector<int> count(101);
    bool ok = false;
    for (int i = 0; i < n; i++) {
        count[arr[i]]++;
        if (count[arr[i]] > 1) {
            ok = true;
            break;
        }
    }
    cout << (ok ? "YES" : "NO") << endl;

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