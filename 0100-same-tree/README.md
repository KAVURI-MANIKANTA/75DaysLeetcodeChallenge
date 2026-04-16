<h2><a href="https://leetcode.com/problems/same-tree">100. Same Tree</a></h2><h3>Easy</h3><hr><p>Given the roots of two binary trees <code>p</code> and <code>q</code>, write a function to check if they are the same or not.</p>

<p>Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/12/20/ex1.jpg" style="width: 622px; height: 182px;" />
<pre>
<strong>Input:</strong> p = [1,2,3], q = [1,2,3]
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/12/20/ex2.jpg" style="width: 382px; height: 182px;" />
<pre>
<strong>Input:</strong> p = [1,2], q = [1,null,2]
<strong>Output:</strong> false
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/12/20/ex3.jpg" style="width: 622px; height: 182px;" />
<pre>
<strong>Input:</strong> p = [1,2,1], q = [1,1,2]
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in both trees is in the range <code>[0, 100]</code>.</li>
	<li><code>-10<sup>4</sup> &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
</ul>
<p>

# 🧠 Problem Summary

You are given two binary trees `p` and `q`.

👉 Return `true` if:

* Structure is same
* Node values are same

---

# 🐢 1. Brute Force Approach (Store + Compare)

### 💡 Idea

1. Traverse both trees
2. Store their structure + values
3. Compare the results

---

### ✅ Code (Using Preorder Traversal)

```java
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        preorder(p, list1);
        preorder(q, list2);

        return list1.equals(list2);
    }

    private void preorder(TreeNode node, List<String> list) {
        if (node == null) {
            list.add("null");  // important for structure
            return;
        }
        list.add(String.valueOf(node.val));
        preorder(node.left, list);
        preorder(node.right, list);
    }
}
```

---

### 🔍 Explanation

* We convert tree into a list like:

  ```
  [1, 2, null, null, 3, null, null]
  ```
* If both lists are same → trees are same

---

### ⏱ Complexity

* Time: **O(n)**
* Space: **O(n)** (extra list used)

---

### ❌ Why it's called brute force?

* Uses extra memory
* Not efficient compared to direct comparison

---

# ⚡ 2. Optimal Approach (Recursive)

### 💡 Idea

Compare nodes **directly while traversing**

---

### ✅ Code

```java
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {

        // Case 1: both null
        if (p == null && q == null) return true;

        // Case 2: one null
        if (p == null || q == null) return false;

        // Case 3: values different
        if (p.val != q.val) return false;

        // Check left and right
        return isSameTree(p.left, q.left) &&
               isSameTree(p.right, q.right);
    }
}
```
doubt:
👉 “If both are null, won’t the program stop here and ignore other nodes?”

✅ Reality: It does NOT stop the whole program

This line only ends that particular function call, not the entire process.

Because this is recursion, many calls are happening in parallel (like a tree).

🧠 Think like this (very important)

Each call checks one pair of nodes:

return isSameTree(p.left, q.left) &&
       isSameTree(p.right, q.right);

So:

One call checks root
Other calls check left subtree
Other calls check right subtree
📌 What happens when both are null?
if (p == null && q == null) return true;

👉 This means:
✔ "These two nodes are same (both empty), so continue checking others"

It does NOT stop everything — it just says:

“This branch is fine, move on”
---

### 🔍 Explanation (Step-by-step)

For every pair `(p, q)`:

1. Both null → ✅ same
2. One null → ❌ not same
3. Values different → ❌
4. Otherwise → check:

   * left subtree
   * right subtree

---

### 🧠 Key Insight

```java
return left && right;
```

👉 Both sides must be true
👉 If one fails → whole answer false

---

### ⏱ Complexity

* Time: **O(n)**
* Space: **O(h)** (recursion stack, h = height)

---

# 🔁 3. Optimal Iterative (BFS) – Bonus

```java
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> qu = new LinkedList<>();
        qu.add(p);
        qu.add(q);

        while (!qu.isEmpty()) {
            TreeNode n1 = qu.poll();
            TreeNode n2 = qu.poll();

            if (n1 == null && n2 == null) continue;
            if (n1 == null || n2 == null) return false;
            if (n1.val != n2.val) return false;

            qu.add(n1.left);
            qu.add(n2.left);
            qu.add(n1.right);
            qu.add(n2.right);
        }
        return true;
    }
}
```

---

# 🔥 Final Comparison

| Approach            | Idea                | Space        | Easy?   |
| ------------------- | ------------------- | ------------ | ------- |
| Brute Force         | Store + compare     | ❌ O(n) extra | 👍 Easy |
| Recursive (Optimal) | Direct compare      | ✅ O(h)       | ⭐ Best  |
| BFS (Optimal)       | Level order compare | ✅ O(n)       | 👍 Good |

---

# 🚀 What you should use in interview

👉 **Recursive solution (Optimal)**
Because:

* Clean
* Short
* Easy to explain

</p>
