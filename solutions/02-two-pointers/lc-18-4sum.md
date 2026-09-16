---
title: "4Sum"
difficulty: Medium
topics: [Hash Table, Array, Two Pointers, Sorting]
category: 02-two-pointers
order: 2
source: [Carl]
platform: LeetCode
url: https://leetcode.com/problems/4sum/
status: ac-unknown
note: ""
date_created: 2026-03-05
date_updated: 2026-03-05
---

# 18. 4Sum

## 題目說明

- 給一個整數陣列 `nums` 與目標值 `target`,找出所有 `nums[a]+nums[b]+nums[c]+nums[d]==target` 的四元組(`a`、`b`、`c`、`d` 兩兩不同索引)。
- 答案不可包含重複的四元組,四元組內與答案間的順序不限。
- 條件:`1 <= nums.length <= 200`、`-10^9 <= nums[i] <= 10^9`、`-10^9 <= target <= 10^9`。數值範圍夠大,四個數字相加會超過 `int` 上限,是這題除了雙指針去重以外的第二個關卡。

## 心得

一開始照「總和為 0」的 3Sum 直覺寫,用 `nums[i] > target` 當第一層剪枝的 break 條件,結果邏輯是錯的——`target` 可以是負數,`nums[i]` 單獨大於 `target` 不代表接下來三個數字湊出的總和一定大於 `target`。看了以前的提交才知道正確剪枝要用「這個 `i` 能湊出的最小四數和」(`nums[i]+nums[i+1]+nums[i+2]+nums[i+3]`)去跟 `target` 比,而不是隨便挑 `nums[i]` 一個值。

---

## 解法一:排序 + 雙層雙指針,搭配四數和/三數和剪枝

### Intuition

先排序陣列,固定最左邊兩個索引 `i`、`j`,在 `j` 右側用雙指針 `l`、`r` 夾逼找出跟 `remainder = target - nums[i] - nums[j]`相等的組合——這是 3Sum 雙指針解法往上疊一層 `i`。

真正的重點在剪枝怎麼寫對。3Sum 的剪枝之所以能用「`nums[i] > 0` 就 break」,是因為那題目標固定是 `0`,`nums[i]` 一旦超過 `0`,後面全部 `>= nums[i] > 0` 的數字只會讓總和更大。這題 `target` 可以是任意負數或正數,單獨比較 `nums[i]` 跟 `target` 沒有意義——`nums[i]` 大於 `target` 不代表**這個 `i` 能湊出的最小四數和**也大於 `target`。正確做法是比較「這個 `i` 往後緊鄰三個數字」的和,因為陣列已排序,這是以 `i` 開頭能拿到的最小四數和:一旦這個最小值都超過 `target`,`i` 再往右移只會更大,才能安全 break。同一個道理套用在內層的 `j`(比較 `i, j, j+1, j+2` 四個數字的和)。

`tail`(全陣列最大 3 個數字的和)與 `tail2`(全陣列最大 2 個數字的和)則是另一個方向的剪枝:如果 `nums[i]` 加上「陣列裡能拿到的最大值」都還不到 `target`,代表 `i` 太小,要往右移(`continue`,不是 `break`,因為更大的 `i` 可能可以)。

### Approach

1. `Arrays.sort(nums)`,之後所有判斷都依賴「陣列已排序」這個 invariant。
2. 用陣列最後 3 個、最後 2 個元素預先算好 `tail`、`tail2`(全域最大可能值,迴圈中不會變)。
3. 外層 `i` 從 `0` 掃到 `len-4`:
   - **上界剪枝(break)**:`nums[i]+nums[i+1]+nums[i+2]+nums[i+3] > target` 就整個外層迴圈結束——這是以 `i` 開頭能湊出的最小四數和,超過 `target` 代表 `i` 再往右也不會有解。
   - **下界剪枝(continue)**:`nums[i] < target - tail` 就跳過這個 `i`——就算搭配全陣列最大的 3 個數字也湊不到 `target`,`i` 太小。
   - **去重**:`i > 0 && nums[i] == nums[i-1]` 跳過。
