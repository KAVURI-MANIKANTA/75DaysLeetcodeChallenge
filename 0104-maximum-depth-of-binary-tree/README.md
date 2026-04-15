<h2><a href="https://leetcode.com/problems/maximum-depth-of-binary-tree">104. Maximum Depth of Binary Tree</a></h2><h3>Easy</h3><hr><p>Given the <code>root</code> of a binary tree, return <em>its maximum depth</em>.</p>

<p>A binary tree&#39;s <strong>maximum depth</strong>&nbsp;is the number of nodes along the longest path from the root node down to the farthest leaf node.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/26/tmp-tree.jpg" style="width: 400px; height: 277px;" />
<pre>
<strong>Input:</strong> root = [3,9,20,null,null,15,7]
<strong>Output:</strong> 3
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [1,null,2]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[0, 10<sup>4</sup>]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
</ul>
<p>

# 🌳 1. What is a Binary Tree?

A **tree** looks like this:

```
        1
       / \
      2   3
     / \
    4   5
```

### Important terms:

* **Root** → Top node (1)
* **Children** → nodes below (2, 3)
* **Leaf** → node with no children (4, 5, 3)

---

# ❓ What is "Max Depth"?

👉 **Max Depth = Number of levels from root to deepest leaf**

For above tree:

```
Level 1 → 1
Level 2 → 2, 3
Level 3 → 4, 5
```

✅ **Answer = 3**

---

# 🔵 CODE 1: BFS (Queue Approach)

```java
if(root==null) return 0;
Queue<TreeNode> qt = new LinkedList<>();
qt.add(root);
int ans = 0;

while(!qt.isEmpty()){
    ans++;
    int n = qt.size();

    for(int i=0; i<n; i++){
        TreeNode PeekN = qt.poll();
        if(PeekN.left!=null) qt.add(PeekN.left);
        if(PeekN.right!=null) qt.add(PeekN.right);
    }
}
return ans;
```

---

## 🧠 Idea (Very Simple)

👉 We go **level by level**

Like:

* First visit level 1
* Then level 2
* Then level 3

Each loop = **one level**

---

## 🔁 Dry Run (Step by Step)

Tree:

```
        1
       / \
      2   3
     / \
    4   5
```

---

### Step 1:

Queue = `[1]`

```
ans = 0
```

Enter loop:

```
ans = 1   (Level 1)
n = 1
```

Process:

* Remove 1
* Add 2, 3

Queue becomes:

```
[2, 3]
```

---

### Step 2:

```
ans = 2   (Level 2)
n = 2
```

Process:

* Remove 2 → add 4,5
* Remove 3 → no children

Queue becomes:

```
[4, 5]
```

---

### Step 3:

```
ans = 3   (Level 3)
n = 2
```

Process:

* Remove 4 → no children
* Remove 5 → no children

Queue becomes:

```
[]
```

---

### Loop ends → return:

```
ans = 3 ✅
```

---

## 🔑 Key Concept

* `qt.size()` → number of nodes in **current level**
* `for loop` → process entire level
* `ans++` → counting levels

---

# 🔴 CODE 2: Recursion (DFS)

```java
if(root==null) return 0;

int left = maxDepth(root.left);
int right = maxDepth(root.right);

return 1 + Math.max(left,right);
```

---

## 🧠 Idea (Super Simple)

👉 "Depth = 1 + max(left depth, right depth)"

---

## 🔁 Dry Run

Tree:

```
        1
       / \
      2   3
     / \
    4   5
```

---

### Start from root (1)

```
maxDepth(1)
= 1 + max(depth(2), depth(3))
```

---

### Go left → node 2

```
maxDepth(2)
= 1 + max(depth(4), depth(5))
```

---

### Node 4:

```
maxDepth(4)
= 1 + max(0, 0)
= 1
```

---

### Node 5:

```
maxDepth(5)
= 1 + max(0, 0)
= 1
```

---

### Back to node 2:

```
maxDepth(2)
= 1 + max(1, 1)
= 2
```

---

### Now node 3:

```
maxDepth(3)
= 1 + max(0, 0)
= 1
```

---

### Back to root:

```
maxDepth(1)
= 1 + max(2, 1)
= 3 ✅
```

---

# ⚖️ BFS vs DFS (Important for Interviews)

| Feature            | BFS (Queue)    | DFS (Recursion) |
| ------------------ | -------------- | --------------- |
| Idea               | Level by level | Go deep first   |
| Code               | Longer         | Short & clean   |
| Memory             | More (queue)   | Less            |
| Easy to understand | Medium         | Easy            |

---

# 🎯 When to Use What?

* Want **level-based processing** → use BFS
* Want **simple code** → use DFS ✅ (most used)

---

# 🔥 Final Intuition

👉 BFS = “Count levels one by one”
👉 DFS = “Find longest path”



✅ **DFS (recursion) = Optimal solution**
❌ **Not brute force**

---

# 🧠 Then what is “Brute Force” here?

For this problem (**Max Depth of Binary Tree**):

👉 There is **NO separate worse brute force solution**

Why?

Because:

* To find depth, you **must visit all nodes**
* So minimum work = **O(n)**

👉 That means:

> Any correct solution will already be optimal

---

# 🔍 Why confusion happens?

Because in some problems:

* DFS = brute force (when exploring all possibilities)
* But here:
  👉 DFS is just a **natural traversal**, not wasteful

---

# ⚖️ Correct Classification

| Approach        | Type      | Time | Space |
| --------------- | --------- | ---- | ----- |
| DFS (Recursion) | ✅ Optimal | O(n) | O(h)  | DFS is optimal than bfs
| BFS (Queue)     | ✅ Optimal | O(n) | O(n)  |

---

# 🔥 So what is the difference?

👉 Not brute vs optimal
👉 It’s about **method**

### 1. DFS (Recursion)

* Goes deep first
* Uses call stack
* Less space ✅

### 2. BFS (Queue)

* Goes level by level
* Uses queue
* More space ❌

---

# 🎯 Interview Answer (Perfect)

If interviewer asks:

👉 “Brute force and optimal?”

You say:

> “For this problem, there is no separate brute force.
> Any solution must visit all nodes, so both DFS and BFS are optimal with O(n) time.”

---

# 🧠 Final Clarity

👉 DFS is called brute force **only when**:

* It tries all combinations (like subsets, permutations)

👉 Here:

* DFS is just **traversing tree once**
  ✔️ So it’s optimal

---

# 💡 Simple Analogy

Think like this:

* Tree depth = “height of building”
* You must check all floors at least once

👉 No shortcut → so every method is already optimal

</p>
