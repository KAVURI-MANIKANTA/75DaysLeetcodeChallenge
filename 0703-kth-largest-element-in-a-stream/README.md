<h2><a href="https://leetcode.com/problems/kth-largest-element-in-a-stream">703. Kth Largest Element in a Stream</a></h2><h3>Easy</h3><hr><p>You are part of a university admissions office and need to keep track of the <code>kth</code> highest test score from applicants in real-time. This helps to determine cut-off marks for interviews and admissions dynamically as new applicants submit their scores.</p>

<p>You are tasked to implement a class which, for a given integer&nbsp;<code>k</code>, maintains a stream of test scores and continuously returns the&nbsp;<code>k</code>th highest test score&nbsp;<strong>after</strong>&nbsp;a new score has been submitted. More specifically, we are looking for the <code>k</code>th highest score in the sorted list of all scores.</p>

<p>Implement the&nbsp;<code>KthLargest</code> class:</p>

<ul>
	<li><code>KthLargest(int k, int[] nums)</code> Initializes the object with the integer <code>k</code> and the stream of test scores&nbsp;<code>nums</code>.</li>
	<li><code>int add(int val)</code> Adds a new test score&nbsp;<code>val</code> to the stream and returns the element representing the <code>k<sup>th</sup></code> largest element in the pool of test scores so far.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong><br />
