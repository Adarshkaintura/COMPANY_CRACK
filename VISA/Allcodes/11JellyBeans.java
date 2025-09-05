/* 11. Given a string consisting of letters that indicate directions i.e. ‘L’ for left, ‘R’ for right, ‘U’
for, up and ‘D’ for down, find the minimum length of string that can be obtained after
deleting some characters of the string so that we reach the same destination as the
original string.*/
import java.util.*;

public class BeansInJars {

    // Function to add beans in the given range
    static void solve(int[] jars, int l, int r, int val) {
        for (int i = l; i <= r; i++) {
            jars[i] += val;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of jars and initial beans
        int n = sc.nextInt();
        int t = sc.nextInt();

        int[] jars = new int[n];
        Arrays.fill(jars, t);

        // Number of operations
        int m = sc.nextInt();

        // Perform each operation
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt(); // left index (0-based)
            int b = sc.nextInt(); // right index (0-based)
            int j = sc.nextInt(); // beans to add
            solve(jars, a, b, j);
        }

        // Output final beans in each jar
        for (int beans : jars) {
            System.out.print(beans + " ");
        }
        System.out.println();
    }
}
