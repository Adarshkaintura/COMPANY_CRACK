import java.util.*;

public class DetectCycleDsu {
    public int detectCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] parent = new int[V];
        int[] rank = new int[V];

        // initialize DSU
        for (int i = 0; i < V; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

       

        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                if (u < v) { // process each edge once
                    if (!union(u, v, parent, rank)) {
                        // union returns false if u and v already connected → cycle
                        return 1;
                    }
                }
            }
        }

        return 0; // no cycle
    }

    private int find(int x, int[] parent) {
        if (parent[x] != x) {
            parent[x] = find(parent[x], parent); // path compression
        }
        return parent[x];
    }

    private boolean union(int x, int y, int[] parent, int[] rank) {
        int px = find(x, parent);
        int py = find(y, parent);

        if (px == py) return false; // cycle detected

        if (rank[px] < rank[py]) {
            parent[px] = py;
        } else if (rank[px] > rank[py]) {
            parent[py] = px;
        } else {
            parent[py] = px;
            rank[px]++;
        }
        return true;
    }
public static void main(String[] args){
    System.out.println("hello");
}
}
