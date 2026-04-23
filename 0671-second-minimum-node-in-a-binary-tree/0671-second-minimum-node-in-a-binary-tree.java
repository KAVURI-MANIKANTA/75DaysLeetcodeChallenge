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
    public int findSecondMinimumValue(TreeNode root) {
        if(root==null) return -1;
        return dfsfind(root,root.val);
    }
    public int dfsfind(TreeNode root, int min){
        if(root==null) return -1;
        if(root.val>min) return root.val;
        int left = dfsfind(root.left,min);
        int right = dfsfind(root.right,min);
        if(left==-1) return right;
        if(right==-1) return left;
        return Math.min(left,right);
    }
}