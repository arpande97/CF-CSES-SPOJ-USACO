#include<bits/stdc++.h>
using namespace std;
void solve() {
    int n, k;
    cin >> n >> k;
    vector<int> perm(n);
    string str;
    cin >> str;
    int ones = 0;
    for (int i = 0; i < k; i++) {
        if (str[i] == '1')
            ones++;
    }
    if (ones == k) {
        cout << "No" << endl;
        return;
    }
    for (int i = k; i < n; i++) {
        if (str[i] == '1')
            ones++;
        if (str[i - k] == '1')
            ones--;
        if (ones == k) {
            cout << "No" << endl;
            return;
        }
    }
    cout << "Yes" << endl;
    int num = n;
    for (int i = 0; i < n; i++) {
        if (str[i] == '0') {
            perm[i] = num;
            num--;
        }
    }
    for (int i = 0; i < n; i++) {
        if (str[i] == '1') {
            perm[i] = num;
            num--;
        }
    }
    for (int i = 0; i < n; i++) {
        cout << perm[i] << " ";
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