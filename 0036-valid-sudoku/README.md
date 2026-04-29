<h2><a href="https://leetcode.com/problems/valid-sudoku">36. Valid Sudoku</a></h2><h3>Medium</h3><hr><p>Determine if a&nbsp;<code>9 x 9</code> Sudoku board&nbsp;is valid.&nbsp;Only the filled cells need to be validated&nbsp;<strong>according to the following rules</strong>:</p>

<ol>
	<li>Each row&nbsp;must contain the&nbsp;digits&nbsp;<code>1-9</code> without repetition.</li>
	<li>Each column must contain the digits&nbsp;<code>1-9</code>&nbsp;without repetition.</li>
	<li>Each of the nine&nbsp;<code>3 x 3</code> sub-boxes of the grid must contain the digits&nbsp;<code>1-9</code>&nbsp;without repetition.</li>
</ol>

<p><strong>Note:</strong></p>

<ul>
	<li>A Sudoku board (partially filled) could be valid but is not necessarily solvable.</li>
	<li>Only the filled cells need to be validated according to the mentioned&nbsp;rules.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Sudoku-by-L2G-20050714.svg/250px-Sudoku-by-L2G-20050714.svg.png" style="height:250px; width:250px" />
<pre>
<strong>Input:</strong> board = 
[[&quot;5&quot;,&quot;3&quot;,&quot;.&quot;,&quot;.&quot;,&quot;7&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;]
,[&quot;6&quot;,&quot;.&quot;,&quot;.&quot;,&quot;1&quot;,&quot;9&quot;,&quot;5&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;]
,[&quot;.&quot;,&quot;9&quot;,&quot;8&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;6&quot;,&quot;.&quot;]
,[&quot;8&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;6&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;3&quot;]
,[&quot;4&quot;,&quot;.&quot;,&quot;.&quot;,&quot;8&quot;,&quot;.&quot;,&quot;3&quot;,&quot;.&quot;,&quot;.&quot;,&quot;1&quot;]
,[&quot;7&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;2&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;6&quot;]
,[&quot;.&quot;,&quot;6&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;2&quot;,&quot;8&quot;,&quot;.&quot;]
,[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;4&quot;,&quot;1&quot;,&quot;9&quot;,&quot;.&quot;,&quot;.&quot;,&quot;5&quot;]
,[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;8&quot;,&quot;.&quot;,&quot;.&quot;,&quot;7&quot;,&quot;9&quot;]]
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> board = 
[[&quot;8&quot;,&quot;3&quot;,&quot;.&quot;,&quot;.&quot;,&quot;7&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;]
,[&quot;6&quot;,&quot;.&quot;,&quot;.&quot;,&quot;1&quot;,&quot;9&quot;,&quot;5&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;]
,[&quot;.&quot;,&quot;9&quot;,&quot;8&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;6&quot;,&quot;.&quot;]
,[&quot;8&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;6&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;3&quot;]
,[&quot;4&quot;,&quot;.&quot;,&quot;.&quot;,&quot;8&quot;,&quot;.&quot;,&quot;3&quot;,&quot;.&quot;,&quot;.&quot;,&quot;1&quot;]
,[&quot;7&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;2&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;6&quot;]
,[&quot;.&quot;,&quot;6&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;2&quot;,&quot;8&quot;,&quot;.&quot;]
,[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;4&quot;,&quot;1&quot;,&quot;9&quot;,&quot;.&quot;,&quot;.&quot;,&quot;5&quot;]
,[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;8&quot;,&quot;.&quot;,&quot;.&quot;,&quot;7&quot;,&quot;9&quot;]]
<strong>Output:</strong> false
<strong>Explanation:</strong> Same as Example 1, except with the <strong>5</strong> in the top left corner being modified to <strong>8</strong>. Since there are two 8&#39;s in the top left 3x3 sub-box, it is invalid.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>board.length == 9</code></li>
	<li><code>board[i].length == 9</code></li>
	<li><code>board[i][j]</code> is a digit <code>1-9</code> or <code>&#39;.&#39;</code>.</li>
</ul>
<p>
---
	
