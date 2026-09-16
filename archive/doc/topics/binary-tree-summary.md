# 二元樹總結篇

> 2025 版；2026 年改寫版見 notes/topics/

---

## 樹的基礎理論

- 樹的基本結構、樹的特性、樹的種類、儲存方式、走訪方式
  - [tree-basics.md](tree-basics.md)

---

## 二元樹的走訪

- 二元樹的深度優先走訪：前序遍歷、中序遍歷、後序遍歷
  - [binary-tree-traversal-dfs.md](binary-tree-traversal-dfs.md)
  
- 二元樹的廣度優先走訪：層級遍歷
  - [binary-tree-traversal-bfs-level-order.md](binary-tree-traversal-bfs-level-order.md)

---

## ✅ 已完成題目一覽（共 39 題）

| 題號  | 題目名稱                                             | 分類         | Java 檔案連結                                                                                                                                                                           |
|-----|--------------------------------------------------|------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 144 | Binary Tree Preorder Traversal                   | DFS        | [ID144BinaryTreePreorderTraversal.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID144BinaryTreePreorderTraversal.java)                                                 |
| 94  | Binary Tree Inorder Traversal                    | DFS        | [ID94BinaryTreeInorderTraversal.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID94BinaryTreeInorderTraversal.java)                                                     |
| 145 | Binary Tree Postorder Traversal                  | DFS        | [ID145BinaryTreePostorderTraversal.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID145BinaryTreePostorderTraversal.java)                                               |
| 102 | Binary Tree Level Order Traversal                | BFS        | [ID102BinaryTreeLevelOrderTraversal.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID102BinaryTreeLevelOrderTraversal.java)                                             |
| 107 | Binary Tree Level Order Traversal II             | BFS        | [ID107BinaryTreeLevelOrderTraversalII.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID107BinaryTreeLevelOrderTraversalII.java)                                         |
| 199 | Binary Tree Right Side View                      | BFS        | [ID199BinaryTreeRightSideView.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID199BinaryTreeRightSideView.java)                                                         |
| 637 | Average of Levels in Binary Tree                 | BFS        | [ID637AverageOfLevelsInBinaryTree.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID637AverageOfLevelsInBinaryTree.java)                                                 |
| 429 | N-ary Tree Level Order Traversal                 | BFS        | [ID429NAryTreeLevelOrderTraversal.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID429NAryTreeLevelOrderTraversal.java)                                                 |
| 515 | Find Largest Value in Each Tree Row              | BFS        | [ID515FindLargestValueInEachTreeRow.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID515FindLargestValueInEachTreeRow.java)                                             |
| 116 | Populating Next Right Pointers in Each Node      | BFS        | [ID116PopulatingNextRightPointersInEachNode.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID116PopulatingNextRightPointersInEachNode.java)                             |
| 117 | Populating Next Right Pointers in Each Node II   | BFS        | [ID117PopulatingNextRightPointersInEachNodeII.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID117PopulatingNextRightPointersInEachNodeII.java)                         |
| 104 | Maximum Depth of Binary Tree                     | 遞迴 / BFS   | [ID104MaximumDepthOfBinaryTree.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID104MaximumDepthOfBinaryTree.java)                                                       |
| 559 | Maximum Depth of N-ary Tree                      | 遞迴 / DFS   | [ID559MaximumDepthOfNAryTree.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID559MaximumDepthOfNAryTree.java)                                                           |
| 111 | Minimum Depth of Binary Tree                     | BFS        | [ID111MinimumDepthOfBinaryTree.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID111MinimumDepthOfBinaryTree.java)                                                       |
| 226 | Invert Binary Tree                               | 遞迴         | [ID226InvertBinaryTree.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID226InvertBinaryTree.java)                                                                       |
| 101 | Symmetric Tree                                   | 遞迴 / BFS   | [ID101SymmetricTree.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID101SymmetricTree.java)                                                                             |
| 222 | Count Complete Tree Nodes                        | DFS + 位元操作 | [ID222CountCompleteTreeNodes.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID222CountCompleteTreeNodes.java)                                                           |
| 110 | Balanced Binary Tree                             | 後序遍歷       | [ID110BalancedBinaryTree.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID110BalancedBinaryTree.java)                                                                   |
| 257 | Binary Tree Paths                                | DFS        | [ID257BinaryTreePaths.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID257BinaryTreePaths.java)                                                                         |
| 404 | Sum of Left Leaves                               | DFS / BFS  | [ID404SumOfLeftLeaves.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID404SumOfLeftLeaves.java)                                                                         |
| 513 | Find Bottom Left Tree Value                      | BFS        | [ID513FindBottomLeftTreeValue.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID513FindBottomLeftTreeValue.java)                                                         |
| 112 | Path Sum                                         | DFS        | [ID112PathSum.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID112PathSum.java)                                                                                         |
| 113 | Path Sum II                                      | 回溯法        | [ID113PathSumII.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID113PathSumII.java)                                                                                     |
| 106 | Construct Binary Tree from Inorder and Postorder | 構建樹        | [ID106ConstructBinaryTreeFromInorderAndPostorderTraversal.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID106ConstructBinaryTreeFromInorderAndPostorderTraversal.java) |
| 105 | Construct Binary Tree from Preorder and Inorder  | 構建樹        | [ID105ConstructBinaryTreeFromPreorderAndInorderTraversal.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID105ConstructBinaryTreeFromPreorderAndInorderTraversal.java)   |
| 654 | Maximum Binary Tree                              | 構建樹        | [ID654MaximumBinaryTree.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID654MaximumBinaryTree.java)                                                                     |
| 617 | Merge Two Binary Trees                           | 遞迴         | [ID617MergeTwoBinaryTrees.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID617MergeTwoBinaryTrees.java)                                                                 |
| 700 | Search in a Binary Search Tree                   | BST 搜尋     | [ID700SearchInABinarySearchTree.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID700SearchInABinarySearchTree.java)                                                     |
| 98  | Validate Binary Search Tree                      | 中序遍歷       | [ID98ValidateBinarySearchTree.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID98ValidateBinarySearchTree.java)                                                         |
| 530 | Minimum Absolute Difference in BST               | 中序遍歷       | [ID530MinimumAbsoluteDifferenceInBST.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID530MinimumAbsoluteDifferenceInBST.java)                                           |
| 501 | Find Mode in Binary Search Tree                  | 中序遍歷       | [ID501FindModeInBinarySearchTree.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID501FindModeInBinarySearchTree.java)                                                   |
| 236 | Lowest Common Ancestor of a Binary Tree          | DFS        | [ID236LowestCommonAncestorOfABinaryTree.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID236LowestCommonAncestorOfABinaryTree.java)                                     |

