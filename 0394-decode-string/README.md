<h2><a href="https://leetcode.com/problems/decode-string">394. Decode String</a></h2><h3>Medium</h3><hr><p>Given an encoded string, return its decoded string.</p>

<p>The encoding rule is: <code>k[encoded_string]</code>, where the <code>encoded_string</code> inside the square brackets is being repeated exactly <code>k</code> times. Note that <code>k</code> is guaranteed to be a positive integer.</p>

<p>You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, <code>k</code>. For example, there will not be input like <code>3a</code> or <code>2[4]</code>.</p>

<p>The test cases are generated so that the length of the output will never exceed <code>10<sup>5</sup></code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;3[a]2[bc]&quot;
<strong>Output:</strong> &quot;aaabcbc&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;3[a2[c]]&quot;
<strong>Output:</strong> &quot;accaccacc&quot;
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;2[abc]3[cd]ef&quot;
<strong>Output:</strong> &quot;abcabccdcdcdef&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 30</code></li>
	<li><code>s</code> consists of lowercase English letters, digits, and square brackets <code>&#39;[]&#39;</code>.</li>
	<li><code>s</code> is guaranteed to be <strong>a valid</strong> input.</li>
	<li>All the integers in <code>s</code> are in the range <code>[1, 300]</code>.</li>
</ul>
<p>
	
## LeetCode 394. Decode String

A string is encoded like:

* `"3[a]"` → `"aaa"`
* `"3[a2[c]]"` → `"accaccacc"`

We need to decode the string.

---

# 1. Brute Force Approach

## Idea

We repeatedly find the **innermost bracket**:

Example:

```text
3[a2[c]]
```

First decode:

```text
2[c] -> cc
```

Now string becomes:

```text
3[acc]
```

Then:

```text
accaccacc
```

We keep doing this until no `]` exists.

---

# Brute Force Code (Java)

```java
class Solution {
    public String decodeString(String s) {

        while (s.contains("]")) {

            int close = s.indexOf(']');

            int open = close;

            // find matching '['
            while (s.charAt(open) != '[') {
                open--;
            }

            // extract number before '['
            int start = open - 1;

            while (start >= 0 && Character.isDigit(s.charAt(start))) {
                start--;
            }

            start++;

            int num = Integer.parseInt(s.substring(start, open));

            String str = s.substring(open + 1, close);

            // repeat string
            StringBuilder repeated = new StringBuilder();

            for (int i = 0; i < num; i++) {
                repeated.append(str);
            }

            // rebuild string
            s = s.substring(0, start) +
                    repeated.toString() +
                    s.substring(close + 1);
        }

        return s;
    }
}
```

---

# Brute Force Dry Run

Input:

```text
3[a2[c]]
```

---

## Step 1

Innermost:

```text
2[c]
```

Repeat `"c"` 2 times:

```text
cc
```

Now:

```text
3[acc]
```

---

## Step 2

Decode:

```text
3[acc]
```

Repeat `"acc"` 3 times:

```text
accaccacc
```

Answer:

```text
accaccacc
```

---

# Brute Force Complexity

## Time Complexity

String rebuilding happens many times.

Worst case:

```text
O(n²)
```

because every replacement creates new strings.

---

## Space Complexity

```text
O(n)
```

for generated strings.

---

# 2. Optimal Solution (Stack)

This is the standard interview solution.

---

# Idea

Use:

* stack for numbers
* stack for strings

Whenever we see:

* digit → build number
* `[` → push current string and number
* letters → append
* `]` → pop and repeat

---

# Optimal Code (Java)

```java
class Solution {
    public String decodeString(String s) {

        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();

        StringBuilder curr = new StringBuilder();
        int num = 0;

        for (char ch : s.toCharArray()) {

            // build number
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }

            // opening bracket
            else if (ch == '[') {

                countStack.push(num);
                stringStack.push(curr);

                num = 0;
                curr = new StringBuilder();
            }

            // closing bracket
            else if (ch == ']') {

                int repeat = countStack.pop();
                StringBuilder prev = stringStack.pop();

                for (int i = 0; i < repeat; i++) {
                    prev.append(curr);
                }

                curr = prev;
            }

            // character
            else {
                curr.append(ch);
            }
        }

        return curr.toString();
    }
}
```

---

# Optimal Dry Run

Input:

```text
3[a2[c]]
```

---

## Initial

```text
curr = ""
num = 0
```

---

## Read '3'

```text
num = 3
```

---

## Read '['

Push:

```text
countStack = [3]
stringStack = [""]
```

Reset:

```text
curr = ""
num = 0
```

---

## Read 'a'

```text
curr = "a"
```

---

## Read '2'

```text
num = 2
```

---

## Read '['

Push:

```text
countStack = [3,2]
stringStack = ["","a"]
```

Reset:

```text
curr = ""
```

---

## Read 'c'

```text
curr = "c"
```

---

## Read ']'

Pop:

```text
repeat = 2
prev = "a"
```

Append:

```text
prev = "acc"
```

Now:

```text
curr = "acc"
```

---

## Read ']'

Pop:

```text
repeat = 3
prev = ""
```

Append `"acc"` 3 times:

```text
accaccacc
```

Answer:

```text
accaccacc
```

---

# Why Two Stacks?

We need to remember:

## 1. Previous string

Example:

```text
3[a2[c]]
```

Before entering inner bracket:

```text
"a"
```

must be saved.

---

## 2. Repeat count

Need `2` for `"c"` and `3` for `"acc"`.

So:

* countStack → stores numbers
* stringStack → stores previous strings

