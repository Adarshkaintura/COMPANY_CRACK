import java.util.Arrays;

public class TripletCounter {
    public static long countTriplets(int n, long sum, long[] arr) {
        Arrays.sort(arr);
        long count = 0;

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;
            while (left < right) {
                if (arr[i] + arr[left] + arr[right] < sum) {
                    count += (right - left);
                    left++;
                } else {
                    right--;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        long[] arr = {5, 1, 3, 4, 7};
        int n = arr.length;
        long sum = 12;
        System.out.println(countTriplets(n, sum, arr)); 
    }
}
