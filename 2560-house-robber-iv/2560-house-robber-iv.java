class Solution {

    public int minCapability(int[] nums, int k) {

        int low = nums[0];
        int high = nums[0];

        // Find minimum and maximum values
        for (int num : nums) {
            low = Math.min(low, num);
            high = Math.max(high, num);
        }

        // Binary search
        while (low < high) {

            int mid = low + (high - low) / 2;

            if (canRob(nums, k, mid)) {

                high = mid;

            } else {

                low = mid + 1;
            }
        }

        return low;
    }

    private boolean canRob(int[] nums, int k, int capability) {

        int count = 0;
        int i = 0;

        while (i < nums.length) {

            if (nums[i] <= capability) {

                count++;

                i += 2;

            } else {

                i++;
            }

            if (count >= k) {
                return true;
            }
        }

        return false;
    }
}