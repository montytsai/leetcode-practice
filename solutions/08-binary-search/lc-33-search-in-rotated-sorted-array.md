---
title: "Search in Rotated Sorted Array"
difficulty: Medium
topics: [Binary Search, Array]
category: 08-binary-search
order: 8
source: [Grind75]
platform: LeetCode
url: https://leetcode.com/problems/search-in-rotated-sorted-array/
status: ac-unknown
note: ""
date_created: 2026-09-05
date_updated: 2026-09-05
---

# 33. Search in Rotated Sorted Array

## 題目說明

- 給一個原本遞增排序、但在某個未知位置被旋轉過的陣列 `nums`(元素不重複)，以及一個 `target`，回傳 `target` 的索引；找不到回傳 `-1`。
- 要求時間複雜度 `O(log n)`。

## 心得

卡在主方法判斷的部分。原本送出後在「單一元素、無反轉」的案例出錯，逐步改成 `min > 0` 才修正。沒辦法一次想到所有條件蠻困擾的，覺得下次再寫要更完整地考慮各種情況。

---

## 解法一：先二分找旋轉點，再對正確半邊做標準二分搜尋

### Intuition

旋轉過的陣列可以看成兩段各自遞增的子陣列接在一起，交界處就是最小值所在的位置(旋轉點)。找到這個位置後，問題就退化成兩個獨立的標準二分搜尋：`target` 落在哪一段，就對那一段做一般的二分搜尋即可。

找旋轉點本身也是一種二分搜尋：用 `min` 記錄目前為止看過的最小值候選(初始設為最右邊)，每次比較 `nums[mid]` 和 `nums[min]`，如果 `nums[mid]` 比較大，代表 mid 落在「較大那一段」，最小值一定在 mid 右邊，把 `left` 往右移；否則 mid 本身可能就是最小值(或更靠近最小值)，把它記下來當新的 `min`，並把 `right` 往左收。

第一版卡住的地方是主方法裡「往哪一段搜尋」的判斷式：原本只用 `target >= nums[0]` 決定要不要搜左段，但這句話有個隱藏前提——它假設陣列真的有被旋轉。當陣列完全沒被旋轉(或只有一個元素)時，旋轉點 `min` 會落在索引 `0`，此時 `nums[0]` 本身就是全陣列最小值，`target >= nums[0]` 幾乎必為真，會誤判成「要搜左段 `[0, min-1]`」，但 `min-1 = -1`，變成一個不合法的搜尋範圍，直接漏掉正確答案。加上 `min > 0` 這個條件，等於先確認「陣列真的有旋轉點」才進入左右分流的判斷；沒有旋轉時就直接對整個陣列做一次標準二分搜尋，行為和沒旋轉前完全一樣。

### Approach

1. 呼叫 `findMin` 找出旋轉點的索引 `min`(即全陣列最小值的位置)。
2. 若 `min > 0`(陣列確實被旋轉過)且 `target >= nums[0]`：`target` 落在旋轉點以前的那一段(較大值構成的遞增段)，對 `[0, min - 1]` 做二分搜尋。
3. 否則(陣列沒被旋轉，或 `target` 落在較小值構成的那一段)：對 `[min, nums.length - 1]` 做二分搜尋。
4. `findMin` 與 `findTarget` 都是標準的迭代二分搜尋，靠 `left <= right` 收斂。

### Complexity

**Time complexity: `O(log n)`**

`findMin` 一次二分是 `O(log n)`，`findTarget` 再一次二分也是 `O(log n)`，兩者相加仍是 `O(log n)`。

**Space complexity: `O(1)`**

只用幾個索引變數，沒有額外資料結構或遞迴呼叫。

### Code

```java
/**
 * 33. Search in Rotated Sorted Array
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
class Solution {
    public int search(int[] nums, int target) {
        int min = findMin(nums);

        // min == 0 means the array was never rotated; search it as one sorted range.
        if (min > 0 && target >= nums[0]) {
            return findTarget(nums, 0, min - 1, target);
        } else {
            return findTarget(nums, min, nums.length - 1, target);
        }
    }

    // Binary search for the rotation point (index of the global minimum).
    private int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        int min = right;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > nums[min]) {
                left = mid + 1;
            } else {
                min = mid;
                right = mid - 1;
            }
        }

        return min;
    }

    // Standard binary search within an already-sorted range.
    private int findTarget(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (target > nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
```

### Code Review

- **Learning provenance**：`self-solved`；第一次送出在單一元素、無反轉的案例出錯，靠自己逐步除錯改成加上 `min > 0` 這個條件才 AC。能否不靠除錯直接一次寫對：未確認。
- **Correctness / invariant**：`findMin` 用 `nums[mid]` 和目前候選 `nums[min]` 比較、而非常見寫法的 `nums[mid]` 對 `nums[right]`，但兩者等價──因為每次 `right` 收縮(`right = mid - 1`)一定同時把 `min` 更新成同一個 `mid`，所以 `min` 隨時保存著上一次收縮當下的參考值，作用等同直接比較 `nums[right]`。實測過單一元素、完全不旋轉、旋轉點在中間、旋轉點在最後一個元素等情況，皆正確。
- **Strength**：把問題拆成「找旋轉點」與「在已知排序段內搜尋」兩個獨立、各自熟悉的子問題，比在同一個迴圈裡一次判斷所有情況更容易驗證正確性。
- **Bug**：無(修正後的版本)。
- **Trade-off**：兩段式作法呼叫了兩次二分搜尋函式，雖然仍是 `O(log n)`，但常數因子比單一趟二分略高；換來的是每個子問題的邏輯都更單純。
- **Edge cases**：`min > 0` 這個 guard 正是為了涵蓋「陣列沒有旋轉」與「陣列只有一個元素」這兩種邊界；`target` 恰好等於 `nums[0]`、`target` 恰好等於旋轉點上的最小值，這兩種邊界也都在追蹤過的案例中驗證正確。

