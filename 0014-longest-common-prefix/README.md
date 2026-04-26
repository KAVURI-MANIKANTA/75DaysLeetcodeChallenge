<h2><a href="https://leetcode.com/problems/longest-common-prefix">14. Longest Common Prefix</a></h2><h3>Easy</h3><hr><p>Write a function to find the longest common prefix string amongst an array of strings.</p>

<p>If there is no common prefix, return an empty string <code>&quot;&quot;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;flower&quot;,&quot;flow&quot;,&quot;flight&quot;]
<strong>Output:</strong> &quot;fl&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;dog&quot;,&quot;racecar&quot;,&quot;car&quot;]
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> There is no common prefix among the input strings.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= strs.length &lt;= 200</code></li>
	<li><code>0 &lt;= strs[i].length &lt;= 200</code></li>
	<li><code>strs[i]</code> consists of only lowercase English letters if it is non-empty.</li>
</ul>
<p>
---

# 🧠 Problem Understanding

You are given an array of strings.
You need to find the **longest prefix (starting substring)** that is common to all strings.

👉 Example:

```
Input: ["flower","flow","flight"]
Output: "fl"
```

---

# 1️⃣ Brute Force Approach (Vertical Scanning)

## 💡 Idea

* Take the **first string** as reference.
* Compare **each character** of it with the same position in all other strings.
* If mismatch → stop.

---

## ✅ Code (Java)

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) return "";

        for(int i = 0; i < strs[0].length(); i++){
            char c = strs[0].charAt(i);

            for(int j = 1; j < strs.length; j++){
                // if index out of bounds OR mismatch
                if(i >= strs[j].length() || strs[j].charAt(i) != c){
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
```

---

## 🔍 Explanation (Step-by-step)

Take:

```
["flower","flow","flight"]
```

### Step 1:

Compare index `0`:

* f == f == f → OK

### Step 2:

Compare index `1`:

* l == l == l → OK

### Step 3:

Compare index `2`:

* o == o != i ❌ mismatch

👉 Stop and return substring till index 2 → `"fl"`

---

## ⏱ Complexity

* Time: **O(n * m)**
  (n = number of strings, m = min length)
* Space: **O(1)**

---

## ⚠️ Key Observations

* Stops early when mismatch happens
* Very intuitive and easy

---

# 2️⃣ Optimal Approach (Sorting Trick) ✅ (Your Code)

---

## 💡 Idea

* Sort the array
* Compare **only first and last string**
* Why?
  Because:
  👉 After sorting, most different strings go to extremes
  👉 Common prefix of entire array = common prefix of first & last

---

## ✅ Code (Your Code)

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);

        char[] first = strs[0].toCharArray();
        char[] last = strs[strs.length - 1].toCharArray();

        StringBuilder res = new StringBuilder();

        int n = Math.min(first.length, last.length);

        for(int i = 0; i < n; i++){
            if(first[i] != last[i]) break;
            res.append(first[i]);
        }

        return res.toString();
    }
}
```

---

## 🔍 Deep Explanation

### Step 1: Sorting

```
["flower","flow","flight"] 
→ ["flight","flow","flower"]
```

👉 Notice:

* `"flight"` (smallest)
* `"flower"` (largest)

### Step 2: Compare only these two

```
flight
flower
```

Compare char by char:

* f == f
* l == l
* i != o ❌ stop

👉 Answer = `"fl"`

---

## 🤔 Why This Works?

Sorting ensures:

* Strings with similar prefixes cluster together
* First and last strings will have **maximum difference**
* So their prefix = prefix of all

---

## ⏱ Complexity

* Time: **O(n log n + m)**
* Space: **O(1)** (ignoring sort space)

---

## ⚠️ Important Detail

Your original code had:

```java
int n = first.length;
```

👉 This can cause **IndexOutOfBounds** if last is shorter

✔ Correct:

```java
int n = Math.min(first.length, last.length);
```

---

# 3️⃣ Trie Data Structure Solution 🌳

---

## 💡 Idea

* Insert all words into a **Trie**
* Traverse from root:

  * Continue while:

    * Only **one child**
    * Not end of word
* Build prefix

---

## 🧱 Trie Structure

Each node:

```java
class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd;
}
```

---

## ✅ Code (Java)

```java
class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
    }

    public String longestCommonPrefix(String[] strs) {
        TrieNode root = new TrieNode();

        // Step 1: Insert all words
        for(String word : strs){
            TrieNode node = root;
            for(char c : word.toCharArray()){
                int index = c - 'a';
                if(node.children[index] == null){
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        // Step 2: Traverse trie
        StringBuilder res = new StringBuilder();
        TrieNode node = root;

        while(true){
            int count = 0;
            int idx = -1;

            // count children
            for(int i = 0; i < 26; i++){
                if(node.children[i] != null){
                    count++;
                    idx = i;
                }
            }

            // stop conditions
            if(count != 1 || node.isEnd) break;

            node = node.children[idx];
            res.append((char)(idx + 'a'));
        }

        return res.toString();
    }
}
```

---

## 🔍 Deep Explanation

### Step 1: Insert Words

For:

```
["flower","flow","flight"]
```

Trie becomes:

```
        root
         |
         f
         |
         l
       /   \
      o     i
```

---

### Step 2: Traverse

Start at root:

#### Level 1:

* Only 1 child → f → add 'f'

#### Level 2:

* Only 1 child → l → add 'l'

#### Level 3:

* 2 children → (o, i) ❌ stop

👉 Result = `"fl"`

---

## ⏱ Complexity

* Time: **O(n * m)**
* Space: **O(n * m)** (Trie nodes)

---

## ⚠️ Key Conditions to Stop

You must stop if:

1. Node has **more than 1 child**
2. Node is **end of a word**

---

# ⚖️ Comparison of All Approaches

| Approach      | Time Complexity | Space | Difficulty | When to Use                   |
| ------------- | --------------- | ----- | ---------- | ----------------------------- |
| Brute Force   | O(n * m)        | O(1)  | Easy       | Interviews (safe)             |
| Sorting Trick | O(n log n)      | O(1)  | Easy       | Quick solution                |
| Trie          | O(n * m)        | High  | Medium     | When prefix problems repeated |

---

# 🚀 Interview Insight

* If interviewer asks **basic → use brute force**
* If they want **optimization → sorting**
* If they want **DS knowledge → Trie**

---

# 🔥 Final Takeaway

👉 Most practical: **Brute Force (vertical scanning)**
👉 Most clever: **Sorting trick**
👉 Most conceptual: **Trie**

---
</p>
