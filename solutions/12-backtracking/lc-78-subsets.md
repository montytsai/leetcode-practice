---
title: "Subsets"
difficulty: Medium
topics: [Backtracking, Array, Bit Manipulation]
category: 12-backtracking
order: 8
source: [Carl]
platform: LeetCode
url: https://leetcode.com/problems/subsets/
status: ac-solo
note: ""
date_created: 2026-04-14
date_updated: 2026-09-15
---

# 78. Subsets

## 題目說明

- 給定一個不含重複元素的整數陣列 `nums`。
- 返回該陣列的所有子集（power set）。
- 不可有重複子集，順序不限。

## 心得

回溯刷的有點多，已經得心應手。

---

## 解法一：Backtracking（start index）

### Intuition

每一層代表一次遞迴「從 index 開始選擇一個元素加入子集」。使用 path 紀錄目前子集，並在每層加入結果（即使不選也算一個子集）。為避免重複子集，每層只對剩餘元素遞迴（透過 start 控制）。

視覺化理解（以 `[1,2,3]` 為例，這棵決策樹沿用 2025 年第一次刷這題時畫的圖）：

```
                           []
            ┌───────────────┬───────────────┐
           取1             取2              取3
            ▼               ▼               ▼
        [1] + 2,3       [2] + 3            [3]       <= [子集] + {剩餘可選元素}
        ┌───────┐           │
       取2      取3         取3
        ▼       ▼           ▼
  [1,2] + 3   [1,3]       [2,3]
        │
       取3
        ▼
     [1,2,3]

所有子集依據路徑產出：
→ [],[1],[1,2],[1,2,3],[1,3],[2],[2,3],[3]
```

### Approach

1. `backtracking(nums, start, ans, path)` 一進入就把目前的 `path`（複製一份）加入 `ans`——每個中繼路徑本身就是一個合法子集，不需要等到遞迴到底才記錄。
2. 對 `i` 從 `start` 走到 `nums.length - 1`：把 `nums[i]` 加入 `path`，遞迴呼叫 `backtracking(nums, i + 1, ans, path)`，回來後把 `nums[i]` 移出 `path`（回溯）。
3. 用 `i + 1` 而不是 `start` 或 `i` 呼叫下一層，確保下一層只會選到目前這個元素之後的元素，這是避免重複子集的關鍵。

### Complexity

**Time complexity: `O(n * 2^n)`**

共有 `2^n` 個子集，每個子集平均需要 `O(n)` 的複製成本。

**Space complexity: `O(n)`**（不含輸出）

額外空間來自遞迴深度與 `path` 本身，兩者都與 `n` 同量級。

### Code

```java
/**
 * 78. Subsets
 * Time Complexity: O(n * 2^n)
 * Space Complexity: O(n) extra space (excluding the output list)
 */
class Solution {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtracking(nums, 0, ans, new ArrayList<>());
        return ans;
    }

    private void backtracking(int[] nums, int start, List<List<Integer>> ans, List<Integer> path) {
        ans.add(new ArrayList<>(path));

        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backtracking(nums, i + 1, ans, path);
            path.remove(path.size() - 1);
        }
    }

}
```

### Code Review

- **Learning provenance**：對應 `status: ac-solo`——她表示回溯類題目刷得夠多、這題已經得心應手，足以認定是獨立完成。
- **Correctness / invariant**：正確。`ans.add(new ArrayList<>(path))` 放在迴圈之前而不是遞迴到底才加，直接把「每個節點都是答案」這個性質寫出來，不需要額外的 base case 判斷；`i + 1` 維持了「同一層不回頭」的不變量。
- **Strength**：三段式（做選擇、遞迴、撤銷選擇）寫得乾淨，`path` 全程只用同一個 `ArrayList`，靠 `add`/`remove(size-1)` 維護，沒有多餘的複製。
- **Trade-off**：無明顯缺點；這是 2026-04-14、2026-09-15 兩次 AC 共用的同一套手法，寫法穩定。
- **Edge cases**：`nums` 只有一個元素時迴圈自然只跑一層；題目保證 `nums` 非空且元素不重複，不需要額外特判。

---

## Optimality

時間 `O(n * 2^n)` 已經是下限——結果本身就有 `2^n` 個子集，每個平均長度 `O(n)`，不可能更快列舉完。空間 `O(n)`（遞迴深度與 `path`）也已經是額外空間的下限。

唯一值得認識的替代法是**迭代法（bitmask 列舉）**：用 `0` 到 `2^n - 1` 每個整數的二進位表示一種選取方式，第 `k` 位是 `1` 就代表選了 `nums[k]`。時間、空間複雜度與回溯法相同，差別在於不用遞迴、不用回溯的加入/移除，改成純粹的位元判斷，寫法更貼近 `topics` 裡的 Bit Manipulation 標籤；缺點是可讀性不如「做選擇、遞迴、撤銷選擇」直覺。這題用回溯法已經足夠，不需要換寫法。

## 相關

- Backtracking — 「答案收在每個節點」而非葉子的代表題，對照 [backtracking](../../topics/T12-21-backtracking.md)。
- Array — 對照 [array](../../topics/T01-21-array.md)。
- [LeetCode 刷題總覽](../../_moc.md)
