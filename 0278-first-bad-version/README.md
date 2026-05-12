<h2><a href="https://leetcode.com/problems/first-bad-version">278. First Bad Version</a></h2><h3>Easy</h3><hr><p>You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.</p>

<p>Suppose you have <code>n</code> versions <code>[1, 2, ..., n]</code> and you want to find out the first bad one, which causes all the following ones to be bad.</p>

<p>You are given an API <code>bool isBadVersion(version)</code> which returns whether <code>version</code> is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5, bad = 4
<strong>Output:</strong> 4
<strong>Explanation:</strong>
call isBadVersion(3) -&gt; false
call isBadVersion(5)&nbsp;-&gt; true
call isBadVersion(4)&nbsp;-&gt; true
Then 4 is the first bad version.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1, bad = 1
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= bad &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
</ul>
<p>
	
# LeetCode 278 — First Bad Version

---

# Problem Statement

You are given versions from `1` to `n`.

After a bad version appears, all next versions are also bad.

Find the **first bad version**.

You can use API:

```java id="twhxdi"
boolean isBadVersion(int version)
```

---

# 1) Brute Force Approach

## Idea

Check every version one by one from `1`.

The first version returning `true` is the answer.

---

# Brute Force Code

```java id="4nh8k5"
public class Solution extends VersionControl {
    
    public int firstBadVersion(int n) {
        
        for (int i = 1; i <= n; i++) {
            
            if (isBadVersion(i)) {
                return i;
            }
        }
        
        return -1;
    }
}
```

---

# Brute Force Explanation

### Step 1

Loop from `1` to `n`

```java id="2ttmv7"
for (int i = 1; i <= n; i++)
```

---

### Step 2

Check if version is bad

```java id="u4x8zd"
isBadVersion(i)
```

---

### Step 3

If bad, return immediately

```java id="q33w8z"
return i;
```

Because first bad version is found.

---

# Example

```text id="f13fqj"
Versions: 1 2 3 4 5
First bad = 4
```

Checks:

```text id="8vruvb"
1 → false
2 → false
3 → false
4 → true
```

Return `4`

---

# Brute Force Complexity

## Time Complexity

```text id="y9xw3v"
O(n)
```

Because we may check all versions.

---

## Space Complexity

```text id="af36qj"
O(1)
```

No extra space used.

---

# Drawback

Very slow for large `n`.

So we use Binary Search.

---

# 2) Optimal Approach — Binary Search

---

# Main Idea

* Left side → good versions
* Right side → bad versions

We need first bad version.

Binary Search reduces search space by half.

---

# Optimal Code

```java id="87p6l4"
public class Solution extends VersionControl {
    
    public int firstBadVersion(int n) {
        
        int low = 1;
        int high = n;
        
        while (low < high) {
            
            int mid = low + (high - low) / 2;
            
            if (isBadVersion(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        
        return low;
    }
}
```

---

# Optimal Code Explanation

---

## Step 1: Initialize Search Space

```java id="p2zx7q"
int low = 1;
int high = n;
```

Search between version `1` and `n`.

---

## Step 2: Run Binary Search

```java id="9w6njk"
while (low < high)
```

Continue until both point to same version.

---

## Step 3: Find Middle

```java id="s6pj39"
int mid = low + (high - low) / 2;
```

Used to avoid integer overflow.

---

## Step 4: Check Mid Version

### If mid is bad

```java id="4n2f9e"
if (isBadVersion(mid))
```

Then first bad version can be:

* mid itself
* or left side

So move left:

```java id="nmgm0d"
high = mid;
```

---

### If mid is good

Then first bad version is on right side.

```java id="b3j89f"
low = mid + 1;
```

---

## Step 5: Return Answer

When:

```text id="m8qjln"
low == high
```

That position is first bad version.

```java id="u8kjw2"
return low;
```

---

# Dry Run

Suppose:

```text id="qtjlwm"
n = 5
First bad = 4
```

---

## Iteration 1

```text id="77xq8p"
low = 1
high = 5
mid = 3
```

`isBadVersion(3)` → false

Move right:

```text id="jqqk2p"
low = 4
```

---

## Iteration 2

```text id="mjlwm6"
low = 4
high = 5
mid = 4
```

`isBadVersion(4)` → true

Move left:

```text id="n6h7z2"
high = 4
```

---

Now:

```text id="a8q7df"
low = 4
high = 4
```

Stop loop.

Answer = `4`

---

# Optimal Complexity

## Time Complexity

```text id="2q7k7m"
O(log n)
```

Because search space halves every time.

---

## Space Complexity

```text id="8vfd77"
O(1)
```

No extra memory used.

---

# Comparison Table

| Approach      | Time Complexity | Space Complexity |
| ------------- | --------------- | ---------------- |
| Brute Force   | O(n)            | O(1)             |
| Binary Search | O(log n)        | O(1)             |

---

# Important Interview Points

## Why Binary Search Works?

Because versions follow pattern:

```text id="7w7vsm"
false false false true true true
```

This is sorted behavior.

---

## Why Use This?

```java id="g9zq32"
low + (high - low) / 2
```

Instead of:

```java id="y4x3p9"
(low + high) / 2
```

To avoid integer overflow for large values.

</p>
