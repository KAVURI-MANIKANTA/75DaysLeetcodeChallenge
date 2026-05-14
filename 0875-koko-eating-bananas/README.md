<h2><a href="https://leetcode.com/problems/koko-eating-bananas">907. Koko Eating Bananas</a></h2><h3>Medium</h3><hr><p>Koko loves to eat bananas. There are <code>n</code> piles of bananas, the <code>i<sup>th</sup></code> pile has <code>piles[i]</code> bananas. The guards have gone and will come back in <code>h</code> hours.</p>

<p>Koko can decide her bananas-per-hour eating speed of <code>k</code>. Each hour, she chooses some pile of bananas and eats <code>k</code> bananas from that pile. If the pile has less than <code>k</code> bananas, she eats all of them instead and will not eat any more bananas during this hour.</p>

<p>Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.</p>

<p>Return <em>the minimum integer</em> <code>k</code> <em>such that she can eat all the bananas within</em> <code>h</code> <em>hours</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> piles = [3,6,7,11], h = 8
<strong>Output:</strong> 4
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> piles = [30,11,23,4,20], h = 5
<strong>Output:</strong> 30
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> piles = [30,11,23,4,20], h = 6
<strong>Output:</strong> 23
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= piles.length &lt;= 10<sup>4</sup></code></li>
	<li><code>piles.length &lt;= h &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= piles[i] &lt;= 10<sup>9</sup></code></li>
</ul>
<p>
	
# LeetCode 875 — Koko Eating Bananas

LeetCode 875: Koko Eating Bananas

## Problem Understanding

Koko has some banana piles.

* `piles[i]` = bananas in ith pile
* She can eat at speed `k` bananas/hour
* In 1 hour:

  * If pile has less than `k`, she finishes it
  * She cannot eat from multiple piles in same hour
* We must find **minimum k** so that all bananas are eaten within `h` hours.

---

# Example

```text
piles = [3,6,7,11]
h = 8
```

If `k = 4`

| Pile | Hours Needed |
| ---- | ------------ |
| 3    | 1            |
| 6    | 2            |
| 7    | 2            |
| 11   | 3            |

Total = 8 hours

Answer = 4

---

# Brute Force Approach

## Idea

Try every eating speed from `1` to `max(piles)`.

For each speed:

* Calculate total hours needed
* If hours <= h

  * return that speed

---

# Brute Force Code (Java)

```java
class Solution {

    public int minEatingSpeed(int[] piles, int h) {

        int max = 0;

        // Find maximum pile
        for (int pile : piles) {
            max = Math.max(max, pile);
        }

        // Try every speed from 1 to max
        for (int k = 1; k <= max; k++) {

            int totalHours = 0;

            // Calculate hours needed
            for (int pile : piles) {

                // ceil(pile / k)
                totalHours += (pile + k - 1) / k;
            }

            // If possible within h hours
            if (totalHours <= h) {
                return k;
            }
        }

        return max;
    }
}
```

---

# Brute Force Explanation

## Step 1: Find maximum pile

```java
for (int pile : piles) {
    max = Math.max(max, pile);
}
```

Why?

Maximum possible speed needed is largest pile.

Example:

```text
[3,6,7,11]
```

Max speed = 11

---

## Step 2: Try every speed

```java
for (int k = 1; k <= max; k++)
```

Check:

* speed 1
* speed 2
* speed 3
* ...

---

## Step 3: Calculate hours

```java
totalHours += (pile + k - 1) / k;
```

This is ceiling division.

Example:

```text
pile = 7
k = 3

7/3 = 2.33
Need 3 hours
```

Formula:

```text
(7 + 3 - 1)/3
= 9/3
= 3
```

---

## Step 4: Return answer

```java
if (totalHours <= h)
    return k;
```

First valid speed = minimum speed.

---

# Brute Force Complexity

## Time Complexity

```text
O(maxPile * n)
```

Where:

* `maxPile` = largest pile
* `n` = number of piles

Very slow for large values.

---

## Space Complexity

```text
O(1)
```

---

# Optimal Approach — Binary Search

## Important Observation

If speed works:

```text
k = 6 works
```

Then:

```text
7,8,9... also work
```

This forms a monotonic pattern:

```text
Speed:
1 2 3 4 5 6 7 8

Work?
N N N N N Y Y Y
```

So we can use Binary Search.

---

# Binary Search Range

```text
low = 1
high = max(piles)
```

---

# Optimal Code (Java)

```java
class Solution {

    public int minEatingSpeed(int[] piles, int h) {

        int low = 1;
        int high = 0;

        // Find maximum pile
        for (int pile : piles) {
            high = Math.max(high, pile);
        }

        while (low <= high) {

            int mid = low + (high - low) / 2;

            int totalHours = calculateHours(piles, mid);

            // Speed works
            if (totalHours <= h) {
                high = mid - 1;
            }
            // Speed too slow
            else {
                low = mid + 1;
            }
        }

        return low;
    }

    private int calculateHours(int[] piles, int speed) {

        int hours = 0;

        for (int pile : piles) {

            hours += (pile + speed - 1) / speed;
        }

        return hours;
    }
}
```

---

# Optimal Solution Explanation

---

# Step 1: Search Space

```java
low = 1;
high = maxPile;
```

Example:

```text
piles = [3,6,7,11]

low = 1
high = 11
```

---

# Step 2: Find Mid

```java
mid = low + (high - low)/2;
```

Suppose:

```text
mid = 6
```

Check if speed 6 works.

---

# Step 3: Calculate Hours

```java
hours += (pile + speed - 1) / speed;
```

Example:

```text
speed = 6

3 -> 1 hour
6 -> 1 hour
7 -> 2 hours
11 -> 2 hours

Total = 6
```

---

# Step 4: If speed works

```java
if(totalHours <= h)
    high = mid - 1;
```

Why move left?

Because we need **minimum** valid speed.

---

# Step 5: If speed too slow

```java
low = mid + 1;
```

Need larger speed.

---

# Dry Run

```text
piles = [3,6,7,11]
h = 8
```

---

## Iteration 1

```text
low = 1
high = 11

mid = 6
```

Hours = 6

```text
6 <= 8
```

Valid

Move left:

```text
high = 5
```

---

## Iteration 2

```text
low = 1
high = 5

mid = 3
```

Hours:

```text
1 + 2 + 3 + 4 = 10
```

Too slow

```text
low = 4
```

---

## Iteration 3

```text
low = 4
high = 5

mid = 4
```

Hours:

```text
1 + 2 + 2 + 3 = 8
```

Valid

```text
high = 3
```

Loop stops.

Answer:

```text
low = 4
```

---

# Why Return low?

At the end:

```text
low = first valid answer
```

Binary search guarantees this.

---

# Optimal Complexity

## Time Complexity

```text
O(n * log(maxPile))
```

Where:

* `n` = number of piles
* `maxPile` = largest pile

Much faster than brute force.

---

## Space Complexity

```text
O(1)
```

---

# Key Interview Points

## Why Binary Search?

Because answer space is monotonic:

```text
Small speed -> invalid
Large speed -> valid
```

---

## Ceiling Formula

```java
(a + b - 1) / b
```

Used for:

```text
ceil(a/b)
```

---

# Pattern Recognition

This problem belongs to:

## Binary Search on Answer

Other similar problems:

* Capacity To Ship Packages
* Aggressive Cows
* Allocate Books
* Split Array Largest Sum

---

# Final Comparison

| Approach      | Time              | Space |
| ------------- | ----------------- | ----- |
| Brute Force   | O(maxPile * n)    | O(1)  |
| Binary Search | O(n log(maxPile)) | O(1)  |

</p>
