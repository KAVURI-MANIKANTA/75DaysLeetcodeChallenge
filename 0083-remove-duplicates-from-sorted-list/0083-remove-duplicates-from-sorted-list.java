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
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)
        {
            return head;
        }
        ListNode ptr = head;
        ListNode temp = ptr.next;
        while(temp!=null)
        {
            if(temp.val == ptr.val)
            {
                temp = temp.next;
                continue;
            }
            ptr.next=temp;
            ptr=temp;
            temp=temp.next;
        }
        ptr.next=null;
        return head;
    }
}