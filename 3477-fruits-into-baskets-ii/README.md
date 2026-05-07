<h2><a href="https://leetcode.com/problems/fruits-into-baskets-ii">3790. Fruits Into Baskets II</a></h2><h3>Easy</h3><hr><p>You are given two arrays of integers, <code>fruits</code> and <code>baskets</code>, each of length <code>n</code>, where <code>fruits[i]</code> represents the <strong>quantity</strong> of the <code>i<sup>th</sup></code> type of fruit, and <code>baskets[j]</code> represents the <strong>capacity</strong> of the <code>j<sup>th</sup></code> basket.</p>

<p>From left to right, place the fruits according to these rules:</p>

<ul>
	<li>Each fruit type must be placed in the <strong>leftmost available basket</strong> with a capacity <strong>greater than or equal</strong> to the quantity of that fruit type.</li>
	<li>Each basket can hold <b>only one</b> type of fruit.</li>
	<li>If a fruit type <b>cannot be placed</b> in any basket, it remains <b>unplaced</b>.</li>
</ul>

<p>Return the number of fruit types that remain unplaced after all possible allocations are made.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">fruits = [4,2,5], baskets = [3,5,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>fruits[0] = 4</code> is placed in <code>baskets[1] = 5</code>.</li>
	<li><code>fruits[1] = 2</code> is placed in <code>baskets[0] = 3</code>.</li>
	<li><code>fruits[2] = 5</code> cannot be placed in <code>baskets[2] = 4</code>.</li>
</ul>

<p>Since one fruit type remains unplaced, we return 1.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">fruits = [3,6,1], baskets = [6,4,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>fruits[0] = 3</code> is placed in <code>baskets[0] = 6</code>.</li>
	<li><code>fruits[1] = 6</code> cannot be placed in <code>baskets[1] = 4</code> (insufficient capacity) but can be placed in the next available basket, <code>baskets[2] = 7</code>.</li>
	<li><code>fruits[2] = 1</code> is placed in <code>baskets[1] = 4</code>.</li>
</ul>

<p>Since all fruits are successfully placed, we return 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == fruits.length == baskets.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= fruits[i], baskets[i] &lt;= 1000</code></li>
</ul>
<p>
	
# Fruits Into Baskets II

This is a continuation type of problem from 904, but easier.

---

# Problem Understanding

You are given:

* `fruits[i]` → number of fruits
* `baskets[i]` → basket capacity

For every fruit:

* place it into the **leftmost available basket**
* basket capacity must be `>= fruit`
* one basket can store only one fruit

Return:

* number of fruits that cannot be placed.

---

# Example

```text id="vk94b7"
fruits  = [4,2,5]
baskets = [3,5,4]
```

---

## Fruit = 4

Check baskets from left:

```text id="m8z0mk"
3 ❌
5 ✅
```

Place fruit in basket 5.

Used baskets:

```text id="g6e3hm"
[3,X,4]
```

---

## Fruit = 2

Check from left:

```text id="1mjlwm"
3 ✅
```

Place there.

Used:

```text id="2cvjlwm"
[X,X,4]
```

---

## Fruit = 5

Only basket left:

```text id="sod9zj"
4 ❌
```

Cannot place.

Answer:

```text id="7m9l7w"
1
```

---

# Brute Force Idea

For each fruit:

* search all baskets from left
* find first valid unused basket
* mark basket used

---

# Java Code (Brute Force)

```java id="pjlwm2"
class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {

        int n = fruits.length;

        boolean[] used = new boolean[n];

        int unplaced = 0;

        for (int i = 0; i < n; i++) {

            boolean placed = false;

            for (int j = 0; j < n; j++) {

                if (!used[j] && baskets[j] >= fruits[i]) {

                    used[j] = true;
                    placed = true;
                    break;
                }
            }

            if (!placed) {
                unplaced++;
            }
        }

        return unplaced;
    }
}
```

---

# Dry Run

Input:

```text id="6bkjlz"
fruits  = [4,2,5]
baskets = [3,5,4]
```

---

## i = 0

Fruit = 4

Check baskets:

```text id="jlwm4p"
3 ❌
5 ✅
```

