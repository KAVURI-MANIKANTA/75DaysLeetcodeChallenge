<h2><a href="https://leetcode.com/problems/next-greater-element-i">496. Next Greater Element I</a></h2><h3>Easy</h3><hr><p>The <strong>next greater element</strong> of some element <code>x</code> in an array is the <strong>first greater</strong> element that is <strong>to the right</strong> of <code>x</code> in the same array.</p>

<p>You are given two <strong>distinct 0-indexed</strong> integer arrays <code>nums1</code> and <code>nums2</code>, where <code>nums1</code> is a subset of <code>nums2</code>.</p>

<p>For each <code>0 &lt;= i &lt; nums1.length</code>, find the index <code>j</code> such that <code>nums1[i] == nums2[j]</code> and determine the <strong>next greater element</strong> of <code>nums2[j]</code> in <code>nums2</code>. If there is no next greater element, then the answer for this query is <code>-1</code>.</p>

<p>Return <em>an array </em><code>ans</code><em> of length </em><code>nums1.length</code><em> such that </em><code>ans[i]</code><em> is the <strong>next greater element</strong> as described above.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [4,1,2], nums2 = [1,3,4,2]
<strong>Output:</strong> [-1,3,-1]
<strong>Explanation:</strong> The next greater element for each value of nums1 is as follows:
- 4 is underlined in nums2 = [1,3,<u>4</u>,2]. There is no next greater element, so the answer is -1.
- 1 is underlined in nums2 = [<u>1</u>,3,4,2]. The next greater element is 3.
- 2 is underlined in nums2 = [1,3,4,<u>2</u>]. There is no next greater element, so the answer is -1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [2,4], nums2 = [1,2,3,4]
<strong>Output:</strong> [3,-1]
<strong>Explanation:</strong> The next greater element for each value of nums1 is as follows:
- 2 is underlined in nums2 = [1,<u>2</u>,3,4]. The next greater element is 3.
- 4 is underlined in nums2 = [1,2,3,<u>4</u>]. There is no next greater element, so the answer is -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length &lt;= nums2.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 10<sup>4</sup></code></li>
	<li>All integers in <code>nums1</code> and <code>nums2</code> are <strong>unique</strong>.</li>
	<li>All the integers of <code>nums1</code> also appear in <code>nums2</code>.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Could you find an <code>O(nums1.length + nums2.length)</code> solution?
<p>
	
# LeetCode 496 — Next Greater Element I

## Problem

You are given:

```java id="n63zqq"
nums1
nums2
```

For every element in `nums1`, find the **first greater element on the right side** in `nums2`.

If no greater element exists, return `-1`.

---

# Example

```java id="sy0bfx"
nums1 = [4,1,2]
nums2 = [1,3,4,2]
```

Output:

```java id="j1f3na"
[-1,3,-1]
```

Explanation:

| Element | Next Greater |
| ------- | ------------ |
| 4       | -1           |
| 1       | 3            |
| 2       | -1           |

---

# 1. Brute Force Solution

## Idea

For every element in `nums1`:

1. Find it in `nums2`
2. Move right side
3. Find first greater element

---

# Brute Force Code

```java id="odr37w"
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        int n1 = nums1.length;
        int n2 = nums2.length;

        int[] result = new int[n1];

        for(int i = 0; i < n1; i++) {

            int target = nums1[i];
            int nextGreater = -1;

            // Find target in nums2
            for(int j = 0; j < n2; j++) {

                if(nums2[j] == target) {

                    // Search right side
                    for(int k = j + 1; k < n2; k++) {

                        if(nums2[k] > target) {
                            nextGreater = nums2[k];
                            break;
                        }
                    }

                    break;
                }
            }

            result[i] = nextGreater;
        }

        return result;
    }
}
```

---

# Brute Force Dry Run

```java id="xjwn5k"
nums1 = [4,1,2]
nums2 = [1,3,4,2]
```

---

## For 4

Find 4 in nums2:

```text id="vt1zmg"
[1,3,4,2]
     ^
```

Right side:

```text id="2"
```

No greater element.

Answer:

```text id="5wx99t"
-1
```

---

## For 1

Find 1:

```text id="ag4yzx"
[1,3,4,2]
 ^
```

Right side:

```text id="9wxclc"
3 > 1
```

Answer:

```text id="9tjlwm"
3
```

---

## For 2

No greater element.

Answer:

```text id="sq34ho"
-1
```

---

# Time Complexity

## TC

Three loops:

```text id="ihqdvy"
O(n1 * n2)
```

Worst case can behave like:

