<h2><a href="https://leetcode.com/problems/kth-smallest-element-in-a-bst">230. Kth Smallest Element in a BST</a></h2><h3>Medium</h3><hr><p>Given the <code>root</code> of a binary search tree, and an integer <code>k</code>, return <em>the</em> <code>k<sup>th</sup></code> <em>smallest value (<strong>1-indexed</strong>) of all the values of the nodes in the tree</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/28/kthtree1.jpg" style="width: 212px; height: 301px;" />
<pre>
<strong>Input:</strong> root = [3,1,4,null,2], k = 1
<strong>Output:</strong> 1
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/28/kthtree2.jpg" style="width: 382px; height: 302px;" />
<pre>
<strong>Input:</strong> root = [5,3,6,2,4,null,null,1], k = 3
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is <code>n</code>.</li>
	<li><code>1 &lt;= k &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?</p>
<p>
==============================================================================================
---

# 🔷 What is the problem? (Kth Smallest Element in a BST)

You are given a **Binary Search Tree (BST)** and a number `k`.

👉 You need to return the **k-th smallest element** in that tree.

---

# 🔷 First understand BST (VERY IMPORTANT)

A **Binary Search Tree** follows this rule:

```
Left subtree  <  Root  <  Right subtree
```

### Example:

```
        5
       / \
      3   7
     / \   \
    2   4   8
```

If you sort all values:

```
2, 3, 4, 5, 7, 8
```

👉 1st smallest = 2
👉 2nd smallest = 3
👉 3rd smallest = 4
👉 4th smallest = 5

---

# 🔷 Key Concept (MOST IMPORTANT)

### ⭐ Inorder Traversal of BST gives SORTED ORDER

```
Left → Root → Right
```

So:

```
Inorder traversal = sorted list
```

---

# 🔷 Brute Force Solution (Your Code)

### Idea:

1. Do inorder traversal
2. Store elements in list
3. Return `(k-1)` index

---

### Code:

```java
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res.get(k - 1);
    }

    public void inorder(TreeNode root, List<Integer> res) {
        if (root == null) return;

        inorder(root.left, res);   // left
        res.add(root.val);         // root
        inorder(root.right, res);  // right
    }
}
```

---

### Dry Run:

Tree:

```
        5
       / \
      3   7
```

Inorder:

```
[3, 5, 7]
```

If `k = 2`
👉 answer = `5`

---

### Complexity:

* Time: **O(n)**
* Space: **O(n)** (list)

---

### ❗ Problem with this approach:

👉 You store ALL elements
👉 But you only need ONE (k-th)

---

# 🔷 Optimal Solution (IMPORTANT FOR INTERVIEWS)

### Idea:

👉 Don’t store everything
👉 Just count while traversing

---

### 🔥 Intuition:

Since inorder gives sorted order:

```
1st visited → smallest
2nd visited → 2nd smallest
...
k-th visited → answer
```

---

### Code:

```java
class Solution {
    int count = 0;
    int ans = -1;

    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return ans;
    }

    public void inorder(TreeNode root, int k) {
        if (root == null) return;

        inorder(root.left, k);

        count++;
        if (count == k) {
            ans = root.val;
            return;
        }

        inorder(root.right, k);
    }
}
```

---

### 🔥 Dry Run:

Tree:

```
        5
       / \
      3   7
```

k = 2

Traversal:

```
visit 3 → count=1
visit 5 → count=2 ✅ ANSWER
```

---

### Complexity:

* Time: **O(h + k)** (best case early stop)
* Space: **O(h)** (recursion stack)

---

# 🔷 Even Better (Advanced Optimal – Iterative)

👉 Avoid recursion (important for interviews)

```java
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();

        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            k--;

            if (k == 0) return root.val;

            root = root.right;
        }
    }
}
```

---

# 🔷 Easy Way to Remember (Exam Trick)

👉 Always remember this pattern:

| Problem Type       | Solution                   |
| ------------------ | -------------------------- |
| BST + kth smallest | **Inorder traversal**      |
| Need full list     | store                      |
| Need only kth      | **count while traversing** |

---

# 🔷 Final Summary (REVISION)

* BST → left < root < right
* Inorder → sorted order
* k-th smallest → k-th element in inorder

### Approaches:

1. **Brute Force** → store list
2. **Optimal** → count during traversal
3. **Best Interview** → iterative stack

---
```
| Approach          | Time     | Space |
| ----------------- | -------- | ----- |
| Brute (list)      | O(n)     | O(n)  |
| Recursive optimal | O(h + k) | O(h)  |
| Iterative (stack) | O(h + k) | O(h)  |
```
</p>
