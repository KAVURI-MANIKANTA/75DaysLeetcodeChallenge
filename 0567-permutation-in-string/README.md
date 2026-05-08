<h2><a href="https://leetcode.com/problems/permutation-in-string">567. Permutation in String</a></h2><h3>Medium</h3><hr><p>Given two strings <code>s1</code> and <code>s2</code>, return <code>true</code> if <code>s2</code> contains a <span data-keyword="permutation-string">permutation</span> of <code>s1</code>, or <code>false</code> otherwise.</p>

<p>In other words, return <code>true</code> if one of <code>s1</code>&#39;s permutations is the substring of <code>s2</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;ab&quot;, s2 = &quot;eidbaooo&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> s2 contains one permutation of s1 (&quot;ba&quot;).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;ab&quot;, s2 = &quot;eidboaoo&quot;
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s1</code> and <code>s2</code> consist of lowercase English letters.</li>
</ul>
<p>
	
# LeetCode 567 — Permutation in String

Problem:
Given two strings `s1` and `s2`, return `true` if `s2` contains a permutation of `s1`, otherwise return `false`.

Example:

```text
Input: s1 = "ab", s2 = "eidbaooo"
Output: true
```

Because `"ba"` is a permutation of `"ab"`.

---

# 1) Brute Force Solution

## Idea

* Generate every substring of length `s1.length()` from `s2`
* Sort both strings and compare
* If any substring matches → return `true`

---

# Brute Force Code (Java)

```java
import java.util.Arrays;

class Solution {
    public boolean checkInclusion(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        if (n > m) {
            return false;
        }

        // Sort s1
        char[] arr1 = s1.toCharArray();
        Arrays.sort(arr1);

        // Check every substring
        for (int i = 0; i <= m - n; i++) {

            String sub = s2.substring(i, i + n);

            char[] arr2 = sub.toCharArray();
            Arrays.sort(arr2);

            // Compare
            if (Arrays.equals(arr1, arr2)) {
                return true;
            }
        }

        return false;
    }
}
```

---

# Brute Force Explanation

## Step-by-step

Suppose:

```text
s1 = "ab"
s2 = "eidbaooo"
```

### Step 1:

Sort `s1`

```text
"ab" -> "ab"
```

---

### Step 2:

Take every substring of length 2 from `s2`

```text
"ei"
"id"
"db"
"ba"
"ao"
"oo"
"oo"
```

---

### Step 3:

Sort each substring and compare

```text
"ei" -> "ei"
"id" -> "di"
"db" -> "bd"
"ba" -> "ab"   <-- matched
```

Return `true`.

---

# Time Complexity

For every substring:

* Sorting takes `O(n log n)`

Total substrings:

* `m - n + 1`

## TC

```text
O((m - n + 1) * n log n)
```

Simplified:

```text
O(m * n log n)
```

---

# Space Complexity

Sorting arrays uses extra space.

## SC

```text
O(n)
```

---

# 2) Optimal Solution — Sliding Window + Frequency Count

## Main Idea

Instead of sorting every substring:

* Count character frequencies
* Use sliding window of size `s1.length()`

If frequencies match → permutation exists.

---

# Optimal Code (Java)

```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        if (n > m) {
            return false;
        }

        int[] s1Count = new int[26];
        int[] windowCount = new int[26];

        // Store frequency of s1
        for (int i = 0; i < n; i++) {
            s1Count[s1.charAt(i) - 'a']++;
            windowCount[s2.charAt(i) - 'a']++;
        }

        // Check first window
        if (matches(s1Count, windowCount)) {
            return true;
        }

        // Sliding window
        for (int i = n; i < m; i++) {

            // Add new character
            windowCount[s2.charAt(i) - 'a']++;

            // Remove old character
            windowCount[s2.charAt(i - n) - 'a']--;

            // Compare frequencies
            if (matches(s1Count, windowCount)) {
                return true;
            }
        }

        return false;
    }

    // Compare two frequency arrays
    private boolean matches(int[] a, int[] b) {

        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }

        return true;
    }
}
```

---

# Optimal Solution Explanation

---

## Step 1: Frequency Array

We store character counts.

Example:

```text
s1 = "ab"
```

Frequency:

```text
a -> 1
b -> 1
```

---

## Step 2: First Window

Take first window from `s2`

```text
s2 = "eidbaooo"

First window = "ei"
```

Frequency:

```text
e -> 1
i -> 1
```

Not equal.

---

## Step 3: Slide Window

Move one step:

```text
"id"
"db"
"ba"
```

When window becomes `"ba"`:

Frequency:

```text
a -> 1
b -> 1
```

