---
title: "Squares of a Sorted Array"
difficulty: Easy
topics: [Array, Two Pointers, Sorting]
category: 08-binary-search
order: 2
source: [Grind75, Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-03-02
date_updated: 2026-08-13
---

https://github.com/montytsai/leetcode-practice/blob/main/src/main/java/io/github/monty/leetcode/array/ID977SquaresOfASortedArray.java

[LeetCode 題目連結](https://leetcode.com/problems/squares-of-a-sorted-array)

## 心得

平方後的最大值一定來自排序陣列的其中一端，因此用左右指針比較平方值，並從結果陣列尾端放入較大的值。

每輪至少移動一個指針，直到兩端交錯。這樣不用先平方再排序，就能以 O(n) 時間得到遞增結果；額外空間是 O(n)，用來存放回傳陣列。

## Java

```java
class Solution {

    public int[] sortedSquares(int[] nums) {
        int len = nums.length;

        int[] res = new int[len];
        // Fill the result from right to left.
        int i = len - 1;

        int left = 0;
        int right = len - 1;

        while (left <= right) {
            int l = nums[left] * nums[left];
            int r = nums[right] * nums[right];

            if (l >= r) {
                res[i] = l;
                left++;
            } else {
                res[i] = r;
                right--;
            }
            i--;
        }

        return res;
    }
}
```

## 相關

- [two-pointers](../../topics/T02-21-two-pointers.md) — 左右夾逼後反向填入結果
- [array](../../topics/T01-21-array.md) — 主分類
