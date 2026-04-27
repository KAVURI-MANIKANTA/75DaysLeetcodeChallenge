<h2><a href="https://leetcode.com/problems/minimum-recolors-to-get-k-consecutive-black-blocks">2463. Minimum Recolors to Get K Consecutive Black Blocks</a></h2><h3>Easy</h3><hr><p>You are given a <strong>0-indexed</strong> string <code>blocks</code> of length <code>n</code>, where <code>blocks[i]</code> is either <code>&#39;W&#39;</code> or <code>&#39;B&#39;</code>, representing the color of the <code>i<sup>th</sup></code> block. The characters <code>&#39;W&#39;</code> and <code>&#39;B&#39;</code> denote the colors white and black, respectively.</p>

<p>You are also given an integer <code>k</code>, which is the desired number of <strong>consecutive</strong> black blocks.</p>

<p>In one operation, you can <strong>recolor</strong> a white block such that it becomes a black block.</p>

<p>Return<em> the <strong>minimum</strong> number of operations needed such that there is at least <strong>one</strong> occurrence of </em><code>k</code><em> consecutive black blocks.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> blocks = &quot;WBBWWBBWBW&quot;, k = 7
<strong>Output:</strong> 3
<strong>Explanation:</strong>
One way to achieve 7 consecutive black blocks is to recolor the 0th, 3rd, and 4th blocks
so that blocks = &quot;BBBBBBBWBW&quot;. 
It can be shown that there is no way to achieve 7 consecutive black blocks in less than 3 operations.
Therefore, we return 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> blocks = &quot;WBWBBBW&quot;, k = 2
<strong>Output:</strong> 0
<strong>Explanation:</strong>
No changes need to be made, since 2 consecutive black blocks already exist.
Therefore, we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == blocks.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>blocks[i]</code> is either <code>&#39;W&#39;</code> or <code>&#39;B&#39;</code>.</li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>
<p>
=====================================================================================
**LeetCode 2379 Minimum Recolors to Get K Consecutive Black Blocks**.

---

# 🧾 1. Understanding the Problem (Very Clearly)

You are given:

* A string `blocks` → contains `'B'` (black) and `'W'` (white)
* An integer `k`

👉 You need:

> **Minimum number of recolors (W → B)** so that there exists **at least one substring of length k** consisting of all `'B'`.

---

## 🔑 Key Observation

* You don’t need to convert the entire string
* You only need **one window of size k**
* Inside that window:

  * Count how many `'W'` are there
  * Those many need recoloring

👉 So the problem becomes:

> Find a substring of size `k` with **minimum number of 'W'**

---

# 🐢 2. Brute Force Solution

## 💡 Idea

* Check every substring of size `k`
* Count number of `'W'` in each substring
* Take minimum

---

## 💻 Code (Brute Force)

```java
class Solution {
    public int minimumRecolors(String blocks, int k) {
        int n = blocks.length();
        int min = Integer.MAX_VALUE;

        for(int i = 0; i <= n - k; i++){
            int w = 0;

            // check window [i, i+k-1]
            for(int j = i; j < i + k; j++){
                if(blocks.charAt(j) == 'W'){
                    w++;
                }
            }

            min = Math.min(min, w);
        }

        return min;
    }
}
```

---

## 🧠 Explanation (Step-by-Step)

* Outer loop → selects starting index of window
* Inner loop → counts whites in that window
* For each window:

  * Compute recolors needed
* Track minimum

---

## ⏱ Complexity

* **Time:** O(n * k)
* **Space:** O(1)

---

## ❌ Problem

* Recalculates same window repeatedly
* Not efficient for large inputs

---

# ⚡ 3. Optimal Solution (Sliding Window)

## 💡 Core Idea

Instead of recomputing:

* Reuse previous window result

👉 When window moves:

* One character **goes out**
* One character **comes in**

---

