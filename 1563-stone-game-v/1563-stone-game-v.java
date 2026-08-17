class Solution {
    private Integer[][] memo;
    private int[] prefixSum;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        memo = new Integer[n][n];
        
        prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + stoneValue[i];
        }

        return solve(stoneValue, 0, n - 1);
    }

    private int solve(int[] stoneValue, int i, int j) {
        if (i == j) {
            return 0;
        }

        if (memo[i][j] != null) {
            return memo[i][j];
        }

        int maxScore = 0;

        for (int k = i; k < j; k++) {
            int leftSum = getSum(i, k);
            int rightSum = getSum(k + 1, j);

            if (leftSum < rightSum) {
                maxScore = Math.max(maxScore, leftSum + solve(stoneValue, i, k));
            } else if (leftSum > rightSum) {
                maxScore = Math.max(maxScore, rightSum + solve(stoneValue, k + 1, j));
            } else {
                int pickLeft = leftSum + solve(stoneValue, i, k);
                int pickRight = rightSum + solve(stoneValue, k + 1, j);
                maxScore = Math.max(maxScore, Math.max(pickLeft, pickRight));
            }
        }

        return memo[i][j] = maxScore;
    }

    private int getSum(int left, int right) {
        return prefixSum[right + 1] - prefixSum[left];
    }
}