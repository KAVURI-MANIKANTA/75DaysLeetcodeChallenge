<h2><a href="https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree">236. Lowest Common Ancestor of a Binary Tree</a></h2><h3>Medium</h3><hr><p>Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.</p>

<p>According to the <a href="https://en.wikipedia.org/wiki/Lowest_common_ancestor" target="_blank">definition of LCA on Wikipedia</a>: &ldquo;The lowest common ancestor is defined between two nodes <code>p</code> and <code>q</code> as the lowest node in <code>T</code> that has both <code>p</code> and <code>q</code> as descendants (where we allow <b>a node to be a descendant of itself</b>).&rdquo;</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2018/12/14/binarytree.png" style="width: 200px; height: 190px;" />
<pre>
<strong>Input:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
<strong>Output:</strong> 3
<strong>Explanation:</strong> The LCA of nodes 5 and 1 is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2018/12/14/binarytree.png" style="width: 200px; height: 190px;" />
<pre>
<strong>Input:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
<strong>Output:</strong> 5
<strong>Explanation:</strong> The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> root = [1,2], p = 1, q = 2
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[2, 10<sup>5</sup>]</code>.</li>
	<li><code>-10<sup>9</sup> &lt;= Node.val &lt;= 10<sup>9</sup></code></li>
	<li>All <code>Node.val</code> are <strong>unique</strong>.</li>
	<li><code>p != q</code></li>
	<li><code>p</code> and <code>q</code> will exist in the tree.</li>
</ul>
<p>
	
# LeetCode 236 — Lowest Common Ancestor of a Binary Tree

LeetCode

## Problem

Given a Binary Tree, find the **Lowest Common Ancestor (LCA)** of two nodes `p` and `q`.

### Lowest Common Ancestor Meaning

The lowest node in the tree that has both `p` and `q` as descendants.

---

# Brute Force Solution

## Idea

1. Find path from root to `p`
2. Find path from root to `q`
3. Compare both paths
4. Last common node is the LCA

---

# Brute Force Code (Java)

```java
class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        List<TreeNode> path1 = new ArrayList<>();
        List<TreeNode> path2 = new ArrayList<>();

        findPath(root, p, path1);
        findPath(root, q, path2);

        TreeNode ans = null;

        for(int i = 0; i < Math.min(path1.size(), path2.size()); i++) {

            if(path1.get(i) == path2.get(i)) {
                ans = path1.get(i);
            } else {
                break;
            }
        }

        return ans;
    }

    private boolean findPath(TreeNode root, TreeNode target, List<TreeNode> path) {

        if(root == null) {
            return false;
        }

        path.add(root);

        if(root == target) {
            return true;
        }

        if(findPath(root.left, target, path) ||
           findPath(root.right, target, path)) {
            return true;
        }

        path.remove(path.size() - 1);

        return false;
    }
}
```

---

# Brute Force Explanation

## Step-by-Step

Suppose tree is:

```text
        3
       / \
      5   1
     / \ / \
    6  2 0  8
```

Find LCA of `5` and `1`

### Path to 5

```text
3 → 5
```

### Path to 1

```text
3 → 1
```

Compare paths:

```text
3 = 3 ✔
5 ≠ 1 ❌
```

Last common node = `3`

So answer is `3`

---

# Dry Run

## findPath(root, 5)

```text
path = [3]
path = [3,5]
Found target
```

## findPath(root, 1)

```text
path = [3]
path = [3,1]
Found target
```

Compare both paths:

```text
3 same
Next different
```

Answer = `3`

---

# Time Complexity

## findPath()

Takes `O(N)`

We call it 2 times:

```text
O(N) + O(N) = O(N)
```

Comparing paths:

```text
O(N)
```

### Total TC

```text
O(N)
```

---

# Space Complexity

Two path lists + recursion stack

```text
O(N)
```

---

# Optimal Solution

## Main Idea

While traversing:

* If current node is `p` or `q`, return it
* Search left subtree
* Search right subtree

### Cases

1. Both left and right return non-null
   → current node is LCA

2. Only one side non-null
   → return that side

3. Both null
   → return null

---

# Optimal Code (Java)

```java
class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if(root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // p and q found in different sides
        if(left != null && right != null) {
            return root;
        }

        // return non-null side
        return (left != null) ? left : right;
    }
}
```

---

# Optimal Solution Explanation

## Important Logic

### Base Case

```java
if(root == null || root == p || root == q)
```

If:

* tree ended → return null
* found `p`
* found `q`

Return current node.

---

## Recursive Search

```java
TreeNode left = lowestCommonAncestor(root.left, p, q);
TreeNode right = lowestCommonAncestor(root.right, p, q);
```

Search both sides.

---

## Case 1

```java
if(left != null && right != null)
```

Means:

* one node found in left subtree
* another found in right subtree

So current root is LCA.

---

## Case 2

```java
return (left != null) ? left : right;
```

If only one side has answer, return it upward.

---

# Dry Run

Tree:

```text
        3
       / \
      5   1
```

Find LCA of `5` and `1`

---

## At Node 5

```text
root == p
return 5
```

---

## At Node 1

```text
root == q
return 1
```

---

## At Node 3

```text
left = 5
right = 1
```

Both non-null:

```text
return 3
```

Answer = `3`

---

# Time Complexity

Every node visited once.

```text
O(N)
```

---

# Space Complexity

Recursive stack height.

Worst case skew tree:

```text
O(N)
```

Balanced tree:

```text
O(H)
```

where `H = height of tree`

---

# Which Solution is Better?

| Solution    | TC   | SC   | Extra Space    | Better? |
| ----------- | ---- | ---- | -------------- | ------- |
| Brute Force | O(N) | O(N) | Path lists     | ❌       |
| Optimal     | O(N) | O(H) | No extra lists | ✅       |

---

# Interview Points

## Why Optimal Works?

Because:

* recursion searches both subtrees
* first node where both sides return non-null is the LCA

---

# Important Edge Cases

## 1. p is ancestor of q

Example:

```text
    5
   /
  6
```

LCA(5,6) = `5`

Optimal solution handles this automatically.

---

## 2. Tree has only one node

```text
1
```

LCA(1,1) = `1`

---

# Easy One-Line Memory Trick

```text
If left and right both return something,
current node is the answer.
```

</p>
