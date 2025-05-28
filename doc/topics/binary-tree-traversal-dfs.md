# 二元樹 - 深度優先（DFS）遍歷筆記（LC144 / 94 / 145）
> 整理前序（Preorder）、中序（Inorder）、後序（Postorder）遍歷，以及三種實作方式（遞迴、迭代、統一迭代）。  
> 以 Leetcode 144, 94, 145 為範例。

包含以下內容：
- 一、題目概述
- 二、解法一：遞迴法
- 三、解法二：迭代法
- 四、解法三：統一迭代寫法（迭代 + 空指針標記法）

--- 

# 一、題目概述

## 題目總覽

| 題號  | 標題                                   | 遍歷順序      | 題目連結                                                                                                                                  |
|-----|--------------------------------------|-----------|---------------------------------------------------------------------------------------------------------------------------------------|
| 144 | Binary Tree Preorder Traversal 前序遍歷  | 中 → 左 → 右 | [ID144BinaryTreePreorderTraversal.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID144BinaryTreePreorderTraversal.java)   |
| 94  | Binary Tree Inorder Traversal 中序遍歷   | 左 → 中 → 右 | [ID94BinaryTreeInorderTraversal.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID94BinaryTreeInorderTraversal.java)       |
| 145 | Binary Tree Postorder Traversal 後續遍歷 | 左 → 右 → 中 | [ID145BinaryTreePostorderTraversal.java](../../src/main/java/io/github/monty/leetcode/binarytree/ID145BinaryTreePostorderTraversal.java) |

--- 

## 共通題目說明

- 給定一棵二元樹，請依照指定順序回傳走訪節點的值。

- 輸入為樹的根節點 TreeNode root，請回傳 List<Integer> 結果。

- 範例輸入：
  ```
                 1
               /   \
             4       2
           /       /   \
         7       3       5
        / \         \     \
      8    9         6     10
     /
   11
  ```
- 對應輸出：
  - Preorder: [1, 4, 7, 8, 11, 9, 2, 3, 6, 5, 10]
  - Inorder: [11, 8, 7, 9, 4, 1, 3, 6, 2, 5, 10]
  - Postorder: [11, 8, 9, 7, 4, 6, 3, 10, 5, 2, 1]

---

## 延伸問題

### 1. 為何使用「遞迴解法」？

- 二元樹本身就是遞迴結構：每個節點的左右子樹仍是二元樹。
- 可直接使用 DFS 深度優先遍歷的特性處理走訪順序。
- 實作簡潔，與樹的結構天然對應。

### 2. 為何使用「迭代解法」？

- 避免因樹太深導致系統棧溢位（Stack Overflow）。
- 更容易控制與擴充遍歷邏輯，例如同時記錄父節點或處理額外資訊。
- 更接近底層實作，有利於理解 DFS 運作原理。
- 有助於理解「統一範本法」（如加 visited 標記的迭代法）。

### 3. 為何使用「迭代 + 空指針標記法」？

- **模擬遞迴調用棧邏輯，容易統一處理前中後序**：使用 `null` 作為已訪問標記，可以讓「處理節點」與「遞迴回來」的語意明確分離。
- **較原始迭代更具可讀性與邏輯性**：傳統迭代解法（如 inorder 使用 peek 判斷 + 指針移動）程式碼分支複雜；空指針標記法結構一致、清晰。
- **方便教學與考場複製套用**：三種遍歷只要改順序即可，統一範本有助快速上手與應對面試。

### 4. 遞迴解法 vs 迭代解法？

#### 遞迴遍歷（遞迴本質）

- 遞迴本質是利用**系統呼叫棧**記錄當前節點的執行狀態。 
- 每次呼叫將局部變數、參數與返回位置壓入棧，返回時彈出還原。
- 所以前中後序遍歷只需改動「處理節點」的位置，即可切換遍歷順序，風格統一、邏輯簡單。

