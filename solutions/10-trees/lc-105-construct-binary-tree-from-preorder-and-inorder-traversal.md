---
title: "Construct Binary Tree from Preorder and Inorder Traversal"
difficulty: Medium
topics: [Binary Tree, Array, Hash Table, Divide and Conquer, Tree]
category: 10-trees
order: 24
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-03-19
date_updated: 2026-03-19
---

[LeetCode 題目連結](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)

---

## 2025 初刷版（Day26，2025-05-13）

*原文見 [archive/doc/daily/day26-2025-05-13.md](../../archive/doc/daily/day26-2025-05-13.md)，已停更，內容按當時所寫原樣搬入*

### LC105. Construct Binary Tree from Preorder and Inorder Traversal

#### 題目說明
給定一個二元樹的前序遍歷（preorder）與中序遍歷（inorder）結果，還原這棵樹。

---

#### 解法一：Divide and Conquer（遞迴）

##### 思路
- 邏輯同 LC106
- 前序遍歷的第一個元素永遠是當前子樹的根節點。
- 根據該值在中序遍歷中的 index，可以知道左子樹與右子樹的大小與位置。
- 遞迴對左右子樹進行相同處理即可。

##### 重點
- 使用 HashMap 儲存中序值與 index 的對應，以加速查找。
- 子樹分界不需實際切 array，只需傳入索引範圍即可避免額外記憶體開銷。
- 遞迴方式，若遇到極端情況（如單邊樹），需留意 stack overflow。

##### 複雜度分析
- 時間複雜度：O(n)，每個節點只處理一次。
- 空間複雜度：O(n)，HashMap 及遞迴堆疊空間。

---

#### Java 程式碼連結
- 題目實作：[ID105ConstructBinaryTreeFromPreorderAndInorderTraversal.java](../../archive/src/main/java/io/github/monty/leetcode/binarytree/ID105ConstructBinaryTreeFromPreorderAndInorderTraversal.java)
- 單元測試：[ID105ConstructBinaryTreeFromPreorderAndInorderTraversalTest.java](../../archive/src/test/java/io/github/monty/leetcode/binarytree/ID105ConstructBinaryTreeFromPreorderAndInorderTraversalTest.java)