Matches `s1`.

Return `true`.

---

# Why Sliding Window Works

We only:

* Add one new character
* Remove one old character

So each window update is very fast.

---

# Time Complexity

Building arrays:

```text
O(n)
```

Sliding window:

```text
O(m)
```

Each comparison checks 26 characters:

```text
O(26) = constant
```

## Final TC

```text
O(m)
```

---

# Space Complexity

Two arrays of size 26.

## SC

```text
O(1)
```

(Constant space)

---

# Interview Points

## Brute Force

* Generate all substrings
* Sort and compare
* Inefficient

---

## Optimal

* Sliding window
* Frequency arrays
* Constant space
* Linear time

---

# Very Short Revision

## Brute Force

```text
Generate every substring
Sort and compare

TC -> O(m * n log n)
SC -> O(n)
```

---

## Optimal

```text
Sliding window + frequency count

TC -> O(m)
SC -> O(1)
```
# LeetCode 567 — HashMap Solution (Java)

This solution uses:

* `HashMap<Character, Integer>`
* Sliding Window

---

# Idea

We:

1. Store frequency of characters of `s1` in a HashMap
2. Take a window of size `s1.length()` in `s2`
3. Maintain frequencies of current window using another HashMap
4. Compare both maps
5. If equal → permutation exists

---

# HashMap Solution Code

```java id="6spx64"
import java.util.HashMap;

class Solution {
    public boolean checkInclusion(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        if (n > m) {
            return false;
        }

        HashMap<Character, Integer> s1Map = new HashMap<>();
        HashMap<Character, Integer> windowMap = new HashMap<>();

        // Store frequency of s1
        for (char ch : s1.toCharArray()) {
            s1Map.put(ch, s1Map.getOrDefault(ch, 0) + 1);
        }

        // First window
        for (int i = 0; i < n; i++) {
            char ch = s2.charAt(i);
            windowMap.put(ch, windowMap.getOrDefault(ch, 0) + 1);
        }

        // Compare first window
        if (s1Map.equals(windowMap)) {
            return true;
        }

        // Sliding window
        for (int i = n; i < m; i++) {

            // Add new character
            char addChar = s2.charAt(i);
            windowMap.put(addChar,
                    windowMap.getOrDefault(addChar, 0) + 1);

            // Remove old character
            char removeChar = s2.charAt(i - n);

            windowMap.put(removeChar,
                    windowMap.get(removeChar) - 1);

            // Remove key if frequency becomes 0
            if (windowMap.get(removeChar) == 0) {
                windowMap.remove(removeChar);
            }

            // Compare maps
            if (s1Map.equals(windowMap)) {
                return true;
            }
        }

        return false;
    }
}
```

---

# Explanation

Example:

```text id="q9e98z"
s1 = "ab"
s2 = "eidbaooo"
```

---

## Step 1: Store frequency of s1

```text id="0th5cg"
a -> 1
b -> 1
```

---

## Step 2: First window

Window:

```text id="q0c24x"
"ei"
```

Map:

```text id="vkw7i7"
e -> 1
i -> 1
```

Not equal.

---

## Step 3: Slide Window

Move window:

```text id="hr5ggs"
"id"
"db"
"ba"
```

When window becomes `"ba"`:

```text id="i2xys2"
b -> 1
a -> 1
```

Matches `s1Map`.

Return `true`.

---

# Why remove key when count becomes 0?

Suppose:

```text id="wdp7d0"
a -> 0
```

If we keep it:

```text id="yx6rx6"
{a=0, b=1}
```

This is NOT equal to:

```text id="8c0c5r"
{b=1}
```

So we remove zero-frequency keys.

---

# Time Complexity

## Building maps

```text id="gvtz3r"
O(n)
```

## Sliding window

```text id="5m3jhv"
O(m)
```

## Map comparison

HashMap `.equals()` may take:

```text id="j8k4pf"
O(26)
```

(for lowercase letters)

---

# Final TC

```text id="xyc3po"
O(m)
```

---

# Space Complexity

Two HashMaps storing at most 26 chars.

```text id="lg8v07"
O(1)
```

(Constant because only lowercase English letters)

---

# Difference: HashMap vs Array Solution

| Feature              | HashMap | Array |
| -------------------- | ------- | ----- |
| Works for any chars  | Yes     | No    |
| Faster               | No      | Yes   |
| Easier for lowercase | No      | Yes   |
| Space                | O(1)    | O(1)  |

---

# Interview Note

For lowercase English letters:

```text id="3sqygk"
Use int[26]
```

For general Unicode/characters:

```text id="z1g7hu"
Use HashMap
```

</p>
