---
title: "Combination Sum II"
difficulty: Medium
topics: [Backtracking, Array]
category: 12-backtracking
order: 5
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-04-09
date_updated: 2026-04-09
---

去重!!! 要 i > start

[LeetCode 題目連結](https://leetcode.com/problems/combination-sum-ii/)

---

## 2025 初刷版（Day43，2025-05-30）

*原文見 [archive/doc/daily/day43-2025-05-30.md](../../archive/doc/daily/day43-2025-05-30.md)，已停更，內容按當時所寫原樣搬入*

### LC40. Combination Sum II

#### 題目說明
- 給定一個整數陣列 `candidates`（可能包含重複值）與一個目標值 `target`。
- 回傳所有不重複的組合，使組合中數字加總為 `target`。
- 每個數字只能使用一次，不能重複組合。

---

#### 解法一：回溯（Backtracking）

##### 思路：回溯 + 同層剪枝
- 將陣列排序以便：
    - 剪枝：若當前數字超過剩餘 target，後面也不用試。
    - 去重：同層若遇到與前一個相同的數字（`i > start && a[i] == a[i-1]`），直接跳過。
- 每次遞迴從 `i + 1` 開始，確保「不能重複選用相同元素」。
- 使用 `target - nums[i]` 作為傳遞條件（避免維護 sum 變數）。

##### 重點技巧
- **剪枝 + 同層跳重複**

- 同層去重：`i > start && nums[i] == nums[i - 1]`
  - 錯誤筆記：若寫成 `i > 0` 則會跳掉不同遞迴分支中的合法值。

- 不同層去重：遞迴傳入 `i + 1` 保證每個數只能用一次
- 與 LC39 的差別在於：
    - LC39 每個數可重複用 → 傳 `i`
    - LC40 每個數只能用一次 → 傳 `i + 1`

##### 複雜度分析
- 時間複雜度：O(2^n)，最壞需遍歷所有子集
- 空間複雜度：O(n)，遞迴深度

---

#### Java 程式碼連結
- 題目實作：[ID40CombinationSumII.java](../../archive/src/main/java/io/github/monty/leetcode/backtracking/ID40CombinationSumII.java)
- 單元測試：[ID40CombinationSumIITest.java](../../archive/src/test/java/io/github/monty/leetcode/backtracking/ID40CombinationSumIITest.java)
