<h2><a href="https://leetcode.com/problems/reverse-string">344. Reverse String</a></h2><h3>Easy</h3><hr><p>Write a function that reverses a string. The input string is given as an array of characters <code>s</code>.</p>

<p>You must do this by modifying the input array <a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank">in-place</a> with <code>O(1)</code> extra memory.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> s = ["h","e","l","l","o"]
<strong>Output:</strong> ["o","l","l","e","h"]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> s = ["H","a","n","n","a","h"]
<strong>Output:</strong> ["h","a","n","n","a","H"]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is a <a href="https://en.wikipedia.org/wiki/ASCII#Printable_characters" target="_blank">printable ascii character</a>.</li>
</ul>
<p>
	
### 🔹 LeetCode 344 Reverse String

**Problem:**
Given a character array `s`, reverse the array **in-place**.

---

# 🧠 1. Brute Force Approach

### 💡 Idea:

* Create a **new array**
* Copy elements from end → start
* Then copy back to original array

---

### ✅ Code (Brute Force)

```java
class Solution {
    public void reverseString(char[] s) {
        int n = s.length;
        char[] temp = new char[n];

        // Fill temp array in reverse
        for (int i = 0; i < n; i++) {
            temp[i] = s[n - 1 - i];
        }

        // Copy back to original array
        for (int i = 0; i < n; i++) {
            s[i] = temp[i];
        }
    }
}
```

---

### 📊 Complexity:

* **Time Complexity (TC):** `O(n)`
  → Two loops (still linear)

* **Space Complexity (SC):** `O(n)`
  → Extra array used ❌

---

### ❌ Drawbacks:

* Not in-place (violates problem constraint)
* Extra memory usage

---

# 🚀 2. Optimal Approach (Two Pointer)

### 💡 Idea:

* Use **two pointers**

  * `left = 0`
  * `right = n - 1`
* Swap elements while moving inward

---

### ✅ Code (Optimal)

```java
class Solution {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;

        while (left < right) {
            // Swap
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            left++;
            right--;
        }
    }
}
```

---

### 📊 Complexity:

* **Time Complexity (TC):** `O(n)`
  → Each element swapped once

* **Space Complexity (SC):** `O(1)`
  → No extra space ✅

---

# 🔍 Dry Run Example

Input:
`s = ['h','e','l','l','o']`

Steps:

```
left=0, right=4 → swap h & o → o e l l h
left=1, right=3 → swap e & l → o l l e h
left=2, right=2 → stop
```

Output:

```
['o','l','l','e','h']
```

---

# ⚖️ Comparison

| Approach    | Time | Space | In-Place | Notes            |
| ----------- | ---- | ----- | -------- | ---------------- |
| Brute Force | O(n) | O(n)  | ❌        | Uses extra array |
| Two Pointer | O(n) | O(1)  | ✅        | Best solution    |

---

# 🎯 Key Takeaway

* Whenever reversal is asked → think **Two Pointer**
* If constraint says **in-place** → avoid extra arrays

---
</p>
