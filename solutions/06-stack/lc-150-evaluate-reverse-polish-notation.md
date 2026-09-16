---
title: "Evaluate Reverse Polish Notation"
difficulty: Medium
topics: [Stack & Queue, Array, Math, Stack]
category: 06-stack
order: 5
source: [Grind75, Carl]
platform: LeetCode
url: https://leetcode.com/problems/evaluate-reverse-polish-notation/
status: ac-unknown
note: ""
date_created: 2025-05-03
date_updated: 2026-08-21
---

# 150. Evaluate Reverse Polish Notation

## 題目說明

- 給定 Reverse Polish Notation（postfix notation）的 token 陣列，計算並回傳整數結果。
- 每個 operator 作用於前面最近的兩個 operand；整數除法朝零截斷，輸入保證是合法運算式。

## 心得

查 Reverse Polish Notation 的意思時被劇透要用 stack，之後實作沒有難度；真正要守住的只有 subtraction 和 division 的 operand 順序。

---

## 解法一：Stack Evaluation

### Intuition

Reverse Polish Notation 把 operator 放在兩個 operand 後面，因此由左到右掃描時，遇到 operator 就能從 stack 取出最近的兩個值完成一個子運算。第一次 pop 出來的是右 operand，第二次才是左 operand。

### Approach

1. 建立一個 `Deque<Integer>` 當 stack。
2. token 不是 operator 時，把整數值 push 進 stack。
3. 遇到 operator 時，先 pop 右 operand，再 pop 左 operand。
4. 依 operator 計算 `left operator right`，把結果 push 回 stack。
5. 掃描結束後，stack 唯一的值就是答案。

Invariant：處理完任何 token prefix 後，stack 由下到上保存所有已完成、但尚未被後續 operator 使用的子運算結果。

### Complexity

**Time complexity: `O(n)`**

`n` 是 token 數量；每個 token 只處理一次，每個值最多 push、pop 各一次。

**Space complexity: `O(n)`**

最壞情況下，operator 出現前可能先累積線性數量的 operand，因此 stack 需要 `O(n)` 空間。

### Code

```java
/**
 * 150. Evaluate Reverse Polish Notation
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String s : tokens) {
            if (!isOperator(s)) {
                stack.push(Integer.valueOf(s));
            } else {
                int count = count(stack.pop(), stack.pop(), s);
                stack.push(count);
            }
        }

        return stack.pop();
    }

    private boolean isOperator(String s) {
        return "+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s);
    }

    private int count(int j, int i, String operator) {
        switch (operator) {
            case "+":
                return i + j;
            case "-":
                return i - j;
            case "*":
                return i * j;
            case "/":
                return i / j;
            default:
                throw new RuntimeException("unkonwn");
        }
    }
}
```

### Code Review

- **Learning provenance**：`reference-assisted`；查 Reverse Polish Notation 時得知要用 stack，之後我表示實作「沒難度」。能否不看資料獨立重現：未確認。
- **Correctness / invariant**：第一次 `stack.pop()` 傳入 `j`，是右 operand；第二次傳入 `i`，是左 operand。`count` 計算 `i - j` 與 `i / j`，因此 non-commutative operator 的順序正確。
- **Strength**：使用 `ArrayDeque` 作為 stack，操作皆為 `O(1)`；`isOperator` 與計算 helper 把判斷責任分開；Java integer division 的朝零截斷符合題意。
- **Bug**：無。
- **Trade-off**：使用 `O(n)` stack 保存尚未被消耗的 operand 或子運算結果；對不修改輸入的單次掃描解法而言，這是必要空間。
- **Style**：`count` 比較像變數而不是動作，建議改成 `applyOperator`；`i`／`j` 建議改成 `left`／`right`，可直接看出順序。`"unkonwn"` 應改為 `"unknown"`，並可改拋 `IllegalArgumentException`。這些都不影響目前 AC 邏輯。
- **Edge cases**：負數 token 如 `"-11"` 不等於單獨的 `"-"`，會正確解析為整數；合法輸入保證 operator 出現時 stack 至少有兩個值。

### Optimality

這個解法在**時間複雜度**上是最佳的 `O(n)`，因為每個 token 至少要讀取一次。在不修改輸入的前提下，任意合法 RPN 可以先出現線性數量的 operand，因此最壞 `O(n)` stack 空間也無法普遍降得更低。唯一值得考慮的替代寫法是直接用 `switch` 處理 token 並使用 `left`／`right` 命名；它只改善**可讀性與面試表達**，時間與空間不變，不需要另列第二個演算法。
