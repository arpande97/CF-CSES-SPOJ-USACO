#include<bits/stdc++.h>
using namespace std;
void dfs(int x, int y, vector<vector<int>>& seen, vector<string>& grid, vector<vector<int>>& dist) {
    seen[x][y] = 1;
    int dx = grid[x][y] == 'D' ? 1 : grid[x][y] == 'U' ? -1 : 0;
    int dy = grid[x][y] == 'R' ? 1 : grid[x][y] == 'L' ? -1 : 0;
    int nx = x + dx, ny = y + dy;
    int n = grid.size(), m = grid[0].size();
    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
        dist[x][y] = 1;
        return;
    }
    else if (grid[nx][ny] == '?') {
        dist[x][y] = 0;
        return;
    }
    else if (seen[nx][ny] == 0) {
        dfs(nx, ny, seen, grid, dist);
    }
    dist[x][y] += dist[nx][ny];
}
void solve() {
    int n, m;
    cin >> n >> m;
    vector<string> grid(n);
    for (int i = 0; i < n; i++) {
        cin >> grid[i];
    }
    vector<vector<int>> dist(n, vector<int>(m, 0));
    vector<vector<int>> seen(n, vector<int>(m, 0));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (grid[i][j] != '?' && seen[i][j] == 0) {
                dfs(i, j, seen, grid, dist);
            }
        }
    }
    int traps = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (grid[i][j] != '?') {
                if (dist[i][j] == 0)
                    traps++;
            }
            else {
                bool ok1 = j - 1 >= 0 && dist[i][j - 1] == 0;
                bool ok2 = j + 1 < m && dist[i][j + 1] == 0;
                bool ok3 = i - 1 >= 0 && dist[i - 1][j] == 0;
                bool ok4 = i + 1 < n && dist[i + 1][j] == 0;
                if (ok1 || ok2 || ok3 || ok4)
                    traps++;
            }
        }
    }
    cout << traps << endl;
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