---
title: "Find All Numbers Disappeared in an Array II"
difficulty: Medium
topics: [Array]
category: 01-arrays-hashing
order: 24
source: [Contest]
platform: LeetCode
url: https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array-ii/
status: ac-unknown
note: ""
date_created: 2026-08-23
date_updated: 2026-08-23
---

# 4031. Find All Numbers Disappeared in an Array II

## 題目說明

- 給定 `nums` 與範圍 `[lower, upper]`(`1 <= lower <= upper <= 10^5`,`nums.length` 與 `nums[i]` 同上限),找出 `[lower, upper]` 內所有沒出現在 `nums` 的整數,把連續缺失的整數合併成一個 `[start, end]` 區間,按遞增順序回傳。
- `nums` 可能含重複值、可能含超出 `[lower, upper]` 的值;若沒有缺失整數回傳空陣列。

## 心得

邊界值靠測資微調出來,不是真的想通。

解法二補充:看懂 boolean 標記法的邏輯沒問題,但自己想把雙層 `while` 改寫成單層 `for` 時卡住,最後照著參考版本送出並 AC。

---

## 解法一:排序後掃描,用 prev 追蹤下一個期望值

### Intuition

把 `nums` 排序後,維護一個 `prev` 代表「目前期望看到的下一個整數」,初始為 `lower`。掃描排序後的陣列,任何超出 `[lower, upper]` 的值直接跳過。當目前值 `curr` 大於 `prev`,代表 `[prev, curr-1]` 這段整數都沒出現過,是一段缺失區間。

`// KEY! 有超過才加` 這行是這次卡住的地方:一定要用嚴格大於 `curr > prev`,不能用 `>=`。原因是重複值——同一個數字出現兩次時,第一次處理完會把 `prev` 設成 `curr + 1`,第二次遇到同樣的 `curr` 時會發現 `curr < prev`(因為 `curr == prev - 1`),自然被跳過、不會誤判成缺口。如果誤用 `>=` 或漏掉這個條件,像 `[2,3,5]` 、`lower=2` 這種案例會在重複值或緊鄰值的地方多算出不存在的區間。

### Approach

1. 排序 `nums`(改變輸入陣列本身)。
2. `prev` 初始化為 `lower`,代表下一個「應該要出現」的整數。
3. 逐一掃描排序後的 `curr`:
   - 超出 `[lower, upper]` 範圍就跳過。
   - 若 `curr > prev`,代表 `[prev, curr-1]` 是一段連續缺失,加入答案。
   - 不論有沒有加區間,都把 `prev` 更新為 `curr + 1`(下一個期望值)。
4. 掃描結束後,若 `prev <= upper`,代表 `[prev, upper]` 這段尾端也是缺失,補進答案。

因為陣列已排序、`curr` 非遞減,`curr` 只會等於 `prev - 1`(重複值,跳過)或 `>= prev`,不會出現 `curr < prev - 1` 的情況,所以這個 invariant 是安全的。

### Complexity

**Time complexity: `O(n log n)`**

`n = nums.length`。排序主導複雜度,掃描本身是 `O(n)`。

**Space complexity: `O(1)`**(不計輸出與排序內部堆疊)

原地排序 `nums`,除了答案陣列沒有用到額外的線性空間。

### Code

```java
/**
 * 4031. Find All Numbers Disappeared in an Array II
 * Time Complexity: O(n log n)
 * Space Complexity: O(1) excluding output
 */
class Solution {
    public List<List<Integer>> findDisappearedNumbers(int[] nums, int lower, int upper) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);

        int prev = lower;
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];

            if (curr < lower || curr > upper) {
                continue;
            }

            // KEY! Only add a gap when strictly greater than prev.
            // This is what lets duplicates (e.g. [2,3,5], lower=2) fall through
            // without being mistaken for a missing range.
            if (curr > prev) {
                ans.add(List.of(prev, curr - 1));
            }
            prev = curr + 1;
        }

        if (prev <= upper) {
            ans.add(List.of(prev, upper));
        }

        return ans;
    }
}
```

### Code Review

