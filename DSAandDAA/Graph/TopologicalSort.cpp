//it is just dfs with a->b so a comes first before b that is its sorting way
#include <iostream>
#include <vector>
#include <stack>
using namespace std;

void TopologicalSort(vector<vector<int>>& adjlist, int u, stack<int>& st, vector<int>& visited) {
    visited[u] = 1;
    for (auto it : adjlist[u]) {
        if (!visited[it]) {
            TopologicalSort(adjlist, it, st, visited);
        }
    }
    st.push(u); 
}

int main() {
    int u, v;
    cout << "Enter number of nodes (u) and edges (v): ";
    cin >> u >> v;

    vector<vector<int>> adjlist(u);

    cout << "Enter " << v << " edges (from to):" << endl;
    for (int i = 0; i < v; i++) {
        int from, to;
        cin >> from >> to;
        adjlist[from].push_back(to);
    }

    stack<int> st;
    vector<int> visited(u, 0);

    for (int i = 0; i < u; i++) {
        if (!visited[i])
            TopologicalSort(adjlist, i, st, visited);
    }

    cout << "Topological Sort: ";
    while (!st.empty()) {
        cout << st.top() << " ";
        st.pop();
    }
    cout << endl;

    return 0;
}
