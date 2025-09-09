#include<bits/stdc++.h>
using namespace std;
#define ll long long 
void solve() {
    int n;
    cin >> n;
    string bin;
    cin >> bin;
    int ops = 0;
    for (int i = 0; i < n; i++) {
        if (bin[i] == '0')
            continue;
        int last_0 = -1;
        for (int j = i + 1; j < n; j++) {
            if (bin[j] == '0') {
                last_0 = j;
            }
        }
        if (last_0 != -1) {
            swap(bin[i], bin[last_0]);
            ops++;
        }
    }
    cout << ops << endl;
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