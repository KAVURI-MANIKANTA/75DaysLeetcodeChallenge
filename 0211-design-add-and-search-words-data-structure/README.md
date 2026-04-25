<h2><a href="https://leetcode.com/problems/design-add-and-search-words-data-structure">211. Design Add and Search Words Data Structure</a></h2><h3>Medium</h3><hr><p>Design a data structure that supports adding new words and finding if a string matches any previously added string.</p>

<p>Implement the <code>WordDictionary</code> class:</p>

<ul>
	<li><code>WordDictionary()</code>&nbsp;Initializes the object.</li>
	<li><code>void addWord(word)</code> Adds <code>word</code> to the data structure, it can be matched later.</li>
	<li><code>bool search(word)</code>&nbsp;Returns <code>true</code> if there is any string in the data structure that matches <code>word</code>&nbsp;or <code>false</code> otherwise. <code>word</code> may contain dots <code>&#39;.&#39;</code> where dots can be matched with any letter.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<pre>
<strong>Input</strong>
[&quot;WordDictionary&quot;,&quot;addWord&quot;,&quot;addWord&quot;,&quot;addWord&quot;,&quot;search&quot;,&quot;search&quot;,&quot;search&quot;,&quot;search&quot;]
[[],[&quot;bad&quot;],[&quot;dad&quot;],[&quot;mad&quot;],[&quot;pad&quot;],[&quot;bad&quot;],[&quot;.ad&quot;],[&quot;b..&quot;]]
<strong>Output</strong>
[null,null,null,null,false,true,true,true]

<strong>Explanation</strong>
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord(&quot;bad&quot;);
wordDictionary.addWord(&quot;dad&quot;);
wordDictionary.addWord(&quot;mad&quot;);
wordDictionary.search(&quot;pad&quot;); // return False
wordDictionary.search(&quot;bad&quot;); // return True
wordDictionary.search(&quot;.ad&quot;); // return True
wordDictionary.search(&quot;b..&quot;); // return True
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 25</code></li>
	<li><code>word</code> in <code>addWord</code> consists of lowercase English letters.</li>
	<li><code>word</code> in <code>search</code> consist of <code>&#39;.&#39;</code> or lowercase English letters.</li>
	<li>There will be at most <code>2</code> dots in <code>word</code> for <code>search</code> queries.</li>
	<li>At most <code>10<sup>4</sup></code> calls will be made to <code>addWord</code> and <code>search</code>.</li>
</ul>
<p>

---

# 📘 LeetCode 211 – Design Add and Search Words Data Structure

## 🧠 Problem Summary

Design a data structure that supports:

* `addWord(word)` → Adds a word to the dictionary
* `search(word)` → Returns `true` if the word exists

### 🔥 Special Rule:

* The search word may contain `.` (dot)
* `.` can match **any one letter**

### 📌 Example:

```
addWord("bad")
addWord("dad")
addWord("mad")

search("bad")  → true
search(".ad")  → true   (matches bad, dad, mad)
search("b..")  → true
search("pad")  → false
```

---

# 💡 Key Idea

👉 Use a **Trie (Prefix Tree)**

Why Trie?

* Efficient for storing words
* Allows **branching search** for `.` wildcard

---

# 🏗️ Data Structure Design

## Trie Node

```java
class TrieNode {
    TrieNode[] children = new TrieNode[26]; // for 'a' to 'z'
    boolean isEnd = false; // marks end of word
}
```

---

# ⚙️ Implementation

```java
class WordDictionary {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    // Add word to Trie
    public void addWord(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }

            node = node.children[index];
        }

        node.isEnd = true;
    }

    // Search word (with '.' support)
    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    // DFS helper
    private boolean dfs(String word, int i, TrieNode node) {

        // ❌ Case 1: invalid path
        if (node == null) return false;

        // ✅ Case 2: reached end of word
        if (i == word.length()) {
            return node.isEnd;
        }

        char c = word.charAt(i);

        // ✅ Case 3: normal character
        if (c != '.') {
            return dfs(word, i + 1, node.children[c - 'a']);
        }

        // 🔥 Case 4: wildcard '.'
        for (int k = 0; k < 26; k++) {
            if (node.children[k] != null) {
                if (dfs(word, i + 1, node.children[k])) {
                    return true; // if any path works
                }
            }
        }

        return false;
    }
}
```

---

# 🔍 How It Works

## ✅ Normal Search

Searching `"bad"`:

```
root → 'b' → 'a' → 'd' → check isEnd = true
```

---

## 🔥 Wildcard Search (`.`)

Searching `".ad"`:

```
Step 1: '.' → try all letters (a–z)
Step 2: go to 'a'
Step 3: go to 'd'
Step 4: if any path reaches a valid word → return true
```

👉 `.` = **try all possible paths (branching)**

---

# 🧩 Core Concept (Very Important)

Think of:

```
dfs(word, i, node)
```

👉 Meaning:

> "Can I match the word from index `i` using this node?"

---

### Behavior:

| Character   | Action           |
| ----------- | ---------------- |
| Normal char | Go to one child  |
| `.`         | Try all children |
| End of word | Check `isEnd`    |

---

# ⚡ Complexity

### addWord:

* Time: `O(L)`
* Space: `O(L)`

### search:

* Worst case: `O(26^L)` (all dots)
* Average: much faster due to pruning

---

# 🚨 Common Mistakes

❌ Returning after checking only one child in `.` case
❌ Not checking `node == null`
❌ Forgetting `isEnd` at last
❌ Treating `.` like a normal character

---

# 🆚 Alternative Approaches

| Approach       | Time      | Good?  |
| -------------- | --------- | ------ |
| HashMap + List | O(N × L)  | ❌ Slow |
| Trie + DFS     | Efficient | ✅ Best |
| Trie + BFS     | Efficient | ✅      |

---

# 🚀 Final Takeaway

* Trie is used for efficient word storage
* DFS helps handle wildcard `.`
* `.` introduces **branching search**
* If **any path matches → return true**

---
---
TRIE+BFS
---

```
class WordDictionary {

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        boolean isEnd = false;
    }

    TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (node.next[index] == null) {
                node.next[index] = new TrieNode();
            }

            node = node.next[index];
        }

        node.isEnd = true;
    }

    public boolean search(String word) {
        Queue<TrieNode> queue = new LinkedList<>();
        queue.add(root);

        for (char c : word.toCharArray()) {

            int size = queue.size();

            // If no nodes to explore → fail
            if (size == 0) return false;

            for (int i = 0; i < size; i++) {
                TrieNode node = queue.poll();

                if (c == '.') {
                    // try all children
                    for (TrieNode child : node.next) {
                        if (child != null) {
                            queue.add(child);
                        }
                    }
                } else {
                    // normal char
                    if (node.next[c - 'a'] != null) {
                        queue.add(node.next[c - 'a']);
                    }
                }
            }
        }

        // After processing all characters
        for (TrieNode node : queue) {
            if (node.isEnd) return true;
        }

        return false;
    }
}
```
</p>
