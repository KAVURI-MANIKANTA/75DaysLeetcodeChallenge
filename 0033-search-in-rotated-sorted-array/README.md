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
	<br>
🔹 Problem Idea (Simple)<br>
You are given a sorted array rotated at some pivot.<br>
Example:<br>
[4,5,6,7,0,1,2]<br>
👉 Originally sorted → [0,1,2,4,5,6,7]<br>
👉 Rotated → [4,5,6,7,0,1,2]<br>

You must find target index in O(log n).<br>
🧠 Core Logic (Most Important)<br>
At every step:<br>
One half is always sorted<br>
Check:<br>
If target lies in sorted half → go there<br>
Else → go to other half<br>
🔍 Step-by-Step Example<br>
Input:<br>
nums = [4,5,6,7,0,1,2], target = 0<br>
Iteration 1:<br>
mid = 7 → left sorted<br>
target not in [4..7]<br>
→ go right<br>
Iteration 2:<br>
mid = 1 → right sorted<br>
target in [0..2]<br>
→ go right<br>
Found index = 4 ✅<br>
⏱️ Time & Space Complexity<br>
🔹 Binary Search Approach<br>
Time Complexity: O(log n)<br>
Space Complexity: O(1)<br>

👉 Best possible solution ✔️<br>
🔹 Brute Force (Your second code)<br>
for(int i=0; i<=n; i++){<br>
    if(nums[i]==target) return i;<br>
}<br>
Time Complexity: O(n)<br>
Space Complexity: O(1)<br>

👉 Works but NOT efficient ❌<br>

🔥 Why Binary Search Works Here?<br>
Because:<br>
Even though rotated → still partially sorted<br>
One half is always sorted → we exploit that<br>
💡 Other Ways to Solve<br>
1️⃣ Find Pivot + Binary Search<br>
Steps:<br>
Find rotation index (smallest element)<br>
Apply binary search in correct half<br>
👉 Time: O(log n)<br>
👉 Slightly more complex<br>

2️⃣ Modified Binary Search (Your approach) ✅ BEST<br>
👉 No need to find pivot separately<br>
👉 Clean + fast + interview favorite<br>

🏆 Final Verdict<br>
| Approach    | Time     | Space | Difficulty | Recommended |<br>
| ----------- | -------- | ----- | ---------- | ----------- |<br>
| Brute Force | O(n)     | O(1)  | Easy       | ❌ No       |<br>
| Pivot + BS  | O(log n) | O(1)  | Medium     | 👍          |<br>
| Modified BS | O(log n) | O(1)  | Medium     | ⭐ BEST     |<br>


🚀 Key Interview Tip<br>
👉 Always remember this line:<br>
“At least one half of rotated sorted array is always sorted”</p><br>
