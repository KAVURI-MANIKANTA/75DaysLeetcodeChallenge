<h2><a href="https://leetcode.com/problems/invert-binary-tree">226. Invert Binary Tree</a></h2><h3>Easy</h3><hr><p>Given the <code>root</code> of a binary tree, invert the tree, and return <em>its root</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/03/14/invert1-tree.jpg" style="width: 500px; height: 165px;" />
<pre>
<strong>Input:</strong> root = [4,2,7,1,3,6,9]
<strong>Output:</strong> [4,7,2,9,6,3,1]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/03/14/invert2-tree.jpg" style="width: 500px; height: 120px;" />
<pre>
<strong>Input:</strong> root = [2,1,3]
<strong>Output:</strong> [2,3,1]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> root = []
<strong>Output:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[0, 100]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
</ul>
<p>

# 🌳 First: What is a Binary Tree?

Each node has:

```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
}
```

Example:

```
    4
   / \
  2   7
```

👉 `4.left = 2`
👉 `4.right = 7`

---

# 🎯 What is “Invert Tree”?

👉 Swap left and right of **every node**

Before:

```
    4
   / \
  2   7
```

After:

```
    4
   / \
  7   2
```

---

# 🔹 CODE 1: USING QUEUE (LEVEL ORDER / BFS)

```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root==null) return root;

        Queue<TreeNode> QTree = new LinkedList<>();
        QTree.add(root);

        while(!QTree.isEmpty()){
            TreeNode QRoot = QTree.poll();

            TreeNode temp = QRoot.left;
            QRoot.left = QRoot.right;
            QRoot.right = temp;

            if(QRoot.left!=null) QTree.add(QRoot.left);
            if(QRoot.right!=null) QTree.add(QRoot.right);
        }
        return root;
    }
}
```
🔸 Brute Force Approach (Using Extra Space - BFS)
💡 Idea:
Use a queue (level order traversal)
For each node:
Swap left and right
Add children to queue
⏱ Time Complexity: O(n)
🧠 Space Complexity: O(n) (queue)
---

## 🧠 Idea (Very Simple)

👉 “Visit nodes one by one and swap”

👉 Use **Queue** → like a line (FIFO)

---

## 🔁 Step-by-Step (Small Example)

Tree:

```
    4
   / \
  2   7
```

---

### ✅ Step 1: Add root to queue

```
Queue = [4]
```

---

### ✅ Step 2: Take 4 out

```
QRoot = 4
```

Swap:

```
4.left = 7
4.right = 2
```

Add children:

```
Queue = [7, 2]
```

---

### ✅ Step 3: Take 7

Swap (if it has children)

---

### ✅ Step 4: Take 2

Swap

---

### ✅ Done

---

## 🔥 Simple Understanding

👉 Queue helps you go **level by level**
👉 For every node:

* swap
* push children

---

# 🔹 CODE 2: USING RECURSION (DFS)

```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root==null) return null;

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }
}
```
🔸 Optimal Approach (Recursion – DFS)
💡 Idea:
Recursively invert left and right subtree
Then swap

👉 This is the cleanest and most used solution

⏱ Time Complexity: O(n)
🧠 Space Complexity: O(h) (recursion stack, h = height)
---

## 🧠 Idea (Very Simple)

👉 “Go to bottom first, then swap while coming back”

---

## 🔁 Step-by-Step Thinking

Take:

```
    4
   / \
  2   7
```

---

### ✅ Step 1: Start at 4

👉 Go LEFT → 2
👉 Go RIGHT → 7

---

### ✅ Step 2: Go to node 2

👉 Go left → null
👉 Go right → null
👉 return 2

---

### ✅ Step 3: Go to node 7

Same → return 7

---

### ✅ Step 4: Back to 4

Now:

```
left = 2
right = 7
```

Swap:

```
left = 7
right = 2
```

---

## 🔥 Simple Understanding

👉 Recursion works like:

```
Go down ↓
Come back ↑
Swap
```

---

# ⚡ KEY DIFFERENCE (VERY IMPORTANT)

| Feature  | Queue Code     | Recursion Code |
| -------- | -------------- | -------------- |
| Style    | Iterative      | Recursive      |
| Movement | Level by level | Bottom to top  |
| Tool     | Queue          | Call stack     |

---

# 🧠 SUPER SIMPLE ANALOGY

### Queue version:

👉 Like visiting people in a line and swapping their bags

### Recursion version:

👉 Like going to last child, fixing it, then coming back to parent

---

# 🚨 COMMON BEGINNER CONFUSION

### ❓ Why this?

```java
TreeNode left = invertTree(root.left);
TreeNode right = invertTree(root.right);
```

👉 Because:

* First fix children
* Then swap

---

# 🎯 FINAL ONE-LINE MEMORY

👉 **Queue:** “Visit → Swap → Add children”
👉 **Recursion:** “Left → Right → Swap”

---
Nice 👍 this is the classic example for **LeetCode 226**. Let’s go step-by-step very clearly.

---

# 🌳 Given Tree

Your input:

```
4 -> 2, 7 -> 1, 3, 6, 9
```

Means:

```
        4
       / \
      2   7
     / \ / \
    1  3 6  9
```

---

# 🔁 Goal: Invert Tree

Final answer should be:

```
        4
       / \
      7   2
     / \ / \
    9  6 3  1
```

---

# 🔥 Recursion Flow (Step-by-Step)

We use:

```java
invertTree(root.left);
invertTree(root.right);
swap(root.left, root.right);
```

---

## 🧠 Step 1: Start at root (4)

Call:

```
invertTree(4)
```

👉 First go **LEFT (2)**

---

## 🧠 Step 2: Node 2

Call:

```
invertTree(2)
```

👉 Go LEFT → node 1

---

## 🧠 Step 3: Node 1 (Leaf)

Call:

```
invertTree(1)
```

* left = null
* right = null
  👉 return 1 (no change)

---

## 🧠 Step 4: Back to node 2 → go RIGHT (3)

Call:

```
invertTree(3)
```

* left = null
* right = null
  👉 return 3

---

## 🔁 Step 5: Now swap at node 2

Before:

```
  2
 / \
1   3
```

After swap:

```
  2
 / \
3   1
```

---

## 🧠 Step 6: Back to root (4) → go RIGHT (7)

Call:

```
invertTree(7)
```

👉 Go LEFT → node 6

---

## 🧠 Step 7: Node 6 (Leaf)

Return 6

---

## 🧠 Step 8: Node 9 (Leaf)

Return 9

---

## 🔁 Step 9: Swap at node 7

Before:

```
  7
 / \
6   9
```

After:

```
  7
 / \
9   6
```

---

## 🔁 Step 10: Final swap at root (4)

Before:

```
    4
   / \
  2   7
```

After:

```
    4
   / \
  7   2
```

---

# ✅ Final Tree

```
        4
       / \
      7   2
     / \ / \
    9  6 3  1
```

---

# 🧠 What You Should Understand

👉 Recursion always:

* Goes to **leaf nodes first**
* Then swaps while coming back (bottom-up)

---

# ⚡ Shortcut Memory Trick

👉 “Go down → come back → swap”

</p>