#### 迭代遍歷（手動模擬棧）
- 需顯式使用 stack 模擬呼叫棧邏輯，依據遍歷順序控制 push/pop 時機。
- 不同遍歷方式程式碼風格差異明顯，無法僅靠調整程式碼順序完成切換。
- 優點：避免系統棧溢位，對大型資料樹更穩定。

#### 核心比較

| 比較項目   | 遞迴法                       | 迭代法                  |
|--------|---------------------------|----------------------|
| 使用資料結構 | 系統呼叫棧（隱式）                 | 明確使用 stack（顯式）       |
| 可讀性    | 簡潔，直觀                     | 程式碼較複雜，需要控制流程        |
| 彈性     | 不易控制遍歷順序或記錄狀態             | 可靈活控制處理順序與狀態（如加入標記法） |
| 運行效率   | 呼叫開銷高，可能造成 Stack Overflow | 控制力強，適合大樹或棧空間可控的場景   |

---

# 二、解法一：遞迴法

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

### LC94. 中序遍歷
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

### LC145. 後序遍歷
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

## 複雜度分析（遞迴通用）

- 時間複雜度：`O(n)`，每個節點訪問一次。
- 空間複雜度：
  - 回傳列表佔用 `O(n)` 空間。
  - 遞迴最深可達 `O(n)`（例如鏈狀樹），平均為 `O(log n)`。

---

# 三、解法二：迭代寫法

## 三種走訪順序與程式範例

### LC144. 前序遍歷 Preorder Traversal（中 → 左 → 右）

#### 思路
- 利用 stack 模擬： 
  - 根節點入棧，處理節點（中）後，先右再左入棧，確保左節點先被處理。
- 處理順序與訪問順序一致，實作較簡單。

#### Java 實作
```java
class Solution {
  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) return result;

    // 利用棧來彈出要處理的元素
    Deque<TreeNode> stack = new ArrayDeque<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();
      result.add(node.val); // 中
      // 右子樹先放，因為 stack 先進後出，左要先處理要先彈出
      if (node.right != null) stack.push(node.right); // 右
      if (node.left != null) stack.push(node.left); // 子
    }
    return result;
  }
}
```

### LC94. 中序遍歷 Inorder Traversal（左 → 中 → 右）

#### 思路
- 使用 Stack 模擬遞迴：
  - 從 root 一路往左壓入 stack，直到最左節點後回頭處理中間與右子樹。
- 訪問順序與處理順序不同，需要額外指標輔助。
- 需分開處理訪問與處理邏輯。

#### Java 實作
```java
class Solution {
  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) return result;

    // stack 用來存放「訪問『過』的元素」
    Deque<TreeNode> stack = new ArrayDeque<>();
    // curr 是指針，用來訪問目前的元素
    TreeNode curr = root;
    while (curr != null || !stack.isEmpty()) {
      // 若 curr 不為空，一路向左
      if (curr != null) {
        stack.push(curr); // 將訪問過的節點 暫存在 stack 中
        curr = curr.left; // 左: 繼續深度向左
      } else { // curr 為空，沒左邊了，處理自己和右邊
        curr = stack.pop();
        result.add(curr.val); // 中
        curr = curr.right; // 右
      }
    }
    return result;
  }
}
```

### LC145. 後序遍歷 Postorder Traversal（左 → 右 → 中）

#### 思路說明
- 將前序（中 → 左 → 右）改為（中 → 右 → 左），再反轉結果。
- 處理節點順序仍為 stack pop 時立即處理（中）。

#### Java 實作
```java
class Solution {
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) return result;

    Deque<TreeNode> stack = new ArrayDeque<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();
      // 處理順序: 中 → 右 → 左
      result.add(node.val);
      if (node.left != null) stack.push(node.left);
      if (node.right != null) stack.push(node.right);
    }

    // 反轉結果: 左 → 右 → 中
    Collections.reverse(result);
    return result;
  }
}
```

---

## 迭代的三種遍歷小結

