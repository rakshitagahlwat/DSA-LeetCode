class Solution {
    public int[] validSequence(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        
        int[] last = new int[n];
        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {
            while (j >= 0 && word1.charAt(j) != word2.charAt(i)) {
                j--;
            }
            last[i] = j;
            if (j >= 0) {
                j--;
            }
        }

        int[] result = new int[n];
        boolean changed = false;
        int ptr = 0; 

        for (int i = 0; i < n; i++) {
            boolean found = false;

            while (ptr < m) {
               
                if (word1.charAt(ptr) == word2.charAt(i)) {
                    result[i] = ptr;
                    ptr++;
                    found = true;
                    break;
                }

               
                if (!changed) {
                    if (i == n - 1 || last[i + 1] > ptr) {
                        result[i] = ptr;
                        changed = true;
                        ptr++;
                        found = true;
                        break;
                    }
                }

                ptr++;
            }

            if (!found) {
                return new int[0];
            }
        }

        return result;
    }
}