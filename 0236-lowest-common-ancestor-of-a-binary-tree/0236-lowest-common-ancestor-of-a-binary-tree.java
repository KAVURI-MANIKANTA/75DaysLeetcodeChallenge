/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p==root || q==root) return root;
        TreeNode lnd = lowestCommonAncestor(root.left, p, q);
        TreeNode rnd = lowestCommonAncestor(root.right, p, q);
        if(lnd==null) return rnd;
        if(rnd==null) return lnd;
        return root;
    }
}