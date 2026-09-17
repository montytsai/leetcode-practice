---
title: "Count Complete Tree Nodes"
difficulty: Medium
topics: [Binary Tree, Binary Search, Bit Manipulation, Tree]
category: 10-trees
order: 16
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-03-21
date_updated: 2026-03-21
---

把完全二元樹切開成左右都是完全二元樹，用深度來決定遞迴左右子樹更優雅

[LeetCode 題目連結](https://leetcode.com/problems/count-complete-tree-nodes)

---

## 2025 初刷版（Day23，2025-05-10）

*原文見 [archive/doc/daily/day23-2025-05-10.md](../../archive/doc/daily/day23-2025-05-10.md)，已停更，內容按當時所寫原樣搬入*

### LC222. Count Complete Tree Nodes（完全二元樹的節點個數）

#### 題目說明

- 給定一棵完全二元樹，請計算其節點總數。
- 完全二元樹定義：除了最底層之外，每層節點都是滿的，且最底層節點從左至右排列。

---

#### 解法一：DFS 遞迴（後序遍歷）

##### 思路
- 遞迴遍歷左、右子樹，最後加上根節點。

##### 複雜度

- 時間複雜度：O(n)
- 空間複雜度：O(h)，h 為樹的高度

---

#### 解法二：DFS 遞迴（前序遍歷）

##### 思路
- 先處理根節點，再遞迴遍歷左、右子樹。
- 解法一的改寫，將遞迴封裝在另一個方法中。

##### 複雜度
- 時間複雜度：O(n)
- 空間複雜度：O(h)

---

#### 解法三：BFS 層序遍歷

##### 思路
- 使用隊列逐層遍歷節點，計算節點數。

##### 複雜度
- 時間複雜度：O(n)
- 空間複雜度：O(n)

---

#### 解法四：利用完全二元樹特性（最優解）

##### 思路
- 計算左、右子樹的深度。
- 若相同，表示該子樹為滿二元樹，節點數為 2^depth - 1。
- 若不同，遞迴計算左右子樹的節點數，加上根節點。

##### 筆記

###### 右移運算子（right shift） `>>`

- 語法：`a >> b`　（a 向右移 b 位）
- 等同於：把 a 除以 2 的 b 次方（捨去小數）

###### 左移運算子（right shift） `<<`

- 語法：`1 << h` （1 向左移 h 位）
- 等同於：2 的 h 次方

###### 位元 vs 數學

| 位元運算     | 數學意義      | 等價寫法（可讀性高）                |
|----------|-----------|---------------------------|
| `a >> 1` | `a / 2`   | `a / 2`                   |
| `a >> b` | `a / 2^b` | `a / (int)Math.pow(2, b)` |
| `1 << b` | `2^b`     | `(int)Math.pow(2, b)`     |

##### 複雜度
- 時間複雜度：`O(log² n)`
  - 每次遞迴都要計算左右子樹深度（`log n`），最多遞迴 `log n` 次（每層只遞迴一邊）。
  - 所以：`log n`（遞迴層數） × `log n`（每層計算深度） = `O(log² n)`
- 空間複雜度：`O(log n)`，遞迴棧深度

---

#### 解法比較

| 解法                        | 核心思想             | 優點                                                | 缺點                                  | 適用情境            |
|---------------------------|------------------|---------------------------------------------------|-------------------------------------|-----------------|
| 1. DFS（後序）<br/>2. DFS（前序） | 遞迴先算左右子樹，再 +1    | - 簡潔直觀<br>- 易於理解與實作                               | - O(n) 時間複雜度，在樹大時效率差                | 練習遞迴、簡單測資       |
| 3. BFS（層序）                | 用佇列逐層掃描所有節點      | - 避免遞迴爆棧風險<br>- 可以邊遍歷邊處理邏輯                        | - 需要額外 queue 空間<br>- 同樣是 O(n) 時間複雜度 | 節點不多、想用迴圈實作時    |
| 4. 完全二元樹特性                | 判斷左右深度，滿樹直接計算節點數 | ✅ **最優時間複雜度 O(log² n)**<br>✅ 精準利用「完全二元樹」特性，加快節點統計 | ⚠ 稍微難理解<br>⚠ 寫法需注意邊界（深度加 1 的邏輯要清楚）  | 樹大、資料量大時，追求效率優先 |

##### 選擇建議（用什麼解法？）

- 小題或先練習 → 解法一、二：邏輯直觀，便於學習。
- 避免遞迴／棧爆 → 解法三：BFS 可避免深度過深時 StackOverflow。
- 大資料、要求效能 → 解法四：利用「完全二元樹」特性，最小化遍歷成本。

---

#### Java 程式碼連結

- 題目實作：[ID222CountCompleteTreeNodes.java](../../archive/src/main/java/io/github/monty/leetcode/binarytree/ID222CountCompleteTreeNodes.java)
- 單元測試：[ID222CountCompleteTreeNodesTest.java](../../archive/src/test/java/io/github/monty/leetcode/binarytree/ID222CountCompleteTreeNodesTest.java)
