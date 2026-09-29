---
title: "Construct Binary Tree from Preorder and Inorder Traversal"
difficulty: Medium
topics: [Binary Tree, Array, Hash Table, Divide and Conquer, Tree]
category: 10-trees
order: 24
source: [Carl]
platform: LeetCode
url: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
status: ac-solo
note: ""
date_created: 2026-03-19
date_updated: 2026-09-24
---

# 105. Construct Binary Tree from Preorder and Inorder Traversal

## 題目說明

- 給定二元樹的 preorder 與 inorder 走訪結果，重建整棵二元樹並回傳 root。
- 節點值不重複；長度介於 1 到 3000。

## 心得

慢慢推導寫出來，可能面試會寫太久

---

## 解法一：Divide and Conquer（前序定根、中序切割）

### Intuition

preorder 的第一個元素永遠是目前子樹的 root：整段序列是先訪問 root 再依序展開左右子樹，第一筆就是還沒展開任何東西時看到的節點。inorder 則相反，root 出現在它自己那組左右子樹之間，因此 root 在 inorder 裡的 index 把當前範圍切成左子樹與右子樹兩段，兩段的大小可以直接算出來。

這題和 LC106（inorder + postorder）用的是同一個手法，差別只在 anchor 節點在序列裡的位置：postorder 的 anchor 在尾端、preorder 的 anchor 在頭端，找到 anchor 之後切割 inorder 的邏輯完全一樣。

用 HashMap 把 inorder 的值對應到 index，把「root 在 inorder 裡的位置」從線性掃描降到 O(1) 查找。

### Approach

1. 用 `inIndexByValMap` 把 `inorder` 的值對應到 index，之後查 root 位置用查表取代掃描。
2. `dfs(preS, preE, inS, inE)` 遞迴建樹：四個邊界中只要有一組區間反轉（`preS > preE` 或 `inS > inE`）就代表這段是空子樹，回傳 `null`。
3. 當前子樹的 root 值是 `preorder[preS]`；查表拿到它在 inorder 的位置 `leftMid`，`leftSize = leftMid - inS` 就是左子樹的節點數。
4. 左子樹遞迴處理 `preorder[preS+1, preS+leftSize]` 與 `inorder[inS, leftMid-1]`。
5. 右子樹遞迴處理 `preorder[preS+1+leftSize, preE]` 與 `inorder[leftMid+1, inE]`。

### Complexity

**Time complexity: `O(n)`**

`n` 為節點數。建 `inIndexByValMap` 花 `O(n)`；之後每個節點在 `dfs` 裡只處理一次，查 root 在 inorder 的位置是 `O(1)`。

**Space complexity: `O(n)`**

`inIndexByValMap` 占用 `O(n)`；遞迴呼叫堆疊在樹傾斜成單邊鏈狀時最深可達 `O(n)`，平衡樹則是 `O(log n)`。

### Code

```java
/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Worked example trace (hand-derived before coding):
 *                   [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]
 * preorder root>left>right = [1, 2,4,8,9,5,10,11, 3,6,12,13,7,14,15]
 * inorder  left>root>right = [8,4,9, 2, 10,5,11, 1, 12,6,13, 3, 14,7,15]
 *
 * pre[0,14], in[0,14], size=15 -> mid: 1 pre[0]->in[7]  -> left: in[0,7)->size7->pre(0,7], right: in(7,14]->size7->pre(0+left7+1, ...)
 * pre[1,7],  in[0,6], size=7  -> mid: 2 pre[1]->in[3]  -> left: in[0,3)->size3->pre(1,4], right: in(3,6]
 * pre[2,4],  in[0,2], size=3  -> mid: 4 pre[2]->in[1]  -> left: in[0,1), right: in(1,2]
 * pre[8,14], in[8,14], size=7  -> mid: 3 pre[8]->in[11] -> left: in[8,11), right: in(11,14]
 * pre[12,14],in[12,14], size=3  -> mid: 7 pre[12]->in[13] -> left: in[12,13), right: in(13,14]
 */
class Solution {

    private Map<Integer, Integer> inIndexByValMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inIndexByValMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inIndexByValMap.put(inorder[i], i);
        }

        return dfs(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    private TreeNode dfs(int[] preorder, int preS, int preE,
            int[] inorder, int inS, int inE) {
        if (preS < 0 || preE >= preorder.length || preS > preE
          || inS < 0 || inE >= inorder.length || inS > inE) {
            return null;
        }

        int val = preorder[preS];
        TreeNode node = new TreeNode(val);

        int leftMid = inIndexByValMap.get(val);
        int leftSize = leftMid - inS;

        node.left = dfs(preorder, preS + 1, preS + 1 + leftSize - 1,
                        inorder, inS, leftMid - 1);

        node.right = dfs(preorder, preS + 1 + leftSize, preE,
                         inorder, leftMid + 1, inE);

        return node;
    }

}
```

