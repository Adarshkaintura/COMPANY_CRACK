//tle
import java.util.*;

public class Main {
    static int maxi = 0;

    public static void solve(int[] arr, List<Integer> temp, int i, int n, int prev) {
        if (i >= n) {
            maxi = Math.max(maxi, temp.size());
            return;
        }

        // Choice 1: take arr[i] if it is greater than prev
        if (arr[i] > prev) {
            temp.add(arr[i]);
            solve(arr, temp, i + 1, n, arr[i]);
            temp.remove(temp.size() - 1); // backtrack
        }

        // Choice 2: skip arr[i]
        solve(arr, temp, i + 1, n, prev);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        solve(arr, new ArrayList<>(), 0, n, Integer.MIN_VALUE);

        System.out.println(maxi);
    }
}

//opmtimized 
import java.util.*;

class Solution {
    Integer[][] dp;

    public int solve(int[] nums, int i, int prevIndex) {
        if (i == nums.length) return 0;

        if (dp[i][prevIndex + 1] != null)
            return dp[i][prevIndex + 1];

        // Option 1: Skip current element
        int notTake = solve(nums, i + 1, prevIndex);

        // Option 2: Take current element (if valid)
        int take = 0;
        if (prevIndex == -1 || nums[i] > nums[prevIndex]) {
            take = 1 + solve(nums, i + 1, i);
        }

        return dp[i][prevIndex + 1] = Math.max(take, notTake);
    }

    public int lengthOfLIS(int[] nums) {
        dp = new Integer[nums.length][nums.length + 1];
        return solve(nums, 0, -1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();

        Solution obj = new Solution();
        System.out.println(obj.lengthOfLIS(nums));
    }
}
