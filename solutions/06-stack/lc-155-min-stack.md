---
title: "Min Stack"
difficulty: Medium
topics: [Stack & Queue, Stack, Design]
category: 06-stack
order: 10
source: [Grind75]
platform: LeetCode
url: https://leetcode.com/problems/min-stack/
status: ac-assisted
note: ""
date_created: 2026-09-02
date_updated: 2026-09-02
---

# 155. Min Stack

## 題目說明

- 設計一個支援 `push`、`pop`、`top`、`getMin` 的棧，四個操作都要 `O(1)`。
- `-2^31 <= val <= 2^31 - 1`，最多 `3 * 10^4` 次操作。
- 題目保證 `pop`、`top`、`getMin` 只在棧非空時被呼叫。

## 心得

我靠 AI 給提示，一開始看 min stack 還以為要用最小堆。總之沒想到可以這樣解：同步維護兩個 stack，一個最小 stack 只放當下最小在堆頂。

---

## 解法一：輔助棧（Auxiliary Stack）

### Intuition

`getMin()` 要求 `O(1)`，但棧只暴露「最後推入的元素」這一個入口，最小值在結構上看不到。每次呼叫都掃一遍就退化成 `O(n)`。

最小堆是很自然的第一個聯想，但堆與棧是兩套不同的順序：堆按大小取，棧按推入時間取。`pop()` 要移除的是「最後推入的那一個」，堆沒有這個入口，得額外維護索引或改用 lazy deletion 才做得到，成本高於問題本身。

真正可用的性質是：**第 i 層的最小值只由前 i 個元素決定，不受之後的 push 與 pop 影響。** 一旦推入第 i 個元素，該層的最小值就固定了，之後只會被更深的層覆蓋，不會被修改。

因此最小值可以在 push 的當下算好、跟著層一起存起來，不必事後計算。這就是輔助棧：第二個棧的第 i 層存放「前 i 個元素的最小值」，與主棧同步 push、同步 pop。兩棧永遠等長且逐層對應，`getMin()` 於是退化成一次 `peek()`。

這是「空間換時間」的典型形式——用 `O(n)` 額外空間，把一個需要掃描的查詢換成常數時間的讀取。

### Approach

1. 建兩個 `Deque<Integer>`：`mainStack` 存實際的值，`minStack` 存每一層對應的最小值。
2. `push(value)`：`mainStack` 推入 `value`；`minStack` 為空時推入 `value`，否則推入 `Math.min(value, minStack.peek())`。
3. `pop()`：兩個棧各彈出一次。
4. `top()`：回傳 `mainStack.peek()`。
5. `getMin()`：回傳 `minStack.peek()`。

invariant：兩棧的 size 永遠相等，且 `minStack` 的第 i 層等於 `mainStack` 前 i 個元素的最小值。因為 `push` 與 `pop` 一律成對操作兩個棧，這個對應關係不會被破壞。

### Complexity

令 `n` 為棧中的元素個數。

**Time complexity: `O(1)`（四個操作皆是）**

`push` 是兩次 push 加一次比較，`pop` 是兩次 pop，`top` 與 `getMin` 各是一次 peek，都與 `n` 無關。

**Space complexity: `O(n)`**

`minStack` 與 `mainStack` 等長。

### Code

```java
/**
 * 155. Min Stack
 * Time Complexity: O(1) for push, pop, top and getMin
 * Space Complexity: O(n)
 */
class MinStack {

    private Deque<Integer> mainStack;
    private Deque<Integer> minStack;

    public MinStack() {
        mainStack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public void push(int value) {
        mainStack.push(value);
        // Store the minimum of this level, so getMin is a single peek.
        int min = minStack.isEmpty() ? value : Math.min(value, minStack.peek());
        minStack.push(min);
    }

    public void pop() {
        // Both stacks always have the same size.
        mainStack.pop();
        minStack.pop();
    }

    public int top() {
        return mainStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
```

### Code Review

- **Learning provenance**：`hint-assisted`，靠 AI 提示才想到輔助棧。能否獨立重現：未確認。
- **Correctness**：正確。兩棧無條件同步，size 恆等，逐層對應關係成立。
- **Strength 1**：`minStack` 每層都存值，而不是「只在更小時才 push」。兩棧永遠等長，`pop()` 不需要任何判斷。這是最不容易寫錯的版本——省空間的變體要在 pop 時比較，而且比較用 `<` 還是 `<=` 是經典 bug（見下方 Optimality）。
- **Strength 2**：用 `ArrayDeque` 而不是 `java.util.Stack`。`Stack` 繼承 `Vector`、方法帶 synchronized、迭代順序還是反的，官方文件本身就建議改用 `Deque`。
- **Strength 3**：用 `push` / `pop` / `peek` 而不是 `addLast` / `removeLast`，語意明確就是在當棧用。
- **Trade-off**：`Deque<Integer>` 每次 push 都會自動裝箱。`Integer` 快取只涵蓋 -128 到 127，超出範圍每次都會配置新物件。本題 `3 * 10^4` 次操作無影響；若要求極致效能，可改用 `int[]` 加一個 top 指標自行管理。
- **Edge cases**：題目保證非空才呼叫，所以不做防護是合理的。若沒有這個保證要知道會怎麼壞：`ArrayDeque.pop()` 在空棧丟 `NoSuchElementException`，而 `peek()` 回傳 `null`、接著自動拆箱成 `int` 會 NPE。
- **Style**：參數命名為 `value` 而非題目的 `val`，不影響。

## 解法比較

只有一個 AC 解法，無需比較表。

### Optimality

以時間為指標，本解已達下限：四個操作都是 `O(1)`，不可能更快。

以空間為指標，`O(n)` 在最壞情況也是下限——輸入嚴格遞減時，每一層的最小值都不同，都必須被記住。

唯一值得學的替代法是把 `minStack` 壓成「只存嚴格必要的最小值」：`push` 時只在 `value <= minStack.peek()` 才推入，`pop` 時只在 `mainStack` 彈出的值等於 `minStack.peek()` 時才跟著彈。最壞情況仍是 `O(n)`，但一般輸入下 `minStack` 會小很多。

這個變體藏著一個經典 bug，值得記住：**比較必須用 `<=`，不能用 `<`。** 用 `<` 的話，重複出現的最小值只會被記錄一次；pop 掉其中一個之後，`minStack` 就把仍然存在於主棧的最小值丟掉了，`getMin()` 從此回傳錯誤答案。

現行寫法沒有這個風險。面試時的理想順序是先寫現行版本（等長同步、不會錯），再補一句「可以只在 `<=` 時 push 來省空間，但等號不能漏」。

## 相關

- [Stack & Queue](../../topics/T06-21-stack-queue.md) — 用第二個棧保存與主棧逐層對應的衍生狀態，把查詢換成 peek
- [LeetCode 刷題總覽](../../_moc.md)
