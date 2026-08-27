class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < target.length(); i++) {

            int targetChar = target.charAt(i) - 'a';

            if (freq[targetChar] > 0) {

                ans.append(target.charAt(i));
                freq[targetChar]--;

            } else {

                for (int c = targetChar + 1; c < 26; c++) {

                    if (freq[c] > 0) {

                        ans.append((char) (c + 'a'));
                        freq[c]--;

                        for (int j = 0; j < 26; j++) {
                            while (freq[j] > 0) {
                                ans.append((char) (j + 'a'));
                                freq[j]--;
                            }
                        }

                        return ans.toString();
                    }
                }

                break;
            }
        }

        for (int i = ans.length() - 1; i >= 0; i--) {

            int oldChar = ans.charAt(i) - 'a';

            freq[oldChar]++;

            for (int c = oldChar + 1; c < 26; c++) {

                if (freq[c] > 0) {

                    StringBuilder result = new StringBuilder();

                    result.append(ans.substring(0, i));

                    result.append((char) (c + 'a'));
                    freq[c]--;

                    for (int j = 0; j < 26; j++) {
                        while (freq[j] > 0) {
                            result.append((char) (j + 'a'));
                            freq[j]--;
                        }
                    }

                    return result.toString();
                }
            }
        }

        return "";
    }
}