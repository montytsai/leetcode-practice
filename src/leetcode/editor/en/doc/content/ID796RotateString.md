<p>Given two strings <code>s</code> and <code>goal</code>, return <code>true</code> <em>if and only if</em> <code>s</code> <em>can become</em> <code>goal</code> <em>after some number of <strong>shifts</strong> on</em> <code>s</code>.</p>

<p>A <strong>shift</strong> on <code>s</code> consists of moving the leftmost character of <code>s</code> to the rightmost position.</p>

<ul> 
 <li>For example, if <code>s = "abcde"</code>, then it will be <code>"bcdea"</code> after one shift.</li> 
</ul>

<p>&nbsp;</p> 
<p><strong class="example">Example 1:</strong></p> 
<pre><strong>Input:</strong> s = "abcde", goal = "cdeab"
<strong>Output:</strong> true
</pre>
<p><strong class="example">Example 2:</strong></p> 
<pre><strong>Input:</strong> s = "abcde", goal = "abced"
<strong>Output:</strong> false
</pre> 
<p>&nbsp;</p> 
<p><strong>Constraints:</strong></p>

<ul> 
 <li><code>1 &lt;= s.length, goal.length &lt;= 100</code></li> 
 <li><code>s</code> and <code>goal</code> consist of lowercase English letters.</li> 
</ul>

<div><div>Related Topics</div><div><li>String</li><li>String Matching</li></div></div><br><div><li>👍 4395</li><li>👎 333</li></div>