---
title: "Balanced Binary Tree"
difficulty: Easy
topics: [Binary Tree, Tree, Depth-First Search]
category: 10-trees
order: 17
source: [Grind75, Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-03-19
date_updated: 2026-07-19
---

提前剪枝可以用-1

[LeetCode 題目連結](https://leetcode.com/problems/balanced-binary-tree/)

---

## 2025 初刷版（Day25，2025-05-12）

*原文見 [archive/doc/daily/day25-2025-05-12.md](../../archive/doc/daily/day25-2025-05-12.md)，已停更，內容按當時所寫原樣搬入*

### LC110. Balanced Binary Tree

#### 題目說明
- 給定一棵二元樹，判斷其是否為「高度平衡」的樹。
- 高度平衡定義：每個節點的左右子樹高度差不得超過 1。

---

#### 解法一：DFS 後序遞迴 + 高度剪枝

##### 思路
- 採後序遞迴，先計算左右子樹高度。
- 若發現任一子樹已不平衡（回傳 -1），立即終止遞迴。
- 否則回傳節點的高度（左右最大 + 1）。

##### 重點
- 後序遍歷是關鍵（需先知道左右子樹的高度）
- 高度差 > 1 就剪枝回傳 -1

##### 複雜度分析
- Time: O(n)，每個節點最多訪問一次
- Space: O(h)，遞迴深度為樹高，最壞為 O(n)

---

#### Java 程式碼連結
- 題目實作：[ID110BalancedBinaryTree.java](../../archive/src/main/java/io/github/monty/leetcode/binarytree/ID110BalancedBinaryTree.java)
- 單元測試：[ID110BalancedBinaryTreeTest.java](../../archive/src/test/java/io/github/monty/leetcode/binarytree/ID110BalancedBinaryTreeTest.java)
