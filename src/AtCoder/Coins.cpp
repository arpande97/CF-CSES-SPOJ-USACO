#include<bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    int n; 
    cin >> n;
    vector<double> p(n);
    for (auto &it : p) {
        cin >> it;
    }
    vector<double> next(n + 1);
    for (int i = n/2 + 1; i <= n; i++) {
        next[i] = 1.0;
    }
    for (int i = n - 1; i >= 0; i--) {
        vector<double> curr(n + 1);
        for (int heads = n - 1; heads >= 0; heads--) {
            curr[heads] = p[i] * next[heads + 1] + (1 - p[i]) * next[heads];
        }
        next = curr;
    }
    cout << setprecision(10) << next[0] << endl;
}