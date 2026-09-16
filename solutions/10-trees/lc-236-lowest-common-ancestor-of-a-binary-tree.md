---
title: "Lowest Common Ancestor of a Binary Tree"
difficulty: Medium
topics: [Binary Tree, Tree, Depth-First Search]
category: 10-trees
order: 31
source: [Carl, LeetCode75]
platform: LeetCode
status: ac-assisted
note: ""
date_created: 2026-03-16
date_updated: 2026-09-05
---

# 236. Lowest Common Ancestor of a Binary Tree

## 題目說明

- 給一棵二元樹的 `root`，以及樹中確實存在的兩個節點 `p`、`q`，回傳 `p`、`q` 的最近共同祖先(LCA)。
- 一個節點也可以是它自己的祖先(例如 p 是 q 的祖先時，LCA 就是 p)。

## 心得

這題也是寫第三次，每次遇到都卡住。問了 AI 提示：如果左子樹和右子樹都回傳了非空值(代表 p 和 q 分別落在當前節點的兩側)，那當前節點扮演什麼角色？我才自己寫出來。

---

## 解法一：後序遍歷，讓每個節點回傳「自己子樹裡找到的目標」

### Intuition

LCA 問題的核心是每個節點要能回答一句話：「p、q 在我的子樹裡找到了誰？」用後序遍歷(先處理左右子樹，再處理自己)剛好符合這個資訊由下往上匯聚的方向。當一個節點本身就是 p 或 q，就直接回傳自己──就算它底下還藏著另一個目標，它自己也已經是候選的 LCA(對應「其中一個節點是另一個節點的祖先」的情況)。

真正的判斷點在於：當左子樹回傳非空、右子樹也回傳非空，代表 p 和 q 分別落在目前節點的兩側，此時目前節點就是它們的最近共同祖先，直接回傳自己；若只有一邊非空，代表兩個目標(或其中一個)都在同一側，把那一側的結果原封不動往上傳，讓上層節點繼續判斷。

這題卡在同一個地方三次：明明知道要用遞迴、要判斷左右，但「左右都非空時，當前節點要回傳誰、為什麼」這句話沒想清楚。這次靠外部提示把問題重新框成「當前節點扮演什麼角色」才想通──遞迴回傳的不是「找到的節點」，而是「以目前子樹範圍能給出的最佳答案」；在還沒同時看到兩側訊號之前，這個答案只能是「目前看到的那個目標」，一旦兩側訊號齊了，答案就升級成「目前節點自己」。

### Approach

1. Base case：若 `root` 為 `null`，或 `root` 本身就是 `p` 或 `q`，直接回傳 `root`。
2. 遞迴呼叫左子樹、右子樹，個別取得該側子樹回傳的結果。
3. 若左右兩側都回傳非 `null`，代表 `p`、`q` 分別在兩側，目前節點就是 LCA，回傳 `root`。
4. 否則兩者中只有一側非 `null`(或都是 `null`)，回傳非 `null` 的那一側(若兩側皆 `null` 則回傳 `null`)，把答案往上傳遞。

### Complexity

**Time complexity: `O(n)`**

`n` 是節點數，每個節點最多被拜訪一次。

**Space complexity: `O(h)`**

`h` 是樹高，即 call stack 深度上限；退化成鏈狀樹時最壞為 `O(n)`。

### Code

```java
/**
 * 236. Lowest Common Ancestor of a Binary Tree
 * Time Complexity: O(n)
 * Space Complexity: O(h), h = tree height (O(n) worst case)
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // If root is null, or root is one of the targets, it is the best answer this subtree can give.
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // Both sides found a target: p and q are on different sides, so root is the LCA.
        if (left != null && right != null) return root;
        // Only one side found something (or neither); pass that result up.
        return left != null ? left : right;
    }
}
```

### Code Review

- **Learning provenance**：`hint-assisted`；拿到「左右都非空時，當前節點扮演什麼角色」的提示後自己寫出程式碼。能否不靠提示獨立重現：未確認(同一題第三次卡在同一個地方，尚未觀察到不靠提示也能寫出來)。
- **Correctness / invariant**：`root == p || root == q` 提早回傳是正確性關鍵──即使某節點是另一節點的祖先，這個 early return 讓它能繼續往上被判定為 LCA；`left != null && right != null` 只在 p、q 分居兩側時成立，此時回傳 root 正確。
- **Strength**：程式碼精簡，沒有多餘的旗標或狀態變數，遞迴回傳值本身就承載了所有需要的資訊。
- **Bug**：無。
- **Trade-off**：遞迴解法在極度不平衡(退化成鏈狀)的樹上，call stack 深度會到 `O(n)`，有 stack overflow 風險；題目未給樹高上限時要留意。
- **Edge cases**：p 是 q 的祖先(或反之)、p 或 q 就是 root 本身、p 和 q 分別在最左與最右葉節點，這三種都被目前的 base case 與左右非空判斷正確涵蓋。

---

## 解法比較

只有一個解法，略。

### Optimality

以時間衡量，`O(n)` 是必要下界，因為最壞情況下 p、q 可能分別藏在樹的兩個角落，必須拜訪過所有節點才能確定。以空間衡量，遞迴版是 `O(h)`，多數情況優於下面替代法固定的 `O(n)`。這個解法在時間與(平均)空間兩個指標上都已經是最佳。

替代法(不同 trade-off)：用一個 HashMap 記錄從 root 走訪時每個節點的 parent(先 BFS 或 DFS 建出 parent map)，再從 p 往上收集所有祖先存進一個 Set，最後從 q 往上走，第一個出現在該 Set 裡的節點就是 LCA。這個做法空間固定是 `O(n)`(parent map + visited set)，比遞迴版費空間，但邏輯是「兩條鏈找交點」，對還沒建立起「後序回傳值代表什麼」這層抽象直覺的人來說更直觀。之後想換個角度鞏固這題，可以練這個版本。

## 相關

- [Binary Tree](../../topics/T10-21-binary-tree.md) ── 後序回傳、由當前節點角色判斷 LCA 的範例
- [Depth-First Search](../../topics/T10-12-depth-first-search.md)
- [LeetCode 刷題總覽](../../_moc.md)
