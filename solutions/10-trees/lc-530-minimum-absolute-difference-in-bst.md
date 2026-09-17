---
title: "Minimum Absolute Difference in BST"
difficulty: Easy
topics: [Binary Tree, Tree, Depth-First Search, Breadth-First Search, Binary Search Tree]
category: 10-trees
order: 29
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-03-20
date_updated: 2026-03-20
---

BST裡面比較，要記得是跟整棵樹，不只當前與左右而已。用prev來紀錄前節點。

---

## 2025 初刷版（Day28，2025-05-15）

*原文見 [archive/doc/daily/day28-2025-05-15.md](../../archive/doc/daily/day28-2025-05-15.md)，已停更，內容按當時所寫原樣搬入*

### LC530. Minimum Absolute Difference in BST

#### 題目說明
- 給定一棵二元搜尋樹（BST），節點值皆為非負整數。
- 求出任意兩個節點值差的絕對值的最小值。

---

#### 解法：中序遍歷 + 記錄前一個節點

##### 思路：
- 中序遍歷 BST，節點值會以遞增順序排列。
- 記錄前一個節點值 `prev`，每次與當前節點值比較，更新 `minDiff`。
- 可提早剪枝（當差值為 1 時即為最小）。

##### 重點：
- 避免比較所有組合，利用 BST 性質只比較中序相鄰節點。
- 中序遍歷順序：左 -> 中 -> 右

##### 複雜度分析
- 時間複雜度：O(n)
- 空間複雜度：O(h)，h 為樹高

---

#### Java 程式碼連結
- 題目實作：[ID530MinimumAbsoluteDifferenceInBST.java](../../archive/src/main/java/io/github/monty/leetcode/binarytree/ID530MinimumAbsoluteDifferenceInBST.java)
- 單元測試：[ID530MinimumAbsoluteDifferenceInBSTTest.java](../../archive/src/test/java/io/github/monty/leetcode/binarytree/ID530MinimumAbsoluteDifferenceInBSTTest.java)
