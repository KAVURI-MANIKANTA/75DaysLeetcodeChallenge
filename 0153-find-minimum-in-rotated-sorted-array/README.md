<h2><a href="https://leetcode.com/problems/find-minimum-in-rotated-sorted-array">153. Find Minimum in Rotated Sorted Array</a></h2><h3>Medium</h3><hr><p>Suppose an array of length <code>n</code> sorted in ascending order is <strong>rotated</strong> between <code>1</code> and <code>n</code> times. For example, the array <code>nums = [0,1,2,4,5,6,7]</code> might become:</p>

<ul>
	<li><code>[4,5,6,7,0,1,2]</code> if it was rotated <code>4</code> times.</li>
	<li><code>[0,1,2,4,5,6,7]</code> if it was rotated <code>7</code> times.</li>
</ul>

<p>Notice that <strong>rotating</strong> an array <code>[a[0], a[1], a[2], ..., a[n-1]]</code> 1 time results in the array <code>[a[n-1], a[0], a[1], a[2], ..., a[n-2]]</code>.</p>

<p>Given the sorted rotated array <code>nums</code> of <strong>unique</strong> elements, return <em>the minimum element of this array</em>.</p>

<p>You must write an algorithm that runs in&nbsp;<code>O(log n) time</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,4,5,1,2]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The original array was [1,2,3,4,5] rotated 3 times.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,5,6,7,0,1,2]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [11,13,15,17]
<strong>Output:</strong> 11
<strong>Explanation:</strong> The original array was [11,13,15,17] and it was rotated 4 times. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 5000</code></li>
	<li><code>-5000 &lt;= nums[i] &lt;= 5000</code></li>
	<li>All the integers of <code>nums</code> are <strong>unique</strong>.</li>
	<li><code>nums</code> is sorted and rotated between <code>1</code> and <code>n</code> times.</li>
</ul>

<p>
	TIME AND SPACE COMPLEXITIES OF 3 DIFFERENT APPROACHES

## ✅ 1. Binary Search Solution

```java
// tc: O(log n)
```

✔ **Correct**

### Why?

* Each iteration cuts the search space into half
* Like: `n → n/2 → n/4 → ... → 1`

👉 Number of steps:
[
\log_2 n
]

✔ So:

* **Time Complexity:** `O(log n)`
* **Space Complexity:** `O(1)` (no extra space)

---

## ✅ 2. Linear Scan Solution

```java
// tc: O(n)
```

✔ **Correct**

### Why?

* You check every element once

👉 Worst case:

```text
[5,6,7,1,2,3,4]
```

You may scan all elements

✔ So:

* **Time Complexity:** `O(n)`
* **Space Complexity:** `O(1)`

---

## ✅ 3. Sorting Solution

```java
// tc: O(n log n)
```

✔ **Correct**

### Why?

* `Arrays.sort()` uses Dual-Pivot QuickSort (for primitives)

👉 Sorting takes:
[
O(n \log n)
]

Then:

* Accessing first element → `O(1)`

✔ So:

* **Time Complexity:** `O(n log n)`
* **Space Complexity:** `O(log n)` (due to recursion stack)

---

## 🔥 Interview Insight (IMPORTANT)

If interviewer asks:

👉 **“Which is best and why?”**

You should say:

* Binary Search (`O(log n)`) ✅ best
* Because:

  * Problem guarantees **sorted + rotated**
  * So we can optimize beyond linear scan

---
This line:

```java
if(nums[l] <= nums[r]) return nums[l];
```

👉 Good optimization
👉 Handles already sorted case in **O(1)**

---

## 🧠 Final Summary

| Approach      | Time Complexity | Best?  |
| ------------- | --------------- | ------ |
| Binary Search | `O(log n)`      | ✅ Best |
| Linear Scan   | `O(n)`          | ❌      |
| Sorting       | `O(n log n)`    | ❌      |


🟢 Final Clear Answer

| Code               | Space Complexity | Why                           |
| ------------------ | ---------------- | ----------------------------- |
| Your Binary Search | `O(1)` ✅         | No recursion, no extra memory |
| Linear Scan        | `O(1)` ✅         | Just a variable               |
| `Arrays.sort()`    | `O(log n)` ⚠️    | Uses recursion internally     |


</p>
