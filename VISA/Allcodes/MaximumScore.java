/*
. Given an array arr of n integers, start with a score of 0. In one
operation, one element of the array is chosen, and its value is added to the score. The element
is replaced by the integer ceiling of one-third of its value. For example, if the element is 10, then
10 is added to the score and the element is replaced by ceil(10/3)=4. The task is to find the
maximum possible score after k operations.
 */

import java.util.*;

public class MaxScore {
    public static long maxScore(int[] arr, int k) {
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int x : arr) pq.add((long)x);

        long score = 0;
        while (k-- > 0) {
            long top = pq.poll();
            score += top;
            long newVal = (top + 2) / 3; // ceil(top/3)
            pq.add(newVal);
        }
        return score;
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 7};
        int k = 4;
        System.out.println("Maximum Score: " + maxScore(arr, k));
    }
}
