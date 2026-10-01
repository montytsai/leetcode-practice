# Sliding Window

滑動窗口用一段連續區間表示當前狀態。當右界加入新元素後破壞條件，就移動左界直到窗口恢復合法。看到「連續子陣列或子字串」、「最長或最短」與「區間內必須維持某條件」時，可以優先想到這個模式。

## 解題技巧

### 先定義窗口不變量

要明確說出 `[l, r]` 代表什麼、什麼情況下不合法，以及左界如何移動才能恢復合法。左界通常只往右移，不能倒退。

### 可變長度：逐步縮小或直接跳躍

- 若只知道窗口不合法，用 `while` 逐步移動左界，同時移除舊狀態。
- 若有元素上次出現的位置，可直接把左界跳到該位置之後。此時必須確保舊位置仍在當前窗口內，避免左界倒退。

### 固定長度

右界每加入一個元素，就在窗口超過固定大小時移除最左元素。這類題通常可以用一個累加狀態在 `O(n)` 內完成。

窗口內容要與目標比對（如異位詞）時，用單一計數表記錄「目標需求減窗口現況」：進窗口減 1、出窗口加 1，全部為 0 即相符。每輪掃表判斷是 `O(字母表大小)`；改成可變長度窗口，在某字元差值小於 0 時縮窗，窗口長度等於目標長度就可直接收錄，不必掃表。

## 已刷題目

- [3. Longest Substring Without Repeating Characters](../solutions/04-sliding-window/lc-3-longest-substring-without-repeating-characters.md) Medium — 用字元最後出現位置跳躍更新左界，並保證左界不倒退。
- [209. Minimum Size Subarray Sum](../solutions/04-sliding-window/lc-209-minimum-size-subarray-sum.md) Medium — 在總和達標時縮小左界，找最短連續子陣列。
- [239. Sliding Window Maximum](../solutions/04-sliding-window/lc-239-sliding-window-maximum.md) Hard — 用單調佇列維持每個固定窗口的最大值。
- [438. Find All Anagrams in a String](../solutions/04-sliding-window/lc-438-find-all-anagrams-in-a-string.md) Medium — 固定長度窗口搭配單一差值 HashMap，全部歸零就是異位詞。
- [643. Maximum Average Subarray I](../solutions/04-sliding-window/lc-643-maximum-average-subarray-i.md) Easy — 用固定長度窗口維持區間總和。
- [1004. Max Consecutive Ones III](../solutions/04-sliding-window/lc-1004-max-consecutive-ones-iii.md) Medium — 當可翻轉的零超過上限時縮小窗口。
- [1456. Maximum Number of Vowels in a Substring of Given Length](../solutions/04-sliding-window/lc-1456-maximum-number-of-vowels-in-a-substring-of-given-length.md) Medium — 固定窗口每次加入一字元並移除一字元。
- [1493. Longest Subarray of 1's After Deleting One Element](../solutions/04-sliding-window/lc-1493-longest-subarray-of-1-s-after-deleting-one-element.md) Medium — 維持最多含一個零的窗口。

## 相關

- [_moc](../_moc.md)
- [Array](T01-21-array.md)
- [String](T03-21-string.md)
