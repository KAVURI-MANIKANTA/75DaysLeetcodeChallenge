<h2><a href="https://leetcode.com/problems/find-the-town-judge">997. Find the Town Judge</a></h2><h3>Easy</h3><hr><p>In a town, there are <code>n</code> people labeled from <code>1</code> to <code>n</code>. There is a rumor that one of these people is secretly the town judge.</p>

<p>If the town judge exists, then:</p>

<ol>
	<li>The town judge trusts nobody.</li>
	<li>Everybody (except for the town judge) trusts the town judge.</li>
	<li>There is exactly one person that satisfies properties <strong>1</strong> and <strong>2</strong>.</li>
</ol>

<p>You are given an array <code>trust</code> where <code>trust[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> representing that the person labeled <code>a<sub>i</sub></code> trusts the person labeled <code>b<sub>i</sub></code>. If a trust relationship does not exist in <code>trust</code> array, then such a trust relationship does not exist.</p>

<p>Return <em>the label of the town judge if the town judge exists and can be identified, or return </em><code>-1</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2, trust = [[1,2]]
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, trust = [[1,3],[2,3]]
<strong>Output:</strong> 3
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 3, trust = [[1,3],[2,3],[3,1]]
<strong>Output:</strong> -1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= trust.length &lt;= 10<sup>4</sup></code></li>
	<li><code>trust[i].length == 2</code></li>
	<li>All the pairs of <code>trust</code> are <strong>unique</strong>.</li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>1 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n</code></li>
</ul>
<p>
	
# LeetCode 997 — Find the Town Judge

## Problem Idea

You are given:

* `n` people labeled from `1` to `n`
* `trust[i] = [a, b]`
  → person `a` trusts person `b`

The **town judge** is the person who:

1. Trusts nobody
2. Everybody else trusts them

We must return the judge number, otherwise `-1`.

---

# Example

```text
n = 3
trust = [[1,3],[2,3]]
```

### Observation

* Person 1 trusts 3
* Person 2 trusts 3
* Person 3 trusts nobody

So:

```text
Judge = 3
```

---

# Brute Force Approach

## Idea

For every person:

* Check:

  * Do they trust nobody?
  * Does everyone trust them?

---

## Brute Force Code (Java)

```java
class Solution {
    public int findJudge(int n, int[][] trust) {

        for(int person = 1; person <= n; person++) {

            boolean trustsSomeone = false;
            int trustedBy = 0;

            for(int[] t : trust) {

                // person trusts someone
                if(t[0] == person) {
                    trustsSomeone = true;
                }

                // someone trusts person
                if(t[1] == person) {
                    trustedBy++;
                }
            }

            // Judge condition
            if(!trustsSomeone && trustedBy == n - 1) {
                return person;
            }
        }

        return -1;
    }
}
```

---

# Brute Force Dry Run

```text
n = 3
trust = [[1,3],[2,3]]
```

## person = 1

* trustsSomeone = true
* not judge

## person = 2

* trustsSomeone = true
* not judge

## person = 3

* trustsSomeone = false
* trustedBy = 2
* n-1 = 2

So return `3`.

---

# Brute Force Complexity

## Time Complexity

```text
O(n * trust.length)
```

## Space Complexity

```text
O(1)
```

---

# HashMap Approach

## Idea

We maintain two HashMaps:

* `out`
  → how many people a person trusts

* `in`
  → how many people trust that person

Judge condition:

```text
outdegree = 0
indegree = n - 1
```

---

# HashMap Code (Java)

```java
class Solution {
    public int findJudge(int n, int[][] trust) {

        HashMap<Integer, Integer> out = new HashMap<>();
        HashMap<Integer, Integer> in = new HashMap<>();

        for(int[] t : trust) {

            int a = t[0];
            int b = t[1];

            // a trusts someone
            out.put(a, out.getOrDefault(a, 0) + 1);

            // b is trusted by someone
            in.put(b, in.getOrDefault(b, 0) + 1);
        }

        for(int i = 1; i <= n; i++) {

            int outdegree = out.getOrDefault(i, 0);
            int indegree = in.getOrDefault(i, 0);

            // Judge condition
            if(outdegree == 0 && indegree == n - 1) {
                return i;
            }
        }

        return -1;
    }
}
```

---

# HashMap Dry Run

## Input

```text
n = 3
trust = [[1,3],[2,3]]
```

---

## Process `[1,3]`

```text
out[1] = 1
in[3] = 1
```

---

## Process `[2,3]`

```text
out[2] = 1
in[3] = 2
```

Now:

```text
out = {1=1, 2=1}
in  = {3=2}
```

---

## Check Each Person

### person = 1

```text
outdegree = 1
indegree = 0
```

Not judge.

---

### person = 2

```text
outdegree = 1
indegree = 0
```

Not judge.

---

### person = 3

```text
outdegree = 0
indegree = 2
```

Since:

```text
indegree = n - 1
outdegree = 0
```

Return:

```text
3
```

---

# HashMap Complexity

## Time Complexity

```text
O(n + trust.length)
```

## Space Complexity

```text
O(n)
```

---

# Optimal Approach (Indegree-Outdegree)

## Core Observation

For judge:

```text
indegree = n - 1
outdegree = 0
```

Where:

* `indegree`
  = how many people trust them

* `outdegree`
  = how many people they trust

---

# Optimal Idea

Create one array:

```java
score[i]
```

Rules:

```text
If a trusts b:

a loses 1  -> score[a]--
b gains 1  -> score[b]++
```

For judge:

```text
score[judge] = n - 1
```

Why?

Because:

```text
trusted by (n-1) people
and trusts nobody
```

---

# Optimal Code (Java)

```java
class Solution {
    public int findJudge(int n, int[][] trust) {

        int[] score = new int[n + 1];

        for(int[] t : trust) {

            int a = t[0];
            int b = t[1];

            score[a]--; // trusts someone
            score[b]++; // trusted by someone
        }

        for(int i = 1; i <= n; i++) {

            if(score[i] == n - 1) {
                return i;
            }
        }

        return -1;
    }
}
```

---

# Optimal Dry Run

## Input

```text
n = 3
trust = [[1,3],[2,3]]
```

Initial:

```text
score = [0,0,0,0]
```

---

## trust = [1,3]

```text
score[1]--
score[3]++
```

```text
score = [0,-1,0,1]
```

---

## trust = [2,3]

```text
score[2]--
score[3]++
```

```text
score = [0,-1,-1,2]
```

Now:

```text
score[3] = 2 = n-1
```

Return:

```text
3
```

---

# Optimal Complexity

## Time Complexity

```text
O(n + trust.length)
```

## Space Complexity

```text
O(n)
```

---

# Which Approach is Better?

| Approach      | Time     | Space |
| ------------- | -------- | ----- |
| Brute Force   | O(n × m) | O(1)  |
| HashMap       | O(n + m) | O(n)  |
| Optimal Array | O(n + m) | O(n)  |

Where:

```text
m = trust.length
```

The array solution is the best because arrays are faster than HashMaps.

---

# Important Edge Case

## Input

```text
n = 3
trust = [[1,3],[2,3],[3,1]]
```

Even though person `3` is trusted by two people:

```text
1 trusts 3
2 trusts 3
```

Person `3` also trusts someone:

```text
3 trusts 1
```

So person `3` cannot be judge.

Answer:

```text
-1
```

---

# Interview Explanation

You can say:

> A town judge trusts nobody and is trusted by everyone else.
>
> For every trust relation:
>
> * truster loses one point
> * trusted person gains one point
>
> Finally, the judge will have score = n-1.

</p>
