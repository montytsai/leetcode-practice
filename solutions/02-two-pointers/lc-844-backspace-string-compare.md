---
title: "Backspace String Compare"
difficulty: Easy
topics: [Two Pointers, String, Stack, Simulation]
category: 02-two-pointers
order: 13
source: [Grind75]
platform: LeetCode
url: https://leetcode.com/problems/backspace-string-compare/
status: ac-unknown
note: ""
date_created: 2026-08-02
date_updated: 2026-08-02
---

## 心得

最直覺的 Stack 解法很快就 AC，但需要 O(m + n) 額外空間。改寫成 O(1) 空間時，真正的困難不是 `findCurrIdx`，而是沒有分清楚「原始索引」與「下一個可見字元索引」。helper 回傳後，每個 pointer 只會有兩種意義：`>= 0` 表示下一個可比較的字元，`-1` 表示已經沒有可見字元。

原本版本有三個問題：

```java
    while (i >= 0 && j >= 0) {
        i = findCurrIdx(s, i);
        j = findCurrIdx(t, j);

        if (i <= 0 || j <= 0) {
            return i == j;
        }

        if (s.charAt(i--) != t.charAt(j--)) {
            return false;
        }
    }
```

1. `i <= 0` 把合法的 index `0` 當成耗盡，正確條件是 `i < 0`。
2. `i == j` 只代表兩個索引數字相同，不代表字元相同；例如 `s="a"`、`t="b"` 時，兩者都是 index `0`，但答案應是 `false`。
3. `while (i >= 0 && j >= 0)` 會在任一原始 pointer 先耗盡時停止，另一邊可能還有能被 `#` 全部刪掉的內容尚未處理；要用 `||`，直到兩邊都真正耗盡。

測資 `s="nzp#o#g"`、`t="b#nzp#o#g"` 的關鍵狀態：

| 比較 | `i` | `j` | 結果 |
| --- | ---: | ---: | --- |
| `g` vs `g` | 6 | 8 | 相同，往左 |
| `z` vs `z` | 1 | 3 | 相同，往左後變成 `i=0`、`j=2` |
| `n` vs `n` | 0 | 2 | 原程式因 `i <= 0` 提前回傳 `0 == 2`，誤判 `false` |

## Java

### 解法一：Stack

```java
class Solution {
    public boolean backspaceCompare(String s, String t) {
        Deque<Character> sStack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '#') {
                if (!sStack.isEmpty()) {
                    sStack.pop();
                }
            } else {
                sStack.push(c);
            }
        }

        Deque<Character> tStack = new ArrayDeque<>();
        for (char c : t.toCharArray()) {
            if (c == '#') {
                if (!tStack.isEmpty()) {
                    tStack.pop();
                }
            } else {
                tStack.push(c);
            }
        }

        if (sStack.size() != tStack.size()) {
            return false;
        }

        while (!sStack.isEmpty()) {
            // Unbox Character before comparing values.
            char sChar = sStack.pop();
            char tChar = tStack.pop();
            if (sChar != tChar) {
                return false;
            }
        }

        return true;
    }
}
```

Time is O(m + n). Space is O(m + n).

### 解法二：Reverse Two Pointers

```java
class Solution {
    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;

        // KEY! Keep going while either string may still have a visible character.
        while (i >= 0 || j >= 0) {
            i = findCurrIdx(s, i);
            j = findCurrIdx(t, j);

            // After normalization, each pointer is a visible character or -1.
            if (i < 0 && j < 0) {
                return true;
            } else if (i < 0 || j < 0) {
                return false;
            } else if (s.charAt(i) != t.charAt(j)) {
                return false;
            }

            i--;
            j--;
        }

        return i == j;
    }

    private int findCurrIdx(String str, int index) {
        int skip = 0;

        while (index >= 0) {
            if (str.charAt(index) == '#') {
                skip++;
            } else if (skip > 0) {
                skip--;
            } else {
                break;
            }
            index--;
        }

        return index;
    }
}
```

Time is O(m + n). Space is O(1).
