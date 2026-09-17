---
title: "Spiral Matrix"
difficulty: Medium
topics: [Array, Matrix, Simulation]
category: 21-math-geometry
order: 9
source: [Grind75]
platform: LeetCode
url: https://leetcode.com/problems/spiral-matrix/
status: ac-assisted
note: ""
date_created: 2026-09-15
date_updated: 2026-09-15
---

# 54. Spiral Matrix

## 題目說明

- 給定 `m x n` 的矩陣，依螺旋順序回傳所有元素。
- 矩陣可能不是正方形，也可能只剩一列或一行。

## 心得

四邊界法本身沒問題，收縮條件卡住，靠 AI 輔助寫出來。原本寫的時候就已經分四個邊處理，也還留著 Spiral Matrix II 的印象，但收縮的部分很亂，最後靠 AI 輔助才寫出來。

---

## 解法一：四邊界收縮

### Intuition

螺旋走訪的本質是一圈一圈往內縮：用 `top`、`bottom`、`left`、`right` 四個邊界描述目前還沒走過的矩形範圍，每繞一圈就把外圈四條邊各走一次，再把對應的邊界往內推一格。

真正容易寫錯的地方不是四個方向怎麼走，而是**什麼時候該停止走某一邊**。當矩陣收縮到只剩一列或一行時，上、下兩條邊會變成同一列，左、右兩條邊會變成同一行，如果四段迴圈無條件都執行，最後一列或最後一行就會被重複加入結果。第三、四段前面的 `if (top <= bottom)` 與 `if (left <= right)` 就是在確認「收縮後這條邊還存在」，不存在就跳過，這是這題唯一需要小心維護的不變量。

### Approach

1. 初始化 `top=0`、`bottom=matrix.length-1`、`left=0`、`right=matrix[0].length-1`，代表目前剩下的矩形範圍。
2. 迴圈條件 `left <= right && top <= bottom`：範圍還存在才繼續繞圈。
3. 走上邊（`left` 到 `right`），完成後 `top++`。
4. 走右邊（新的 `top` 到 `bottom`），完成後 `right--`。
5. 若 `top <= bottom` 仍成立，代表還有獨立的下邊可走，走下邊（`right` 到 `left`）後 `bottom--`。
6. 若 `left <= right` 仍成立，代表還有獨立的左邊可走，走左邊（`bottom` 到 `top`）後 `left++`。
7. 回到步驟 2，直到範圍收縮完畢。

### Complexity

**Time complexity: `O(m * n)`**

`m`、`n` 為矩陣的列數與行數；每個格子恰好被加入結果一次。

**Space complexity: `O(1)`**（不含輸出）

只用了四個邊界變數，額外空間與輸入大小無關；回傳的 `res` 是必要輸出，不計入額外空間。

### Code

```java
/**
 * 54. Spiral Matrix
 * Time Complexity: O(m * n)
 * Space Complexity: O(1) extra space (excluding the output list)
 */
class Solution {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (left <= right && top <= bottom) {

            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            right--;

            // Guard: skip the bottom row once it has collapsed into the top row.
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    res.add(matrix[bottom][i]);
                }
                bottom--;
            }

            // Guard: skip the left column once it has collapsed into the right column.
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    res.add(matrix[i][left]);
                }
                left++;
            }
        }

        return res;
    }

}
```

程式碼規則遵循 `_solution-template.md`；原碼沒有 `// KEY!` 標記，上方兩處 guard 的英文註解是本次補充說明，邏輯與原 AC 程式碼完全一致。

### Code Review

- **Learning provenance**：對應 `status: ac-assisted`——四邊界骨架是自己想出來的（還記得 Spiral Matrix II 的寫法），但收縮條件（兩個 `if` guard）靠 AI 輔助才寫對，能否獨立重現未確認。
- **Correctness / invariant**：正確。核心不變量是「`[top, bottom] x [left, right]` 永遠是還沒走過的矩形範圍」，兩個 `if` guard 正確處理了範圍收縮成單列或單行時的邊界情況，不會重複計入格子。用單一儲存格、單列、單行三種邊界案例手動追過都成立。
- **Strength**：四個方向共用同一個 `while` 迴圈依序展開，不需要方向陣列或額外的 flag 變數，邏輯線性、容易照著唸出每一步在做什麼。
- **Trade-off**：寫死四段迴圈在這題最直接，但如果之後遇到「原地在矩陣上標記」或「起始方向可變」的變形題，四段式會整段重寫；方向陣列版本會更容易搬過去用，細節見下方 Optimality。
- **Edge cases**：單列、單行、單一格三種都由兩個 guard 正確處理，已如上追蹤過；矩陣保證至少 1x1，不需要額外的空矩陣檢查。

---

## Optimality

以時間衡量，`O(m * n)` 已經是下限——結果必須包含每個格子恰好一次，不可能更快。以額外空間衡量，`O(1)` 也已經是下限，不需要 visited 矩陣或方向索引陣列。這個解法在時間與空間兩個指標上都是最佳解。

唯一值得認識的替代寫法是**方向陣列 + 步數控制**：用 `dr/dc` 表示上右下左四個方向，搭配一個「這個方向還能走幾步」的計數器，撞到計數歸零就換方向並讓下個方向的步數減一。它的時間、空間複雜度跟四邊界法一樣，學習價值在於這個模式能直接套用到「原地生成螺旋矩陣」（59 題）或「螺旋方向可能不是從右上開始」的變形題，通用性比四邊界法高；代價是初次寫的時候步數遞減的邏輯比邊界收縮更抽象。這題本身用四邊界法已經足夠，不需要換寫法。

## 相關

- Array — 四邊界收縮是陣列題常見的「原地維護範圍」手法，對照 [array](../../topics/T01-21-array.md)。
- Matrix — 這題與 Spiral Matrix II 是同一組「邊界逐圈收縮」的正反面（一個讀、一個填），對照 [matrix](../../topics/T01-11-matrix.md)。
- [LeetCode 刷題總覽](../../_moc.md)
