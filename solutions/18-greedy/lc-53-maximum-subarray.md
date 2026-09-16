---
title: Maximum Subarray
difficulty: Medium
topics: 
category: 18-greedy
order: 3
source: [Extra]
platform: LeetCode
status: review
note: ""
date_created: 2026-03-26
date_updated: 2026-08-13
---

[LeetCode 題目連結](https://leetcode.com/problems/maximum-subarray/)

## 心得

貪婪解，從前面開始加；如果加上目前元素後還不如直接從目前元素重算，就丟棄前面的總和。

一開始沒有看清楚 subarray 必須是 contiguous 且 non-empty，也就是至少要包含一個值。因此 `sum` 與 `maxSum` 都要用 `nums[0]` 初始化，不能用 0，否則全負數陣列會得到錯誤答案。

## Java

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Keep the previous sum only when it helps the current subarray.
            sum = Math.max(nums[i], sum + nums[i]);
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }
}
```

## 相關

- [greedy](../../topics/T18-21-greedy.md) — 保留有幫助的前綴和，否則從目前元素重算
- [array](../../topics/T01-21-array.md) — 線性掃描陣列
- [divide-and-conquer](../../topics/T00-22-divide-and-conquer.md) — 官方題目標籤的另一種解法
- [dynamic-programming](../../topics/T16-21-dynamic-programming.md) — `sum` 是壓縮成一個變數的狀態
