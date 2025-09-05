// hackerrank question
import java.util.*;

public class Main {

    public static long solve(int n, int[][] operations) {
        int k = operations.length;
        long sum = 0;
        for (int i = 0; i < k; i++) {
            long l = operations[i][0];
            long h = operations[i][1];
            long val = operations[i][2];
            sum += (h - l + 1) * val;
        }
        return sum / n;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] operations = new int[m][3];
        for (int i = 0; i < m; i++) {
            operations[i][0] = sc.nextInt();
            operations[i][1] = sc.nextInt();
            operations[i][2] = sc.nextInt();
        }

        System.out.println(solve(n, operations));
        sc.close();
    }
}
