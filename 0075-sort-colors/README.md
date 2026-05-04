<h2><a href="https://leetcode.com/problems/sort-colors">75. Sort Colors</a></h2><h3>Medium</h3><hr><p>Given an array <code>nums</code> with <code>n</code> objects colored red, white, or blue, sort them <strong><a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank">in-place</a> </strong>so that objects of the same color are adjacent, with the colors in the order red, white, and blue.</p>

<p>We will use the integers <code>0</code>, <code>1</code>, and <code>2</code> to represent the color red, white, and blue, respectively.</p>

<p>You must solve this problem without using the library&#39;s sort function.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,0,2,1,1,0]
<strong>Output:</strong> [0,0,1,1,2,2]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,0,1]
<strong>Output:</strong> [0,1,2]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 300</code></li>
	<li><code>nums[i]</code> is either <code>0</code>, <code>1</code>, or <code>2</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong>&nbsp;Could you come up with a one-pass algorithm using only&nbsp;constant extra space?</p>
<p>

**LeetCode 75 – Sort Colors** 

---

# 🟥 Problem Summary

Given an array containing only `0`, `1`, `2`
👉 Sort it **in-place** (no library sort ideally)

---

# 🔴 1. Brute Force Approach (Sorting)

## 💡 Idea

Just use sorting (like Arrays.sort)

## ✅ Code

```java
class Solution {
    public void sortColors(int[] nums) {
        Arrays.sort(nums);
    }
}
```

## 🧠 Explanation

* Sorting rearranges elements in ascending order
* Works because only 3 values exist

## ⏱ Complexity

* **Time Complexity:** `O(n log n)`
* **Space Complexity:** `O(1)` (for primitive arrays, Java uses in-place Dual-Pivot QuickSort)

## ❌ Drawback

* Not optimal
* Doesn’t use problem constraint (only 3 numbers)

---

# 🟡 2. Better Approach (Counting Sort)

## 💡 Idea

Count number of 0s, 1s, 2s and overwrite array

## ✅ Code

```java
class Solution {
    public void sortColors(int[] nums) {
        int count0 = 0, count1 = 0, count2 = 0;

        for (int num : nums) {
            if (num == 0) count0++;
            else if (num == 1) count1++;
            else count2++;
        }

        int i = 0;

        while (count0-- > 0) nums[i++] = 0;
        while (count1-- > 0) nums[i++] = 1;
        while (count2-- > 0) nums[i++] = 2;
    }
}
```

## 🧠 Explanation

* First pass → count frequencies
* Second pass → fill values

## ⏱ Complexity

* **Time Complexity:** `O(n)`
* **Space Complexity:** `O(1)`

## ⚠️ Note

* Not truly “one-pass”
* Requires 2 passes

---

# 🟢 3. Optimal Approach (Dutch National Flag Algorithm)

## 💡 Idea

Use 3 pointers:

* `l` → next position of 0
* `r` → next position of 2
* `i` → current element

---

## ✅ Code

```java
class Solution {
    public void sortColors(int[] nums) {
        int l = 0, i = 0;
        int r = nums.length - 1;

        while (i <= r) {
            if (nums[i] == 0) {
                int temp = nums[l];
                nums[l] = nums[i];
                nums[i] = temp;
                l++;
                i++;
            } 
            else if (nums[i] == 2) {
                int temp = nums[r];
                nums[r] = nums[i];
                nums[i] = temp;
                r--;
            } 
            else {
                i++;
            }
        }
    }
}
```

---

## 🧠 Explanation (Important for Interviews)

### Maintain 3 regions:

```
[0 ... l-1] → all 0s
[l ... i-1] → all 1s
[i ... r]   → unknown
[r+1 ... n-1] → all 2s
```

---

### 🔄 Cases

### 1️⃣ If `nums[i] == 0`

👉 Swap with `l`, move both `l++` and `i++`

### 2️⃣ If `nums[i] == 2`

👉 Swap with `r`, move only `r--`
❗ Don’t move `i` (re-check needed)

### 3️⃣ If `nums[i] == 1`

👉 Just `i++`

---

## ⏱ Complexity

* **Time Complexity:** `O(n)` (single pass)
* **Space Complexity:** `O(1)`

---

# ⚖️ Comparison Table

| Approach      | Time       | Space | Passes | Interview Value |
| ------------- | ---------- | ----- | ------ | --------------- |
| Brute (Sort)  | O(n log n) | O(1)  | 1      | ❌ Low           |
| Counting      | O(n)       | O(1)  | 2      | ⚠️ Medium       |
| Optimal (DNF) | O(n)       | O(1)  | 1      | ✅ High          |

---

# 🔥 Final Interview Tip

👉 If interviewer says:

* “Can you do it in one pass?”
  → Use **Dutch National Flag**

👉 If stuck:

* Start with **counting approach**, then optimize

---
</p>
