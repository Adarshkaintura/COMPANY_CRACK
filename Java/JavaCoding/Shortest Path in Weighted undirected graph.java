import java.util.*;

public class ShortestPath {
    public static List<Integer> shortestPath(int n, int m, int[][] edges) {
        // Build adjacency list
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

        for (int[] it : edges) {
            int u = it[0], v = it[1], w = it[2];
            adj.get(u).add(new int[]{v, w});
            adj.get(v).add(new int[]{u, w});
        }

        // Dijkstra setup
        int[] dist = new int[n + 1];
        int[] parent = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        for (int i = 1; i <= n; i++) parent[i] = i;

        // TreeSet for (distance, node)
        TreeSet<int[]> set = new TreeSet<>((a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        dist[1] = 0;
        set.add(new int[]{0, 1});

        while (!set.isEmpty()) {
            int[] top = set.pollFirst();
            int d = top[0], u = top[1];

            for (int[] edge : adj.get(u)) {
                int v = edge[0], w = edge[1];
                int dis = d + w;

                if (dis < dist[v]) {
                    if (dist[v] != Integer.MAX_VALUE) {
                        set.remove(new int[]{dist[v], v}); // remove old entry
                    }
                    dist[v] = dis;
                    parent[v] = u;
                    set.add(new int[]{dis, v});
                }
            }
        }

        // If no path to n
        if (dist[n] == Integer.MAX_VALUE) {
            return Arrays.asList(-1);
        }

        // Reconstruct path
        List<Integer> path = new ArrayList<>();
        int node = n;
        while (parent[node] != node) {
            path.add(node);
            node = parent[node];
        }
        path.add(1);
        Collections.reverse(path);

        // Build result [weight, path...]
        List<Integer> result = new ArrayList<>();
        result.add(dist[n]);
        result.addAll(path);
        return result;
    }

    public static void main(String[] args) {
        int n = 5, m = 6;
        int[][] edges = {
            {1, 2, 2}, {2, 5, 5}, {2, 3, 4},
            {1, 4, 1}, {4, 3, 3}, {3, 5, 1}
        };

        List<Integer> ans = shortestPath(n, m, edges);
        for (int x : ans) System.out.print(x + " ");
        System.out.println();
    }
}

/*
 Time Complexity: O(E log V)
   - Each insert/remove in TreeSet is O(log V)
   - Each edge relaxed at most once
 Space Complexity: O(V + E)
*/
