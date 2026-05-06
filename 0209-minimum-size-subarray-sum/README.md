<h2><a href="https://leetcode.com/problems/minimum-size-subarray-sum">209. Minimum Size Subarray Sum</a></h2><h3>Medium</h3><hr><p>Given an array of positive integers <code>nums</code> and a positive integer <code>target</code>, return <em>the <strong>minimal length</strong> of a </em><span data-keyword="subarray-nonempty"><em>subarray</em></span><em> whose sum is greater than or equal to</em> <code>target</code>. If there is no such subarray, return <code>0</code> instead.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> target = 7, nums = [2,3,1,2,4,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The subarray [4,3] has the minimal length under the problem constraint.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> target = 4, nums = [1,4,4]
<strong>Output:</strong> 1
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> target = 11, nums = [1,1,1,1,1,1,1,1]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= target &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> If you have figured out the <code>O(n)</code> solution, try coding another solution of which the time complexity is <code>O(n log(n))</code>.
<p>

**LeetCode 209 – Minimum Size Subarray Sum** 

---

# 🧩 Problem Summary

Given an array `nums` of **positive integers** and a target, find the **minimum length subarray** whose sum is **≥ target**.
If none exists → return `0`.

---

# 🐢 1. Brute Force Approach

## 💡 Idea

* Try **every possible subarray**
* Calculate sum each time
* Track minimum length

---

## ✅ Code (Brute Force)

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int minLen = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];

                if (sum >= target) {
                    minLen = Math.min(minLen, j - i + 1);
                    break; // no need to extend further
                }
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
```

---

## ⏱ Complexity

* **Time:** `O(n²)`
* **Space:** `O(1)`

---

## ❌ Drawback

* Too slow for large inputs (TLE in interviews/platforms)

---

# 🚀 2. Optimal Approach (Sliding Window)

## 💡 Key Observation

* All numbers are **positive**
  👉 So:
* Expanding window → sum increases
* Shrinking window → sum decreases

This allows **two pointers**

---

## 🧠 Idea

* Use window `[l … r]`
* Expand `r` → increase sum
* When `sum ≥ target`, shrink `l` to minimize length

---

## ✅ Code (Optimal)

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int l = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;

        for (int r = 0; r < nums.length; r++) {
            sum += nums[r];

            while (sum >= target) {
                minLen = Math.min(minLen, r - l + 1);
                sum -= nums[l];
                l++;
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
```

---

## 🔍 Dry Run (Quick)

### Input:

```
target = 11
nums = [1,2,3,4,5]
```

Window movement:

* Expand → sum = 15
* Shrink:

  * [1,2,3,4,5] → 5
  * [2,3,4,5] → 4
  * [3,4,5] → 3 ✅ (minimum)

---

## ⏱ Complexity

* **Time:** `O(n)`
  (each element visited at most twice)

* **Space:** `O(1)`

---

# ⚡ Why Sliding Window Works?

Because:

* Array has **only positive numbers**
* No need to re-calculate sums from scratch
* Window adjusts dynamically

---

# 🆚 Brute vs Optimal

| Approach       | Time Complexity | Space | Efficient |
| -------------- | --------------- | ----- | --------- |
| Brute Force    | O(n²)           | O(1)  | ❌         |
| Sliding Window | O(n)            | O(1)  | ✅         |

---

# 🎯 Interview Tip

Whenever you see:

* **Subarray**
* **Positive integers**
* **Condition like ≥, ≤**

👉 Think: **Sliding Window**

---
</p>
