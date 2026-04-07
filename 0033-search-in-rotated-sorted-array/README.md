<h2><a href="https://leetcode.com/problems/search-in-rotated-sorted-array">33. Search in Rotated Sorted Array</a></h2><h3>Medium</h3><hr><p>There is an integer array <code>nums</code> sorted in ascending order (with <strong>distinct</strong> values).</p>

<p>Prior to being passed to your function, <code>nums</code> is <strong>possibly left rotated</strong> at an unknown index <code>k</code> (<code>1 &lt;= k &lt; nums.length</code>) such that the resulting array is <code>[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]</code> (<strong>0-indexed</strong>). For example, <code>[0,1,2,4,5,6,7]</code> might be left rotated by&nbsp;<code>3</code>&nbsp;indices and become <code>[4,5,6,7,0,1,2]</code>.</p>

<p>Given the array <code>nums</code> <strong>after</strong> the possible rotation and an integer <code>target</code>, return <em>the index of </em><code>target</code><em> if it is in </em><code>nums</code><em>, or </em><code>-1</code><em> if it is not in </em><code>nums</code>.</p>

<p>You must write an algorithm with <code>O(log n)</code> runtime complexity.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [4,5,6,7,0,1,2], target = 0
<strong>Output:</strong> 4
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [4,5,6,7,0,1,2], target = 3
<strong>Output:</strong> -1
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> nums = [1], target = 0
<strong>Output:</strong> -1
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5000</code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li>All values of <code>nums</code> are <strong>unique</strong>.</li>
	<li><code>nums</code> is an ascending array that is possibly rotated.</li>
	<li><code>-10<sup>4</sup> &lt;= target &lt;= 10<sup>4</sup></code></li>
</ul>
<p>
🔹 Problem Idea (Simple)
You are given a sorted array rotated at some pivot.
Example:
[4,5,6,7,0,1,2]
👉 Originally sorted → [0,1,2,4,5,6,7]
👉 Rotated → [4,5,6,7,0,1,2]

You must find target index in O(log n).
🧠 Core Logic (Most Important)
At every step:

One half is always sorted
Check:
If target lies in sorted half → go there
Else → go to other half
🔍 Step-by-Step Example
Input:
nums = [4,5,6,7,0,1,2], target = 0
Iteration 1:
mid = 7 → left sorted
target not in [4..7]
→ go right
Iteration 2:
mid = 1 → right sorted
target in [0..2]
→ go right
Found index = 4 ✅
⏱️ Time & Space Complexity
🔹 Binary Search Approach
Time Complexity: O(log n)
Space Complexity: O(1)

👉 Best possible solution ✔️
🔹 Brute Force (Your second code)
for(int i=0; i<=n; i++){
    if(nums[i]==target) return i;
}
Time Complexity: O(n)
Space Complexity: O(1)

👉 Works but NOT efficient ❌

🔥 Why Binary Search Works Here?
Because:
Even though rotated → still partially sorted
One half is always sorted → we exploit that
💡 Other Ways to Solve
1️⃣ Find Pivot + Binary Search
Steps:
Find rotation index (smallest element)
Apply binary search in correct half
👉 Time: O(log n)
👉 Slightly more complex

2️⃣ Modified Binary Search (Your approach) ✅ BEST
👉 No need to find pivot separately
👉 Clean + fast + interview favorite

🏆 Final Verdict
| Approach    | Time     | Space | Difficulty | Recommended |
| ----------- | -------- | ----- | ---------- | ----------- |
| Brute Force | O(n)     | O(1)  | Easy       | ❌ No       |
| Pivot + BS  | O(log n) | O(1)  | Medium     | 👍          |
| Modified BS | O(log n) | O(1)  | Medium     | ⭐ BEST     |


🚀 Key Interview Tip
👉 Always remember this line:
“At least one half of rotated sorted array is always sorted”</p>