---

# Optimal Complexity

## Time Complexity

```text
O(n)
```

Each character processed once.

---

## Space Complexity

```text
O(n)
```

for stacks and result.

---

# Interview Important Points

## Why use StringBuilder?

Strings are immutable in Java.

`StringBuilder` avoids repeated string creation.

---

## Why `num = num * 10 + digit`?

Handles multi-digit numbers.

Example:

```text
12[a]
```

Steps:

```text
num = 1
num = 1*10 + 2 = 12
```

---

# Which Solution Should You Use?

| Approach      | Time  | Good? |
| ------------- | ----- | ----- |
| Brute Force   | O(n²) | No    |
| Stack Optimal | O(n)  | Yes ✅ |

The stack solution is the expected interview solution.



```text id="2hajxg"
Is it solving from start?
or from middle brackets?
How curr becomes final answer?
Why curr changes again and again?
```

THIS is the real confusion.

Now it will click.

---

# VERY IMPORTANT UNDERSTANDING

The algorithm processes:

```text id="c8vdbj"
LEFT TO RIGHT
```

ONLY ONCE.

It does NOT start from middle.

---

# Example

```text id="5i5lln"
2[a3[b]]
```

Characters are read one by one:

```text id="mjlwm0"
2
[
a
3
[
b
]
]
```

LEFT → RIGHT.

---

# KEY IDEA

The moment we see:

```text id="jlwm1"
]
```

the INNER bracket automatically finishes first.

That’s why it FEELS like middle is solved first.

---

# Think Like This

---

# When `[` Comes

We say:

```text id="jlwm2"
"pause current work"
```

Save everything in stack.

---

# When `]` Comes

We say:

```text id="jlwm3"
"okay inner work finished"
```

Now combine it with old work.

---

# Let’s Track ONLY `curr`

THIS is your main confusion.

---

# Example

```text id="jlwm4"
2[a3[b]]
```

---

# START

```text id="jlwm5"
curr = ""
```

---

# Read `2`

Only number changes.

```text id="jlwm6"
curr = ""
```

---

# Read `[`

Push old state.

Reset curr.

```text id="jlwm7"
curr = ""
```

---

# Read `a`

Append:

```text id="jlwm8"
curr = "a"
```

---

# Read `3`

Only num changes.

```text id="jlwm9"
curr = "a"
```

---

# Read `[`

Push current `"a"` into stack.

Reset curr.

NOW IMPORTANT:

```text id="jlwm10"
curr = ""
```

Why?

Because we entered INNER bracket.

---

# Read `b`

```text id="jlwm11"
curr = "b"
```

---

# Read `]`

NOW INNER BRACKET FINISHED.

Current:

```text id="jlwm12"
curr = "b"
```

Pop:

```text id="jlwm13"
repeat = 3
prev = "a"
```

Now:

```java id="jlwm14"
prev.append(curr)
```

3 times:

```text id="jlwm15"
"a" + "b" + "b" + "b"
```

Result:

```text id="jlwm16"
prev = "abbb"
```

NOW IMPORTANT LINE:

```java id="jlwm17"
curr = prev;
```

So:

```text id="jlwm18"
curr = "abbb"
```

---

# HUGE UNDERSTANDING

After inner bracket finished:

```text id="jlwm19"
curr now stores decoded INNER result
```

VERY IMPORTANT.

---

# Continue

Now last `]`.

Current:

```text id="jlwm20"
curr = "abbb"
```

Pop:

```text id="jlwm21"
repeat = 2
prev = ""
```

Repeat:

```text id="jlwm22"
"" + "abbb" + "abbb"
```

Result:

```text id="jlwm23"
prev = "abbbabbb"
```

Then:

```java id="jlwm24"
curr = prev;
```

So:

```text id="jlwm25"
curr = "abbbabbb"
```

END.

Return curr.

---

# YOUR BIGGEST CONFUSION

You asked:

> how again curr becomes final answer?

Because EVERY TIME a bracket finishes:

```java id="jlwm26"
curr = decoded result of that bracket
```

---

# Think of curr as:

```text id="jlwm27"
current completed work
```

---

# Visual Flow

---

# Outer Bracket

```text id="jlwm28"
2[      ]
```

Inside it:

```text id="jlwm29"
a3[b]
```

---

# Inner Bracket Finishes First

```text id="jlwm30"
3[b] -> bbb
```

Now curr becomes:

```text id="jlwm31"
abbb
```

---

# Outer Bracket Finishes

```text id="jlwm32"
2[abbb]
```

→

```text id="jlwm33"
abbbabbb
```

Now curr becomes final answer.

---

# SUPER IMPORTANT INSIGHT

`curr` is NOT fixed.

It keeps changing.

| Situation           | curr value |
| ------------------- | ---------- |
| start               | ""         |
| after reading a     | "a"        |
| after inner bracket | "abbb"     |
| after outer bracket | "abbbabbb" |

---

# WHY STACK IS NEEDED

Because when entering inner bracket:

```text id="jlwm34"
we temporarily forget outer work
```

So we SAVE outer work in stack.

Later restore it.

---

# REAL LIFE ANALOGY

Suppose you are writing assignment.

You pause main assignment to solve sub-question.

After sub-question finished:

```text id="jlwm35"
attach sub-answer to main answer
```

Exactly same here.

---

# FINAL SIMPLE SENTENCE

The algorithm:

```text id="jlwm36"
moves left to right,
but brackets naturally finish from inside to outside.
```

That’s why inner brackets decode first.

</p>
