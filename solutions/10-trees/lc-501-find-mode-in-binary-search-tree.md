---
title: "Find Mode in Binary Search Tree"
difficulty: Easy
topics: [Binary Tree, Tree, Depth-First Search, Binary Search Tree]
category: 10-trees
order: 30
source: [Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2025-05-15
date_updated: 2025-05-15
---


## 2025 初刷版（Day28，2025-05-15）

*原文見 [archive/doc/daily/day28-2025-05-15.md](../../archive/doc/daily/day28-2025-05-15.md)，已停更，內容按當時所寫原樣搬入*

### LC501. Find Mode in Binary Search Tree

#### 題目說明
- 給定一棵 BST（二元搜尋樹）。
- 找出所有出現頻率最高的節點值（可能不只一個）。
- 題目保證答案唯一時回傳陣列；若有多個值，順序不限。

---

#### 解法一：中序遍歷 + 模擬頻率統計（O(1) 空間）

##### 思路：
- 利用 BST 的中序遍歷特性（值會是遞增的）來判斷連續節點是否相同。
- 使用 `prev` 記錄前一個節點值。
- 若與 `curr.val` 相同，累加頻率 `curFreq++`；否則重設為 1。
- 若 `curFreq > maxFreq`，更新最大頻率並清空 result。
- 若相等，加入 result。

##### 重點：
- 中序遍歷標準寫法。
    ```
    Deque<TreeNode> stack = new ArrayDeque<>();
    TreeNode curr = root;

    while (!stack.isEmpty() || curr != null) {
        ......
    }
    ```
- 空間 O(h)：不使用 HashMap，僅用棧記錄節點順序。

##### 複雜度分析：
- 時間：O(n)
- 空間：O(h)，h 為樹高

---

#### 解法二：遞迴 + HashMap 統計

##### 思路：
- 使用遞迴 DFS 遍歷整棵樹。
- 用 Map 統計每個值出現次數。
- 最後找出最大值，過濾所有符合條件的值。

##### 筆記：

###### Java 函式好用
- 找到 map 的 value 最大值
    ```java
    int maxValue = Collections.max(map.values());
    ```
- 把 List 轉成 int[]
    ```java
    int[] arr = list.stream().mapToInt(Integer::intValue).toArray();
    ```

##### 複雜度分析：
- 時間：O(n)
- 空間：O(n)，Map 空間

---

#### 解法比較

| 解法                   | 時間複雜度 | 空間複雜度 | 適用情境          |
|----------------------|-------|-------|---------------|
| 解法一：中序迭代 + 模擬        | O(n)  | O(h)  | 記憶體限制嚴格時使用    |
| 解法二：前序遞迴 + Map 統計    | O(n)  | O(n)  | 快速實作，邏輯直觀     |
| 解法三：中序遞迴 + 全域變數（未實作） | O(n)  | O(h)  | 把解法一改成遞迴，寫法簡潔 |      

---

#### Java 程式碼連結
- 題目實作：[ID501FindModeInBinarySearchTree.java](../../archive/src/main/java/io/github/monty/leetcode/binarytree/ID501FindModeInBinarySearchTree.java)
- 單元測試：[ID501FindModeInBinarySearchTreeTest.java](../../archive/src/test/java/io/github/monty/leetcode/binarytree/ID501FindModeInBinarySearchTreeTest.java)
