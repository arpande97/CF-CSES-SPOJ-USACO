#include<bits/stdc++.h>
using namespace std;
#define ll long long
const ll MOD = (ll)1e9 + 7;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int n, k;
    cin >> n >> k;
    if (k == 1) {
        cout << n << endl;
        return 0;
    }
    /*
    - originally I was running the dp on the n numbers
    - fix is to run the dp on the k spots to fill as the state and the last used number
    */
    vector<ll> next(n + 1, 1);
    for (int i = k - 1; i >= 0; i--) {
        vector<ll> curr(n + 1);
        for (int j = n; j >= 1; j--) {
            ll sum = 0;
            //finding multiples will be O(log n)
            for (int inc = j; inc <= n; inc += j) {
                sum = (sum + next[inc]) % MOD;
            }
            curr[j] = sum;
        }
        next = curr;

    }
    
    cout << next[1] << endl;
}