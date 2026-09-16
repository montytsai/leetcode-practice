# Binary Search

## 解題技巧

二分搜尋每次排除一半範圍。這題也有二分解法，但本次使用等差級數總和，直接找出缺少值。

旋轉排序陣列(如 lc-33)可以拆成兩段二分搜尋：先用二分找出旋轉點(最小值的索引)，再依 target 與 `nums[0]` 的大小關係決定往哪一段做標準二分搜尋。另一種常見寫法是單一趟二分，在每個 mid 判斷「左半邊還是右半邊是排序好的」，再檢查 target 是否落在那段的值域內──兩者時間複雜度相同，都是 `O(log n)`，差別只在於拆成兩段小問題還是一次處理完所有分支。

**Floor search**(如 lc-981):不是找剛好等於 target 的元素，而是找「所有 ≤ target 的元素裡最大的那個」。寫法上，`mid` 命中就直接回傳；`mid` 的值 < target 時，先把它記成目前最佳候選，再往右繼續找有沒有更接近的(`l = mid + 1`)；`mid` 的值 > target 才往左縮(`r = mid - 1`)。跟一般二分搜尋的差別在於：找不到完全命中不代表失敗，要回傳的是搜尋過程中記錄下的最後一個合法候選，而不是直接判定沒有答案。

## 已刷題目

- [Search in Rotated Sorted Array](../solutions/08-binary-search/lc-33-search-in-rotated-sorted-array.md) Medium
- [Find Peak Element](../solutions/08-binary-search/lc-162-find-peak-element.md) Medium
- [Minimum Size Subarray Sum](../solutions/04-sliding-window/lc-209-minimum-size-subarray-sum.md) Medium
- [Count Complete Tree Nodes](../solutions/10-trees/lc-222-count-complete-tree-nodes.md) Medium
- [Missing Number](../solutions/20-bit-manipulation/lc-268-missing-number.md) Easy
- [First Bad Version](../solutions/08-binary-search/lc-278-first-bad-version.md) Easy
- [Intersection of Two Arrays](../solutions/01-arrays-hashing/lc-349-intersection-of-two-arrays.md) Easy
- [Guess Number Higher or Lower](../solutions/08-binary-search/lc-374-guess-number-higher-or-lower.md) Easy
- [Binary Search](../solutions/08-binary-search/lc-704-binary-search.md) Easy
- [Time Based Key-Value Store](../solutions/08-binary-search/lc-981-time-based-key-value-store.md) Medium — floor search:找「≤ target 裡最新一筆」，不是 exact match。
- [Max Consecutive Ones III](../solutions/04-sliding-window/lc-1004-max-consecutive-ones-iii.md) Medium
- [Successful Pairs of Spells and Potions](../solutions/08-binary-search/lc-2300-successful-pairs-of-spells-and-potions.md) Medium

## 相關

- [_moc](../_moc.md)
