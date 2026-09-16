---
title: "Binary Tree Level Order Traversal"
difficulty: Medium
topics: [Binary Tree, Tree, Breadth-First Search]
category: 10-trees
order: 4
source: [Grind75, Carl]
platform: LeetCode
url: https://leetcode.com/problems/binary-tree-level-order-traversal/
status: ac-assisted
note: ""
date_created: 2026-03-16
date_updated: 2026-08-21
---

# 102. Binary Tree Level Order Traversal

## 題目說明

- 給定一棵 binary tree，回傳每一層節點值所組成的清單，順序由上到下、同層由左到右。
- `root == null` 時回傳空清單；節點總數最多 2,000。

## 心得

迭代秒解；遞迴雖然看答案才想起來，但很快就理解，證明最近刻意用迭代解樹題已經形成可直接取用的 BFS 模式。

---

## 解法一：Iterative BFS with Level Size

### Intuition

Level order traversal 最直接的工具是 queue。最近刻意用迭代解樹題，這次已能立即想到：每輪先保存當下的 `queue.size()`，它就是目前這一層的節點數；只處理這些節點，過程中加入的 child 留給下一輪。

### Approach

1. `root == null` 時直接回傳空結果。
2. 把 root 放進 queue。
3. 每次外層迴圈開始時保存 `size = queue.size()`，這是目前這層的邊界。
4. 取出恰好 `size` 個節點，把值加入本層清單，並把非 null child 放入 queue。
5. 本層處理完後加入結果。

Invariant：每次外層迴圈開始時，queue 內恰好是尚未處理的目前層節點；迴圈結束時，queue 內恰好是下一層節點。

### Complexity

**Time complexity: `O(n)`**

`n` 是節點數；每個節點只會 enqueue、dequeue 各一次。

**Space complexity: `O(w)`**

`w` 是樹的最大寬度；queue 最多同時保存一層附近的節點。最壞情況為 `O(n)`。結果清單不計入額外空間。

### Code

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

/**
 * 102. Binary Tree Level Order Traversal
 * Time Complexity: O(n)
 * Space Complexity: O(w), O(n) in the worst case
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            while (size-- > 0) {
                TreeNode node = queue.poll();
                level.add(node.val);

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            res.add(level);
        }

        return res;
    }
}
```

### Code Review

- **Learning provenance**：`self-solved`；我表示迭代解法「秒解」，可獨立重現。
- **Correctness / invariant**：`size` 在處理本層前固定，因此本輪不會誤處理剛加入的下一層 child；由左到右 enqueue 也保證同層輸出順序。
- **Strength**：解法和 level order 的定義直接對應；`root == null` guard 清楚；使用 `ArrayDeque` 避免 `LinkedList` 的額外節點成本。
- **Bug**：無。
- **Trade-off**：寬樹的 queue 可能同時保存 `O(w)` 個節點，這是 iterative BFS 的必要代價。
- **Style**：巢狀迴圈短且責任清楚，沒有需要為了形式而抽 helper。
- **Edge cases**：空樹已處理；單一節點與只有單側 child 的樹也會正確分層。

---

## 解法二：Recursive DFS Grouped by Depth

### Intuition

看過答案後想起來：不一定要真的逐層走訪，只要 DFS 時把 `depth` 一起帶下去，就能把每個節點放進對應的層。當 `depth == res.size()`，代表第一次抵達新的一層，先建立該層清單。

### Approach

1. 從 root 與 depth `0` 開始遞迴。
2. null node 直接返回。
3. 若 `depth == res.size()`，建立這一層的清單。
4. 把目前節點值加入 `res.get(depth)`。
5. 先走 left，再走 right，並把 depth 加一。

Invariant：處理 depth `d` 的節點前，`res` 已經包含 `0..d-1` 層；第一次到達第 `d` 層時只建立一次清單。Preorder 的 left-before-right 順序也會讓同層節點保持由左到右。

### Complexity

**Time complexity: `O(n)`**

每個節點只處理一次。

**Space complexity: `O(h)`**

`h` 是樹高，來自 recursion call stack；平衡樹為 `O(log n)`，偏斜樹最壞為 `O(n)`。結果清單不計入額外空間。

### Code

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

/**
 * 102. Binary Tree Level Order Traversal
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        bfs(root, 0, res);
        return res;
    }

    private void bfs(TreeNode node, int depth, List<List<Integer>> res) {
        if (node == null)
            return;

        if (depth == res.size()) {
            res.add(new ArrayList<>());
        }

        res.get(depth).add(node.val);
        bfs(node.left, depth + 1, res);
        bfs(node.right, depth + 1, res);
    }
}
```

### Code Review

- **Learning provenance**：`reference-assisted`；我看答案後想起遞迴做法並很快理解。能否不看答案獨立重現：未確認。
- **Correctness / invariant**：`depth == res.size()` 只會在第一次抵達新層時成立；每個節點依 depth 進入唯一清單。left-before-right DFS 保證同層順序。
- **Strength**：用 depth 直接對應結果索引，程式短且沒有額外 queue；在平衡樹上 call stack 只有 `O(log n)`。
- **Bug**：無。
- **Trade-off**：極深的偏斜樹會使用 `O(n)` call stack，Java 有 stack overflow 風險；iterative 版本沒有這個風險。
- **Style**：helper 名稱 `bfs` 不符合實際走訪方式，因為程式執行的是 DFS。建議改成 `dfs` 或 `collectByDepth`；這是命名問題，不影響正確性。
- **Edge cases**：空樹由第一個 null guard 處理；第一次抵達每一層時都會建立清單。

---

## 解法比較

| 解法 | Time | Space | 優點 | Trade-off | 使用時機 |
| --- | --- | --- | --- | --- | --- |
| Iterative BFS | `O(n)` | `O(w)` | 和逐層走訪語意一致，沒有 recursion stack 風險 | 寬樹的 queue 可能很大 | 面試預設主解、真正需要逐層處理時 |
| Recursive DFS by depth | `O(n)` | `O(h)` | 寫法精簡，平衡樹的 call stack 較小 | 偏斜樹可能 stack overflow，較不直觀 | 想展示 depth 分桶觀念時 |

### Optimality

兩個解法在**時間複雜度**上都是最佳的 `O(n)`，因為輸出包含每個節點，任何正確解法都至少要讀取每個節點一次。額外空間沒有單一解法在所有樹形都勝出：平衡寬樹通常是 recursive 的 `O(h)` 較小，偏斜樹則是 iterative 的 `O(w)` 較小且更安全。以**可讀性與面試價值**判斷，iterative BFS 是主解；recursive DFS 是有學習價值的替代法，不需要再增加第三種解法。
