---
title: "Time Based Key-Value Store"
difficulty: Medium
topics: [Binary Search, Hash Table, String, Design]
category: 08-binary-search
order: 9
source: [Grind75]
platform: LeetCode
url: https://leetcode.com/problems/time-based-key-value-store/
status: ac-unknown
note: ""
date_created: 2026-09-09
date_updated: 2026-09-09
---
# 981. Time Based Key-Value Store

## 題目說明

- 實作 `TimeMap`：`set(key, value, timestamp)` 記錄某個 `key` 在某個時間點的值；同一個 `key` 的多次 `set` 呼叫，`timestamp` 保證嚴格遞增。
- `get(key, timestamp)` 要回傳這個 `key` 在「時間 ≤ timestamp」的所有紀錄裡，時間最新的那一筆 value；找不到符合的紀錄就回傳空字串。

## 心得

這題原本想用 `TreeMap`，後來看提示才想到可以自己包一個 `Data` 物件把 value 跟 timestamp 綁在一起；`computeIfAbsent` 的寫法也是查來的，平常都用 `getOrDefault`。核心的 binary search（找「時間 ≤ target 裡最新的一筆」）是自己想的。

---

## 解法一：`Map<String, List<Data>>` + binary search 找 floor 值

### Intuition

TimeMap 本質上是「每個 `key` 各自維護一份按時間排序的歷史紀錄」。因為同一個 `key` 的 `set` 呼叫保證 timestamp 嚴格遞增，把每筆 `(value, timestamp)` 依呼叫順序塞進 `ArrayList`，清單天生就是照時間排序好的，不需要額外排序，也不需要 `TreeMap` 這種自帶排序的結構。

`get` 要找的不是「剛好等於 target 的那一筆」，而是「時間 ≤ target 裡最新的一筆」——這是 floor search，跟一般找剛好命中的二分搜尋不一樣。二分搜尋在沒有完全命中時，要持續記錄「目前看過、時間仍 ≤ target」的候選值，而不是直接判定找不到。

自訂 `Data` 物件把 value 跟 timestamp 綁在一起，是這題乾淨寫法的關鍵：如果只存 value 陣列，二分搜尋比較 timestamp 時就沒有東西可比；把兩者包成一筆記錄，清單裡的每個元素同時具備排序依據（timestamp）和要回傳的內容（value）。

### Approach

1. `store` 用 `Map<String, List<Data>>`；`set` 呼叫 `computeIfAbsent(key, k -> new ArrayList<>())` 取得（或建立）該 `key` 的清單，直接 `add` 新紀錄——利用「呼叫保證 timestamp 遞增」這個前提，不需要額外排序。
2. `get` 先確認 `key` 是否存在，不存在直接回傳 `""`。
3. 對該 `key` 的清單做二分搜尋：`l = 0`、`r = size - 1`，`result` 預設 `""`。
4. 每輪比較 `list.get(m).timestamp` 與 `target`：相等就直接回傳該筆 value；小於 `target` 就把該筆存進 `result` 並往右找有沒有更接近的（`l = m + 1`）；大於就往左縮（`r = m - 1`）。
5. 迴圈結束回傳目前記錄到的 `result`——可能是找到的最新一筆，也可能是初始的空字串（代表沒有任何紀錄的時間 ≤ target）。

### Complexity

**Time complexity: `set` `O(1)` amortized、`get` `O(log n)`**

`n` 是該 `key` 呼叫過 `set` 的次數。`ArrayList.add` 是 amortized `O(1)`；`get` 對清單做二分搜尋，每輪排除一半範圍。

**Space complexity: `O(m)`**

`m` 是所有 `key` 的 `set` 呼叫總次數，每筆呼叫都存成一個 `Data` 物件。

### Code

