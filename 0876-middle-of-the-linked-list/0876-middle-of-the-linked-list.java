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
    public ListNode middleNode(ListNode head) {
        ListNode slowPtr = head;
        ListNode fastPtr = head;
        while(fastPtr!=null && fastPtr.next!=null){
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }
        return slowPtr;
    }
}





/*
LeetCode **876 – Middle of the Linked List**

---

## 🧠 Problem

Given the head of a singly linked list, return the **middle node**.
If there are two middle nodes → return the **second middle**.

---

# 🔴 Brute Force Approach

## 💡 Idea

1. Traverse the list and **count total nodes (n)**
2. Traverse again to the **n/2-th node**
3. Return that node

---

## ⏱ Complexity

* Time: **O(n) + O(n) = O(n)**
* Space: **O(1)**

---

## ✅ Java Code

```java
class Solution {
    public ListNode middleNode(ListNode head) {
        int count = 0;
        ListNode temp = head;

        // Step 1: Count nodes
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        // Step 2: Move to middle (count/2)
        temp = head;
        for (int i = 0; i < count / 2; i++) {
            temp = temp.next;
        }

        return temp;
    }
}
```

---

# 🟢 Optimal Approach (Fast & Slow Pointer)

## 💡 Idea

* Use two pointers:

  * **slow → moves 1 step**
  * **fast → moves 2 steps**
* When fast reaches end → slow will be at middle

---

## ⏱ Complexity

* Time: **O(n)**
* Space: **O(1)**

---

## ✅ Java Code

```java
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;       // 1 step
            fast = fast.next.next;  // 2 steps
        }

        return slow;
    }
}
```

---

# 🔥 Why Optimal is Better?

* Only **one traversal**
* Cleaner and commonly used in interviews
* Works naturally for both odd & even length lists

---

# ⚡ Quick Example

### Input:

```
1 → 2 → 3 → 4 → 5 → 6
```

### Steps:

```
slow: 1 → 2 → 3 → 4
fast: 1 → 3 → 5 → null
```

### Output:

```
4 (second middle)
```
*/