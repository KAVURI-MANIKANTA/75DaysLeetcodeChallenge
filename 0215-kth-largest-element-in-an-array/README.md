<h2><a href="https://leetcode.com/problems/kth-largest-element-in-an-array">215. Kth Largest Element in an Array</a></h2><h3>Medium</h3><hr><p>Given an integer array <code>nums</code> and an integer <code>k</code>, return <em>the</em> <code>k<sup>th</sup></code> <em>largest element in the array</em>.</p>

<p>Note that it is the <code>k<sup>th</sup></code> largest element in the sorted order, not the <code>k<sup>th</sup></code> distinct element.</p>

<p>Can you solve it without sorting?</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [3,2,1,5,6,4], k = 2
<strong>Output:</strong> 5
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [3,2,3,1,2,4,5,5,6], k = 4
<strong>Output:</strong> 4
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>
<p>
	
# LeetCode 215 — Kth Largest Element in an Array

LeetCode 215

Given an integer array `nums` and an integer `k`, return the `kth` largest element in the array.

---

# 1. Brute Force Solution (Sorting)

## Idea

* Sort the array in ascending order.
* The kth largest element will be at index:

[
n - k
]

because arrays use 0-based indexing.

---

# Brute Force Code (Java)

```java
import java.util.*;

class Solution {
    public int findKthLargest(int[] nums, int k) {

        Arrays.sort(nums);

        return nums[nums.length - k];
    }
}
```

---

# Dry Run

```text
nums = [3,2,1,5,6,4]
k = 2
```

After sorting:

```text
[1,2,3,4,5,6]
```

`nums.length - k`

```text
6 - 2 = 4
```

Element at index `4`:

```text
5
```

Answer = `5`

---

# Brute Force Explanation

## Step-by-step

1. Sort the array.
2. Largest element is at last index.
3. 2nd largest is before last.
4. kth largest is at index `n-k`.
5. Return that element.

---

# Time Complexity

## Sorting takes:

[
O(n \log n)
]

---

# Space Complexity

Java sorting uses:
[
O(\log n)
]
(auxiliary stack space)

---

# 2. Optimal Solution — Min Heap (Priority Queue)

## Idea

Use a **Min Heap** of size `k`.

* Keep only the `k` largest elements in heap.
* Smallest among them will be the kth largest element.

---

# Why Min Heap?

Suppose:

```text
nums = [3,2,1,5,6,4]
k = 2
```

We only need the 2 largest numbers.

Heap should finally contain:

```text
[5,6]
```

Top of min heap:

```text
5
```

which is the 2nd largest.

---

# Optimal Code (Priority Queue)

```java
import java.util.*;

class Solution {
    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int num : nums) {

            pq.offer(num);

            // Keep heap size only k
            if(pq.size() > k) {
                pq.poll();
            }
        }

        return pq.peek();
    }
}
```

---

# Dry Run

```text
nums = [3,2,1,5,6,4]
k = 2
```

---

## Step 1

Add `3`

Heap:

```text
[3]
```

---

## Step 2

Add `2`

Heap:

```text
[2,3]
```

---

## Step 3

Add `1`

Heap:

```text
[1,3,2]
```

Size > k

Remove smallest:

```text
poll() -> 1
```

Heap:

```text
[2,3]
```

---

## Step 4

Add `5`

Heap:

```text
[2,3,5]
```

Remove smallest:

```text
2
```

Heap:

```text
[3,5]
```

---

## Step 5

Add `6`

Heap:

```text
[3,5,6]
```

Remove smallest:

```text
3
```

Heap:

```text
[5,6]
```

---

## Step 6

Add `4`

Heap:

```text
[4,6,5]
```

Remove smallest:

```text
4
```

Heap:

```text
[5,6]
```

---

Now heap top:

```text
5
```

Answer = `5`

---

# Why Does This Work?

Heap always stores only the `k` largest elements.

Whenever size becomes greater than `k`:

* remove smallest element.

At the end:

* smallest element in heap
  = kth largest in array.

---

# Time Complexity

For each element:

* insertion = `O(log k)`
* deletion = `O(log k)`

Total:
[
O(n \log k)
]

---

# Space Complexity

Heap stores only `k` elements:
[
O(k)
]

---

# Comparison

| Method   | Time Complexity | Space Complexity |
| -------- | --------------- | ---------------- |
| Sorting  | O(n log n)      | O(log n)         |
| Min Heap | O(n log k)      | O(k)             |

---

# Which is Better?

## Sorting

* Easy to write.
* Good for small inputs.

## Min Heap

* Better when `k` is small.
* More optimal for large arrays.

---

# Important Interview Point

## Default PriorityQueue in Java

Java `PriorityQueue` is:

* **Min Heap by default**

Example:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
```

Smallest element stays at top.

---

# Max Heap Syntax (Extra)

```java
PriorityQueue<Integer> maxHeap =
    new PriorityQueue<>(Collections.reverseOrder());
```

But for this problem:

* Min Heap is more efficient.

</p>
