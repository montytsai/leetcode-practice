---
title: "Clone Graph"
difficulty: Medium
topics: [Graph, Hash Table, Depth-First Search, Breadth-First Search]
category: 14-graphs
order: 3
source: [Grind75]
platform: LeetCode
url: https://leetcode.com/problems/clone-graph/
status: ac-unknown
note: ""
date_created: 2026-09-01
date_updated: 2026-09-01
---

# 133. Clone Graph

## 題目說明

- 給一個連通無向圖的某個節點，回傳整張圖的深拷貝：每個節點都要是新物件，鄰居關係一模一樣。
- `1 <= Node.val <= 100`，`val` 唯一，每條邊雙向，沒有重邊與自環。
- 邊界：`node == null` 回 `null`；單一節點無鄰居。

## 心得

跟樹概念很像，先寫了第一版進入無窮迴圈，才看圖論 basic.md 然後才加入 visited 概念，加入後又少想一步。

同日再用 BFS 寫一次，卡的點換成「visited 要放哪」。

---

## 解法一：DFS ＋ old→new 對照表

### Intuition

複製圖與複製樹的差別在於節點的入邊數。樹中每個節點只有一個父節點，走到它、建立新節點、掛回去，這個節點就處理完了；圖中同一個節點可能是多個節點的鄰居，第二次抵達時需要的是**先前已建立的那個複本**，而不是再建一個新的。

因此需要一張「舊節點 → 新節點」的對照表（一般寫成 `Map<Node, Node>`）。它同時回答兩件事：這個節點複製過了嗎、複製品在哪裡。

第一版失敗的原因就在這裡。`boolean[] visited` 只答得出前者；查不到複本就只能跳過該鄰居，於是節點都複製出來了，邊卻遺失。

這張對照表也順帶終止了遞迴。無向邊 `A—B` 本身即構成環，第二次抵達時直接回傳現成複本，遞迴自然收斂。

本題 `val` 唯一且介於 1 到 100，可用長度 101 的陣列取代 HashMap，即直接定址表（雜湊表在 key 為小範圍連續整數時的退化形式）。

### Approach

1. `node == null` 直接回 `null`。
2. 進 `dfs` 先查對照表：`visited[node.val] != null` 就回傳那個 clone。
3. 沒複製過就 `new Node(node.val)`，**立刻寫進 `visited`，然後才遞迴鄰居**。
4. 逐一遞迴鄰居，把回傳的 clone 加進 `clone.neighbors`。
5. 回傳 clone。

invariant：`visited[v]` 一旦有值，那個 clone 的 `val` 就已正確，鄰居清單保證在遞迴結束前補完。

第 3 步的順序是命根子。先遞迴再登記的話，環會讓同一個節點被無限展開。這跟 BFS「入隊當下就標記」是同一條規則。

### Complexity

令 `V` 為節點數、`E` 為邊數。

**Time complexity: `O(V + E)`**

每個節點只在對照表為空時建立一次（貢獻 `V`），每條邊在兩端各被看一次（貢獻 `2E`）。

**Space complexity: `O(V)`**

對照表固定 101 格，遞迴堆疊最深 `O(V)`。回傳的新圖是輸出，不計入。

### Code

```java
/**
 * 133. Clone Graph
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 */
class Solution {

    public Node cloneGraph(Node node) {
        // val is unique and 1 <= val <= 100, so an array can replace a HashMap.
        Node[] visited = new Node[101];
        return dfs(node, visited);
    }

    private Node dfs(Node node, Node[] visited) {
        if (node == null) {
            return null;
        }

        // KEY! Already cloned: return the old clone instead of skipping this
        // neighbor. Skipping would drop the edge and break the copy.
        if (visited[node.val] != null) {
            return visited[node.val];
        }

        Node clone = new Node(node.val);
        // KEY! Register the clone BEFORE we recurse, or a cycle never ends.
        visited[node.val] = clone;

        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(dfs(neighbor, visited));
        }

        return clone;
    }

}
```

### Code Review