```java
/**
 * 981. Time Based Key-Value Store
 * Time Complexity: set O(1) amortized, get O(log n)
 * Space Complexity: O(m), m = total number of set calls
 */
class TimeMap {

    private class Data {
        String value;
        int timestamp;

        Data(String value, int timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    private Map<String, List<Data>> store;

    public TimeMap() {
        store = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        store.computeIfAbsent(key, k -> new ArrayList<>()).add(new Data(value, timestamp));
    }

    public String get(String key, int timestamp) {
        if (!store.containsKey(key)) {
            return "";
        }
        List<Data> list = store.get(key);
        return binarySearch(list, timestamp);
    }

    // Floor search: the latest record whose timestamp is <= target.
    private String binarySearch(List<Data> list, int target) {
        String result = "";

        int l = 0;
        int r = list.size() - 1;

        while (l <= r) {
            int m = l + ((r - l) >> 1);

            if (list.get(m).timestamp == target) {
                return list.get(m).value;
            } else if (list.get(m).timestamp < target) {
                result = list.get(m).value;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return result;
    }

}
```

### Code Review

- **Learning provenance**：`hint-assisted`——自訂 `Data` 物件是看提示才想到，`computeIfAbsent` 是查來的寫法；binary search 找 floor 值的邏輯是自己想的。獨立重現能力未確認。
- **Correctness / invariant**：正確。因為 `set` 保證同一 `key` 的 timestamp 嚴格遞增，清單天生排序，二分搜尋的前提成立；`result` 只在 `timestamp < target` 時更新，且更新後持續往右找有沒有更接近 target 的值，迴圈結束時就是「所有 ≤ target 中 timestamp 最大」的那筆，等同 floor 語義。
- **Strength**：用一個 `Data` 內部類同時攜帶 value 與 timestamp，讓二分搜尋的比較依據和要回傳的內容共用同一份資料，不必開兩個平行陣列對齊索引。
- **Style**：`if/else if` 三個分支裡各呼叫了 `list.get(m)` 一到兩次；先存成區域變數 `Data d = list.get(m);` 可以少幾次呼叫，對 `ArrayList` 雖然都是 `O(1)`，但讀起來更乾淨。
- **Edge cases**：`get` 查詢的時間早於該 `key` 最早一次 `set` 時，`result` 維持初始的 `""` 正確回傳；查詢不存在的 `key` 由 `containsKey` 提早擋掉。

---

## 解法比較

單一解法，暫不需要比較表。

### Optimality

`get` 的 `O(log n)` 已經是這題能拿到的最佳時間等級——要在 `n` 筆已排序紀錄裡找 floor 值，沒有額外資訊時無法比二分搜尋更快。空間上每筆 `set` 都要保留（題目要求歷史紀錄可查），`O(m)` 也已經是必要開銷。

**一個可以考慮的替代法**：把 `List<Data>` 換成 `TreeMap<Integer, String>`（key 是 timestamp），`get` 直接呼叫內建的 `floorEntry(timestamp)`，不用自己寫二分搜尋。時間複雜度一樣是 `O(log n)`，可讀性更高、也不用擔心手寫二分搜尋的邊界錯誤；取捨在於 `TreeMap` 是紅黑樹，每個節點都有額外的指標與物件開銷，記憶體局部性不如連續記憶體的 `ArrayList`，單純看常數因子時反而較慢。面試中如果對方在意「別重造輪子」，`TreeMap` 版更有利；在意「理解二分搜尋的能力」時，手寫版更有展示價值。

## 相關

- [Binary Search](../../topics/T08-21-binary-search.md) — floor search：二分搜尋不用找到剛好相等，而是持續記錄「≤ target」裡最新的候選
- [Hash Table](../../topics/T01-22-hash-table.md) — 用 `key` 對應到各自獨立的歷史紀錄清單
- [String 字串](../../topics/T03-21-string.md) — value 是字串，單純作為儲存內容，這題的練習重點不在字串操作
- [LeetCode 刷題總覽](../../_moc.md)
