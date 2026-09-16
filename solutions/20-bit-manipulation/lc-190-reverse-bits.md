---
title: "Reverse Bits"
difficulty: Easy
topics: [Bit Manipulation, Divide and Conquer]
category: 20-bit-manipulation
order: 4
source: [Grind75, Extra]
platform: LeetCode
url: https://leetcode.com/problems/reverse-bits/
status: review
note: ""
date_created: 2026-08-11
date_updated: 2026-08-11
---

## 心得

從最低位開始讀取 `n`，每次先把 `res` 左移一格，再把目前最低位接到右側；重複 32 次後，原本的位元順序就會反轉。

Java `int` 是 32-bit signed integer，所以要用 `>>>` 做 unsigned right shift。它會在左側補 `0`，即使 `n` 的最高位是 `1`、Java 將它解讀成負數，也能把原本的 32 個 bit 全部依序處理完。

本次只完成並補登這個逐位迭代的 AC 解法，最佳解尚未研究。

## Java

```java
class Solution {
    public int reverseBits(int n) {
        int res = 0;

        // Read all 32 bits from right to left.
        for (int i = 0; i < 32; i++) {
            // Make room, then append the current lowest bit.
            res = (res << 1) + (n & 1);

            // Fill the left side with 0, even when n is negative.
            n >>>= 1;
        }

        return res;
    }
}
```

## 複雜度

- 時間：`O(32) = O(1)`，因為 Java `int` 固定只有 32 bits。
- 空間：`O(1)`，只使用固定數量的變數。

## 相關

- [Bit Manipulation 主題](../../topics/T20-21-bit-manipulation.md) — 逐位讀取並反向組合
- [Divide and Conquer 主題](../../topics/T00-22-divide-and-conquer.md) — 官方題目標籤的另一種解法方向
- [LeetCode 刷題總覽](../../_moc.md)
