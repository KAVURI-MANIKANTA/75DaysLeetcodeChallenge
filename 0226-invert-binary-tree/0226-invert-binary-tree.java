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
    public TreeNode invertTree(TreeNode root) {
        if(root==null) return root;
        Queue<TreeNode> QTree = new LinkedList<>();
        QTree.add(root);
        while(!QTree.isEmpty()){
            TreeNode QRoot = QTree.poll();
            TreeNode temp = QRoot.left;
            QRoot.left = QRoot.right;
            QRoot.right = temp;
            if(QRoot.left!=null) QTree.add(QRoot.left);
            if(QRoot.right!=null) QTree.add(QRoot.right);
        }
        return root;
    }
}