**brute force → simple using 3 HashSets** (this is actually the easiest way to understand before optimization).
---
---


# 🔴 1. Brute Force Solution

# 🟡 Idea (Very Simple)

Instead of:

* scanning row again and again ❌

We do:

* **one pass**
* use **3 separate HashSets**

👉 For every row, column, and box we track numbers

---

# ✅ Code (3 HashSets)

```java
import java.util.*;

class Solution {
    public boolean isValidSudoku(char[][] board) {

        // 9 sets for rows
        HashSet<Character>[] rows = new HashSet[9];
        // 9 sets for cols
        HashSet<Character>[] cols = new HashSet[9];
        // 9 sets for boxes
        HashSet<Character>[] boxes = new HashSet[9];

        // initialize all sets
        for(int i = 0; i < 9; i++){
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){

                char num = board[i][j];

                if(num == '.') continue;

                int boxIndex = (i/3) * 3 + (j/3);

                // check duplicate
                if(rows[i].contains(num) || 
                   cols[j].contains(num) || 
                   boxes[boxIndex].contains(num)){
                    return false;
                }

                // add number
                rows[i].add(num);
                cols[j].add(num);
                boxes[boxIndex].add(num);
            }
        }

        return true;
    }
}
```

---

# 🧠 Explanation (Step-by-step)

## 🔹 What we created

```java
rows[0] → numbers in row 0
cols[1] → numbers in column 1
boxes[4] → numbers in box 4
```

---

## 🔍 Example

Suppose:

```java
board[0][0] = '5'
```

### Step 1:

```java
rows[0].contains('5') ? ❌
cols[0].contains('5') ? ❌
boxes[0].contains('5') ? ❌
```

👉 No duplicate → safe

---

### Step 2:

```java
rows[0].add('5')
cols[0].add('5')
boxes[0].add('5')
```

---

## ❌ Duplicate Case

Later:

```java
board[0][3] = '5'
```

Check:

```java
rows[0].contains('5') → YES ❌
```

👉 Same row duplicate → return false

---

# 📦 Box Index Understanding

```java
int boxIndex = (i/3) * 3 + (j/3);
```

| (i,j) | Box |
| ----- | --- |
| (0,0) | 0   |
| (0,5) | 1   |
| (4,4) | 4   |
| (8,8) | 8   |

---

# 🔥 Why This is Easier Than String Method

| Method     | Difficulty             |
| ---------- | ---------------------- |
| Strings    | Confusing (collisions) |
| 3 HashSets | 🔥 Very clear          |

---

# ⏱️ Complexity

* Time: `O(81)` → `O(1)`
* Space: `O(81)` → `O(1)`

---

# 🎯 Final Intuition

Instead of:

> “Let me check whole row again”

We do:

> “Did I already see this number in this row?”

---

# 💬 Summary

* 9 sets for rows
* 9 sets for cols
* 9 sets for boxes
* Check → then insert

---
mention **TC & SC** in interviews 👍

Let’s analyze your **3 HashSet approach**

---

# ⏱️ Time Complexity (TC)

### 🔍 Loops

```java
for(int i = 0; i < 9; i++){
    for(int j = 0; j < 9; j++){
```

👉 Total iterations:

```
9 × 9 = 81 cells
```

---

### 🔍 Inside each cell

```java
contains()  → O(1)
add()       → O(1)
```

👉 Constant time operations

---

## ✅ Final TC

```
O(81) → O(1)
```

👉 Because Sudoku size is **fixed (9×9)**

---

# 📦 Space Complexity (SC)

### 🔍 What we use

```java
HashSet<Character>[] rows = new HashSet[9];
HashSet<Character>[] cols = new HashSet[9];
HashSet<Character>[] boxes = new HashSet[9];
```

👉 Total sets:

```
9 + 9 + 9 = 27 sets
```

Each set can store **at most 9 elements**

---

### 🔢 Total storage

```
27 × 9 = 243 elements (max)
```

---

## ✅ Final SC

```
O(1)
```

👉 Because max size is fixed (not dependent on input)

---

# 🔥 Interview Tip (VERY IMPORTANT)

