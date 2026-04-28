<h2><a href="https://leetcode.com/problems/majority-element-ii">229. Majority Element II</a></h2><h3>Medium</h3><hr><p>Given an integer array of size <code>n</code>, find all elements that appear more than <code>&lfloor; n/3 &rfloor;</code> times.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,3]
<strong>Output:</strong> [3]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1]
<strong>Output:</strong> [1]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2]
<strong>Output:</strong> [1,2]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you solve the problem in linear time and in <code>O(1)</code> space?</p>
<p>
---
	
**LeetCode 229 (Majority Element II)**

---

# 🔴 Brute Force Idea

👉 For **every element**, count how many times it appears
👉 If count **> n/3**, add it to result
👉 Make sure **no duplicates** in result

---

# ✅ Code (Brute Force)

```java
import java.util.*;

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();

        for(int i = 0; i < n; i++){
            int count = 0;

            // count frequency of nums[i]
            for(int j = 0; j < n; j++){
                if(nums[j] == nums[i]){
                    count++;
                }
            }

            // check majority condition and avoid duplicates
            if(count > n/3 && !res.contains(nums[i])){
                res.add(nums[i]);
            }
        }

        return res;
    }
}
```

---

# 🔍 Explanation (Step-by-step)

Take your example:

```java
nums = [1,2,3,1,2,3,1,2,3]
n = 9 → n/3 = 3
```

---

### Iteration 1:

* `i = 0`, element = 1
* Count how many 1’s:
  → 3 times
* Check:

```text
3 > 3 → false
```

❌ Not added

---

### Iteration 2:

* `i = 1`, element = 2
* Count = 3

```text
3 > 3 → false
```

❌ Not added

---

### Iteration 3:

* `i = 2`, element = 3
* Count = 3

```text
3 > 3 → false
```

❌ Not added

---

### Remaining iterations:

* Same counts repeat
* Nothing satisfies condition

---

# ✅ Final Result

```java
[]
```

---

# 🔁 One More Example

```java
nums = [1,1,1,2,2,3,3]
n = 7 → n/3 = 2
```

---

### i = 0 → element = 1

* Count = 3

```text
3 > 2 → true
```

✅ Add → `[1]`

---

### i = 3 → element = 2

* Count = 2

```text
2 > 2 → false
```

❌ Skip

---

### i = 5 → element = 3

* Count = 2

```text
2 > 2 → false
```

❌ Skip

---

# ✅ Final Result

```java
[1]
```

---

# ⏱️ Complexity

| Metric           | Value                            |
| ---------------- | -------------------------------- |
| Time Complexity  | **O(n²)** (two loops)            |
| Space Complexity | **O(1)** (excluding output list) |

---

# ⚠️ Important Notes

* `> n/3` → strictly greater (not ≥)
* `res.contains()` → avoids duplicates
* Works for all cases but **slow**

---



---

# ✅ HashMap solution

👉 Only add the element **once**

```java
import java.util.*;

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        HashMap<Integer,Integer> hm = new HashMap<>();

        for(int num : nums){
            hm.put(num, hm.getOrDefault(num, 0) + 1);

            if(hm.get(num) > (n / 3) && !res.contains(num)){
                res.add(num);
            }
        }

        return res;
    }
}
```

---

## ⚠️ Note

* `res.contains(num)` prevents duplicates
* But this is **not optimal**

---

# 🟡 Better Approach (HashMap clean way)

👉 First count, then check

```java
import java.util.*;

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        // count frequency
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer> res = new ArrayList<>();

        // check condition
        for(int key : map.keySet()){
            if(map.get(key) > n / 3){
                res.add(key);
            }
        }

        return res;
    }
}
```

---

## ⏱️ Complexity

| Metric | Value |
| ------ | ----- |
| Time   | O(n)  |
| Space  | O(n)  |

---

# 🟢 Optimal Approach (Boyer-Moore for n/3)

🔥 **Most important for interviews**

---

## 💡 Key Concept

For `n/3`:

* There can be **at most 2 majority elements**

👉 Why?
If 3 elements appear more than n/3 → impossible (sum > n)

---

## ⚙️ Algorithm

Maintain:

* `candidate1`, `candidate2`
* `count1`, `count2`

---

## ✅ Code (Optimal)

```java
import java.util.*;

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int count1 = 0, count2 = 0;
        int candidate1 = 0, candidate2 = 0;

        // Step 1: Find candidates
        for(int num : nums){
            if(num == candidate1){
                count1++;
            } else if(num == candidate2){
                count2++;
            } else if(count1 == 0){
                candidate1 = num;
                count1 = 1;
            } else if(count2 == 0){
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        // Step 2: Verify candidates
        count1 = 0;
        count2 = 0;

        for(int num : nums){
            if(num == candidate1) count1++;
            else if(num == candidate2) count2++;
        }

        List<Integer> res = new ArrayList<>();
        int n = nums.length;

        if(count1 > n/3) res.add(candidate1);
        if(count2 > n/3) res.add(candidate2);

        return res;
    }
}
```

---

## 🔍 Intuition (VERY IMPORTANT)

* Similar to LeetCode 169, but:

  * There can be **2 winners**
