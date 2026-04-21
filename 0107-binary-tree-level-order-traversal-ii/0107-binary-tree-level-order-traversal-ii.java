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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root,0,res);
        Collections.reverse(res);
        return res;
    }
    public void dfs(TreeNode root, int l, List<List<Integer>> res){
        if(root==null) return;
        if(l==res.size()) res.add(new ArrayList<>());
        res.get(l).add(root.val);
        dfs(root.left,l+1,res);
        dfs(root.right,l+1,res);
    }
}