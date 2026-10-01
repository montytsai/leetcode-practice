---
title: "Find All Anagrams in a String"
difficulty: Medium
topics: [Sliding Window, Hash Table, String]
category: 04-sliding-window
order: 9
source: [Grind75]
platform: LeetCode
url: https://leetcode.com/problems/find-all-anagrams-in-a-string/
status: ac-solo
note: ""
date_created: 2026-10-01
date_updated: 2026-10-01
---

# 438. Find All Anagrams in a String

## 題目說明

- 給字串 `s` 與 `p`，回傳 `s` 中所有是 `p` 的字母異位詞（anagram）的子字串的起始索引，順序不限。
- `s`、`p` 只含小寫英文字母；`p` 比 `s` 長時沒有任何答案，回傳空 list。

## 心得

我全程自己想出來的 用滑動窗口跟hashmap寫 一開始比較貪心想剪枝但是剪錯了(原本想剪如果map的value有<0就貪心移多步)

---

## 解法一：固定長度窗口＋差值 HashMap

### Intuition

異位詞的條件是「每個字元的出現次數相同」，與順序無關。所以 `s` 中的異位詞子字串，長度一定等於 `p.length()`，問題就變成：在 `s` 上滑一個固定長度的窗口，逐一判斷窗口內的字元計數是否與 `p` 相同。

計數只用一張 HashMap：先放入 `p` 每個字元的次數，窗口加入一個字元就減 1，移出一個字元就加 1。Map 內每個值代表「`p` 需要的次數減去窗口目前有的次數」，全部為 0 就表示兩邊計數完全一致。這樣不必維護兩張表再逐項比對。

窗口長度固定，左界由右界決定，不需要「不合法就縮窗」的迴圈；每一步只做進一個、出一個。

### Approach

1. 把 `p` 的字元次數存入 `window`。
2. 右界 `r` 每走一格，`s[r]` 的值減 1。
3. 窗口長度 `r - l + 1` 等於 `p.length()` 時：若 `window` 所有值為 0，記錄 `l`；接著把 `s[l]` 的值加回 1，`l` 右移一格，窗口長度回到 `p.length() - 1`，下一輪加入新字元後又滿。
4. 窗口還沒滿時只擴張，不判斷。

### Complexity

**Time complexity: `O(n)`**

`n = s.length()`。右界走一遍，每輪的進出是 `O(1)`；`isAnagram` 掃描 map 的所有值，key 數量最多是出現過的不同字元數（題目限小寫英文字母，上限 26），所以每輪判斷是常數，整體 `O(26 * n)`，簡化為 `O(n)`。建立 `p` 的計數另外 `O(m)`，`m = p.length()`。

**Space complexity: `O(1)`**

Map 的 key 只可能是小寫字母，最多 26 個，不隨輸入長度成長。

### Code

```java
/**
 * 438. Find All Anagrams in a String
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();

        // Store how many of each character the window still needs.
        Map<Character, Integer> window = new HashMap<>();
        for (char c : p.toCharArray()) {
            int count = window.getOrDefault(c, 0);
            window.put(c, ++count);
        }

        int l = 0;
        int r = 0;
        while (r < s.length()) {
            // The new character enters the window, so the need goes down.
            int rCnt = window.getOrDefault(s.charAt(r), 0);
            window.put(s.charAt(r), --rCnt);

            int len = r - l + 1;
            if (len == p.length()) {
                // All values are 0, so the window has the same counts as p.
                if (isAnagram(window)) {
                    res.add(l);
                }

                // The left character leaves the window, so the need goes up.
                int lCnt = window.getOrDefault(s.charAt(l), 0);
                window.put(s.charAt(l), ++lCnt);
                l++;
            }

            r++;
        }

        return res;
    }

    private boolean isAnagram(Map<Character, Integer> window) {
        for (int v : window.values()) {
            if (v != 0) {
                return false;
            }
        }
        return true;
    }
}
```

### Code Review

- **Learning provenance**：本人明說全程自己想出來（`ac-solo`）；AC 不證明能獨立重現，這點未確認。
- **Correctness / invariant**：正確。每輪結束時 `window[c] = p 中 c 的次數 - 窗口 [l, r] 中 c 的次數`，窗口長度恰為 `p.length()` 時，所有值為 0 等價於兩邊計數相同。`l` 只在窗口滿時才移動，所以窗口長度不會超過 `p.length()`。
- **Strength**：用單一差值 map 取代兩張計數表；先加右、判斷、再移左的順序讓固定窗口的進出邏輯集中在同一個 `if`。
- **Trade-off**：每個滿窗口都呼叫 `isAnagram` 掃一次 map，常數是字母表大小（26）；`HashMap<Character, Integer>` 還有裝箱成本。計數可以改用 `int[26]`，或追蹤「還有幾個字元的差值不為 0」讓判斷變 `O(1)`。
- **Style**：`window` 存的是差值（需求減現況），不是窗口內容，名稱會讓人誤讀；改成 `need` 之類更貼切。沒有 bug。
- **Edge cases**：`p` 比 `s` 長時窗口永遠不會滿，回傳空 list，正確；map 內值為 0 的 key 不會被移除，但 `isAnagram` 只看是否為 0，不受影響。

---

## Optimality

以時間複雜度為指標，`O(n)` 已是漸進最佳，因為至少要讀過 `s` 的每個字元；空間 `O(1)` 在小寫字母限制下也是最佳。目前寫法的差別只在常數：每個滿窗口多掃一次 26 格。

單一替代法：改用 `int[26]` 計數，窗口改成可變長度。右界加入 `s[r]` 後，若該字元的差值小於 0（窗口內這個字元比 `p` 多），就移動左界並把移出的字元加回，直到差值回到 0 以上；此時窗口內沒有任何字元超量，只要長度等於 `p.length()`，就代表計數完全相同，直接收錄 `l`，不需要掃描判斷。這就是「差值小於 0 時多移幾步」的剪枝想法能成立的條件：必須搭配可變長度窗口與「長度等於 `p.length()`」的收錄判斷。時間仍是 `O(n)`（左右界各走一遍），常數更小；固定窗口版本則勝在邏輯單純、不易寫錯。

## 相關

- Sliding Window — 固定長度窗口，進一個出一個，不需要縮窗迴圈
- Hash Table — 用單一差值 map 同時表示「需求」與「窗口現況」
- String — 異位詞只看字元次數，與順序無關
- [sliding-window](../../topics/T04-21-sliding-window.md)
- [hash-table](../../topics/T01-22-hash-table.md)
- [string](../../topics/T03-21-string.md)
- [LeetCode 刷題總覽](../../_moc.md)
