---
title: "Convert Sorted Array to Binary Search Tree"
difficulty: Easy
topics: [Binary Tree, Array, Divide and Conquer, Tree, Binary Search Tree]
category: 10-trees
order: 36
source: [Grind75, Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-03-20
date_updated: 2026-08-10
---

## 心得

使用 divide-and-conquer DFS：每次選區間中點當 root，左半段遞迴建左子樹，右半段遞迴建右子樹。中點分割讓兩邊的節點數盡量接近，因此建立出的 BST 高度平衡。

時間是 O(n)，每個元素只建立一個節點。遞迴呼叫堆疊是 O(log n)，因為樹高度平衡；若把回傳的輸出樹也算進空間，則是 O(n)。本次提交顯示 runtime percentile 100%，這只代表這次提交的結果。

## Java

```java
class Solution {

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] nums, int start, int end) {
        if (start > end) return null;

        // Pick the middle value as the root.
        int mid = start + ((end - start) >> 1);

        // Build balanced left and right subtrees.
        TreeNode left = dfs(nums, start, mid - 1);
        TreeNode right = dfs(nums, mid + 1, end);

        return new TreeNode(nums[mid], left, right);
    }

}
```

---

## 2025 初刷版（Day39，2025-05-26）

*原文見 [archive/doc/daily/day39-2025-05-26.md](../../archive/doc/daily/day39-2025-05-26.md)，已停更，內容按當時所寫原樣搬入*

### LC108. Convert Sorted Array to Binary Search Tree

#### 題目說明
- 給定一個**遞增排序**的整數陣列 `nums`
- 請將其轉換為一棵**高度平衡的二元搜尋樹（BST）**
  - 高度平衡：每個節點的左右子樹高度差最多為 1

---

#### 解法：Divide & Conquer（遞迴建樹）

##### 思路
- 每次取陣列中間元素作為根節點
- 左半段遞迴建立左子樹、右半段遞迴建立右子樹
- 遞迴直到子區間為空

##### 重點：
- 中間元素作為根，天然保證平衡
- 為了平衡，可選 `(start + end) / 2` 或 `(start + end + 1) / 2` 作為根，效果相近

##### 複雜度分析
- 時間：O(n)，每個元素都造訪一次
- 空間：O(log n)，遞迴堆疊深度（平均）；最壞為 O(n)

---

#### Java 程式碼連結
- 題目實作：[ID108ConvertSortedArrayToBinarySearchTree.java](../../archive/src/main/java/io/github/monty/leetcode/binarytree/ID108ConvertSortedArrayToBinarySearchTree.java)
- 單元測試：[ID108ConvertSortedArrayToBinarySearchTreeTest.java](../../archive/src/test/java/io/github/monty/leetcode/binarytree/ID108ConvertSortedArrayToBinarySearchTreeTest.java)