- **Learning provenance**：`reference-assisted`。第一版自己寫（無窮迴圈），讀圖論基礎篇後補上 visited 仍答錯，最後看他人解答才 AC。能否獨立重現：未確認。
- **Correctness**：正確。去環與補邊由同一個 `visited` 承擔。
- **Strength 1**：`visited` 走參數不走 instance field，沒有跨測資殘留狀態。第一版是 instance field，`Solution` 一被重用，第二筆測資整張圖會被當成走過。
- **Strength 2**：用上了題目約束（`val` 唯一且 ≤ 100），陣列取代 HashMap。
- **Strength 3**：陣列開 101 而不是第一版的 100。`val` 可以等於 100，第一版那個大小會 `ArrayIndexOutOfBoundsException`。
- **Trade-off**：陣列做法綁在「`val` 是唯一且範圍小的整數」上。面試較安全的預設是 `Map<Node, Node>`（用節點物件當 key），再補一句「題目保證 val ≤ 100，可以換成陣列優化」。
- **Style**：`new Node[101]` 是魔術數字，值得一行註解綁回約束（已補）。`visited` 這名字名不副實，它存的不是走訪與否而是對應的新節點，叫 `cloned` 或 `oldToNew` 更準。
- **Edge cases**：`node == null` 已處理。`neighbors` 為空清單時迴圈不跑，回傳孤點。第一版有的 `neighbor != null` 檢查，AC 版拿掉了——依約束成立，但那是靠約束不是靠邏輯。

---

## 解法二：BFS ＋ 同一份 old→new 對照表

### Intuition

BFS 版的對照表角色不變，差別只在走訪順序與待辦容器。

關鍵在登記時機：**建立複本、寫入對照表、入隊，必須是同一個動作**，在「發現」鄰居的當下完成，而不是在「處理」（出隊）時。若延後到出隊才登記，同時是多個節點鄰居的那個節點會被重複入隊、重複處理，它的複本會取得重複的鄰居清單。

這一點在一般 BFS 只是效能問題，重複入隊只是慢；在複製圖時它升級為正確性問題，直接產生錯誤答案。

另一件要分清楚的是兩個世界：**佇列存放原圖節點，對照表存放新圖節點**。整個流程是從舊圖讀取鄰居關係、往新圖寫入鄰居關係，所以 `queue.offer()` 傳入的必須是原節點。

### Approach

1. `node == null` 回 `null`。
2. 起點先登記再入隊：建 clone 寫進 `visited`，然後 `queue.offer(node)`（放的是原節點）。
3. 每次 `poll` 一個舊節點 `curr`，用 `visited[curr.val]` 取出它的 clone。
4. 走訪 `curr.neighbors`：
   - 沒登記過 → 建 clone、寫進表、把原鄰居入隊
   - 不論新舊，都把 `visited[next.val]` 接到 `clone.neighbors`
5. 佇列清空後，新圖完成。

第 4 步的接邊那行必須在 `if` 外面。放進 `if` 裡就退化成第一版 DFS 的錯誤。

invariant：一個舊節點只會被 `offer` 一次，所以它的 clone 的鄰居清單只會被填一次，不會有重複邊。

### Complexity

**Time complexity: `O(V + E)`**

每個節點入隊、出隊各一次，每條無向邊在兩端各被看一次。

**Space complexity: `O(V)`**

對照表固定 101 格，佇列最多同時裝 `O(V)` 個節點。跟 DFS 版的差別是不吃遞迴堆疊。

### Code

```java
/**
 * 133. Clone Graph (BFS)
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 */
class Solution {

    public Node cloneGraph(Node node) {
        if (node == null) return null;

        // visited holds NEW nodes; the queue holds ORIGINAL nodes.
        Node[] visited = new Node[101];
        Queue<Node> queue = new ArrayDeque<>();

        // KEY! Create the clone BEFORE the offer, for the start node too.
        visited[node.val] = new Node(node.val);
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();       // original node
            Node clone = visited[curr.val]; // its copy, still being filled

            for (Node next : curr.neighbors) {
                if (visited[next.val] == null) {
                    visited[next.val] = new Node(next.val);
                    queue.offer(next);      // offer the ORIGINAL, not the clone
                }
                // KEY! This line stays OUTSIDE the if, or known neighbors
                // would be skipped and the edge would disappear.
                clone.neighbors.add(visited[next.val]);
            }
        }

        // NOTE: this passes only because LeetCode always hands us node 1.
        // The contract is "return the copy of the given node" -> visited[node.val].
        return visited[1];
    }

}
```

### Code Review

