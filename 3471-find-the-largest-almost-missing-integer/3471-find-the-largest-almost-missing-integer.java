class Solution {
    public int largestInteger(int[] nums, int k) {

        int[] count = new int[51];

        int n = nums.length;

        for (int i = 0; i <= n - k; i++) {

            boolean[] seen = new boolean[51];

            for (int j = i; j < i + k; j++) {

                int num = nums[j];

                if (!seen[num]) {
                    count[num]++;
                    seen[num] = true;
                }
            }
        }

        for (int num = 50; num >= 0; num--) {

            if (count[num] == 1) {
                return num;
            }
        }

        return -1;
    }
}