---
title: "Longest Substring Without Repeating Characters"
difficulty: Medium
topics: [Sliding Window, Hash Table, String]
category: 04-sliding-window
order: 8
source: [Grind75]
platform: LeetCode
status: ac-unknown
note: ""
date_created: 2026-08-18
date_updated: 2026-08-18
---

## 心得

完全靠自己想出滑動窗口解法；AC 後再把原本對 `r = 0` 的額外處理收斂成 `lastIdx >= l` 這個統一條件，不再需要特判。

用右指標逐字元擴張窗口，並記錄每個字元最後出現的位置。若重複字元仍在當前窗口內，就把左界直接跳到上次位置的下一格。

## Java

```java
/*
 * 3. Longest Substring Without Repeating Characters
 * Approach: Sliding window with an array cache
 *
 * Time: O(n) - initialize 128 slots and scan the string once
 * Space: O(1) - use a fixed array of 128 ASCII entries
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] dic = new int[128]; // Store the last index of each ASCII character.
        Arrays.fill(dic, -1); // Use -1 when a character has not appeared.

        int longest = 0;
        int l = 0; // The left boundary of the window.

        // Move the right boundary forward.
        for (int r = 0; r < s.length(); r++) {
            int lastIdx = dic[s.charAt(r)]; // The previous index of this character.

            // If it is inside the window, move the left boundary after it.
            // This condition also handles r = 0 without a special case.
            if (lastIdx >= l) {
                l = lastIdx + 1;
            }

            longest = Math.max(r - l + 1, longest);
            dic[s.charAt(r)] = r; // Save the latest index.
        }

        return longest;
    }
}
```

## Code Review

### Learning provenance

- `self-solved`：完全由自己推導並 AC。
- AC 後主動重構，把 `r = 0` 特判合併進一般條件。

### Submission review

- **Correctness**：`[l, r]` 在每輪結束時都不含重複字元。當前字元上次出現在窗口內時，`l = lastIdx + 1` 剛好排除舊字元；若在窗口外，`l` 不動，所以左界永遠不會倒退。
- **Edge cases**：空字串回傳 `0`；`r = 0` 時 `lastIdx` 為 `-1`，自然略過移動左界的條件；重複字元在窗口外時也不會錯誤拉回左界。
- **Complexity**：時間 `O(n)`，因為每個字元只被右指標處理一次；額外空間 `O(1)`，因為陣列大小固定為 128。
- **Strength**：直接跳過重複字元，不需要逐步縮小左界；`lastIdx >= l` 同時表達「重複字元在當前窗口內」與「左界不倒退」兩個關鍵概念。
- **Trade-off**：`int[128]` 只適用於 ASCII 字元。這符合本題範圍；若同一段程式要處理 Java `char` 值大於 127 的輸入，會有陣列越界風險。
- **Style**：沒有必須修正的風格問題。可選擇把 `s.charAt(r)` 存成局部變數，減少重複取值，但不影響正確性或複雜度。

### Optimality and alternative

以時間複雜度來看，`O(n)` 已是漸進最佳，因為至少必須讀過每個字元。以本題的 ASCII 範圍來看，固定 128 格陣列的 `O(1)` 額外空間也已是漸進最佳。

一個有不同取捨的替代法是用 `HashMap<Character, Integer>` 記錄最後位置：時間仍為平均 `O(n)`，空間為 `O(min(n, alphabet))`，字元範圍比較彈性，但常數成本與記憶體開銷比陣列高。
