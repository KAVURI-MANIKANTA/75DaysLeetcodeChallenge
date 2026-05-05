<h2><a href="https://leetcode.com/problems/3sum-closest">16. 3Sum Closest</a></h2><h3>Medium</h3><hr><p>Given an integer array <code>nums</code> of length <code>n</code> and an integer <code>target</code>, find three integers at <strong>distinct indices</strong> in <code>nums</code> such that the sum is closest to <code>target</code>.</p>

<p>Return <em>the sum of the three integers</em>.</p>

<p>You may assume that each input would have exactly one solution.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,2,1,-4], target = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0,0], target = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong> The sum that is closest to the target is 0. (0 + 0 + 0 = 0).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 500</code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>-10<sup>4</sup> &lt;= target &lt;= 10<sup>4</sup></code></li>
</ul>
<p>
	
**LeetCode 16 – 3Sum Closest** 

---

## 🔹 Problem

Given an array `nums` and an integer `target`, find **3 numbers whose sum is closest to target**.

👉 Return the **sum**, not the triplet.

---

## 🔹 Example

```
nums = [-1, 2, 1, -4], target = 1

Output: 2
Explanation: (-1 + 2 + 1 = 2) → closest to 1
```

---

## 🔹 Brute Force Approach

### Idea:

Try **all possible triplets**.

### Code:

```java
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        int closest = nums[0]+nums[1]+nums[2];

        for(int i = 0; i < n-2; i++) {
            for(int j = i+1; j < n-1; j++) {
                for(int k = j+1; k < n; k++) {
                    int sum = nums[i] + nums[j] + nums[k];

                    if(Math.abs(target - sum) < Math.abs(target - closest)) {
                        closest = sum;
                    }
                }
            }
        }
        return closest;
    }
}
```

### Complexity:

* **Time:** O(n³) ❌ (too slow)
* **Space:** O(1)

---

## 🔹 Optimal Approach (Two Pointers) ✅

### 🔥 Key Idea:

1. **Sort the array**
2. Fix one element
3. Use **two pointers** to find best pair

---

### 🔹 Steps:

1. Sort `nums`
2. Loop `i` from `0 → n-3`
3. Set:

   * `left = i + 1`
   * `right = n - 1`
4. Calculate `sum = nums[i] + nums[left] + nums[right]`
5. Compare with target:

   * Update closest
   * If sum < target → move `left++`
   * If sum > target → move `right--`
   * If equal → return immediately

---

## 🔹 Optimal Code (Java)

```java
import java.util.*;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;

        int closest = nums[0] + nums[1] + nums[2];

        for(int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if(Math.abs(target - sum) < Math.abs(target - closest)) {
                    closest = sum;
                }

                if(sum < target) {
                    left++;
                } else if(sum > target) {
                    right--;
                } else {
                    return sum; // perfect match
                }
            }
        }
        return closest;
    }
}
```

---

## 🔹 Complexity

* **Time:** O(n²) ✅
* **Space:** O(1)

---

## 🔹 Dry Run (Important for Interviews)

```
nums = [-4, -1, 1, 2], target = 1

i = 0 → -4
left = -1, right = 2 → sum = -3
move left

left = 1, right = 2 → sum = -1
move left

i = 1 → -1
left = 1, right = 2 → sum = 2 ✅ closest
```

---

## 🔹 Important Tips

* Always **initialize closest properly**
* Use `Math.abs()` for comparison
* Sorting is **must**
* Early return when exact match found

---

## 🔹 Interview One-Line Explanation

> "Sort the array, fix one element, and use two pointers to find the closest sum in O(n²) time."

---
</p>
