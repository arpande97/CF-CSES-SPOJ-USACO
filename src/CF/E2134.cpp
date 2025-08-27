#include<bits/stdc++.h>
using namespace std;
#define ll long long
int throw_query(int idx) {
    cout << "throw " << idx << endl;
    int x;
    cin >> x;
    return x;

}
void swap_query(int idx) {
    cout << "swap " << idx << endl;
}
void solve() {
    int n;
    cin >> n;
    vector<int> power(n + 1);
    vector<int> d(n + 1);
    d[n] = 1;
    for (int i = n - 1; i >= 1; i--) {
        int next = d[i + 1];
        int next_next = i == n - 1 ? 0 : d[i + 2];
        if (next != next_next) {
            int x = throw_query(i);
            if (x == next + 1) {
                power[i] = 1;
            }
            else {
                power[i] = 2;
            }
            d[i] = x;
        }
        else {
            d[i] = next + 1;
        }
    }
    for (int i = 1; i <= n - 2; i++) {
        if (power[i] == 0) {
            swap_query(i);
            int x = throw_query(i + 1);
            int next = d[i + 2], next_next = i + 3 > n ? 0 : d[i + 3];
            if (x == next + 1) {
                power[i] = 1;
            }
            else    
                power[i] = 2;
        }
    }
    swap_query(n - 1);
    int x = throw_query(n - 1);
    power[n] = x == 1 ? 2 : 1;
    cout << "! ";
    for (int i = 1; i <= n; i++) {
        cout << power[i] << " ";
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