- **Learning provenance**:`self-solved`(用測資慢慢微調寫出來,非參考或 AI 協助);獨立重現能力自評「感覺不是真的很懂邊界值」,對邊界條件的判斷未確認能否獨立重現。
- **Correctness / invariant**:正確。核心 invariant 是「`prev` = 目前為止已確認涵蓋到的下一個期望整數」,配合陣列已排序、`curr` 非遞減的前提,`curr > prev` 才加區間可以同時處理「重複值」與「範圍外的值」而不誤判,推導見上方 Intuition。
- **Strength**:用一個 `prev` 變數同時扛住「起點」「跳過重複」「跳過範圍外值」三件事,沒有另外開 Set 或 boolean 陣列,邏輯精簡。
- **Trade-off**:`Arrays.sort(nums)` 會就地排序、改動呼叫端傳進來的原陣列;如果呼叫端之後還要用原始順序的 `nums`,這是一個真實的副作用風險,值得在多人協作或呼叫端不知情時特別注意。
- **Edge cases**:
  - 重複值相鄰(如 `[2,3,5]`)——`curr > prev` 的嚴格大於已經處理正確,見 KEY 註解。
  - `nums` 內全是範圍外的值——迴圈全部 `continue`,`prev` 停在 `lower`,迴圈結束後的補尾邏輯會把整個 `[lower, upper]` 當一段區間補上,正確。
  - 沒有缺失整數——每次都不會觸發 `curr > prev`,結束後 `prev == upper + 1`,補尾條件不成立,回傳空陣列,正確。

---

## 解法二:boolean 標記法,一次標記、一次掃描連續遊程(run)

### Intuition

不用排序。既然 `range = upper - lower + 1` 有題目保證的安全上限(判斷過程見 [複雜度分析主題](../../topics/T00-01-complexity-analysis.md)),可以直接開一個大小 `range` 的 `boolean[] seen`,`seen[k]` 代表數字 `lower + k` 有沒有出現過。先掃一次 `nums` 把範圍內的值標記起來,再掃一次 `seen`,把連續一段 `false` 轉成一個缺失區間。

### Approach

1. 開 `boolean[range] seen`,`range = upper - lower + 1`。
2. 掃一次 `nums`:值落在 `[lower, upper]` 內就把對應的 `seen[num - lower]` 設成 `true`。
3. 用 `i` 從左掃到右找「連續 `false` 的區段(run)」:
   - `seen[i]` 是 `true` 就跳過,`i++`。
   - `seen[i]` 是 `false`,記下這段的起點 `start`,用內層 `while` 把整段連續 `false` 一次吃完,直到遇到 `true` 或掃完整個陣列,再把 `[lower+start, lower+i-1]` 加進答案。

**為什麼是兩層迴圈,硬改成單層 `for` 會卡關**:外層在問「還有沒有東西可以看」,內層在問「這一整段連續 `false` 有多長」——這兩件事的前進步伐不一樣。外層每次要往前跳幾格,是**執行到當下才知道**的資訊(取決於這次 run 有多長),不是固定值。`for` 迴圈的語法習慣是在標頭就寫死一個固定的遞增方式(`i++`),但這裡的步伐是變動的,所以自然會卡在「`for` 的遞增子句該寫什麼」這個問題上。就算硬把它塞進 `for (int i = 0; i < range; )` 的殼、自己在 body 手動控制 `i++`,本質上還是同一套「兩段式前進」邏輯,只是換了語法外觀——單層迴圈這個目標,在「找出一段連續符合條件的區間」這種 pattern 下本來就達不到,不是寫法不夠熟練的問題。這個「外層找 run 起點、內層吃掉整個 run」的兩層迴圈,是掃描連續遊程(run-length scanning)最自然的形狀。

### Complexity

**Time complexity: `O(n + range)`**

`n = nums.length`,`range = upper - lower + 1`。標記一次 `nums` 是 `O(n)`,掃描 `seen` 一次是 `O(range)`,兩段各自線性、互不巢狀相乘(外層 `i` 跟內層 `i` 共用同一個變數,兩層迴圈總共只會把 `i` 從 `0` 推進到 `range`,不會重複掃)。

**Space complexity: `O(range)`**

`seen` 陣列大小固定為 `range`;`range <= 1e5` 有題目保證的上限,大小安全(見 [複雜度分析主題](../../topics/T00-01-complexity-analysis.md))。

### Code

