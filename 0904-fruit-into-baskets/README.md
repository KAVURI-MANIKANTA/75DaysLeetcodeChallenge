<h2><a href="https://leetcode.com/problems/fruit-into-baskets">940. Fruit Into Baskets</a></h2><h3>Medium</h3><hr><p>You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array <code>fruits</code> where <code>fruits[i]</code> is the <strong>type</strong> of fruit the <code>i<sup>th</sup></code> tree produces.</p>

<p>You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:</p>

<ul>
	<li>You only have <strong>two</strong> baskets, and each basket can only hold a <strong>single type</strong> of fruit. There is no limit on the amount of fruit each basket can hold.</li>
	<li>Starting from any tree of your choice, you must pick <strong>exactly one fruit</strong> from <strong>every</strong> tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.</li>
	<li>Once you reach a tree with fruit that cannot fit in your baskets, you must stop.</li>
</ul>

<p>Given the integer array <code>fruits</code>, return <em>the <strong>maximum</strong> number of fruits you can pick</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> fruits = [<u>1,2,1</u>]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can pick from all 3 trees.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> fruits = [0,<u>1,2,2</u>]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can pick from trees [1,2,2].
If we had started at the first tree, we would only pick from trees [0,1].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> fruits = [1,<u>2,3,2,2</u>]
<strong>Output:</strong> 4
<strong>Explanation:</strong> We can pick from trees [2,3,2,2].
If we had started at the first tree, we would only pick from trees [1,2].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= fruits.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= fruits[i] &lt; fruits.length</code></li>
</ul>
<p>
	
# LeetCode 904 — Fruit Into Baskets

You are given an array `fruits[]`.

* Each number represents a fruit type.
* You have **2 baskets**.
* Each basket can store only **one type** of fruit.
* You must pick fruits continuously from trees.
* Find the **maximum number of fruits** you can collect.

---

# Example

```text
Input: [1,2,1]
Output: 3
```

Because we can collect all fruits using 2 baskets:

* Basket1 → type 1
* Basket2 → type 2

---

# 1) Brute Force Solution

## Idea

Check every possible subarray.

For each subarray:

* Count distinct fruit types.
* If distinct types ≤ 2 → valid.
* Update maximum length.

---

# Brute Force Code (Java)

```java
class Solution {
    public int totalFruit(int[] fruits) {

        int n = fruits.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {

            HashMap<Integer, Integer> map = new HashMap<>();

            for (int j = i; j < n; j++) {

                map.put(fruits[j], map.getOrDefault(fruits[j], 0) + 1);

                // More than 2 fruit types
                if (map.size() > 2) {
                    break;
                }

                maxLen = Math.max(maxLen, j - i + 1);
            }
        }

        return maxLen;
    }
}
```

---

# Brute Force Explanation

Suppose:

```text
fruits = [1,2,3,2,2]
```

We try all subarrays.

---

## i = 0

### j = 0

Subarray:

```text
[1]
```

Map:

```text
{1=1}
```

Valid → size = 1

maxLen = 1

---

### j = 1

Subarray:

```text
[1,2]
```

Map:

```text
{1=1, 2=1}
```

Valid → size = 2

maxLen = 2

---

### j = 2

Subarray:

```text
[1,2,3]
```

Map:

```text
{1=1, 2=1, 3=1}
```

Now map size > 2

Invalid → break

---

Continue similarly for all `i`.

Final answer:

```text
4
```

Subarray:

```text
[2,3,2,2]
```

---

# Time Complexity (Brute Force)

## TC

```text
O(N²)
```

Because:

* Outer loop → `N`
* Inner loop → `N`

---

## SC

```text
O(1)
```

(At most 3 types stored in map.)

---

# 2) Optimal Solution — Sliding Window

## Main Idea

We need:

* Longest subarray
* With at most 2 distinct numbers

This is a classic **Sliding Window** problem.

---

# Sliding Window Concept

We maintain:

* `left`
* `right`

Window:

```text
[left ... right]
```

Keep expanding `right`.

If fruit types become more than 2:

* Shrink from left.

---

# Optimal Code (Java)

```java
class Solution {
    public int totalFruit(int[] fruits) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < fruits.length; right++) {

            // Add current fruit
            map.put(fruits[right],
                    map.getOrDefault(fruits[right], 0) + 1);

            // If more than 2 fruit types
            while (map.size() > 2) {

                map.put(fruits[left], map.get(fruits[left]) - 1);

                // Remove if count becomes 0
                if (map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }

                left++;
            }

            // Update maximum length
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
```

---

# Optimal Solution Dry Run

Input:

```text
[1,2,3,2,2]
```

---

## right = 0

Fruit = 1

Map:

```text
{1=1}
```

Window:

```text
[1]
```

maxLen = 1

---

## right = 1

Fruit = 2

Map:

```text
{1=1, 2=1}
```

Window:

```text
[1,2]
```

maxLen = 2

---

## right = 2

Fruit = 3

Map:

```text
{1=1, 2=1, 3=1}
```

Now size > 2

Shrink window.

Remove fruits[left]:

left = 0 → fruit = 1

Map becomes:

```text
{2=1, 3=1}
```

left = 1

Now valid again.

Window:

```text
[2,3]
```

Length = 2

---

## right = 3

Fruit = 2

Map:

```text
{2=2, 3=1}
```

Window:

```text
[2,3,2]
```

maxLen = 3

---

## right = 4

Fruit = 2

Map:

```text
{2=3, 3=1}
```

Window:

```text
[2,3,2,2]
```

maxLen = 4

---

Final Answer:

```text
4
```

---

# Why Sliding Window Works

Because:

* We only need continuous subarrays.
* If window becomes invalid (>2 types),
  moving left is the only way to fix it.
* Every element is added and removed once.

So overall linear time.

---

# Time Complexity (Optimal)

## TC

```text
O(N)
```

Each element:

* added once
* removed once

---

## SC

```text
O(1)
```

Map stores at most 3 fruit types.

---

# Difference Between Brute Force and Optimal

| Feature                    | Brute Force         | Optimal               |
| -------------------------- | ------------------- | --------------------- |
| Approach                   | Check all subarrays | Sliding Window        |
| Time Complexity            | O(N²)               | O(N)                  |
| Space Complexity           | O(1)                | O(1)                  |
| Efficient for large input? | No                  | Yes                   |
| Main Idea                  | Try everything      | Maintain valid window |

</p>
