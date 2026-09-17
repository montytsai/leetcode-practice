---
title: "Search in a Binary Search Tree"
difficulty: Easy
topics: [Binary Tree, Tree, Binary Search Tree]
category: 10-trees
order: 27
source: [Carl, LeetCode75]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-03-19
date_updated: 2026-03-19
---

[LeetCode 題目連結](https://leetcode.com/problems/search-in-a-binary-search-tree/description/)

---

## 2025 初刷版（Day27，2025-05-14）

*原文見 [archive/doc/daily/day27-2025-05-14.md](../../archive/doc/daily/day27-2025-05-14.md)，已停更，內容按當時所寫原樣搬入*

### LC700. Search in a Binary Search Tree

#### 題目說明
- 給定一棵二元搜尋樹 (BST) 的根節點 `root` 和一個整數 `val`
- 返回值等於 `val` 的節點所代表的子樹根節點；若找不到，則回傳 null

---

#### 解法：迴圈搜尋（Iterative BST Traversal）

##### 思路
- 根據 BST 性質：若目標值小於節點，往左子樹搜尋；大於則往右子樹
- 若等於則直接回傳該節點，代表已找到子樹根

##### 重點
- 使用迴圈，節省 call stack

##### 複雜度分析
- 時間複雜度：O(h)，h 為樹高，最壞為 O(n)
- 空間複雜度：O(1)

---

#### 解法比較
| 解法        | 優點           | 缺點            | 適用情境         |
|-----------|--------------|---------------|--------------|
| 迴圈搜尋      | 節省空間，結構簡單    | 可讀性稍差，不支援回朔追蹤 | 非平衡樹、大型節點數   |
| 遞迴搜尋（未實作） | 可保留搜尋路徑，較易閱讀 | call stack 增加 | 體積小、偏重邏輯推演用途 |

---

#### Java 程式碼連結
- 題目實作：[ID700SearchInABinarySearchTree.java](../../archive/src/main/java/io/github/monty/leetcode/binarytree/ID700SearchInABinarySearchTree.java)
- 單元測試：[ID700SearchInABinarySearchTreeTest.java](../../archive/src/test/java/io/github/monty/leetcode/binarytree/ID700SearchInABinarySearchTreeTest.java)
