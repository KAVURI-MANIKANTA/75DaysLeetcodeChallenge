<h2><a href="https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree">1116. Maximum Level Sum of a Binary Tree</a></h2><h3>Medium</h3><hr><p>Given the <code>root</code> of a binary tree, the level of its root is <code>1</code>, the level of its children is <code>2</code>, and so on.</p>

<p>Return the <strong>smallest</strong> level <code>x</code> such that the sum of all the values of nodes at level <code>x</code> is <strong>maximal</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2019/05/03/capture.JPG" style="width: 200px; height: 175px;" />
<pre>
<strong>Input:</strong> root = [1,7,0,7,-8,null,null]
<strong>Output:</strong> 2
<strong>Explanation: </strong>
Level 1 sum = 1.
Level 2 sum = 7 + 0 = 7.
Level 3 sum = 7 + -8 = -1.
So we return the level with the maximum sum which is level 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [989,null,10250,98693,-89388,null,null,null,-32127]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>
<p>
==========================================================================================================
---

# 🧠 Problem in one line

> Find the **level (1-indexed)** that has the **maximum sum of node values**

---

# ✅ Your Code

```java id="u7h1r5"
class Solution {
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        int res = 0;
        if(root==null) return res;

        q.add(root);
        int ans = Integer.MIN_VALUE;
        int count = 0;

        while(!q.isEmpty()){
            count++;
            int n = q.size();
            int sum = 0;

            for(int i=0; i<n; i++){
                TreeNode peek = q.poll();
                sum = sum + peek.val;

                if(peek.left!=null) q.add(peek.left);
                if(peek.right!=null) q.add(peek.right);
            }

            if(ans < sum){
                res = count;
                ans = sum;
            }
        }
        return res;
    }
}
```

---

# 🔍 Step-by-step explanation

## 1. BFS Setup

```java id="4q6xq6"
Queue<TreeNode> q = new LinkedList<>();
q.add(root);
```

👉 You are doing **level order traversal (BFS)**

---

## 2. Level Counter

```java id="s3w6si"
count++;
```

👉 Tracks:

```id="l41y3r"
Level 1, Level 2, Level 3...
```

---

## 3. Process one level

```java id="1v6jpn"
int n = q.size();
int sum = 0;
```

👉 `n` = number of nodes in current level

---

## 4. Traverse level

```java id="0o4pqb"
for(int i=0; i<n; i++){
    TreeNode peek = q.poll();
    sum += peek.val;
}
```

👉 Compute **sum of that level**

---

## 5. Track maximum

```java id="2z4l8j"
if(ans < sum){
    res = count;
    ans = sum;
}
```

👉 If current level sum is bigger:

* Update **max sum**
* Store **level number**

---

# 🧠 Example

Tree:

```id="l0m4g8"
        1
       / \
      7   0
     / \
    7  -8
```

---

### Level-wise sums:

```id="j7l9xq"
Level 1 → 1
Level 2 → 7 + 0 = 7
Level 3 → 7 + (-8) = -1
```

👉 Maximum = **7 at Level 2**

---

# 🔥 Final Output

```id="k6w0nq"
2
```

---

# ⚔️ Pattern Recognition

This is just:

> **Level Order Traversal + Aggregation (sum)**

---
---

# 🚀 Final Insight

👉 Whenever problem says:

* “level”
* “sum”
* “max/min per level”

➡️ Think:

> **BFS + compute something per level**

---
===================================================================================================================
Here’s the **DFS version** for **Maximum Level Sum of a Binary Tree** — this is a great upgrade from BFS thinking 👇

---

# 🧠 Idea (DFS Version)

Instead of processing level by level using a queue:

👉 We:

* Traverse tree using **DFS**
* Keep track of **level**
* Store **sum of each level in a list**

---

# ✅ DFS Code

```java id="n9j2qk"
class Solution {
    public int maxLevelSum(TreeNode root) {
        List<Integer> levelSum = new ArrayList<>();
        dfs(root, 0, levelSum);

        int maxSum = Integer.MIN_VALUE;
        int resLevel = 0;

        for(int i = 0; i < levelSum.size(); i++){
            if(levelSum.get(i) > maxSum){
                maxSum = levelSum.get(i);
                resLevel = i + 1; // 1-based index
            }
        }

        return resLevel;
    }

    public void dfs(TreeNode root, int level, List<Integer> levelSum){
        if(root == null) return;

        if(level == levelSum.size()){
            levelSum.add(root.val);   // first node at this level
        } else {
            levelSum.set(level, levelSum.get(level) + root.val);
        }

        dfs(root.left, level + 1, levelSum);
        dfs(root.right, level + 1, levelSum);
    }
}
```

---

# 🔍 How it works (Step-by-step)

### Example tree

```id="5l1c3x"
        1
       / \
      7   0
     / \
    7  -8
```

---

## DFS traversal order

```id="1z2r6s"
1 → 7 → 7 → -8 → 0
```

---

## Level-wise updates

### Level 0:

```id="v6t6u3"
levelSum = [1]
```

---

### Level 1:

```id="fjw2q9"
levelSum = [1, 7]
then add 0 → [1, 7]
```

---

### Level 2:

```id="h6q0e9"
levelSum = [1, 7, 7]
then add -8 → [1, 7, -1]
```

---

# 🔥 Final level sums

```id="5u2ncz"
[1, 7, -1]
```

👉 Max = **7 → Level 2**

---

# 🧠 Key Logic (VERY IMPORTANT)

### 1. First time visiting level

```java id="33zsvq"
if(level == levelSum.size())
```

👉 Create new level

---

### 2. Already visited level

```java id="e1xt3o"
levelSum.set(level, levelSum.get(level) + root.val);
```

👉 Add to existing sum

---

# ⚔️ DFS vs BFS

| Feature        | BFS     | DFS                    |
| -------------- | ------- | ---------------------- |
| Approach       | Queue   | Recursion              |
| Level handling | Natural | Manual                 |
| Space          | Queue   | Recursion stack + list |
| Interview ease | Easy    | Medium                 |

---

# 🎯 Pattern Insight

👉 This is same pattern as:

* Level Order (102)
* Average of Levels (637)

Only difference:

```id="2q6n4o"
Instead of storing nodes → store SUM
```

---

# 🚀 Final Intuition

> “DFS doesn’t go level by level…
> But I *simulate levels* using an index and store results in a list.”

---

# 🔥 Pro Tip

If interviewer asks:

> “Can you do it without BFS?”

👉 This DFS solution is your answer.

---
</p>
