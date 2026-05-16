<h2><a href="https://leetcode.com/problems/intersection-of-two-linked-lists">160. Intersection of Two Linked Lists</a></h2><h3>Easy</h3><hr><p>Given the heads of two singly linked-lists <code>headA</code> and <code>headB</code>, return <em>the node at which the two lists intersect</em>. If the two linked lists have no intersection at all, return <code>null</code>.</p>

<p>For example, the following two linked lists begin to intersect at node <code>c1</code>:</p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/03/05/160_statement.png" style="width: 500px; height: 162px;" />
<p>The test cases are generated such that there are no cycles anywhere in the entire linked structure.</p>

<p><strong>Note</strong> that the linked lists must <strong>retain their original structure</strong> after the function returns.</p>

<p><strong>Custom Judge:</strong></p>

<p>The inputs to the <strong>judge</strong> are given as follows (your program is <strong>not</strong> given these inputs):</p>

<ul>
	<li><code>intersectVal</code> - The value of the node where the intersection occurs. This is <code>0</code> if there is no intersected node.</li>
	<li><code>listA</code> - The first linked list.</li>
	<li><code>listB</code> - The second linked list.</li>
	<li><code>skipA</code> - The number of nodes to skip ahead in <code>listA</code> (starting from the head) to get to the intersected node.</li>
	<li><code>skipB</code> - The number of nodes to skip ahead in <code>listB</code> (starting from the head) to get to the intersected node.</li>
</ul>

<p>The judge will then create the linked structure based on these inputs and pass the two heads, <code>headA</code> and <code>headB</code> to your program. If you correctly return the intersected node, then your solution will be <strong>accepted</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/03/05/160_example_1_1.png" style="width: 500px; height: 162px;" />
<pre>
<strong>Input:</strong> intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
<strong>Output:</strong> Intersected at &#39;8&#39;
<strong>Explanation:</strong> The intersected node&#39;s value is 8 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
- Note that the intersected node&#39;s value is not 1 because the nodes with value 1 in A and B (2<sup>nd</sup> node in A and 3<sup>rd</sup> node in B) are different node references. In other words, they point to two different locations in memory, while the nodes with value 8 in A and B (3<sup>rd</sup> node in A and 4<sup>th</sup> node in B) point to the same location in memory.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/03/05/160_example_2.png" style="width: 500px; height: 194px;" />
<pre>
<strong>Input:</strong> intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
<strong>Output:</strong> Intersected at &#39;2&#39;
<strong>Explanation:</strong> The intersected node&#39;s value is 2 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/03/05/160_example_3.png" style="width: 300px; height: 189px;" />
<pre>
<strong>Input:</strong> intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
<strong>Output:</strong> No intersection
<strong>Explanation:</strong> From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
Explanation: The two lists do not intersect, so return null.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes of <code>listA</code> is in the <code>m</code>.</li>
	<li>The number of nodes of <code>listB</code> is in the <code>n</code>.</li>
	<li><code>1 &lt;= m, n &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= skipA &lt;= m</code></li>
	<li><code>0 &lt;= skipB &lt;= n</code></li>
	<li><code>intersectVal</code> is <code>0</code> if <code>listA</code> and <code>listB</code> do not intersect.</li>
	<li><code>intersectVal == listA[skipA] == listB[skipB]</code> if <code>listA</code> and <code>listB</code> intersect.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Could you write a solution that runs in <code>O(m + n)</code> time and use only <code>O(1)</code> memory?
<p>
	
# LeetCode 160 — Intersection of Two Linked Lists

LeetCode 160: Intersection of Two Linked Lists

## Problem Statement

Given two singly linked lists, return the node where the two lists intersect.
If the two linked lists have no intersection, return `null`.

---

# 1) Brute Force Solution

## Idea

* Take one node from List A.
* Compare it with every node in List B.
* If both node addresses are same (`tempA == tempB`), intersection found.

---

## Java Code (Brute Force)

```java
public class Solution {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode tempA = headA;

        while (tempA != null) {

            ListNode tempB = headB;

            while (tempB != null) {

                // Compare node addresses
                if (tempA == tempB) {
                    return tempA;
                }

                tempB = tempB.next;
            }

            tempA = tempA.next;
        }

        return null;
    }
}
```

---

# Brute Force Explanation

Suppose:

```text
List A: 1 → 2 → 3 → 7 → 8
List B: 4 → 5 → 7 → 8
```

* First node of A (`1`) compares with all nodes of B.
* Then node `2` compares with all nodes of B.
* Then node `3`.
* Finally node `7`.
* `7 == 7` (same memory node), so return node `7`.

---

# Time Complexity (TC)

### Outer loop:

Runs `m` times.

### Inner loop:

Runs `n` times.

```text
TC = O(m × n)
```

---

# Space Complexity (SC)

No extra space used.

```text
SC = O(1)
```

---

# 2) Optimal Solution (Two Pointer Method)

## Idea

Use two pointers:

* `p1` starts from List A
* `p2` starts from List B

When a pointer reaches end:

* move it to other list head.

Eventually:

* both pointers travel equal distance.
* They meet at intersection node.

---

## Java Code (Optimal)

```java
public class Solution {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode p1 = headA;
        ListNode p2 = headB;

        while (p1 != p2) {

            // If p1 becomes null, move to headB
            if (p1 == null) {
                p1 = headB;
            } else {
                p1 = p1.next;
            }

            // If p2 becomes null, move to headA
            if (p2 == null) {
                p2 = headA;
            } else {
                p2 = p2.next;
            }
        }

        return p1;
    }
}
```