---

## 解法二：單一趟二分搜尋，每次判斷哪一半有序

> Learning provenance：這是 AI 提供的參考實作，我尚未在 LeetCode 實際送出這個版本，用來對照另一種模板，不算她本人的 AC 記錄。

### Intuition

不先花一次二分找出旋轉點，而是在同一次二分搜尋的每一步，直接判斷「以 `mid` 切開後，左半邊 `[left, mid]` 還是右半邊 `[mid, right]` 是嚴格排序好的」。因為原陣列只被旋轉一次，任何一次切割中，至少有一邊一定是完整遞增的(旋轉點只可能落在另一邊)。

只要知道哪一邊是排序好的，就能直接用該邊的邊界值判斷 `target` 是否落在裡面：若排序好的那一邊涵蓋 `target` 的值域，就往那邊收斂；否則 `target` 一定在另一邊，往另一邊收斂。不管 `mid` 落在旋轉點的哪一側，每一步都能排除掉一半的搜尋空間，全程只需要一次二分搜尋，不用像解法一那樣先跑一次找旋轉點、再跑第二次搜尋。

判斷哪邊排序好的方法：比較 `nums[left]` 和 `nums[mid]`。若 `nums[left] <= nums[mid]`，代表左半邊 `[left, mid]` 沒有被旋轉點切到，是嚴格遞增的；否則右半邊 `[mid, right]` 才是完整遞增的那一段。

### Approach

1. 初始化 `left = 0`、`right = nums.length - 1`。
2. 迴圈中計算 `mid`；若 `nums[mid] == target` 直接回傳 `mid`。
3. 若 `nums[left] <= nums[mid]`(左半邊排序好)：
   - 若 `nums[left] <= target && target < nums[mid]`，`target` 落在左半邊值域內，收斂 `right = mid - 1`。
   - 否則 `target` 必在右半邊，收斂 `left = mid + 1`。
4. 否則(右半邊排序好)：
   - 若 `nums[mid] < target && target <= nums[right]`，`target` 落在右半邊值域內，收斂 `left = mid + 1`。
   - 否則 `target` 必在左半邊，收斂 `right = mid - 1`。
5. 迴圈以 `left <= right` 收斂；跳出迴圈代表沒找到，回傳 `-1`。

### Complexity

**Time complexity: `O(log n)`**

單一趟二分搜尋，每一步排除一半範圍。

**Space complexity: `O(1)`**

只用固定幾個索引變數。

### Code

```java
/**
 * 33. Search in Rotated Sorted Array (single-pass variant)
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) return mid;

            if (nums[left] <= nums[mid]) {
                // Left half [left, mid] is strictly sorted.
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // Right half [mid, right] is strictly sorted.
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}
```

### Code Review

- **Learning provenance**：AI-provided reference implementation；未經我在 LeetCode 實際提交，能否獨立重現未確認。
- **Correctness / invariant**：陣列只旋轉一次，任一次切割後兩半之中恰好有一半完整遞增，`nums[left] <= nums[mid]` 足以區分是哪一半；已手動驗證旋轉點在中間、旋轉點在最後一格、完全沒旋轉、單一元素等情況皆正確。
- **Strength**：全程一個迴圈、一次二分，沒有子函式呼叫，常數因子比兩段式略低。
- **Bug**：無。
- **Trade-off**：每一步都要同時處理「哪邊排序」與「target 是否在值域內」兩層判斷，分支比解法一略多，第一次寫容易在 `<` 和 `<=` 的位置上出錯。
- **Edge cases**：`nums[left] == nums[mid]`(範圍只剩一個元素時)會歸類成「左半邊排序好」，此時 `target < nums[mid]` 不可能與自己相等而成立，會正確落入「target 必在右半邊」的分支，不會誤判成死迴圈或漏搜。

---

## 解法比較

| 解法 | Time | Space | 優點 | Trade-off | 使用時機 |
| --- | --- | --- | --- | --- | --- |
| 解法一 | `O(log n)` | `O(1)` | 拆成「找旋轉點」「範圍內搜尋」兩個熟悉的子問題，各自好驗證 | 呼叫兩次二分搜尋函式，常數因子略高 | 想把問題拆解成小步驟、降低單次判斷複雜度時 |
| 解法二 | `O(log n)` | `O(1)` | 一個迴圈打完，少一次函式呼叫，常數因子較低 | 每步判斷分支較多，邊界(`<` 與 `<=`)較密集容易寫錯 | 面試最常見模板、追求精簡寫法時 |

### Optimality

以時間衡量，兩個解法都是 `O(log n)`，都是這題要求的最佳複雜度；以空間衡量，兩者都是 `O(1)`。兩者在漸進複雜度上沒有差異，差別只在於「拆成兩個子問題」還是「一次迴圈處理所有分支」這個工程與可讀性層面的選擇。解法一勝在每個子問題可以單獨驗證，比較不容易出錯(這次實際發生的 bug 也證明了這點)；解法二勝在少一次函式呼叫，也是面試中最常被期待看到的寫法。沒有哪一個在效率上更好，選哪個看當下想練哪種拆解習慣。

## 相關

- [Binary Search](../../topics/T08-21-binary-search.md) — 兩種二分模板對照：先定位旋轉點再搜尋 vs. 單一趟判斷哪一半有序
- [Array](../../topics/T01-21-array.md)
- [LeetCode 刷題總覽](../../_moc.md)
