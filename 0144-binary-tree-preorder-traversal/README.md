<h2><a href="https://leetcode.com/problems/binary-tree-preorder-traversal">144. Binary Tree Preorder Traversal</a></h2><h3>Easy</h3><hr><p>Given the <code>root</code> of a binary tree, return <em>the preorder traversal of its nodes&#39; values</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">root = [1,null,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,3]</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2024/08/29/screenshot-2024-08-29-202743.png" style="width: 200px; height: 264px;" /></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">root = [1,2,3,4,5,null,8,null,null,6,7,9]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,4,5,6,7,3,8,9]</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2024/08/29/tree_2.png" style="width: 350px; height: 286px;" /></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">root = []</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">root = [1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[0, 100]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Recursive solution is trivial, could you do it iteratively?</p>
<p>
	
# LeetCode 144 — Binary Tree Preorder Traversal

Preorder Traversal order:

```text
Root → Left → Right
```

---

# Brute Force Solution (Recursive + Helper Method)

## Idea

* Visit root first
* Recursively traverse left subtree
* Recursively traverse right subtree
* Use helper method to share same list

---

## Code

```java
class Solution {

    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<>();

        preorder(root, res);

        return res;
    }

    public void preorder(TreeNode root, List<Integer> res) {

        if(root == null) {
            return;
        }

        res.add(root.val);          // Root

        preorder(root.left, res);  // Left

        preorder(root.right, res); // Right
    }
}
```

---

# Explanation

## Step 1

Create empty list:

```java
List<Integer> res = new ArrayList<>();
```

---

## Step 2

Call helper function:

```java
preorder(root, res);
```

---

## Step 3

Base condition:

```java
if(root == null)
```

Stop recursion.

---

## Step 4

Add current node:

```java
res.add(root.val);
```

---

## Step 5

Traverse left subtree:

```java
preorder(root.left, res);
```

---

## Step 6

Traverse right subtree:

```java
preorder(root.right, res);
```

---

# Dry Run

Tree:

```text
       1
      / \
     2   3
    / \
   4   5
```

Traversal:

```text
1 → 2 → 4 → 5 → 3
```

Output:

```text
[1,2,4,5,3]
```

---

# Time Complexity

```text
O(n)
```

Because every node visited once.

---

# Space Complexity

Recursive stack space:

```text
O(h)
```

Where:

* `h = height of tree`

Worst case skew tree:

```text
O(n)
```

Balanced tree:

```text
O(log n)
```

---

# Optimal Solution (Iterative Using Stack)

## Idea

Use stack instead of recursion.

Since stack is LIFO:

* push right first
* push left second

So left gets processed first.

---

## Code

```java
class Solution {

    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<>();

        if(root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);

        while(!stack.isEmpty()) {

            TreeNode node = stack.pop();

            res.add(node.val);

            // Push right first
            if(node.right != null) {
                stack.push(node.right);
            }

            // Push left second
            if(node.left != null) {
                stack.push(node.left);
            }
        }

        return res;
    }
}
```

---

# Explanation

## Step 1

Push root into stack.

```java
stack.push(root);
```

---

## Step 2

Pop node from stack.

```java
TreeNode node = stack.pop();
```

---

## Step 3

Add node value.

```java
res.add(node.val);
```

---

## Step 4

Push right child first.

```java
stack.push(node.right);
```

---

## Step 5

Push left child next.

```java
stack.push(node.left);
```

Because stack is LIFO, left processed first.

---

# Dry Run

Tree:

```text
    1
   / \
  2   3
```

---

## Initial

```text
stack = [1]
res = []
```

---

## Pop 1

```text
res = [1]
```

Push 3 then 2

```text
stack = [3,2]
```

---

## Pop 2

```text
res = [1,2]
```

---

## Pop 3

```text
res = [1,2,3]
```

Done.

---

# Time Complexity

```text
O(n)
```

Every node visited once.

---

# Space Complexity

```text
O(n)
```

Stack may store all nodes.

---

# Global Declaration Solution

This is the solution you gave.

---

## Code

```java
class Solution {

    List<Integer> res = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {

        if(root == null) {
            return res;
        }

        res.add(root.val);

        preorderTraversal(root.left);

        preorderTraversal(root.right);

        return res;
    }
}
```

---

# Explanation

## Global Variable

```java
List<Integer> res = new ArrayList<>();
```

This list belongs to whole class.

So every recursive call uses SAME list.

---

## Base Condition

```java
if(root == null)
```

Stop recursion.

---

## Add Root

```java
res.add(root.val);
```

---

## Traverse Left

```java
preorderTraversal(root.left);
```

---

## Traverse Right

```java
preorderTraversal(root.right);
```

---

# Why This Works

Because `res` is NOT local variable.

It is shared by all recursive calls.

---

# Drawback

Global variables are usually less preferred because:

* shared state
* not thread-safe
* repeated function calls may cause issues

---

# Time Complexity

```text
O(n)
```

---

# Space Complexity

```text
O(h)
```

Worst case:

```text
O(n)
```

Balanced tree:

```text
O(log n)
```

---

# Comparison of 3 Solutions

| Solution         | Method        | Extra DS | TC   | SC   |
| ---------------- | ------------- | -------- | ---- | ---- |
| Recursive Helper | DFS Recursion | No       | O(n) | O(h) |
| Iterative Stack  | Stack         | Yes      | O(n) | O(n) |
| Global Variable  | DFS Recursion | No       | O(h) | O(h) |

---

# Which One is Best?

## Interview Preferred

✅ Helper Method Recursive

Because:

* clean
* safe
* no global state
* better coding practice

---

## Best for Iterative Understanding

✅ Stack Solution

Shows understanding of traversal mechanics.

---

## Easy to Write Quickly

✅ Global Variable Solution

Short and simple, but less preferred in interviews.

</p>
