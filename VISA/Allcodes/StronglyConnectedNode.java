import java.util.*;

public class StronglyConnectedNodes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();  // number of nodes
        int e = sc.nextInt();  // number of edges
        
        int k = 0; // maximum strongly connected nodes
        
        for (int i = 1; i <= n; i++) {
            int req = (n - i); // edges required for this node to stay connected
            
            if (e >= req) {
                k++;
                e -= req;
            } else {
                break;
            }
        }
        
        System.out.println(k);
        
        sc.close();
    }
}
