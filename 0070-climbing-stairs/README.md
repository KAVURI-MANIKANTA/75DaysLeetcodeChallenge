<h2><a href="https://leetcode.com/problems/climbing-stairs">70. Climbing Stairs</a></h2><h3>Easy</h3><hr><p>You are climbing a staircase. It takes <code>n</code> steps to reach the top.</p>

<p>Each time you can either climb <code>1</code> or <code>2</code> steps. In how many distinct ways can you climb to the top?</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 45</code></li>
</ul>
<p>
	
# LeetCode 70 — Climbing Stairs

## Problem Statement

You are climbing a staircase.

It takes `n` steps to reach the top.

Each time, you can either:

* Climb **1 step**
* Climb **2 steps**

Find the number of distinct ways to reach the top.

### Example 1

```text
n = 2

Way 1: 1 + 1
Way 2: 2

Answer = 2
```

### Example 2

```text
n = 3

Way 1: 1 + 1 + 1
Way 2: 1 + 2
Way 3: 2 + 1

Answer = 3
```

---

# What Concept Is This?

This is a classic **Dynamic Programming (DP)** problem.

More specifically:

```text
Recursion
   ↓
Recursion + Memoization
   ↓
Bottom-Up DP
   ↓
Space Optimized DP
```

It is also related to the **Fibonacci Sequence**.

---

# Why Fibonacci?

Suppose:

```text
f(n) = ways to reach step n
```

To reach step `n`:

### Option 1

Come from step `n-1`

```text
ways = f(n-1)
```

### Option 2

Come from step `n-2`

```text
ways = f(n-2)
```

Therefore:

```text
f(n) = f(n-1) + f(n-2)
```

Exactly the Fibonacci recurrence.

---

# Brute Force (Pure Recursion)

## Idea

Try both possibilities:

```text
Current Step
    |
    +--> Take 1 step
    |
    +--> Take 2 steps
```

Count all valid paths.

---

## Recursive Tree for n = 4

```text
f(4)
├── f(3)
│   ├── f(2)
│   │   ├── f(1)
│   │   └── f(0)
│   └── f(1)
└── f(2)
    ├── f(1)
    └── f(0)
```

Notice:

```text
f(2) computed multiple times
f(1) computed multiple times
```

Lots of repeated work.

---

## Brute Force Code

```java
class Solution {

    public int climbStairs(int n) {
        return solve(n);
    }

    private int solve(int n) {

        if(n == 0 || n == 1) {
            return 1;
        }

        return solve(n - 1) + solve(n - 2);
    }
}
```

---

## Dry Run (n = 4)

```text
solve(4)

= solve(3) + solve(2)

= (solve(2)+solve(1))
  +
  (solve(1)+solve(0))

= (2+1)+(1+1)

= 5
```

Answer:

```text
5 ways
```

---

## Time Complexity

Each call creates two more calls.

```text
TC = O(2^n)
```

---

## Space Complexity

Recursion stack:

```text
SC = O(n)
```

---

# Optimal Solution (Space Optimized DP)

Since:

```text
f(n) = f(n-1) + f(n-2)
```

we only need the previous two answers.

No need for an entire DP array.

---

## Idea

Maintain:

```text
prev2 = f(n-2)
prev1 = f(n-1)
```

Compute:

```text
current = prev1 + prev2
```

Then shift values.

---

## Code

```java
class Solution {

    public int climbStairs(int n) {

        if(n <= 1) {
            return 1;
        }

        int prev2 = 1; // f(0)
        int prev1 = 1; // f(1)

        for(int i = 2; i <= n; i++) {

            int current = prev1 + prev2;

            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}
```

---

## Dry Run (n = 5)

Initial:

```text
prev2 = 1
prev1 = 1
```

### i = 2

```text
current = 1 + 1 = 2

prev2 = 1
prev1 = 2
```

### i = 3

```text
current = 2 + 1 = 3

prev2 = 2
prev1 = 3
```

### i = 4

```text
current = 3 + 2 = 5

prev2 = 3
prev1 = 5
```

### i = 5

```text
current = 5 + 3 = 8

prev2 = 5
prev1 = 8
```

Return:

```text
8
```

---

## Time Complexity

Single loop.

```text
TC = O(n)
```

---

## Space Complexity

Only 3 variables.

```text
SC = O(1)
```

---

# DP Array Version (Intermediate Approach)

```java
class Solution {

    public int climbStairs(int n) {

        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}
```

### Complexity

```text
TC = O(n)
SC = O(n)
```

---

# Interview Answer

**Pattern:**

```text
Dynamic Programming
→ Fibonacci Pattern
→ 1D DP
→ Space Optimization
```

**Recurrence:**

```text
f(n) = f(n-1) + f(n-2)
```

**Best Solution:**

```text
Space Optimized DP
TC = O(n)
SC = O(1)
```

This problem is one of the most important DP starters because many DP problems are just extensions of this Fibonacci-style recurrence.

</p>
