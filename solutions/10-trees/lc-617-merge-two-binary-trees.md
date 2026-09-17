---
title: "Merge Two Binary Trees"
difficulty: Easy
topics: [Binary Tree, Tree, Depth-First Search, Breadth-First Search]
category: 10-trees
order: 26
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2025-05-14
date_updated: 2025-05-14
---


## 2025 初刷版（Day27，2025-05-14）

*原文見 [archive/doc/daily/day27-2025-05-14.md](../../archive/doc/daily/day27-2025-05-14.md)，已停更，內容按當時所寫原樣搬入*

### LC617. Merge Two Binary Trees

#### 題目說明
給定兩棵二元樹 `root1` 與 `root2`，請合併它們為一棵新樹：

- 若兩個節點都存在，節點值相加作為新節點值。
- 若只有一個節點存在，則該節點直接作為新節點。

回傳合併後的新樹根節點。

---

#### 解法：DFS 遞迴合併

##### 思路
- 自頂向下遞迴合併兩棵樹。
- 若兩節點都為非空，則節點值相加，並遞迴左右子樹。
- 若任一節點為 null，直接回傳另一節點。

##### 重點
- 修改的是 `root1` 節點，節省記憶體。
- 保持簡潔遞迴，處理 null 情況即可。

##### 複雜度分析
- **時間複雜度**：O(n)，n 為節點總數，兩樹遍歷一次。
- **空間複雜度**：O(h)，h 為樹高，遞迴堆疊所需空間。

---

#### Java 程式碼連結

- 題目實作：[ID617MergeTwoBinaryTrees.java](../../archive/src/main/java/io/github/monty/leetcode/binarytree/ID617MergeTwoBinaryTrees.java)
- 單元測試：[ID617MergeTwoBinaryTreesTest.java](../../archive/src/test/java/io/github/monty/leetcode/binarytree/ID617MergeTwoBinaryTreesTest.java)
