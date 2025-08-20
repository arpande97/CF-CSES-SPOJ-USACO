#include<bits/stdc++.h>
using namespace std;
int MAX;
vector<int> allowed, active;
void reset(int num) {
    if (num % 2 == 0) {
        allowed[2] = 0;
    }
    while (num % 2 == 0) {
        num /= 2;
    }
    for (int d = 3; (long long)d * d < MAX; d += 2) {
        if (num % d == 0) 
            allowed[d] = 0;
        while (num % d == 0) {
            num /= d;
        }
    }
    if (num > 1)
        allowed[num] = 0;
    
}
int valid(int num) {
    vector<int> factors;
    int x = num;
    if (num % 2 == 0) {
        if (allowed[2] > 0)
            return allowed[2];
        factors.push_back(2);
    }
    while (num % 2 == 0) {
        num /= 2;
    }
    for (int d = 3; (long long)d * d < MAX; d += 2) {
        if (num % d == 0) {
            if (allowed[d] > 0)
                return allowed[d];
            factors.push_back(d);
        }
        while (num % d == 0) {
            num /= d;
        }
    }
    if (num > 1) {
        if (allowed[num] > 0)
            return allowed[num];
        factors.push_back(num);
    }
    for (int d : factors) {
        allowed[d] = x;
    }
    return 0;
}
int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    int n, m;
    cin >> n >> m;
    MAX = n;
    active.resize(MAX + 1);
    allowed.resize(MAX + 1);
    //0 means alllowed, anything else means some number which is active has a common prime factor
    vector<pair<char, int>> ops;
    
    for (int i = 0; i < m; i++) {
        char op;
        int collider;
        cin >> op >> collider;
        ops.push_back({op, collider});
    }
    for (int i = 0; i < m; i++) {
        char op = ops[i].first;
        int collider = ops[i].second;
        
        if (op == '+') {
            if (active[collider] == 1)
                cout << "Already on" << endl;
            else {
                int conf = valid(collider);
                if (conf == 0) {
                    cout << "Success" << endl;
                    active[collider] = 1;
                }
                else
                    cout << "Conflict with " << conf << endl;
            }
        }
        else {
            if (active[collider] == 0)
                cout << "Already off" << endl;
            else {
                active[collider] = 0;
                reset(collider);
                cout << "Success" << endl;
            }
        }

    }

    //sucess: if i is co-prime with all the already active numbers;
    //already on
    //conflict with j: j is not coprime with i

    //success:
    //already off
    //m logn

    //there is an allowed array and an active array
    //first check if active: if yes, already on
    //first check on allowed, if allowed set the active to true and then change all factors and multiples 
    //in allowed to false (this will be O(log n))

    //tc: O(m. sqrt(n))


}