---
title: "Path Sum II"
difficulty: Medium
topics: [Binary Tree, Backtracking, Tree, Depth-First Search]
category: 10-trees
order: 22
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2025-05-12
date_updated: 2025-05-12
---


## 2025 初刷版（Day25，2025-05-12）

*原文見 [archive/doc/daily/day25-2025-05-12.md](../../archive/doc/daily/day25-2025-05-12.md)，已停更，內容按當時所寫原樣搬入*

### LC113. Path Sum II

#### 題目說明
給定一棵二元樹 `root` 和一個整數 `targetSum`，請找出所有從根節點到**葉節點**的路徑，使得這條路徑上節點值的總和等於 `targetSum`。

- 每條路徑都必須從根節點開始，到葉節點結束。
- 每個節點只能出現一次。

---

#### 解法一：DFS 遞迴 + 回溯

##### 思路

- 使用深度優先搜尋（DFS）從根節點往下遍歷每一條可能的路徑，同時維護目前走過的節點（即當前路徑 `path`）與剩餘目標值 `targetSum`。
- 當走到葉節點且剩餘目標值剛好為 0 時，將這條路徑加入結果清單中。

##### 重點
- 使用一個共用的 List<Integer> path 保存目前走的路徑。
- 為了避免重複或錯誤結果，加入結果集使用複製版本，避免參考同一物件
  - `res.add(new LinkedList<>(path));`
- 為了避免重複或錯誤結果，需要進行**回溯**操作，即在遞迴結束時移除當前節點。

###### 回溯操作

####### ❓ 為什麼回溯時只要移除一個節點？
每次遞迴只加入一個節點： `path.add(node.val);`  
因此只需要：`path.remove(path.size() - 1);`還原現場，不需要移除多個節點或重建 path，這就是「回溯一步」。

#######  ❓ 為什麼葉節點也需要回溯？
即使是正確答案的路徑，仍然要回溯：
- 因為 path 是共用變數，不還原的話下一個遞迴會錯誤地包含上一條的內容。
- 葉節點只是其中一種遞迴出口，也必須「restore state」。

##### 複雜度分析
- 時間複雜度：O(n²)，最壞情況下每條路徑都符合要求，每次複製路徑需 O(n) 時間。
- 空間複雜度：O(n)，最深的遞迴深度為樹的高度 n。

---

#### Java 程式碼連結

- 題目實作：[ID113PathSumII.java](../../archive/src/main/java/io/github/monty/leetcode/binarytree/ID113PathSumII.java)
- 單元測試：[ID113PathSumIITest.java](../../archive/src/test/java/io/github/monty/leetcode/binarytree/ID113PathSumIITest.java)
