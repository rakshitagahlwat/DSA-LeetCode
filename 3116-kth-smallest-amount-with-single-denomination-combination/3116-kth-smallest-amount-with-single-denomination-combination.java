class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        
        // Binary search bounds
        long low = 1;
        long minCoin = coins[0];
        for (int coin : coins) {
            minCoin = Math.min(minCoin, coin);
        }
        long high = minCoin * k;
        long result = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            
            if (countAmounts(mid, coins, n) >= k) {
                result = mid;
                high = mid - 1; // Try to find a smaller valid amount
            } else {
                low = mid + 1; // Increase amount
            }
        }

        return result;
    }

    private long countAmounts(long x, int[] coins, int n) {
        long count = 0;
        int totalSubsets = 1 << n;

        for (int mask = 1; mask < totalSubsets; mask++) {
            long currentLcm = 1;
            int setBitsCount = 0;
            boolean overflow = false;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    setBitsCount++;
                    currentLcm = lcm(currentLcm, coins[i]);
                    if (currentLcm > x) { 
                        overflow = true;
                        break;
                    }
                }
            }

            if (overflow) continue;

            if (setBitsCount % 2 == 1) {
                count += x / currentLcm;
            } else {
                count -= x / currentLcm;
            }
        }

        return count;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        if (a == 0 || b == 0) return 0;
        return (a / gcd(a, b)) * b;
    }
}