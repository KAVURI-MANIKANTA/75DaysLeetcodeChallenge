<h2><a href="https://leetcode.com/problems/squares-of-a-sorted-array">1019. Squares of a Sorted Array</a></h2><h3>Easy</h3><hr><p>Given an integer array <code>nums</code> sorted in <strong>non-decreasing</strong> order, return <em>an array of <strong>the squares of each number</strong> sorted in non-decreasing order</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-4,-1,0,3,10]
<strong>Output:</strong> [0,1,9,16,100]
<strong>Explanation:</strong> After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-7,-3,2,3,11]
<strong>Output:</strong> [4,9,9,49,121]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code><span>1 &lt;= nums.length &lt;= </span>10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>nums</code> is sorted in <strong>non-decreasing</strong> order.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Squaring each element and sorting the new array is very trivial, could you find an <code>O(n)</code> solution using a different approach?
<p>
	
LeetCode 977 — **Squares of a Sorted Array**

👉 Problem:
You are given a sorted array (can contain negative numbers). You need to return a new array where each element is squared and the result is still sorted.

---

# 🟡 1. Brute Force Approach

### 💡 Idea:

* Square every element
* Sort the result again

---

### ✅ Code (Brute Force - Java)

```java
import java.util.*;

class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Step 1: square each element
        for (int i = 0; i < n; i++) {
            result[i] = nums[i] * nums[i];
        }

        // Step 2: sort the squared array
        Arrays.sort(result);

        return result;
    }
}
```

---

### 🧠 Explanation:

1. First loop squares all numbers (negative becomes positive).
2. Then we sort the array because squaring breaks original order.
3. Sorting restores correct increasing order.

---

### ⏱ Time Complexity:

* Squaring: **O(n)**
* Sorting: **O(n log n)**
  👉 Total: **O(n log n)**

---

### 📦 Space Complexity:

* Output array: **O(n)**
  👉 Total: **O(n)**

---

# 🟢 2. Optimal Approach (Two Pointers)

### 💡 Key Insight:

* Largest square will come from either far left or far right (because negatives can become large positive after squaring).
* So we use two pointers.

---

### ✅ Code (Optimal - Java)

```java
class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        int left = 0;
        int right = n - 1;
        int pos = n - 1; // fill from back

        while (left <= right) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];

            if (leftSquare > rightSquare) {
                result[pos] = leftSquare;
                left++;
            } else {
                result[pos] = rightSquare;
                right--;
            }
            pos--;
        }

        return result;
    }
}
```

---

### 🧠 Explanation (Step-by-step):

1. Array is sorted but contains negatives.
2. Negative values on left can become large after squaring.
3. Compare both ends:

   * Left square vs Right square
4. Put bigger square at the end of result array.
5. Move pointers inward.

👉 We fill from **back to front** to maintain sorted order.

---

### 🔥 Why this works:

Largest squared values are always at extremes (left or right).

---

### ⏱ Time Complexity:

* Single pass through array: **O(n)**

---

### 📦 Space Complexity:

* Output array: **O(n)**
  👉 Total: **O(n)** (optimal, no extra sorting)

---

# ⚖️ Comparison

| Approach               | Time Complexity | Space Complexity | Idea          |
| ---------------------- | --------------- | ---------------- | ------------- |
| Brute Force            | O(n log n)      | O(n)             | Square + Sort |
| Optimal (Two Pointers) | O(n)            | O(n)             | Compare ends  |

---
</p>
