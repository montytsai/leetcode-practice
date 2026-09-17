---
title: "Binary Tree Paths"
difficulty: Easy
topics: [Binary Tree, String, Backtracking, Tree, Depth-First Search]
category: 10-trees
order: 18
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-03-19
date_updated: 2026-03-19
---

[LeetCode 題目連結](https://leetcode.com/problems/binary-tree-paths/)

---

## 2025 初刷版（Day25，2025-05-12）

*原文見 [archive/doc/daily/day25-2025-05-12.md](../../archive/doc/daily/day25-2025-05-12.md)，已停更，內容按當時所寫原樣搬入*

### LC257. Binary Tree Paths

#### 題目說明
- 給定一個二元樹，請找出所有從根節點到葉節點的路徑。
- 每條路徑需以 `->` 連接節點數值，並以字串表示。
  - 例如： 
  - 輸入：[1,2,3,null,5]
  - 輸出：["1->2->5","1->3"]

---

#### 解法一：DFS 遞迴（使用 StringBuilder + 回溯）

##### 思路
- 使用前序遍歷方式（根 -> 左 -> 右）遍歷整棵樹。
- 每次遇到節點就將其值加入路徑字串中，若為葉節點就將整條路徑加入結果。
- 使用 `StringBuilder` 優化字串拼接效能，並使用回溯方式避免記憶體污染。

##### 重點
- 葉節點定義為左右子節點皆為 null 的節點。
- `StringBuilder` 共用記憶體空間並還原狀態 (`path.setLength(len)`) 是效能優化關鍵。

###### String vs StringBuilder 解法比較

| 方法                   | 空間複雜度  | 優點       | 缺點             |
|----------------------|--------|----------|----------------|
| String 相加            | O(n^2) | 寫法直觀     | 每次都建立新字串，浪費記憶體 |
| StringBuilder + 回溯 ✅ | O(h)   | 效能佳、記憶體低 | 稍需掌握回溯邏輯       |

##### 複雜度分析
- 時間複雜度：O(n)，每個節點拜訪一次。
- 空間複雜度：O(h)，h 為樹的高度，為遞迴棧深度。

---

#### Java 程式碼連結
- 題目實作：[ID257BinaryTreePaths.java](../../archive/src/main/java/io/github/monty/leetcode/binarytree/ID257BinaryTreePaths.java)
- 單元測試：[ID257BinaryTreePathsTest.java](../../archive/src/test/java/io/github/monty/leetcode/binarytree/ID257BinaryTreePathsTest.java)
