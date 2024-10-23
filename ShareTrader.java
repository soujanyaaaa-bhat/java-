class ShareTrader {
    // Static variable to store the maximum profit
    static int maxProfit;

    public static int findMaxProfit(int[] prices) {
        int[][][] dp = new int[prices.length + 2][2][3];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                for (int k = 0; k < dp[i][j].length; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        maxProfit = fun(prices, 0, 1, 2, dp);
        return maxProfit;
    }

    public static int fun(int[] arr, int i, int take, int count, int[][][] dp) {
        if (i >= arr.length || count == 0) {
            return 0;
        }
        if (dp[i][take][count] != -1) {
            return dp[i][take][count];
        }
        int taken = 0;
        int notTaken = 0;
        if (take == 1) {
            taken = -arr[i] + fun(arr, i + 1, 0, count, dp);
            notTaken = fun(arr, i + 1, 1, count, dp);
        } else {
            taken = arr[i] + fun(arr, i + 1, 1, count - 1, dp);
            notTaken = fun(arr, i + 1, 0, count, dp);
        }
        return dp[i][take][count] = Math.max(taken, notTaken);
    }

    // Driver code
    public static void main(String[] args) {
        int[] prices1 = {10, 22, 5, 75, 65, 80};
        System.out.println("Max Profit: " + findMaxProfit(prices1)); // Output: 87

        int[] prices2 = {2, 30, 15, 10, 8, 25, 80};
        System.out.println("Max Profit: " + findMaxProfit(prices2)); // Output: 100
    }
}
