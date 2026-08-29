
import java.util.*;

class Solution {

    public List<List<Integer>> permuteUnique(int[] nums) {

        Set<List<Integer>> set = new HashSet<>();

        List<Integer> current = new ArrayList<>();

        boolean[] used = new boolean[nums.length];

        backtrack(nums, used, current, set);

        return new ArrayList<>(set);
    }

    private void backtrack(
            int[] nums,
            boolean[] used,
            List<Integer> current,
            Set<List<Integer>> set) {

        if (current.size() == nums.length) {
            set.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (used[i]) {
                continue;
            }

            used[i] = true;
            current.add(nums[i]);

            backtrack(nums, used, current, set);

            current.remove(current.size() - 1);
            used[i] = false;
        }
    }
}

