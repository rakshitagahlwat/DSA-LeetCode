class Solution {

    class Node {
        int leftChar;
        int rightChar;

        int prefix;
        int suffix;
        int max;

        int length;

        Node(int c) {
            leftChar = c;
            rightChar = c;
            prefix = 1;
            suffix = 1;
            max = 1;
            length = 1;
        }

        Node() {
        }
    }

    Node[] tree;
    char[] s;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {

        this.s = s.toCharArray();

        int n = s.length();

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {

            int index = queryIndices[i];
            int ch = queryCharacters.charAt(i) - 'a';

            update(1, 0, n - 1, index, ch);

            ans[i] = tree[1].max;
        }

        return ans;
    }

    void build(int node, int start, int end) {

        if (start == end) {
            tree[node] = new Node(s[start] - 'a');
            return;
        }

        int mid = start + (end - start) / 2;

        build(node * 2, start, mid);
        build(node * 2 + 1, mid + 1, end);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    void update(int node, int start, int end, int index, int ch) {

        if (start == end) {
            tree[node] = new Node(ch);
            return;
        }

        int mid = start + (end - start) / 2;

        if (index <= mid) {
            update(node * 2, start, mid, index, ch);
        } else {
            update(node * 2 + 1, mid + 1, end, index, ch);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    Node merge(Node left, Node right) {

        Node result = new Node();

        result.length = left.length + right.length;

        result.leftChar = left.leftChar;
        result.rightChar = right.rightChar;

        result.prefix = left.prefix;
        result.suffix = right.suffix;

        result.max = Math.max(left.max, right.max);

        if (left.rightChar == right.leftChar) {

            // Entire left segment is same character
            if (left.prefix == left.length) {
                result.prefix = left.length + right.prefix;
            }

            if (right.suffix == right.length) {
                result.suffix = right.length + left.suffix;
            }

            result.max = Math.max(
                result.max,
                left.suffix + right.prefix
            );
        }

        return result;
    }
}