---
title: "Single Number"
difficulty: Easy
topics: [Bit Manipulation, Array]
category: 20-bit-manipulation
order: 2
source: [Grind75, Carl]
platform: LeetCode
url: https://leetcode.com/problems/single-number/
status: ac-assisted
note: ""
date_created: 2026-08-04
date_updated: 2026-08-04
---

## 心得

題目限制 `O(n)`／`O(1)`，一直想不到方法，靠 AI 理解 XOR 的成對消除特性。

## Java

```java
class Solution {
    public int singleNumber(int[] nums) {
        // XOR with 0 keeps the original number: 0 ^ x = x.
        int res = 0;

        for (int num : nums) {
            // Equal numbers cancel each other: x ^ x = 0.
            // XOR is commutative, so the order does not matter.
            res ^= num;
        }

        // All duplicated numbers have canceled out.
        // The only number left is the number that appears once.
        return res;
    }
}
```

## XOR 為什麼可以解這題？

XOR 的三個核心規則：

```text
x ^ x = 0
x ^ 0 = x
0 ^ x = x
```

例如：

```text
nums = [4, 1, 2, 1, 2]

0 ^ 4 ^ 1 ^ 2 ^ 1 ^ 2
= 4 ^ (1 ^ 1) ^ (2 ^ 2)
= 4 ^ 0 ^ 0
= 4
```

每個出現兩次的數字都會互相消除，最後只留下出現一次的數字。

`res` 從 `0` 開始，是因為：

```text
0 ^ XXXX = XXXX
```

所以初始的 `0` 不會影響第一個數字。Enhanced `for` 會從陣列第一個元素開始走訪，不會漏掉 `nums[0]`。

## 複雜度

- Time：`O(n)`，每個陣列元素只走訪一次。
- Space：`O(1)`，只使用 `res` 與迴圈變數，不建立額外集合。

## 相關

- [Bit Manipulation 主題](../../topics/T20-21-bit-manipulation.md)
- [時間與空間複雜度主題](../../topics/T00-01-complexity-analysis.md)
- [_moc](../../_moc.md)
