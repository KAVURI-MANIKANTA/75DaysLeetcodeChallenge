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
        List<Integer> res = new ArrayList<>();
        dfs(root,0,res);
        int n = res.size();
        int ms = Integer.MIN_VALUE;
        int lev = 0;
        for(int i=0; i<n; i++){
            if(res.get(i)>ms){
                ms = res.get(i);
                lev = i+1;
            }
        }
        return lev;
    }
    public void dfs(TreeNode root,int l,List<Integer> res){
        if(root==null) return;
        if(l==res.size()) res.add(l,root.val);
        else res.set(l,res.get(l)+root.val);
        dfs(root.left,l+1,res);
        dfs(root.right,l+1,res);
    }
}