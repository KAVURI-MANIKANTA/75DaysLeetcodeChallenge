<h2><a href="https://leetcode.com/problems/average-of-levels-in-binary-tree">637. Average of Levels in Binary Tree</a></h2><h3>Easy</h3><hr>Given the <code>root</code> of a binary tree, return <em>the average value of the nodes on each level in the form of an array</em>. Answers within <code>10<sup>-5</sup></code> of the actual answer will be accepted.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/03/09/avg1-tree.jpg" style="width: 277px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [3,9,20,null,null,15,7]
<strong>Output:</strong> [3.00000,14.50000,11.00000]
Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
Hence return [3, 14.5, 11].
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/03/09/avg2-tree.jpg" style="width: 292px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [3,9,20,15,7]
<strong>Output:</strong> [3.00000,14.50000,11.00000]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-2<sup>31</sup> &lt;= Node.val &lt;= 2<sup>31</sup> - 1</code></li>
</ul>
<p>
---

# 🌳 Problem in One Line

👉 For each level of a tree, find the **average of values**

---

# 🧠 Core Idea (Very Simple)

👉 Use **BFS (level order traversal)**

* Process tree **level by level**
* For each level:

  1. Count nodes (`n`)
  2. Add their values (`sum`)
  3. Compute average = `sum / n`


---
/*
Definition for a binary tree node.<br>
public class TreeNode {<br>
     int val;<br>
     TreeNode left;<br>
     TreeNode right;<br>
     TreeNode() {}<br>
     TreeNode(int val) { this.val = val; }<br>
     TreeNode(int val, TreeNode left, TreeNode right) {<br>
         this.val = val;<br>
         this.left = left;<br>
         this.right = right;<br>
     }<br>
 }<br>
*/<br>
class Solution {<br>
    public List<Double> averageOfLevels(TreeNode root) {<br>
        List<Double> res = new ArrayList<>();<br>
        if(root==null) return res;<br>
        Queue<TreeNode> q = new LinkedList<>();<br>
        q.add(root);<br>
        while(!q.isEmpty()){<br>
            double n = q.size();<br>
            double sum = 0;<br>
            for(int i=0; i<n; i++){<br>
                TreeNode peekN = q.poll();<br>
                sum = sum + peekN.val;<br>
                if(peekN.left!=null) q.add(peekN.left);<br>
                if(peekN.right!=null) q.add(peekN.right);<br>
             }<br>
            res.add(sum/n);<br>
        }<br>
        return res;<br>
    }<br>
}<br>
---
---

# 🔍 Code Explanation (Line by Line)

```java
List<Double> res = new ArrayList<>();
```

👉 Store final answers (averages of each level)

---

```java
if(root == null) return res;
```

👉 If tree is empty → return empty list

---

```java
Queue<TreeNode> q = new LinkedList<>();
q.add(root);
```

👉 Queue helps us process nodes **level by level**

---

## 🔁 Main Loop

```java
while(!q.isEmpty())
```

👉 Run until all nodes are processed

---

### 📌 Step 1: Count nodes in current level

```java
int n = q.size();
```

👉 This tells:

> “How many nodes are present in this level?”

---

### 📌 Step 2: Initialize sum

```java
double sum = 0;
```

---

### 📌 Step 3: Process all nodes of that level

```java
for(int i = 0; i < n; i++)
```

👉 Loop runs **n times = one full level**

---

### Inside loop:

```java
TreeNode node = q.poll();
```

👉 Remove node from queue

---

```java
sum += node.val;
```

👉 Add its value

---

```java
if(node.left != null) q.add(node.left);
if(node.right != null) q.add(node.right);
```

👉 Add children for **next level**

---

### 📌 Step 4: Add average

```java
res.add(sum / n);
```

👉 Store average of current level

---

# 🔁 Dry Run (Step by Step)

Tree:

```text
        3
       / \
      9   20
          / \
         15  7
```

---

## 🟢 Start

Queue = `[3]`
Result = `[]`

---

## 🔵 Level 1

```text
n = 1
```

Process:

* node = 3 → sum = 3
* add 9, 20

Queue:

```text
[9, 20]
```

Average:

```text
3 / 1 = 3
```

Result:

```text
[3.0]
```

---

## 🔵 Level 2

```text
n = 2
```

Process:

* node = 9 → sum = 9
* node = 20 → sum = 29
* add 15, 7

Queue:

```text
[15, 7]
```

Average:

```text
29 / 2 = 14.5
```

Result:

```text
[3.0, 14.5]
```

---

## 🔵 Level 3

```text
n = 2
```

Process:

* node = 15 → sum = 15
* node = 7 → sum = 22

Queue:

```text
[]
```

Average:

```text
22 / 2 = 11
```

Result:

```text
[3.0, 14.5, 11.0]
```

---

# ⏱️ Time Complexity

👉 **O(n)**

Why?

* Each node is visited **exactly once**

---

# 💾 Space Complexity

👉 **O(n)**

Why?

* Queue stores nodes
* In worst case (last level), it can store ~n/2 nodes

---

# 🧠 Important Pattern (Remember This!)

👉 This pattern works for ALL level-order problems:

```text
while queue not empty:
    get size (level size)
    loop size times:
        process node
        add children
    do something with level result
```

---

# 🎯 Final Understanding

👉 This problem = **Level Order + Calculation**

* BFS → gives levels
* Sum → gives total
* Divide → gives average

---

</p>