- **Learning provenance**：`self-solved`（同題第二種寫法，在已理解 DFS 版之後）。卡點是 visited 放哪，想很久後放對。能否獨立重現：未確認。
- **要改的一處**：`return visited[1]` 應為 `return visited[node.val]`。

  函式的契約是「回傳傳進來那個節點的複本」，`visited[1]` 只有在「傳進來的一定是 val 為 1 的節點」時才等價。LeetCode 的測資序列化永遠從節點 1 開始、交給你的也是節點 1，所以會 AC——但這是靠評測平台的慣例，不是靠題目寫明的約束。起點若不是 1，回傳的會是別人的圖。

  DFS 版沒有這個問題，因為它直接回傳遞迴結果。BFS 把「起點」和「答案」拆成兩個地方，就多了一個對不起來的機會。
- **Correctness**：除上一項外邏輯正確。登記與入隊綁在一起，每個舊節點只被處理一次；接邊在 `if` 外面，邊不會掉。
- **Strength 1**：起點也先登記再入隊。這是 BFS 最容易漏的一行——只標記鄰居、忘了標記起點，起點會被自己的鄰居再塞回來。
- **Strength 2**：`clone.neighbors.add(...)` 放在 `if` 外面。這正是第一版 DFS 踩爆的坑，換一個完全不同的寫法沒有再踩。
- **Strength 3**：用 `ArrayDeque` 而不是 `LinkedList`。同樣是 `Queue`，`ArrayDeque` 沒有每個元素配一個 node 物件的開銷。
- **Style**：`Node clone = visited[curr.val]` 提到迴圈外，語意清楚。`visited` 的命名問題同解法一。
- **Edge cases**：`node == null` 已處理。孤點回傳只有一個節點的新圖。`ArrayDeque` 不接受 `null` 元素，本題鄰居保證非 null，安全。

---

## 解法比較

| 解法 | Time | Space | 優點 | Trade-off | 使用時機 |
| --- | --- | --- | --- | --- | --- |
| 解法一 DFS | `O(V + E)` | `O(V)`（含遞迴堆疊） | 程式碼最短，查表那行同時當 base case | 吃遞迴堆疊，節點數大時可能 `StackOverflowError` | 面試限時手寫、圖不大時的預設 |
| 解法二 BFS | `O(V + E)` | `O(V)`（佇列，無遞迴堆疊） | 不吃呼叫堆疊，深圖也安全 | 多一個佇列、程式碼長幾行 | 節點數可能到 `10^5`，或本來就要用 BFS 的題型 |

### Optimality

兩個解法都已達漸進最佳，本題沒有更優的解法可追。

下限來自問題本身：複製整張圖必須走訪每個節點與每條邊，時間下限為 `O(V + E)`；舊節點到新節點的對應關係必須被記錄，空間下限為 `O(V)`。兩解皆達到。

其餘只是取捨：

- 時間與空間：平手，同階。
- 手寫速度與可讀性：DFS 略勝，少一個佇列、少幾行。
- 堆疊安全：BFS 勝。本題 `V <= 100` 無影響，但同一模式在 `10^5` 節點時就是會不會 `StackOverflowError` 的差別。
- 面試價值：平手，差別在能否說出取捨。可講「我用 DFS 因為短；圖可能很深就改 BFS 避免堆疊溢位」。

兩解的差異只在待辦容器：DFS 用呼叫堆疊，BFS 用佇列。對照表、先登記後展開、接邊那行不放進條件式，這三件事完全相同，才是本題的本體。

因此不需要再追第三種寫法，下一步的價值在把這套搬去結構不同的題（島嶼系列、拓撲排序）。

## 相關

- [圖論 基礎篇](../../topics/T14-01-graph-basics.md) — 樹不用 visited、圖一定要 visited；以及 visited 可以存值不只存旗標
- [Hash Table](../../topics/T01-22-hash-table.md) — old → new 對照表；key 是 1..100 的唯一整數，直接用陣列當直接定址表
- [Depth-First Search](../../topics/T10-12-depth-first-search.md) — 解法一：base case 從「節點是 null」變成「對照表已有值」
- [Breadth-First Search](../../topics/T10-13-breadth-first-search.md) — 解法二：先登記再入隊
- [LeetCode 刷題總覽](../../_moc.md)