If interviewer asks:

👉 “What if board size is N×N?”

Then answer:

### ✔️ Time Complexity

```
O(N²)
```

### ✔️ Space Complexity

```
O(N²)
```

---

# 🎯 Final Answer Summary

| Metric           | Value                           |
| ---------------- | ------------------------------- |
| Time Complexity  | **O(1)** (or O(N²) generalized) |
| Space Complexity | **O(1)** (or O(N²) generalized) |

---

# 💬 One-line Answer (for interview)

> “We traverse the board once, so time is O(1) for fixed size, and we use constant extra space for storing seen elements.”

---

---

# 🟡 2. Your Approach (HashSet) – Improved

## 💡 Idea

Instead of re-checking:
👉 Store what you've already seen

---

## ✅ Your Code (Slightly Cleaned)

```java
import java.util.HashSet;

class Solution {
    public boolean isValidSudoku(char[][] board) {

        HashSet<String> hs = new HashSet<>();

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){

                if(board[i][j] == '.') continue;

                char num = board[i][j];

                String row = i + "r" + num;
                String col = j + "c" + num;
                String box = (i/3) + "" + (j/3) + num;

                if(hs.contains(row) || hs.contains(col) || hs.contains(box)){
                    return false;
                }

                hs.add(row);
                hs.add(col);
                hs.add(box);
            }
        }
        return true;
    }
}
```

---

## 🧠 Explanation (VERY IMPORTANT)

For each number:

```java
'5' at (i=1, j=2)
```

We store:

```text
"1r5"   → 5 seen in row 1
"2c5"   → 5 seen in col 2
"025"   → 5 seen in box (0,2)
```

---

### 🔥 What HashSet does

* If same key appears again → duplicate
* Immediately return `false`

---

### 🎯 Why this is better than brute force

| Brute Force       | HashSet       |
| ----------------- | ------------- |
| Re-checks again   | Stores once   |
| Repeated scanning | Direct lookup |
| Slower            | Faster        |

---

## ⏱️ Complexity

* Time: `O(81)` → `O(1)`
* Space: `O(81)` → `O(1)`

---

# 🟢 3. Optimal Solution (Best – Boolean Arrays)

## 💡 Idea

Instead of Strings:
👉 Use direct indexing

---

## ✅ Code

```java
class Solution {
    public boolean isValidSudoku(char[][] board) {

        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){

                if(board[i][j] == '.') continue;

                int num = board[i][j] - '1'; // 0 to 8
                int boxIndex = (i/3) * 3 + (j/3);

                if(rows[i][num] || cols[j][num] || boxes[boxIndex][num]){
                    return false;
                }

                rows[i][num] = true;
                cols[j][num] = true;
                boxes[boxIndex][num] = true;
            }
        }
        return true;
    }
}
```

---

## 🧠 Explanation

Example:

```java
board[i][j] = '5'
```

```java
num = 5 - 1 = 4
```

Now:

```java
rows[i][4]   → is 5 already in row i?
cols[j][4]   → is 5 already in col j?
boxes[b][4]  → is 5 already in box?
```

---

## 📦 Box Index Trick

```java
int boxIndex = (i/3) * 3 + (j/3);
```

| (i,j) | Box |
| ----- | --- |
| (0,0) | 0   |
| (1,4) | 1   |
| (4,7) | 5   |
| (8,8) | 8   |

---

## ⏱️ Complexity

* Time: `O(81)` → `O(1)`
* Space: `O(1)`
* 🔥 Fastest in practice

---

# 🚀 Final Comparison

| Approach      | Time | Space | Difficulty | Interview |
| ------------- | ---- | ----- | ---------- | --------- |
| Brute Force   | O(1) | O(1)  | Easy       | ❌         |
| HashSet       | O(1) | O(1)  | Medium     | ✅         |
| Boolean Array | O(1) | O(1)  | Medium     | 🔥 Best   |

---

# 🎯 Final Takeaway

* Brute force → **using 3 hashsets**
* HashSet → **stores rules as strings**
* Boolean → **direct indexing (fastest & cleanest)**

---
</p>
