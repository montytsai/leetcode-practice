# Binary Tree

## 解題技巧

以節點關係決定走訪與比較順序；LC 101 的 BFS 可從雙端維持鏡像節點配對，不需要先記錄每層 size。只需判斷兩棵樹是否相同時，應優先思考能否配對比較並在第一個差異停止，避免先物化完整序列。

LC 102 有兩個可重用模式：iterative BFS 在每輪開始保存 `queue.size()` 作為層邊界；recursive DFS 攜帶 `depth`，當 `depth == res.size()` 時建立新層，再把節點放進對應索引。前者最符合逐層走訪語意，後者在平衡樹只需 `O(h)` call stack。

LC 236(最近共同祖先)是後序遍歷的另一種用法：每個節點的遞迴回傳值代表「以我為根的子樹裡，目前找到的最佳答案」。節點本身是 p 或 q 就回傳自己；左右子樹都回傳非空，代表 p、q 分居兩側，當前節點就是答案，回傳自己；只有一側非空就原封不動往上傳。卡點通常在「左右都非空時，當前節點要回傳誰」這一步，把問題框成「當前節點在這個時刻扮演什麼角色」比死記步驟更容易想通。

## 已刷題目

- [100. Same Tree](../solutions/10-trees/lc-100-same-tree.md) Easy
- [101. Symmetric Tree](../solutions/10-trees/lc-101-symmetric-tree.md) Easy
- [102. Binary Tree Level Order Traversal](../solutions/10-trees/lc-102-binary-tree-level-order-traversal.md) Medium
- [104. Maximum Depth of Binary Tree](../solutions/10-trees/lc-104-maximum-depth-of-binary-tree.md) Easy
- [105. Construct Binary Tree from Preorder and Inorder Traversal](../solutions/10-trees/lc-105-construct-binary-tree-from-preorder-and-inorder-traversal.md) Medium
- [106. Construct Binary Tree from Inorder and Postorder Traversal](../solutions/10-trees/lc-106-construct-binary-tree-from-inorder-and-postorder-traversal.md) Medium
- [107. Binary Tree Level Order Traversal II](../solutions/10-trees/lc-107-binary-tree-level-order-traversal-ii.md) Medium
- [108. Convert Sorted Array to Binary Search Tree](../solutions/10-trees/lc-108-convert-sorted-array-to-binary-search-tree.md) Easy
- [110. Balanced Binary Tree](../solutions/10-trees/lc-110-balanced-binary-tree.md) Easy
- [111. Minimum Depth of Binary Tree](../solutions/10-trees/lc-111-minimum-depth-of-binary-tree.md) Easy
- [112. Path Sum](../solutions/10-trees/lc-112-path-sum.md) Easy
- [113. Path Sum II](../solutions/10-trees/lc-113-path-sum-ii.md) Medium
- [116. Populating Next Right Pointers in Each Node](../solutions/08-binary-search/lc-116-populating-next-right-pointers-in-each-node.md) Medium
- [1161. Maximum Level Sum of a Binary Tree](../solutions/10-trees/lc-1161-maximum-level-sum-of-a-binary-tree.md) Medium
- [117. Populating Next Right Pointers in Each Node II](../solutions/10-trees/lc-117-populating-next-right-pointers-in-each-node-ii.md) Medium
- [1372. Longest ZigZag Path in a Binary Tree](../solutions/10-trees/lc-1372-longest-zigzag-path-in-a-binary-tree.md) Medium
- [144. Binary Tree Preorder Traversal](../solutions/10-trees/lc-144-binary-tree-preorder-traversal.md) Easy
- [1448. Count Good Nodes in Binary Tree](../solutions/10-trees/lc-1448-count-good-nodes-in-binary-tree.md) Medium
- [145. Binary Tree Postorder Traversal](../solutions/10-trees/lc-145-binary-tree-postorder-traversal.md) Easy
- [199. Binary Tree Right Side View](../solutions/10-trees/lc-199-binary-tree-right-side-view.md) Medium
- [222. Count Complete Tree Nodes](../solutions/10-trees/lc-222-count-complete-tree-nodes.md) Medium
- [226. Invert Binary Tree](../solutions/10-trees/lc-226-invert-binary-tree.md) Easy
- [235. Lowest Common Ancestor of a Binary Search Tree](../solutions/10-trees/lc-235-lowest-common-ancestor-of-a-binary-search-tree.md) Medium
- [236. Lowest Common Ancestor of a Binary Tree](../solutions/10-trees/lc-236-lowest-common-ancestor-of-a-binary-tree.md) Medium
- [257. Binary Tree Paths](../solutions/10-trees/lc-257-binary-tree-paths.md) Easy
- [404. Sum of Left Leaves](../solutions/10-trees/lc-404-sum-of-left-leaves.md) Easy
- [429. N-ary Tree Level Order Traversal](../solutions/10-trees/lc-429-n-ary-tree-level-order-traversal.md) Medium
- [437. Path Sum III](../solutions/10-trees/lc-437-path-sum-iii.md) Medium
- [450. Delete Node in a BST](../solutions/10-trees/lc-450-delete-node-in-a-bst.md) Medium
- [501. Find Mode in Binary Search Tree](../solutions/10-trees/lc-501-find-mode-in-binary-search-tree.md) Easy
- [513. Find Bottom Left Tree Value](../solutions/10-trees/lc-513-find-bottom-left-tree-value.md) Medium
- [515. Find Largest Value in Each Tree Row](../solutions/10-trees/lc-515-find-largest-value-in-each-tree-row.md) Medium
- [530. Minimum Absolute Difference in BST](../solutions/10-trees/lc-530-minimum-absolute-difference-in-bst.md) Easy
- [538. Convert BST to Greater Tree](../solutions/10-trees/lc-538-convert-bst-to-greater-tree.md) Medium
- [543. Diameter of Binary Tree](../solutions/10-trees/lc-543-diameter-of-binary-tree.md) Easy
- [559. Maximum Depth of N-ary Tree](../solutions/10-trees/lc-559-maximum-depth-of-n-ary-tree.md) Easy
- [572. Subtree of Another Tree](../solutions/10-trees/lc-572-subtree-of-another-tree.md) Easy
- [617. Merge Two Binary Trees](../solutions/10-trees/lc-617-merge-two-binary-trees.md) Easy
- [637. Average of Levels in Binary Tree](../solutions/10-trees/lc-637-average-of-levels-in-binary-tree.md) Easy
- [654. Maximum Binary Tree](../solutions/10-trees/lc-654-maximum-binary-tree.md) Medium
- [669. Trim a Binary Search Tree](../solutions/10-trees/lc-669-trim-a-binary-search-tree.md) Medium
- [700. Search in a Binary Search Tree](../solutions/10-trees/lc-700-search-in-a-binary-search-tree.md) Easy
- [701. Insert into a Binary Search Tree](../solutions/10-trees/lc-701-insert-into-a-binary-search-tree.md) Medium
- [703. Kth Largest Element in a Stream](../solutions/11-heap-priority-queue/lc-703-kth-largest-element-in-a-stream.md) Easy
- [872. Leaf-Similar Trees](../solutions/10-trees/lc-872-leaf-similar-trees.md) Easy
- [94. Binary Tree Inorder Traversal](../solutions/10-trees/lc-94-binary-tree-inorder-traversal.md) Easy
- [968. Binary Tree Cameras](../solutions/18-greedy/lc-968-binary-tree-cameras.md) Hard
- [98. Validate Binary Search Tree](../solutions/10-trees/lc-98-validate-binary-search-tree.md) Medium

## 相關

- [_moc](../_moc.md)
