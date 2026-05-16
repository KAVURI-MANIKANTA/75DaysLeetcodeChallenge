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
        ListNode tempa = headA;
        ListNode tempb = headB;
        if(a>b){
            while(a>b){
                tempa=tempa.next;
                a--;
            }
        }
        else{
            while(b>a){
                tempb=tempb.next;
                b--;
            }
        }
        while(tempa!=null){
            if(tempa==tempb){
                return tempa;
            }
            tempa=tempa.next;
            tempb=tempb.next;
        }
        return tempa;
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