# 🧠 Your First Code Explanation (Detailed)
```
class Solution {
    public int minimumRecolors(String blocks, int k) {
        int b = 0;
        int w = 0;
        int n = blocks.length();

        // first window
        for(int i = 0; i < k; i++){
            char ch = blocks.charAt(i);
            if(ch == 'B'){
                b++;
            } else {
                w++;
            }
        }

        int min = w; // ✅ FIX: initialize with first window

        // sliding window
        for(int i = k; i < n; i++){
            char newChar = blocks.charAt(i);
            char oldChar = blocks.charAt(i - k);

            if(oldChar == 'W') w--;
            else b--;

            if(newChar == 'W') w++;
            else b++;

            min = Math.min(min, w);
        }

        return min;
    }
}
```

```java
class Solution {
    public int minimumRecolors(String blocks, int k) {
        int b = 0;
        int w = 0;
        int n = blocks.length();
```

### 🔹 Variables

* `b` → count of black blocks in window
* `w` → count of white blocks in window
* `n` → length of string

---

## 🔹 Step 1: Build First Window

```java
for(int i = 0; i < k; i++){
    char ch = blocks.charAt(i);
    if(ch == 'B'){
        b++;
    } else {
        w++;
    }
}
```

👉 You count:

* How many `'B'`
* How many `'W'`

---

## 🔹 Step 2: Initialize Answer

```java
int min = w;
```

👉 Very important:

* First window is also a valid answer
* So initialize with its `'W'` count

---

## 🔹 Step 3: Slide the Window

```java
for(int i = k; i < n; i++){
    char newChar = blocks.charAt(i);
    char oldChar = blocks.charAt(i - k);
```

👉 Window moves:

* `oldChar` → removed
* `newChar` → added

---

## 🔹 Step 4: Remove Old Character

```java
if(oldChar == 'W') w--;
else b--;
```

👉 If outgoing char is:

* `'W'` → reduce white count
* `'B'` → reduce black count

---

## 🔹 Step 5: Add New Character

```java
if(newChar == 'W') w++;
else b++;
```

👉 If incoming char is:

* `'W'` → increase white count
* `'B'` → increase black count

---

## 🔹 Step 6: Update Answer

```java
min = Math.min(min, w);
```

👉 We only care about `'W'`

* Because those need recoloring

---

## 🔹 Step 7: Return Result

```java
return min;
```

---

## ⏱ Complexity

* **Time:** O(n)
* **Space:** O(1)

---

# ⚡ 4. Your Second Code (Optimized Version)

```
class Solution {
    public int minimumRecolors(String blocks, int k) {
        int w = 0;

        for(int i = 0; i < k; i++){
            if(blocks.charAt(i) == 'W') w++;
        }

        int min = w;

        for(int i = k; i < blocks.length(); i++){
            if(blocks.charAt(i - k) == 'W') w--;
            if(blocks.charAt(i) == 'W') w++;
            min = Math.min(min, w);
        }

        return min;
    }
}
```

```java
class Solution {
    public int minimumRecolors(String blocks, int k) {
        int w = 0;
        int n = blocks.length();
```

👉 You removed `b` — good optimization 👍

---

## 🔹 First Window

```java
for(int i=0; i<k; i++){
    if(blocks.charAt(i)=='W') w++;
}
```

👉 Count only whites

---

## 🔹 Initialize

```java
int min = w;
```

---

## 🔹 Sliding Window

```java
for(int i=k; i<n; i++){
    if(blocks.charAt(i)=='W') w++;
    if(blocks.charAt(i-k)=='W') w--;
```

👉 Same logic:

* Add new char
* Remove old char

---

## 🔹 Update Answer

```java
min = Math.min(min,w);
```

---

## 🔹 Return

```java
return min;
```

---

## 🧠 Why This is Better

* You only track what matters (`W`)
* Less variables → cleaner code
* Same performance

---

## ⏱ Complexity

* **Time:** O(n)
* **Space:** O(1)

---

# 🔥 Key Concepts You Should Remember

### ✅ 1. Fixed Size Sliding Window

* Window size = k
* Move one step at a time

---

### ✅ 2. First Window is Important

* Always process before loop

---

### ✅ 3. Only Track What Matters

* Here only `'W'` matters

---

### ✅ 4. Reuse Previous Work

* Don’t recompute entire window

---

# 🎯 Final Takeaway

👉 Brute Force:

* Easy but slow

👉 Sliding Window:

* Smart reuse of computation
* Industry-level approach

---
</p>
