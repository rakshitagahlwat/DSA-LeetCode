class Solution {
    private static final int[] COUNT_2 = {0, 0, 1, 0, 2, 0, 1, 0, 3, 0};
    private static final int[] COUNT_3 = {0, 0, 0, 1, 0, 0, 1, 0, 0, 2};
    private static final int[] COUNT_5 = {0, 0, 0, 0, 0, 1, 0, 0, 0, 0};
    private static final int[] COUNT_7 = {0, 0, 0, 0, 0, 0, 0, 1, 0, 0};

    public String smallestNumber(String num, long t) {
        long c2 = 0, c3 = 0, c5 = 0, c7 = 0;

        // Factorize t into prime factors 2, 3, 5, 7
        while (t % 2 == 0) { c2++; t /= 2; }
        while (t % 3 == 0) { c3++; t /= 3; }
        while (t % 5 == 0) { c5++; t /= 5; }
        while (t % 7 == 0) { c7++; t /= 7; }

        // If t contains prime factors > 7, it's impossible using digits 1-9
        if (t > 1) return "-1";

        int n = num.length();

        // Find index of the first '0' in num if present
        int firstZero = n;
        for (int i = 0; i < n; i++) {
            if (num.charAt(i) == '0') {
                firstZero = i;
                break;
            }
        }

        // Precompute cumulative prime factor counts for prefixes
        long[] pref2 = new long[n + 1];
        long[] pref3 = new long[n + 1];
        long[] pref5 = new long[n + 1];
        long[] pref7 = new long[n + 1];

        for (int i = 0; i < n; i++) {
            int digit = num.charAt(i) - '0';
            pref2[i + 1] = pref2[i] + COUNT_2[digit];
            pref3[i + 1] = pref3[i] + COUNT_3[digit];
            pref5[i + 1] = pref5[i] + COUNT_5[digit];
            pref7[i + 1] = pref7[i] + COUNT_7[digit];
        }

        // Try keeping prefix of length i and incrementing digit at index i
        for (int i = n; i >= 0; i--) {
            if (i > firstZero) continue; // Prefix cannot extend past a '0'

            long rem2 = c2 - pref2[i];
            long rem3 = c3 - pref3[i];
            long rem5 = c5 - pref5[i];
            long rem7 = c7 - pref7[i];

            // Case: num itself is already valid
            if (i == n) {
                if (firstZero == n && getSum(getFactorCount(rem2, rem3, rem5, rem7)) == 0) {
                    return num;
                }
                continue;
            }

            int startDigit = (num.charAt(i) - '0') + 1;
            for (int dNext = startDigit; dNext <= 9; dNext++) {
                long cur2 = rem2 - COUNT_2[dNext];
                long cur3 = rem3 - COUNT_3[dNext];
                long cur5 = rem5 - COUNT_5[dNext];
                long cur7 = rem7 - COUNT_7[dNext];

                int spaceAfter = n - 1 - i;
                int[] factorCount = getFactorCount(cur2, cur3, cur5, cur7);
                if (getSum(factorCount) <= spaceAfter) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i);
                    sb.append(dNext);
                    sb.append(buildSuffix(spaceAfter, factorCount));
                    return sb.toString();
                }
            }
        }

        // If no candidate exists at length n, increase total string length
        int[] factorCount = getFactorCount(c2, c3, c5, c7);
        int targetLen = Math.max(n + 1, getSum(factorCount));
        return buildSuffix(targetLen, factorCount);
    }

    private int[] getFactorCount(long c2, long c3, long c5, long c7) {
        c2 = Math.max(0, c2);
        c3 = Math.max(0, c3);
        c5 = Math.max(0, c5);
        c7 = Math.max(0, c7);

        int[] count = new int[10];
        count[8] = (int) (c2 / 3);
        long rem2 = c2 % 3;

        count[9] = (int) (c3 / 2);
        long rem3 = c3 % 2;

        count[5] = (int) c5;
        count[7] = (int) c7;

        if (rem2 == 0 && rem3 == 1) {
            count[3]++;
        } else if (rem2 == 1 && rem3 == 0) {
            count[2]++;
        } else if (rem2 == 1 && rem3 == 1) {
            count[6]++;
        } else if (rem2 == 2 && rem3 == 0) {
            count[4]++;
        } else if (rem2 == 2 && rem3 == 1) {
            count[2]++;
            count[6]++;
        }

        return count;
    }

    private int getSum(int[] count) {
        int sum = 0;
        for (int c : count) {
            sum += c;
        }
        return sum;
    }

    private String buildSuffix(int targetLen, int[] count) {
        int totalDigits = getSum(count);
        int ones = targetLen - totalDigits;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ones; i++) {
            sb.append('1');
        }
        for (int d = 2; d <= 9; d++) {
            for (int i = 0; i < count[d]; i++) {
                sb.append((char) ('0' + d));
            }
        }
        return sb.toString();
    }
}