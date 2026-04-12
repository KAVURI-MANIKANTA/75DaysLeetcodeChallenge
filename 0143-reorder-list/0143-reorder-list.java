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
    public void reorderList(ListNode head) {
        ListNode sl = head;
        ListNode fa = head;
        while(fa.next!=null && fa.next.next!=null){
            sl = sl.next;
            fa = fa.next.next;
        }
        ListNode curr = sl.next;
        sl.next = null;
        ListNode pre = null;
        while(curr!=null){
            ListNode nextn = curr.next;
            curr.next = pre;
            pre = curr;
            curr = nextn;
        }
        ListNode first = head;
        ListNode second = pre;
        while(second!=null){
            ListNode temp1 = first.next;
            ListNode temp2 = second.next;
            first.next = second;
            second.next = temp1;
            first = temp1;
            second = temp2;
        }
    }
}