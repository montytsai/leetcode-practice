---
title: "Letter Combinations of a Phone Number"
difficulty: Medium
topics: [Backtracking, Hash Table, String]
category: 12-backtracking
order: 3
source: [Carl]
platform: LeetCode
url: https://leetcode.com/problems/letter-combinations-of-a-phone-number/
status: ac-solo
note: ""
date_created: 2026-04-09
date_updated: 2026-09-29
---

# 17. Letter Combinations of a Phone Number

## 題目說明

- 給一組只含 `2`~`9` 的數字字串，回傳它在九宮格鍵盤上能對應到的所有英文字母組合。
- `digits` 長度 0 到 4；`digits` 為空字串時回傳空陣列。

## 心得

秒解，沒什麼心得。

---

## 解法一：回溯

### Intuition

這題是「每一位數字各選一個字母」的組合問題，不是「從一個集合裡挑子集」。每一層對應 `digits` 的一個位置，這一層要選的不是「要不要」，而是「選這個位置對應鍵盤上的哪個字母」。

樹的深度等於 `digits` 的長度，寬度等於當層數字對應的字母數（`7`、`9` 是 4 個字母，其餘是 3 個）。走到深度等於長度時就是一組完整組合，直接收。

### Approach

以 `digits = "23"` 為例：

```
                           ""
            ┌──────────────┼──────────────┐
           "a"            "b"            "c"
       ┌────┼────┐    ┌────┼────┐    ┌────┼────┐
      "ad" "ae" "af" "bd" "be" "bf" "cd" "ce" "cf"
```

1. 用 `index` 追蹤目前處理到 `digits` 的第幾位。
2. 終止條件：`path.length() == digits.length()`，代表每一位都選過了，把 `path` 收進結果。
3. 查表拿到 `digits.charAt(index)` 對應的字母清單，逐一嘗試：加入字母 → 遞迴 `index + 1` → 移除字母（回溯，讓下一個字母能重新嘗試）。
4. `digits` 為空字串時，初始呼叫的 `index == 0` 且 `path` 也是空的，終止條件在還沒進迴圈前就成立，會直接收一個空字串，而不是回傳空陣列——這點跟題目要求的「空輸入回傳空陣列」不一致，見 Code Review。

### Complexity

**Time complexity: `O(4^n · n)`**

`n` 為 `digits` 長度，每位最多對應 4 個字母，最壞情況分支數是 `4^n`；每組結果還要花 `O(n)` 把 `path` 轉成字串收進 `res`。

**Space complexity: `O(n)`**

不算輸出的話，額外空間只有遞迴深度與 `path` 這個 `StringBuilder`，都跟 `digits` 長度同階。

### Code

```java
/**
 * 17. Letter Combinations of a Phone Number
 * Time Complexity: O(4^n * n)
 * Space Complexity: O(n), excluding the output list
 */
class Solution {

    private Map<Character, List<Character>> phone;

    public List<String> letterCombinations(String digits) {
        phone = Map.of(
                '2', Arrays.asList('a', 'b', 'c'),
                '3', Arrays.asList('d', 'e', 'f'),
                '4', Arrays.asList('g', 'h', 'i'),
                '5', Arrays.asList('j', 'k', 'l'),
                '6', Arrays.asList('m', 'n', 'o'),
                '7', Arrays.asList('p', 'q', 'r', 's'),
                '8', Arrays.asList('t', 'u', 'v'),
                '9', Arrays.asList('w', 'x', 'y', 'z'));

        List<String> res = new ArrayList<>();
        backtracking(digits, 0, res, new StringBuilder(digits.length()));
        return res;
    }

    private void backtracking(String digits, int index, List<String> res, StringBuilder path) {
        if (digits.length() == path.length()) {
            res.add(path.toString());
            return;
        }

        List<Character> letters = phone.get(digits.charAt(index));
        for (int i = 0; i < letters.size(); i++) {
            int len = path.length();
            path.append(letters.get(i));

            backtracking(digits, index + 1, res, path);

            path.setLength(len);
        }
    }
}
```

程式碼規則：完整包在 `java` code fence 裡；Header 含題號、題名、Time/Space Complexity；所有註解 B1 English；保留 AC 的實際邏輯，只整理格式。

### Code Review

- **Learning provenance**：`ac-solo`，使用者自述秒解，無提示、無參考。
- **Correctness / invariant**：`path` 的加入與 `setLength(len)` 回退成對出現，遞迴不變式（`path` 永遠等於目前路徑上已選的字母）成立，主邏輯正確。
- **Edge case（真實風險）**：`digits` 為空字串時，`backtracking` 一進去就滿足終止條件，會把空字串收進 `res`，回傳 `[""]` 而不是題目要求的 `[]`。要修的話在 `letterCombinations` 開頭加一行 `if (digits.isEmpty()) return res;` 即可。
- **Trade-off**：`phone` 這張表在每次呼叫 `letterCombinations` 時都用 `Map.of` 重建一次；內容固定不變，適合拉出去當 `static final` 欄位，省掉每次呼叫的建表成本（九宮格鍵盤只有 8 個 entry，實務上可忽略，但作為習慣值得改）。
- **Style**：用 `Map<Character, List<Character>>` 查表取代 `switch`，可讀性好；`List<Character>` 換成 `char[]` 或 `String` 可以少一層 boxing，但差異同樣可忽略。

---

## Optimality

以時間複雜度為指標，這是最優解：輸出本身就是 `O(4^n)` 級，不可能有漸進更快的做法，回溯已經是「只走存在的分支」，沒有多餘工作。

替代法（學習價值，非效能提升）：用佇列做 BFS——先把 `digits[0]` 對應字母放進佇列當成初始組合，每處理一位數字就把佇列裡每個既有組合展開成新的組合。跟遞迴回溯本質相同，差別只在用顯式佇列取代呼叫堆疊，遞迴深度受限或不想用遞迴時是個選項。

## 相關

- Backtracking — 多個集合各取一個；樹的深度是輸入長度，寬度是該數字對應的字母數
- Hash Table — 用 `Map<Character, List<Character>>` 查表對應鍵盤字母
- String — 用 `StringBuilder` 累積路徑，`path.setLength(len)` 做字串層級的回溯
- [LeetCode 刷題總覽](../../_moc.md)
