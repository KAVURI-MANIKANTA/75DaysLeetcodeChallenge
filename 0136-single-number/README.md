<h2><a href="https://leetcode.com/problems/single-number">136. Single Number</a></h2><h3>Easy</h3><hr><p>Given a <strong>non-empty</strong>&nbsp;array of integers <code>nums</code>, every element appears <em>twice</em> except for one. Find that single one.</p>

<p>You must&nbsp;implement a solution with a linear runtime complexity and use&nbsp;only constant&nbsp;extra space.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,1,2,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-3 * 10<sup>4</sup> &lt;= nums[i] &lt;= 3 * 10<sup>4</sup></code></li>
	<li>Each element in the array appears twice except for one element which appears only once.</li>
</ul>
<p>
==============================================================================
---

# 🧠 Problem Understanding

You are given an array where:

* Every element appears **twice**
* Except **one element appears once**

👉 You need to find that **single element**

### Example:

```
Input: [2, 2, 1]
Output: 1
```

---

# 🔴 1. Brute Force (Check Each Element)

## 💡 Idea

For every element, count how many times it appears.
If it appears only once → return it.

---

## ✅ Code

```java
class Solution {
    public int singleNumber(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            int count = 0;

            for(int j = 0; j < nums.length; j++){
                if(nums[i] == nums[j]){
                    count++;
                }
            }

            if(count == 1){
                return nums[i];
            }
        }
        return -1;
    }
}
```

---

## 🧩 Explanation

* Outer loop picks one number at a time
* Inner loop counts its frequency
* If frequency = 1 → return that number

👉 Example:

```
nums = [4,1,2,1,2]

Check 4 → count = 1 → return 4
```

---

## ⏱ Complexity

* **Time Complexity:** O(n²)
* **Space Complexity:** O(1)

👉 Slow because of nested loops.

---

# 🟡 2. Sorting + Adjacent Comparison

## 💡 Idea

* Sort the array
* Equal elements will be **next to each other**
* The unique element will **break the pattern**

---

## ✅ Code

```java
import java.util.Arrays;

class Solution {
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 1; i += 2){
            if(nums[i] != nums[i + 1]){
                return nums[i];
            }
        }

        return nums[nums.length - 1];
    }
}
```

---

## 🧩 Explanation

After sorting:

```
[1,1,2,2,4]
```

* Compare pairs:

  * (1,1) ✔
  * (2,2) ✔
  * (4) ❌ → answer

👉 We jump `i += 2` because duplicates come in pairs.

---

## ⏱ Complexity

* **Time Complexity:** O(n log n) (sorting)
* **Space Complexity:** O(1) or O(log n) (depending on sort)

---

# 🟢 3. HashMap (Frequency Count)

## 💡 Idea

* Store frequency of each number
* Return the one with frequency = 1

---

## ✅ Code

```java
import java.util.HashMap;

class Solution {
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for(int num : nums){
            if(map.get(num) == 1){
                return num;
            }
        }

        return -1;
    }
}
```

---

## 🧩 Explanation

Step 1 → Build map:

```
[4,1,2,1,2]

Map:
4 → 1
1 → 2
2 → 2
```

Step 2 → Find element with count = 1 → **4**

---

## ⏱ Complexity

* **Time Complexity:** O(n)
* **Space Complexity:** O(n)

👉 Faster than brute force but uses extra memory.

---

# 🔵 4. XOR (Optimal Solution)

## 💡 Core Idea (VERY IMPORTANT 🔥)

XOR has special properties:

```
a ^ a = 0
a ^ 0 = a
```

👉 So:

```
2 ^ 2 = 0
1 ^ 0 = 1
```

---

## ✅ Code

```java
class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;

        for(int num : nums){
            result ^= num;
        }

        return result;
    }
}
```

---

## 🧩 Deep Explanation

Let’s take:

```
nums = [4,1,2,1,2]
```

Step by step:

```
result = 0

result = 0 ^ 4 = 4
result = 4 ^ 1 = 5
result = 5 ^ 2 = 7
result = 7 ^ 1 = 6
result = 6 ^ 2 = 4
```

👉 Final result = **4**

---

## 🔥 Why It Works

Because duplicates cancel out:

```
(1 ^ 1) = 0
(2 ^ 2) = 0
Remaining → 4
```

---

## ⏱ Complexity

* **Time Complexity:** O(n)
* **Space Complexity:** O(1)

👉 ✅ BEST SOLUTION (used in interviews)

---

# 🟣 Summary Comparison

| Approach      | Time Complexity | Space Complexity | Notes        |
| ------------- | --------------- | ---------------- | ------------ |
| Brute Force   | O(n²)           | O(1)             | Very slow    |
| Sorting       | O(n log n)      | O(1)             | Easy logic   |
| HashMap       | O(n)            | O(n)             | Extra memory |
| XOR (Optimal) | O(n)            | O(1)             | 🔥 Best      |

---

# 🚀 Interview Tips

* If interviewer hints **no extra space → use XOR**
* If you forget XOR → go with HashMap
* Always explain XOR properties clearly

---
</p>
