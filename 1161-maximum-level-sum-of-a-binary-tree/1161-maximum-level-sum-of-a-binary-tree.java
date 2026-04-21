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
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        int res = 0;
        if(root==null) return res;
        q.add(root);
        int ans = Integer.MIN_VALUE;
        int count = 0;
        while(!q.isEmpty()){
            count++;
            int n = q.size();
            int sum = 0;
            for(int i=0; i<n; i++){
                TreeNode peek = q.poll();
                sum = sum + peek.val;
                if(peek.left!=null) q.add(peek.left);
                if(peek.right!=null) q.add(peek.right);
            }
            if(ans<sum){
                res = count;
                ans = sum;
            }
        }
        return res;
    }
}