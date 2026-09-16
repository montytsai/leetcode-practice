---
title: "Symmetric Tree"
difficulty: Easy
topics: [Binary Tree, Tree, Depth-First Search, Breadth-First Search]
category: 10-trees
order: 14
source: [Grind75, Carl]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-03-19
date_updated: 2026-08-10
---

## 心得

重新練習 BFS，沒看舊解也能獨立寫出。這次用兩個 `Deque` 從兩端成對取節點，比較鏡像位置；寫法和舊筆記不同，也不需要先記錄每層的 size。

## Java

```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        Deque<TreeNode> curr = new LinkedList<>();
        curr.addFirst(root.left);
        curr.addLast(root.right);

        while (!curr.isEmpty()) {
            Deque<TreeNode> next = new LinkedList<>();

            while (!curr.isEmpty()) {
                TreeNode left = curr.removeFirst();
                TreeNode right = curr.removeLast();

                // Both empty nodes are symmetric.
                if (left == null && right == null) {
                    continue;
                }
                if (left == null || right == null || left.val != right.val) {
                    return false;
                }

                // Add children in mirror order for the next level.
                next.addFirst(left.right);
                next.addFirst(left.left);
                next.addLast(right.left);
                next.addLast(right.right);
            }

            curr = next;
        }

        return true;
    }
}
```
