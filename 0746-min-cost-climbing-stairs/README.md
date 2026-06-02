<h2><a href="https://leetcode.com/problems/min-cost-climbing-stairs">746. Min Cost Climbing Stairs</a></h2><h3>Easy</h3><hr><p>You are given an integer array <code>cost</code> where <code>cost[i]</code> is the cost of <code>i<sup>th</sup></code> step on a staircase. Once you pay the cost, you can either climb one or two steps.</p>

<p>You can either start from the step with index <code>0</code>, or the step with index <code>1</code>.</p>

<p>Return <em>the minimum cost to reach the top of the floor</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> cost = [10,<u>15</u>,20]
<strong>Output:</strong> 15
<strong>Explanation:</strong> You will start at index 1.
- Pay 15 and climb two steps to reach the top.
The total cost is 15.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> cost = [<u>1</u>,100,<u>1</u>,1,<u>1</u>,100,<u>1</u>,<u>1</u>,100,<u>1</u>]
<strong>Output:</strong> 6
<strong>Explanation:</strong> You will start at index 0.
- Pay 1 and climb two steps to reach index 2.
- Pay 1 and climb two steps to reach index 4.
- Pay 1 and climb two steps to reach index 6.
- Pay 1 and climb one step to reach index 7.
- Pay 1 and climb two steps to reach index 9.
- Pay 1 and climb one step to reach the top.
The total cost is 6.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= cost.length &lt;= 1000</code></li>
	<li><code>0 &lt;= cost[i] &lt;= 999</code></li>
</ul>
<p>
	
# LeetCode 746 — Min Cost Climbing Stairs

## Problem Statement

You are given an array `cost`.

`cost[i]` = cost of stepping on stair `i`.

You can start from:

* Stair `0`
* Stair `1`

And from any stair, you can move:

* 1 step
* 2 steps

Your goal is to reach the **top floor** (just after the last stair) with **minimum total cost**.

---

## Example 1

```java
cost = [10,15,20]
```

Visualization:

```text
Top
 ↑
20
 ↑
15
 ↑
10
```

Possible paths:

### Start at 0

```text
10 → 15 → Top

Cost = 25
```

### Start at 1

```text
15 → Top

Cost = 15
```

Answer:

```java
15
```

---

## Example 2

```java
cost = [1,100,1,1,1,100,1,1,100,1]
```

Best path:

```text
1 → 1 → 1 → 1 → 1 → 1
```

Total:

```java
6
```

Answer:

```java
6
```

---

# Key Observation

At every stair:

```text
You have 2 choices

1. Move 1 step
2. Move 2 steps
```

Whenever a problem says:

```text
Choose minimum/maximum among multiple choices
```

and

```text
Same states repeat
```

it is usually **Dynamic Programming**.

---

# How to Think

Suppose:

```java
cost = [10,15,20]
```

Let:

```java
f(i)
```

be

```text
Minimum cost needed to reach the top
starting from stair i
```

From stair `i`:

```text
Pay cost[i]

Then either:

go to i+1
or
go to i+2
```

Therefore:

```java
f(i) = cost[i] + min(f(i+1), f(i+2))
```

This is the recurrence relation.

---

# Brute Force (Recursion)

## Idea

Try both choices from every stair.

```java
class Solution {

    public int minCostClimbingStairs(int[] cost) {

        return Math.min(
            solve(0, cost),
            solve(1, cost)
        );
    }

    private int solve(int i, int[] cost) {

        if(i >= cost.length) {
            return 0;
        }

        return cost[i] +
               Math.min(
                   solve(i + 1, cost),
                   solve(i + 2, cost)
               );
    }
}
```

---

# Dry Run

```java
cost = [10,15,20]
```

Call:

```java
solve(0)
```

Tree:

```text
solve(0)

10 +
├── solve(1)
│   15 +
│   ├── solve(2)
│   └── solve(3)
│
└── solve(2)
    20 +
    ├── solve(3)
    └── solve(4)
```

Notice:

```text
solve(2)
```

is computed multiple times.

This is the overlapping subproblem property.

---

# Complexity

### Time

```text
O(2^n)
```

Every stair branches into 2 choices.

### Space

```text
O(n)
```

