---
title: "01 Matrix"
difficulty: Medium
topics: [DP, Array, Dynamic Programming, Breadth-First Search, Matrix]
category: 14-graphs
order: 2
source: [Grind75]
platform: LeetCode
url: https://leetcode.com/problems/01-matrix/
status: ac-unknown
note: ""
date_created: 2026-08-17
date_updated: 2026-08-17
---

## 心得

原本想用遞迴一次查看上、下、左、右四個方向，並用 `ans[][]` 記錄結果；但相鄰格的答案可能都還沒算完，彼此又會互相參考，因此形成循環依賴，最後可能無窮遞迴。

經過一次「一次掃描只看已經計算完成的方向」的提示後，改成兩次相反方向的掃描。第一次從左上往右下，只參考已完成的上方與左方；第二次從右下往左上，只參考已完成的下方與右方，再和第一次的結果取較小值。這樣四個方向都有被考慮，但每一輪都只讀取安全的狀態。

核心心得：DP 的掃描方向決定哪些狀態可以安全參考；把四個方向拆成兩次相反掃描，就能消除循環依賴。

第二個 AC 解法是看過 AI 的 Multi-source BFS 解法後再寫。看完可以理解「所有 0 同時往外擴散」的做法，但完全想不到這題可以用 BFS；目前不能宣稱已經能從題意獨立想到這個方向。程式裡的 `KEY!` 是理解後自己默寫時仍寫錯的部分：要跳過已處理的格子，以及距離必須從上一層的 `{r, c}` 指派給下一層的 `{nr, nc}`。

## Java

### Two-pass DP

```java
/**
 * Dynamic Programming: scan twice and only use completed directions.
 *
 * Time: O(m * n)
 * Space: O(m * n) for the output and O(1) auxiliary space
 */
class Solution {

    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int maxDistance = (m - 1) + (n - 1); // The longest possible distance.

        int[][] ans = new int[m][n];

        // First pass: scan from top-left to bottom-right and use top and left.
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (mat[row][col] == 0) {
                    ans[row][col] = 0;
                    continue;
                }

                int top = row > 0 ? ans[row - 1][col] : maxDistance;
                int left = col > 0 ? ans[row][col - 1] : maxDistance;
                ans[row][col] = Math.min(top, left) + 1;
            }
        }

        // Second pass: use bottom and right, then keep the smaller result.
        for (int row = m - 1; row >= 0; row--) {
            for (int col = n - 1; col >= 0; col--) {
                if (mat[row][col] == 0) {
                    ans[row][col] = 0;
                    continue;
                }

                int bottom = row < m - 1 ? ans[row + 1][col] : maxDistance;
                int right = col < n - 1 ? ans[row][col + 1] : maxDistance;
                ans[row][col] = Math.min(ans[row][col], Math.min(bottom, right) + 1);
            }
        }

        return ans;
    }

}
```

- 時間：O(m * n)。雖然會掃描矩陣兩次，但每一格只做固定次數的運算，因此省略常數後仍是 O(m * n)。
- 空間：回傳的 `ans` 矩陣使用 O(m * n)；若不計輸出空間，auxiliary space 是 O(1)。

### Multi-source BFS

```java
/**
 * BFS: use a queue to expand from every zero.
 *
 * Time: O(m * n)
 * Space: O(m * n)
 */
class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] dirR = new int[] { -1, 1, 0, 0 };
        int[] dirC = new int[] { 0, 0, -1, 1 };
        Queue<int[]> queue = new ArrayDeque<>();
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (mat[r][c] == 0) {
                    queue.offer(new int[] { r, c });
                } else {
                    mat[r][c] = -1;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0], c = cell[1];
            for (int i = 0; i < 4; i++) {
                int nr = r + dirR[i];
                int nc = c + dirC[i];
                // KEY! Skip cells outside the matrix or cells that were already processed.
                if (nr < 0 || nr >= m || nc < 0 || nc >= n || mat[nr][nc] != -1) {
                    continue;
                }
                // KEY! Set the next layer from the current {r, c} cell.
                mat[nr][nc] = mat[r][c] + 1;
                queue.offer(new int[] { nr, nc });
            }
        }
        return mat;
    }
}
```

- 時間：O(m * n)，因為每一格最多入隊與出隊一次。
- 空間：queue 的 auxiliary space 最差為 O(m * n)。這個版本直接把距離寫回 `mat`，不另外配置結果矩陣。

## Code Review

### Learning provenance

- Two-pass DP：`hint-assisted`。收到「一次掃描只看已完成方向」的提示後完成 AC；目前能否不看參考獨立重現，未確認。
- Multi-source BFS：`AI-assisted / reference-assisted`。看過 AI 解法後可以理解並默寫成 AC，但我明確表示完全想不到 BFS。兩個 `KEY!` 是理解後重寫仍出錯的點，不等於已獨立掌握。

### Correctness and invariant

- 兩個 `KEY!` 都是正確性關鍵：判斷 `mat[nr][nc] != -1` 可跳過已處理格；距離必須由目前層 `mat[r][c]` 推到下一層 `mat[nr][nc]`。
- Two-pass DP 正確。第一輪只讀取上方與左方的完成狀態，第二輪只讀取下方與右方的完成狀態，兩輪合併四個方向的最短距離。
- Multi-source BFS 也是正確解法。先把所有 0 同時放入 queue，等於以全部 0 作為距離 0 的起點；非 0 設成 `-1`，代表尚未走訪。
- BFS 每一層的距離只比上一層多 1，所以某格第一次被發現時就是最近距離。先賦值再 enqueue，會讓該格立刻脫離 `-1` 狀態，可避免被其他鄰居重複加入 queue。

### Complexity, strengths, and improvements

- 兩種解法的時間都是 O(m * n)。DP 配置 O(m * n) 的輸出矩陣、auxiliary space 為 O(1)；BFS 的 queue auxiliary space 最差為 O(m * n)。
- BFS 直接改寫 `mat`，可省去另一個結果矩陣，但 input mutation 是 tradeoff：若呼叫端之後仍需要原始 0/1 matrix，就應先複製或改用獨立結果矩陣。
- `dirR` 與 `dirC` 用相同 index 配對，邏輯與效能都合理，不是 bug。若更重視可讀性，可以改成 `int[][] directions`；代價是多一層陣列。保留 `ArrayDeque` 是合適選擇。
- 沒有需要修正的 overflow 風險；題目限制下距離不會超出 `int`。

### Optimality and alternatives

以 time complexity 為指標，兩種解法都是 O(m * n)，已達漸近最佳，因為答案包含 m * n 個格子，至少要處理每格一次，因此沒有唯一的「最佳解」。Multi-source BFS 是無權圖多源最短路的直觀解；Two-pass DP 不需要 queue，但目前版本會配置 `ans`。兩者已互為最值得學習的替代法，不再額外堆疊第三種解法。

## 相關

- [Dynamic Programming](../../topics/T16-21-dynamic-programming.md) — 掃描方向決定可安全參考的狀態
- [Dynamic Programming](../../topics/T16-21-dynamic-programming.md) — 兩次相反方向的狀態更新
- [Array](../../topics/T01-21-array.md) — 以二維陣列保存輸入與答案
- [Breadth-First Search](../../topics/T10-13-breadth-first-search.md) — 所有 0 同時入隊的 Multi-source BFS
- [Matrix](../../topics/T01-11-matrix.md) — 二維座標與四方向鄰居
- [LeetCode 刷題總覽](../../_moc.md)
