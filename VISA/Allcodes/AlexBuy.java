/*
Alex is shopping at Ozone Galleria Mall. There is a dedicated cubicle for a type of product at
the shopping center. All the products sold at the ith cubicle are priced the same, denoted by
prices[i]. The cubicles are arranged such that the price of the products sold at each cubicle are
in non-decreasing order. Several queries would be asked about the problem. In each query, the
cubicle number Alex is initially standing at, and the amount of money Alex has is given, and Alex
can travel in the right direction visiting from the current cubicle to the last cubicle. Alex may buy
at most one item from any cubicle visited, but the total cost of the purchase must not exceed
the amount Alex has. Report the maximum number of products that can be purchased for each
query. More formally, given an array of n integers, prices, where prices[i] denotes the price of the
product sold in the ith cubicle. The array prices are in non-decreasing order (i.e., the
price[i]≤price[i+1]), and q queries need to be processed. For each query, two integers are given:
pos: Alex’s initial position
amount: the amount of money Alex has
Alex needs to visit each cubicle from number pos to n, purchasing at most one product from
each cubicle. For each query, the goal is to find the maximum number of products that Alex can
buy.
*/
import java.util.*;

public class OzoneMall {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), q = sc.nextInt();

        long[] prices = new long[n+1];
        long[] prefix = new long[n+1];

        for (int i = 1; i <= n; i++) {
            prices[i] = sc.nextLong();
            prefix[i] = prefix[i-1] + prices[i];
        }

        while (q-- > 0) {
            int pos = sc.nextInt();
            long amount = sc.nextLong();

            int low = pos, high = n, ans = pos - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                long cost = prefix[mid] - prefix[pos-1];
                if (cost <= amount) {
                    ans = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            System.out.println(ans - pos + 1);
        }
    }
}
