#include<bits/stdc++.h>
using namespace std;
#define ll long long
const ll MOD = (ll)1e9 + 7;
int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    int n;
    cin >> n;
    int d = 1, a = 0;
    //0 is the top vertex, 1 is the bottom one
    for (int steps = n - 1; steps >= 0; steps--) {
        int curr_d = 0, curr_a = 0;
        curr_a = ((2ll * a) % MOD + d) % MOD;
        curr_d = (3ll * a) % MOD;
        d = curr_d;
        a = curr_a;
    }
    cout << d << endl;

}