```java
/**
 * 4031. Find All Numbers Disappeared in an Array II — Approach 2 (boolean marking)
 * Time Complexity: O(n + range), range = upper - lower + 1
 * Space Complexity: O(range)
 *
 * Worked example: nums = [3,9,7], lower = 1, upper = 12
 * index: [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11]
 * value: [ 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12]
 * seen:  [ F, F, T, F, F, F, T, F, T, F, F, F]
 */
class Solution {
    public List<List<Integer>> findDisappearedNumbers(int[] nums, int lower, int upper) {
        List<List<Integer>> ans = new ArrayList<>();

        int range = upper - lower + 1;
        boolean[] seen = new boolean[range];

        for (int num : nums) {
            if (num < lower || num > upper) continue;
            seen[num - lower] = true;
        }

        int i = 0;
        while (i < range) {
            if (seen[i]) {
                i++;
                continue;
            }

            int start = i;
            while (i < range && !seen[i]) {
                i++;
            }
            ans.add(List.of(lower + start, lower + i - 1));
        }

        return ans;
    }
}
```

### Code Review

- **Learning provenance**:`AI-assisted`——看懂了這個標記法的邏輯,自己嘗試把雙層 `while` 改寫成單層 `for` 但卡住,最後照參考版本送出並 AC;獨立重寫成單層迴圈的能力目前未確認做得到。
- **Correctness / invariant**:正確。標記階段的 invariant 是「`seen[k] == true` 若且唯若 `lower+k` 出現在 `nums` 裡」;掃描階段的 invariant 是「`i` 左邊的位置都已經判斷完畢,歸進某個區間或被跳過」,兩段合起來覆蓋所有情況,推導與邊界案例跟解法一相同(可交叉參照)。
- **Strength**:不排序、不動輸入陣列,比解法一更乾淨;標記與掃描是兩個獨立的線性階段,沒有互相耦合,好推理好測試。
- **Trade-off**:換到 `O(range)` 額外空間換取拿掉排序的 `log n` 因子,前提是 `range` 有安全的上限(這題 `upper <= 1e5` 滿足)。
- **Style**:偏好單層迴圈是個好習慣,通常能降低狀態管理的出錯機會,但這裡卡關不是熟練度問題——「找出一段連續符合條件的區間」這種 run-length 掃描,天生需要兩種不同步伐的前進方式,雙層迴圈是這個 pattern 最自然、最不用扭曲邏輯的寫法,不是需要改掉的壞習慣。
- **Edge cases**:與解法一相同的三種(重複值、全部範圍外、沒有缺失整數)在這個版本下都是同樣正確,推導見標記/掃描的 invariant,不用另外重新驗證。

---

## 解法比較

| 解法 | Time | Space | 優點 | Trade-off | 使用時機 |
| --- | --- | --- | --- | --- | --- |
| 解法一(排序 + 雙指標) | `O(n log n)` | `O(1)`(不含輸出) | 不需要額外陣列,`range` 多大都不影響空間 | 會排序、動到輸入陣列 | 想要最省空間,或 `range` 上限不明/可能很大時 |
| 解法二(boolean 標記) | `O(n + range)` | `O(range)` | 不排序、不動輸入陣列,時間更快 | 額外空間跟 `range` 成正比,`range` 要有安全上限才能放心開 | `range` 有明確且不大的上限(這題成立)時的預設選擇 |

### Optimality

**Time 指標**:解法二 `O(n + range)` 比解法一 `O(n log n)` 更快,因為題目保證 `upper <= 1e5`,`range` 跟 `n` 同數量級,不需要排序帶來的 `log n` 因子。在這題的 constraints 下,解法二是更好的選擇。

**Space 指標**:解法一 `O(1)` 仍然是空間上的最佳解。兩個解法沒有一個同時贏兩個指標,是真正的時間換空間 trade-off——但這題的 `range` 上限經過查證是安全的(見 [複雜度分析主題](../../topics/T00-01-complexity-analysis.md)的判斷步驟),所以解法二換到的空間成本是可承受的,值得優先選它;解法一則是 `range` 上限不確定或想完全不留副作用時的備案。

## 相關

- [Array](../../topics/T01-21-array.md) — 已排序陣列用嚴格大於掃描連續缺失區間
- [時間與空間複雜度分析](../../topics/T00-01-complexity-analysis.md) — `upper <= 1e5` 有明確上限，直接開 `boolean[range]` 是安全且更快的選擇
- [LeetCode 刷題總覽](../../_moc.md)
