//it is just simply topological sort but with bfs
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

void findIndegree(vector<vector<int>>& adjlist, vector<int>& indegree) {
    for (int i = 0; i < adjlist.size(); i++) {
        for (auto it : adjlist[i]) {
            indegree[it]++;
        }
    }
}

void KhansAlgo(vector<vector<int>>& adjlist, vector<int> indegree, queue<int>& q) {

    for (int i = 0; i < indegree.size(); i++) {
        if (indegree[i] == 0) {
            q.push(i);
        }
    }

    vector<int> ans;
    while (!q.empty()) {
        int u = q.front();
        q.pop();  

        ans.push_back(u);

        for (auto it : adjlist[u]) {
            indegree[it]--;
            if (indegree[it] == 0) {
                q.push(it);
            }
        }
    }

    cout << "Topological Order: ";
    for (auto it : ans) cout << it << " ";
    cout << endl;
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

    vector<int> indegree(u, 0);
    findIndegree(adjlist, indegree);

    cout << "The indegree of each node is: ";
    for (auto it : indegree) cout << it << " ";
    cout << endl;

    queue<int> q;
    KhansAlgo(adjlist, indegree, q);

    return 0;
}
 