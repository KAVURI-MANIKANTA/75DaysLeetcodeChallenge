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
        List<Integer> r = new ArrayList<>();
        dfs(root,0,r);
        return r;
    }
    public void dfs(TreeNode root, int l, List<Integer> r){
        if(root==null) return;
        if(l==r.size()) r.add(root.val);
        dfs(root.right,l+1,r);
        dfs(root.left,l+1,r);
    }
}