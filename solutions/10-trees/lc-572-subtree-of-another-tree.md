---
title: "Subtree of Another Tree"
difficulty: Easy
topics: [Binary Tree, Tree, Depth-First Search, String Matching, Hash Function]
category: 10-trees
order: 45
source: [Grind75]
platform: LeetCode
url: https://leetcode.com/problems/subtree-of-another-tree/
status: ac-unknown
note: ""
date_created: 2026-08-18
date_updated: 2026-08-18
---

# 572. Subtree of Another Tree

## 題目說明

- 判斷 `subRoot` 是否完整出現在 `root` 中；完整相同包含節點值與左右子樹結構。
- `root` 與 `subRoot` 都非空；相同根值只代表可能匹配，仍需比較整棵子樹。

## 心得

保留兩個自己寫到 AC 的搜尋方式：BFS 用 queue 走訪候選根，遞迴 DFS 直接搜尋當前、左、右子樹；兩者共用 `isSameTree`，比較途中遇到第一個差異就停止。

---

## 解法一：BFS 搜尋候選根 + 遞迴比較

### Intuition

先用 BFS 走訪 `root` 的每個節點，把每個節點當成 `subRoot` 的候選根。對每個候選呼叫 `isSameTree`，只有值與左右結構全部相同才算找到。

這版不需要先把樹序列化成 list，也不需要為每個候選建立新資料；比較一發現不同便能提早回傳 `false`。

### Approach

1. 把 `root` 放入 queue。
2. 每次取出一個候選節點，以 `isSameTree` 比較它與 `subRoot`。
3. 若完整相同就回傳 `true`；否則把非空的左右子節點加入 queue。
4. queue 清空仍未匹配時回傳 `false`。

`isSameTree` 的 invariant 是：只有目前兩個節點的值相同，而且左右子樹也分別相同，這兩棵樹才相同。

### Complexity

**Time complexity: `O(n * m)`**

令 `n` 為 `root` 的節點數、`m` 為 `subRoot` 的節點數。最壞情況下，`n` 個候選都可能各比較最多 `m` 個節點。

**Space complexity: `O(w_root + h_sub)`**

外層 BFS queue 最多保存 `root` 某一層的寬度 `w_root`；`isSameTree` 的遞迴 stack 最深為 `subRoot` 高度 `h_sub`。

### Code

```java
/**
 * 572. Subtree of Another Tree
 * Time Complexity: O(n * m)
 * Space Complexity: O(w_root + h_sub)
 */
class Solution {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (isSameTree(node, subRoot)) return true;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

        return false;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
```

### Code Review

- **Learning provenance**：`hint-assisted`。使用 LC 100 Same Tree 的提示後，自己改寫比較方法並 AC；能否完全不看提示重現未確認。
- **Correctness / invariant**：BFS 會檢查每個可能的候選根；`isSameTree` 同時驗證值與結構，沒有正確性 bug。
- **Strength**：不再為每個候選建立含 `null` 的序列化 list；比較能在第一個差異處短路停止。
- **Trade-off**：寬樹的 queue 可能較大。題目保證 `root` 非空；若方法離開題目條件重用，`ArrayDeque.offer(null)` 會拋出例外。
- **Style**：`isSameTree` 是 helper，改成 `private` 會更精確，但目前 `public` 不影響正確性。

---

## 解法二：遞迴 DFS 搜尋候選根 + 遞迴比較

### Intuition

對每個節點只有三種可能：目前節點就是相同子樹、答案在左子樹、答案在右子樹。因此可以直接用遞迴表達搜尋，不需要額外 queue。

### Approach

1. `root == null` 代表這條搜尋路徑已沒有候選，回傳 `false`。
2. 先用 `isSameTree(root, subRoot)` 檢查目前節點。
3. 目前不匹配時，遞迴搜尋左子樹或右子樹。

外層 `||` 會在任一分支找到答案時短路；內層 `&&` 會在任一對應子樹不同時短路。

### Complexity

**Time complexity: `O(n * m)`**

令 `n` 為 `root` 的節點數、`m` 為 `subRoot` 的節點數。最壞情況下，每個候選根都可能觸發最多 `m` 個節點的完整比較。

**Space complexity: `O(h_root + h_sub)`**

外層搜尋的 call stack 最深為 `root` 高度 `h_root`；比較時還可能使用最多 `h_sub` 層的遞迴 stack。

### Code

```java
/**
 * 572. Subtree of Another Tree
 * Time Complexity: O(n * m)
 * Space Complexity: O(h_root + h_sub)
 */
class Solution {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;
        if (isSameTree(root, subRoot)) return true;
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
```

### Code Review

- **Learning provenance**：這個搜尋版本的來源未另外說明；能否獨立重現未確認。
- **Correctness / invariant**：每次遞迴都先檢查目前候選，再完整涵蓋左右子樹，因此不會漏掉任何可能根；沒有正確性 bug。
- **Strength**：程式直接對應「目前、左、右」三種可能，短而清楚；兩層短路都能避免不必要工作。
- **Trade-off**：退化成 linked list 形狀的深樹會使用較深的 Java call stack，存在 stack overflow 風險。
- **Style**：helper 使用 `private`，可見性與用途一致。

---

## 解法比較

| 解法 | Time | Space | 優點 | Trade-off | 使用時機 |
| --- | --- | --- | --- | --- | --- |
| BFS + 遞迴比較 | `O(n * m)` | `O(w_root + h_sub)` | 外層不增加搜尋遞迴深度；候選走訪明確 | 寬樹的 queue 可能較大 | 想避免對 `root` 做深層遞迴時 |
| 遞迴 DFS + 遞迴比較 | `O(n * m)` | `O(h_root + h_sub)` | 最精簡，直接表達遞迴樹結構 | 深退化樹可能 stack overflow | 一般面試與樹高可控時 |

### Optimality

以可讀性與面試價值為指標，兩版都清楚，DFS 版更精簡；以額外空間為指標，選擇取決於樹的寬度與高度。

以最壞時間複雜度為指標，`O(n * m)` 不是漸進最佳。若要進一步降低，需要讓不同候選重用已計算的結構資訊；依這次「不要給最佳答案」的要求，不展開替代解法。
