---
title: "3Sum"
difficulty: Medium
topics: [Hash Table, Array, Two Pointers, Sorting]
category: 02-two-pointers
order: 1
source: [Grind75, Carl]
platform: LeetCode
url: https://leetcode.com/problems/3sum/
status: ac-unknown
note: ""
date_created: 2026-03-04
date_updated: 2026-08-20
---

# 15. 3Sum

## 題目說明

- 給一個整數陣列 `nums`,找出所有 `i != j != k` 且 `nums[i] + nums[j] + nums[k] == 0` 的三元組。
- 答案不可包含重複的三元組(元素值可重複出現在陣列裡,但同一組數值只能出現一次),三元組與答案的順序不限。
- 條件:`3 <= n <= 3000`、`-10^5 <= nums[i] <= 10^5`。**n 到 3000 是本題的關卡**:O(n^3) 級別的列舉會超時。

## 心得

把題目看成「每個數字選或不選」的決策樹,用回溯列舉所有三元組。這個解法在 NeetCode 平台會 AC,但在 LeetCode 原題 **TLE**——剪枝改善的是常數,改不掉 O(n^3) 的量級。二刷改成排序 + 雙指針,把複雜度壓到 `O(n^2)` 後 LeetCode 也過了(見解法二)。

---

## 解法一:回溯列舉三元組(NeetCode AC / LeetCode TLE)

> **提交狀態**:NeetCode Accepted;LeetCode Time Limit Exceeded。邏輯正確,但複雜度過不了 `n = 3000` 的測資。

### Intuition

先排序,再把「湊出三個數字」當成一棵決策樹:每一層決定要不要撿目前這個數字,撿滿三個就檢查總和是不是 0。這其實就是組合題 `combinations` 的骨架換一個終止條件,所以去重手法也照組合題那一套:同一層(橫向)遇到相同的值只用第一個,不同層(縱向)可以用相同的值。

排序在這裡有兩個作用:一是讓相同的數字相鄰,橫向去重才做得到;二是讓「已經超過 0 就不用再往下試」這個剪枝成立。

### Approach

1. `Arrays.sort(nums)`,讓相同的值相鄰、整體遞增。
2. 進入回溯:參數 `start` 是這一層可以挑的起點,`path` 是目前撿到的數字,`sum` 是 `path` 的總和。
3. 終止條件:`path.size() == 3` 時,`sum == 0` 就把 `path` 的複本存進結果,然後 return。
4. 每一層從 `start` 掃到底,套三個剪枝:
   - **數量剪枝**:剩下的元素個數 `nums.length - i` 少於還需要的個數 `3 - path.size()` 時 `break`,再往後不可能湊滿。
   - **去重剪枝**:`i > start && nums[i] == nums[i - 1]` 時跳過。`i > start` 保證同一層的第一個出現仍會被使用,跳掉的只是同一層的重複值,所以不會漏解,只會少掉重複的三元組。
   - **上界剪枝**:`sum + nums[i] > 0` 時跳過。因為陣列遞增,後面所有可撿的數字都 `>= nums[i]`,總和只會越加越大,不可能回到 0。
5. 撿了就往下一層 `i + 1` 遞迴,回來以後把 `path` 最後一個元素移除(回溯)。

**為什麼上界剪枝是安全的**:若 `nums[i] < 0`,則 `path` 裡的數字都來自更前面的索引、值都 `<= nums[i] < 0`,所以 `sum <= 0`,不可能發生 `sum + nums[i] > 0`。也就是說這個剪枝只會在 `nums[i] >= 0` 時觸發,而此時剩餘元素全部 `>= 0`,最終總和一定大於 0,確實無解可剪。

### Complexity

**Time complexity: `O(n^3)`**

`n` 是陣列長度。排序是 `O(n log n)`;決策樹有三層,每層最多掃 `O(n)` 個位置,所以列舉本身是 `O(n^3)`,總和由後者主導。剪枝只降低常數,量級不變:當陣列由大量相異值組成時(例如正負值分布均勻),三層迴圈幾乎都要跑完。`n = 3000` 時約 10^10 級的運算量,因此 LeetCode 會 TLE。

**Space complexity: `O(log n)`**

不計輸出。遞迴深度固定為 3,`path` 最多 3 個元素,都是 `O(1)`;額外空間主要來自 `Arrays.sort` 對 primitive 陣列使用 dual-pivot quicksort 的遞迴堆疊 `O(log n)`。

### Code

```java
/**
 * 15. 3Sum
 * Time Complexity: O(n^3)
 * Space Complexity: O(log n), output excluded
 *
 * A decision tree: for each number we choose to take it or skip it.
 * Accepted on NeetCode, but TLE on LeetCode because n can be 3000.
 */
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtracking(nums, 0, result, new ArrayList<>(), 0);
        return result;
    }

    private void backtracking(int[] nums, int start, List<List<Integer>> result, List<Integer> path, int sum) {
        if (path.size() == 3) { // Stop condition: we only take three numbers.
            if (sum == 0) {
                result.add(new ArrayList<>(path));
            }
            return;
        }

        for (int i = start; i < nums.length; i++) {
            // Prune: the remaining elements are not enough to complete a triplet.
            if (nums.length - i < 3 - path.size()) break;
            // Prune: skip duplicates on the same level, so the result has no duplicate triplet.
            if (i > start && nums[i] == nums[i - 1]) continue;
            // Prune: the array is sorted, so a positive sum can only grow and never return to zero.
            if (nums[i] + sum > 0) continue;

            path.add(nums[i]);
            backtracking(nums, i + 1, result, path, sum + nums[i]);
            path.remove(path.size() - 1);
        }
    }
}
```

