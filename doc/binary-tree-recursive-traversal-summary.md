# 二元樹遞迴走訪總整理（LC144 / 94 / 145）

## 題目總覽

| 題號  | 標題                              | 遍歷順序      | 題目連結                                                                                                                                  |
|-----|---------------------------------|-----------|---------------------------------------------------------------------------------------------------------------------------------------|
| 144 | Binary Tree Preorder Traversal  | 中 → 左 → 右 | [ID144BinaryTreePreorderTraversal.java](../src/main/java/io/github/monty/leetcode/binarytree/ID144BinaryTreePreorderTraversal.java)   |
| 94  | Binary Tree Inorder Traversal   | 左 → 中 → 右 | [ID94BinaryTreeInorderTraversal.java](../src/main/java/io/github/monty/leetcode/binarytree/ID94BinaryTreeInorderTraversal.java)       |
| 145 | Binary Tree Postorder Traversal | 左 → 右 → 中 | [ID145BinaryTreePostorderTraversal.java](../src/main/java/io/github/monty/leetcode/binarytree/ID145BinaryTreePostorderTraversal.java) |

--- 

## 共通題目說明

- 給定一棵二元樹，請依照指定順序回傳走訪節點的值。

- 輸入為樹的根節點 TreeNode root，請回傳 List<Integer> 結果。

- 範例輸入：[1,null,2,3]  
  對應結構：
  ```
    1
     \
      2
     /
    3
  ```
- 對應輸出：
  - Inorder: [1,3,2]
  - Preorder: [1,2,3]
  - Postorder: [3,2,1]

---

## 思路

### 1. 為何使用遞迴解法？

- 二元樹本身就是遞迴結構：每個節點的左右子樹仍是二元樹。
- 可直接使用 DFS 深度優先遍歷的特性處理走訪順序。
- 實作簡潔，與樹的結構天然對應。

### 2. 與迭代法的比較？
TODO 待補充

---

## 遞迴解法共通技巧

### 遞迴三大要素

| 步驟                   | 說明                                               |
|----------------------|--------------------------------------------------|
| 1. **確定遞迴函數的參數和返回值** | 通常傳入 `TreeNode node` 以及累積結果的 `List<Integer> res` |
| 2. **確定終止條件**        | `if (node == null) return;` 防止 null pointer      |
| 3. **確定單層遞迴的邏輯**     | 根據走訪順序安排：pre/in/post 的位置執行 `res.add(node.val)`   |

#### 1. 確定遞迴函數的參數和返回值

- 傳入要遞迴的目前節點 curr。(不建議直接修改參數 `cur`，避免污染原樹。)
- 要印出前序遍歷 node 的 val，所以參數傳入 `List<Integer>` 來放印出結果。
- 結果已在參數傳遞，不需要有返回值，返回類型為 `void`。

```
    private void preorder(TreeNode cur, List<Integer> result) {
        // TODO 遞迴邏輯
    }
```

#### 2. 確定終止條件

- 當前節點為空，直接 return 結束遞迴
```
    if (cur == null) {
        return;
    }
```

#### 3. 確定單層遞迴的邏輯

- 根據走訪順序安排：pre/in/post 的位置執行 `res.add(node.val)`
```
    // 以前序為例: 中 → 左 → 右，根排在前面。
    result.add(cur.val);         // 中 *重點
    preorder(cur.left, result);  // 左
    preorder(cur.right, result); // 右
```
- 順序不可寫錯：res.add(node.val) 的位置依遍歷方式決定
- 可以在遞迴函數中抽成 dfs(TreeNode node, List<Integer> res) 提高重用性

--- 

## 三種走訪順序與程式範例

### LC144. 前序遍歷

```java
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }
    private void dfs(TreeNode cur, List<Integer> result) {
        if (cur == null) return;
        result.add(cur.val);    // 中*
        dfs(cur.left, result);  // 左
        dfs(cur.right, result); // 右
    }
}
```

#### LC94. 中序遍歷
```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }
    private void dfs(TreeNode cur, List<Integer> result) {
        if (cur == null) return;
        // 左 → [中] → 右
        dfs(cur.left, result);
        result.add(cur.val);
        dfs(cur.right, result);
    }
}
```

#### LC145. 後序遍歷
```java
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }
    private void dfs(TreeNode cur, List<Integer> res) {
        if (cur == null) return;
        // 左 → 右 → [中]
        dfs(cur.left, res);
        dfs(cur.right, res);
        res.add(cur.val);
    }
}
```

--- 

## 複雜度分析（通用）

- 時間複雜度：`O(n)`，每個節點訪問一次。
- 空間複雜度：
  - 回傳列表佔用 `O(n)` 空間。
  - 遞迴最深可達 `O(n)`（例如鏈狀樹），平均為 `O(log n)`。