<h2><a href="https://leetcode.com/problems/check-if-matrix-is-x-matrix">2398. Check if Matrix Is X-Matrix</a></h2><h3>Easy</h3><hr><p>A square matrix is said to be an <strong>X-Matrix</strong> if <strong>both</strong> of the following conditions hold:</p>

<ol>
	<li>All the elements in the diagonals of the matrix are <strong>non-zero</strong>.</li>
	<li>All other elements are 0.</li>
</ol>

<p>Given a 2D integer array <code>grid</code> of size <code>n x n</code> representing a square matrix, return <code>true</code><em> if </em><code>grid</code><em> is an X-Matrix</em>. Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/05/03/ex1.jpg" style="width: 311px; height: 320px;" />
<pre>
<strong>Input:</strong> grid = [[2,0,0,1],[0,3,1,0],[0,5,2,0],[4,0,0,2]]
<strong>Output:</strong> true
<strong>Explanation:</strong> Refer to the diagram above. 
An X-Matrix should have the green elements (diagonals) be non-zero and the red elements be 0.
Thus, grid is an X-Matrix.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/05/03/ex2.jpg" style="width: 238px; height: 246px;" />
<pre>
<strong>Input:</strong> grid = [[5,7,0],[0,3,1],[0,5,0]]
<strong>Output:</strong> false
<strong>Explanation:</strong> Refer to the diagram above.
An X-Matrix should have the green elements (diagonals) be non-zero and the red elements be 0.
Thus, grid is not an X-Matrix.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length == grid[i].length</code></li>
	<li><code>3 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
</ul>
<p>

---

# 🧠 Problem Summary

A matrix is **X-Matrix** if:

1. Diagonals → must be **NON-ZERO**

   * `(i == j)` primary diagonal
   * `(i + j == n - 1)` secondary diagonal

2. All other cells → must be **ZERO**

---

# 🔴 1. Brute Force Approach

## 💡 Idea

Check every cell and validate rule directly.

---

## ✅ Code

```java id="br1x9a"
class Solution {
    public boolean checkXMatrix(int[][] grid) {

        int n = grid.length;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){

                boolean isDiagonal = (i == j || i + j == n - 1);

                if(isDiagonal){
                    if(grid[i][j] == 0){
                        return false;
                    }
                }
                else{
                    if(grid[i][j] != 0){
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
```

---

## 🧠 Explanation

We check every cell:

### If diagonal:

✔ must NOT be 0

### If not diagonal:

✔ must be 0

---

## ⏱️ Complexity

| Type  | Value     |
| ----- | --------- |
| Time  | **O(n²)** |
| Space | **O(1)**  |

---

# 🟢 2. Optimal Approach

## 💡 Idea

We can **reduce unnecessary checks**:

* First check diagonals in O(n)
* Then verify rest indirectly

---

## ✅ Code (Cleaner Optimal)

```java id="op1x7b"
class Solution {
    public boolean checkXMatrix(int[][] grid) {

        int n = grid.length;

        // Step 1: check diagonals
        for(int i = 0; i < n; i++){
            if(grid[i][i] == 0) return false;
            if(grid[i][n - i - 1] == 0) return false;
        }

        // Step 2: check non-diagonals
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){

                if(i == j || i + j == n - 1) continue;

                if(grid[i][j] != 0) return false;
            }
        }

        return true;
    }
}
```

---

## 🧠 Explanation

### Step 1 (Diagonal check)

We ensure:

* both diagonals contain **non-zero values**

### Step 2 (Rest check)

We ensure:

* all non-diagonal elements are **zero**

---

## ⏱️ Complexity

| Type  | Value     |
| ----- | --------- |
| Time  | **O(n²)** |
| Space | **O(1)**  |

---

# 🚀 Key Insight

Even optimal still needs O(n²) because:

👉 We must inspect all non-diagonal elements
👉 No shortcut exists without missing invalid cases

---

# 📊 Comparison

