<h2><a href="https://leetcode.com/problems/remove-duplicates-from-sorted-list">83. Remove Duplicates from Sorted List</a></h2><h3>Easy</h3><hr><p>Given the <code>head</code> of a sorted linked list, <em>delete all duplicates such that each element appears only once</em>. Return <em>the linked list <strong>sorted</strong> as well</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/04/list1.jpg" style="width: 302px; height: 242px;" />
<pre>
<strong>Input:</strong> head = [1,1,2]
<strong>Output:</strong> [1,2]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/04/list2.jpg" style="width: 542px; height: 222px;" />
<pre>
<strong>Input:</strong> head = [1,1,2,3,3]
<strong>Output:</strong> [1,2,3]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the list is in the range <code>[0, 300]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
	<li>The list is guaranteed to be <strong>sorted</strong> in ascending order.</li>
</ul>
<p>
	
# LeetCode 83 — Remove Duplicates from Sorted List

Problem:
Given the head of a **sorted linked list**, delete all duplicates such that each element appears only once.

Example:

Input:
`1 -> 1 -> 2 -> 3 -> 3`

Output:
`1 -> 2 -> 3`

---

# Brute Force Approach

## Idea

1. Traverse the linked list.
2. Store unique values in an ArrayList.
3. Create a new linked list using unique values.

Since the list is sorted, duplicates come together.

---

# Brute Force Code (Java)

```java
import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

class Solution {
    public ListNode deleteDuplicates(ListNode head) {

        if (head == null)
            return null;

        ArrayList<Integer> list = new ArrayList<>();

        ListNode temp = head;

        // Store unique values
        while (temp != null) {

            if (list.isEmpty() || list.get(list.size() - 1) != temp.val) {
                list.add(temp.val);
            }

            temp = temp.next;
        }

        // Create new linked list
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        for (int num : list) {
            curr.next = new ListNode(num);
            curr = curr.next;
        }

        return dummy.next;
    }
}
```

---

# Brute Force Explanation

Suppose:

`1 -> 1 -> 2 -> 3 -> 3`

### Step 1:

Store unique values in ArrayList.

ArrayList becomes:

`[1, 2, 3]`

### Step 2:

Create new linked list from ArrayList.

Result:

`1 -> 2 -> 3`

---

# Time Complexity (TC)

* Traversing list → `O(N)`
* Creating new list → `O(N)`

### Total TC:

# `O(N)`

---

# Space Complexity (SC)

ArrayList used.

### SC:

# `O(N)`

---

# Optimal Approach

## Idea

Since list is already sorted:

* If current node value equals next node value,
  skip duplicate node.
* Otherwise move forward.

Modify original list directly.

---

# Optimal Code (Java)

```java
class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

class Solution {
    public ListNode deleteDuplicates(ListNode head) {

        ListNode current = head;

        while (current != null && current.next != null) {

            // Duplicate found
            if (current.val == current.next.val) {
                current.next = current.next.next;
            }
            else {
                current = current.next;
            }
        }

        return head;
    }
}
```

---

# Optimal Explanation

Input:

`1 -> 1 -> 2 -> 3 -> 3`

---

## Step 1

Current at first `1`

Compare:
`1 == 1`

Duplicate found.

Skip node:

```java
current.next = current.next.next;
```

List becomes:

`1 -> 2 -> 3 -> 3`

---

## Step 2

Now current still at `1`

Compare:
`1 != 2`

Move current forward.

Current becomes `2`

---

## Step 3

Compare:
`2 != 3`

Move current.

Current becomes `3`

---

## Step 4

Compare:
`3 == 3`

Duplicate found.

Skip duplicate.

Final list:

`1 -> 2 -> 3`

---

# Time Complexity (TC)

Only one traversal.

### TC:

# `O(N)`

---

# Space Complexity (SC)

No extra space used.

### SC:

# `O(1)`

---

# Why Optimal is Better?

| Brute Force          | Optimal                |
| -------------------- | ---------------------- |
| Uses extra ArrayList | No extra space         |
| Creates new list     | Modifies existing list |
| SC = O(N)            | SC = O(1)              |
| Less efficient       | More efficient         |

---

# Interview Important Points

## Why does optimal work?

Because the linked list is already **sorted**.

Duplicates always appear adjacent.

Example:

`1 1 2 3 3`

So we only compare:

```java
current.val == current.next.val
```

---

# Edge Cases

## Empty list

Input:
`null`

Output:
`null`

---

## Single node

Input:
`1`

Output:
`1`

---

## All duplicates

Input:
`1 -> 1 -> 1`

Output:
`1`

---

# Short Revision

## Brute Force

* Store unique values
* Create new list
* TC = `O(N)`
* SC = `O(N)`

## Optimal

* Compare adjacent nodes
* Skip duplicates directly
* TC = `O(N)`
* SC = `O(1)`

</p>
