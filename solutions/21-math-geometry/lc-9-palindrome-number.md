---
title: "Palindrome Number"
difficulty: Easy
topics: [Math]
category: 21-math-geometry
order: 7
source: [Grind75]
platform: LeetCode
status: ac-assisted
note: ""
date_created: 2026-07-19
date_updated: 2026-08-10
---

## 心得

不把數字轉成字串。這題一開始想不到，問 AI 後聯想到第 5 題的做法：只反轉後半段數字，再和前半段比較。負數不是回文；非零且尾數是 `0` 的數字也不是回文。若位數是奇數，反轉後的中間位不用比較，所以比較 `x == reverse / 10`。

本次提交顯示 runtime percentile 100%，這只代表這次提交的結果，不當成穩定效能結論。

## Java

```java
class Solution {

    public boolean isPalindrome(int x) {
        // Negative numbers are not palindromes.
        if (x < 0) return false;
        if (x == 0) return true;
        // A non-zero number ending in zero cannot be a palindrome.
        if (x % 10 == 0) return false;

        int reverse = 0;

        // Reverse only the second half of the number.
        while (x > reverse) {
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }

        // For an odd number of digits, ignore the middle digit.
        return x == reverse || x == (reverse / 10);
    }

}
```
