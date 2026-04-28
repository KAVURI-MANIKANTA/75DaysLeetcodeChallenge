<h2><a href="https://leetcode.com/problems/majority-element">169. Majority Element</a></h2><h3>Easy</h3><hr><p>Given an array <code>nums</code> of size <code>n</code>, return <em>the majority element</em>.</p>

<p>The majority element is the element that appears more than <code>&lfloor;n / 2&rfloor;</code> times. You may assume that the majority element always exists in the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [3,2,3]
<strong>Output:</strong> 3
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [2,2,1,1,1,2,2]
<strong>Output:</strong> 2
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li>The input is generated such that a majority element will exist in the array.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow-up:</strong> Could you solve the problem in linear time and in <code>O(1)</code> space?
<p>
========================================================================================
**LeetCode 169: Majority Element**

---

# 🔹 Problem Understanding

You are given an integer array `nums` of size `n`.

👉 You need to find the **majority element**, i.e.

> the element that appears **more than ⌊n/2⌋ times**

### Example:

```
Input: nums = [2,2,1,1,1,2,2]
Output: 2
```

Why?

* `2` appears 4 times
* `n = 7`, so ⌊7/2⌋ = 3
* 4 > 3 → ✅ majority element

---

# 🔴 Brute Force Approach

## 💡 Idea

* For each element, count its frequency by scanning the whole array again.
* If any element appears more than `n/2`, return it.

---

## ✅ Code (Brute Force)

```java
class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length;

        for(int i = 0; i < n; i++){
            int count = 0;

            for(int j = 0; j < n; j++){
                if(nums[j] == nums[i]){
                    count++;
                }
            }

            if(count > n / 2){
                return nums[i];
            }
        }

        return -1; // never reached as majority element always exists
    }
}
```

---

## 🔍 Explanation (Step-by-step)

Take:

```
nums = [2,2,1,1,1,2,2]
```

### Iteration 1:

* `i = 0`, nums[i] = 2
* Count occurrences of 2:
  → Found 4 times
* Check: 4 > 3 → ✅ return 2

If not found, it would continue for next elements.

---

## ⏱️ Complexity

| Metric           | Value                    |
| ---------------- | ------------------------ |
| Time Complexity  | **O(n²)** (nested loops) |
| Space Complexity | **O(1)**                 |

---

## ❌ Drawbacks

* Very slow for large inputs
* Repeated counting of same elements

---

# 🟡 Better Approach (HashMap)

## 💡 Idea

* Use a HashMap to store frequency of each element.
* Return element when frequency exceeds `n/2`.

---

## ✅ Code (HashMap)

```java
import java.util.*;

class Solution {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int n = nums.length;

        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);

            if(map.get(num) > n / 2){
                return num;
            }
        }

        return -1;
    }
}
```

---

## 🔍 Explanation

For:

```
[2,2,1,1,1,2,2]
```

We build:

```
2 → 1
2 → 2
1 → 1
1 → 2
1 → 3
2 → 3
2 → 4 → exceeds 3 → return 2
```

---

## ⏱️ Complexity

| Metric           | Value    |
| ---------------- | -------- |
| Time Complexity  | **O(n)** |
| Space Complexity | **O(n)** |

---

## ❌ Drawback

* Uses extra memory

---

# 🟢 Optimal Approach (Boyer-Moore Voting Algorithm)

👉 This is the **MOST IMPORTANT** (very common interview question)

---

## 💡 Core Idea

Think like this:

* Majority element appears more than half → it will **survive elimination**
* Cancel out different elements

---

## ⚙️ Algorithm

1. Take:

   * `candidate`
   * `count = 0`

2. Traverse array:

   * If `count == 0` → set `candidate = nums[i]`
   * If element == candidate → `count++`
   * Else → `count--`

---

## ✅ Code (Optimal)

```java
class Solution {
    public int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;

        for(int num : nums){
            if(count == 0){
                candidate = num;
            }

            if(num == candidate){
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }
}
```

---

## 🔍 Detailed Dry Run

```
nums = [2,2,1,1,1,2,2]
```

| Step | num | candidate | count |
| ---- | --- | --------- | ----- |
| 1    | 2   | 2         | 1     |
| 2    | 2   | 2         | 2     |
| 3    | 1   | 2         | 1     |
| 4    | 1   | 2         | 0     |
| 5    | 1   | 1         | 1     |
| 6    | 2   | 1         | 0     |
| 7    | 2   | 2         | 1     |

Final Answer → **2**

---

## 🧠 Intuition (VERY IMPORTANT)

* Every time we see a different element → we cancel one occurrence
* Majority element is **more than half**, so it cannot be fully canceled
* It will remain as the final candidate

---

## ⚠️ Important Note

This works because:

> Problem guarantees that majority element **always exists**

If NOT guaranteed → we must verify candidate again.

---

## ⏱️ Complexity

| Metric           | Value    |
| ---------------- | -------- |
| Time Complexity  | **O(n)** |
| Space Complexity | **O(1)** |

---

# 🔥 Final Comparison

| Approach    | Time  | Space | Notes                    |
| ----------- | ----- | ----- | ------------------------ |
| Brute Force | O(n²) | O(1)  | Simple but slow          |
| HashMap     | O(n)  | O(n)  | Easy, uses memory        |
| Boyer-Moore | O(n)  | O(1)  | Best, interview favorite |

---

# 🧠 Interview Tips

* If interviewer asks:

  * First → explain brute force
  * Then → HashMap
  * Finally → Boyer-Moore (impresses interviewer)

* Keywords to mention:

  * "Cancellation logic"
  * "Majority survives elimination"
  * "Greedy + Voting algorithm"

---
</p>
