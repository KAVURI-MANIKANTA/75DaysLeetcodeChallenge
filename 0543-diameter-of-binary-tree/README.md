<h2><a href="https://leetcode.com/problems/diameter-of-binary-tree">543. Diameter of Binary Tree</a></h2><h3>Easy</h3><hr><p>Given the <code>root</code> of a binary tree, return <em>the length of the <strong>diameter</strong> of the tree</em>.</p>

<p>The <strong>diameter</strong> of a binary tree is the <strong>length</strong> of the longest path between any two nodes in a tree. This path may or may not pass through the <code>root</code>.</p>

<p>The <strong>length</strong> of a path between two nodes is represented by the number of edges between them.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/03/06/diamtree.jpg" style="width: 292px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,4,5]
<strong>Output:</strong> 3
<strong>Explanation:</strong> 3 is the length of the path [4,2,1,3] or [5,2,1,3].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [1,2]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
</ul>
<p>
**LeetCode 543 – Diameter of Binary Tree**

👉 First understand the problem simply:

* **Diameter = longest path between any two nodes**
* Path length = number of edges
* Path **may or may not pass through root**

---

# ✅ 1. Brute Force Solution (O(n²))

### 💡 Idea:

For every node:

1. Find left subtree height
2. Find right subtree height
3. Diameter through that node = `leftHeight + rightHeight`
4. Recursively check for left & right subtrees

👉 This repeats height calculation many times → slow

---

### ✅ Code (Brute Force)

```java
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        int currentDiameter = leftHeight + rightHeight;

        int leftDiameter = diameterOfBinaryTree(root.left);
        int rightDiameter = diameterOfBinaryTree(root.right);

        return Math.max(currentDiameter, Math.max(leftDiameter, rightDiameter));
    }

    public int height(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }
}
```

---

### ⏱ Complexity:

* Time: **O(n²)**
* Space: **O(h)** (recursion stack)

---

# ✅ 2. Optimal Solution (O(n))

### 💡 Core Idea:

👉 While calculating height, also calculate diameter

Instead of separate calls:

* Compute height
* Update diameter at same time

---

### 🔥 Key Observation:

At each node:

```
diameter = leftHeight + rightHeight
```

---

### ✅ Code (Optimal)

```java
class Solution {
    int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return diameter;
    }

    public int height(TreeNode root) {
        if (root == null) return 0;

        int left = height(root.left);
        int right = height(root.right);

        // update diameter
        diameter = Math.max(diameter, left + right);

        return 1 + Math.max(left, right);
    }
}
```

---

### ⏱ Complexity:

* Time: **O(n)** ✅
* Space: **O(h)**

---

# 🧠 Super Simple Intuition

Think like this:

* Height → "How tall is this tree?"
* Diameter → "What is the longest path passing through this node?"

👉 Instead of calculating height again and again (brute force),
👉 **Use height calculation to also update diameter**

---

# ⚡ Interview Tip

If interviewer asks:

👉 Start with:

> “Brute force is O(n²) because height is recalculated”

👉 Then say:

> “We can optimize by calculating height and diameter together in one traversal (O(n))”

---


The **HashMap + Stack approach** is another way to solve this **iteratively** (without recursion). It’s still **O(n)** like the optimal recursive solution, but uses an explicit stack.

---

# ✅ HashMap + Stack Solution (Iterative DFS)

### 💡 Idea:

We simulate **post-order traversal** (Left → Right → Node) using a stack.

👉 Why post-order?
Because we need:

* left height
* right height
  before computing current node height

---

### 🧠 How it works:

1. Use a **Stack** to traverse the tree
2. Use a **HashMap** to store height of each node
3. Process nodes only **after children are processed**
4. At each node:

   * get left height from map
   * get right height from map
   * update diameter
   * store current node height in map

---

# ✅ Code (HashMap + Stack)

```java
import java.util.*;

class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        Map<TreeNode, Integer> map = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        int diameter = 0;

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();

            // If children are already processed
            if ((node.left == null || map.containsKey(node.left)) &&
                (node.right == null || map.containsKey(node.right))) {

                stack.pop();

                int left = map.getOrDefault(node.left, 0);
                int right = map.getOrDefault(node.right, 0);

                // update diameter
                diameter = Math.max(diameter, left + right);

                // store height
                map.put(node, 1 + Math.max(left, right));

            } else {
                // push children first (post-order simulation)
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
            }
        }

        return diameter;
    }
}
```

---

# ⏱ Complexity

* Time: **O(n)** ✅
* Space: **O(n)** (map + stack)

---

# 🔥 Intuition (Very Simple)

Think like this:

👉 Recursion uses **call stack automatically**
👉 Here we are **manually controlling stack**

* HashMap = “store height of processed nodes”
* Stack = “control traversal order”

---

# ⚖️ Comparison

| Approach            | Time  | Space | Difficulty |
| ------------------- | ----- | ----- | ---------- |
| Brute Force         | O(n²) | O(h)  | Easy       |
| Optimal (Recursion) | O(n)  | O(h)  | Medium     |
| HashMap + Stack     | O(n)  | O(n)  | Hard       |

---

# ⚡ When to use this?

* If interviewer says:

  > “Solve without recursion”
* Or asks:

  > “Convert recursive to iterative”

---

# 🚨 Important Insight

👉 This is NOT better than recursion
👉 It’s just an **alternative implementation**

---
</p>