4. 內層 `j` 從 `i+1` 掃到 `len-3`,套用同樣的三種剪枝,只是把「四數和」換成「`i, j, j+1, j+2` 四數和」,把 `tail` 換成 `tail2`。
5. `remainder = target - nums[i] - nums[j]`,用雙指針 `l = j+1`、`r = len-1` 夾逼找出 `nums[l]+nums[r] == remainder` 的組合,找到就收進結果並雙邊一起移動;`l` 去重,`r` 不需要另外去重(理由見 Code Review)。

### Complexity

**Time complexity: `O(n^3)`**

`n = nums.length`。排序 `O(n log n)`;`i`、`j` 兩層迴圈是 `O(n^2)`,每一組 `(i, j)` 的雙指針掃描最多 `O(n)`,合計 `O(n^3)`,主導掉排序的 `O(n log n)`。四個剪枝只降低常數,不改變最壞情況的量級。

**Space complexity: `O(log n)`**(不含輸出)

`Arrays.sort(int[])` 對 primitive 陣列使用 dual-pivot quicksort,遞迴堆疊平均 `O(log n)`;其餘只有固定數量的變數。

### Code

```java
/**
 * 18. 4Sum
 * Time Complexity: O(n^3)
 * Space Complexity: O(log n), output excluded
 */
class Solution {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        if (len < 4) return result;

        Arrays.sort(nums);

        long tail = (long) nums[len - 3] + nums[len - 2] + nums[len - 1];
        long tail2 = nums[len - 2] + nums[len - 1];
        for (int i = 0; i < len - 3; i++) {
            // The smallest possible 4-sum starting at i already exceeds target; no larger i can work either.
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            // Even with the 3 largest values in the array, nums[i] cannot reach target; i is too small.
            if (nums[i] < target - tail) continue;
            // Skip duplicate i.
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < len - 2; j++) {
                // The smallest possible 4-sum for this i, j already exceeds target; no larger j can work either.
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                // Even with the 2 largest values in the array, nums[i] + nums[j] cannot reach target; j is too small.
                if (nums[i] + nums[j] < target - tail2) continue;
                // Skip duplicate j.
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                int remainder = target - nums[i] - nums[j];

                int l = j + 1;
                int r = len - 1;
                while (l < r) {
                    if (l > j + 1 && nums[l] == nums[l - 1]) {
                        l++;
                        continue;
                    }

                    if (nums[l] + nums[r] < remainder) {
                        l++;
                    } else if (nums[l] + nums[r] > remainder) {
                        r--;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        l++;
                        r--;
                    }
                }
            }
        }

        return result;
    }

}
```

### Code Review

