# Binary Tree - Level Order Traversal

> 整理層序遍歷（Level-order traversal）的內容及相關題型。  
> 以 LC102 為基礎，根據題目要求彈性變化，彙整共通邏輯與實作技巧。

包含以下內容：
- BFS 與 DFS 核心概念
- 通用模板（LC102）
- 題型變化與解法（LC107、LC199...）
- 題目總覽表格
- 解題技巧
- 總結

--- 

## Binary Tree Level Order Traversal

- 層序遍歷（Level Order Traversal）是 廣度優先搜尋（BFS） 的典型應用，遍歷方式為「一層一層」從上而下、從左到右。

### 📌 BFS 核心概念

- **層序遍歷主力**。
- 通常使用 `Queue` 實現，從根節點開始，將每層節點依序加入 queue，逐層訪問。
- 若要避免層級混淆，需在每層開始前記錄當前 queue 的 `size`。
- 若要求「某層資訊」（如最右節點、最大值、平均值等），可在遍歷層時額外處理。

### ✨ DFS 模擬層序

- 使用前序遍歷，並根據 depth 控制結果 list 的插入位置。 
- DFS 雖然也能實作層序，但較難處理「從左至右」的順序（因為先遞迴左或右），對於只需要層級資訊（如最大深度）時較方便。

---  

## 🌳 層序遍歷題型通用模板 - 以 LC 102 為例

### 解法一: BFS + Queue

```java
public List<List<Integer>> levelOrderQueue(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;

    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
        List<Integer> level = new ArrayList<>();

        int levelSize = queue.size(); // queue 的 size 不斷變化，要先儲存當前層數的大小
        while (levelSize-- > 0) {
            TreeNode node = queue.poll();
            level.add(node.val); // 將當前節點加入結果集

            // 把下一層的左右子節點加入 queue
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

        result.add(level);
    }

    return result;
}
```

- 使用 queue 儲存每一層節點，在每一層遍歷過程中記錄 queue 的長度，避免節點動態加入導致層級混淆。
- 應在每層開始前，先記錄當前 queue 的大小，以避免節點動態加入造成混淆。

### 解法二: DFS + 遞迴（以 depth 控制層級）

```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    dfs(root, result, 0);
    return result;
}

private void dfs(TreeNode cur, List<List<Integer>> result, int depth) {
    if (cur == null) return;

    // 每層第一次到達時建立新的 List（層數從 0 開始）
    if (depth >= result.size()) {
        result.add(new ArrayList<>());
    }

    // 加入當層結果
    List<Integer> level = result.get(depth);
    level.add(cur.val);
    // 遞迴左右子樹
    dfs(cur.left, result, depth + 1);
    dfs(cur.right, result, depth + 1);
}
```

- 使用「前序遍歷」的思路，每遇到一個節點，就根據其 `depth` 決定加入哪一層。
- 當目前深度 `depth` 與結果 list 的大小相等時，表示這是該層第一個出現的節點 → 應新建 list。

---

## ✅ 題目彙整與變化說明

### LC102 - Binary Tree Level Order Traversal

- 輸出每層節點值。
- 作法：
  - BFS：使用 Queue 層層遍歷。
  - DFS：以 depth 控制層級，前序遍歷遞迴新增節點值。
- 核心範本題。

### LC107 - Binary Tree Level Order Traversal II

- 輸出結果從底層到頂層。
- 作法：DFS（遞迴）
  - `Collections.reverse()` 反轉 LC102 結果，或
  - 每層插入 `result.add(0, currentLevel)`。

### LC199 - Binary Tree Right Side View

- 每層僅取最右側節點。
- 作法：在 BFS 中，記錄每層最後一個節點。

### LC637 - Average of Levels in Binary Tree

- 每層輸出平均值。
- 作法：BFS 遍歷每層後求平均 `sum / size`。

### LC429 - N-ary Tree Level Order Traversal

- N 元樹的層序遍歷。
- 作法：BFS，與二元樹相同，但需遍歷 `node.children`。
- 變化：多個 children，需迴圈遍歷 `for (Node child : node.children)`。

### LC515 - Find Largest Value in Each Tree Row
- 每層找最大值。
- 作法：BFS 遍歷每層，每層中 `max = Math.max(max, node.val)`。

### LC116 - Populating Next Right Pointers in Each Node
- 完全二元樹，填 next。
- 限制： 空間 O(1)。
- 作法：BFS，使用已建立的 `next` 指針逐層連接。
- 筆記：要注意空間限制與連接邏輯。

### LC117 - Populating Next Right Pointers in Each Node II

- 任意二元樹，填 next。
- 作法：BFS，同 LC116，需處理 null 子節點或不對稱結構。
- 補充：進階作法可使用 dummy node 簡化 next 指標連接邏輯，可列為未來挑戰。
- 筆記：
  - 要注意空間限制與連接邏輯。
  - 判斷葉節點：`node.left == null && node.right == null`

### LC104 - Maximum Depth of Binary Tree

- 最大深度。
- 作法：
  - DFS 後序遞迴：`max(left, right) + 1`，回傳左右子樹最大深度 + 1。
  - BFS 前序遞迴 + 傳遞參數：層數迴圈記錄 `depth++`，每到一層就更新最大層數。
- 延伸：[LC559. Maximum Depth of N-ary Tree](daily/day23-2025-05-10.md)
  
### LC111 - Minimum Depth of Binary Tree

- 最小深度。
- 作法：
  - BFS： 首次遇到葉節點時回傳 depth。
  - DFS： 若左右子樹有一邊為 null，不取 min，而是走另一邊。

---

## 題目總覽

| 題號  | 題目            | 關鍵差異             |
|-----|---------------|------------------|
| 102 | 基本層序遍歷        | 標準 BFS / DFS     |
| 107 | 底層到頂層         | reverse 結果       |
| 199 | 右視圖           | 每層最後一個節點         |
| 637 | 每層平均值         | 求和後除以 size       |
| 429 | N 元樹          | children 為 List  |
| 515 | 每層最大值         | 比大小              |
| 116 | 填 right（完全二元） | 空間 O(1), 利用 next |
| 117 | 填 right（任意二元） | 多判斷，dummy node   |
| 104 | 最大深度          | BFS or DFS       |
| 111 | 最小深度          | 遇葉節點即回傳          |

---  

## 🛠 小技巧

- BFS queue 記得先記錄 size，避免層數混淆。
- DFS 可搭配 depth 控制層級（前序遍歷）。
- 葉節點定義：同時無左子與右子節點（`node.left == null && node.right == null`）

---

##  總結

- 層序遍歷是 BFS 最基礎應用，但也可用 DFS 模擬。
- 多數變形題本質上仍是「以層為單位」處理節點，只需額外記錄該層狀態即可。
- 熟練 BFS 模板與 queue 操作，有助於處理同層邏輯與層數相關的判斷。