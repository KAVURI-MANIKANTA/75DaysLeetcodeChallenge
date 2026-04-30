<h2><a href="https://leetcode.com/problems/longest-consecutive-sequence">128. Longest Consecutive Sequence</a></h2><h3>Medium</h3><hr><p>Given an unsorted array of integers <code>nums</code>, return <em>the length of the longest consecutive elements sequence.</em></p>

<p>You must write an algorithm that runs in&nbsp;<code>O(n)</code>&nbsp;time.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [100,4,200,1,3,2]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The longest consecutive elements sequence is <code>[1, 2, 3, 4]</code>. Therefore its length is 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,3,7,2,5,8,4,6,0,1]
<strong>Output:</strong> 9
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,0,1,2]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>
<p>
---

LeetCode 128 — **Longest Consecutive Sequence** key idea is to find the longest sequence of consecutive integers in an unsorted array.
---

# 🟢 Problem Idea

Given an array `nums`, find the **longest sequence of consecutive numbers**.

Example:

```
Input: [100, 4, 200, 1, 3, 2]
Output: 4  → (1,2,3,4)
```

---

# 🔴 1. Brute Force Approach

## 💡 Idea

For every element, try to build the sequence by checking:

* num + 1
* num + 2
* num + 3 ...
  using linear search.

---

## 🧠 Code

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        int n = nums.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            int current = nums[i];
            int length = 1;

            while (linearSearch(nums, current + 1)) {
                current++;
                length++;
            }

            maxLen = Math.max(maxLen, length);
        }

        return maxLen;
    }

    private boolean linearSearch(int[] nums, int target) {
        for (int num : nums) {
            if (num == target) return true;
        }
        return false;
    }
}
```

---

## ⚙️ Logic

* Start from each element
* Keep searching next consecutive number using full array scan
* Count sequence length

---

## ⏱ Complexity

* Time: **O(n³)** (n elements × n search × sequence traversal)
* Space: **O(1)**

---

## ❌ Why bad?

* Repeated scanning makes it very slow
* Works only for small inputs

---

# 🟡 2. Better Approach (Sorting)

## 💡 Idea

Sort the array → consecutive elements become adjacent.

Then just scan once.

---

## 🧠 Code

```java
import java.util.Arrays;

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;

        Arrays.sort(nums);

        int maxLen = 1;
        int currentLen = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                continue; // skip duplicates
            }

            if (nums[i] == nums[i - 1] + 1) {
                currentLen++;
            } else {
                maxLen = Math.max(maxLen, currentLen);
                currentLen = 1;
            }
        }

        maxLen = Math.max(maxLen, currentLen);
        return maxLen;
    }
}
```

---

## ⚙️ Logic

* Sort array
* If consecutive → increase count
* If not → reset count
* Ignore duplicates

---

## ⏱ Complexity

* Time: **O(n log n)** (sorting dominates)
* Space: **O(1)** or **O(n)** depending on sort

---

## 👍 Advantages

* Much simpler than brute force
* Easy to implement

---

## 👎 Disadvantage

* Sorting is still expensive compared to optimal solution

---

# 🟢 3. Most Optimal Approach (HashSet / HashMap style)

Your approach is close to this idea 👍

## 💡 Key Idea (VERY IMPORTANT)

Instead of checking every element repeatedly:

👉 Only start counting if it is the **start of a sequence**

A number is a start if:

```
num - 1 does NOT exist
```

---

## 🧠 Correct Optimal Code (Best Version)

```java
import java.util.HashSet;

class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int maxLen = 0;

        for (int num : set) {

            // only start from beginning of sequence
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int length = 1;

                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    length++;
                }

                maxLen = Math.max(maxLen, length);
            }
        }

        return maxLen;
    }
}
```

---

## ⚙️ Logic (VERY IMPORTANT CONCEPT)

### Step 1: Put all numbers in HashSet

Fast lookup O(1)

---

### Step 2: Find sequence starts

Only start when:

```
num - 1 is NOT present
```

Example:

```
[1,2,3,4,100]

Start points: 1, 100
Skip: 2,3,4 (because they are middle)
```

---

### Step 3: Expand sequence

From start:

```
1 → 2 → 3 → 4
length = 4
```

---

## ⏱ Complexity

* Time: **O(n)** (each element visited once)
* Space: **O(n)** (HashSet)

---

## 🚀 Why this is BEST?

* No sorting
* No repeated scanning
* Each number processed only once

---

# ⚠️ Your Code vs Optimal Fix

Your code idea:

* tries expanding both directions (left + right)
* but marks visited → unnecessary complexity risk

### Problem:

```
You expand from every number → repeated work
```

### Optimal fix:

```
Only expand from sequence start → no repetition
```

---

# 📊 Final Comparison

| Approach          | Time       | Space | Idea                          |
| ----------------- | ---------- | ----- | ----------------------------- |
| Brute Force       | O(n³)      | O(1)  | Check every sequence manually |
| Sorting           | O(n log n) | O(1)  | Sort + scan                   |
| HashSet (Optimal) | O(n)       | O(n)  | Expand only from start        |

---

# 🧠 One-Line Intuition

👉 “Instead of searching everywhere, only start from numbers that are the beginning of a sequence.”

---
</p>
