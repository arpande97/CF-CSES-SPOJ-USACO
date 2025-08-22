#include<bits/stdc++.h>
using namespace std;
#define ll long long
vector<ll> cost;
void solve() {
    ll n, k;
    cin >> n >> k;
    ll min_deals = 0;
    vector<int> deals;
    while (n) {
        min_deals += (n % 3);
        //largest deals will be at the end;
        deals.push_back(n % 3);
        n /= 3;
    }
    if (min_deals > k) {
        cout << -1 << endl;
        return;
    }
    ll c = 0;
    k -= min_deals;
    k /= 2;
    //suppose you have k ops left after above
    //how many ops of moving can you do-> k/2
    //f.e. for 5 left ops -> you can do 5/2 = 2 moving ops
    //inside the loop treat each k as 1 op of removing larger deal and adding 3 smaller deals
    for (int i = deals.size() - 1; i >= 1; i--) {
        if (deals[i] <= k) {
            deals[i - 1] += 3 * deals[i];
            k -= deals[i];
            deals[i] = 0;
        }
        else {
            deals[i - 1] += k * 3;
            deals[i] -= k;
            break;
        }

    }
    for (int i = 0; i < deals.size(); i++) {
        c += cost[i] * deals[i];
    }

    
    cout << c << endl;
    
    
    //you want no more than k deals
    //you want more deals of less 
    //deal A buys you 1 watermelon at cost 3
    //deal B buys you 3 watermelon at cost 10
    //deal C buys you 9 watermelons at cost 33
    //deal D buys you 27 watermelons at cost 108 -> cost is inc per watermelon
    //so it's better to use smaller deals
    //but with smaller deals, you need to make more deals to get to the n
    //this might take you over k deals
    //when will the answer be -1?
    //in c1_2132, it doesn't make sense to make 3 or more deals of the same deal
    //if you make 3 txs of some deal A, you can instead make 1 tx of next deal
}
int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    int t;
    cin >> t;

    ll c = 3;
    ll pow = 1;
    for (int i = 1; i <= 19; i++) {
        cost.push_back(c);
        c = 3 * c + pow;
        pow *= 3;
    }
    while (t--) {
        solve();
    }
}