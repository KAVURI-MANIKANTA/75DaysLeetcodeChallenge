<h2><a href="https://leetcode.com/problems/course-schedule">207. Course Schedule</a></h2><h3>Medium</h3><hr><p>There are a total of <code>numCourses</code> courses you have to take, labeled from <code>0</code> to <code>numCourses - 1</code>. You are given an array <code>prerequisites</code> where <code>prerequisites[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that you <strong>must</strong> take course <code>b<sub>i</sub></code> first if you want to take course <code>a<sub>i</sub></code>.</p>

<ul>
	<li>For example, the pair <code>[0, 1]</code>, indicates that to take course <code>0</code> you have to first take course <code>1</code>.</li>
</ul>

<p>Return <code>true</code> if you can finish all courses. Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> numCourses = 2, prerequisites = [[1,0]]
<strong>Output:</strong> true
<strong>Explanation:</strong> There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> numCourses = 2, prerequisites = [[1,0],[0,1]]
<strong>Output:</strong> false
<strong>Explanation:</strong> There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= numCourses &lt;= 2000</code></li>
	<li><code>0 &lt;= prerequisites.length &lt;= 5000</code></li>
	<li><code>prerequisites[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; numCourses</code></li>
	<li>All the pairs prerequisites[i] are <strong>unique</strong>.</li>
</ul>
<p>
You solved it using **Kahn's Algorithm (BFS Topological Sort)**.

The main idea of LeetCode 207 (**Course Schedule**) is:

> Can we complete all courses without getting stuck in a cycle?

If a cycle exists:

```text
0 -> 1 -> 2 -> 0
```

You can never start any course in the cycle because each one depends on another.

---

# Step 1: Understand prerequisites

Given:

```java
prerequisites = [[1,0]]
```

Meaning:

```text
To take course 1,
you must finish course 0 first.
```

So edge:

```text
0 -> 1
```

---

# Step 2: Build Graph

Your code:

```java
for(int[] e : prerequisites){
    adj.get(e[1]).add(e[0]);
}
```

Creates:

```text
e[1] -> e[0]
```

Example:

```java
[[1,0],[2,0],[3,1],[3,2]]
```

Graph:

```text
0 → 1
0 → 2
1 → 3
2 → 3
```

Adjacency List:

```text
0 : [1,2]
1 : [3]
2 : [3]
3 : []
```

---

# Step 3: Calculate Indegree

Indegree means:

```text
How many prerequisites does a course have?
```

Your code:

```java
for(int i=0;i<numCourses;i++){
    for(int neighbor : adj.get(i)){
        inDig[neighbor]++;
    }
}
```

For graph:

```text
0 → 1
0 → 2
1 → 3
2 → 3
```

Indegree:

```text
0 = 0
1 = 1
2 = 1
3 = 2
```

Array:

```text
[0,1,1,2]
```

---

# Step 4: Put all indegree 0 nodes into Queue

```java
if(inDig[i]==0){
    q.add(i);
}
```

Queue:

```text
[0]
```

Why?

Because course 0 has no prerequisites.

It can be taken immediately.

---

# Step 5: BFS

Your loop:

```java
while(!q.isEmpty())
```

Take:

```java
curr = q.poll();
```

---

### Iteration 1

Queue:

```text
[0]
```

Take:

```text
0
```

Result:

```text
[0]
```

Neighbors:

```text
1,2
```

Reduce indegree:

```text
1 : 1→0
2 : 1→0
```

Add to queue:

```text
[1,2]
```

---

### Iteration 2

Take:

```text
1
```

Result:

```text
[0,1]
```

Neighbor:

```text
3
```

Reduce indegree:

```text
3 : 2→1
```

Queue:

```text
[2]
```

---

### Iteration 3

Take:

```text
2
```

Result:

```text
[0,1,2]
```

Neighbor:

```text
3
```

Reduce indegree:

```text
3 : 1→0
```

Add:

```text
[3]
```

---

### Iteration 4

Take:

```text
3
```

Result:

```text
[0,1,2,3]
```

Queue empty.

---

Result size:

```text
4
```

Number of courses:

```text
4
```

Therefore:

```java
return true;
```

All courses can be completed.

---

# What if there is a cycle?

Example:

```java
[[1,0],[0,1]]
```

Graph:

```text
0 → 1
1 → 0
```

Indegree:

```text
[1,1]
```

Queue:

```text
[]
```

No node has indegree 0.

BFS never starts.

Result:

```text
[]
```

Size:

```text
0
```

numCourses:

```text
2
```

So:

```java
return false;
```

Because there is a cycle.

---

# The Key Observation

Kahn's Algorithm removes nodes with indegree 0.

If a cycle exists:

```text
0 → 1 → 2 → 0
```

every node always has indegree ≥ 1.

Therefore:

```text
No indegree 0 node
→ Queue becomes empty
→ Some courses remain unprocessed
→ Cycle exists
```

---

# One Bug in Your Code

You wrote:

```java
for(int i=0; i<numCourses; i++){
    for(int[] e:prerequisites){
        adj.get(e[1]).add(e[0]);
    }
}
```

The outer loop is unnecessary.

It adds the same edges repeatedly.

Correct:

```java
for(int[] e : prerequisites){
    adj.get(e[1]).add(e[0]);
}
```

---

# Clean Version

```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] e : prerequisites) {
            adj.get(e[1]).add(e[0]);
        }

        int[] indegree = new int[numCourses];

        for(int i = 0; i < numCourses; i++) {
            for(int neighbor : adj.get(i)) {
                indegree[neighbor]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i = 0; i < numCourses; i++) {
            if(indegree[i] == 0) {
                q.add(i);
            }
        }

        int count = 0;

        while(!q.isEmpty()) {
            int curr = q.poll();
            count++;

            for(int neighbor : adj.get(curr)) {
                indegree[neighbor]--;

                if(indegree[neighbor] == 0) {
                    q.add(neighbor);
                }
            }
        }

        return count == numCourses;
    }
}
```

### Time Complexity

* Build graph: **O(E)**
* Calculate indegree: **O(E)**
* BFS: **O(V + E)**

Overall:

```text
O(V + E)
```

### Space Complexity

```text
Adjacency List : O(V + E)
Indegree Array : O(V)
Queue          : O(V)
```

Overall:

```text
O(V + E)
```

### Interview One-Liner

> LeetCode 207 is a cycle-detection problem in a directed graph. Using Kahn's Algorithm, if we can process all courses through topological sorting (`count == numCourses`), then no cycle exists and all courses can be finished; otherwise a cycle is present.
</p>
