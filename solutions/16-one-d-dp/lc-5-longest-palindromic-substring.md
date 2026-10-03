---
title: "Longest Palindromic Substring"
difficulty: Medium
topics: [Two Pointers, String, Dynamic Programming]
category: 16-one-d-dp
order: 22
source: [Extra]
platform: LeetCode
url: https://leetcode.com/problems/longest-palindromic-substring/
status: ac-assisted
note: ""
date_created: 2026-10-03
date_updated: 2026-10-03
---

# 5. Longest Palindromic Substring

## 題目說明

- 給字串 `s`，回傳其中最長的回文子字串（palindromic substring）；有多個同長答案時任一個都可以。
- `s` 長度 1 到 1000，由數字與英文字母組成。

## 心得

看 AI 的答案解出來的（中心擴展法）。

---

## 解法一：中心擴展（Expand Around Center）

### Intuition

回文以中心左右對稱，每個回文都有唯一的中心：奇數長度的中心是一個字元，偶數長度的中心是兩個相鄰字元之間的空隙。長度 `n` 的字串共有 `2n - 1` 個中心，枚舉這些中心，就涵蓋所有可能的回文。

從一個中心向兩邊擴展時，每多一層就檢查新的左右兩端是否相同。第一次不同就可以停止：更外層的子字串必定包含這一對不相等的字元，不可能是以此中心的回文。所以每個中心只需要擴展到第一次失配，不必逐一判斷所有子字串。

相較於枚舉全部子字串再各自判斷回文的 `O(n^3)`，中心擴展把「判斷是否回文」和「枚舉候選」合併成同一次向外走訪，把時間降到 `O(n^2)`。

### Approach

1. 長度不超過 1 時，直接回傳原字串。
2. 對每個索引 `i`，各呼叫一次 `expand`：`(i, i)` 處理奇數長度，`(i, i + 1)` 處理偶數長度。
3. `expand` 在兩端都沒有越界且字元相同時繼續向外。迴圈結束時 `left`、`right` 各自多走了一格，所以回傳 `[left + 1, right - 1]`。
4. 回傳區間比目前最佳區間 `res` 更長（嚴格大於）才更新，同長度時保留先找到的。
5. 掃完所有中心後，用 `res` 切出子字串。

### Complexity

**Time complexity: `O(n^2)`**

`n = s.length()`。共有 `2n - 1` 個中心，每個中心最多向外擴展 `O(n)` 步。最壞情況是整串都是同一個字元（例如 `"aaaa...a"`），每個中心都能擴展到接近邊界。

**Space complexity: `O(n)`**

`toCharArray()` 會複製出長度 `n` 的陣列。演算法本身只需要 `O(1)` 的額外變數；每次 `expand` 配置的 `int[2]` 用完即丟，不累積。

### Code

```java
/**
 * 5. Longest Palindromic Substring
 * Time Complexity: O(n^2)
 * Space Complexity: O(n)
 */
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        // res stores the start and end index of the best palindrome so far.
        int[] res = new int[2];
        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            // Odd length: the center is one character.
            int[] odd = expand(arr, i, i);
            // Even length: the center is between two characters.
            int[] even = expand(arr, i, i + 1);

            if ((odd[1] - odd[0] + 1) > (res[1] - res[0] + 1)) {
                res = odd;
            }
            if ((even[1] - even[0] + 1) > (res[1] - res[0] + 1)) {
                res = even;
            }
        }

        return s.substring(res[0], res[1] + 1);
    }

    private int[] expand(char[] arr, int left, int right) {
        while (left >= 0 && right < arr.length && arr[left] == arr[right]) {
            left--;
            right++;
        }
        // The loop stops one step too far on each side, so we move back.
        return new int[] { left + 1, right - 1 };
    }
}
```

### Code Review

- **Learning provenance**：本人明說是看 AI 的答案解出來的，對應 `ac-assisted`；能否不看答案獨立重現，未確認。
- **Correctness / invariant**：正確。`expand` 的迴圈不變量是進入每一輪前 `arr[left + 1 .. right - 1]` 已是回文；迴圈結束時，這個區間就是以該中心的最長回文。偶數中心在 `i = n - 1` 時 `right` 已越界，迴圈不執行，回傳 `[n, n - 1]`（長度 0），不會取代 `res`。`res` 初值 `[0, 0]` 代表第一個字元，長度至少為 1，與題目保證的答案下限一致。
- **Strength**：奇偶兩種中心共用同一個 `expand`，只差起點；全程只傳索引區間，不在迴圈裡建立字串，`substring` 只在最後呼叫一次。
- **Trade-off**：`toCharArray()` 複製了整串，改用 `s.charAt` 可讓空間降為 `O(1)`，代價是每次比較多一次方法呼叫，在 `n <= 1000` 下兩者差異可忽略。每次 `expand` 配置一個 `int[2]`，共約 `2n` 個短命物件；也可只回傳長度並由外層推算起點。
- **Style**：`s == null` 的檢查在題目限制下用不到；長度為 1 時一般流程也能得到正確結果，所以提前回傳是冗餘但無害。長度算式 `x[1] - x[0] + 1` 重複四次，抽成小函式會比較好讀。沒有 bug。
- **Edge cases**：整串同字元是最壞情況，仍在 `O(n^2)` 內。全部字元皆相異時，每個中心只能擴展 0 步，回傳第一個字元。同長度並列時因為嚴格大於，保留較早出現的，題目允許任一答案。

---

## Optimality

以時間複雜度為指標，`O(n^2)` 不是漸進最佳，Manacher 演算法可以做到 `O(n)`。以面試價值與可讀性為指標，在 `n <= 1000` 的限制下，中心擴展是最合適的寫法：程式碼短、不易出錯，也是面試中被期待的標準解。

單一更佳解法：Manacher。做法是先在字元之間插入分隔符，讓奇偶長度統一成奇數長度；掃描時維護目前已知「最右邊界」的回文及其中心，新的中心若落在這個回文內部，就能用它的鏡像位置的回文半徑當作起始值，只需要從已知範圍之外繼續擴展。每個位置的擴展只會把最右邊界向右推進，不會回頭，所以總共 `O(n)`。空間為 `O(n)`（存回文半徑陣列）。適合在 `n` 很大（例如 10^5 以上）或面試官明確要求線性時間時使用；一般情況下，先寫出中心擴展並說明還有 Manacher 可以優化，通常已足夠。

## 相關

- Two Pointers — 以中心為起點，左右指標向外擴展
- String — 回文判斷只看字元對稱位置
- Dynamic Programming — 這題也可以用 `dp[i][j]` 表示「`s[i..j]` 是否為回文」，二維表的轉移與 [647. Palindromic Substrings](lc-647-palindromic-substrings.md) 相同
- [two-pointers](../../topics/T02-21-two-pointers.md)
- [string](../../topics/T03-21-string.md)
- [dynamic-programming](../../topics/T16-21-dynamic-programming.md)
- [LeetCode 刷題總覽](../../_moc.md)
