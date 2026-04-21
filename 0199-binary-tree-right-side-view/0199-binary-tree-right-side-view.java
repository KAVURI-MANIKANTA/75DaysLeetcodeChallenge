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
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        if(root==null) return res;
        q.add(root);
        while(!q.isEmpty()){
            int rn = 0;
            int n = q.size();
            for(int i=0; i<n; i++){
                TreeNode peek = q.poll();
                rn = peek.val;
                if(peek.left!=null) q.add(peek.left);
                if(peek.right!=null) q.add(peek.right);
            }
            res.add(rn);
        }
        return res;
    }
}