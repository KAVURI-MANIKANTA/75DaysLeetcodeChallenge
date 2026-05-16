/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int a = len(headA);
        int b = len(headB);
        if(a>b){
            while(a>b){
                headA=headA.next;
                a--;
            }
        }
        else{
            while(b>a){
                headB=headB.next;
                b--;
            }
        }
        while(headA!=headB){
            headA=headA.next;
            headB=headB.next;
        }
        return headA;
    }
    public int len(ListNode head){
        int l=0;
        ListNode temp = head;
        while(temp!=null){
            l++;
            temp=temp.next;
        }
        return l;
    }
}