### Code Review

- **Learning provenance**:未確認(當時沒說明這次是自解、看提示還是參考資料;能否獨立重現也未說明)。NeetCode 的 AC 只證明邏輯正確,不證明這個解法能過 LeetCode 的資料量。
- **Correctness / invariant**:正確,沒有 bug。三個 invariant 都成立——(1) `path` 內的索引嚴格遞增,所以不會重複使用同一個元素;(2) `sum` 恆等於 `path` 的總和,因為加入與移除永遠成對;(3) 同一層只使用相同值的第一個出現,所以結果不含重複三元組,且因為 `i > start` 的條件,縱向重複值(例如 `[-1, -1, 2]`)仍然找得到。
- **Strength**:去重條件寫的是 `i > start` 而不是 `i > 0`,這是組合類回溯最常寫錯的一行,這裡是對的。三個剪枝各有各的理由、註解也標明,而且「上界剪枝」有用到排序後的單調性,不是亂剪。把 `sum` 當參數傳、不在終止條件才重算,也省掉一輪加總。
- **Trade-off(主要問題)**:量級是 O(n^3),這是解法本身的天花板,不是寫法問題。回溯框架讓「撿三個數字」這件事變成通用的組合列舉,通用性換來的代價就是完全沒有利用「只需要三個數、而且第三個數是被前兩個決定的」這個結構。
- **Style(小)**:`if (nums[i] + sum > 0) continue;` 可以直接寫成 `break`。陣列遞增,一旦這個條件成立,後面每個 `i` 也都成立,`continue` 只是把剩下的位置再空轉一遍。
- **Risk**:`Arrays.sort(nums)` 就地修改了呼叫端傳進來的陣列。LeetCode 不在意,但面試時值得主動說一句「我會就地排序,如果不能改動輸入就先複製一份」。
- **Edge cases**:`n == 3` 時數量剪枝立刻生效,不會越界;全 0 的輸入靠去重只會產生一組 `[0,0,0]`;全正或全負的輸入靠上界剪枝與數量剪枝很快結束。這些都已經被現有邏輯涵蓋。

## 解法二:排序 + 雙指針 + 提前剪枝(LeetCode AC)

### Intuition

先排序陣列,固定最左邊的 `i`,在 `i` 右側區間用左右雙指針 `l`、`r` 夾逼找和為 0 的組合。陣列排序過之後,`l` 右移總和只會變大、`r` 左移總和只會變小,這個單調性讓每一步都能明確判斷該移動哪一邊,不必像三層迴圈暴力解一樣枚舉所有組合。

### Approach

1. `Arrays.sort(nums)`,之後所有判斷都依賴「陣列已排序」這個 invariant。
2. `for (i = 0; i < len - 2 && nums[i] <= 0; i++)`:一旦 `nums[i] > 0`,代表排序後面的數只會更大,不可能再湊出 0,整個迴圈直接結束。
3. `i` 若和前一個值相同就跳過,避免同一個 `nums[i]` 重複產生相同三元組。
4. `l = i + 1`, `r = len - 1`,`while (l < r)`:
   - 若 `nums[i] + nums[l] > 0`:因為 `r > l` 時 `nums[r] >= nums[l]`(排序過),這代表**這個 `l`(乃至更大的 `l`)搭配右側任何 `r`,總和必然 > 0**,直接跳出整個 `while`,不用一步步 `l++` 才發現同樣結論。
   - `l` 若和前一個值相同就跳過(去重)。`r` 沒有另外去重,因為一旦命中一組解就會同時移動 `l++`、`r--`,下一輪的 `l` 去重會連帶擋掉對應的重複 `r`。
   - 比較 `nums[l] + nums[r]` 與 `-nums[i]`:小了就 `l++`,大了就 `r--`,相等就收進結果並雙邊一起移動。

### Complexity

**Time complexity: `O(n^2)`**

排序 `O(n log n)`,外層固定 `i` 是 `O(n)`,內層雙指針每個 `i` 最多掃過整個區間 `O(n)`,合計 `O(n^2)`,主導掉排序的 `O(n log n)`。這也是這題「多大會 TLE」的關鍵:LeetCode 評測機大約每秒能跑 `10^8`~`10^9` 次基本運算。`n = 3000` 時,三層迴圈暴力解是 `O(n^3) ≈ 2.7 × 10^10` 次運算,遠超預算,必 TLE;換成這裡的 `O(n^2) ≈ 9 × 10^6` 次運算,綽綽有餘。

**Space complexity: `O(log n)`**

