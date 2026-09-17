---
title: "Delete Node in a BST"
difficulty: Medium
topics: [Binary Tree, Tree, Binary Search Tree]
category: 10-trees
order: 34
source: [Carl, LeetCode75]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-03-19
date_updated: 2026-03-19
---

想不到遞迴怎寫，「遞迴不是一步到位，是相信遞迴函式會完成。」將子樹直接相信遞迴。
刪除時根據子節點不同情況可以簡化（葉子等）。
更改節點，記得先改了再指。

---

## 2025 初刷版（Day34，2025-05-21）

*原文見 [archive/doc/daily/day34-2025-05-21.md](../../archive/doc/daily/day34-2025-05-21.md)，已停更，內容按當時所寫原樣搬入*

### LC450. Delete Node in a BST

#### 題目說明

- 給定一棵二元搜尋樹（BST），請刪除指定數值的節點，並保持整棵樹仍是合法 BST。
- 若找不到該節點，返回原樹。
- 若刪除節點有子節點，需正確調整子樹結構。

---

#### 解法：搬移子樹（左子樹接到右子樹最小值）

##### 思路

1. 遞迴遍歷樹找目標值。
2. 若未找到，繼續遞迴往左右子樹找。
3. 找到後依據以下狀況刪除：
    - 左右子節點皆為空：return null。
    - 左空右不空：return 右子節點。
    - 右空左不空：return 左子節點。
    - 左右皆不空：找到右子樹最小值，將左子樹接到該節點左邊，並回傳 root.right 作為新根節點。

##### 重點

- `minRight.left == null` 是必要前提，才能安全掛上原左子樹。
- 回傳 `root.right` 時，外層會自動接回回傳結果，不需額外指標維護。

##### 複雜度分析

- 時間複雜度：O(h)，h 為樹高，最壞 O(n)，最好 O(log n)
- 空間複雜度：O(h)，為遞迴棧的深度

---

#### 解法比較

| 解法              | 邏輯簡單 | 不需換值    | 複雜度  | 傳統寫法   |
|-----------------|------|---------|------|--------|
| 右子樹接替 + 左子樹掛右最小 | ✅    | ✅       | O(h) | ❌（非傳統） |
| 標準換值法（右子樹最小值複製） | ✅    | ❌（需刪兩次） | O(h) | ✅      |

---

#### Java 程式碼連結

- 題目實作：[ID450DeleteNodeInABST.java](../../archive/src/main/java/io/github/monty/leetcode/binarytree/ID450DeleteNodeInABST.java)
- 單元測試：[ID450DeleteNodeInABSTTest.java](../../archive/src/test/java/io/github/monty/leetcode/binarytree/ID450DeleteNodeInABSTTest.java)
