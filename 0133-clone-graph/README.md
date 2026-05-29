<h2><a href="https://leetcode.com/problems/clone-graph">133. Clone Graph</a></h2><h3>Medium</h3><hr><p>Given a reference of a node in a <strong><a href="https://en.wikipedia.org/wiki/Connectivity_(graph_theory)#Connected_graph" target="_blank">connected</a></strong> undirected graph.</p>

<p>Return a <a href="https://en.wikipedia.org/wiki/Object_copying#Deep_copy" target="_blank"><strong>deep copy</strong></a> (clone) of the graph.</p>

<p>Each node in the graph contains a value (<code>int</code>) and a list (<code>List[Node]</code>) of its neighbors.</p>

<pre>
class Node {
    public int val;
    public List&lt;Node&gt; neighbors;
}
</pre>

<p>&nbsp;</p>

<p><strong>Test case format:</strong></p>

<p>For simplicity, each node&#39;s value is the same as the node&#39;s index (1-indexed). For example, the first node with <code>val == 1</code>, the second node with <code>val == 2</code>, and so on. The graph is represented in the test case using an adjacency list.</p>

<p><b>An adjacency list</b> is a collection of unordered <b>lists</b> used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.</p>

<p>The given node will always be the first node with <code>val = 1</code>. You must return the <strong>copy of the given node</strong> as a reference to the cloned graph.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2019/11/04/133_clone_graph_question.png" style="width: 454px; height: 500px;" />
<pre>
<strong>Input:</strong> adjList = [[2,4],[1,3],[2,4],[1,3]]
<strong>Output:</strong> [[2,4],[1,3],[2,4],[1,3]]
<strong>Explanation:</strong> There are 4 nodes in the graph.
1st node (val = 1)&#39;s neighbors are 2nd node (val = 2) and 4th node (val = 4).
2nd node (val = 2)&#39;s neighbors are 1st node (val = 1) and 3rd node (val = 3).
3rd node (val = 3)&#39;s neighbors are 2nd node (val = 2) and 4th node (val = 4).
4th node (val = 4)&#39;s neighbors are 1st node (val = 1) and 3rd node (val = 3).
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/01/07/graph.png" style="width: 163px; height: 148px;" />
<pre>
<strong>Input:</strong> adjList = [[]]
<strong>Output:</strong> [[]]
<strong>Explanation:</strong> Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> adjList = []
<strong>Output:</strong> []
<strong>Explanation:</strong> This an empty graph, it does not have any nodes.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the graph is in the range <code>[0, 100]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 100</code></li>
	<li><code>Node.val</code> is unique for each node.</li>
	<li>There are no repeated edges and no self-loops in the graph.</li>
	<li>The Graph is connected and all nodes can be visited starting from the given node.</li>
</ul>
<p>
	
# LeetCode 133 — Clone Graph

## Problem Statement

You are given a reference to a node in a **connected undirected graph**.

Your task is to create a **deep copy** (clone) of the graph.

Each node contains:

```java
class Node {
    public int val;
    public List<Node> neighbors;
}
```

---

# What is a Deep Copy?

A deep copy means:

* Create completely new nodes
* Copy all connections
* Do NOT use original nodes in cloned graph

---

# Example

Original Graph:

```text
1 -- 2
|    |
4 -- 3
```

If we clone it:

```text
1' -- 2'
|      |
4' -- 3'
```

All nodes are new objects.

---

# Important Observation

Graphs can contain:

* cycles
* repeated neighbors

So if we keep cloning without tracking visited nodes:

```text
1 → 2 → 1 → 2 → ...
```

infinite recursion happens.

---

# Main Idea

We use:

```java
HashMap<OriginalNode, ClonedNode>
```

This helps us:

* avoid infinite loops
* reuse already cloned nodes

---

# Approach (DFS)

For every node:

1. If node already cloned:
   return cloned node

2. Create clone node

3. Store in hashmap

4. Clone all neighbors recursively

---

# Visualization

Original:

```text
1 -> 2
↑    ↓
4 <- 3
```

Suppose we start at node `1`.

---

## Step 1

Clone node `1`

```text
map:
1 -> 1'
```

---

## Step 2

Go to neighbor `2`

Clone node `2`

```text
map:
1 -> 1'
2 -> 2'
```

Connect:

```text
1' -> 2'
```

---

## Step 3

Go to neighbor `3`

Clone `3`

```text
map:
1 -> 1'
2 -> 2'
3 -> 3'
```

---

## Step 4

Go to neighbor `4`

Clone `4`

```text
map:
1 -> 1'
2 -> 2'
3 -> 3'
4 -> 4'
```

---

## Step 5

Now `4` points back to `1`

But `1` already exists in hashmap.

So reuse cloned `1'`.

No infinite loop.

---

# DFS Solution in Java

```java
class Solution {

    private HashMap<Node, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {

        // Empty graph
        if(node == null) {
            return null;
        }

        // Already cloned
        if(map.containsKey(node)) {
            return map.get(node);
        }

        // Create clone
        Node clone = new Node(node.val);

        // Store in hashmap
        map.put(node, clone);

        // Clone neighbors
        for(Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }

        return clone;
    }
}
```

---

# Dry Run

Graph:

```text
1 -- 2
```

---

## cloneGraph(1)

Create:

```text
1'
```

Store:

```text
1 -> 1'
```

Now clone neighbor `2`

---

## cloneGraph(2)

Create:

```text
2'
```

Store:

```text
2 -> 2'
```

Neighbor of `2` is `1`

But `1` already cloned.

So return `1'`

Connect:

```text
2' -> 1'
```

Return `2'`

Now:

```text
1' -> 2'
```

Done.

---

# Time Complexity

## `O(V + E)`

Where:

* `V` = vertices
* `E` = edges

Because each node and edge visited once.

---

# Space Complexity

## `O(V)`

For:

* hashmap
* recursion stack

---

# Why HashMap is Necessary?

Without hashmap:

* cycles create infinite recursion
* duplicate nodes created

HashMap ensures:

```text
one original node -> one cloned node
```

---

# BFS Solution (Alternative)

We can also solve using BFS.

---

## BFS Idea

Use:

* Queue for traversal
* HashMap for cloned nodes

---

# BFS Code

```java
class Solution {

    public Node cloneGraph(Node node) {

        if(node == null) {
            return null;
        }

        HashMap<Node, Node> map = new HashMap<>();

        Queue<Node> q = new LinkedList<>();

        // Clone first node
        Node clone = new Node(node.val);

        map.put(node, clone);

        q.offer(node);

        while(!q.isEmpty()) {

            Node current = q.poll();

            for(Node neighbor : current.neighbors) {

                // If not cloned
                if(!map.containsKey(neighbor)) {

                    map.put(neighbor,
                            new Node(neighbor.val));

                    q.offer(neighbor);
                }

                // Connect cloned nodes
                map.get(current).neighbors.add(
                    map.get(neighbor)
                );
            }
        }

        return clone;
    }
}
```

---

# DFS vs BFS

| DFS                      | BFS                      |
| ------------------------ | ------------------------ |
| Uses recursion           | Uses queue               |
| Simpler code             | Iterative                |
| Uses call stack          | Uses explicit queue      |
| Easy for graph recursion | Good for level traversal |

Both are correct.

---

# Key Interview Points

## Why hashmap?

To:

* avoid revisiting nodes
* prevent infinite loops
* maintain mapping

---

## Why deep copy?

Because cloned graph must be independent.

Changing original graph should NOT affect clone.

---

# Important Line

```java
if(map.containsKey(node))
```

This is the core line that prevents cycles from causing infinite recursion.

</p>
