<h2><a href="https://leetcode.com/problems/second-minimum-node-in-a-binary-tree">671. Second Minimum Node In a Binary Tree</a></h2><h3>Easy</h3><hr><p>Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly <code>two</code> or <code>zero</code> sub-node. If the node has two sub-nodes, then this node&#39;s value is the smaller value among its two sub-nodes. More formally, the property&nbsp;<code>root.val = min(root.left.val, root.right.val)</code>&nbsp;always holds.</p>

<p>Given such a binary tree, you need to output the <b>second minimum</b> value in the set made of all the nodes&#39; value in the whole tree.</p>

<p>If no such second minimum value exists, output -1 instead.</p>

<p>&nbsp;</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/15/smbt1.jpg" style="width: 431px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [2,2,5,null,null,5,7]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The smallest value is 2, the second smallest value is 5.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/15/smbt2.jpg" style="width: 321px; height: 182px;" />
<pre>
<strong>Input:</strong> root = [2,2,2]
<strong>Output:</strong> -1
<strong>Explanation:</strong> The smallest value is 2, but there isn&#39;t any second smallest value.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 25]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>root.val == min(root.left.val, root.right.val)</code>&nbsp;for each internal node of the tree.</li>
</ul>
<p>
========================================================================
---

# 🔷 Problem (Second Minimum Node In a Binary Tree)

### Given:

A special binary tree where:

```text
Every node = min(left, right)
```

👉 That means:

* Root = smallest value in entire tree
* Children ≥ parent

---

### Task:

👉 Find the **second minimum value**
👉 If it doesn’t exist → return `-1`

---

# 🔷 Example

```text
        2
       / \
      2   5
         / \
        5   7
```

👉 Values: `[2, 2, 5, 5, 7]`
👉 Smallest = `2`
👉 Second smallest = `5`

---

# 🔷 Brute Force

### Idea:

1. Traverse tree
2. Store all values in ArrayList
3. Sort / find unique
4. Return second smallest

---

### Code:

```java
class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);

        Collections.sort(list);

        int first = list.get(0);

        for (int num : list) {
            if (num > first) return num;
        }

        return -1;
    }

    public void dfs(TreeNode root, List<Integer> list) {
        if (root == null) return;

        list.add(root.val);
        dfs(root.left, list);
        dfs(root.right, list);
    }
}
```

---

# 🔷 Complexity

* Time: **O(n log n)** (sorting)
* Space: **O(n)**

---

# ❗ Is it OK?

👉 YES for understanding
👉 BUT ❌ Not optimal for interviews

Because:

* You are using extra space
* You are ignoring the **special property of tree**

---

# 🔷 Optimal Approach (IMPORTANT)

### 🔥 Key Observation:

```text
Root = smallest value
```

So:
👉 We only need to find the **smallest value > root.val**

---

# 🔷 Intuition

* If node value == root → explore children
* If node value > root → candidate for answer

---

# 🔷 Optimal Code

```java
class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;

        return helper(root, root.val);
    }

    public int helper(TreeNode root, int min) {
        if (root == null) return -1;

        if (root.val > min) return root.val;

        int left = helper(root.left, min);
        int right = helper(root.right, min);

        if (left == -1) return right;
        if (right == -1) return left;

        return Math.min(left, right);
    }
}
```

---

# 🔷 Dry Run

Tree:

```text
        2
       / \
      2   5
```

* root = 2
* left = 2 → ignore → go deeper
* right = 5 → candidate

👉 Answer = 5

---

# 🔷 Complexity

* Time: **O(n)**
* Space: **O(h)** (recursion)

---

# 🔷 Easy Memory Trick 🧠

```text
Need smallest? → root

Need second smallest?
→ find smallest > root
```

---

# 🔷 Final Verdict

| Approach         | Good?  | Why               |
| ---------------- | ------ | ----------------- |
| ArrayList + sort | ✅ OK   | Easy but slow     |
| DFS without list | ⭐ BEST | Uses BST property |

---

</p>
