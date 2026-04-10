/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        if(head==null || head.next==null) return false;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast) return true;
        }
        return false;
    }
}


/*
public class Solution {
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> hs = new HashSet<>();
        ListNode temp = head;
        while(temp!=null){
            if(hs.contains(temp)) return true;
            hs.add(temp);
            temp = temp.next;
        }
        return false;
    }
}
*/



/*
Here are the **Brute Force** and **Optimal** solutions for **LeetCode 141 – Linked List Cycle** in Java:

---

# 🔴 Problem Summary

Detect if a linked list has a cycle (loop).

---

# 🧠 1. Brute Force Approach (Using HashSet)

### 💡 Idea:

* Traverse the list
* Store each node in a HashSet
* If a node is already visited → cycle exists

### ✅ Code:

```java
import java.util.HashSet;

public class Solution {
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        
        ListNode temp = head;
        
        while (temp != null) {
            if (set.contains(temp)) {
                return true; // cycle detected
            }
            set.add(temp);
            temp = temp.next;
        }
        
        return false; // no cycle
    }
}
```

### ⏱ Complexity:

* **Time:** O(n)
* **Space:** O(n) ❌ (extra memory used)

---

# ⚡ 2. Optimal Approach (Floyd’s Cycle Detection / Tortoise & Hare)

### 💡 Idea:

* Use two pointers:

  * **Slow** → moves 1 step
  * **Fast** → moves 2 steps
* If there is a cycle → they will meet

### ✅ Code:

```java
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;          // 1 step
            fast = fast.next.next;     // 2 steps
            
            if (slow == fast) {
                return true; // cycle detected
            }
        }
        
        return false; // no cycle
    }
}
```

### ⏱ Complexity:

* **Time:** O(n)
* **Space:** O(1) ✅ (no extra space)

---

# 🔥 Key Difference

| Approach    | Time | Space | Idea                 |
| ----------- | ---- | ----- | -------------------- |
| Brute Force | O(n) | O(n)  | Store visited nodes  |
| Optimal     | O(n) | O(1)  | Fast & Slow pointers |

---

# 🚀 Interview Tip

If interviewer asks:

* First explain **HashSet (easy)**
* Then move to **Floyd’s Algorithm (best answer)**
*/