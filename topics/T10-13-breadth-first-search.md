# Breadth-First Search

## 解題技巧

LC 101 可用兩個雙端 Deque 從兩端取出鏡像節點，逐層比較而不需要記錄每層 size。

Multi-source BFS 先把所有起點一起放入 queue，再同步向外擴散。無權圖中，節點第一次被發現時就是最短距離；在 enqueue 前先標記已走訪，可防止同一節點重複入隊。LC 542 以所有 0 為起點，並用 `-1` 表示尚未走訪的格子。

需要分層輸出時，在展開 child 前保存當下的 `queue.size()` 作為目前層邊界，並只處理這個固定數量；本輪新加入的節點自然留給下一層。

BFS 不是只有求最短路才用。[lc-133-clone-graph](../solutions/14-graphs/lc-133-clone-graph.md) 用 BFS 複製整張圖，走訪順序其實無所謂——但「登記與入隊綁在一起」的理由從**效能**升格成**正確性**：等出隊才登記的話，有兩個父節點的節點會被處理兩次，它的複本就會拿到兩份重複的鄰居清單。

## 已刷題目

- [100. Same Tree](../solutions/10-trees/lc-100-same-tree.md) Easy
- [101. Symmetric Tree](../solutions/10-trees/lc-101-symmetric-tree.md) Easy
- [102. Binary Tree Level Order Traversal](../solutions/10-trees/lc-102-binary-tree-level-order-traversal.md) Medium
- [104. Maximum Depth of Binary Tree](../solutions/10-trees/lc-104-maximum-depth-of-binary-tree.md) Easy
- [107. Binary Tree Level Order Traversal II](../solutions/10-trees/lc-107-binary-tree-level-order-traversal-ii.md) Medium
- [111. Minimum Depth of Binary Tree](../solutions/10-trees/lc-111-minimum-depth-of-binary-tree.md) Easy
- [112. Path Sum](../solutions/10-trees/lc-112-path-sum.md) Easy
- [116. Populating Next Right Pointers in Each Node](../solutions/08-binary-search/lc-116-populating-next-right-pointers-in-each-node.md) Medium
- [1161. Maximum Level Sum of a Binary Tree](../solutions/10-trees/lc-1161-maximum-level-sum-of-a-binary-tree.md) Medium
- [117. Populating Next Right Pointers in Each Node II](../solutions/10-trees/lc-117-populating-next-right-pointers-in-each-node-ii.md) Medium
- [133. Clone Graph](../solutions/14-graphs/lc-133-clone-graph.md) Medium
- [1448. Count Good Nodes in Binary Tree](../solutions/10-trees/lc-1448-count-good-nodes-in-binary-tree.md) Medium
- [199. Binary Tree Right Side View](../solutions/10-trees/lc-199-binary-tree-right-side-view.md) Medium
- [226. Invert Binary Tree](../solutions/10-trees/lc-226-invert-binary-tree.md) Easy
- [404. Sum of Left Leaves](../solutions/10-trees/lc-404-sum-of-left-leaves.md) Easy
- [429. N-ary Tree Level Order Traversal](../solutions/10-trees/lc-429-n-ary-tree-level-order-traversal.md) Medium
- [513. Find Bottom Left Tree Value](../solutions/10-trees/lc-513-find-bottom-left-tree-value.md) Medium
- [515. Find Largest Value in Each Tree Row](../solutions/10-trees/lc-515-find-largest-value-in-each-tree-row.md) Medium
- [530. Minimum Absolute Difference in BST](../solutions/10-trees/lc-530-minimum-absolute-difference-in-bst.md) Easy
- [542. 01 Matrix](../solutions/14-graphs/lc-542-01-matrix.md) Medium
- [559. Maximum Depth of N-ary Tree](../solutions/10-trees/lc-559-maximum-depth-of-n-ary-tree.md) Easy
- [617. Merge Two Binary Trees](../solutions/10-trees/lc-617-merge-two-binary-trees.md) Easy
- [637. Average of Levels in Binary Tree](../solutions/10-trees/lc-637-average-of-levels-in-binary-tree.md) Easy
- [733. Flood Fill](../solutions/14-graphs/lc-733-flood-fill.md) Easy
- [797. All Paths From Source to Target](../solutions/14-graphs/lc-797-all-paths-from-source-to-target.md) Medium

## 相關

- [_moc](../_moc.md)
