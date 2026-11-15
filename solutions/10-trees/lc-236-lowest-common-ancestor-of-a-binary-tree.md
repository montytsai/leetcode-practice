---
title: "Lowest Common Ancestor of a Binary Tree"
difficulty: Medium
topics: [Binary Tree, Tree, Depth-First Search]
category: 10-trees
order: 31
source: [Carl, LeetCode75]
platform: LeetCode
url: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
status: ac-assisted
note: ""
date_created: 2026-03-16
date_updated: 2026-11-15
---

# 236. Lowest Common Ancestor of a Binary Tree

## 題目說明

- 給一棵二元樹的 `root`，以及樹中確實存在的兩個節點 `p`、`q`，回傳 `p`、`q` 的最近共同祖先(LCA)。
- 一個節點也可以是它自己的祖先(例如 p 是 q 的祖先時，LCA 就是 p)。

## 心得

這題也是寫第三次，每次遇到都卡住。問了 AI 提示：如果左子樹和右子樹都回傳了非空值(代表 p 和 q 分別落在當前節點的兩側)，那當前節點扮演什麼角色？我才自己寫出來。

---

## 解法一：後序遍歷，讓每個節點回傳「自己子樹裡找到的目標」

### Intuition

LCA 問題的核心是每個節點要能回答一句話：「p、q 在我的子樹裡找到了誰？」用後序遍歷(先處理左右子樹，再處理自己)剛好符合這個資訊由下往上匯聚的方向。當一個節點本身就是 p 或 q，就直接回傳自己──就算它底下還藏著另一個目標，它自己也已經是候選的 LCA(對應「其中一個節點是另一個節點的祖先」的情況)。

真正的判斷點在於：當左子樹回傳非空、右子樹也回傳非空，代表 p 和 q 分別落在目前節點的兩側，此時目前節點就是它們的最近共同祖先，直接回傳自己；若只有一邊非空，代表兩個目標(或其中一個)都在同一側，把那一側的結果原封不動往上傳，讓上層節點繼續判斷。

這題卡在同一個地方三次：明明知道要用遞迴、要判斷左右，但「左右都非空時，當前節點要回傳誰、為什麼」這句話沒想清楚。這次靠外部提示把問題重新框成「當前節點扮演什麼角色」才想通──遞迴回傳的不是「找到的節點」，而是「以目前子樹範圍能給出的最佳答案」；在還沒同時看到兩側訊號之前，這個答案只能是「目前看到的那個目標」，一旦兩側訊號齊了，答案就升級成「目前節點自己」。

### Approach

1. Base case：若 `root` 為 `null`，或 `root` 本身就是 `p` 或 `q`，直接回傳 `root`。
2. 遞迴呼叫左子樹、右子樹，個別取得該側子樹回傳的結果。
3. 若左右兩側都回傳非 `null`，代表 `p`、`q` 分別在兩側，目前節點就是 LCA，回傳 `root`。
4. 否則兩者中只有一側非 `null`(或都是 `null`)，回傳非 `null` 的那一側(若兩側皆 `null` 則回傳 `null`)，把答案往上傳遞。

### Complexity

**Time complexity: `O(n)`**

`n` 是節點數，每個節點最多被拜訪一次。

**Space complexity: `O(h)`**

`h` 是樹高，即 call stack 深度上限；退化成鏈狀樹時最壞為 `O(n)`。

### Code

```java
/**
 * 236. Lowest Common Ancestor of a Binary Tree
 * Time Complexity: O(n)
 * Space Complexity: O(h), h = tree height (O(n) worst case)
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // If root is null, or root is one of the targets, it is the best answer this subtree can give.
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // Both sides found a target: p and q are on different sides, so root is the LCA.
        if (left != null && right != null) return root;
        // Only one side found something (or neither); pass that result up.
        return left != null ? left : right;
    }
}
```

### Code Review

- **Learning provenance**：`hint-assisted`；拿到「左右都非空時，當前節點扮演什麼角色」的提示後自己寫出程式碼。能否不靠提示獨立重現：未確認(同一題第三次卡在同一個地方，尚未觀察到不靠提示也能寫出來)。
- **Correctness / invariant**：`root == p || root == q` 提早回傳是正確性關鍵──即使某節點是另一節點的祖先，這個 early return 讓它能繼續往上被判定為 LCA；`left != null && right != null` 只在 p、q 分居兩側時成立，此時回傳 root 正確。
- **Strength**：程式碼精簡，沒有多餘的旗標或狀態變數，遞迴回傳值本身就承載了所有需要的資訊。
- **Bug**：無。
- **Trade-off**：遞迴解法在極度不平衡(退化成鏈狀)的樹上，call stack 深度會到 `O(n)`，有 stack overflow 風險；題目未給樹高上限時要留意。
- **Edge cases**：p 是 q 的祖先(或反之)、p 或 q 就是 root 本身、p 和 q 分別在最左與最右葉節點，這三種都被目前的 base case 與左右非空判斷正確涵蓋。