```text id="c9ndrz"
O(n1 * n2)
```

If both arrays nearly same size:

```text id="0d2p41"
O(n²)
```

---

## SC

Only result array used:

```text id="4fej03"
O(1)
```

(ignoring output array)

---

# 2. Optimal Solution (Stack + HashMap)

---

# Main Idea

Instead of searching again and again:

We preprocess `nums2` once.

We store:

```text id="h5fy3p"
number -> next greater element
```

Example:

```text id="4n7mwv"
1 -> 3
3 -> 4
4 -> -1
2 -> -1
```

Then directly answer for nums1 using map.

---

# Important Concept = Monotonic Stack

We use a stack that stores elements in decreasing order.

When a larger element comes:

```text id="wftiwv"
current > stack top
```

then current element becomes next greater for stack top.

---

# Optimal Code

```java id="p7cvr0"
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        int n1 = nums1.length;

        Stack<Integer> st = new Stack<>();

        // Stores:
        // element -> next greater element
        HashMap<Integer, Integer> hm = new HashMap<>();

        // Process nums2
        for(int num : nums2) {

            // Current number is greater
            // than stack top
            while(!st.isEmpty() && st.peek() < num) {

                int smaller = st.pop();

                hm.put(smaller, num);
            }

            st.push(num);
        }

        // Remaining elements have no greater element
        while(!st.isEmpty()) {
            hm.put(st.pop(), -1);
        }

        // Build answer
        int[] result = new int[n1];

        for(int i = 0; i < n1; i++) {
            result[i] = hm.get(nums1[i]);
        }

        return result;
    }
}
```

---

# Optimal Solution Dry Run

```java id="3avz4j"
nums2 = [1,3,4,2]
```

---

# Step 1

Current:

```text id="gqzc5t"
1
```

Stack empty.

Push 1.

```text id="g1l9lb"
stack = [1]
```

---

# Step 2

Current:

```text id="vr3pm3"
3
```

Check:

```text id="e7xy96"
1 < 3
```

So:

```text id="0v4i0e"
next greater of 1 = 3
```

Map:

```text id="uhuxg3"
{1=3}
```

Pop 1.

Push 3.

```text id="n11shw"
stack = [3]
```

---

# Step 3

Current:

```text id="mh9s2k"
4
```

Check:

```text id="zfwc0g"
3 < 4
```

So:

```text id="v27lpa"
next greater of 3 = 4
```

Map:

```text id="j6d9jg"
{1=3, 3=4}
```

Push 4.

```text id="jlwm9n"
stack = [4]
```

---

# Step 4

Current:

```text id="3snr1s"
2
```

Check:

```text id="g0v48d"
4 < 2 ? NO
```

Push 2.

```text id="2w43d9"
stack = [2,4]
```

---

# End of Loop

Remaining stack:

```text id="47rp2v"
[2,4]
```

No greater element exists.

So:

```text id="uywtcw"
2 -> -1
4 -> -1
```

Final map:

```text id="n8snr8"
{
1=3,
3=4,
2=-1,
4=-1
}
```

---

# Build Final Answer

```java id="m3k33d"
nums1 = [4,1,2]
```

Using map:

```text id="zj0io4"
4 -> -1
1 -> 3
2 -> -1
```

Result:

```text id="8x7czn"
[-1,3,-1]
```

---

# Why Stack Works

Stack stores elements waiting for their next greater element.

Whenever a bigger element comes:

```text id="plw0y8"
current > stack top
```

the current element resolves answers for smaller stack elements.

---

# Time Complexity

## TC

Every element:

* pushed once
* popped once

So:

```text id="nccuzl"
O(n)
```

More accurately:

```text id="vmg1xk"
O(n1 + n2)
```

---

## SC

HashMap + Stack:

```text id="vh4dms"
O(n2)
```

---

# Difference Between Both Approaches

| Feature    | Brute Force       | Optimal                |
| ---------- | ----------------- | ---------------------- |
| Method     | Search repeatedly | Preprocess using stack |
| TC         | O(n²)             | O(n)                   |
| SC         | O(1)              | O(n)                   |
| Efficient? | No                | Yes                    |

---

# Core Concept to Remember

## Monotonic Decreasing Stack

The stack stores decreasing elements.

When a larger element appears:

```text id="9l1gz2"
current > stack top
```

then:

```text id="0mf28y"
current becomes next greater element
```

This same concept is used in:

* Next Greater Element
* Stock Span
* Daily Temperatures
* Largest Rectangle Histogram
* Trapping Rain Water
* Monotonic Stack problems

</p>
