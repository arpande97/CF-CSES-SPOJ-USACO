#include<bits/stdc++.h>
using namespace std;
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int a, b;
    cin >> a >> b;
    if (a < b) {
        cout << 0 << endl;
        return 0;
    }
    if (a == b) {
        cout << "infinity" << endl;
        return 0;
    }
    //all factors of a - b greater than b
    int n = a - b, limit = sqrt(n), res = 0;
    for (int i = 1; i <= limit; i++) {
        if ((a - b) % i == 0) {
            if (i > b)
                res++;
            if ((a - b)/i != i) {
                if ((a - b)/i > b)
                    res++;
            }
        }
    }
    cout << res << endl;
    return 0;

}