<h2><a href="https://leetcode.com/problems/binary-tree-level-order-traversal-ii">107. Binary Tree Level Order Traversal II</a></h2><h3>Medium</h3><hr><p>Given the <code>root</code> of a binary tree, return <em>the bottom-up level order traversal of its nodes&#39; values</em>. (i.e., from left to right, level by level from leaf to root).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/tree1.jpg" style="width: 277px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [3,9,20,null,null,15,7]
<strong>Output:</strong> [[15,7],[9,20],[3]]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [1]
<strong>Output:</strong> [[1]]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> root = []
<strong>Output:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[0, 2000]</code>.</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
</ul>
<p>
**Binary Tree Level Order Traversal II** 👇
---

# 🧠 Problem in one line

> Same as level order (102), but **bottom → top**

---

# 🔴 1. DFS + Front Insertion (Tricky but Smart)

### ✅ Full Code

```java
class Solution { 
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root,0,res);
        return res;
    }
    public void dfs(TreeNode root, int l, List<List<Integer>> res){
        if(root==null) return ;
        if(l==res.size()) res.add(0,new ArrayList<>());
        res.get(res.size()-l-1).add(root.val);
        dfs(root.left,l+1,res);
        dfs(root.right,l+1,res);
    }
}
```

---

## 💡 Core Idea

* DFS goes **top → bottom**
* But we want **bottom → top**
* So we:

  1. Insert new levels at **front**
  2. Use **reverse indexing**

---

## 🔍 Key Formula

```java
res.get(res.size() - l - 1)
```

👉 Mapping:

```
level 0 → last index
level 1 → second last
```

---

## ⚠️ Why this works

* `res` grows from **front**
* Index adjusts dynamically

---

## 🧠 Difficulty

❌ Hard to think
❌ Easy to mess up

---

# 🟡 2. DFS + Reverse (Clean DFS Way)

### ✅ Full Code

```java
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root,0,res);
        Collections.reverse(res);
        return res;
    }
    public void dfs(TreeNode root, int l, List<List<Integer>> res){
        if(root==null) return ;
        if(l==res.size()) res.add(new ArrayList<>());
        res.get(l).add(root.val);
        dfs(root.left,l+1,res);
        dfs(root.right,l+1,res);
    }
}
```

---

## 💡 Core Idea

* Build normal level order:

```
[[1], [2,3], [4,5]]
```

* Then reverse:

```
[[4,5], [2,3], [1]]
```

---

## 🧠 Why this is better than #1

* Simple logic
* No tricky indexing
* Easy to explain

---

## ⏱ Complexity

* DFS: O(n)
* Reverse: O(n)
  👉 Total: **O(n)**

---

# 🟢 3. BFS + Front Insertion

### ✅ Full Code

```java
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int n = q.size();
            List<Integer> lev = new ArrayList<>();
            for(int i=0; i<n; i++){
                TreeNode peek = q.poll();
                lev.add(peek.val);
                if(peek.left!=null) q.add(peek.left);
                if(peek.right!=null) q.add(peek.right);
            }
            res.add(0,lev);
        }
        return res;
    }
}
```

---

## 💡 Core Idea

* BFS processes **level by level**
* Insert each level at **front**

---

## 🔥 Why it works

* Each loop = one level
* Front insertion reverses order naturally

---

## ⚠️ Problem

```java
res.add(0, lev);  // O(n)
```

👉 Causes shifting
👉 Total = **O(n²)** worst case ❌

---

# 🟢 4. BFS + Reverse (BEST SOLUTION)

### ✅ Full Code

```java
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int n = q.size();
            List<Integer> lev = new ArrayList<>();
            for(int i=0; i<n; i++){
                TreeNode peek = q.poll();
                lev.add(peek.val);
                if(peek.left!=null) q.add(peek.left);
                if(peek.right!=null) q.add(peek.right);
            }
            res.add(lev);
        }
        Collections.reverse(res);
        return res;
    }
}
```

---

## 💡 Core Idea

* Do normal BFS
* Reverse once at end

---

## 🔥 Why this is BEST

* No recursion
* No tricky math
* No shifting
* Clean and efficient

---

## ⏱ Complexity

* BFS: O(n)
* Reverse: O(n)
  👉 Total: **O(n)** ✅

---

# ⚔️ FINAL COMPARISON

| Approach | Type          | Complexity | Difficulty | Recommendation |
| -------- | ------------- | ---------- | ---------- | -------------- |
| 1        | DFS + front   | O(n)       | ❌ Hard     | ❌ Avoid        |
| 2        | DFS + reverse | O(n)       | 🟡 Medium  | 👍 Good        |
| 3        | BFS + front   | O(n²)      | 🟢 Easy    | ❌ Avoid        |
| 4        | BFS + reverse | O(n)       | 🟢 Easy    | ✅ BEST         |

---

# 🧠 What YOU should say in interview

👉 If asked **optimal solution**:

> “I’ll use BFS to collect levels and reverse once to achieve bottom-up order in O(n) time.”

---

# 🔥 Final Intuition

| Pattern          | What to do            |
| ---------------- | --------------------- |
| Need level order | BFS                   |
| Need reverse     | Reverse at end        |
| Avoid shifting   | Don’t use `add(0, …)` |

---

# 🚀 Pro Tip

Whenever you think:

> “Let me insert at front”

👉 Ask:

> “Will this shift elements?”

If YES → Avoid it ❌

---
</p>
