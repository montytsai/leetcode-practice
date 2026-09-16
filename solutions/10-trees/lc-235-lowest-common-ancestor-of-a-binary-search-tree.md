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
