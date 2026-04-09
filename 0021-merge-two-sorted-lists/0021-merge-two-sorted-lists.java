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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode preN = dummy;
        while(list1!=null && list2!=null){
            if(list1.val<=list2.val){
                preN.next = list1;
                list1 = list1.next;
            }
            else{
                preN.next = list2;
                list2 = list2.next;
            }
            preN = preN.next;
        }
        if(list1==null) preN.next = list2;
        else preN.next = list1;
        return dummy.next;
    }
}


/*

> Why do we compare using `.val` but assign using `list1` or `list2`?

---

## 🧠 Core Concept (Very Important)

👉 A **ListNode is not just a value**, it is a **whole node (value + next pointer)**

```
ListNode = [ val | next ]
```

So:

* `list1.val` → gives **only the value**
* `list1` → gives the **entire node (with its next connections)**

---

## ⚡ Step-by-Step Understanding

### 1️⃣ Comparison → We only need values

```java
if(list1.val <= list2.val)
```

✔ Here we are just deciding:
👉 “Which value is smaller?”

So we use `.val`

---

### 2️⃣ Assignment → We need the whole node

```java
pre.next = list1;
```

❗ Here we are NOT adding just the value
We are attaching the **entire node**

---

## 🚨 Why not use `.val` here?

If you try this:

```java
pre.next = list1.val; ❌
```

💥 Error! Because:

* `pre.next` expects a **ListNode**
* `list1.val` is just an **int**

---

## 🧩 Real Visualization

Imagine:

```
list1: 1 -> 3 -> 5
list2: 2 -> 4 -> 6
```

### Step:

```java
pre.next = list1;
```

👉 You are attaching:

```
1 -> 3 -> 5
```

NOT just `1`

---

## 🔥 Why this is powerful?

Because:
👉 You are **reusing existing nodes**
👉 No need to create new nodes
👉 Efficient (O(1) extra space)

---

## 💡 Simple Analogy

Think like this:

* `.val` → checking **marks of a student**
* `list1` → selecting the **whole student (with all details)**

---

## ✅ Final Summary

* `.val` → used for **comparison**
* `list1` / `list2` → used for **linking nodes**
* We attach **nodes**, not just values

*/