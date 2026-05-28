<h2><a href="https://leetcode.com/problems/number-of-islands">200. Number of Islands</a></h2><h3>Medium</h3><hr><p>Given an <code>m x n</code> 2D binary grid <code>grid</code> which represents a map of <code>&#39;1&#39;</code>s (land) and <code>&#39;0&#39;</code>s (water), return <em>the number of islands</em>.</p>

<p>An <strong>island</strong> is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> grid = [
  [&quot;1&quot;,&quot;1&quot;,&quot;1&quot;,&quot;1&quot;,&quot;0&quot;],
  [&quot;1&quot;,&quot;1&quot;,&quot;0&quot;,&quot;1&quot;,&quot;0&quot;],
  [&quot;1&quot;,&quot;1&quot;,&quot;0&quot;,&quot;0&quot;,&quot;0&quot;],
  [&quot;0&quot;,&quot;0&quot;,&quot;0&quot;,&quot;0&quot;,&quot;0&quot;]
]
<strong>Output:</strong> 1
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [
  [&quot;1&quot;,&quot;1&quot;,&quot;0&quot;,&quot;0&quot;,&quot;0&quot;],
  [&quot;1&quot;,&quot;1&quot;,&quot;0&quot;,&quot;0&quot;,&quot;0&quot;],
  [&quot;0&quot;,&quot;0&quot;,&quot;1&quot;,&quot;0&quot;,&quot;0&quot;],
  [&quot;0&quot;,&quot;0&quot;,&quot;0&quot;,&quot;1&quot;,&quot;1&quot;]
]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 300</code></li>
	<li><code>grid[i][j]</code> is <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>
<p>
	
# LeetCode 200 — Number of Islands

---

# Problem Statement

You are given a 2D grid containing:

* `'1'` → land
* `'0'` → water

Return the number of islands.

An island is formed by connected lands using:

✅ up
✅ down
✅ left
✅ right

Diagonal connection does NOT count.

---

# Important Concept

## Island does NOT mean land on all 4 sides.

Island means:

> One or more connected `'1'` cells.

Even a single `'1'` alone is an island.

---

# Example 1

```text id="u2f9v4"
0 0 0
0 1 0
0 0 0
```

Middle `1` is alone.

Still:

✅ 1 island

---

# Example 2

```text id="m8x3q7"
1 1 0
0 1 0
0 0 0
```

All connected.

So:

✅ 1 island

---

# Example 3

```text id="k4p1n6"
1 0 1
```

Not connected.

So:

✅ 2 islands

---

# Diagonal Does NOT Count

```text id="d5t8w2"
1 0
0 1
```

These are diagonal.

So:

✅ 2 islands

---

# Approach 1 — Brute Force (DFS + Separate Visited Array)

---

# Intuition

We traverse every cell.

Whenever we find an unvisited `'1'`:

* Start DFS
* Visit all connected land
* Mark visited
* Increase island count

---

# Brute Force Code

```java
class Solution {

    public void dfs(int i, int j,
                    char[][] grid,
                    boolean[][] visited) {

        // Boundary check
        if(i < 0 || j < 0 ||
           i >= grid.length ||
           j >= grid[0].length)
            return;

        // Water or already visited
        if(grid[i][j] == '0' || visited[i][j])
            return;

        // Mark visited
        visited[i][j] = true;

        // Explore 4 directions
        dfs(i - 1, j, grid, visited);
        dfs(i + 1, j, grid, visited);
        dfs(i, j - 1, grid, visited);
        dfs(i, j + 1, grid, visited);
    }

    public int numIslands(char[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited =
                new boolean[rows][cols];

        int count = 0;

        for(int i = 0; i < rows; i++) {

            for(int j = 0; j < cols; j++) {

                if(grid[i][j] == '1'
                        && !visited[i][j]) {

                    dfs(i, j, grid, visited);

                    count++;
                }
            }
        }

        return count;
    }
}
```

---

# Brute Force Dry Run

Grid:

```text id="v1z7p3"
0 0 0
0 1 1
0 0 0
```

---

## Loop reaches `(1,1)`

```java
dfs(1,1,grid,visited);
count++;
```

Count = 1

DFS visits:

