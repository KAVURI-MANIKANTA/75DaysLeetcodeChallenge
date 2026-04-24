<h2><a href="https://leetcode.com/problems/implement-trie-prefix-tree">208. Implement Trie (Prefix Tree)</a></h2><h3>Medium</h3><hr><p>A <a href="https://en.wikipedia.org/wiki/Trie" target="_blank"><strong>trie</strong></a> (pronounced as &quot;try&quot;) or <strong>prefix tree</strong> is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.</p>

<p>Implement the Trie class:</p>

<ul>
	<li><code>Trie()</code> Initializes the trie object.</li>
	<li><code>void insert(String word)</code> Inserts the string <code>word</code> into the trie.</li>
	<li><code>boolean search(String word)</code> Returns <code>true</code> if the string <code>word</code> is in the trie (i.e., was inserted before), and <code>false</code> otherwise.</li>
	<li><code>boolean startsWith(String prefix)</code> Returns <code>true</code> if there is a previously inserted string <code>word</code> that has the prefix <code>prefix</code>, and <code>false</code> otherwise.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;Trie&quot;, &quot;insert&quot;, &quot;search&quot;, &quot;search&quot;, &quot;startsWith&quot;, &quot;insert&quot;, &quot;search&quot;]
[[], [&quot;apple&quot;], [&quot;apple&quot;], [&quot;app&quot;], [&quot;app&quot;], [&quot;app&quot;], [&quot;app&quot;]]
<strong>Output</strong>
[null, null, true, false, true, null, true]

<strong>Explanation</strong>
Trie trie = new Trie();
trie.insert(&quot;apple&quot;);
trie.search(&quot;apple&quot;);   // return True
trie.search(&quot;app&quot;);     // return False
trie.startsWith(&quot;app&quot;); // return True
trie.insert(&quot;app&quot;);
trie.search(&quot;app&quot;);     // return True
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length, prefix.length &lt;= 2000</code></li>
	<li><code>word</code> and <code>prefix</code> consist only of lowercase English letters.</li>
	<li>At most <code>3 * 10<sup>4</sup></code> calls <strong>in total</strong> will be made to <code>insert</code>, <code>search</code>, and <code>startsWith</code>.</li>
</ul>
<p>
========================================================================================

**cheat sheet of Trie syntax** 👇
````
---

# 🔥 1. Create Node

```java
TrieNode node = new TrieNode();
```

👉 Create a new node

---

# 🔥 2. Access children array

```java
node.children
```

👉 This is an array of size 26 (a–z)

---

# 🔥 3. Convert char → index

```java
int index = ch - 'a';
```

👉 VERY IMPORTANT
👉 Used everywhere

---

# 🔥 4. Check if path exists

```java
if (node.children[index] == null)
```

👉 Means:

> “This character path does NOT exist”

---

# 🔥 5. Create new node (insert step)

```java
node.children[index] = new TrieNode();
```

👉 Build path

---

# 🔥 6. Move to next node

```java
node = node.children[index];
```

👉 Traverse forward

---

# 🔥 7. Mark end of word

```java
node.isEnd = true;
```

👉 Word completed

---

# 🔥 8. Check full word exists

```java
return node.isEnd;
```

👉 Used in `search()`

---

# 🔥 9. Loop through word

```java
for (char ch : word.toCharArray())
```

👉 Iterate characters

---

# 🔥 10. Full insert pattern

```java
TrieNode node = root;

for (char ch : word.toCharArray()) {
    int index = ch - 'a';

    if (node.children[index] == null) {
        node.children[index] = new TrieNode();
    }

    node = node.children[index];
}

node.isEnd = true;
```

---

# 🔥 11. Full search pattern

```java
TrieNode node = root;

for (char ch : word.toCharArray()) {
    int index = ch - 'a';

    if (node.children[index] == null) {
        return false;
    }

    node = node.children[index];
}

return node.isEnd;
```

---

# 🔥 12. Full prefix pattern

```java
TrieNode node = root;

for (char ch : prefix.toCharArray()) {
    int index = ch - 'a';

    if (node.children[index] == null) {
        return false;
    }

    node = node.children[index];
}

return true;
```

---

# 🧠 SUPER IMPORTANT (must remember)

👉 These 3 lines are the **heart of Trie**

```java
int index = ch - 'a';
if (node.children[index] == null)
node = node.children[index];
```

---

# 🔥 Bonus (common variations)

## ✔ Count words

```java
node.count++;
```

---

## ✔ Store full word (optional)

```java
node.word = word;
```

---

## ✔ Check leaf node

```java
if (node.isEnd)
```

---

# 🧠 Memory Trick

👉 Every Trie problem = same pattern:

```text
START → LOOP → CHECK → MOVE → END
```

---
````


---

# 🟢 First: What problem are we solving?

From Implement Trie (Prefix Tree)

We need 3 things:

