import java.util.Scanner;

public class MinDirectionString {
    public static int solve(String s) {
        int l = 0, r = 0, u = 0, d = 0;
        for (char ch : s.toCharArray()) {
            if (ch == 'L') l++;
            if (ch == 'R') r++;
            if (ch == 'U') u++;
            if (ch == 'D') d++;
        }
        return Math.abs(l - r) + Math.abs(u - d);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println("minimum length is: " + solve(s));
        sc.close();
    }
}
