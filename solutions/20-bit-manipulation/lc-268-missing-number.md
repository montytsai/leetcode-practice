---
title: "Missing Number"
difficulty: Easy
topics: [Bit Manipulation, Array, Hash Table, Math, Binary Search, Sorting]
category: 20-bit-manipulation
order: 5
source: [Grind75, Extra]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-08-10
date_updated: 2026-08-10
---

## 心得

題目給長度 `n` 的陣列，裡面有 `[0, n]` 中 `n` 個不同數字，要找缺少的那一個。第一反應想到等差級數公式；實際公式有查 Google 確認。先算 `0..n` 的總和 `n * (n + 1) / 2`，再逐一減掉陣列元素，最後剩下的就是缺少的數字。

時間是 O(n)，因為每個元素只處理一次；額外空間是 O(1)，只使用 `len` 與 `sum`。本次提交顯示 runtime percentile 100%，這只代表這次提交的結果，不當成穩定效能結論。

## Java

```java
class Solution {

    public int missingNumber(int[] nums) {
        int len = nums.length;
        // Sum of every number from 0 to len.
        int sum = (1 + len) * len / 2;

        // Remove every number that is present.
        for (int i = 0; i < len; i++) {
            sum -= nums[i];
        }

        return sum;
    }

}
```
