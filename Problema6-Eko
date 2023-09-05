#include<bits/stdc++.h>
#define fast_io ios_base::sync_with_stdio(false);cin.tie(0)
using namespace std;
typedef long long ll;
const int maxn = 1e6 + 5;
int a[maxn];
int n, m;
ll f(int h) {
    ll ans = 0;
    for (int i = 1; i <= n; i ++) {
        if (h <= a[i]) {
            ans += (a[i] - h);
        }
    }
    return ans;
}
int main(){
    fast_io;
    cin >> n >> m;
    int h_max = 1;
    for (int i = 1; i <= n; i ++) {
        cin >> a[i];
        h_max = max(h_max,a[i]);
    }
    int low = 0, high = h_max;
    while (low < high) {
        int mid = low + (high - low)/2;
        if (f(mid) > m) {
            low = mid + 1;
        }
        else high = mid;
    }
    if (f(low) < m) low --;
    cout << low << "\n";
    return 0;
}
