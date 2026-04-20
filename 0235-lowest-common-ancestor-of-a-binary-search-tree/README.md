<h2><a href="https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree">235. Lowest Common Ancestor of a Binary Search Tree</a></h2><h3>Medium</h3><hr><p>Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.</p>

<p>According to the <a href="https://en.wikipedia.org/wiki/Lowest_common_ancestor" target="_blank">definition of LCA on Wikipedia</a>: &ldquo;The lowest common ancestor is defined between two nodes <code>p</code> and <code>q</code> as the lowest node in <code>T</code> that has both <code>p</code> and <code>q</code> as descendants (where we allow <strong>a node to be a descendant of itself</strong>).&rdquo;</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2018/12/14/binarysearchtree_improved.png" style="width: 200px; height: 190px;" />
<pre>
<strong>Input:</strong> root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
<strong>Output:</strong> 6
<strong>Explanation:</strong> The LCA of nodes 2 and 8 is 6.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2018/12/14/binarysearchtree_improved.png" style="width: 200px; height: 190px;" />
<pre>
<strong>Input:</strong> root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
<strong>Output:</strong> 2
<strong>Explanation:</strong> The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> root = [2,1], p = 2, q = 1
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[2, 10<sup>5</sup>]</code>.</li>
	<li><code>-10<sup>9</sup> &lt;= Node.val &lt;= 10<sup>9</sup></code></li>
	<li>All <code>Node.val</code> are <strong>unique</strong>.</li>
	<li><code>p != q</code></li>
	<li><code>p</code> and <code>q</code> will exist in the BST.</li>
</ul>
<p>
---




# 🌳 PROBLEM CORE (VERY IMPORTANT)

We want:

> **The lowest node where p and q meet**

Think:

* Paths from root → p
* Paths from root → q
  👉 The **last common point** = LCA


# 🔴 1. BRUTE FORCE (Full Tree Search)
```
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        if(root == null || root == p || root == q){
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null){
            return root;
        }

        return (left != null) ? left : right;
    }
}
```
## 🧠 Real Intuition (not surface level)

This approach is doing:

> “From every node, check: do I contain p and q in my subtree?”


## 🔥 What recursion actually returns

This is the key you were missing:

👉 Each recursive call returns:

* `null` → nothing found
* `p` or `q` → found one node
* `LCA` → already found answer


## 🔍 Deep Dry Run

Tree:

```text
        6
       / \
      2   8
     / \
    0   4
```

Find LCA of `0` and `4`


### Step-by-step recursion

#### Call at node 6
left = LCA(2)
right = LCA(8)

#### Call at node 2
left = LCA(0)
right = LCA(4)

#### Call at node 0
root == p → return 0


#### Call at node 4
root == q → return 4


#### Back to node 2
Now:
left = 0
right = 4


👉 BOTH NON-NULL

💥 This means:

> “I am the first node where p and q meet”

👉 return 2



#### Back to node 6


left = 2
right = null


👉 Only one side → pass it up

👉 return 2



## 🧠 Why this works (important insight)

* The recursion is doing a **post-order traversal**
* It finds p and q **bottom-up**
* The **first node where both sides return something = LCA**



## ⚠️ Important Observation

This works for:

* Binary Tree
* BST
* Any structure

BUT:
👉 It wastes time searching entire tree

Good — this is exactly the right way to understand it deeply 👍
We’ll go **condition by condition**, with **separate examples** for each.
``````
# 🧠 First remember the 4 conditions

At every node:

| left     | right    | meaning                        | return                 |
| -------- | -------- | ------------------------------ | ---------------------- |
| not null | not null | p & q found in different sides | **current node (LCA)** |
| not null | null     | both in left OR found one      | **left**               |
| null     | not null | both in right OR found one     | **right**              |
| null     | null     | nothing found                  | **null**               |

---

# 🔴 CASE 1: left ≠ null AND right ≠ null

👉 **This is where LCA is found**

---

## Example

```text
        3
       / \
      5   1
     / \
    6   2
```

Find LCA of:
👉 `p = 6`, `q = 2`

---

### Flow

At node `5`:

* left → returns 6
* right → returns 2

```text
left = 6
right = 2
```

🔥 Both NOT null → **return 5**

---

✅ Final Answer = **5**

---

# 🟡 CASE 2: left ≠ null AND right = null

👉 Means: “I found something only in left”

---

## Example

```text
        3
       / \
      5   1
     /
    6
```

Find LCA of:
👉 `p = 6`, `q = 5`

---

### Flow

At node `5`:

* It matches `q` → returns 5

At node `3`:

```text
left = 5
right = null
```

👉 Only left has value → return left

---

✅ Final Answer = **5**

---

# 🟢 CASE 3: left = null AND right ≠ null

👉 Same logic, but on right side

---

## Example

