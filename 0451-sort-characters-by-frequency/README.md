<h2><a href="https://leetcode.com/problems/sort-characters-by-frequency">451. Sort Characters By Frequency</a></h2><h3>Medium</h3><hr><p>Given a string <code>s</code>, sort it in <strong>decreasing order</strong> based on the <strong>frequency</strong> of the characters. The <strong>frequency</strong> of a character is the number of times it appears in the string.</p>

<p>Return <em>the sorted string</em>. If there are multiple answers, return <em>any of them</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;tree&quot;
<strong>Output:</strong> &quot;eert&quot;
<strong>Explanation:</strong> &#39;e&#39; appears twice while &#39;r&#39; and &#39;t&#39; both appear once.
So &#39;e&#39; must appear before both &#39;r&#39; and &#39;t&#39;. Therefore &quot;eetr&quot; is also a valid answer.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;cccaaa&quot;
<strong>Output:</strong> &quot;aaaccc&quot;
<strong>Explanation:</strong> Both &#39;c&#39; and &#39;a&#39; appear three times, so both &quot;cccaaa&quot; and &quot;aaaccc&quot; are valid answers.
Note that &quot;cacaca&quot; is incorrect, as the same characters must be together.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;Aabb&quot;
<strong>Output:</strong> &quot;bbAa&quot;
<strong>Explanation:</strong> &quot;bbaA&quot; is also a valid answer, but &quot;Aabb&quot; is incorrect.
Note that &#39;A&#39; and &#39;a&#39; are treated as two different characters.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>s</code> consists of uppercase and lowercase English letters and digits.</li>
</ul>
<p>


