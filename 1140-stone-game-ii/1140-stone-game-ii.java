class Solution {

    int n;
    int[][] dp;
    int[] suffix;

    public int stoneGameII(int[] piles) {

        n = piles.length;

        dp = new int[n][n + 1];

        suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        return solve(piles, 0, 1);
    }

    private int solve(int[] piles, int i, int M) {

        if (i == n) {
            return 0;
        }

        
        if (2 * M >= n - i) {
            return suffix[i];
        }

       
        if (dp[i][M] != 0) {
            return dp[i][M];
        }

        int best = 0;

    
        for (int X = 1; X <= 2 * M && i + X <= n; X++) {

            int newM = Math.max(M, X);

            int opponent = solve(piles, i + X, newM);

            int current = suffix[i] - opponent;

            best = Math.max(best, current);
        }

        dp[i][M] = best;

        return best;
    }
}