Recursion stack.

---

# Optimal Solution 1 (DP Memoization)

## Idea

Store already calculated answers.

```java
class Solution {

    public int minCostClimbingStairs(int[] cost) {

        int[] dp = new int[cost.length];

        Arrays.fill(dp, -1);

        return Math.min(
            solve(0, cost, dp),
            solve(1, cost, dp)
        );
    }

    private int solve(int i, int[] cost, int[] dp) {

        if(i >= cost.length) {
            return 0;
        }

        if(dp[i] != -1) {
            return dp[i];
        }

        dp[i] = cost[i] +
                Math.min(
                    solve(i + 1, cost, dp),
                    solve(i + 2, cost, dp)
                );

        return dp[i];
    }
}
```

---

## Complexity

### Time

```text
O(n)
```

Each state computed once.

### Space

```text
O(n)
```

DP array.

```text
+
```

```text
O(n)
```

Recursion stack.

Total:

```text
O(n)
```

---

# Optimal Solution 2 (Bottom-Up DP)

## Idea

Build answer from smaller states.

### Meaning

```java
dp[i]
```

=

```text
Minimum cost needed to reach stair i
```

---

## Formula

To reach stair `i`:

```text
Come from i-1
or
Come from i-2
```

Therefore:

```java
dp[i] = min(
            dp[i-1] + cost[i-1],
            dp[i-2] + cost[i-2]
         );
```

---

## Code

```java
class Solution {

    public int minCostClimbingStairs(int[] cost) {

        int n = cost.length;

        int[] dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = 0;

        for(int i = 2; i <= n; i++) {

            dp[i] = Math.min(
                        dp[i - 1] + cost[i - 1],
                        dp[i - 2] + cost[i - 2]
                    );
        }

        return dp[n];
    }
}
```

---

## Dry Run

```java
cost = [10,15,20]
```

### Initial

```text
dp = [0,0,0,0]
```

### i = 2

```java
dp[2] = min(
            dp[1]+15,
            dp[0]+10
         )
      = min(15,10)
      = 10
```

```text
dp = [0,0,10,0]
```

### i = 3

```java
dp[3] = min(
            dp[2]+20,
            dp[1]+15
         )
      = min(30,15)
      = 15
```

```text
dp = [0,0,10,15]
```

Answer:

```java
15
```

---

# Most Optimal (Space Optimized DP)

Notice:

```java
dp[i]
```

depends only on:

```java
dp[i-1]
dp[i-2]
```

So we don't need the entire array.

---

## Code

```java
class Solution {

    public int minCostClimbingStairs(int[] cost) {

        int prev2 = 0;
        int prev1 = 0;

        for(int i = 2; i <= cost.length; i++) {

            int curr = Math.min(
                           prev1 + cost[i - 1],
                           prev2 + cost[i - 2]
                       );

            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }
}
```

---

# Complexity

### Time

```text
O(n)
```

### Space

```text
O(1)
```

---

# How to Identify This Is a DP Problem

Look for these clues:

### 1. Minimum / Maximum Question

```text
Minimum cost
Maximum profit
Minimum path
Maximum score
```

LeetCode 746 asks:

```text
Minimum Cost
```

---

### 2. Multiple Choices at Every Step

```text
Take 1 step
Take 2 steps
```

---

### 3. Same State Repeats

```java
solve(2)
```

gets called many times in recursion.

---

### 4. Recurrence Can Be Written

```java
f(i) = cost[i] + min(f(i+1), f(i+2))
```

Once you can write a recurrence relation, DP is usually applicable.

---

# Interview Summary

### Brute Force

```text
Recursion
Time  : O(2^n)
Space : O(n)
```

### Memoization DP

```text
Time  : O(n)
Space : O(n)
```

### Bottom-Up DP

```text
Time  : O(n)
Space : O(n)
```

### Space Optimized DP (Best)

```text
Time  : O(n)
Space : O(1)
```

### Core DP Relation

```java
f(i) = cost[i] + Math.min(f(i+1), f(i+2))
```

This is one of the easiest and most important **"1-step / 2-step DP"** patterns, very similar to **LeetCode 70 (Climbing Stairs)**, except here we minimize cost instead of counting ways.

</p>
