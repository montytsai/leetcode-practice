---
title: "Move Zeroes"
difficulty: Easy
topics: [Two Pointers, Array]
category: 02-two-pointers
order: 7
source: [Grind75, LeetCode75]
platform: LeetCode
url: https://leetcode.com/problems/move-zeroes/
status: ac-unknown
note: ""
date_created: 2026-02-28
date_updated: 2026-08-10
---

## 心得

卡住時先想「有額外空間我會怎麼做」,再把那個做法壓回原陣列——本題 0 值不重要,讀寫指針可以無腦覆寫。

馬上就想到要分讀寫指針,但一開始卡在「指標該怎麼移動」:腦中在跑 read/write 各種組合的四宮格,想著「真的需要寫入才寫」,結果愈想愈亂。反璞歸真後改問自己:如果允許 O(n) 額外空間,我會開一個新陣列、依序放非零值、剩下補零。這個做法搬回原陣列時完全不衝突——`write` 永遠不會超過 `read`,被覆蓋掉的位置一定是已經讀過的,而且被丟掉的值只可能是 0,沒有保存價值。想通這點就秒解。

**可推廣的招式**:同向讀寫指針的原地改寫(27 Remove Element、443 String Compression 同一招)。判準是「被覆寫的內容不需要保留」——成立就無腦覆寫,不成立才要 swap。

複雜度:time O(n)、space O(1)。2026-08-10 重刷,一次過。

## Java

```java
class Solution {

    public void moveZeroes(int[] nums) {
        // KEY! write = the next position that can take a non-zero number.
        int write = 0;

        // First pass: move every non-zero number to the front, keeping the order.
        for (int read = 0; read < nums.length; read++) {
            if (nums[read] != 0) {
                // Safe to overwrite: write <= read, so this slot was already read,
                // and the value we drop can only be a zero.
                nums[write] = nums[read];
                write++;
            }
        }

        // Second pass: fill the rest of the array with zeros.
        for (; write < nums.length; write++) {
            nums[write] = 0;
        }
    }

}
```

## Follow-up:寫入次數的 trade-off

這題唯一能聊出深度的地方。設 n 個元素、m 個非零、p = 第一個 0 出現之前的非零個數:

| 版本 | 寫入次數 |
| --- | --- |
| 兩趟覆寫(本解) | 固定 n 次(pass 1 寫 m 次,pass 2 補 n−m 個 0) |
| 一趟 swap(`swap(nums[read], nums[write])`) | 2m 次 |
| 覆寫 + 守衛 `if (read != write)` | n − p 次 |

所以「一趟 swap 比較好」是有條件的:零多於一半(m < n/2)時 swap 才寫得少;零稀疏時反而兩趟覆寫贏。真正能省的是第三種——陣列開頭一長串非零時,守衛讓那一段完全不寫(全無 0 的輸入則 pass 1 一次都不寫)。

在 LeetCode 上三者跑起來沒差;有意義的是面試官把場景換成「寫入有成本」時(寫 SSD、寫入會觸發 observer、cache line 變 dirty)。另外不必為了「用了兩趟」道歉——連續記憶體上兩趟 O(n) 的常數差異幾乎測不出來。

## 相關

- [two-pointers](../../topics/T02-21-two-pointers.md) — 主題筆記
- [lc-27-remove-element](../01-arrays-hashing/lc-27-remove-element.md) — 同一招:同向讀寫指針原地改寫
- [lc-443-string-compression](lc-443-string-compression.md) — 同一招用在字串壓縮
