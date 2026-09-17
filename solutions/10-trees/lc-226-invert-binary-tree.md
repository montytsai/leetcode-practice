---
title: "Invert Binary Tree"
difficulty: Easy
topics: [Binary Tree, Tree, Depth-First Search, Breadth-First Search]
category: 10-trees
order: 13
source: [Grind75, Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-03-19
date_updated: 2026-07-14
---

[LeetCode 題目連結](https://leetcode.com/problems/invert-binary-tree/)

---

## 2025 初刷版（Day22，2025-05-09）

*原文見 [archive/doc/daily/day22-2025-05-09.md](../../archive/doc/daily/day22-2025-05-09.md)，已停更，內容按當時所寫原樣搬入*

### LC226. Invert Binary Tree

#### 題目說明

給定一個二元樹，請將它的左右子樹進行交換，並回傳根節點。

---

#### 解法一：BFS（使用 Stack 迭代）

##### 思路
- 使用 Stack 模擬 DFS 的前序遍歷順序。
- 每次拜訪節點時即交換左右子樹。
- 將右節點與左節點依序放入 Stack，以確保左節點先處理。

##### 複雜度分析
- 時間複雜度：O(n)，每個節點拜訪一次。
- 空間複雜度：O(n)，最壞情況 Stack 儲存所有節點。

---

#### 解法二：DFS 遞迴

##### 思路
- 後序處理：先遞迴處理左右子樹，再交換左右子節點。
- 遞迴的回傳值作為新的左右子樹連接到當前節點。

##### 複雜度分析
- 時間複雜度：O(n)，每個節點拜訪一次。
- 空間複雜度：O(h)，h 為樹的高度（遞迴深度）。

---

#### Java 程式碼連結

- 題目實作：[ID226InvertBinaryTree.java](../../archive/src/main/java/io/github/monty/leetcode/binarytree/ID226InvertBinaryTree.java)
- 單元測試：[ID226InvertBinaryTreeTest.java](../../archive/src/test/java/io/github/monty/leetcode/binarytree/ID226InvertBinaryTreeTest.java)
