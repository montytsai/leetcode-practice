---
title: "Sort Colors"
difficulty: Medium
topics: [Two Pointers, Array, Sorting]
category: 01-arrays-hashing
order: 25
source: [Grind75]
platform: LeetCode
url: https://leetcode.com/problems/sort-colors/
status: ac-unknown
note: ""
date_created: 2026-09-08
date_updated: 2026-09-08
---
# 75. Sort Colors

## 題目說明

- 陣列只含 `0`、`1`、`2` 三種值（代表紅白藍三色），要求原地排序，讓相同顏色相鄰，且顏色順序是紅（0）→ 白（1）→ 藍（2）。
- 不能使用內建排序函式；目標是常數額外空間，並嘗試用最少的掃描趟數完成（LeetCode 原題有一趟解的 follow-up）。

## 心得

想到之前「把 0 移到陣列尾端」那題（Move Zeroes）用的讀寫指標套路，直接套兩輪：先把 0 分到前面，再把 1（在剩下的部分裡）分到前面，寫完一次就 AC。

---

## 解法一：讀寫指標手法連續套用兩輪，每輪用 swap 分區

### Intuition

這題是把 lc-283 Move Zeroes 的讀寫指標手法連續套用兩輪。第一輪只在意「是不是 0」：用 `write`、`read` 兩個指標從頭掃描，把所有 0 依序搬到陣列最前面，`write` 停下的位置就是「0 的區塊」結束的地方。

第二輪從 `write` 停下的位置重新開始掃描（`read` 重設成上一輪結束時的 `write`），這次只在意「是不是 1」，把 1 依序搬到緊接在 0 之後的位置。掃完後，`write` 之前是 0、`write` 到目前 `read` 之間是 1，剩下的自然就是 2——不需要額外處理，因為 0 跟 1 都已經被搬到前面，剩下的位置本來就只剩 2。

跟 Move Zeroes 不同的地方在於：Move Zeroes 被踢出去的值只會是 0，是可以丟棄的廢值，所以用**覆寫**就夠；這題三種值都要保留、不能丟，所以要用 **swap**——被換出 `write` 位置的值不會消失，而是被放到 `read` 的位置，等這一輪或下一輪繼續處理它。

跟 Dutch National Flag 的三指標一次掃描解法比，這個寫法多跑一趟，但每一趟都是單一條件的「同向讀寫指標分區」，思路跟 lc-283 完全一樣，不用一次想清楚三個指標交錯時的邊界。

### Approach

1. 第一輪：`write = 0`，`read` 從 `0` 掃到底，`nums[read] == 0` 就 `swap(write, read)` 並讓 `write` 前進。
2. 第一輪結束後，`[0, write)` 都是 0，`write` 是 0 區塊之後的第一個空位。
3. 第二輪：`read` 從 `write` 重新開始掃到底，`nums[read] == 1` 就 `swap(write, read)` 並讓 `write` 前進。
4. 兩輪結束，`[0, write)` 內先是若干個 0、接著若干個 1，`write` 之後（到陣列結尾）全部是 2，原地排序完成。

### Complexity

**Time complexity: `O(n)`**

兩趟線性掃描，各自是 `O(n)`，加總仍是 `O(n)`，只是常數因子是單趟解法的兩倍。

**Space complexity: `O(1)`**

只用 `write`、`read` 兩個索引與 swap 用的暫存變數，沒有額外資料結構。

### Code

```java
/**
 * 75. Sort Colors
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class Solution {

    public void sortColors(int[] nums) {
        int write = 0;
        int read = 0;

        // First pass: partition all 0s to the front.
        for (; read < nums.length; read++) {
            if (nums[read] == 0) {
                swap(nums, write, read);
                write++;
            }
        }

        // Second pass: partition all 1s right after the 0 block.
        // Everything left after this pass is 2, with no extra work needed.
        read = write;
        for (; read < nums.length; read++) {
            if (nums[read] == 1) {
                swap(nums, write, read);
                write++;
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

}
```

### Code Review

- **Learning provenance**：`self-solved`——想到 Move Zeroes 的讀寫指標手法後直接套用，一次寫完就 AC；獨立重現能力未確認。
- **Correctness / invariant**：正確。每一輪都是標準的分區（partition）掃描：`write` 之前的區間永遠只裝已確定符合條件的值；被 swap 換到 `read` 位置的值，一定是先前已經檢查過、不符合當輪條件的值，所以放到 `read`（下一步就會跳過）不會遺漏任何應該被分類的元素。
- **Strength**：正確辨識出「被踢出去的值不能丟」，改用 swap 而不是 Move Zeroes 的覆寫，這是兩題的關鍵差異，也是這次能直接套用舊手法又不出錯的原因。
- **Trade-off**：兩趟掃描讓陣列被完整走訪兩次，時間複雜度仍是 `O(n)`，但常數因子是經典 Dutch National Flag 三指標一次掃描解法的兩倍；面試被追問「能不能一趟做完」時，三指標版本(`low`/`mid`/`high`)才是預期答案。
- **Edge cases**：空陣列或長度 1 的陣列，兩輪迴圈都不會觸發或只執行一次無意義的 swap，結果仍正確；全部同色（全 0、全 1 或全 2）時，兩輪中至多一輪會實際搬動元素，另一輪迴圈直接跳過，也不影響正確性。

---

## 解法比較

單一解法，暫不需要比較表。

### Optimality

時間複雜度 `O(n)` 已經是這題的理論下限——排序結果需要至少檢視每個元素一次。這個兩趟版本在 Time 與 Space 的漸進複雜度上已經最佳，跟經典的 Dutch National Flag 一趟三指標解法完全同級，差別只在常數因子（兩趟 vs. 一趟）。

**一個值得知道的替代法**：Dutch National Flag 演算法用 `low`、`mid`、`high` 三個指標一次掃描完成三色分類——`mid` 指向目前檢查的元素，`nums[mid] == 0` 就跟 `low` 交換並兩者一起前進，`nums[mid] == 2` 就跟 `high` 交換並只讓 `high` 後退（`mid` 不動，因為換過來的值還沒檢查過），`nums[mid] == 1` 則只前進 `mid`。這個版本只掃一趟陣列，是這題在面試裡最常被期待看到的答案；本題的兩趟版本勝在直接複用已經很熟的 Move Zeroes 手法，出錯機會更低。

## 相關

- [Two Pointers](../../topics/T02-21-two-pointers.md) — 同向讀寫指標的分區手法，套用兩輪做三色分類；被換出去的值要留在陣列裡，因此用 swap 而非覆寫
- [Array](../../topics/T01-21-array.md) — 原地排序，不使用額外陣列
- [Sorting](../../topics/T00-21-sorting.md) — 三種值的計數式分類，不是一般的比較排序
- [lc-283-move-zeroes](../02-two-pointers/lc-283-move-zeroes.md) — 同一招的原型：單一條件的讀寫指標分區
- [LeetCode 刷題總覽](../../_moc.md)
