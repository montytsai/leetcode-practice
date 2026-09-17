---
title: "Subsets"
difficulty: Medium
topics: [Backtracking, Array, Bit Manipulation]
category: 12-backtracking
order: 8
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-04-14
date_updated: 2026-04-14
---

[LeetCode 題目連結](https://leetcode.com/problems/subsets/submissions/1977490093/)

---

## 2025 初刷版（Day60，2025-06-16）

*原文見 [archive/doc/daily/day60-2025-06-16.md](../../archive/doc/daily/day60-2025-06-16.md)，已停更，內容按當時所寫原樣搬入*

### LC78. Subsets

#### 題目說明
- 給定一個不含重複元素的整數陣列 `nums`。
- 返回該陣列的所有子集（power set）。
- 不可有重複子集，順序不限。

---

#### 解法：回溯法（Backtracking）

##### 思路：邏輯與步驟
- 每一層代表一次遞迴「從 index 開始選擇一個元素加入子集」。 
- 使用 path 紀錄目前子集，並在每層加入結果（即使不選也算一個子集）。 
- 為避免重複子集，每層只對剩餘元素遞迴（透過 start 控制）。

##### 視覺化理解（以 [1,2,3] 為例）：
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

##### 複雜度分析
- 時間：O(2^n)，共 2^n 種子集。
- 空間：O(n)，為 path 及遞迴深度。

---

#### 解法比較

| 解法       | 時間複雜度  | 空間複雜度       | 適用情境                      |
|----------|--------|-------------|---------------------------|
| 回溯法      | O(2^n) | O(n)        | 遞迴處理全組合                   |
| 迭代法（未實作） | O(2^n) | O(1) ~ O(n) | 使用 while 或擴展 List，更適合底層語言 |

---

#### Java 程式碼連結
- 題目實作：[ID78Subsets.java](../../archive/src/main/java/io/github/monty/leetcode/backtracking/ID78Subsets.java)
- 單元測試：[ID78SubsetsTest.java](../../archive/src/test/java/io/github/monty/leetcode/backtracking/ID78SubsetsTest.java)
