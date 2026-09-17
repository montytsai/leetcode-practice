---
title: "Convert BST to Greater Tree"
difficulty: Medium
topics: [Binary Tree, Tree, Depth-First Search, Binary Search Tree]
category: 10-trees
order: 37
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2025-05-26
date_updated: 2025-05-26
---


## 2025 初刷版（Day39，2025-05-26）

*原文見 [archive/doc/daily/day39-2025-05-26.md](../../archive/doc/daily/day39-2025-05-26.md)，已停更，內容按當時所寫原樣搬入*

### LC538. Convert BST to Greater Tree

#### 題目說明
- 給定一棵 **二元搜尋樹（BST）**。
- 將其轉換成一棵「Greater Tree」。
  - 每個節點值等於 **原本值 + 所有大於它節點的值總和**。
- 保證輸入為合法 BST。

---

#### 解法一：Iterative（使用 Stack 進行反向中序遍歷）

##### 思路
- 採用「右 → 中 → 左」的反向中序遍歷。
- 使用變數 pre 記錄目前累加的總和。
- 每訪問一個節點：
  - 將其值加上 pre
  - 更新 pre 為新的節點值

##### 複雜度分析
- 時間複雜度：O(n)
- 空間複雜度：O(h)，其中 h 為樹高

---

#### 解法二：Recursive（遞迴 DFS）

##### 思路
- 與解法一邏輯相同，改採遞迴方式實作。
- 使用方法內的**可變參照變數** `int[] sum` 來記錄累加總和。
- 每次遞迴處理節點時，更新 `sum[0]` 並同步更新節點值。

##### 重點
- 使用 `int[] sum = new int[1]` 建立可變容器，達到模擬傳參考效果。
- `sum[0]` 在遞迴過程中持續更新，避免使用類別層級變數，有利封裝與測試。

####### 為何使用 int[]

| 類型        | 是否可被遞迴方法修改（模擬 by reference）     |
|-----------|---------------------------------|
| `int`     | ❌ 不行，primitive 是傳值              |
| `Integer` | ❌ 不行，雖是物件，但是 **immutable**（不可變） |
| `int[]`   | ✅ 可以，因為是物件，可變內容                 |

##### 複雜度分析
- 時間：O(n)
- 空間：O(h)

---

#### 解法比較

| 解法  | 實作方式           | 複雜度 (時間/空間) | 優點         | 缺點               |
|-----|----------------|-------------|------------|------------------|
| 解法一 | Stack 模擬反向中序遍歷 | O(n) / O(h) | 無遞迴限制，可讀性佳 | 稍需額外空間           |
| 解法二 | 遞迴 DFS         | O(n) / O(h) | 程式短、直觀     | Stack 深度受限，需注意溢位 |

---

#### Java 程式碼連結
- 題目實作：[ID538ConvertBstToGreaterTree.java](../../archive/src/main/java/io/github/monty/leetcode/binarytree/ID538ConvertBstToGreaterTree.java)
- 單元測試：[ID538ConvertBstToGreaterTreeTest.java](../../archive/src/test/java/io/github/monty/leetcode/binarytree/ID538ConvertBstToGreaterTreeTest.java)
