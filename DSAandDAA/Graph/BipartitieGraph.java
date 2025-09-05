import java.util.*;

class Solution {
    public boolean isBipartite(int[][] graph) {
        int u = graph.length;

        // Build adjacency list (optional, since graph[][] is already adjacency list)
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < u; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < u; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                adj.get(i).add(graph[i][j]);
            }
        }

        int[] color = new int[u];
        Arrays.fill(color, -1);

        for (int i = 0; i < u; i++) {
            if (color[i] == -1) {
                if (!isBipar(color, adj, i)) {
                    return false;   
                }
            }
        }
        return true;
    }

    public boolean isBipar(int[] color, List<List<Integer>> adj, int start) {
        color[start] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            int node = q.poll();
            for (int nei : adj.get(node)) {
                if (color[nei] == -1) {
                    color[nei] = 1 - color[node]; 
                    q.add(nei);
                } else if (color[nei] == color[node]) {
                    return false; // conflict
                }
            }
        }
        return true;
    }
}
