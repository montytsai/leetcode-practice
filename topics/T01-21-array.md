# Array

Array 標籤目前有 76 題已完成；以下依題號列出所有 `status: done` 的對應題解。

## 解題技巧

- 先釐清 index、value range 與題目要求保留的資訊，再決定要原地覆寫、排序或使用額外資料結構。
- 當陣列包含固定範圍且只缺一個值時，可用「預期總和減去實際總和」把空間降到 O(1)；計算前先確認整數範圍。
- 在已排序、可能含重複值的陣列上找「連續缺失範圍」：維護一個 `prev` 代表下一個期望值，用**嚴格大於**（`curr > prev`）才判定為缺口，重複值會自然因為 `curr < prev` 被跳過，不需要額外去重。
- 掃描「連續符合某條件的區段」（run-length scanning）天生需要兩層迴圈：外層負責找到 run 的起點、內層把整段 run 一次吃完。兩層各自的前進步伐不一樣——外層每次要跳幾格取決於這次 run 有多長，是執行期才知道的資訊，硬塞進單一 `for` 迴圈只是換語法外殼，兩段式前進的本質不會消失。

## 已刷題目

- [Two Sum](../solutions/01-arrays-hashing/lc-1-two-sum.md) Easy
- [Container With Most Water](../solutions/02-two-pointers/lc-11-container-with-most-water.md) Medium
- [Longest Common Prefix](../solutions/01-arrays-hashing/lc-14-longest-common-prefix.md) Easy
- [3Sum](../solutions/02-two-pointers/lc-15-3sum.md) Medium
- [4Sum](../solutions/02-two-pointers/lc-18-4sum.md) Medium
- [Remove Element](../solutions/01-arrays-hashing/lc-27-remove-element.md) Easy
- [Combination Sum](../solutions/12-backtracking/lc-39-combination-sum.md) Medium
- [Combination Sum II](../solutions/12-backtracking/lc-40-combination-sum-ii.md) Medium
- [Jump Game II](../solutions/18-greedy/lc-45-jump-game-ii.md) Medium
- [Permutations](../solutions/12-backtracking/lc-46-permutations.md) Medium
- [Permutations II](../solutions/12-backtracking/lc-47-permutations-ii.md) Medium
- [N-Queens](../solutions/12-backtracking/lc-51-n-queens.md) Hard
- [Maximum Subarray](../solutions/18-greedy/lc-53-maximum-subarray.md) Medium
- [Spiral Matrix](../solutions/21-math-geometry/lc-54-spiral-matrix.md) Medium
- [Jump Game](../solutions/18-greedy/lc-55-jump-game.md) Medium
- [Merge Intervals](../solutions/19-intervals/lc-56-merge-intervals.md) Medium
- [Insert Interval](../solutions/19-intervals/lc-57-insert-interval.md) Medium
- [Spiral Matrix II](../solutions/21-math-geometry/lc-59-spiral-matrix-ii.md) Medium
- [Sort Colors](../solutions/01-arrays-hashing/lc-75-sort-colors.md) Medium
- [Subsets](../solutions/12-backtracking/lc-78-subsets.md) Medium
- [Subsets II](../solutions/12-backtracking/lc-90-subsets-ii.md) Medium
- [Construct Binary Tree from Preorder and Inorder Traversal](../solutions/10-trees/lc-105-construct-binary-tree-from-preorder-and-inorder-traversal.md) Medium
- [Construct Binary Tree from Inorder and Postorder Traversal](../solutions/10-trees/lc-106-construct-binary-tree-from-inorder-and-postorder-traversal.md) Medium
- [Convert Sorted Array to Binary Search Tree](../solutions/10-trees/lc-108-convert-sorted-array-to-binary-search-tree.md) Easy
- [Best Time to Buy and Sell Stock](../solutions/04-sliding-window/lc-121-best-time-to-buy-and-sell-stock.md) Easy
- [Best Time to Buy and Sell Stock II](../solutions/01-arrays-hashing/lc-122-best-time-to-buy-and-sell-stock-ii.md) Medium
- [Gas Station](../solutions/18-greedy/lc-134-gas-station.md) Medium
- [Candy](../solutions/18-greedy/lc-135-candy.md) Hard
- [Single Number](../solutions/20-bit-manipulation/lc-136-single-number.md) Easy
- [Evaluate Reverse Polish Notation](../solutions/06-stack/lc-150-evaluate-reverse-polish-notation.md) Medium
- [Find Peak Element](../solutions/08-binary-search/lc-162-find-peak-element.md) Medium
- [Majority Element](../solutions/01-arrays-hashing/lc-169-majority-element.md) Easy
- [Minimum Size Subarray Sum](../solutions/04-sliding-window/lc-209-minimum-size-subarray-sum.md) Medium
- [Kth Largest Element in an Array](../solutions/11-heap-priority-queue/lc-215-kth-largest-element-in-an-array.md) Medium
- [Combination Sum III](../solutions/12-backtracking/lc-216-combination-sum-iii.md) Medium
- [Contains Duplicate](../solutions/01-arrays-hashing/lc-217-contains-duplicate.md) Easy
- [Product of Array Except Self](../solutions/01-arrays-hashing/lc-238-product-of-array-except-self.md) Medium
- [Sliding Window Maximum](../solutions/04-sliding-window/lc-239-sliding-window-maximum.md) Hard
- [Meeting Rooms](../solutions/19-intervals/lc-252-meeting-rooms.md) Easy
- [Meeting Rooms II](../solutions/19-intervals/lc-253-meeting-rooms-ii.md) Medium
- [Missing Number](../solutions/20-bit-manipulation/lc-268-missing-number.md) Easy
- [Move Zeroes](../solutions/02-two-pointers/lc-283-move-zeroes.md) Easy
- [Reconstruct Itinerary](../solutions/15-advanced-graphs/lc-332-reconstruct-itinerary.md) Hard
- [Increasing Triplet Subsequence](../solutions/01-arrays-hashing/lc-334-increasing-triplet-subsequence.md) Medium
- [Top K Frequent Elements](../solutions/01-arrays-hashing/lc-347-top-k-frequent-elements.md) Medium
- [Intersection of Two Arrays](../solutions/01-arrays-hashing/lc-349-intersection-of-two-arrays.md) Easy
- [Wiggle Subsequence](../solutions/18-greedy/lc-376-wiggle-subsequence.md) Medium
- [Queue Reconstruction by Height](../solutions/18-greedy/lc-406-queue-reconstruction-by-height.md) Medium
- [Non-overlapping Intervals](../solutions/19-intervals/lc-435-non-overlapping-intervals.md) Medium
- [Minimum Number of Arrows to Burst Balloons](../solutions/18-greedy/lc-452-minimum-number-of-arrows-to-burst-balloons.md) Medium
- [4Sum II](../solutions/01-arrays-hashing/lc-454-4sum-ii.md) Medium
- [Assign Cookies](../solutions/18-greedy/lc-455-assign-cookies.md) Easy
- [Non-decreasing Subsequences](../solutions/12-backtracking/lc-491-non-decreasing-subsequences.md) Medium
- [01 Matrix](../solutions/14-graphs/lc-542-01-matrix.md) Medium — 用二維答案陣列保存每格到最近 0 的距離。
- [Can Place Flowers](../solutions/01-arrays-hashing/lc-605-can-place-flowers.md) Easy
- [Maximum Average Subarray I](../solutions/04-sliding-window/lc-643-maximum-average-subarray-i.md) Easy
- [Maximum Binary Tree](../solutions/10-trees/lc-654-maximum-binary-tree.md) Medium
- [Binary Search](../solutions/08-binary-search/lc-704-binary-search.md) Easy
- [Find Pivot Index](../solutions/01-arrays-hashing/lc-724-find-pivot-index.md) Easy
- [Flood Fill](../solutions/14-graphs/lc-733-flood-fill.md) Easy
- [Asteroid Collision](../solutions/06-stack/lc-735-asteroid-collision.md) Medium
- [Lemonade Change](../solutions/18-greedy/lc-860-lemonade-change.md) Easy
- [K Closest Points to Origin](../solutions/11-heap-priority-queue/lc-973-k-closest-points-to-origin.md) Medium — 以座標陣列計算距離平方，再取最近的 K 個點。
- [Squares of a Sorted Array](../solutions/08-binary-search/lc-977-squares-of-a-sorted-array.md) Easy
- [Max Consecutive Ones III](../solutions/04-sliding-window/lc-1004-max-consecutive-ones-iii.md) Medium
- [Maximize Sum Of Array After K Negations](../solutions/18-greedy/lc-1005-maximize-sum-of-array-after-k-negations.md) Easy
- [Unique Number of Occurrences](../solutions/01-arrays-hashing/lc-1207-unique-number-of-occurrences.md) Easy
- [Kids With the Greatest Number of Candies](../solutions/01-arrays-hashing/lc-1431-kids-with-the-greatest-number-of-candies.md) Easy
- [Longest Subarray of 1's After Deleting One Element](../solutions/04-sliding-window/lc-1493-longest-subarray-of-1-s-after-deleting-one-element.md) Medium
- [Max Number of K-Sum Pairs](../solutions/02-two-pointers/lc-1679-max-number-of-k-sum-pairs.md) Medium
- [Find the Highest Altitude](../solutions/05-prefix-sum/lc-1732-find-the-highest-altitude.md) Easy
- [Find the Difference of Two Arrays](../solutions/01-arrays-hashing/lc-2215-find-the-difference-of-two-arrays.md) Easy
- [Successful Pairs of Spells and Potions](../solutions/08-binary-search/lc-2300-successful-pairs-of-spells-and-potions.md) Medium
- [Equal Row and Column Pairs](../solutions/01-arrays-hashing/lc-2352-equal-row-and-column-pairs.md) Medium
- [Minimum Increase to Maximize Special Indices](../solutions/18-greedy/lc-3891-minimum-increase-to-maximize-special-indices.md) Medium
- [Find All Numbers Disappeared in an Array II](../solutions/01-arrays-hashing/lc-4031-find-all-numbers-disappeared-in-an-array-ii.md) Medium — 排序後用嚴格大於掃描缺口，練重複值與範圍邊界的處理。

## 相關

- [_moc](../_moc.md)
