public class KadaneAlgorithm {

    public static int maxSubarraySum(int[] arr) {
        int n = arr.length;
        int maxSoFar = arr[0];
        int currSum = arr[0];

        for (int i = 1; i < n; i++) {
            currSum = Math.max(arr[i], currSum + arr[i]);
            maxSoFar = Math.max(maxSoFar, currSum);
        }

        return maxSoFar;
    }

    public static void main(String[] args) {
        int[] arr = {-2, -3, 4, -1, -2, 1, 5, -3};
        int maxSum = maxSubarraySum(arr);
        System.out.println("Maximum Subarray Sum is: " + maxSum);
    }
}
