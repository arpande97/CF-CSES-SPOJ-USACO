#include<bits/stdc++.h>
using namespace std;
#define ll long long
int N = 10000001;
vector<int> primes(N), prefix(N);
void sieve() {
    primes[0] = primes[1] = 1;
    for (int i = 2; (long long)i * i <= N; i++) {
        if (primes[i] == 0) {
            for (int j = i * i; j <= N; j += i) {
                primes[j] = 1;
            }
        }
    }
}
void solve() {
    sieve();
    vector<int> mark(N + 1);
    for (int i = 1; 1ll * i * i < N; i++) {
        for (int j = 1; 1ll * i * i + 1ll * j * j * j * j < N; j++) {
            ll val = (1ll * i * i) + (1ll * j * j * j * j);
            if (primes[val] == 0 && mark[val] == 0) {
                mark[val] = 1;
            }
        }
    }
    for (int i = 2; i < N; i++) {
        prefix[i] = mark[i] + prefix[i - 1];
    }
    
    

}
int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int t;
    cin >> t;
    solve();
    while (t--) {
        int x;
        cin >> x;
        cout << prefix[x] << endl;
    }
}