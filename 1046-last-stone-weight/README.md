<h2><a href="https://leetcode.com/problems/last-stone-weight">1046. Last Stone Weight</a></h2><h3>Easy</h3><hr><p>You are given an array of integers <code>stones</code> where <code>stones[i]</code> is the weight of the <code>i<sup>th</sup></code> stone.</p>

<p>We are playing a game with the stones. On each turn, we choose the <strong>heaviest two stones</strong> and smash them together. Suppose the heaviest two stones have weights <code>x</code> and <code>y</code> with <code>x &lt;= y</code>. The result of this smash is:</p>

<ul>
	<li>If <code>x == y</code>, both stones are destroyed, and</li>
	<li>If <code>x != y</code>, the stone of weight <code>x</code> is destroyed, and the stone of weight <code>y</code> has new weight <code>y - x</code>.</li>
</ul>

<p>At the end of the game, there is <strong>at most one</strong> stone left.</p>

<p>Return <em>the weight of the last remaining stone</em>. If there are no stones left, return <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> stones = [2,7,4,1,8,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong> 
We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we combine 1 and 1 to get 0 so the array converts to [1] then that&#39;s the value of the last stone.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> stones = [1]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= stones.length &lt;= 30</code></li>
	<li><code>1 &lt;= stones[i] &lt;= 1000</code></li>
</ul>
<p>
	
# LeetCode 1046 — Last Stone Weight

Problem:
You are given an array `stones[]` where each value represents a stone weight.

Rules:

* Pick the **2 heaviest stones**
* If both are equal → both destroyed
* Else → smaller destroyed and bigger becomes `(bigger - smaller)`
* Repeat until 0 or 1 stone remains.

Return the final stone weight.

---

# 1. Brute Force Solution

## Idea

Every time:

1. Sort the array
2. Take the largest 2 stones
3. Smash them
4. Put remaining weight back
5. Repeat

---

# Brute Force Code (Java)

```java
import java.util.*;

class Solution {
    public int lastStoneWeight(int[] stones) {

        List<Integer> list = new ArrayList<>();

        for (int stone : stones) {
            list.add(stone);
        }

        while (list.size() > 1) {

            Collections.sort(list);

            int y = list.remove(list.size() - 1); // largest
            int x = list.remove(list.size() - 1); // second largest

            if (y != x) {
                list.add(y - x);
            }
        }

        return list.isEmpty() ? 0 : list.get(0);
    }
}
```

---

# Step-by-Step Example

Input:

```text
[2,7,4,1,8,1]
```

### Iteration 1

Sorted:

```text
[1,1,2,4,7,8]
```

Take:

* 8
* 7

Smash:

```text
8 - 7 = 1
```

New list:

```text
[1,1,2,4,1]
```

---

### Iteration 2

Sorted:

```text
[1,1,1,2,4]
```

Take:

* 4
* 2

Smash:

```text
2
```

New list:

```text
[1,1,1,2]
```

---

Continue until:

```text
1
```

Answer = `1`

---

# Brute Force Explanation

## Convert array → ArrayList

```java
List<Integer> list = new ArrayList<>();
```

Because:

* easy to remove
* easy to add new stone

---

## Sort every iteration

```java
Collections.sort(list);
```

Largest stones come at end.

---

## Remove largest 2 stones

```java
int y = list.remove(list.size() - 1);
int x = list.remove(list.size() - 1);
```

---

## If unequal, add difference

```java
if (y != x) {
    list.add(y - x);
}
```

---

# Time Complexity

## Sorting every iteration

If `n` stones:

* sorting = `O(n log n)`
* repeated about `n` times

### Total

```text
O(n² log n)
```

---

# Space Complexity

```text
O(n)
```

For ArrayList.

---

# 2. Optimal Solution (Max Heap / Priority Queue)

## Main Idea

We always need:

> largest stone
> second largest stone

Max Heap gives largest element quickly.

---

# Why Max Heap?

Normal PriorityQueue in Java is:

```java
Min Heap
```

We need:

```java
Max Heap
```

So use:

```java
PriorityQueue<Integer> pq =
    new PriorityQueue<>(Collections.reverseOrder());
```

---

# Optimal Code (Java)

```java
import java.util.*;

class Solution {
    public int lastStoneWeight(int[] stones) {

        PriorityQueue<Integer> pq =
                new PriorityQueue<>(Collections.reverseOrder());

        for (int stone : stones) {
            pq.add(stone);
        }

        while (pq.size() > 1) {

            int y = pq.poll(); // largest
            int x = pq.poll(); // second largest

            if (y != x) {
                pq.add(y - x);
            }
        }

        return pq.isEmpty() ? 0 : pq.poll();
    }
}
```

---

# Optimal Solution Dry Run

Input:

```text
[2,7,4,1,8,1]
```

Heap:

```text
8 7 4 2 1 1
```

---

Take:

* 8
* 7

Difference:

```text
1
```

Add back:

```text
4 2 1 1 1
```

---

Take:

* 4
* 2

Difference:

```text
2
```

Add back:

```text
2 1 1 1
```

---

Continue…

Final:

```text
1
```

---

# Optimal Code Explanation

## Create Max Heap

```java
PriorityQueue<Integer> pq =
        new PriorityQueue<>(Collections.reverseOrder());
```

Largest value stays at top.

---

## Insert all stones

```java
for (int stone : stones) {
    pq.add(stone);
}
```

---

## Remove top 2 largest stones

```java
int y = pq.poll();
int x = pq.poll();
```

`poll()` removes highest priority element.

---

## Add difference

```java
pq.add(y - x);
```

Only if unequal.

---

# Time Complexity

## Heap Operations

Insertion:

```text
O(log n)
```

Polling:

```text
O(log n)
```

We do this for all stones.

### Total

```text
O(n log n)
```

---

# Space Complexity

```text
O(n)
```

For heap.

---

# Comparison

| Approach                         | Time        | Space |
| -------------------------------- | ----------- | ----- |
| Brute Force (sorting repeatedly) | O(n² log n) | O(n)  |
| Max Heap (Optimal)               | O(n log n)  | O(n)  |

---

# Key Interview Point

Whenever question asks repeatedly for:

* largest
* smallest
* top K

Think about:

* Heap / PriorityQueue

For this problem:

* Need largest repeatedly
* So use **Max Heap**.

</p>
