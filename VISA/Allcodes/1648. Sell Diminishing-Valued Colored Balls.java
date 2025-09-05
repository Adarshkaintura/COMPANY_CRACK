class Solution {
    public int maxProfit(int[] inventory, int orders) {
        final int MOD = 1000000007;
        Arrays.sort(inventory);
        int n = inventory.length;
        long profit = 0;
        int i = n - 1;

        while (orders > 0) {
            int curr = inventory[i];
            int prev = (i > 0 ? inventory[i - 1] : 0);
            int count = n - i; // number of balls at this level

            long totalBalls = (long) (curr - prev) * count;

            if (orders >= totalBalls) {
                // Sell all from curr down to prev
                profit += sumFromTo(prev + 1, curr) * count;
                orders -= totalBalls;
            } else {
                // Only partial
                int fullRows = orders / count;
                int remainder = orders % count;

                profit += sumFromTo(curr - fullRows + 1, curr) * count;
                profit += (long) remainder * (curr - fullRows);
                orders = 0;
            }
            profit %= MOD;
            i--;
        }

        return (int) (profit % MOD);
    }

    private long sumFromTo(int low, int high) {
        // Sum of integers from low to high
        return (long) (high - low + 1) * (low + high) / 2;
    }
}
