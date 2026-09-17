---
title: "Subsets II"
difficulty: Medium
topics: [Backtracking, Array, Bit Manipulation]
category: 12-backtracking
order: 9
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-06-04
date_updated: 2026-06-04
---

剪枝從start開始

[LeetCode 題目連結](https://leetcode.com/problems/subsets-ii/)

---

## 2025 初刷版（Day61，2025-06-17）

*原文見 [archive/doc/daily/day61-2025-06-17.md](../../archive/doc/daily/day61-2025-06-17.md)，已停更，內容按當時所寫原樣搬入*

### LC90. Subsets II

#### 題目說明
- 給定一個整數陣列 `nums`，其中可能包含重複元素。
- 返回該陣列所有**不重複**的子集（power set）。
- 結果中不能有重複的子集，順序不限。

---

#### 解法：回溯法（Backtracking）

##### 思路與邏輯
- 題目與 LC78 類似，不過陣列中可能有重複元素，需處理**去重**問題。
- 步驟：
    1. 對 `nums` 進行排序，將相同元素相鄰排在一起。
    2. 每層遞迴時，若出現與前一個元素相同的值且「同層」，就跳過該元素（避免重複子集）。
- 每個節點都是一個合法子集，直接加入結果。

##### 視覺化（以 [1,2,2] 為例）
```    
                        [ ]
          ┌──────────────┼──────────────┐
         [1]2,2         [2]2           [2](重複X)     <= [子集] + {剩餘可選元素}
       ┌──┴──┐           │
    [1,2]2  (X)       [2,2]
       │
  [1,2,2]
```

##### 複雜度分析
- 時間複雜度：O(2^n)，最多產生 2^n 個子集，但實際數量會因重複元素減少。
- 空間複雜度：O(n)，遞迴深度與 path 使用。

---

#### Java 程式碼連結
- [ID90SubsetsII.java](../../archive/src/main/java/io/github/monty/leetcode/backtracking/ID90SubsetsII.java)
- [ID90SubsetsIITest.java](../../archive/src/test/java/io/github/monty/leetcode/backtracking/ID90SubsetsIITest.java)
