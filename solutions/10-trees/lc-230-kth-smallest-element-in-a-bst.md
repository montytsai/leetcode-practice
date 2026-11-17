---
title: Kth Smallest Element in a BST
difficulty: Medium
topics: [Binary Tree, Tree, Depth-First Search, Binary Search Tree]
category: 10-trees
order: 47
source: [Grind75]
platform: LeetCode
url: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
status: ac-assisted
note: "迭代解：先複習迭代走訪模板，再自行完成本題，未表示看過本題解答。遞迴解：原本以方法參數傳 k，詢問 AI 後改用成員欄位共享剩餘排名。"
date_created: 2026-11-17
date_updated: 2026-11-17
---

# 230. Kth Smallest Element in a BST

## 題目說明

- 給定 BST 的根節點與從 1 開始的排名 k，回傳第 k 小的節點值。
- 節點數為 n，題目保證 1 <= k <= n，輸入樹不為空。

## 心得

迭代有點忘記有先去複習一下

遞迴我原本把k放在方法參數 不確定k要怎增減 問AI改成放全域

---

## 解法一：迭代中序走訪與提早結束

### Intuition

BST 的中序走訪依數值遞增排列，因此第 k 個被拜訪的節點就是答案。不必先建立完整的排序序列，只要依序產生節點並計數，到達目標排名就停止。

迭代中序使用 stack 保存尚未處理的祖先。沿左分支下降時，這些節點只是排定稍後處理，還沒有被拜訪；直到左子樹處理完並從 stack 取出節點，才輪到它的排名。

每次取出節點後，下一段搜尋範圍是它的右子樹。重新下降到右子樹的最左側，便能延續同一個遞增順序。stack 讓我們保留返回祖先的路徑，不需要修改樹的指標。

### Approach

1. 準備空 stack，令 curr 指向 root。
2. 沿 curr 的左分支逐一入 stack，直到沒有左側節點。
3. 從 stack 取出下一個節點，此時左子樹已處理完，這才算一次中序拜訪。將 k 減一，若為零便回傳節點值。
4. 令 curr 指向該節點的右子樹，繼續處理。外層在 curr 非空或 stack 還有節點時維持運作。

Invariant：每次計數前取出的節點，都是所有尚未拜訪節點中最小的一個；k 代表距離目標還需要的拜訪次數。

### Complexity

- **Time complexity: O(h + k)，最壞 O(n)**。h 為樹高，k 為原始輸入排名。回傳前恰好 pop k 次；已 push 但尚未 pop 的節點最多有 h 個，所以 push 與 pop 的總工作量為 O(h + k)。左側長鏈即使 k = 1，也可能先走 h 個節點。
- **Space complexity: O(h)**。stack 只保存一條尚未完成的祖先路徑；平衡樹為 O(log n)，退化成長鏈時為 O(n)。

### Code

```java
/**
 * 230. Kth Smallest Element in a BST
 * Time Complexity: O(h + k), O(n) in the worst case
 * Space Complexity: O(h)
 */
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) { // Follow the left path.
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            if (--k == 0) {
                return curr.val;
            }

            curr = curr.right;
        }

        return -1;
    }
}
```

### Code Review

- **Learning provenance**：使用者表示先複習迭代走訪，再自行寫出本題並 AC。以 ac-assisted 記錄參考模板的協助，細節保留在 note；沒有證據顯示看過本題解答，也不由 AC 推定能獨立重寫。
- **Correctness / invariant**：左側下降只入 stack，pop 後才減 k，正確區分排程與拜訪。右子樹接回同一流程，維持遞增順序。外層 OR 確保 curr 暫時為空時，仍能回到 stack 裡的祖先。
- **Strength**：第 k 次拜訪就返回，避免建立完整陣列；使用 Deque 與 ArrayDeque，且不修改輸入樹。
- **Bug**：在題目合法輸入下未發現邏輯錯誤。題目保證有效 k，因此最後的 return -1 不會被執行。
- **Trade-off**：顯式 stack 需要 O(h) 空間，但沒有遞迴呼叫深度的風險。樹退化時空間仍可能達 O(n)。
- **Style**：--k 清楚表達剩餘排名；不需要為風格更動 AC 邏輯。訊息中的兩段程式完全相同，只保留一份解法。
- **Edge cases**：單節點 k = 1、k = 1 的左側長鏈、k = n，以及只有右分支的樹，都符合相同的計數規則。數值只讀取與回傳，沒有額外算術溢位風險。

---

## 解法二：遞迴中序走訪與共享剩餘排名

### Intuition

遞迴中序同樣依照左子樹、目前節點、右子樹的順序取得遞增值。排名計數必須延續整段走訪，讓左子樹消耗的次數能影響祖先與右子樹。

Java 的 int 參數採 pass-by-value，每層遞迴收到各自的值。只修改其中一層的 k，不會更新呼叫端的 k。這份解法以同一個 Solution 實例的 count 欄位共享剩餘排名，每次 public 方法開始時設為 k，並在真正拜訪節點時減一。

遞迴的回傳值負責傳遞答案：非 null 代表已找到目標節點，null 代表這個子樹尚未找到。左子樹一旦找到答案就立即向上傳，避免繼續計算祖先或進入其他右子樹。

### Approach

