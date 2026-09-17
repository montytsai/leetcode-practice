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

---

## 2025 初刷版（Day22，2025-05-09）

*原文見 [archive/doc/daily/day22-2025-05-09.md](../../archive/doc/daily/day22-2025-05-09.md)，已停更，內容按當時所寫原樣搬入*

### LC101. Symmetric Tree

#### 題目說明

- 給定一個二元樹，檢查其是否為自身的鏡像（即對稱）。
- 範例：
  ```
        1
     /     \ 
    2       2
   / \     / \ 
  3   4   4   3
  ```
- 輸出：true

---

#### 解法一：DFS 遞迴

##### 思路
- 使用遞迴函數同時比對左子樹與右子樹是否為鏡像。
- 比較規則：
  - 空節點也要比對（null vs null 為對稱）。
  - 值需相等
  - 結構需對稱：左.left vs 右.right、左.right vs 右.left
- 子節點入隊順序非常關鍵：左樹為 left → right，右樹為 right → left。

##### 複雜度分析
- Time Complexity: O(n)
- Space Complexity: O(h)，h 為樹高（最壞為 O(n)）

---

#### 解法二：BFS 單佇列

##### 思路
- 使用單個佇列儲存節點，每次加入**成對**節點（left, right），因此每次可以成對拿出要比較的節點。
- 每次出列比對值是否相同，並按**鏡像順序**加入下一層子節點。
  - 鏡像順序：最左側 → 最右側 → 第二左左側 → 第二右右側 → ...
- 注意 `Queue` 應使用 `LinkedList` 而非 `ArrayDeque`，因為會加入 null 值並比較

##### 複雜度分析
- Time Complexity: O(n)
- Space Complexity: O(n)

---

#### 解法三： BFS 解法 + 兩個 Queue

##### 思路
- 使用兩個 Queue，從左子樹與右子樹開始同步廣度優先走訪。
- 每次比對兩個節點的值是否相同，同時確保其子節點結構為鏡像（左對右，右對左）。

##### 重點
- 空節點也要比對（null vs null 為對稱）。
- 子節點入隊順序非常關鍵：左樹為 left → right，右樹為 right → left。
- BFS 有助於同步左右子樹每層結構比對，避免遞迴爆棧風險。

##### 複雜度分析
- **時間複雜度**：O(n)，每個節點都走訪一次。
- **空間複雜度**：O(n)，Queue 最多同時保存一整層節點。

---

#### Java 程式碼連結

- 題目實作：[ID101SymmetricTree.java](../../archive/src/main/java/io/github/monty/leetcode/binarytree/ID101SymmetricTree.java)
- 單元測試：[ID101SymmetricTreeTest.java](../../archive/src/test/java/io/github/monty/leetcode/binarytree/ID101SymmetricTreeTest.java)