<span class="example-io">[&quot;KthLargest&quot;, &quot;add&quot;, &quot;add&quot;, &quot;add&quot;, &quot;add&quot;, &quot;add&quot;]<br />
[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[null, 4, 5, 5, 8, 8]</span></p>

<p><strong>Explanation:</strong></p>

<p>KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);<br />
kthLargest.add(3); // return 4<br />
kthLargest.add(5); // return 5<br />
kthLargest.add(10); // return 5<br />
kthLargest.add(9); // return 8<br />
kthLargest.add(4); // return 8</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong><br />
<span class="example-io">[&quot;KthLargest&quot;, &quot;add&quot;, &quot;add&quot;, &quot;add&quot;, &quot;add&quot;]<br />
[[4, [7, 7, 7, 7, 8, 3]], [2], [10], [9], [9]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[null, 7, 7, 7, 8]</span></p>

<p><strong>Explanation:</strong></p>
KthLargest kthLargest = new KthLargest(4, [7, 7, 7, 7, 8, 3]);<br />
kthLargest.add(2); // return 7<br />
kthLargest.add(10); // return 7<br />
kthLargest.add(9); // return 7<br />
kthLargest.add(9); // return 8</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length + 1</code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= val &lt;= 10<sup>4</sup></code></li>
	<li>At most <code>10<sup>4</sup></code> calls will be made to <code>add</code>.</li>
</ul>
<p>
	
# LeetCode 703 — Kth Largest Element in a Stream

Design a class:

* `KthLargest(int k, int[] nums)`
* `add(int val)`

After every insertion, return the **kth largest element**.

---

# ✅ Brute Force Solution

## 💡 Idea

1. Store all elements in a list
2. Whenever `add()` is called:

   * Insert new element
   * Sort the list
   * Return kth largest element

---

# ✅ Brute Force Code (Java)

```java
import java.util.*;

class KthLargest {

    List<Integer> list = new ArrayList<>();
    int k;

    public KthLargest(int k, int[] nums) {

        this.k = k;

        for(int num : nums) {
            list.add(num);
        }
    }

    public int add(int val) {

        list.add(val);

        Collections.sort(list);

        return list.get(list.size() - k);
    }
}
```

---

# 🔍 Brute Force Explanation

---

## Step 1 — Store k

```java
int k;
```

We store `k` globally because both constructor and `add()` need it.

---

## Step 2 — Add Initial Elements

```java
for(int num : nums) {
    list.add(num);
}
```

Store all numbers in list.

---

## Step 3 — Add New Element

```java
list.add(val);
```

Insert new value into list.

---

## Step 4 — Sort List

```java
Collections.sort(list);
```

Sorts list in ascending order.

Example:

```text
[2,4,5,8]
```

---

## Step 5 — Find kth Largest

```java
return list.get(list.size() - k);
```

Why?

Because array is ascending sorted.

Example:

```text
Index : 0 1 2 3
Value : 2 4 5 8
```

If:

```text
k = 3
```

Then:

```text
3rd largest = 4
```

Index:

```text
size - k = 4 - 3 = 1
```

So:

```java
list.get(1)
```

returns:

```text
4
```

---

# ⏱ Brute Force Complexity

## Time Complexity

### add()

Sorting every time:

```text
O(n log n)
```

---

## Space Complexity

```text
O(n)
```

because all elements are stored.

---

# ✅ Optimal Solution — Min Heap

---

# 💡 Main Idea

Maintain only the:

```text
k largest elements
```

using a:

```text
Min Heap
```

Whenever heap size becomes greater than `k`,
remove the smallest element.

Finally:

```text
heap top = kth largest element
```

---

# ✅ Optimal Code (Java)

```java
import java.util.*;

class KthLargest {

    PriorityQueue<Integer> pq = new PriorityQueue<>();
    int k;

    public KthLargest(int k, int[] nums) {

        this.k = k;

        for(int num : nums) {
            add(num);
        }
    }

    public int add(int val) {

        pq.add(val);

        if(pq.size() > k) {
            pq.poll();
        }

        return pq.peek();
    }
}
```

---

# 🔍 Optimal Explanation

---

# Step 1 — Min Heap

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
```

Java PriorityQueue is a:

```text
Min Heap
```

Meaning:

```text
smallest element stays at top
```

---

# Step 2 — Store k

```java
int k;
```

Needed inside both constructor and `add()`.

---

# Step 3 — Use add() Inside Constructor

```java
for(int num : nums) {
    add(num);
}
```

Instead of repeating heap logic,
we reuse the `add()` method.

This avoids code duplication.

---

# Step 4 — Add Element

```java
pq.add(val);
```

Insert value into heap.

---

# Step 5 — Remove Smallest if Size > k

```java
if(pq.size() > k) {
    pq.poll();
}
```

If heap grows larger than `k`,
remove smallest element.

So only:

```text
k largest elements survive
```

---

# Example Dry Run

## k = 3

Numbers:

```text
4,5,8,2
```

---

## Add 4

Heap:

```text
[4]
```

---

## Add 5

Heap:

```text
[4,5]
```

---

## Add 8

Heap:

```text
[4,5,8]
```

---

## Add 2

Heap:

```text
[2,4,5,8]
```

Size > 3

Remove smallest:

```text
remove 2
```

Heap:

```text
[4,5,8]
```

Now heap contains:

```text
3 largest elements
```

---

# Why `peek()`?

```java
return pq.peek();
```

Heap stores:

```text
k largest elements
```

Smallest among them is:

```text
kth largest element
```

And min heap top gives smallest element.

---

# 🔥 Main Intuition

We are NOT using min heap to store minimum values.

We use min heap because:

```text
it can quickly remove the smallest element
```

So smaller values get removed,
and larger values remain.

---

# ⏱ Optimal Complexity

## Constructor

For `n` elements:

```text
O(n log k)
```

---

## add()

Heap insertion/removal:

```text
O(log k)
```

---

## Space Complexity

```text
O(k)
```

because heap stores only `k` elements.

---

# 📊 Brute Force vs Optimal

| Method         | Time Complexity | Space Complexity |
| -------------- | --------------- | ---------------- |
| Brute Force    | O(n log n)      | O(n)             |
| Optimal (Heap) | O(log k)        | O(k)             |

---

# ✅ Key Interview Point

## Why Min Heap?

Because we need to:

```text
remove smallest element quickly
```

Whenever heap size exceeds `k`.

This ensures:

```text
only k largest elements remain
```

Then:

```text
heap top = kth largest
```

</p>