---

# Optimal Solution Explanation

## Example

```text
List A: 1 → 2 → 3 → 7 → 8
List B: 4 → 5 → 7 → 8
```

### Pointer movement

```text
p1: 1 → 2 → 3 → 7 → 8 → null → 4 → 5 → 7
p2: 4 → 5 → 7 → 8 → null → 1 → 2 → 3 → 7
```

Both meet at node `7`.

---

# Why It Works

Both pointers travel:

```text
LengthA + LengthB
```

So distance becomes equal.

If intersection exists:

* they meet there.

If no intersection:

* both become `null`.

---

# Time Complexity (TC)

Each pointer travels at most:

```text
m + n
```

So:

```text
TC = O(m + n)
```

---

# Space Complexity (SC)

No extra data structure used.

```text
SC = O(1)
```

---

# Comparison Table

| Method      | Time Complexity | Space Complexity | Efficient? |
| ----------- | --------------- | ---------------- | ---------- |
| Brute Force | O(m × n)        | O(1)             | No         |
| Two Pointer | O(m + n)        | O(1)             | Yes        |

---

# Interview Points

## Important Note

Intersection means:

```text
Same node address
```

NOT same value.

Correct:

```java
if (tempA == tempB)
```

Wrong:

```java
if (tempA.val == tempB.val)
```

Because values can be same in different nodes.

---

# Short Exam Definition

### Brute Force

Compare every node of first linked list with every node of second linked list to find common node.

### Optimal Method

Use two pointers that switch lists after reaching end so both travel equal distance and meet at intersection node.


# Optimal Solution — Easy Understanding

LeetCode 160: Intersection of Two Linked Lists

## First Understand the Main Trick

Two lists may have different lengths.

Example:

```text id="q9xj5u"
List A: 1 → 2 → 3 → 7 → 8
List B: 4 → 5 → 7 → 8
```

Here:

```text id="6mk1dw"
Length of A = 5
Length of B = 4
```

Intersection starts from node `7`.

---

# Problem

If we move normally:

```text id="syfsvj"
p1 from A
p2 from B
```

They will NOT meet together because lengths are different.

---

# Main Idea of Optimal Solution

When a pointer reaches end:

```text id="ry6b4y"
send it to other list
```

Meaning:

```text id="3xq7yk"
p1 finishes A → start from B
p2 finishes B → start from A
```

So both travel SAME total distance.

---

# Visual Understanding

## Initial

```text id="lq2g1q"
A: 1 → 2 → 3 → 7 → 8
B: 4 → 5 → 7 → 8
```

---

# Step-by-Step Movement

## Step 1

```text id="w4m7mi"
p1 = 1
p2 = 4
```

---

## Step 2

```text id="awz7fh"
p1 = 2
p2 = 5
```

---

## Step 3

```text id="ns0dvy"
p1 = 3
p2 = 7
```

---

## Step 4

```text id="k6w2n0"
p1 = 7
p2 = 8
```

---

## Step 5

```text id="z2v6gm"
p1 = 8
p2 = null
```

Now:

```text id="f7r4ur"
p2 reached end
```

So move p2 to headA.

---

## Step 6

```text id="1c6j2o"
p1 = null
p2 = 1
```

Now:

```text id="1t0xq6"
p1 reached end
```

So move p1 to headB.

---

## Step 7

```text id="s2ycfq"
p1 = 4
p2 = 2
```

---

## Step 8

```text id="m7w5dn"
p1 = 5
p2 = 3
```

---

## Step 9

```text id="y2x4gk"
p1 = 7
p2 = 7
```

BOTH MEET 🎉

Return node `7`.

---

# Why They Meet

Because both travel equal distance:

## p1 travels

```text id="b0r5qj"
A + B
```

## p2 travels

```text id="jw5s1r"
B + A
```

Same total distance.

So they become aligned automatically.

---

# Easy Memory Trick

```text id="g3m6ls"
"If one pointer finishes,
send it to other list."
```

That is the entire trick.

---

# Simplified Code

```java id="byrclg"
public class Solution {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode p1 = headA;
        ListNode p2 = headB;

        while (p1 != p2) {

            p1 = (p1 == null) ? headB : p1.next;

            p2 = (p2 == null) ? headA : p2.next;
        }

        return p1;
    }
}
```

---

# Important Point

We compare:

```java id="0p3uq2"
p1 == p2
```

NOT:

```java id="wwu3fb"
p1.val == p2.val
```

Because intersection means:

```text id="p3h4s8"
same memory node
```

not same value.

---

# Time Complexity

```text id="6x9mtr"
O(m + n)
```

---

# Space Complexity

```text id="1h9g6v"
O(1)
```

```
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = getListLength(headA);
        int lenB = getListLength(headB);

        if(lenA>lenB)
        {
            while(lenA>lenB)
            {
               headA=headA.next;
               lenA--;
            }
        }
        else
        {
             while(lenA<lenB)
             {
                headB=headB.next;
                lenB--;
             }
        }
        while(headA!=headB)
        {
            headA=headA.next;
            headB=headB.next;
        }
        return headA;
    }
    public int getListLength(ListNode head)
    {
        int length = 0;
        while(head!=null)
        {
            length++;
            head=head.next;
        }
        return length;
    }
}
```


</p>
