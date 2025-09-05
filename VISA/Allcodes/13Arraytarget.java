import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, N;
        System.out.print("Enter the size of array: ");
        n = sc.nextInt();
        System.out.print("Enter N: ");
        N = sc.nextInt();
        System.out.print("Enter the elements of the array: ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Map<Integer, Integer> freq = new HashMap<>();
        long count = 0;
        for (int i = 0; i < n; i++) {
            int needed = arr[i] - N;
            if (freq.containsKey(needed)) {
                count += freq.get(needed);
            }
            freq.put(arr[i], freq.getOrDefault(arr[i], 0) + 1);
        }
        System.out.println("Ans is: " + count);
        sc.close();
    }
}
