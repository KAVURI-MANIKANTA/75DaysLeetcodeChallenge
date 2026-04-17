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
    public boolean isSame(TreeNode a, TreeNode b){
        if(a==null && b==null) return true;
        if(a==null || b==null) return false;
        return a.val==b.val && isSame(a.left,b.left) && isSame(a.right,b.right);
    }
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root==null) return false;
        if(isSame(root,subRoot)) return true;
        return isSubtree(root.left,subRoot) || isSubtree(root.right,subRoot);
    }
}

/*
class Solution {
    public String strtree(TreeNode node){
        StringBuilder sb = new StringBuilder("^");
        if(node==null) return "null";
        sb.append(node.val);
        sb.append(strtree(node.left));
        sb.append(strtree(node.right));
        return sb.toString();
    }
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        String fTree = strtree(root);
        String subTree = strtree(subRoot);
        return fTree.contains(subTree);
    }
}
*/
