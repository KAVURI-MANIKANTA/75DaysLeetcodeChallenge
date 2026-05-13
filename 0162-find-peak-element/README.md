<h2><a href="https://leetcode.com/problems/find-peak-element">162. Find Peak Element</a></h2><h3>Medium</h3><hr><p>A peak element is an element that is strictly greater than its neighbors.</p>

<p>Given a <strong>0-indexed</strong> integer array <code>nums</code>, find a peak element, and return its index. If the array contains multiple peaks, return the index to <strong>any of the peaks</strong>.</p>

<p>You may imagine that <code>nums[-1] = nums[n] = -&infin;</code>. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.</p>

<p>You must write an algorithm that runs in <code>O(log n)</code> time.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 3 is a peak element and your function should return the index number 2.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,1,3,5,6,4]
<strong>Output:</strong> 5
<strong>Explanation:</strong> Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>nums[i] != nums[i + 1]</code> for all valid <code>i</code>.</li>
</ul>
<p>
	
# LeetCode 162 — Find Peak Element

A **peak element** is an element strictly greater than its neighbors.

Example:

```text
Input: [1,2,3,1]
Output: 2
```

Because `nums[2] = 3` is greater than `2` and `1`.

---

# 1) Brute Force Solution

## Idea

Check every element one by one and see whether it is a peak.

---

## Brute Force Code

```java id="s5g39i"
class Solution {
    public int findPeakElement(int[] nums) {

        int n = nums.length;

        // only one element
        if(n == 1) return 0;

        for(int i = 0; i < n; i++) {

            // first element
            if(i == 0) {
                if(nums[i] > nums[i + 1]) {
                    return i;
                }
            }

            // last element
            else if(i == n - 1) {
                if(nums[i] > nums[i - 1]) {
                    return i;
                }
            }

            // middle elements
            else {
                if(nums[i] > nums[i - 1] &&
                   nums[i] > nums[i + 1]) {
                    return i;
                }
            }
        }

        return -1;
    }
}
```

---

# Brute Force Explanation

Suppose:

```text
nums = [1,2,3,1]
```

### i = 0

Check:

```text
1 > 2 ? false
```

### i = 1

```text
2 > 1 && 2 > 3 ? false
```

### i = 2

```text
3 > 2 && 3 > 1 ? true
```

Return index `2`.

---

# Brute Force Complexity

| Complexity       | Value |
| ---------------- | ----- |
| Time Complexity  | O(n)  |
| Space Complexity | O(1)  |

---

# 2) Optimal Solution (Binary Search)

## Main Idea

If mid element is in increasing slope:

```text
1 2 3 4 5
```

Peak exists on right side.

If mid element is in decreasing slope:

```text
5 4 3 2 1
```

Peak exists on left side.

So we can use Binary Search.

---

# Optimal Code

```java id="gn2x9l"
class Solution {
    public int findPeakElement(int[] nums) {

        int n = nums.length;

        // single element
        if(n == 1) return 0;

        // first element peak
        if(nums[0] > nums[1]) return 0;

        // last element peak
        if(nums[n - 1] > nums[n - 2]) return n - 1;

        int l = 1;
        int r = n - 2;

        while(l <= r) {

            int mid = l + (r - l) / 2;

            // peak found
            if(nums[mid] > nums[mid - 1] &&
               nums[mid] > nums[mid + 1]) {

                return mid;
            }

            // increasing slope
            else if(nums[mid] > nums[mid - 1]) {
                l = mid + 1;
            }

            // decreasing slope
            else {
                r = mid - 1;
            }
        }

        return -1;
    }
}
```

---

# Optimal Solution Step-by-Step

## Example

```text
nums = [1,2,3,1]
```

### Initial

```text
l = 1
r = 2
```

### mid

```text
mid = 1
nums[mid] = 2
```

Check:

```text
2 > 1 true
2 > 3 false
```

Not peak.

Since:

```text
nums[mid] > nums[mid-1]
```

Array is increasing.

Move right:

```text
l = mid + 1
```

---

### Next

```text
mid = 2
nums[mid] = 3
```

Check:

```text
3 > 2 true
3 > 1 true
```

Peak found.

Return `2`.

---

# Why Binary Search Works

At least one peak always exists.

If:

* array rising → peak on right
* array falling → peak on left

So we can safely remove half of the array every time.

---

# Optimal Complexity

| Complexity       | Value    |
| ---------------- | -------- |
| Time Complexity  | O(log n) |
| Space Complexity | O(1)     |

---

# Difference Between Brute Force and Optimal

| Feature                    | Brute Force   | Optimal       |
| -------------------------- | ------------- | ------------- |
| Approach                   | Linear Search | Binary Search |
| Time                       | O(n)          | O(log n)      |
| Space                      | O(1)          | O(1)          |
| Efficient for large arrays | No            | Yes           |

</p>
