<h2><a href="https://leetcode.com/problems/binary-tree-right-side-view">199. Binary Tree Right Side View</a></h2><h3>Medium</h3><hr><p>Given the <code>root</code> of a binary tree, imagine yourself standing on the <strong>right side</strong> of it, return <em>the values of the nodes you can see ordered from top to bottom</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">root = [1,2,3,null,5,null,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,3,4]</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2024/11/24/tmpd5jn43fs-1.png" style="width: 400px; height: 207px;" /></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">root = [1,2,3,4,null,null,null,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,3,4,5]</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2024/11/24/tmpkpe40xeh-1.png" style="width: 400px; height: 214px;" /></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">root = [1,null,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,3]</span></p>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">root = []</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[0, 100]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
</ul>
<p>
===========================================================================================
	
---


# 🧠 Problem in one line

> From each level of the tree, return the **node visible from the right side**

---

# 🌳 Example

```
    1
   / \
  2   3
   \   \
    5   4
```

👉 Right view:

```
[1, 3, 4]
```

---

# 🟢 1. BFS Approach (Level Order)

### ✅ Your Code

```java
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        if(root==null) return res;

        q.add(root);

        while(!q.isEmpty()){
            int n = q.size();

            for(int i=0; i<n; i++){
                TreeNode peek = q.poll();

                if(i==n-1) res.add(peek.val); // ⭐ key line

                if(peek.left!=null) q.add(peek.left);
                if(peek.right!=null) q.add(peek.right);
            }
        }
        return res;
    }
}
```

---

## 💡 Core Idea

* BFS processes **level by level**
* At each level:

  * The **last node** = rightmost node

---

## 🔍 Key Line

```java
if(i == n - 1)
```

👉 Means:

> “Pick the last node in this level”

---

## 🧠 Step-by-step

Level order:

```
Level 0 → [1]       → pick 1
Level 1 → [2, 3]    → pick 3
Level 2 → [5, 4]    → pick 4
```

---

## ✅ Why it works

* You process nodes **left → right**
* So last node = **rightmost**

---

# 🔵 2. DFS Approach (Right-first Traversal)

### ✅ Your Code

```java
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root,0,res);
        return res;
    }

    public void dfs(TreeNode root, int l, List<Integer> res){
        if(root==null) return;

        if(l==res.size()) res.add(root.val); // ⭐ key line

        dfs(root.right,l+1,res);
        dfs(root.left,l+1,res);
    }
}
```

---

## 💡 Core Idea

* DFS goes **depth-wise**
* Visit **right first**
* Add **first node seen at each level**

---

## 🔍 Key Line

```java
if(l == res.size())
```

👉 Means:

> “If this level is visited first time → add node”

---

## 🔥 Why RIGHT first?

```java
dfs(root.right, l+1, res);
dfs(root.left, l+1, res);
```

👉 So at each level:

* You visit **rightmost node first**
* Then left nodes later (ignored)

---

## 🧠 Step-by-step

Traversal order:

```
1 → 3 → 4 → 2 → 5
```

Levels:

```
Level 0 → 1 → add
Level 1 → 3 → add
Level 2 → 4 → add
```

Later:

```
2, 5 → skipped (already visited level)
```

---

# ⚔️ BFS vs DFS

| Feature   | BFS          | DFS             |
| --------- | ------------ | --------------- |
| Approach  | Level order  | Depth-first     |
| Trick     | Last node    | First node      |
| Order     | Left → Right | Right → Left    |
| Condition | `i == n-1`   | `level == size` |

---

# 🎯 Pattern Understanding (VERY IMPORTANT)

| Goal             | Trick             |
| ---------------- | ----------------- |
| Right view (BFS) | Take last node    |
| Right view (DFS) | Visit right first |
| Left view (DFS)  | Visit left first  |

---

# 🔥 Final Intuition

### BFS Thinking:

> “I’ll go level by level and pick the last node.”

### DFS Thinking:

> “I’ll go right first so I see the rightmost node first.”

---

# 🚀 Interview Tip

If asked:

> “Give both approaches”

Say:

1. BFS → take last node of each level
2. DFS → right-first + first node per level

---
</p>
