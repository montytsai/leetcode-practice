---
title: "Container With Most Water"
difficulty: Medium
topics: [Two Pointers, Array, Greedy]
category: 02-two-pointers
order: 6
source: [LeetCode75]
platform: LeetCode
url: https://leetcode.com/problems/container-with-most-water/
status: ac-unknown
note: ""
date_created: 2026-02-28
date_updated: 2026-09-24
---

# 11. Container With Most Water

## 題目說明

- 給一個非負整數陣列 `height`，索引 `i` 的值代表該處垂直線的高度。
- 從中選兩條線與 x 軸圍出容器，求能裝下的最大水量（面積 = 寬 × 兩線中較矮的高）。

## 心得

用 Greedy 解

---

## 解法一：對撞雙指標

### Intuition

兩條邊界線圍出的面積由「寬度」與「兩邊中較矮的那條」共同決定，寬度隨指標往中間夾逼只會變窄，所以每一步唯一能改善結果的動作是換掉限制高度的那條邊。

初始把兩根指標放在最左、最右，先取得最大寬度下的面積。之後每步比較兩指標的高度，移動較矮的那一側：矮的那條線本身就是目前面積的瓶頸，留著它、只移動另一側，寬度只會縮小，高度上限仍卡在這條矮線，不可能得到更大的面積，所以可以放心丟棄它。這個「丟棄不利選擇、只保留有機會變好的那一側」的判斷，就是 Greedy 的局部最優選擇；實作上落地成同向夾逼的兩指標寫法。

### Approach

1. `l` 指向最左、`r` 指向最右，`maxArea` 記錄目前看過的最大面積。
2. 迴圈條件 `l < r`：先算目前寬度 `r - l`，乘上兩指標中較矮的高度得到這一輪面積，更新 `maxArea`。
3. 比較 `height[l]` 與 `height[r]`，較矮的一側指標往中間移動一格；兩者相等時移動哪一側都不影響正確性，程式選擇移動左指標。
4. 指標相遇時迴圈結束，回傳 `maxArea`。

### Complexity

**Time complexity: `O(n)`**

`l` 與 `r` 只會往中間移動，總移動次數不超過 `n`，每輪是常數工作，單一 pass 處理完整個陣列。

**Space complexity: `O(1)`**

只用兩個指標與兩個累加變數，不隨輸入大小增加額外空間。

### Code

```java
/**
 * 11. Container With Most Water
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;

        int l = 0;
        int r = height.length - 1;

        while (l < r) {
            int area = r - l;

            if (height[l] < height[r]) {
                area *= height[l];
                l++;
            } else {
                area *= height[r];
                r--;
            }

            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}
```

程式碼規則：完整包在 `java` code fence 裡；Header 含題號、題名、Time/Space Complexity；所有註解 B1 English；保留 AC 的實際邏輯，只整理格式。

### Code Review

- **Learning provenance**：未確認，使用者只留下「用 Greedy 解」的心得，沒有說明是否獨力想到或有無參考。
- **Correctness / invariant**：正確。核心不變式是「較矮的那一側決定目前面積的高度上限」——固定較矮的指標、只移動另一側，寬度必然縮小，而高度上限仍受限於原本較矮的那條線，不可能產生更大面積，所以每輪移動較矮側都不會丟掉真正的最優解。`l < r` 保證不會自己跟自己配對。
- **Strength**：寬度與高度的計算寫在同一輪，比較完立刻決定移動方向，沒有多餘的重複計算或分支。
- **Bug / Trade-off / Style**：沒有發現需要修的問題。高度上限 `10^4`、寬度上限略小於 `10^4`，乘積在 `int` 範圍內不會溢位；變數命名 `l`／`r`／`area`／`maxArea` 意圖清楚。
- **Edge cases**：題目保證 `height.length >= 2`，迴圈至少會跑一輪，不需要額外處理空陣列或單一元素。

---

## Optimality

以時間複雜度為指標，這是最優解：至少要讀過每個元素一次才能知道所有高度，`O(n)` 已經是下界，雙指標單一 pass 沒有多餘工作。

替代法（學習價值，非效能提升）：暴力列舉所有 `(i, j)` 配對取最大面積，`O(n^2)`。這是還沒看出「移動較矮側不會丟掉最優解」這個 invariant 時最直覺的寫法，可以當作推導雙指標解法前的起點，實際提交不建議用。

## 相關

- Two Pointers — 對撞雙指標，靠「較矮側是瓶頸」的不變式決定移動方向
- Array — 在原陣列上直接夾逼，不需要額外資料結構
- Greedy — 每步丟棄較矮邊界的局部選擇可證明不影響全域最佳解
- [two-pointers](../../topics/T02-21-two-pointers.md)
- [array](../../topics/T01-21-array.md)
- [greedy](../../topics/T18-21-greedy.md)
- [LeetCode 刷題總覽](../../_moc.md)
