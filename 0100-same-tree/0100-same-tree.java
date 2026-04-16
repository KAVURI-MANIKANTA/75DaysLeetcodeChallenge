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
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        preorder(p,list1);
        preorder(q,list2);
        return list1.equals(list2);
    }
    public void preorder(TreeNode node, List<String> list){
        if(node==null){
            list.add("null");
            return;
        }
        list.add(String.valueOf(node.val));
        preorder(node.left,list);
        preorder(node.right,list);
    }
}