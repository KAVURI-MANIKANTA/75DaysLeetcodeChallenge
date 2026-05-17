<h2><a href="https://leetcode.com/problems/add-two-numbers">2. Add Two Numbers</a></h2><h3>Medium</h3><hr><p>You are given two <strong>non-empty</strong> linked lists representing two non-negative integers. The digits are stored in <strong>reverse order</strong>, and each of their nodes contains a single digit. Add the two numbers and return the sum&nbsp;as a linked list.</p>

<p>You may assume the two numbers do not contain any leading zero, except the number 0 itself.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/02/addtwonumber1.jpg" style="width: 483px; height: 342px;" />
<pre>
<strong>Input:</strong> l1 = [2,4,3], l2 = [5,6,4]
<strong>Output:</strong> [7,0,8]
<strong>Explanation:</strong> 342 + 465 = 807.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> l1 = [0], l2 = [0]
<strong>Output:</strong> [0]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
<strong>Output:</strong> [8,9,9,9,0,0,0,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in each linked list is in the range <code>[1, 100]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 9</code></li>
	<li>It is guaranteed that the list represents a number that does not have leading zeros.</li>
</ul>
<p>
	
# LeetCode LeetCode 2 — Add Two Numbers

---

# Problem Statement

You are given 2 linked lists:

```text id="r0dx1z"
l1 = 2 -> 4 -> 3
l2 = 5 -> 6 -> 4
```

Each node contains **one digit**.

Digits are stored in **reverse order**.

That means:

```text id="18q8rd"
2 -> 4 -> 3 = 342
5 -> 6 -> 4 = 465
```

Now add:

```text id="zghz9o"
342 + 465 = 807
```

Return answer in reverse order:

```text id="h6i4xu"
7 -> 0 -> 8
```

---

# Why Are We Adding From Starting?

This is the MOST important concept.

Normally:

```text id="e6p2z1"
342
465
```

We add from RIGHT side:

```text id="x3nxx0"
2 + 5
4 + 6
3 + 4
```

But linked lists are stored in REVERSE order:

```text id="5kt3z7"
2 -> 4 -> 3
```

So:

* first node = ones place
* second node = tens place
* third node = hundreds place

That means:

```text id="qjlwm8"
Starting node already contains smallest digit
```

So we can directly start adding from head.

---

# Why Reverse Order Is Helpful?

If digits were normal order:

```text id="dgr6vy"
3 -> 4 -> 2
4 -> 6 -> 5
```

We would need:

* reverse list OR
* recursion OR
* stack

because addition starts from end.

But here:

* least significant digit comes first
* easy addition possible from head itself

That is why problem stores digits in reverse order.

---

# Brute Force Solution

## Idea

1. Convert linked lists into numbers
2. Add numbers
3. Convert answer back into linked list

---

# Brute Force Code (Java)

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        long num1 = 0;
        long num2 = 0;

        long place = 1;

        // Convert l1 to number
        while(l1 != null) {
            num1 += l1.val * place;
            place *= 10;
            l1 = l1.next;
        }

        place = 1;

        // Convert l2 to number
        while(l2 != null) {
            num2 += l2.val * place;
            place *= 10;
            l2 = l2.next;
        }

        long sum = num1 + num2;

        // Special case
        if(sum == 0) {
            return new ListNode(0);
        }

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        // Convert sum to linked list
        while(sum > 0) {

            int digit = (int)(sum % 10);

            curr.next = new ListNode(digit);

            curr = curr.next;

            sum /= 10;
        }

        return dummy.next;
    }
}
```

---

# Brute Force Explanation

## Step 1 — Convert linked list to number

Example:

```text id="76x8u9"
2 -> 4 -> 3
```

becomes:

```text id="f1f2v3"
342
```

using:

```java
num += digit * place
```

---

## Step 2 — Add numbers

```java
sum = num1 + num2;
```

---

## Step 3 — Convert answer into linked list

Take digits using:

```java
sum % 10
```

and move using:

```java
sum /= 10
```

---

# Problem in Brute Force

Very important.

This fails for VERY large numbers.

Example:

```text id="fkgc0d"
999999999999999999999
```

cannot fit inside:

```java
long
```

So brute force is NOT good for interviews.

---

# Brute Force Complexity

| Complexity | Value                   |
| ---------- | ----------------------- |
| Time       | O(n + m)                |
| Space      | O(1) (excluding output) |

---

# Optimal Solution

## Main Idea

Do addition digit by digit exactly like school math.

Use:

* carry
* linked list traversal

---

# Optimal Code (Java)

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);

        ListNode curr = dummy;

        int carry = 0;

        while(l1 != null || l2 != null || carry != 0) {

            int x = 0;
            int y = 0;

            if(l1 != null) {
                x = l1.val;
                l1 = l1.next;
            }

            if(l2 != null) {
                y = l2.val;
                l2 = l2.next;
            }

            int sum = x + y + carry;

            carry = sum / 10;

            int digit = sum % 10;

            curr.next = new ListNode(digit);

            curr = curr.next;
        }

        return dummy.next;
    }
}
```

---

# Optimal Solution Dry Run

## Input

```text id="d74nzc"
2 -> 4 -> 3
5 -> 6 -> 4
```

---

## Iteration 1

```text id="mafp4y"
2 + 5 + 0 = 7
```

digit:

```text id="0l8ysn"
7
```

carry:

```text id="9qdbgg"
0
```

Answer:

```text id="qf0cfx"
7
```

---

## Iteration 2

```text id="gk87r2"
4 + 6 + 0 = 10
```

digit:

```text id="k0m3sm"
0
```

carry:

```text id="i9cl8r"
1
```

Answer:

```text id="2u3brn"
7 -> 0
```

---

## Iteration 3

```text id="j7zq40"
3 + 4 + 1 = 8
```

digit:

```text id="n5xjlwm"
8
```

carry:

```text id="rmr7yh"
0
```

Final:

```text id="98mjlwm"
7 -> 0 -> 8
```

---

# Why Dummy Node Is Used?

Without dummy node:

* handling first node becomes difficult

Dummy node helps:

* easy insertion
* clean code

Example:

```java
ListNode dummy = new ListNode(0);
```

---

# Important Logic

## Digit

```java
digit = sum % 10;
```

Gets last digit.

Example:

```text id="w1w76s"
17 % 10 = 7
```

---

## Carry

```java
carry = sum / 10;
```

Example:

```text id="9zyv9k"
17 / 10 = 1
```

---

# Why This Is Optimal?

Because:

* no number conversion
* works for huge inputs
* directly processes linked lists

---

# Optimal Complexity

| Complexity | Value                        |
| ---------- | ---------------------------- |
| Time       | O(max(n,m))                  |
| Space      | O(1) (excluding output list) |

---

# Edge Cases

## Different lengths

```text id="0cnkk3"
[9,9,9]
[1]
```

Output:

```text id="qlqic5"
0 -> 0 -> 0 -> 1
```

---

## Final carry exists

```text id="m8xvy4"
[5]
[5]
```

Output:

```text id="dgmzqn"
0 -> 1
```

---

# Interview Points

Interviewer checks:

* linked list traversal
* carry handling
* dummy node usage
* edge case handling
* simulation skills

---

# Which Solution Should You Write?

## In Interview

Always write:
✅ Optimal solution

Because brute force:

* fails for large inputs
* integer overflow issue

---

# Final Intuition

This problem is simply:

```text id="5g0x3d"
Normal addition using linked list
```

The only trick is:

```text id="6gj0l9"
Digits are stored in reverse order
```

So we can start addition directly from head nodes.

</p>
