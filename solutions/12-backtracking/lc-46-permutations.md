---
title: "Permutations"
difficulty: Medium
topics: [Backtracking, Array]
category: 12-backtracking
order: 11
source: [Carl]
platform: LeetCode
url: https://leetcode.com/problems/permutations/
status: ac-unknown
note: ""
date_created: 2026-06-15
date_updated: 2026-09-03
---

# 46. Permutations

## 題目說明

- 給一個元素互不相同的整數陣列 `nums`，回傳所有排列，順序不限。
- `nums` 長度 1 到 6，元素互不重複，所以不需要去重。

## 心得

每層都從 0 開始掃，用 `used[]` 排除已經在路徑上的元素，這是排列跟組合的分水嶺。

---

## 解法一：回溯＋`used[]`

### Intuition

排列跟組合的差別在「順序重不重要」。組合用 `startIndex` 只往後看，天生不會回頭拿前面的元素；排列的每一個位置都可以放任何還沒用過的元素，所以每一層都要從 `0` 掃到尾。

從 `0` 開始掃會掃到已經放進路徑的元素，因此需要 `used[]` 記錄「目前這條路徑上有誰」。它是還原式的標記：往下遞迴前設成 `true`，回來後設回 `false`，跟 `path.add`／`path.remove` 成對出現。

決策樹的第一層有 `n` 個分支、第二層 `n-1` 個，依此類推，葉子共有 `n!` 個，每片葉子就是一組排列。

### Approach

1. 終止條件：`path.size() == nums.length`，代表每個元素都放進去了，把 `path` 的**複本**收進 `res`。
2. 每一層從 `i = 0` 掃到 `nums.length - 1`，`used[i]` 為 `true` 就跳過。
3. 選 `nums[i]`：加入 `path`、標記 `used[i] = true`，遞迴下一層。
4. 回溯：移除 `path` 最後一個元素、`used[i] = false`，讓同一層的下一個 `i` 能在乾淨的狀態下嘗試。

### Complexity

**Time complexity: `O(n · n!)`**

葉子有 `n!` 個，每片葉子要花 `O(n)` 複製 `path`；中間節點的總數也被 `n!` 的常數倍限制住，每個節點的 `for` 迴圈是 `O(n)`。

**Space complexity: `O(n)`**

不算輸出的話，遞迴深度、`path` 與 `used[]` 都是 `O(n)`。

### Code

```java
/**
 * 46. Permutations
 * Time Complexity: O(n * n!)
 * Space Complexity: O(n), excluding the output list
 */
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        boolean[] used = new boolean[nums.length];
        backtracking(nums, used, res, new ArrayList<>());

        return res;
    }

    private void backtracking(int[] nums, boolean[] used, List<List<Integer>> res, List<Integer> path) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path)); // copy, because path keeps changing
            return;
        }

        // Every level scans from 0; used[] skips elements already on the path.
        for (int i = 0; i < nums.length; i++) {
            if (used[i])
                continue;

            path.add(nums[i]);
            used[i] = true;

            backtracking(nums, used, res, path);

            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}
```

### Code Review

- **Learning provenance**：未確認（`ac-unknown`），本人未說明是否獨立寫出。
- **Correctness / invariant**：`used[i]` 的設定與還原、`path` 的加入與移除都成對出現，遞迴回來時狀態跟進入前完全相同；收答案用 `new ArrayList<>(path)` 深拷貝，沒有踩到「收參考」的地雷。
- **Strength**：`used[]` 用 `boolean[]` 而不是 `Set`，查詢與還原都是 `O(1)`，也沒有 boxing。
- **Style**：`if (used[i]) continue;` 沒有加大括號；單行可以接受，團隊規範要求一律加括號時再補。沒有其他問題。
- **Edge cases**：題目保證元素不重複，所以不需要去重；輸入有重複值時就是 [lc-47-permutations-ii](lc-47-permutations-ii.md)，要先排序再加同層去重。

---

## Optimality

以時間複雜度為指標，這是最優解：輸出本身有 `n!` 組、每組長度 `n`，光寫出答案就要 `O(n · n!)`。

替代法（空間取捨）：原地交換版回溯，第 `k` 層把 `nums[k]` 依序跟 `nums[k..n-1]` 交換，遞迴後再換回來，不需要 `used[]` 與 `path`。代價是輸出順序不再是字典序，也比較難延伸到 lc-47 的去重，面試時 `used[]` 版比較好講清楚。

## 相關

- Backtracking — 排列的原型：每層從 0 開始，靠 `used[]` 排除已選元素 → [backtracking](../../topics/T12-21-backtracking.md)
- Array — 以陣列索引當 `used[]` 的鍵 → [array](../../topics/T01-21-array.md)
- [LeetCode 刷題總覽](../../_moc.md)
