/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    HashMap<TreeNode, Integer> dp = new HashMap<>();

    public int rob(TreeNode root) {
        
        if(root == null) return 0;

        if(dp.containsKey(root)){
            return dp.get(root);
        }

        int robNode = root.val;

        if(root.left != null){
            robNode += rob(root.left.left);
            robNode += rob(root.left.right);

        }
        if(root.right != null){
            robNode += rob(root.right.left);
            robNode += rob(root.right.right);

        }
        int skipCurrentNode = rob(root.left) + rob(root.right);
        int answer = Math.max(robNode, skipCurrentNode);
        dp.put(root, answer);
        return answer;        
        }
}