程式碼規則：完整包在 `java` code fence 裡；Header 含題號、題名、Time/Space Complexity；所有註解 B1 English；保留 AC 的實際邏輯，只整理格式與翻譯推導軌跡的中文標籤。

### Code Review

- **Learning provenance**：`ac-solo`，使用者自述是逐步手動推導寫出來（程式碼開頭保留了完整的手算軌跡）。
- **Correctness / invariant**：`preS/preE` 與 `inS/inE` 兩組區間的長度全程保持相等（由 `leftSize` 的算法保證），左右子樹的索引切分與這個 invariant 對齊，邏輯正確。
- **Strength**：開頭手寫的推導軌跡把遞迴前先手算一輪範例的過程完整留下，之後回來複習能直接重建當時怎麼想的，這是很值得保留的習慣。
- **Bug**：無。
- **Trade-off**：`dfs` 的四個防呆條件（`preS<0`、`preE>=length`、`inS<0`、`inE>=length`）裡有三個在目前的遞迴呼叫方式下永遠不會成立——`preS`、`inS` 只會遞增，`preE`、`inE` 只會遞減或不變，四個都是由合法範圍推出來的合法範圍。真正會觸發的只有 `preS>preE`（等價於 `inS>inE`，因為兩段區間長度全程相等，只留一個就夠）。這連帶讓 `inorder` 這個參數在 `dfs` 內其實只被拿來做這些防呆用的 `.length`，它的元素值從未被讀取（root 在 inorder 裡的位置是查 `inIndexByValMap`，不是掃 `inorder`）——可以只傳 `inorder.length`，甚至拿掉這個參數。
- **Style**：`inIndexByValMap` 依賴 inorder 值不重複；題目保證這點成立，這裡不算風險，換到不保證唯一值的變形題才需要處理。

---

## Optimality

以時間複雜度為指標，這是最優解：每個節點都要被建立與訪問一次，不可能有漸進更快的做法；`HashMap` 把找 root 在 inorder 位置的成本降到 `O(1)`，避免了不用查表時的線性掃描讓整體退化成 `O(n^2)`。

面試手速的做法（學習價值，非效能提升）：把 `preS/preE` 這組索引整個拿掉，改用一個 instance 欄位 `preIdx` 記錄目前走到 preorder 的第幾個元素，每次進 `dfs` 就讀走並 `+1`；因為 preorder 本身就是「root、完整左子樹、完整右子樹」依序排列，只要遞迴呼叫順序正確（先建左子樹、再建右子樹），`preIdx` 自然會照順序吃完該吃的範圍，`dfs` 只需要維護 inorder 的 `inS`、`inE` 兩個邊界。指標數量少一半，是這題常見的縮短寫法，對心得裡提到的「面試會寫太久」這個顧慮直接有幫助。

## 相關

- Binary Tree — 前序定根、中序切割兩段子樹，遞迴重建二元樹
- Array — 用區間索引切分 preorder/inorder，不實際切陣列
- Hash Table — 用 `Map<Integer, Integer>` 把值對應到 inorder index，查找降到 O(1)
- Divide and Conquer — 每層把問題拆成左右兩個較小子問題，遞迴後直接組合成一棵子樹
- Tree — 從走訪序列反推樹的結構
- [LeetCode 刷題總覽](../../_moc.md)
