---
title: "Lowest Common Ancestor of a Binary Search Tree"
difficulty: Medium
topics: [Binary Tree, Tree, Depth-First Search, Binary Search Tree]
category: 10-trees
order: 32
source: [Grind75, Carl]
platform: LeetCode
status: ac-assisted
note: ""
date_created: 2025-05-20
date_updated: 2026-07-17
---

## 複習紀錄

- **2026-07-16 重刷**:忘記解法,一開始想用 DFS(左右子樹任一找到就回傳),後來看答案才想起可以利用 BST 的大小關係——p.val > q.val 時交換、沿路比較 root.val 與 [p,q] 區間,落在區間內即為答案,不必真的遞迴找兩邊。複雜度 O(H) space O(1)。

---

## 2025 初刷版（Day33，2025-05-20）

*原文見 [archive/doc/daily/day33-2025-05-20.md](../../archive/doc/daily/day33-2025-05-20.md)，已停更，內容按當時所寫原樣搬入*

### LC235. Lowest Common Ancestor of a Binary Search Tree

#### 題目說明

- 給定一個二元搜尋樹（BST）及其中的兩個節點 `p`, `q`。
- 找出這兩個節點的最小共同祖先（Lowest Common Ancestor, LCA）。
- 最小共同祖先定義為：同時為 `p`, `q` 的祖先，且是最深的那一個。

---

#### 解法一：迴圈版（二分搜尋思路）

##### 思路

- 利用 BST 中序遞增特性，若找到某節點值介於 p 與 q 之間，必為最小祖先。
  - 若 `root.val > q.val`，代表兩節點皆在左子樹。
  - 若 `root.val < p.val`，代表兩節點皆在右子樹。
  - 否則：p <= root <= q，`root` 即為 LCA。
- 先交換 p, q 順序，使 p 在左、q 在右，簡化邏輯。

##### 複雜度分析

- 時間複雜度：O(h)，最壞為 O(n)
- 空間複雜度：O(1)，無遞迴堆疊

---

#### 解法二：遞迴版

##### 思路

- 同上，將條件改寫為遞迴處理。遞迴每次 stack 加一，空間複雜度較差。

##### 重點

- 若未命中 LCA 條件，遞迴到對應的左右子樹。

##### 複雜度分析

- 時間複雜度：O(h)
- 空間複雜度：O(h)，，遞迴 stack 最多 h 層

---

#### 解法比較

| 解法  | 方法  | 時間複雜度 | 空間複雜度 | 備註          |
|-----|-----|-------|-------|-------------|
| 解法一 | 迴圈版 | O(h)  | O(1)  | 無遞迴堆疊，較節省空間 |
| 解法二 | 遞迴版 | O(h)  | O(h)  | 邏輯清楚、易實作    |

---

#### Java 程式碼連結

- 題目實作：[ID235LowestCommonAncestorOfABinarySearchTree.java](../../archive/src/main/java/io/github/monty/leetcode/binarytree/ID235LowestCommonAncestorOfABinarySearchTree.java)
- 單元測試：[ID235LowestCommonAncestorOfABinarySearchTreeTest.java](../../archive/src/test/java/io/github/monty/leetcode/binarytree/ID235LowestCommonAncestorOfABinarySearchTreeTest.java)