```text
        3
       / \
      5   1
           \
            8
```

Find LCA of:
👉 `p = 1`, `q = 8`

---

### Flow

At node `1`:

* right → returns 8

At node `3`:

```text
left = null
right = 1
```

👉 Only right has value → return right

---

✅ Final Answer = **1**

---

# ⚫ CASE 4: left = null AND right = null

👉 Means: “Nothing found here”

---

## Example

```text
        3
       / \
      5   1
```

Find LCA of:
👉 `p = 10`, `q = 20` (not in tree)

---

At every node:

```text
left = null
right = null
```

👉 return null

---

✅ Final Answer = **null**

---

# 🔥 BONUS IMPORTANT CASE (VERY COMMON)

## When node itself is p or q

```java
if(root == null || root == p || root == q){
    return root;
}
```

---

## Example

```text
        3
       / \
      5   1
```

Find LCA of:
👉 `p = 5`, `q = 1`

---

At node `5`:
→ return 5

At node `1`:
→ return 1

At node `3`:

```text
left = 5
right = 1
```

🔥 Both sides → return 3

---

✅ Final Answer = **3**

---

# 🧩 Final Memory Trick

👉 Think like this:

* “Did left find something?”
* “Did right find something?”

| Situation      | Answer      |
| -------------- | ----------- |
| Both found     | I am LCA    |
| Only one found | Pass it up  |
| None found     | return null |
``````
# 🟢 2. OPTIMAL (BST – Iterative)

Now we become **smart instead of brute**
```
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while(root != null){
            if(p.val < root.val && q.val < root.val){
                root = root.left;
            }
            else if(p.val > root.val && q.val > root.val){
                root = root.right;
            }
            else{
                return root;
            }
        }
        return null;
    }
}
```


## 🧠 Core Insight

In BST:

```text
left < root < right
```

👉 So we don’t need to search everywhere



## 🔥 Key Observation

If:

```text
p = 2, q = 4, root = 6
```

Both are **smaller than 6**

👉 So:

> “They must be in LEFT subtree”

💡 No need to check right side at all



## 🔍 Deep Dry Run

Same tree:

```text
        6
       / \
      2   8
     / \
    0   4
```

Find LCA of `0` and `4`



### Step 1: root = 6

```text
p=0, q=4 → both < 6
```

👉 Move LEFT



### Step 2: root = 2

```text
p=0 < 2
q=4 > 2
```

👉 They split here

💥 This is LCA



## 🧠 Why split means LCA?

Because:

* One node is in left subtree
* One node is in right subtree

👉 So this is the **first meeting point**

## ⚠️ Key Difference from Brute Force

| Brute Force                       | Optimal                       |
| --------------------------------- | ----------------------------- |
| searches BOTH sides               | chooses ONE direction         |
| finds answer after full traversal | finds answer while going down |



# 🔵 3. YOUR CODE (Recursive BST)

This is same as optimal, but recursive.
```
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return root;

        if(p.val < root.val && q.val < root.val){
            return lowestCommonAncestor(root.left, p, q);
        }
        else if(p.val > root.val && q.val > root.val){
            return lowestCommonAncestor(root.right, p, q);
        }

        return root;
    }
}
```
``

## 🧠 What your recursion is doing

It is NOT exploring both sides.

It is doing:

```text
"Only go where both nodes exist"
```



## 🔍 Deep Dry Run

Tree:

```text
        6
       / \
      2   8
     / \
    0   4
```



### Call 1: root = 6

```text
p=0, q=4 → both < 6
```

👉 Call:

```java
return LCA(root.left)
```


### Call 2: root = 2

```text
p=0 < 2
q=4 > 2
```

👉 Split happens

👉 return root (2)



### Back to call 1

👉 directly return 2



## 🧠 Important Insight

Unlike brute force:

* We NEVER go to unnecessary nodes
* We don’t explore both sides

---

# ⚡ Deep Comparison (Real Understanding)

| Feature        | Brute Force     | Optimal (Iterative) | Your Code        |
| -------------- | --------------- | ------------------- | ---------------- |
| Tree Type      | Any             | BST only            | BST only         |
| Search         | Both sides      | One direction       | One direction    |
| Traversal      | Bottom-up       | Top-down            | Top-down         |
| Decision point | After recursion | During traversal    | During recursion |
| Efficiency     | Slow            | Fast                | Fast             |

---

# 🧩 FINAL MENTAL MODEL

### 🔴 Brute Force

> “Let me search EVERYTHING, then decide”


### 🟢 Optimal (BST)

> “I already know where both nodes are → go there directly”


### 🔵 Your Code

> “Same as optimal, but using recursion instead of loop”


# 🔥 Most Important Line to Remember

### For Brute Force:

```text
First node where left and right both return something = LCA
```

### For BST:

```text
First node where p and q split = LCA
```
``
</p>