`Arrays.sort(int[])` 針對基本型別陣列用的是 dual-pivot quicksort(不是 `Object[]`/`List` 才有的 Timsort),遞迴堆疊平均 `O(log n)`,不是 `O(n)`。除此之外只有輸出用的 `result`,一般空間複雜度的說法不含輸出,所以標 `O(log n)`;若要把輸出算進去,最壞是 `O(log n + k)`(`k` 是找到的三元組數)。

### Code

```java
/**
 * 15. 3Sum
 * Time Complexity: O(n^2)
 * Space Complexity: O(log n) (excluding output; Arrays.sort recursion depth)
 */
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);
        int len = nums.length;

        // Prune 1: not enough elements left to form a triplet.
        // Prune 2: once nums[i] is positive, every later value is >= it too, so no triplet can sum to 0 anymore.
        for (int i = 0; i < len - 2 && nums[i] <= 0; i++) {
            // Prune 3: skip duplicate i to avoid repeated triplets.
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int l = i + 1;
            int r = len - 1;

            while (l < r) {
                if (nums[i] + nums[l] > 0) break;
                if (l > i + 1 && nums[l] == nums[l - 1]) {
                    l++;
                    continue;
                }

                if (nums[l] + nums[r] < -nums[i]) {
                    l++;
                } else if (nums[l] + nums[r] > -nums[i]) {
                    r--;
                } else {
                    result.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                }
            }
        }

        return result;
    }
}
```

### Code Review

- **Learning provenance**:當時沒記是 self-solved / hint-assisted / reference-assisted / AI-assisted,記「未確認」;能否獨立重現同樣未確認。
- **Correctness / invariant**:用 `[-1,0,1,2,-1,-4]` 手動 trace 過,`i=1` 時先命中 `[-1,-1,2]` 再命中 `[-1,0,1]`,與 LeetCode 官方範例輸出一致。去重邏輯(只 dedup `i` 與 `l`)是正確且標準的寫法:因為命中解時 `l`、`r` 同步移動,任何重複的 `r` 都會伴隨重複的 `l` 被上面的 `l` dedup 擋下,不需要對 `r` 另外去重。
- **Strength**:三個剪枝的順序和理由都對——尤其是 `nums[i] + nums[l] > 0` 直接 `break` 整個 `while`(而不是只跳過這一個 `l`),推導正確且比逐步 `l++` 更乾脆。
- **Trade-off**:`Arrays.sort(nums)` 會直接改動呼叫者傳進來的陣列(in-place),LeetCode 這題無妨,但若這個方法被其他程式呼叫、呼叫方還需要原始順序,就要注意這個副作用。
- **Edge cases**:`nums.length < 3` 時 `len - 2 <= 0`,迴圈條件直接不成立,回傳空 list,正確;元素範圍在題目限制內三數相加不會 overflow int,不必額外處理。

## 解法比較

| 解法 | Time | Space | 結果 |
| --- | --- | --- | --- |
| 解法一:回溯列舉三元組 | O(n^3) | O(log n) | NeetCode Accepted / LeetCode TLE |
| 解法二:排序 + 雙指針 | O(n^2) | O(log n) | LeetCode Accepted |

## Optimality

**時間**:解法二的 `O(n^2)` 是這題在一般整數輸入下已知的最佳實務解,沒有更快的常見做法(次二次的作法要靠 FFT-based 3SUM 之類的進階技巧,且通常有額外的數值範圍限制,面試/一般刷題不會期待這個)。解法一的 `O(n^3)` 差一個 `n`,這正是 LeetCode TLE 的原因,保留作為回溯框架的練習記錄。

**替代法(不同 trade-off,非更優)**:固定 `i` 後,不排序、改用 `HashSet<Integer>` 找 `l`、`r` 的配對(對每個 `j`,查 `-nums[i]-nums[j]` 在不在集合裡)。時間一樣 `O(n^2)`,但空間變成 `O(n)`(雙指針版只要 `O(log n)`),而且結果去重要另外用 `Set<List<Integer>>` 處理,程式碼通常更繁雜。價值在於不依賴排序、且在「陣列本來就不能排序」的變形題(例如要求保留原始 index)時才會優先選它;這題本身沒有這個限制,雙指針版更好。

**正確性與可讀性**:兩個解法邏輯都清楚、去重乾淨。解法一把「組合列舉 + 剪枝」的框架練熟,這個框架在真正的回溯題(如組合總和、子集)上是會拿分的;解法二則是這題本身的正解路徑。

**面試價值**:解法一在面試中會被追問優化,能講出「我知道這是 O(n^3),瓶頸在第三層的列舉」是有效溝通的起點;解法二才是應該在面試中直接寫出的版本。

## 相關

- [hash-table](../../topics/T01-22-hash-table.md) — 本題在 [_moc](../../_moc.md) 的主題分段
- [array](../../topics/T01-21-array.md) — 排序後的單調性是所有剪枝的前提
- [two-pointers](../../topics/T02-21-two-pointers.md) — 解法二的核心技巧
- [sorting](../../topics/T00-21-sorting.md) — 排序讓相同值相鄰,橫向去重才成立
