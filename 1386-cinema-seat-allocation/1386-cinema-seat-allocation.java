import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            if (col >= 2 && col <= 9) {
                int mask = map.getOrDefault(row, 0);

                mask |= (1 << col);

                map.put(row, mask);
            }
        }

        int answer = (n - map.size()) * 2;

        for (int mask : map.values()) {

            boolean left = (mask & (1 << 2)) == 0 &&
                           (mask & (1 << 3)) == 0 &&
                           (mask & (1 << 4)) == 0 &&
                           (mask & (1 << 5)) == 0;

            boolean middle = (mask & (1 << 4)) == 0 &&
                             (mask & (1 << 5)) == 0 &&
                             (mask & (1 << 6)) == 0 &&
                             (mask & (1 << 7)) == 0;

            boolean right = (mask & (1 << 6)) == 0 &&
                            (mask & (1 << 7)) == 0 &&
                            (mask & (1 << 8)) == 0 &&
                            (mask & (1 << 9)) == 0;

            if (left && right) {
                answer += 2;
            } 
            else if (left || middle || right) {
                answer += 1;
            }
        }

        return answer;
    }
}