* We “cancel out” 3 different elements at a time
* Only strong candidates survive

---

## ⏱️ Complexity

| Metric | Value    |
| ------ | -------- |
| Time   | **O(n)** |
| Space  | **O(1)** |

---

# 🔥 Final Comparison

| Approach          |    Time    |    Space    |    Notes                |
| ----------------- |    ----    |    -----    |    -----------------    |
| **Your approach** |    O(n) 	 | 	  O(n)     |    ❌ duplicate issue   |
| **Fixed HashMap** |    O(n) 	 |    O(n)     |    ✅ correct           |
| **Boyer-Moore**   |    O(n) 	 | 	**O(1)**   |    🚀 **best**          |

---

# 🧠 Interview Insight

👉 If interviewer says:

* “Can you optimize space?”
  → Immediately go to **Boyer-Moore (2 candidates)**

---


**full, interview-level dry run** of the **Boyer–Moore Voting Algorithm (n/3 version)** for **LeetCode 229**.

---

# 🔹 Problem Reminder

👉 Find all elements that appear **more than ⌊n/3⌋ times**

* Max possible answers = **2 elements**

---

# 🧠 Algorithm Structure

We do **2 passes**:

### ✅ Pass 1 → Find candidates

### ✅ Pass 2 → Verify them

---

# 🔥 Example for Full Dry Run

```java
nums = [1,2,3,1,2,1,1]
n = 7 → n/3 = 2
```

👉 Elements appearing more than 2 times:

* `1 → 4 times` ✅
* `2 → 2 times` ❌ (not >2)

👉 Final Answer = `[1]`

---

# 🔵 PASS 1: Find Candidates

We maintain:

```java
candidate1, candidate2
count1 = 0, count2 = 0
```

---

## Step-by-step Table

| Step | num | candidate1 | count1 | candidate2 | count2 | Explanation                          |
| ---- | --- | ---------- | ------ | ---------- | ------ | ------------------------------------ |
| 1    | 1   | 1          | 1      | -          | 0      | count1=0 → set candidate1            |
| 2    | 2   | 1          | 1      | 2          | 1      | count2=0 → set candidate2            |
| 3    | 3   | 1          | 0      | 2          | 0      | different from both → decrement both |
| 4    | 1   | 1          | 1      | 2          | 0      | count1=0 → reset candidate1          |
| 5    | 2   | 1          | 1      | 2          | 1      | count2=0 → reset candidate2          |
| 6    | 1   | 1          | 2      | 2          | 1      | matches candidate1                   |
| 7    | 1   | 1          | 3      | 2          | 1      | matches candidate1                   |

---

## 🔍 What just happened?

* We **cancel out groups of 3 different elements**
* Weak elements get eliminated
* Strong elements (frequent ones) survive

---

## 🟢 After Pass 1

```java
candidate1 = 1
candidate2 = 2
```

⚠️ These are just **possible candidates**, not guaranteed answers

---

# 🟡 PASS 2: Verification

Now count actual frequencies:

```java
count1 = 0
count2 = 0
```

Traverse again:

* Count `1 → 4 times`
* Count `2 → 2 times`

---

## ✅ Final Check

```java
if(count1 > n/3) → 4 > 2 → add 1
if(count2 > n/3) → 2 > 2 → false
```

---

# 🎯 Final Answer

```java
[1]
```

---

# 🔥 Another Quick Example (2 answers case)

```java
nums = [1,1,1,3,3,2,2,2]
n = 8 → n/3 = 2
```

### Frequencies:

* 1 → 3 times ✅
* 2 → 3 times ✅
* 3 → 2 times ❌

👉 Answer = `[1,2]`

---

## 🧠 Key Intuition (VERY IMPORTANT)

### Why at most 2 elements?

Assume 3 elements appear more than n/3:

```text
> n/3 + n/3 + n/3 = n
```

👉 Impossible → exceeds array size

So:

> Only **2 majority elements max**

---

## 💡 Why decrement both counts?

When we see a new element:

```java
count1--
count2--
```

👉 Means:

* We are removing a **group of 3 different elements**
* This does NOT affect majority element dominance

---

# 🧠 Visualization Trick

Think like this:

```
[1,2,3] → cancel out
[1,2,3] → cancel out
Remaining → only strong elements survive
```

---

# ⚠️ Common Mistakes

❌ Forgetting second pass
❌ Returning candidates directly
❌ Not handling count reset properly
❌ Using only one candidate (wrong for n/3)

---

---

# 🔥 Final Takeaway

👉 Boyer-Moore (n/3):

* Maintains **2 candidates**
* Cancels out weak elements
* Needs **verification pass**
* **O(n) time, O(1) space**

---





# Example 2
```java
nums = [1,2,3,1,2,3,1,2,3]
n = 9 → n/3 = 3
```

Count frequencies:

* 1 → 3 times
* 2 → 3 times
* 3 → 3 times

Condition:

```text
must be strictly > n/3 → > 3
```

But all are:

```text
= 3 (not greater)
```

👉 So none satisfy the condition.

---

# ✅ Final Answer

```java
[]
```
</p>
