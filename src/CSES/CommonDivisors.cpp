#include<bits/stdc++.h>
using namespace std;
const int MAX_VALUE = 1000001;
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n;
    cin >> n;

    vector<int> a(MAX_VALUE);
    for (int i = 0; i < n; i++) {
        int x;
        cin >> x;
        a[x]++;
    }
    for (int i = MAX_VALUE - 1; i >= 1; i--) {
        int curr = 0;
        for (int j = i; j < MAX_VALUE; j += i) {
            int value = a[j];
            curr += value;
        }
        if (curr >= 2) {
            cout << i << endl;
            return 0;
        }
    }
    return 0;


}