---

## 解法二：Parent Map + 祖先 Set

### Intuition

LCA 是 p、q 各自往 root 的祖先鏈上第一個交會的節點。二元樹節點沒有 parent 指標，所以先走訪一次，把每個節點的父節點記進 `Map`，「往上走」才有辦法執行。有了 parent map，問題就變成兩條鏈找交點，和 Intersection of Two Linked Lists 同一個結構。

做法是把 p 與它所有祖先(含 p 自己)放進 `Set`，再讓 q 由下往上走，第一個落在 `Set` 內的節點同時是 p、q 的祖先。因為 q 是由下往上走，第一個命中的就是最深的那個，也就是最近的。祖先鏈含節點自身，所以 p 是 q 的祖先時，答案自然是 p。

建 Map 時可以提早結束。節點是在它的父節點被彈出時才登記，父節點一定比子節點先在 Map 內，所以只要 p、q 都已登記，兩條祖先鏈就都完整，不必走完整棵樹。

這個解法適合節點本來就有 parent 指標、或同一棵樹要查多組 LCA 的情境(完整 parent map 建一次，之後每次查詢 `O(h)`)。

### Approach

1. `root` 對應 `null` 放進 `parentMap`，`root` 推入 `stack`。
2. 當 `p`、`q` 還有任一個不在 `parentMap` 裡，彈出一個節點，把它非 `null` 的左、右子節點登記進 `parentMap` 並推入 `stack`。
3. 從 `p` 開始往上走到 `null`，把沿途每個節點(含 `p`)加入 `ancestors`。
4. 從 `q` 開始往上走，第一個在 `ancestors` 內的節點就是 LCA，直接回傳。

### Complexity

**Time complexity: `O(n)`**

建 Map 最壞要走訪全部 `n` 個節點；兩段向上追溯各最多 `h` 步，`h` 最壞等於 `n`，合計仍是 `O(n)`。

**Space complexity: `O(n)`**

`parentMap` 最壞存 `n` 個節點；`stack` 最多 `O(h)`、`ancestors` 最多 `O(h)`，都被 `parentMap` 涵蓋。

### Code

```java
/**
 * 236. Lowest Common Ancestor of a Binary Tree
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Map each node to its parent.
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        parentMap.put(root, null);

        // Walk the tree with a stack to build the map.
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        // Stop early once both p and q have a recorded parent.
        while (!parentMap.containsKey(p) || !parentMap.containsKey(q)) {
            TreeNode curr = stack.pop();

            if (curr.left != null) {
                parentMap.put(curr.left, curr);
                stack.push(curr.left);
            }

            if (curr.right != null) {
                parentMap.put(curr.right, curr);
                stack.push(curr.right);
            }
        }

        // Collect p and all of its ancestors.
        Set<TreeNode> ancestors = new HashSet<>();
        while (p != null) {
            ancestors.add(p);
            p = parentMap.get(p);
        }

        // Climb up from q. The first node found in the set is the LCA.
        while (q != null) {
            if (ancestors.contains(q)) {
                return q;
            }
            q = parentMap.get(q);
        }

        return null;
    }
}
```

### Code Review

- **Learning provenance**：`reference-assisted`；參考他人的寫法後自己重寫並 AC。能否不看參考獨立重現：未確認。
- **Correctness / invariant**：節點在父節點被彈出時才登記，所以 `p`、`q` 都登記時，兩條祖先鏈上的每個節點都已在 Map 內，向上追溯不會斷。root 的 value 是 `null`，所以必須用 `containsKey` 而不是 `get(...) != null` 判斷是否已登記，這裡用對了。`ancestors` 含 `p` 自身，涵蓋 p 是 q 祖先的情況。
- **Strength**：提早結束讓最佳情況不必走完整棵樹；用顯式 `stack` 避開遞迴深度，退化成鏈狀的樹不會 stack overflow。`TreeNode` 沒覆寫 `equals`/`hashCode`，`HashMap`/`HashSet` 以節點身分比對，正是需要的語意。
- **Bug**：無(題目保證 `p`、`q` 都在樹內)。
- **Trade-off**：空間固定 `O(n)`，比解法一的 `O(h)` 多。若 `p` 或 `q` 不在樹內，`stack.pop()` 會在 stack 空時丟 `NoSuchElementException`，本題因保證存在所以不會發生。
- **Style**：迴圈內直接重新賦值參數 `p`、`q`，讀的人要追蹤它們已經不是原本的節點；改用 `curr` 之類的區域變數更清楚。時間／空間註解建議寫成 `O(n)`，不用小寫 `o`。
- **Edge cases**：p 或 q 就是 root(root 一開始就在 Map 內，只等另一個節點)、p 是 q 的祖先或反之、兩者分在最深的左右葉節點，都被正確處理。

