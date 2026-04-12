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
        if(head==null || head.next==null) return;
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

/*
LeetCode **143. Reorder List** is a very important linked list problem 🔥
Goal:
`L0 → L1 → L2 → ... → Ln`
Reorder to:
`L0 → Ln → L1 → Ln-1 → L2 → Ln-2 ...`

---

# 🧠 Brute Force Approach

## 💡 Idea

* Store all nodes in an ArrayList
* Use two pointers (`i`, `j`)
* Reconnect nodes from start and end alternately

## ⏱ Time & Space

* Time: **O(n)**
* Space: **O(n)** (extra list)

## ✅ Code (Brute Force)

```java
class Solution {
    public void reorderList(ListNode head) {
        if (head == null) return;

        List<ListNode> list = new ArrayList<>();
        
        // Step 1: Store nodes
        ListNode temp = head;
        while (temp != null) {
            list.add(temp);
            temp = temp.next;
        }

        // Step 2: Reorder using two pointers
        int i = 0, j = list.size() - 1;

        while (i < j) {
            list.get(i).next = list.get(j);
            i++;

            if (i == j) break;

            list.get(j).next = list.get(i);
            j--;
        }

        list.get(i).next = null; // important
    }
}
```

---

# 🚀 Optimal Approach

## 💡 Idea (3 Steps)

1. **Find middle of list**
2. **Reverse second half**
3. **Merge both halves alternately**

---

## 🔍 Step-by-Step

### 1. Find Middle (Slow & Fast)

* Slow moves 1 step
* Fast moves 2 steps

### 2. Reverse second half

### 3. Merge

* Alternate nodes from first and reversed second half

---

## ⏱ Time & Space

* Time: **O(n)**
* Space: **O(1)** (in-place)

---

## ✅ Code (Optimal)

```java
        submitted code
```

---

# ⚡ Quick Comparison

| Approach    | Time | Space | Notes                   |
| ----------- | ---- | ----- | ----------------------- |
| Brute Force | O(n) | O(n)  | Easy, uses extra memory |
| Optimal     | O(n) | O(1)  | Interview favorite      |

*/