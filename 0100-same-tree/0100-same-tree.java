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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> qu = new LinkedList<>();
        qu.add(p);
        qu.add(q);
        while(!qu.isEmpty()){
            TreeNode peek1 = qu.poll();
            TreeNode peek2 = qu.poll();
            if(peek1==null && peek2==null) continue;
            if(peek1==null || peek2==null || peek1.val!=peek2.val) return false;
            qu.add(peek1.left);
            qu.add(peek2.left);
            qu.add(peek1.right);
            qu.add(peek2.right);
        }
        return true;
    }
}