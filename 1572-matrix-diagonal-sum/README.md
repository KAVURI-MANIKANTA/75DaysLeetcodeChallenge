<h2><a href="https://leetcode.com/problems/matrix-diagonal-sum">1677. Matrix Diagonal Sum</a></h2><h3>Easy</h3><hr><p>Given a&nbsp;square&nbsp;matrix&nbsp;<code>mat</code>, return the sum of the matrix diagonals.</p>

<p>Only include the sum of all the elements on the primary diagonal and all the elements on the secondary diagonal that are not part of the primary diagonal.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/08/14/sample_1911.png" style="width: 336px; height: 174px;" />
<pre>
<strong>Input:</strong> mat = [[<strong>1</strong>,2,<strong>3</strong>],
&nbsp;             [4,<strong>5</strong>,6],
&nbsp;             [<strong>7</strong>,8,<strong>9</strong>]]
<strong>Output:</strong> 25
<strong>Explanation: </strong>Diagonals sum: 1 + 5 + 9 + 3 + 7 = 25
Notice that element mat[1][1] = 5 is counted only once.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> mat = [[<strong>1</strong>,1,1,<strong>1</strong>],
&nbsp;             [1,<strong>1</strong>,<strong>1</strong>,1],
&nbsp;             [1,<strong>1</strong>,<strong>1</strong>,1],
&nbsp;             [<strong>1</strong>,1,1,<strong>1</strong>]]
<strong>Output:</strong> 8
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> mat = [[<strong>5</strong>]]
<strong>Output:</strong> 5
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == mat.length == mat[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= mat[i][j] &lt;= 100</code></li>
</ul>
<p>

LeetCode **1572 – Matrix Diagonal Sum** .

---

# 🧠 Problem Idea

You need to sum:

1. **Primary diagonal** → `(i, i)`
2. **Secondary diagonal** → `(i, n - i - 1)`

---

# 🔴 Your Code

```java id="x7k2qp"
sum = sum + mat[i][i] + mat[n-i-1][i];
```



```java id="p9m2lw"
class Solution {
    public int diagonalSum(int[][] mat) {

        int n = mat.length;
        int sum = 0;

        for(int i = 0; i < n; i++) {
            sum += mat[i][i];               // primary diagonal
            sum += mat[i][n - i - 1];      // secondary diagonal
        }

        // if odd n, subtract center element (counted twice)
        if(n % 2 == 1) {
            sum -= mat[n / 2][n / 2];
        }

        return sum;
    }
}
```

---

# 🧠 Why we subtract middle element?

In odd matrix:

Example:

```text
1 2 3
4 5 6
7 8 9
```

Diagonals:

* Primary: 1 + 5 + 9
* Secondary: 3 + 5 + 7

👉 Middle element `5` counted twice ❌

So we subtract once:

```text
sum - mat[n/2][n/2]
```

---

# 🔍 Dry Run

For:

```text
1 2 3
4 5 6
7 8 9
```

### Step 1:

Primary:

* 1, 5, 9

Secondary:

* 3, 5, 7

### Step 2:

Sum:

```text
1+5+9+3+5+7 = 30
```

### Step 3:

Subtract middle:

```text
30 - 5 = 25
```

---

# ⏱️ Complexity

| Type  | Value |
| ----- | ----- |
| Time  | O(n)  |
| Space | O(1)  |

---

# 🎯 Key Insight

Instead of:

```java
mat[n-i-1][i]
```

Use:

```java
mat[i][n-i-1]
```

👉 Much easier to understand + less confusion in interviews

---

# 💬 One-line Interview Answer

> “We traverse the matrix once, summing both diagonals, and subtract the center element if n is odd to avoid double counting.”

---
---
sum = sum + mat[i][i] + mat[n-i-1][i];
sum = sum + mat[i][i] + mat[i][n-i-1];
both are correct because the matrix is always a square matrix n*n.
---
Both are valid because the matrix is **square (n × n)**.

---

# 🧠 Why both formulas work

## 1️⃣ Your version:

```java id="a1"
mat[n - i - 1][i]
```

This means:

* row → bottom to top
* column → left to right

👉 This is the **secondary diagonal from bottom-left to top-right**

---

## 2️⃣ Standard version:

```java id="a2"
mat[i][n - i - 1]
```

This means:

* row → top to bottom
* column → right to left

👉 This is the **same diagonal but traversed differently**

---

# 🔍 Key Insight

In an **n × n matrix**, both expressions represent the same set of positions:

| i | (i, n-i-1) | (n-i-1, i) |
| - | ---------- | ---------- |
| 0 | (0, n-1)   | (n-1, 0)   |
| 1 | (1, n-2)   | (n-2, 1)   |
| 2 | (2, n-3)   | (n-3, 2)   |

👉 Just reversed traversal order
👉 Same diagonal elements

---

# ⚠️ So why do people prefer one?

### ✔️ Preferred:

```java id="a3"
mat[i][n - i - 1]
```

### Reasons:

* Easier to understand (row-wise traversal)
* Matches normal loop direction (`i = 0 → n-1`)
* Less mental confusion in interviews

---

# 🧠 Important Interview Insight

Both are:

* ✅ Correct
* ✅ O(n)
* ✅ Equivalent mathematically

But interviewers prefer:

> “consistent forward traversal”

---

# 🎯 Final Conclusion

✔️ Your statement is correct:

> both expressions work in an n × n matrix

✔️ Difference is only:

* traversal direction
* readability

---
</p>