---

## 📚 知識分類

### ➤ 基礎結構與遍歷方式
- DFS（遞迴／迴圈）：前序、中序、後序
- BFS：層序、右視圖、統計平均值／最大值

### ➤ 樹的性質判斷
- 高度／深度：`104`, `111`, `110`, `559`
- 平衡與對稱：`101`, `110`
- 是否為 BST：`98`, `700`
- 完全二元樹：`222`

### ➤ 建構與轉換
- 由前／中／後序重建：`105`, `106`, `654`
- 合併與反轉：`226`, `617`

### ➤ 特殊應用
- 路徑問題：`112`, `113`, `257`
- 最左／右節點查找：`513`, `199`
- LCA：`236`
- Left leaves sum：`404`

---

## 🧠 解題技巧整理

### 1. 遞迴與後序遍歷的應用
許多問題需要透過「後序」順序獲得子樹高度、路徑等資訊，例如：
- `110` 平衡樹
- `104` 最大深度
- `226` 翻轉樹

### 2. 中序遍歷與 BST 性質
- BST 中序遍歷應為遞增序列，應用於：
    - `98` 驗證 BST
    - `530` 最小差值
    - `501` 出現頻率最高值

### 3. BFS 的層級控制
- `102`, `107`, `637`, `515`, `513` 都可以用 `Queue` 處理層級資訊，甚至結合 index 或 size 控制層尾節點。

### 4. 回溯法與記錄路徑
- `113` 與 `257` 等題目需追蹤走訪路徑，適合回溯結構。

### 5. 構建樹時善用 index 查表
- `105`, `106` 建樹時建議先建 value -> index map 提高效率。

---

> 📅 訓練區間：2025/5/8 ～ 2025/5/26  
> ✍️ 每日筆記請見 `daily` 文件夾下的 `dayXX-2025-05-XX.md`
