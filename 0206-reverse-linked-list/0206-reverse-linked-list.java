/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head==null || head.next==null) return head;
        ListNode curN = head;
        ListNode preN = null;
        while(curN!=null){
            ListNode nextNode = curN.next;
            curN.next = preN;
            preN = curN;
            curN = nextNode;
        }
        return preN;
    }
}