1. 每次呼叫 kthSmallest 時，將 count 初始化為本次的 k。
2. inorder 遇到空節點回傳 null；非空時先搜尋左子樹。
3. 左子樹若回傳節點，直接回傳相同答案。否則拜訪目前節點，令 count 減一；為零時回傳目前節點。
4. 若答案尚未出現，直接回傳右子樹的搜尋結果，讓答案沿呼叫鏈傳回 public 方法。

Invariant：答案出現前，count 等於原始 k 減去已拜訪的節點數；答案出現後，各層只傳遞該節點，不再拜訪其他節點。

### Complexity

- **Time complexity: O(h + k)，最壞 O(n)**。h 為樹高，k 為原始排名。只拜訪前 k 個節點，另有沿搜尋路徑下降但尚未拜訪的祖先與空節點呼叫；這些成本合計為 O(h + k)。答案找到後立即沿呼叫鏈返回。
- **Space complexity: O(h)**。遞迴呼叫堆疊最深為樹高；平衡樹為 O(log n)，退化樹為 O(n)。count 與每層的區域變數各占常數空間。

### Code

```java
/**
 * 230. Kth Smallest Element in a BST
 * Time Complexity: O(h + k), O(n) in the worst case
 * Space Complexity: O(h)
 */
class Solution {
    private int count; // KEY! 需要用全域變數正確累加上層的遞迴

    public int kthSmallest(TreeNode root, int k) {
        count = k;
        TreeNode ans = inorder(root);
        return ans != null ? ans.val : -1;
    }

    private TreeNode inorder(TreeNode curr) {
        if (curr == null) {
            return null;
        }

        TreeNode left = inorder(curr.left);
        if (left != null) {
            return left;
        }

        count--;
        if (count == 0) {
            return curr;
        }

        return inorder(curr.right);
    }
}
```

### Code Review

- **KEY! / 狀態共享**：Java 的 int 參數採 pass-by-value，每層收到自己的 k 副本；子呼叫修改它，不會回寫呼叫端。原註解逐字保留，精確名稱是 instance field，並非真正的全域變數；count 從 k 開始逐次減一，記錄的是剩餘排名。同一個 Solution 實例上的遞迴呼叫共享這個欄位。遞迴不一定需要欄位，也能設計回傳剩餘次數；此處只說明這份 AC 寫法。
- **Learning provenance**：使用者表示原本將 k 放在方法參數，詢問 AI 後改為欄位，因此此解為 AI-assisted，status 維持 ac-assisted。只記錄這項明說的協助，不推定 AI 寫出完整解法或已能獨立重寫。
- **Correctness / invariant**：左子樹先消耗排名；left 非 null 時立刻回傳，跳過祖先與其餘右子樹。右子樹結果以 return 直接向上傳遞。null 代表該子樹尚無答案，非 null 代表已找到第 k 個節點。
- **Strength**：每次 public 方法都重新設定 count，循序重複呼叫同一個 Solution 實例也能正確開始；找到答案即停止，不修改輸入樹。
- **Bug**：在題目保證的合法輸入與一般單次呼叫方式下未發現邏輯錯誤。
- **Trade-off**：退化樹的遞迴深度可能造成 Java call stack 溢位。count 是可變成員狀態，同一個實例不適合同時或重入執行多次查詢；這是狀態設計的限制，並非本題一般呼叫方式下的 bug。
- **Edge cases / Style**：k = 1、k = n 與單節點樹都維持相同回傳規則；合法 k 必定找到節點，public 方法的 -1 是防守回傳值。原始 KEY! 註解依使用者要求保留，其餘只整理格式，未更動 AC 邏輯。

---

## 解法比較

| 解法 | Time | Space | 優點 | Trade-off | 使用時機 |
| --- | --- | --- | --- | --- | --- |
| 迭代中序 | O(h + k)，最壞 O(n) | O(h) 顯式 stack | 排名與路徑都留在本次方法內，沒有遞迴深度風險 | 需自行維護左側下降與 pop 時機 | 樹可能很深，或希望避免成員狀態 |
| 遞迴中序 | O(h + k)，最壞 O(n) | O(h) call stack | 走訪順序直觀，回傳值能立即傳遞答案 | 深樹有 call stack 風險；此寫法使用可變成員欄位 | 樹高受控，並希望直接表達中序遞迴 |

### Optimality

以**單次查詢的最壞時間**衡量，原始 BST 未附子樹大小，且不能預先建立索引時，兩解的 O(n) 已達漸近最佳：退化樹或接近尾端的排名都可能需要讀取線性數量的節點。兩者的 O(h + k) 都表達提早結束的收益，不能一律簡寫成 O(k)。迭代解避免遞迴深度風險；遞迴解直接表達中序順序。兩者的 O(h) 額外空間均不宣稱為所有方法中的最小值。

若同一棵樹需要反覆查詢、插入與刪除，可改用記錄每個節點子樹大小的 BST。以左子樹大小判斷往哪邊走，建置 metadata 需要 O(n) 時間與 O(n) 空間；單次查詢及維護大小的更新為 O(h)，平衡樹可達 O(log n)。這個取捨適合多次操作，單次查詢不必額外建置。

## 相關

- [Binary Tree](../../topics/T10-21-binary-tree.md) — 比較迭代與遞迴中序的拜訪時機。
- [Depth-First Search](../../topics/T10-12-depth-first-search.md) — 顯式 stack 與遞迴狀態共享、答案回傳。
- [Binary Search Tree](../../topics/T10-11-binary-search-tree.md) — 以中序遞增順序回答排名問題。
- [LeetCode 刷題總覽](../../_moc.md)
