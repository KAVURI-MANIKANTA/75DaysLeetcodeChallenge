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
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> avgs = new ArrayList<>();
        if(root==null) return avgs;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            double n = q.size();
            double sum = 0;
            for(int i=0; i<n; i++){
                TreeNode peekN = q.poll();
                sum = sum + peekN.val;
                if(peekN.left!=null) q.add(peekN.left);
                if(peekN.right!=null) q.add(peekN.right);
             }
            avgs.add(sum/n);
        }
        return avgs;
    }
}