```text id="e9k2r5"
(1,1)
(1,2)
```

Both become visited.

Later loop reaches `(1,2)`:

```java
visited[1][2] == true
```

So no new island counted.

---

# Important Doubt

You asked:

> When DFS goes to `(1,2)` and sees left `(1,1)` = `1`,
> will it count again?

Answer:

❌ No.

Because DFS never increases count.

Only this increases count:

```java
count++;
```

And it happens ONLY when loop finds a NEW unvisited `'1'`.

---

# Time Complexity

## TC

```text id="n6w4m8"
O(rows × cols)
```

Each cell visited once.

---

## SC

```text id="c3j9x1"
O(rows × cols)
```

Visited array + recursion stack.

---

# Approach 2 — Optimal (Modify Grid Directly)

This is your code approach.

Instead of using extra visited array:

✅ directly change visited land to `'x'`

This saves space.

---

# Optimal Intuition

When land is visited:

```java
grid[i][j] = 'x';
```

So:

* `'1'` → unvisited land
* `'x'` → visited land

---

# Optimal Code

```java
class Solution {

    public void dfs(int i, int j, char[][] grid) {

        // Boundary check
        if(i < 0 || j < 0 ||
           i >= grid.length ||
           j >= grid[0].length)
            return;

        // Water or visited
        if(grid[i][j] != '1')
            return;

        // Mark visited
        grid[i][j] = 'x';

        // Explore 4 directions
        dfs(i - 1, j, grid);
        dfs(i + 1, j, grid);
        dfs(i, j - 1, grid);
        dfs(i, j + 1, grid);
    }

    public int numIslands(char[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        int count = 0;

        for(int i = 0; i < rows; i++) {

            for(int j = 0; j < cols; j++) {

                if(grid[i][j] == '1') {

                    dfs(i, j, grid);

                    count++;
                }
            }
        }

        return count;
    }
}
```

---

# Optimal Dry Run

Initial grid:

```text id="b7f5u2"
0 0 0
0 1 1
0 0 0
```

---

## Loop reaches `(1,1)`

```java
dfs(1,1,grid);
count++;
```

Count = 1

---

# DFS Starts

Mark `(1,1)`:

```text id="p4m8r1"
0 0 0
0 x 1
0 0 0
```

---

## DFS goes right `(1,2)`

Mark:

```text id="y8n2k6"
0 0 0
0 x x
0 0 0
```

---

## From `(1,2)`

DFS checks left `(1,1)`.

But `(1,1)` is already `'x'`.

So:

```java
if(grid[i][j] != '1')
    return;
```

DFS stops.

---

# Important Point

Later loop reaches `(1,2)`:

But now:

```text id="r5v9d3"
grid[1][2] = 'x'
```

So condition fails:

```java
if(grid[i][j] == '1')
```

Thus:

❌ no new DFS
❌ no new island count

---

# Why Mark Visited?

Without marking visited:

DFS keeps revisiting same cells.

Example:

```text id="j6q1m7"
1 1
```

Left visits right.
Right visits left.
Infinite recursion happens.

So marking visited is necessary.

---

# Time Complexity

## TC

```text id="g4w8x2"
O(rows × cols)
```

Each cell visited once.

---

## SC

```text id="s9n3k5"
O(rows × cols)
```

Worst-case recursion stack.

(If entire grid is land.)

---

# Brute Force vs Optimal

| Feature             | Brute Force          | Optimal          |
| ------------------- | -------------------- | ---------------- |
| Extra visited array | Yes                  | No               |
| Modifies grid       | No                   | Yes              |
| Space               | More                 | Less             |
| Logic               | Easier to understand | Better optimized |

---

# Core Intuition

## Island count increases ONLY when:

```java
you find a NEW unvisited '1'
```

DFS simply:

✅ visits connected land
✅ marks visited

DFS does NOT count islands.

---

# Final Rule To Remember

## Connected using:

```text id="h2f7v9"
up
down
left
right
```

✅ Same island

---

## Not connected

```text id="w5m1q8"
1 0 1
```

✅ Different islands

---

# Interview Tip

Whenever you see:

* grid
* connected cells
* groups/components

Think:

✅ DFS
✅ BFS
✅ Connected Components Pattern

</p>
