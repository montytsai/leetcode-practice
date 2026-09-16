# 廣度優先搜尋 基礎篇 (BFS basics)

> **這是哪一篇**：兩篇制的**基礎篇**（刷題前讀）。總結篇是 [breadth-first-search](T10-13-breadth-first-search.md)（已刷題目與實戰技巧住那邊）。上一層的地基是 [圖論 基礎篇](T14-01-graph-basics.md)，對照組是 [DFS 基礎篇](T14-02-depth-first-search-basics.md)。
>
> **講法**：仿李宏毅老師講給修資料結構與演算法的大學生。
>
> 理論骨架與插圖取自[代碼隨想錄 — 廣度優先搜索理論基礎](https://programmercarl.com/algo/graph/breadth-first-search-basics.html)（圖片已存本地 `assets/leetcode/bfs/`）。程式碼一律改寫成 Java（原文是 C++）。英文名詞在文末附錄。

---

## 第 0 節：一句話定位

廣搜是**一圈一圈**的搜索過程；深搜是**一條路跑到黑，然後回溯**。

這篇要講清楚三件事：**BFS 適合什麼題**、**一圈一圈到底怎麼做到的**、**模板長什麼樣**。

---

## 第 1 節：什麼時候該想到 BFS

### 主場：兩點之間的最短路徑

這是 BFS 的看家本領。因為它從起點出發、以起點為中心一圈一圈往外擴，所以——

> **一旦碰到終點，走過的那條路一定是最短路。**

不可能有更短的，因為更短的那幾圈已經先擴過了。這個保證 DFS 給不了。

### 副場：島嶼類問題（但 DFS 也可以）

有些題 BFS、DFS 都能解，島嶼問題就是。這類題的特徵是——

> **不在乎你用什麼順序遍歷，只要能把「相鄰且屬性相同」的節點標記起來就好。**

既然順序無所謂，那就兩個都行。這時候選哪個看你順手（實務上島嶼題用 DFS 寫比較短，但格子多的時候 BFS 比較不會爆堆疊）。

### 反面：有權重的最短路，BFS 不行

BFS 數的是「幾步」，不是「多少成本」。權重一旦不一樣，走 3 步（1+1+1）可能比走 1 步（權重 10）還便宜，BFS 一看到 1 步就收工，答案直接錯。那是 Dijkstra 的場子。

---

## 第 2 節：一圈一圈，到底怎麼個圈法

我們用方格地圖來看，假設每次能走**上下左右**四個方向（不含斜角）。給一個起點 `start`，BFS 的第一步就是往四個方向各走一格：

> 🖼 圖待重繪（mermaid，B94 階段四）——參考原圖：https://programmercarl.com/algo/graph/breadth-first-search-basics.html

加上終點 `end` 之後，整個搜索過程長這樣：

> 🖼 圖待重繪（mermaid，B94 階段四）——參考原圖：https://programmercarl.com/algo/graph/breadth-first-search-basics.html

格子上的編號就是**第幾步走到的**：編號 1 是第一步碰到的、編號 2 是第二步碰到的⋯⋯第四步的時候摸到終點。

**正因為是一圈一圈擴，所以碰到終點的那一刻，步數一定是最少的。**

### 有障礙物也一樣

> 🖼 圖待重繪（mermaid，B94 階段四）——參考原圖：https://programmercarl.com/algo/graph/breadth-first-search-basics.html

圖上只把關鍵節點染色（其他方向沒畫，不然太亂）。加了牆之後，變成第六步才走到終點——但性質不變：**第一次碰到就是最短**。

---

## 第 3 節：為什麼用佇列？（其實不一定要用）

這裡有個小知識，很多資料直接說「BFS 就是用佇列」，但真相是：

> **你只需要一個「能把待走的節點裝起來」的容器。用佇列、用堆疊、甚至用陣列，都可以。**

差別在轉圈的方向：

- **用佇列 (queue)**：先進先出，加入和取出的順序沒變 → 每一圈都朝同一個方向轉（統一順時針或逆時針）。
- **用堆疊 (stack)**：先進後出，順序被反轉 → 第一圈順時針、第二圈逆時針、第三圈又順時針。

那 BFS 在乎轉圈的方向嗎？**不在乎。** 反正一圈都要走完，先走哪個方向不影響「這一圈走完才走下一圈」這件事。

所以用佇列或堆疊都對，只是大家習慣用佇列。**但你要知道它為什麼可以，而不是背下來。**

> ⚠️ 注意這裡講的是「同一圈內的順序」。用堆疊**不會**讓它變成 DFS——DFS 是「還沒走完這一圈就先往下鑽」，BFS 是「這一圈全放進容器裡，才處理下一圈」。差別在**何時把鄰居放進容器**，不在容器本身。

---

## 第 4 節：程式碼模板（網格版）

這是針對上面那種四方格地圖的模板，Java 版：

```java
private static final int[][] DIRS = {{0,1},{1,0},{-1,0},{0,-1}};  // 四個方向

void bfs(char[][] grid, boolean[][] visited, int x, int y) {
    Queue<int[]> q = new ArrayDeque<>();
    q.offer(new int[]{x, y});      // 起點入隊
    visited[x][y] = true;          // 只要入隊，立刻標記

    while (!q.isEmpty()) {
        int[] cur = q.poll();
        int cx = cur[0], cy = cur[1];
        for (int[] d : DIRS) {
            int nx = cx + d[0], ny = cy + d[1];
            if (nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[0].length) continue;  // 先判界
            if (visited[nx][ny]) continue;
            q.offer(new int[]{nx, ny});
            visited[nx][ny] = true;   // 入隊當下就標記，避免重複入隊
        }
    }
}
```

圖（鄰接表）版更短，因為鄰居直接拿得到：

```java
Queue<Integer> q = new ArrayDeque<>();
q.offer(start);
visited[start] = true;
while (!q.isEmpty()) {
    int cur = q.poll();
    for (int next : g.get(cur)) {
        if (visited[next]) continue;
        visited[next] = true;
        q.offer(next);
    }
}
```

---

## 第 5 節：我要再講一次——入隊就標記

這句話你已經在 [圖論基礎篇](T14-01-graph-basics.md) 看過一次，在 [BFS 總結篇](T10-13-breadth-first-search.md)（lc-542）自己寫過一次，這是第三次：

> **標記 `visited` 的時機是 `offer` 的當下，不是 `poll` 出來的時候。**

為什麼？假設格子 X 同時是 A、B 兩格的鄰居。等出隊才標記的話，A 會把 X 放一次、B 又放一次——同一格重複入隊。圖大一點就是 TLE，再大一點記憶體直接爆。

這是 BFS 唯一的致命錯誤，其他都可以慢慢 debug，這個不行。

---

## 第 6 節：需要「第幾層」的時候怎麼寫（補充）

原文沒講，但你的題單一定會用到（lc-994 爛橘子要算分鐘數、lc-1926 要算步數、二元樹層序要分層輸出）。

做法：**在展開鄰居之前，先把當下的 `q.size()` 拍下來當這一層的邊界**，只處理這麼多個；這一輪新加進去的自然留給下一層。

```java
int steps = 0;
while (!q.isEmpty()) {
    int size = q.size();          // ← 快照：這一層有幾個
    for (int i = 0; i < size; i++) {
        int[] cur = q.poll();
        // ... 展開四個方向，符合條件就 offer + 標記
    }
    steps++;                      // 一整層走完，步數 +1
}
```

**關鍵是 `size` 一定要先存起來**，不能在 for 的條件裡直接寫 `q.size()`——那個值在迴圈中會一直變大，層就分不出來了。

### 多源 BFS（multi-source）

還有一種變形你已經寫過：**一開始就把所有起點全部丟進佇列**，再一起往外擴。lc-542 就是把所有 `0` 當起點，lc-994 是把所有爛橘子當起點。

心智模型：不是「很多個 BFS」，而是**一個 BFS，只是第 0 圈就有很多格**。

---

## 第 7 節：常見陷阱

- **出隊才標記**：第 5 節那個，頭號地雷。
- **忘記標記起點**：起點會被自己的鄰居再塞一次。
- **分層時把 `q.size()` 寫進迴圈條件**：層數全錯。
- **邊界檢查寫在讀值後面**：先判 `nx`、`ny` 在界內，再讀 `grid[nx][ny]`。
- **拿 BFS 解有權重的最短路**：見第 1 節，答案會錯而不是慢。
- **用 `LinkedList` 當 queue 順手就用 `add`／`remove`**：能動，但 `ArrayDeque` + `offer`／`poll` 比較快也比較不會誤用。

---

## 第 8 節：開刷前自我檢查

1. 為什麼 BFS 碰到終點就一定是最短路？這個保證 DFS 為什麼給不了？
2. 島嶼題為什麼 BFS、DFS 都行？這類題的共同特徵是什麼？
3. BFS 一定要用佇列嗎？用堆疊會變成什麼？為什麼不會變成 DFS？
4. `visited` 為什麼一定要在入隊時標記？出隊才標記的具體後果是什麼？
5. 要算「第幾步／第幾分鐘」時，模板要多寫哪一行？為什麼那一行不能寫進迴圈條件？
6. 多源 BFS 跟一般 BFS 差在哪？

---

## 已刷題目

見總結篇 [breadth-first-search](T10-13-breadth-first-search.md)（本篇只管地基，不重複列題）。

## 相關

- [圖論 基礎篇](T14-01-graph-basics.md) — 上一層地基
- [DFS 基礎篇](T14-02-depth-first-search-basics.md) — 對照組（回溯在幹嘛）
- [breadth-first-search](T10-13-breadth-first-search.md) — 總結篇（已刷題目與實戰技巧）
- [_index](_index.md)、[_moc](../_moc.md)、[刷題規劃](../_moc.md)

---

## 附錄：名詞中英對照

| 中文 | English | 備註 |
| --- | --- | --- |
| 廣度優先搜尋 | BFS (breadth-first search) | 一圈一圈擴散 |
| 佇列 | queue | 先進先出；Java 用 `ArrayDeque` |
| 堆疊 | stack | 先進後出；BFS 也能用，只是轉圈方向會變 |
| 層 | level | 分層 BFS 的一圈 |
| 多源廣搜 | multi-source BFS | 一開始就放進多個起點 |
| 最短路徑 | shortest path | 無權圖用 BFS，有權圖用 Dijkstra |

面試講法：

```text
BFS expands level by level, so the first time a node is dequeued
we have reached it in the fewest number of steps.
I mark a node as visited when I push it, not when I pop it,
otherwise the same node can be enqueued by several neighbors.
To count steps, I snapshot the queue size before expanding a level.
```
