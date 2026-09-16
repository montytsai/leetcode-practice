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
