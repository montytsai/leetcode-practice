---
title: "Binary Tree Right Side View"
difficulty: Medium
topics: [Binary Tree, Tree, Depth-First Search, Breadth-First Search]
category: 10-trees
order: 6
source: [Carl, LeetCode75]
platform: LeetCode
status: ac-solo
note: ""
date_created: 2026-03-17
date_updated: 2026-09-14
---

# 199. Binary Tree Right Side View

## 題目說明

- 給定一棵二元樹的 `root`，站在樹的右側往左看。
- 由上到下回傳每一層「最右邊看得到」的節點值。

## 心得

得心應手秒解。

---

## 解法一：BFS 逐層取最後一個節點

### Intuition

站在右側看，每一層看到的就是那一層最右邊的節點。逐層走訪（level order）時，只要照順序處理完一整層，該層最後被處理到的節點就是最右邊的節點——不需要額外判斷「是不是最右邊」，用走訪順序本身取代判斷。

### Approach

1. `root` 為 `null` 時直接回傳空陣列。
2. 用一個 queue 做標準 BFS，起點放入 `root`。
3. 外層迴圈每次代表一層：先用 `size = queue.size()` 鎖定這一層有幾個節點。
4. 內層迴圈跑 `size` 次，每次 `poll` 一個節點、把它的子節點依序 `offer` 進 queue，並把目前節點的值存進 `val`。
5. 內層迴圈結束時，`val` 停留在這一層最後一個被處理的節點，也就是最右邊的節點；把 `val` 加入結果。
6. 重複步驟 3 到 5，直到 queue 清空。

### Complexity

**Time complexity: `O(n)`**

`n` 為節點數；每個節點恰好被加入 queue 一次、處理一次。

**Space complexity: `O(w)`**（不含輸出，`w` 為樹的最大寬度）

queue 在任一時刻最多同時存放一層的節點，最壞情況（接近滿的二元樹）`w` 可達 `O(n)`。

### Code

```java
/**
 * 199. Binary Tree Right Side View
 * Time Complexity: O(n)
 * Space Complexity: O(w) extra space, where w is the widest level
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            int val = 0;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                val = node.val;

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            res.add(val);
        }

        return res;
    }
}
```

### Code Review

- **Learning provenance**：對應 `status: ac-solo`——她表示這題得心應手、秒解完成。
- **Correctness / invariant**：正確。`val` 在每層的內層迴圈開始前雖然歸零，但只要 queue 非空就保證 `size > 0`，內層迴圈至少執行一次，`val` 一定會被真正的節點值覆寫，不會有讀到初始值 `0` 誤判的風險。
- **Strength**：用 `queue.size()` 鎖住當層節點數這個標準手法，把「這一層最後處理到的節點＝最右節點」直接寫成迴圈順序，不需要額外變數記錄層內的 index 或位置。
- **Trade-off**：無明顯缺點；子節點固定先 `left` 後 `right` 加入 queue，因為靠 queue 本身的長度分層，加入順序不影響「取最後一個」的正確性。
- **Edge cases**：空樹已由開頭的 `null` 檢查處理；只有左子節點或只有右子節點的層，`val` 一樣會正確落在該層唯一存在的節點上。

---

## Optimality

時間 `O(n)` 已經是下限——每個節點都要檢查一次才能確定它是否是該層最右邊的節點。空間的最佳選擇則要看樹的形狀：這個 BFS 解法的額外空間是 `O(w)`（最大寬度），對又高又窄的樹（例如一路往左延伸的樹）`w` 很小，效率很好；但對接近滿二元樹的寬樹，`w` 可以逼近 `O(n)`。

唯一值得認識的替代法是 **DFS（先右後左）**：遞迴時先走右子樹再走左子樹，並帶一個 `depth` 參數，只要 `depth == res.size()`，代表這一層第一次被走到的節點就是最右節點，直接加入結果。它的額外空間是 `O(h)`（樹高，來自遞迴呼叫堆疊），對又高又窄的樹反而比 BFS 差（`h` 逼近 `n`），但對寬而矮的樹比 BFS 好（`h` 只有 `O(log n)`)。兩種寫法在時間上都是 `O(n)`，在空間上互有優劣，沒有哪一個在所有樹形下都嚴格更好，選哪個純粹看預期的樹的形狀或個人偏好。

## 相關

- Binary Tree — 這題是「逐層取層內特定位置節點」的代表題，對照 [binary-tree](../../topics/T10-21-binary-tree.md)。
- Breadth-First Search — 用 `queue.size()` 鎖住當層節點數的標準模板，對照 [breadth-first-search](../../topics/T10-13-breadth-first-search.md)。
- [LeetCode 刷題總覽](../../_moc.md)
