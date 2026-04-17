<h2><a href="https://leetcode.com/problems/subtree-of-another-tree">572. Subtree of Another Tree</a></h2><h3>Easy</h3><hr><p>Given the roots of two binary trees <code>root</code> and <code>subRoot</code>, return <code>true</code> if there is a subtree of <code>root</code> with the same structure and node values of<code> subRoot</code> and <code>false</code> otherwise.</p>

<p>A subtree of a binary tree <code>tree</code> is a tree that consists of a node in <code>tree</code> and all of this node&#39;s descendants. The tree <code>tree</code> could also be considered as a subtree of itself.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/04/28/subtree1-tree.jpg" style="width: 532px; height: 400px;" />
<pre>
<strong>Input:</strong> root = [3,4,5,1,2], subRoot = [4,1,2]
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/04/28/subtree2-tree.jpg" style="width: 502px; height: 458px;" />
<pre>
<strong>Input:</strong> root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the <code>root</code> tree is in the range <code>[1, 2000]</code>.</li>
	<li>The number of nodes in the <code>subRoot</code> tree is in the range <code>[1, 1000]</code>.</li>
	<li><code>-10<sup>4</sup> &lt;= root.val &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= subRoot.val &lt;= 10<sup>4</sup></code></li>
</ul>

<p>
# 🌱 PART 1: Basic Idea of the Problem

👉 You are given:

* A big tree → `root`
* A small tree → `subRoot`

👉 You must check:

> “Does this small tree appear exactly somewhere inside the big tree?”

---

# 🌿 APPROACH 1: DFS + Tree Comparison (Optimal & Standard)

```java
class Solution { 
    public boolean isSame(TreeNode a, TreeNode b){
        if(a==null && b==null) return true;
        if(a==null || b==null) return false;
        return a.val==b.val && isSame(a.left,b.left) && isSame(a.right,b.right);
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root==null) return false;

        if(isSame(root,subRoot)) return true;

        return isSubtree(root.left,subRoot) || isSubtree(root.right,subRoot);
    }
}
```

---

## 🧠 Step 1: Understand `isSame()`

### 🔹 What it does:

👉 Checks if **two trees are exactly identical**

---

## 🔍 Base cases:

```java
if(a==null && b==null) return true;
```

👉 Both empty → same ✅

```java
if(a==null || b==null) return false;
```

👉 One empty, one not → different ❌

---

## 🔁 Recursive check:

```java
a.val==b.val && isSame(a.left,b.left) && isSame(a.right,b.right)
```

👉 Means:

1. Values must match
2. Left subtree must match
3. Right subtree must match

---

## 🌳 Visual intuition

```
   4        4
  / \      / \
 1   2    1   2
```

👉 This returns TRUE

---

# 🧠 Step 2: Understand `isSubtree()`

### 🔹 What it does:

👉 Checks **every node in root** as a possible starting point

---

## 🔁 Logic:

```java
if(isSame(root,subRoot)) return true;
```

👉 Check if subtree starts here

---

If not:

```java
return isSubtree(root.left,subRoot) || isSubtree(root.right,subRoot);
```

👉 Try:

* Left side
* Right side

---

## 🌳 Intuition

Think:

> “At every node, ask: is this the start of my subtree?”

---

## 🔥 Deep Understanding

👉 This is like:

* Traverse full tree (DFS)
* At each node → run another DFS (`isSame`)

---

## ⏱ Time Complexity

* Worst: **O(n * m)**
  (n = root nodes, m = subRoot nodes)

---

---

# 🌿 APPROACH 2: String Serialization

```java
class Solution {
    public String strtree(TreeNode node){
        StringBuilder sb = new StringBuilder("^");
        if(node==null) return "null";

        sb.append(node.val);
        sb.append(strtree(node.left));
        sb.append(strtree(node.right));

        return sb.toString();
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        String fTree = strtree(root);
        String subTree = strtree(subRoot);
        return fTree.contains(subTree);
    }
}
```

---

# 🧠 Step 1: Convert tree → string

### Example tree:

```
    4
   / \
  1   2
```

Becomes:

```
^4^1nullnull^2nullnull
```

---

## 🔑 Important components

### 1️⃣ `"^"`

👉 Separator to avoid wrong matches

Example:

```
12 vs 2
```

Without `^`:

```
"12" contains "2" ❌
```

With `^`:

```
"^12" contains "^2" ❌ correct
```

---

### 2️⃣ `"null"`

👉 Keeps structure

Without `"null"`:

```
Left-child tree and right-child tree look same ❌
```

With `"null"`:

```
Structure preserved ✅
```

---

## 🔁 Recursion meaning

```java
sb.append(strtree(node.left));
```

👉 Takes full left subtree string and attaches it

---

# 🧠 Step 2: Substring check

```java
fTree.contains(subTree)
```

👉 If subtree string exists → return true

---

# 🔥 Deep Comparison Between Both

| Feature   | DFS Approach          | String Approach        |
| --------- | --------------------- | ---------------------- |
| Logic     | Structural comparison | Pattern matching       |
| Speed     | O(n * m)              | O(n * m) worst         |
| Space     | O(height)             | O(n) string            |
| Interview | ✅ Preferred          | ⚠️ Less preferred     |
| Risk      | Safe                  | Needs careful encoding |

---

# 🧠 Very Deep Insight (IMPORTANT)

### DFS Approach:

👉 Works like:

> “Find matching roots → verify structure deeply”

---

### String Approach:

👉 Works like:

> “Flatten tree → check substring pattern”

---

# ⚡ Which should YOU use?

👉 For interviews:
✅ Always go with **DFS approach**

👉 For quick coding:
✅ String approach is okay (but tricky)

---

# 🧠 Final Intuition (Golden Line)

👉 DFS Approach:

> “Check node by node”

👉 String Approach:

> “Convert to string and match pattern”

---
</p>