```text
insert(word)
search(word)
startsWith(prefix)
```

---

# 🔥 Idea BEFORE code (must understand)

Think like this:

👉 Words are stored like **paths**

Example:

```text
cat, car
```

Structure:

```text
root
 └── c
      └── a
           ├── t (word ends)
           └── r (word ends)
```

---

# 🟡 CODE 1: REAL TRIE (Tree structure)

---
```
class TrieNode{
    TrieNode[] children;
    boolean isEnd;
    TrieNode(){
        children = new TrieNode[26];
        isEnd = false;
    }
}
class Trie {
    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode node = root;
        for(char ch:word.toCharArray()){
            int index = ch-'a';
            if(node.children[index]==null){
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }
    
    public boolean search(String word) {
        TrieNode node = root;
        for(char ch:word.toCharArray()){
            int index = ch-'a';
            if(node.children[index]==null){
                return false;
            }
            node = node.children[index];
        }
        return node.isEnd;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for(char ch:prefix.toCharArray()){
            int index = ch-'a';
            if(node.children[index]==null){
                return false;
            }
            node = node.children[index];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
```
## 🔹 Step 1: TrieNode (building block)

```java
class TrieNode{
    TrieNode[] children;
    boolean isEnd;
```

👉 Each node has:

* `children[26]` → next letters (a–z)
* `isEnd` → is this a complete word?

---

### Constructor

```java
TrieNode(){
    children = new TrieNode[26];
    isEnd = false;
}
```

👉 Initially:

* no children
* not end of word

---

## 🔹 Step 2: Trie class

```java
TrieNode root;
```

👉 root = starting point (empty)

---

```java
public Trie() {
    root = new TrieNode();
}
```

---

# 🔥 INSERT (most important)

```java
public void insert(String word) {
    TrieNode node = root;
```

👉 Start from root

---

```java
for(char ch:word.toCharArray()){
```

👉 Go letter by letter

---

```java
int index = ch-'a';
```

👉 Convert char → number

Example:

```text
a → 0
b → 1
c → 2
```

---

```java
if(node.children[index]==null){
    node.children[index] = new TrieNode();
}
```

👉 If path doesn’t exist → create it

---

```java
node = node.children[index];
```

👉 Move forward

---

```java
node.isEnd = true;
```

👉 Mark end of word

---

## 🧠 Insert Summary

👉 “Create path if missing + move forward + mark end”

---

# 🔥 SEARCH

```java
TrieNode node = root;
```

👉 Start from root

---

```java
for(char ch:word.toCharArray()){
```

---

```java
if(node.children[index]==null){
    return false;
}
```

👉 Path missing → word not present

---

```java
node = node.children[index];
```

👉 Move forward

---

```java
return node.isEnd;
```

👉 Only return true if full word exists

---

## 🧠 Example

Inserted: `"cat"`

```text
search("ca") → false ❌ (not full word)
search("cat") → true ✅
```

---

# 🔥 startsWith

Same as search BUT:

```java
return true;
```

👉 No need to check `isEnd`

---

## 🧠 Example

```text
startsWith("ca") → true ✅
```

---

# 🟡 CODE 2: HashSet Approach (NOT Trie)

---
```
class Trie {
    Set<String> hs;
    public Trie() {
        hs = new HashSet<>();
    }
    
    public void insert(String word) {
        hs.add(word);
    }
    
    public boolean search(String word) {
        return hs.contains(word);
    }
    
    public boolean startsWith(String prefix) {
        for(String words:hs){
            if(words.startsWith(prefix)) return true;
        }
        return false;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
```
```java
Set<String> hs = new HashSet<>();
```

👉 Stores full words directly

---

## 🔹 insert

```java
hs.add(word);
```

👉 Just store word

---

## 🔹 search

```java
return hs.contains(word);
```

👉 Direct lookup

---

## 🔹 startsWith

```java
for(String words:hs){
    if(words.startsWith(prefix)) return true;
}
```

👉 Check every word

---

# 🔥 BIG DIFFERENCE

| Feature    | Trie Code              | HashSet Code |
| ---------- | ---------------------- | ------------ |
| Storage    | character by character | full words   |
| Structure  | tree                   | flat         |
| startsWith | fast ✅                 | slow ❌       |
| Idea       | path-based             | scan-based   |

---

# 🔥 Simple Analogy

### Trie:

👉 Like navigating roads:

* c → a → t

### HashSet:

👉 Like checking names in a list:

* check cat
* check car
* check dog

---

# 🔥 Why Trie is better?

### startsWith("ca")

👉 Trie:

* just follow path → O(L)

👉 HashSet:

* check all words → O(N * L)

---

# 🔥 Final Understanding

👉 **Trie stores characters as paths**
👉 **HashSet stores complete words**

---

# 🧠 One-line memory

👉
**Trie = path traversal**
**HashSet = list scanning**

---
</p>
