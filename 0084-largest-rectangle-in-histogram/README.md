<h2><a href="https://leetcode.com/problems/largest-rectangle-in-histogram">84. Largest Rectangle in Histogram</a></h2><h3>Hard</h3><hr><p>Given an array of integers <code>heights</code> representing the histogram&#39;s bar height where the width of each bar is <code>1</code>, return <em>the area of the largest rectangle in the histogram</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/04/histogram.jpg" style="width: 522px; height: 242px;" />
<pre>
<strong>Input:</strong> heights = [2,1,5,6,2,3]
<strong>Output:</strong> 10
<strong>Explanation:</strong> The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/04/histogram-1.jpg" style="width: 202px; height: 362px;" />
<pre>
<strong>Input:</strong> heights = [2,4]
<strong>Output:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= heights.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= heights[i] &lt;= 10<sup>4</sup></code></li>
</ul>
<p>
	
# LeetCode 84

Given an array:

```text id="gw19g7"
heights[i]
```

Each value represents histogram bar height.

We must find:

```text id="8mif7x"
largest rectangle area
```

that can be formed.

---

# Example

```text id="qu5gfd"
heights = [2,1,5,6,2,3]
```

Answer:

```text id="ydvkv8"
10
```

Because:

```text id="c7m2h2"
5 × 2 = 10
```

using bars `[5,6]`.

---

# 1. Brute Force Solution

---

# Intuition

For every bar:

* expand left until smaller element comes
* expand right until smaller element comes

Then:

```text id="94a1sn"
width = left + right + 1
```

Area:

```text id="b8fcdl"
height × width
```

Take maximum.

---

# Brute Force Code (Java)

```java id="o4i6w1"
class Solution {

    public int rightL(int[] heights, int i){

        int n = heights.length;

        int count = 0;

        for(int j = i + 1; j < n; j++){

            if(heights[j] >= heights[i]){
                count++;
            }
            else{
                break;
            }
        }

        return count;
    }

    public int leftL(int[] heights, int i){

        int count = 0;

        for(int j = i - 1; j >= 0; j--){

            if(heights[j] >= heights[i]){
                count++;
            }
            else{
                break;
            }
        }

        return count;
    }

    public int largestRectangleArea(int[] heights) {

        int n = heights.length;

        int max = 0;

        for(int i = 0; i < n; i++){

            int height = heights[i];

            int width =
                    leftL(heights, i)
                  + rightL(heights, i)
                  + 1;

            int area = height * width;

            max = Math.max(max, area);
        }

        return max;
    }
}
```

---

# Brute Force Dry Run

Input:

```text id="f6qk1u"
[2,1,5,6,2,3]
```

---

## For 5

Left:

```text id="ifv6h6"
1 is smaller
```

So left count = 0

Right:

```text id="8sd08z"
6 >= 5
```

Right count = 1

Width:

```text id="a53dnx"
0 + 1 + 1 = 2
```

Area:

```text id="cc3g3w"
5 × 2 = 10
```

---

# Brute Force Time Complexity

For every index:

* left scan → O(n)
* right scan → O(n)

Total:

```text id="w9cn5k"
O(n²)
```

---

# Brute Force Space Complexity

```text id="0b6m4z"
O(1)
```

---

# Why TLE Happens

Worst case:

```text id="v79c5p"
[5,5,5,5,5,5,5...]
```

Every loop scans almost whole array.

So operations become:

```text id="p4dks0"
1 + 2 + 3 + ... + n
```

which is:

```text id="2fdd9y"
O(n²)
```

Too slow.

---

# 2. Optimal Solution — Monotonic Stack

---

# Core Idea

For every element find:

1. Previous Smaller Element (PSE)
2. Next Smaller Element (NSE)

Then:

```text id="z7wyoc"
width = NSE - PSE - 1
```

Area:

```text id="mql3ce"
height × width
```

---

# Why Stack?

Stack helps efficiently find:

* nearest smaller on left
* nearest smaller on right

in one pass.

---

# Optimal Code (Java)

```java id="q21gwv"
import java.util.*;

class Solution {

    public int largestRectangleArea(int[] heights) {

        int n = heights.length;

        Stack<Integer> st = new Stack<>();

        int maxArea = 0;

        for(int i = 0; i <= n; i++){

            while(!st.isEmpty() &&
                 (i == n || heights[st.peek()] >= heights[i])){

                int height = heights[st.pop()];

                int nse = i;

                int pse;

                if(st.isEmpty()){
                    pse = -1;
                }
                else{
                    pse = st.peek();
                }

                int width = nse - pse - 1;

                int area = height * width;

                maxArea = Math.max(maxArea, area);
            }

            st.push(i);
        }

        return maxArea;
    }
}
```

---

# Optimal Dry Run

Input:

```text id="vgm0vc"
[2,1,5,6,2,3]
```

---

## Step 1

Push 2.

```text id="scvkp6"
stack = [0]
```

---

## Step 2

1 comes.

```text id="cm87dq"
1 < 2
```

So 2 cannot expand further.

Pop 2.

Area:

```text id="v3wn6m"
2 × 1 = 2
```

Push 1.

---

## Step 3

Push 5.

Push 6.

---

## Step 4

2 comes.

Now:

```text id="k5miyv"
2 < 6
```

Pop 6.

Area:

```text id="9e4m3x"
6 × 1 = 6
```

Again:

```text id="v9x1v3"
2 < 5
```

Pop 5.

Width becomes 2.

Area:

```text id="86mbp1"
5 × 2 = 10
```

Maximum = 10.

---

# Important Understanding

Stack stores:

```text id="3hgvmy"
indices of increasing heights
```

Example:

```text id="rfspw8"
[1,5,6]
```

When smaller element appears:

* larger bars stop expanding
* calculate their areas immediately

---

# Why Width Formula Works

```text id="sld33d"
width = nse - pse - 1
```

Because:

* `pse` = smaller boundary on left
* `nse` = smaller boundary on right

Rectangle exists between them.

---

# Optimal Time Complexity

```text id="cgx3y0"
O(n)
```

Each element:

* pushed once
* popped once

---

# Optimal Space Complexity

```text id="jszy0l"
O(n)
```

Stack.

---

# Comparison Table

| Feature          | Brute Force       | Optimal Stack |
| ---------------- | ----------------- | ------------- |
| Idea             | Expand left/right | Use PSE + NSE |
| Time Complexity  | O(n²)             | O(n)          |
| Space Complexity | O(1)              | O(n)          |
| Passes           | Many rescans      | Single pass   |
| TLE Risk         | Yes               | No            |

---

# Most Important Interview Intuition

You can explain:

> “For every bar, I want the maximum width where this bar remains minimum height. Smaller elements define the boundaries. Monotonic stack helps find those boundaries efficiently.”

That is the key concept interviewer expects.

</p>
