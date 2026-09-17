---
title: "Path Sum"
difficulty: Easy
topics: [Binary Tree, Tree, Depth-First Search, Breadth-First Search]
category: 10-trees
order: 21
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2025-05-12
date_updated: 2025-05-12
---


## 2025 初刷版（Day25，2025-05-12）

*原文見 [archive/doc/daily/day25-2025-05-12.md](../../archive/doc/daily/day25-2025-05-12.md)，已停更，內容按當時所寫原樣搬入*

### LC112. Path Sum

#### 題目說明
- 給定一棵二元樹和一個整數 `targetSum`。
- 判斷是否存在一條從 root 到 leaf 的路徑，其路徑總和等於 `targetSum`。
- Leaf 節點指沒有子節點的節點。

---

#### 解法一：DFS（遞減 targetSum）

##### 思路
- 每次走訪節點時，就從 `targetSum` 中扣除當前節點值。
  - 使用減法，可避免需要額外參數記錄累積值。
- 當遇到葉節點時，檢查目前 targetSum 是否為 0。

##### 複雜度分析
- Time Complexity: O(n)，每個節點最多被訪問一次
- Space Complexity: O(h)，h 為樹高，遞迴堆疊深度

##### 複雜度分析
- Time Complexity: O(n)
- Space Complexity: O(h)

---

#### Java 程式碼連結
- 題目實作：[ID112PathSum.java](../../archive/src/main/java/io/github/monty/leetcode/binarytree/ID112PathSum.java)
- 單元測試：[ID112PathSumTest.java](../../archive/src/test/java/io/github/monty/leetcode/binarytree/ID112PathSumTest.java)