| 遍歷方式 | 訪問順序      | 可否同步（訪問 == 處理） | 備註        |
|------|-----------|----------------|-----------|
| 前序   | 中 → 左 → 右 | ✅ 同步           | 先處理中，再壓右左 |
| 中序   | 左 → 中 → 右 | ❌ 非同步          | 需輔助指標走訪   |
| 後序   | 左 → 右 → 中 | ✅ 同步後需反轉       | 模擬中右左後反轉  |

---

## 複雜度分析（迭代通用）

- 時間複雜度：`O(n)`
  - 每個節點最多被**壓入與彈出 stack 各一次**，因此總操作次數為 `2n` 級別。
  - 所以時間複雜度為 `O(n)`，其中 `n` 是節點總數。
- 空間複雜度：
  - 最壞情況：`O(n)`，退化成鏈狀樹（例如只有右子樹），此時 stack 最深可達 n。
  - 平均情況：`O(log n)`，若為 平衡樹（例如 AVL 或完全二元樹），stack 深度平均為樹高 `log n`。

---

# 四、解法三：統一迭代寫法（迭代 + 空指針標記法）

## 共用思路

這三種遍歷方式本質是「遞迴模擬 → 改為迭代實作」，利用 `null` 標記法實現節點狀態的區分：

1. **將節點壓入棧中時，不立刻處理**，而是根據遍歷順序壓入 **右、中、左** 順序的節點。
2. **加入節點本身前先壓入一個 `null`** 作為標記，表示這個節點已經訪問過但尚未處理。
3. 每次從棧中拿出來的元素如果是 `null`，就彈出並處理下一個節點（即「已訪問但尚未處理」的節點）。

> ❗ 注意：使用此方法時，**棧中需要允許 `null` 元素作為標記**，因此：
>
> - **可用：** `LinkedList`、`Stack`（允許 `null`）
> - **不可用：** `ArrayDeque`（`push(null)` 會丟出 `NullPointerException`）

--- 

## 三種走訪順序與程式範例

### 共用程式碼

```java
class Solution {
  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) return result;

    // 使用 stack 存放被訪問過的節點
    Deque<TreeNode> stack = new LinkedList<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      TreeNode node = stack.peek();

      if (node != null) {
        stack.pop(); // 把 node 先彈出，再照順序放回來
        
        // 根據遍歷順序，決定壓入順序（中左右、左右中等）
        // ↓↓↓ 唯一不同的部分 ↓↓↓
        stack.push(node); // 中
        stack.push(null); // 標記此節點已訪問過
        if (node.right != null) stack.push(node.right); // 右
        if (node.left != null) stack.push(node.left); // 左
        // ↑↑↑ 唯一不同的部分 ↑↑↑
        
      } else { // 遇到空指針，此指針被訪問過，可以放進結果集
        stack.pop(); // 彈出 null
        node = stack.pop(); // 彈出被標記的指針

        result.add(node.val); // 加入結果集
      }
    }

    return result;
  }
}
```

### LC144. 前序遍歷 Preorder Traversal（中 → 左 → 右）

```
      // ...
        if (node.right != null) stack.push(node.right); // 右
        if (node.left != null) stack.push(node.left); // 左
        stack.push(node); // 中
        stack.push(null);
      // ...
```

### LC94. 中序遍歷 Inorder Traversal（左 → 中 → 右）

```
      // ...
        if (node.right != null) stack.push(node.right); // 右
        stack.push(node); // 中
        stack.push(null);
        if (node.left != null) stack.push(node.left); // 左
      // ...
```

### LC145. 後序遍歷 Postorder Traversal（左 → 右 → 中）

```
      // ...
        stack.push(node); // 中
        stack.push(null);
        if (node.right != null) stack.push(node.right); // 右
        if (node.left != null) stack.push(node.left); // 左
      // ...
```

--- 

## 複雜度分析（統一迭代解法通用）

- **時間複雜度：`O(n)`**
每個節點最多被壓棧與彈棧各一次，處理一次。

- **空間複雜度：`O(n)`**  
  最壞情況（例如不平衡樹）會退化到高度為 `n` 的情況，stack 最深為 `n`；平均情況約 `O(log n)`。