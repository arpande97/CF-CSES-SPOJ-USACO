#include<bits/stdc++.h>
using namespace std;
#define ll long long

void move(int x, int y, vector<int>& a, vector<int>& b, vector<int>& pos, vector<pair<int, int>>& moves) {
    if (x == y)
        return;
    moves.push_back({x, y});
    swap(a[x], a[y]);
    swap(b[x], b[y]);
    swap(pos[a[x]], pos[a[y]]);
}
void solve() {
    int n;
    cin >> n;
    vector<int> a(n + 1), b(n + 1);
    vector<int> pos(n + 1);
    vector<pair<int, int>> moves;
    for (int i = 1; i <= n; i++) {
        cin >> a[i];
        pos[a[i]] = i;
    }
    for (int i = 1; i <= n; i++) {
        cin >> b[i];
    }
    int mid = 0;
    for (int i = 1; i <= n; i++) {
        if (a[i] == b[i]) {
            if (n % 2 == 0 || mid) {
                cout << "-1\n";
                return;
            }
            mid = i;
        }
        else if (a[i] != b[pos[b[i]]]) {
            cout << "-1\n";
            return;
        }
    }
    if (n & 1) {
        move(mid, (n + 1)/2, a, b, pos, moves);
    }
    for (int i = 1; i <= n / 2; i++) {
        move(pos[b[i]], n - i + 1, a, b, pos, moves);
    }
    cout << moves.size() << endl;
    for (auto [x, y] : moves) {
        cout << x << " " << y << endl;
    }
    

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