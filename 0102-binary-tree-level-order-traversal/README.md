<h2><a href="https://leetcode.com/problems/binary-tree-level-order-traversal">102. Binary Tree Level Order Traversal</a></h2><h3>Medium</h3><hr><p>Given the <code>root</code> of a binary tree, return <em>the level order traversal of its nodes&#39; values</em>. (i.e., from left to right, level by level).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/tree1.jpg" style="width: 277px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [3,9,20,null,null,15,7]
<strong>Output:</strong> [[3],[9,20],[15,7]]
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
===================================================================================================================================================================




For **LeetCode 102 (Binary Tree Level Order Traversal)**, there isn’t a huge gap like “bad vs best” — but we can still classify:

* **Brute Force → DFS + level tracking**
* **Optimal → BFS (Queue) level order traversal**

Let’s go step by step 👇

---

# 🟥 1. Brute Force (DFS Approach)

### 💡 Idea

* Use recursion (DFS)
* Track **level (depth)** manually
* Store values in list based on level

---

### ✔️ Code

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }

    public void dfs(TreeNode node, int level, List<List<Integer>> res) {
        if(node == null) return;

        // create new level if not exists
        if(res.size() == level){
            res.add(new ArrayList<>());
        }

        res.get(level).add(node.val);

        dfs(node.left, level + 1, res);
        dfs(node.right, level + 1, res);
    }
}
```

---

### 🧠 How it works

* Go deep (like preorder traversal)
* Keep track of level
* Add node value to correct level list

---

### ⏱ Complexity

* Time: **O(n)**
* Space: **O(n)** (recursion stack + result)

---

### ⚠️ Why this is called "Brute Force"

* Not natural for level order
* Requires extra thinking (tracking level manually)
* Uses recursion stack

---

# 🟩 2. Optimal Solution (BFS / Queue)

### 💡 Idea

* Use **Queue**
* Process **level by level**
* Queue size tells how many nodes in current level

---

### ✔️ Code

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> level = new ArrayList<>();

            for(int i = 0; i < size; i++){
                TreeNode node = q.poll();

                level.add(node.val);

                if(node.left != null) q.add(node.left);
                if(node.right != null) q.add(node.right);
            }

            res.add(level);
        }

        return res;
    }
}
```

---

### 🧠 Why this is Optimal

* Matches **problem requirement directly**
* No recursion needed
* Clean level-by-level traversal

---

### ⏱ Complexity

* Time: **O(n)**
* Space: **O(n)** (queue)

---

# 🔥 Final Comparison

| Approach | Method            | Natural? | Extra Work           |
| -------- | ----------------- | -------- | -------------------- |
| DFS      | Recursion + level | ❌ No     | Track level manually |
| BFS      | Queue             | ✅ Yes    | None                 |

---

# 🎯 Interview Tip

If interviewer asks:

> “Level order traversal?”

👉 Immediately think:

> **BFS + Queue**

---
</p>
