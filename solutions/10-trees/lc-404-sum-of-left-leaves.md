---
title: "Sum of Left Leaves"
difficulty: Easy
topics: [Binary Tree, Tree, Depth-First Search, Breadth-First Search]
category: 10-trees
order: 19
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2025-05-10
date_updated: 2025-05-10
---


## 2025 初刷版（Day25，2025-05-12）

*原文見 [archive/doc/daily/day25-2025-05-12.md](../../archive/doc/daily/day25-2025-05-12.md)，已停更，內容按當時所寫原樣搬入*

### LC404. Sum of Left Leaves

#### 題目說明

- 給定一棵二元樹，請回傳 **所有左葉節點的值總和**。
- 左葉節點定義為：是其父節點的 **左子節點**，而且沒有子節點。

---

#### 解法一：DFS（Stack 迭代）

##### 思路
- 使用 Stack 來模擬前序遍歷
- 每次遇到 `node.left` 時，判斷是否為葉節點（`left.left == null && left.right == null`）
- 若是，將該值加總；若不是則繼續遞迴推入
- `node.right` 則無論是否為葉節點，都要推入（因為其子節點中可能有左葉）

##### 複雜度分析
- 時間複雜度：O(n)
- 空間複雜度：O(h)，h 為樹高
- 
---

#### Java 程式碼連結
- 題目實作：[ID404SumOfLeftLeaves.java](../../archive/src/main/java/io/github/monty/leetcode/binarytree/ID404SumOfLeftLeaves.java)
- 單元測試：[ID404SumOfLeftLeavesTest.java](../../archive/src/test/java/io/github/monty/leetcode/binarytree/ID404SumOfLeftLeavesTest.java)