Use basket index 1.

---

## i = 1

Fruit = 2

Check:

```text id="jlwm5p"
3 ✅
```

Use basket index 0.

---

## i = 2

Fruit = 5

Only basket left = 4

```text id="jlwm6p"
4 ❌
```

unplaced = 1

---

# Time Complexity

## TC

```text id="jlwm7p"
O(N²)
```

Nested loops.

---

# Space Complexity

## SC

```text id="jlwm8p"
O(N)
```

Used array.

---

# Why This Works

Because the problem specifically says:

```text id="jlwm9p"
leftmost available basket
```

So greedy left-to-right checking is correct.

---

# Important Observation

Unlike LeetCode 904:

* no sliding window
* no hashmap
* just simulation/greedy

This is mainly an implementation problem.




# Optimal Solution — Fruits Into Baskets II

The brute force solution is:

```text id="84j1mg"
O(N²)
```

because for every fruit we scan all baskets.

We can optimize using a data structure.

---

# Main Idea

We need:

```text id="7u6x3h"
leftmost basket with capacity >= fruit
```

and after using it:

* basket becomes unavailable.

This is a classic:

* range maximum query
* first valid index query

We can solve using a **Segment Tree**.

---

# Why Segment Tree?

Each node stores:

```text id="j19d8f"
maximum basket capacity in that range
```

Then for every fruit:

* find leftmost basket whose value ≥ fruit
* mark it used by setting value = -1

---

# Optimal Complexity

## TC

```text id="v4m2e7"
O(N log N)
```

Because:

* each query = `log N`
* each update = `log N`

---

# Java Optimal Code (Segment Tree)

```java id="m2s8qp"
class Solution {

    int[] tree;
    int n;

    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {

        n = baskets.length;

        tree = new int[4 * n];

        build(0, 0, n - 1, baskets);

        int unplaced = 0;

        for (int fruit : fruits) {

            int idx = query(0, 0, n - 1, fruit);

            if (idx == -1) {
                unplaced++;
            } else {
                update(0, 0, n - 1, idx, -1);
            }
        }

        return unplaced;
    }

    // Build segment tree
    private void build(int node, int start, int end, int[] baskets) {

        if (start == end) {
            tree[node] = baskets[start];
            return;
        }

        int mid = (start + end) / 2;

        build(2 * node + 1, start, mid, baskets);
        build(2 * node + 2, mid + 1, end, baskets);

        tree[node] = Math.max(tree[2 * node + 1],
                              tree[2 * node + 2]);
    }

    // Find leftmost basket >= fruit
    private int query(int node, int start, int end, int fruit) {

        // No basket possible
        if (tree[node] < fruit) {
            return -1;
        }

        // Found answer
        if (start == end) {
            return start;
        }

        int mid = (start + end) / 2;

        // Prefer left side
        if (tree[2 * node + 1] >= fruit) {
            return query(2 * node + 1, start, mid, fruit);
        }

        return query(2 * node + 2, mid + 1, end, fruit);
    }

    // Mark basket used
    private void update(int node, int start, int end,
                        int idx, int val) {

        if (start == end) {
            tree[node] = val;
            return;
        }

        int mid = (start + end) / 2;

        if (idx <= mid) {
            update(2 * node + 1, start, mid, idx, val);
        } else {
            update(2 * node + 2, mid + 1, end, idx, val);
        }

        tree[node] = Math.max(tree[2 * node + 1],
                              tree[2 * node + 2]);
    }
}
```

---

# Simple Understanding

Suppose:

```text id="m5k7hq"
baskets = [3,5,4]
```

Segment tree stores max values.

For fruit = 4:

* root says max = 5 → possible
* go left first
* 3 not enough
* go right
* 5 works

Pick index 1.

Then update:

```text id="9o1xka"
[3,-1,4]
```

---

# Difference Between Brute Force and Optimal

| Feature              | Brute Force | Optimal      |
| -------------------- | ----------- | ------------ |
| Search basket        | Linear scan | Segment Tree |
| TC                   | O(N²)       | O(N log N)   |
| SC                   | O(N)        | O(N)         |
| Suitable for large N | No          | Yes          |



</p>
