class Solution {

    public int maxJump(int[] stones) {

        int n = stones.length;

        int[] dp = new int[n];

        dp[0] = 0;
        dp[1] = stones[1] - stones[0];

        for (int i = 2; i < n; i++) {

            int jump = stones[i] - stones[i - 2];

            dp[i] = Math.max(dp[i - 1], jump);
        }

        return dp[n - 1];
    }
}