# 🔥 LeetCode 451 – Sort Characters By Frequency<br>
<br>
## 🧠 Problem Statement<br>
<br>
Given a string `s`, sort it in decreasing order based on the frequency of characters.<br>
<br>
---<br>
<br>
# 🚀 Approach 1: Bucket Sort (Optimal – O(n))<br>
<br>
## 💡 Idea<br>
<br>
Group characters based on frequency and process highest frequency first.<br>
<br>
## 🔧 Steps<br>
<br>
1. Count frequency using HashMap.<br>
2. Create bucket array where index = frequency.<br>
3. Store characters in bucket[freq].<br>
4. Traverse bucket from high → low.<br>
5. Append characters freq times.<br>
<br>
## ⏱ Complexity<br>
<br>
Time Complexity: O(n)<br>
Space Complexity: O(n)<br>
<br>
## ✅ Code<br>
<br>
```java
class Solution {
    public String frequencySort(String s) {
        HashMap<Character,Integer> hm = new HashMap<>();

        for(char ch:s.toCharArray()){
            hm.put(ch,hm.getOrDefault(ch,0)+1);
        }

        List<Character>[] bucket = new ArrayList[s.length()+1];

        for(char ch:hm.keySet()){
            int freq = hm.get(ch);
            if(bucket[freq]==null){
                bucket[freq]=new ArrayList<>();
            }
            bucket[freq].add(ch);
        }

        StringBuilder sb = new StringBuilder();

        for(int i=bucket.length-1; i>=0; i--){
            if(bucket[i]!=null){
                for(char ch:bucket[i]){
                    int freq = i;
                    while(freq-- > 0){
                        sb.append(ch);
                    }
                }
            }
        }

        return sb.toString();
    }
}
````

<br>
---<br>
<br>
# 🚀 Approach 2: Heap + Pair Class<br>
<br>
## 💡 Idea<br>
<br>
Use Max Heap to always pick character with highest frequency.<br>
<br>
## 🔧 Steps<br>
<br>
1. Count frequency using HashMap.<br>
2. Store (char, freq) in max heap.<br>
3. Poll max element.<br>
4. Append character freq times.<br>
<br>
## ⏱ Complexity<br>
<br>
Time Complexity: O(n log k)<br>
Space Complexity: O(n)<br>
<br>
## ✅ Code<br>
<br>
```java
class Solution {
    public String frequencySort(String s) {
        HashMap<Character,Integer> hm = new HashMap<>();

```
    for(char ch:s.toCharArray()){
        hm.put(ch,hm.getOrDefault(ch,0)+1);
    }

    PriorityQueue<Pair> pq = new PriorityQueue<>(
        (a,b)->b.v-a.v
    );

    for(char ch:hm.keySet()){
        pq.add(new Pair(ch,hm.get(ch)));
    }

    StringBuilder sb = new StringBuilder();

    while(!pq.isEmpty()){
        Pair p = pq.poll();

        int freq = p.v;
        while(freq-- > 0){
            sb.append(p.ch);
        }
    }

    return sb.toString();
}

class Pair{
    char ch;
    int v;

    Pair(char ch,int v){
        this.ch=ch;
        this.v=v;
    }
}
```

}

````
<br>
---<br>
<br>
# 🚀 Approach 3: Heap Without Pair (Cleaner)<br>
<br>
## 💡 Idea<br>
<br>
Use heap of characters and compare using HashMap frequency.<br>
<br>
## 🔧 Steps<br>
<br>
1. Count frequency using HashMap.<br>
2. Create max heap of characters.<br>
3. Sort using hm.get().<br>
4. Append each character freq times.<br>
<br>
## ⏱ Complexity<br>
<br>
Time Complexity: O(n log k)<br>
Space Complexity: O(n)<br>
<br>
## ✅ Code<br>
<br>
```java
class Solution {
    public String frequencySort(String s) {
        HashMap<Character,Integer> hm = new HashMap<>();

        for(char ch:s.toCharArray()){
            hm.put(ch,hm.getOrDefault(ch,0)+1);
        }

        PriorityQueue<Character> pq = new PriorityQueue<>(
            (a,b)->hm.get(b)-hm.get(a)
        );

        pq.addAll(hm.keySet());

        StringBuilder sb = new StringBuilder();

        while(!pq.isEmpty()){
            char ch = pq.poll();

            int freq = hm.get(ch);
            while(freq-- > 0){
                sb.append(ch);
            }
        }

        return sb.toString();
    }
}
````

<br>
---<br>
<br>
# 🧠 Final Comparison<br>
<br>
Bucket Sort → O(n) ✅ fastest<br>
Heap + Pair → O(n log k)<br>
Heap (no Pair) → O(n log k) but cleaner<br>
<br>
---<br>
<br>
# 💯 Interview Tip<br>
<br>
If asked:<br>
👉 “Can you optimize?”<br>
<br>
Answer:<br>
👉 “Yes, using Bucket Sort we can reduce time complexity from O(n log k) to O(n)”<br>
<br>
---<br>
<br>
# 🚀 Conclusion<br>
<br>
Use Bucket Sort when frequency matters.<br>
Use Heap when top-k elements are needed.<br>
Always think about reducing log factor in interviews.<br>
```

Here is a **clean, simple explanation of all 3 approaches**
## 🔥 1. Bucket Sort Approach (BEST – O(n))

**Idea:** Group characters based on frequency and build result from highest frequency.<br>

**Steps:**<br>

1. Count frequency using HashMap.<br>
2. Create bucket array where index = frequency.<br>
3. Store characters in corresponding bucket.<br>
4. Traverse buckets from high → low and build string.<br>

**Why it works fast:**<br>
Each character is processed limited times → overall linear work.<br>

**Time Complexity:** O(n)<br>
**Space Complexity:** O(n)<br>

---

## 🔥 2. Heap + Pair Class

**Idea:** Store (character, frequency) in max heap and always pick highest frequency first.<br>

**Steps:**<br>

1. Count frequency using HashMap.<br>
2. Create max heap based on frequency.<br>
3. Store Pair(ch, freq) in heap.<br>
4. Remove max element and append character freq times.<br>

**Why slower than bucket:**<br>
Heap operations take log(k) time.<br>

**Time Complexity:** O(n log k)<br>
(k = unique characters)<br>
**Space Complexity:** O(n)<br>

---

## 🔥 3. Heap Without Pair (Optimized Heap)

**Idea:** Store only characters in heap and use HashMap to compare frequency.<br>

**Steps:**<br>

1. Count frequency using HashMap.<br>
2. Create max heap of characters using comparator hm.get().<br>
3. Add all characters to heap.<br>
4. Poll each character and append freq times.<br>

**Why better than Pair version:**<br>
No custom class → cleaner code.<br>

**Time Complexity:** O(n log k)<br>
**Space Complexity:** O(n)<br>

---

## 🧠 Final Comparison

Bucket Sort → O(n) ✅ fastest<br>
Heap (Pair) → O(n log k)<br>
Heap (No Pair) → O(n log k) but cleaner<br>

---

## 💯 Interview Tip

If asked to optimize:<br>
👉 Say: “We can use Bucket Sort to reduce from O(n log k) to O(n)”<br>

</p>
