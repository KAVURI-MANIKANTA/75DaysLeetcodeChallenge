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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        for(int i=0; i<n; i++) fast = fast.next;
        if(fast==null) return head.next;
        ListNode slow = head;
        while(fast.next!=null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}



/*
🧠 1. Brute Force (Two Pass)
💡 Idea
Count total nodes
Find (length - n) → node before target
Remove it
✅ Code
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp = head;
        int count = 0;
        while(temp!=null){
            count++;
            temp=temp.next;
        }
        if(count==n) return head.next;
        ListNode fn = head;
        int firstn = count-n;
        while(firstn>1){
            firstn--;
            fn = fn.next;
        }
        fn.next = fn.next.next;
        return head;
    }
}
⏱ Complexity
Time: O(n) + O(n) = O(n)
Space: O(1)
*/

/*
🔥 Interview Tip (VERY IMPORTANT)
Case	Meaning
n == length	Remove head
n == 1	Remove last node
*/


/* OPTIMAL
# 🧠 Core Idea (In One Line)

We keep a **gap of `n` nodes** between `fast` and `slow`.

So when `fast` reaches the end,
👉 `slow` will be **just before the node to delete**

---

# 🔥 Step-by-Step Example

### Example:

```
head = [1,2,3,4,5], n = 2
```

We need to remove **4**

---

## Step 1: Initialize

```
slow = 1
fast = 1
```

---

## Step 2: Move `fast` n steps ahead

Move `fast` 2 steps:

```
fast = 3
slow = 1
```

👉 Gap = 2 nodes

---

## Step 3: Move both together

Move both until `fast.next == null`

```
Step 1:
slow = 2
fast = 4

Step 2:
slow = 3
fast = 5  (fast.next = null → STOP)
```

---

## Step 4: Delete node

```
slow = 3
slow.next = 4 (node to delete)

Do:
slow.next = slow.next.next
```

Result:

```
1 → 2 → 3 → 5
```

---

# ⚠️ Special Case (VERY IMPORTANT)

### Example:

```
head = [1,2], n = 2
```

Move `fast` 2 steps:

```
fast = null
```

👉 That means we must remove **head**

So:

```java
return head.next;
```

---

# ✅ Final Code (Same, but now understandable)

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;

        // Step 1: Move fast n steps ahead
        for(int i = 0; i < n; i++){
            fast = fast.next;
        }

        // Step 2: If fast is null → remove head
        if(fast == null){
            return head.next;
        }

        // Step 3: Move both until fast reaches last node
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }

        // Step 4: Delete node
        slow.next = slow.next.next;

        return head;
    }
}
```
⏱ Complexity
Time: O(n)
Space: O(1)

---

# 💡 Why This Works (Simple Logic)

Think like this:

* `fast` reaches end → position = length
* `slow` is `n` nodes behind
* So `slow` is at **(length - n)** position
* Which is exactly **before the node to delete**

---

# 🎯 One More Quick Example (Small)

### Input:

```
[1,2], n = 1
```

After moving fast 1 step:

```
fast = 2
slow = 1
```

Move both:

```
fast reaches end
slow = 1
```

Delete:

```
remove 2
→ [1]
```

---

# 🔑 Memory Trick

👉 “**Fast moves first, slow follows later**”
👉 Gap = n → slow lands before target
*/