---

## 解法比較

| 指標 | 解法一：後序遞迴 | 解法二：Parent Map + 祖先 Set |
| --- | --- | --- |
| 時間 | `O(n)` | `O(n)`，可提早結束 |
| 空間 | `O(h)` | `O(n)` |
| 遞迴深度風險 | 有，鏈狀樹最壞 `O(n)` | 無(顯式 `stack`) |
| 想法 | 回傳值向上匯聚，需要先理解回傳值的意義 | 兩條祖先鏈找交點，直觀 |
| 適用 | 一般 LCA，面試主要答案 | 節點有 parent 指標，或同一棵樹查多組 LCA |

### Optimality

時間指標下兩個解法都是 `O(n)`。最壞情況 p、q 可能藏在樹的兩個角落，必須拜訪過所有節點才能確定，`O(n)` 是下界。

空間指標下解法一的 `O(h)` 優於解法二的 `O(n)`，所以以空間衡量，解法一是比較好的選擇，也是面試時的預設答案。以可讀性與直觀程度衡量，解法二不需要理解「回傳值代表什麼」，比較容易自己從零推出來。

如果樹的節點本來就帶 parent 指標(LeetCode 1650)，不必建 Map，兩條鏈可以直接用雙指標找交點，空間降到 `O(1)`；解法二的祖先鏈概念可以直接沿用到那一題。

---

## 2025 初刷版（Day28，2025-05-15）

*原文見 [archive/doc/daily/day28-2025-05-15.md](../../archive/doc/daily/day28-2025-05-15.md)，已停更，內容按當時所寫原樣搬入*

### LC236. Lowest Common Ancestor of a Binary Tree

#### 題目說明
- 給定一棵二元樹，和其中兩個節點 `p` 與 `q`。
- 找出並回傳它們的最近公共祖先（LCA）。
- 注意：祖先可以是節點本身。

---

#### 解法：DFS 遞迴（後序遍歷自底向上）

##### 思路：邏輯與步驟
- 使用後序遍歷（postorder）自底向上，從左右子樹遞迴尋找 `p` 和 `q`。
- 若某節點的左、右子樹分別出現 `p` 和 `q`，則該節點就是 LCA。
- 若其中一邊找到兩者之一，另一邊為 null，則回傳非 null 節點（可能是 `p`、`q` 或其祖先）。
- 若當前節點本身為 `p` 或 `q`，直接回傳（即使它尚未遇到另一個節點）。

##### 重點：兩種情況說明

- 範例如：
  ```
      3
     / \
    5   1
   / \ / \
  6  2 4  8
  ```

###### ✅ 情況一：p 和 q 分別出現在左右子樹
  - 若 `p = 5`, `q = 1`，那麼節點 3 左右子樹各自找到 `p` 與 `q`，3 為最近公共祖先。

###### ✅ 情況二：p 或 q 本身就是最近公共祖先
  - `p = 5`, `q = 4`，其中 4 是 5 的子孫。
  - 當走到節點 5 時，發現自己就是 `p`，即使還未遍歷到 `q`，也會先回傳 `p`，最終由其上層確認是否為 LCA。

##### 為什麼這樣的遞迴可以涵蓋兩種情況？
- 因為遞迴會：
  - 把每個節點視為潛在答案，並從左右子樹回傳結果。
  - 利用返回值（left/right）向上彙整訊息，一步步傳遞哪邊找到 p/q。
  - 最早同時收集到兩者的節點，就是他們最近的公共祖先。

##### 複雜度分析
- 時間複雜度：O(n)，每個節點最多遍歷一次。
- 空間複雜度：O(h)，h 為樹高，遞迴呼叫堆疊。

---

#### 筆記
- 遞迴的返回值扮演「訊息向上傳遞」的角色，是整體解法關鍵。
- 若目標節點是另一個節點的祖先，遞迴會直接回傳該祖先節點作為答案。

---

#### Java 程式碼連結
- 題目實作：[ID236LowestCommonAncestorOfABinaryTree.java](../../archive/src/main/java/io/github/monty/leetcode/binarytree/ID236LowestCommonAncestorOfABinaryTree.java)
- 單元測試：[ID236LowestCommonAncestorOfABinaryTreeTest.java](../../archive/src/test/java/io/github/monty/leetcode/binarytree/ID236LowestCommonAncestorOfABinaryTreeTest.java)

---

## 相關

- [Binary Tree](../../topics/T10-21-binary-tree.md) ── 後序回傳、由當前節點角色判斷 LCA 的範例
- [Depth-First Search](../../topics/T10-12-depth-first-search.md)
- [LeetCode 刷題總覽](../../_moc.md)
