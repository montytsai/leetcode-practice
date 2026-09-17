---
title: "Find Bottom Left Tree Value"
difficulty: Medium
topics: [Binary Tree, Tree, Depth-First Search, Breadth-First Search]
category: 10-trees
order: 20
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2025-05-12
date_updated: 2025-05-12
---


## 2025 初刷版（Day25，2025-05-12）

*原文見 [archive/doc/daily/day25-2025-05-12.md](../../archive/doc/daily/day25-2025-05-12.md)，已停更，內容按當時所寫原樣搬入*

### LC513. Find Bottom Left Tree Value

#### 題目說明
- 給定一個二元樹，請找出其**最底層最左邊節點的值**。
- 樹中節點值皆為整數且非空。

---

#### 解法：BFS（層序遍歷）

##### 思路
- 使用 BFS（Queue）層序遍歷整棵樹。
- 每當處理一層時，將當層第一個節點（queue.peek()）紀錄為目前的最左值。
- 最終離開 BFS 時，`leftValue` 就是最底層最左邊節點。

##### 複雜度分析
- 時間複雜度：`O(n)`，n 為節點數。
- 空間複雜度：`O(w)`，w 為最寬層節點數。

---

#### Java 程式碼連結
- 題目實作：[ID513FindBottomLeftTreeValue.java](../../archive/src/main/java/io/github/monty/leetcode/binarytree/ID513FindBottomLeftTreeValue.java)
- 單元測試：[ID513FindBottomLeftTreeValueTest.java](../../archive/src/test/java/io/github/monty/leetcode/binarytree/ID513FindBottomLeftTreeValueTest.java)
