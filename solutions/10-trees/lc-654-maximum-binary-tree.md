---
title: "Maximum Binary Tree"
difficulty: Medium
topics: [Binary Tree, Array, Divide and Conquer, Stack, Tree, Monotonic Stack]
category: 10-trees
order: 25
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2025-05-13
date_updated: 2025-05-13
---


## 2025 初刷版（Day26，2025-05-13）

*原文見 [archive/doc/daily/day26-2025-05-13.md](../../archive/doc/daily/day26-2025-05-13.md)，已停更，內容按當時所寫原樣搬入*

### LC654. Maximum Binary Tree

#### 題目說明
給定一個不含重複值的整數陣列 `nums`，根據以下規則構建最大二元樹：
1. 樹的根節點為陣列中的最大元素。
2. 左子樹由最大值左側子陣列構建（遞迴）。
3. 右子樹由最大值右側子陣列構建（遞迴）。

返回構建好的最大二元樹。

---

#### 解法一：遞迴分治

##### 思路
1. 在 `nums[start..end]` 中遍歷尋找最大值的索引 `idx`。
2. `nums[idx]` 作為當前子樹的根節點。
3. 遞迴構建左、右子樹。

##### 重點
- **分治框架**：切分子陣列後重複相同邏輯。
- **線性搜尋**：每層都需一次 O(n) 搜尋最大值。

##### 複雜度分析
- 時間：最壞 O(n²)，平均情況受資料分佈影響。
- 空間：O(n)，遞迴深度最壞情況為 n。

---

#### 解法二：單調棧法（O(n)） 
*最優解，先存未來看

##### 思路
1. 使用「遞減單調棧」維護棧內節點值單調遞減。
2. 遍歷 `nums`，對於當前元素：
    - 若比棧頂元素大，則不斷彈棧，直到遇到比自己大或棧空。
    - 彈出的元素最後一個成為當前元素的左子節點。
    - 若棧未空，棧頂元素成為當前元素的右子節點。
    - 將當前元素入棧。
3. 最後棧底元素即為根。

##### 複雜度分析
- 時間：O(n)
- 空間：O(n) 用於棧

---

#### 解法比較

| 方法   | 時間    | 空間   | 優點     | 缺點       |
|------|-------|------|--------|----------|
| 分治遞迴 | O(n²) | O(n) | 簡單直觀   | 最壞情況效能較差 |
| 單調棧  | O(n)  | O(n) | 最佳時間效率 | 理解與實作較複雜 |

---

#### Java 程式碼連結

- 實作檔案：[ID654MaximumBinaryTree.java](../../archive/src/main/java/io/github/monty/leetcode/binarytree/ID654MaximumBinaryTree.java)
- 單元測試：[ID654MaximumBinaryTreeTest.java](../../archive/src/test/java/io/github/monty/leetcode/binarytree/ID654MaximumBinaryTreeTest.java)
