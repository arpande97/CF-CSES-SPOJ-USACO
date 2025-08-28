#include<bits/stdc++.h>
using namespace std;
const int MAXVAL = 1001;
int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    vector<int> prime(MAXVAL);
    fill(prime.begin(), prime.end(), 1);
    prime[0] = prime[1] = 0;
    vector<int> primes;
    for (int i = 2; i < MAXVAL; i++) {
        if (prime[i] == 1) {
            primes.push_back(i);
            for (int j = i * i; j < MAXVAL; j += i) {
                prime[j] = 0;
            }
        }
    }
    int n;
    cin >> n;
    vector<int> guess;
    for (int p : primes) {
        int x = p;
        while (x <= n) {
            guess.push_back(x);
            x *= p;
        }
    }
    cout << guess.size() << '\n';
    for (int g : guess) {
        cout << g << " ";
    }
    cout << '\n';

}