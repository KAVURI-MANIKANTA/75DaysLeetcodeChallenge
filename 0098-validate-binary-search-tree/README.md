<h2><a href="https://leetcode.com/problems/validate-binary-search-tree">98. Validate Binary Search Tree</a></h2><h3>Medium</h3><hr><p>Given the <code>root</code> of a binary tree, <em>determine if it is a valid binary search tree (BST)</em>.</p>

<p>A <strong>valid BST</strong> is defined as follows:</p>

<ul>
	<li>The left <span data-keyword="subtree">subtree</span> of a node contains only nodes with keys&nbsp;<strong>strictly less than</strong> the node&#39;s key.</li>
	<li>The right subtree of a node contains only nodes with keys <strong>strictly greater than</strong> the node&#39;s key.</li>
	<li>Both the left and right subtrees must also be binary search trees.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/12/01/tree1.jpg" style="width: 302px; height: 182px;" />
<pre>
<strong>Input:</strong> root = [2,1,3]
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/12/01/tree2.jpg" style="width: 422px; height: 292px;" />
<pre>
<strong>Input:</strong> root = [5,1,4,null,null,3,6]
<strong>Output:</strong> false
<strong>Explanation:</strong> The root node&#39;s value is 5 but its right child&#39;s value is 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-2<sup>31</sup> &lt;= Node.val &lt;= 2<sup>31</sup> - 1</code></li>
</ul>
<p>
==================================================================================================
---

# 🌳 First: What is a Valid BST?

A **Binary Search Tree (BST)** follows this rule:

👉 For every node:

* Left subtree → values **smaller than node**
* Right subtree → values **greater than node**

Example:

```
    5
   / \
  3   7
```

✔ valid BST

```
    5
   / \
  3   4   ❌ (4 is on right but smaller than 5)
```

---

# ✅ CODE 1: RANGE METHOD (Most Important / Optimal)

```java
class Solution { 
    public boolean isValidBST(TreeNode root) {
        return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    boolean valid(TreeNode root, Long min, Long max) {
        if(root == null) return true;

        if(root.val <= min || root.val >= max) return false;

        return valid(root.left, min, (long)root.val) &&
               valid(root.right, (long)root.val, max);
    }
}
```

---

## 🧠 IDEA (Very Important)

👉 Each node must lie within a **valid range**

* Root can be anything → (-∞, +∞)
* Left child must be < parent
* Right child must be > parent

---

## 🔍 Step-by-Step Example

Tree:

```
      10
     /  \
    5    15
        /  \
       6   20
```

⚠️ This is **NOT a BST** (6 is wrong)

---

### Step 1:

```
valid(10, -∞, +∞) ✅
```

---

### Step 2 (left subtree):

```
valid(5, -∞, 10) ✅
```

---

### Step 3 (right subtree):

```
valid(15, 10, +∞) ✅
```

---

### Step 4:

Now check node `6`:

```
valid(6, 10, 15) ❌
```

👉 6 is **less than 10**, but it is in right subtree
👉 So condition fails

---

## 🔥 KEY LINE

```java
if(root.val <= min || root.val >= max) return false;
```

👉 This ensures:

* Node must stay inside allowed range
* NOT just comparing with parent — comparing with **entire ancestry**

---

## ❓ Why `Long`?

```java
Long.MIN_VALUE, Long.MAX_VALUE
```

👉 Because:

* `int` range may overflow
* Safe for extreme cases

---

## ⏱ Time & Space

* Time: **O(n)**
* Space: **O(h)** (recursion stack)

---

# ✅ CODE 2: INORDER METHOD (Very Intuitive)

```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root,res);

        int n = res.size();
        for(int i=1; i<n; i++){
            if(res.get(i) <= res.get(i-1)) return false;
        }
        return true;
    }

    public void inorder(TreeNode root,List<Integer> res){
        if(root==null) return;

        inorder(root.left,res);
        res.add(root.val);
        inorder(root.right,res);
    }
}
```

---

## 🧠 IDEA

👉 Inorder traversal of BST gives:

```
SORTED ARRAY
```

---

## 🔍 Example

Tree:

```
    5
   / \
  3   7
```

Inorder:

```
[3, 5, 7] ✅ sorted
```

---

❌ Invalid BST:

```
    5
   / \
  3   4
```

Inorder:

```
[3, 5, 4] ❌ not sorted
```

---

## 🔥 KEY PART

```java
if(res.get(i) <= res.get(i-1)) return false;
```

👉 Checks if array is strictly increasing

---

## ⏱ Time & Space

* Time: **O(n)**
* Space: **O(n)** (extra list)

---

# ⚖️ WHICH IS BETTER?

| Method           | Time | Space | Notes              |
| ---------------- | ---- | ----- | ------------------ |
| Range (Code 1)   | O(n) | O(h)  | ✅ Optimal          |
| Inorder (Code 2) | O(n) | O(n)  | Easy to understand |

---

# 🧠 DEEP UNDERSTANDING (IMPORTANT FOR INTERVIEWS)

👉 Many beginners think:

```
Check only parent relation
```

❌ WRONG

Example:

```
      10
     /  \
    5    15
        /
       6
```

👉 6 < 15 (looks ok)
👉 But 6 < 10 ❌ (global violation)

✔ That’s why **range method is powerful**

---

# 🚀 SIMPLE SUMMARY

* BST = global ordering, not local
* Code 1 → tracks valid range → BEST
* Code 2 → uses sorted property of inorder

---
</p>
