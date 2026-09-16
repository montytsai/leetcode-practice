# Prefix Sum

前綴和的本質是**把「一段區間的答案」換成「兩個端點的差」**:先花一趟 `O(n)` 把「從頭到某個位置」的累積結果存起來,之後任何一段區間都能 `O(1)` 回答,不必每次重新加一遍。代價是一個長度 `n` 的陣列。

什麼時候該想到它:

- 題目反覆問「某一段的總和/乘積」,而且會問很多次 → 預處理換查詢。
- 題目問「除了自己以外」「左邊全部 vs 右邊全部」→ 前綴 + 後綴各一半,兩趟掃完(見 [238](../solutions/01-arrays-hashing/lc-238-product-of-array-except-self.md)、[724](../solutions/01-arrays-hashing/lc-724-find-pivot-index.md))。
- 題目在區間上做**加減**(不是查詢)→ 反過來用差分陣列,改端點、最後掃一次還原。
- 注意:官方在不少滑動視窗題上也掛 `Prefix Sum` tag(如 [209](../solutions/04-sliding-window/lc-209-minimum-size-subarray-sum.md)、[1004](../solutions/04-sliding-window/lc-1004-max-consecutive-ones-iii.md))。那是因為同向雙指針算的就是「兩個前綴的差」在滾動——**看到 tag 不代表要真的開陣列**,能滾動就別存。

Prefix Sum 標籤目前有 7 題已完成。

## 解題技巧

**通用模板(前綴和)**

```java
// prefix[i] = 前 i 個元素的和(不含 nums[i]),長度取 n + 1 可以免掉 i == 0 的特判
int[] prefix = new int[n + 1];
for (int i = 0; i < n; i++) {
    prefix[i + 1] = prefix[i] + nums[i];
}
// 區間 [l, r] 的和(閉區間)
int sum = prefix[r + 1] - prefix[l];
```

- **長度取 `n + 1` 還是 `n`,先想清楚再開**。`n + 1` 版有個 `prefix[0] = 0` 的虛擬起點,區間公式不用特判左端;`n` 版省一格但每次都要判 `l == 0`。乘法版的虛擬起點是 1(乘法單位元素),不是 0。
- **前綴 + 後綴的兩趟拆法**:凡是「左半邊 × 右半邊」「左半邊 vs 右半邊」的題,方向相反所以一定是兩趟。**其中一趟可以用滾動變數取代陣列**——因為第二趟邊走邊算就邊用掉了,不需要留著。這是把額外空間從 `O(n)` 壓到 `O(1)` 的固定手法。
- **輸出陣列不算額外空間**,所以第一趟的前綴結果可以直接寫進答案陣列,第二趟再就地乘/加上後綴。省一個陣列的代價是同一塊記憶體在兩趟裡語意不同,記得補註解。
- **差分陣列(前綴和的反向)**:要對區間 `[l, r]` 全部 `+v`,只改 `diff[l] += v`、`diff[r + 1] -= v`,最後對 `diff` 做一次前綴和就是結果。掃描線類的題(會議室、航班訂位)本質都是這個。
- **常見陷阱**:
  - 溢位。前綴和累積得很快,`int` 常常不夠;沒有題目明文保證範圍時一律用 `long`。
  - 端點差一。閉區間、開區間、`prefix` 的偏移量三者要一起對,建議固定用一種寫法別換來換去。
  - 前綴和只在「元素不變動」時有效。中途要改值就得換 Fenwick Tree / Segment Tree。

## 已刷題目

- [209. Minimum Size Subarray Sum](../solutions/04-sliding-window/lc-209-minimum-size-subarray-sum.md) Medium — 找最短的「和 ≥ target」子陣列;實作走滑動視窗,體會「視窗和 = 兩個前綴的差」
- [238. Product of Array Except Self](../solutions/01-arrays-hashing/lc-238-product-of-array-except-self.md) Medium — 前綴**積**版本;左邊用陣列、右邊用滾動變數,不用除法也天然處理 0
- [253. Meeting Rooms II](../solutions/19-intervals/lc-253-meeting-rooms-ii.md) Medium — 掃描線視角:開始 +1、結束 -1,同時最大重疊數就是差分的前綴最大值
- [724. Find Pivot Index](../solutions/01-arrays-hashing/lc-724-find-pivot-index.md) Easy — 前綴和的入門題型:一趟總和 + 一趟掃描,`left == total - left - nums[i]` 就是答案
- [1004. Max Consecutive Ones III](../solutions/04-sliding-window/lc-1004-max-consecutive-ones-iii.md) Medium — 「最多翻 k 個 0」= 視窗內 0 的個數 ≤ k;同樣是前綴計數在滾動
- [1732. Find the Highest Altitude](../solutions/05-prefix-sum/lc-1732-find-the-highest-altitude.md) Easy — 最單純的前綴和:一路累加取最大值,連陣列都不用開
- [3891. Minimum Increase to Maximize Special Indices](../solutions/18-greedy/lc-3891-minimum-increase-to-maximize-special-indices.md) Medium — 先認出前綴和,難點在偶數長度時要動態切換「前取奇數山峰、後取偶數山峰」

## 相關

- [array](T01-21-array.md) — 前綴/差分本來就是陣列章的線性掃描手法之一
- [sliding-window](T04-21-sliding-window.md) — 同向雙指針 = 前綴差在滾動,能滾就不要存陣列
- [intervals](T19-21-intervals.md) — 掃描線/差分在區間題上的落地
- [_index](_index.md) — 主題筆記索引
- [_moc](../_moc.md) — 全題清單與完成狀態