- **Learning provenance**:`reference-assisted`(這個剪枝寫法是看了自己以前的提交才改對);獨立重現能力未確認。
- **Correctness / invariant**:正確,而且這正是這題最容易寫錯的地方。`nums[i] > target` 這種「單一數字對 `target`」的剪枝,只有在 `target` 固定為 `0`(如 3Sum)時才成立;`target` 可正可負時,必須比較「以 `i` 開頭能湊出的最小四數和」(`nums[i]+nums[i+1]+nums[i+2]+nums[i+3]`)才安全——因為陣列已排序,這四個數字就是以 `i` 為首能拿到的最小組合,一旦這個最小值都超過 `target`,`i` 再往右移沒有意義。`j` 層的剪枝是同一個推導套用到內層。`tail`/`tail2` 的下界剪枝方向相反(太小往右跳),兩種剪枝合起來覆蓋了排序陣列上「和太大」與「和太小」兩側,邏輯完整。
- **Strength**:剪枝比課本最簡版本(只排序 + 雙指針、不做提前終止)多做了四層優化,而且都推導正確,不是亂加條件。`remainder = target - nums[i] - nums[j]` 這行乍看像會 int overflow(`target`、`nums[i]`、`nums[j]` 都到 `10^9` 級,理論上三者相減可以到 `3×10^9`,超過 `int` 上限),但實際上能走到這一行,已經先通過了 `j` 層的兩個剪枝——「`j` 層上界剪枝存活」保證 `remainder ≥ 2 * nums[j]`,「`j` 層下界剪枝存活」保證 `remainder ≤ tail2 ≤ 2×10^9`,兩邊夾出 `remainder` 落在 `[-2×10^9, 2×10^9]`,完全在 `int` 範圍內,不會溢位。這個安全性是剪枝順序帶來的,不是巧合,但也因此是**隱性依賴**:如果之後改動剪枝條件(例如拿掉下界剪枝、或把 break 條件換成別的比較式),這個不溢位的保證就會跟著失效。
- **Trade-off(小)**:`tail` 用了 `(long)` 強制轉型,`tail2` 沒有(`nums[len-2] + nums[len-1]` 是純 `int` 運算)。這是因為題目限制下兩個最大值相加最多 `2×10^9`,剛好還在 `int` 範圍內,所以現在不會出錯;但這個安全邊界是「剛好没超」,风格上跟 `tail` 不一致,也是「多讀一行才看得出安全」的隱性假設,不如統一用 `(long)` 來得穩健、少一次心算。
- **Style**:同一層的去重寫法(`i > 0`、`j > i+1`)跟 3Sum 一致;雙指針的 `l` 去重寫在迴圈最前面、不論這一步是因為「太小前進」還是「命中後前進」都會檢查,比只在命中後才去重的教科書寫法更保險。`r` 沒有另外去重,理由跟 3Sum 一樣:命中解時 `l`、`r` 是同步移動的,下一輪 `l` 的去重會連帶擋掉對應的重複 `r`,不需要重複處理。
- **Edge cases**:`len < 4` 提早回傳空 list;四個索引全部落在同一個重複值區段時(例如全部都是同一個數字),`i`/`j`/`l` 三層去重會確保只收一次;`tail`/`tail2` 在 `len == 4` 時仍然指向合法索引(`len-3=1`、`len-2=2`、`len-3` 到 `len-1` 都在陣列範圍內),不會出界。

## Optimality

**時間**:`O(n^3)` 是排序 + 雙層雙指針這個做法在一般整數輸入下的標準複雜度,也是這題公認的實務最佳解——4Sum 是固定 `k=4` 的 k-Sum,通用做法是排序後用 `(k-2)` 層迴圈固定前面的值、最後兩層用雙指針,複雜度 `O(n^{k-1})`,`k=4` 時就是 `O(n^3)`,沒有更快的常見做法。這裡的四個剪枝優化的是常數,不改變量級,但對含大量重複值或提前能判斷無解的輸入有實際加速效果。
**空間**:`O(log n)`(不含輸出)已經是排序法的下限,沒有更省的做法。
**替代法(不同 trade-off,非更優)**:內層雙指針可以換成「固定 `i`、`j` 後,對每個 `l` 用 `HashSet` 查 `remainder - nums[l]` 在不在集合裡」。時間一樣 `O(n^3)`(建 `HashSet` 是 `O(n)`,查詢 `O(1)`,乘上外層 `O(n^2)`),但額外空間變成 `O(n)`(雙指針版只要 `O(log n)`),而且要另外處理重複四元組的去重(通常要 `Set<List<Integer>>`)。這個版本的價值在於不依賴排序、原始索引順序可以保留,但這題沒有這個限制,雙指針版更好。

## 相關

- [Hash Table](../../topics/T01-22-hash-table.md) — 本題在 [_moc](../../_moc.md) 的主題分段
- [Array](../../topics/T01-21-array.md) — 排序後的單調性是所有剪枝的前提
- [Two Pointers](../../topics/T02-21-two-pointers.md) — 雙層雙指針,3Sum 疊一層 `i`
- [Sorting](../../topics/T00-21-sorting.md) — 排序讓相同值相鄰,去重與所有剪枝才成立
- [LeetCode 刷題總覽](../../_moc.md)