| Approach    | Idea                                | Time  | Space |
| ----------- | ----------------------------------- | ----- | ----- |
| Brute Force | Check every rule per cell           | O(n²) | O(1)  |
| Optimal     | Split diagonal + non-diagonal check | O(n²) | O(1)  |

---

# 🎯 Interview Summary

> “We check diagonal elements to ensure they are non-zero and verify all non-diagonal elements are zero. Both approaches run in O(n²) since every cell must be validated.”

---

# 💬 Final Insight

This problem teaches:

✔ Matrix classification
✔ Diagonal logic
✔ When optimization is only about readability, not complexity

---
this is actually a **very good optimized style** 👍


---

# 🧠 Your Code (Optimized Row-wise Check)

```java id="xcm1qa"
class Solution {
    public boolean checkXMatrix(int[][] grid) {

        int n = grid.length;

        for(int i = 0; i < n; i++){

            int sdi = n - i - 1;

            // check both diagonals
            if(grid[i][i] == 0 || grid[i][sdi] == 0){
                return false;
            }

            // check rest of row
            for(int j = 0; j < n; j++){
                if(j != i && j != sdi && grid[i][j] != 0){
                    return false;
                }
            }
        }

        return true;
    }
}
```

---

# 🧠 Idea Explained

You are doing:

## 🔹 Step 1 (Diagonal check per row)

```java
grid[i][i]
grid[i][n-i-1]
```

✔ must NOT be zero

---

## 🔹 Step 2 (Row-wise validation)

For each row:

* skip diagonals
* ensure everything else is zero

---

## 💡 Key Insight

Instead of checking full matrix blindly:
👉 You reduce checks using row-wise filtering

---

# ⏱️ Time Complexity (Your Code)

## Outer loop:

```text
n rows
```

## Inner loop:

```text
n columns
```

👉 Total:

```text
O(n × n) = O(n²)
```

---

# 📦 Space Complexity

```text
O(1)
```

✔ No extra data structures
✔ Only variables used

---

# 🔥 Now Compare All 3 Approaches

---

# 🔴 1. Brute Force

```java id="bf1"
check every cell directly
```

## ⏱️ TC:

```text
O(n²)
```

## 📦 SC:

```text
O(1)
```

## 💡 Idea:

Check each cell:

* diagonal → non-zero
* non-diagonal → zero

---

# 🟡 2. Standard Optimal (Full Scan with condition)

```java id="op1"
if(i==j || i+j==n-1) check diagonal
else check zero
```

## ⏱️ TC:

```text
O(n²)
```

## 📦 SC:

```text
O(1)
```

## 💡 Idea:

Single pass, clear logic

---

# 🟢 3. Your Approach (Row-wise optimization)

```java id="op2"
fix row → check diagonals → scan row
```

## ⏱️ TC:

```text
O(n²)
```

## 📦 SC:

```text
O(1)
```

---

# 📊 Final Comparison Table

| Approach         | Idea                      | Time  | Space | Readability |
| ---------------- | ------------------------- | ----- | ----- | ----------- |
| Brute Force      | Check every rule per cell | O(n²) | O(1)  | Medium      |
| Standard Optimal | Single pass full matrix   | O(n²) | O(1)  | ⭐ Best      |
| Your Approach    | Row-wise validation       | O(n²) | O(1)  | Medium      |

---

# 🧠 Key Insight

👉 All 3 are **same time complexity O(n²)**
👉 Difference is only **how we structure checks**

---

# ⚠️ Important Observation

Your approach:
✔ reduces repeated diagonal logic
✔ groups checks per row

BUT:
❌ still scans full row for each i
👉 so no real asymptotic gain

---

# 🎯 Final Interview Answer

> “We validate X-matrix by ensuring both diagonals are non-zero and all other elements are zero. Although different implementations exist (cell-wise, full scan, row-wise), all approaches run in O(n²) time since every element must be checked at least once.”

---

# 💬 Final Insight

👉 This problem teaches:

* diagonal detection
* matrix traversal patterns
* optimization = readability, not complexity reduction

---
</p>
