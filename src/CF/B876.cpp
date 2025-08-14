#include<bits/stdc++.h>
using namespace std;
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int n, k, m;
    cin >> n >> k >> m;
    vector<int> arr(n), rem(m);
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
        rem[arr[i] % m]++;
    }
    int chosen = -1;
    for (int i = 0; i < m; i++) {
        if (rem[i] >= k) {
            chosen = i;
            break;
        }
    }
    if (chosen == -1) {
        cout << "No" << endl;
        return 0;
    }
    cout << "Yes" << endl;
    int cnt = 0;
    for (int i = 0; i < n && cnt < k; i++) {
        if (arr[i] % m == chosen) {
            cout << arr[i] << " ";
            cnt++;
        }
            
    }
    cout << endl;
}