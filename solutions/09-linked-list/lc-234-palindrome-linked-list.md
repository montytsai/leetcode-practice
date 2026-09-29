---
title: "Palindrome Linked List"
difficulty: Easy
topics: [Linked List, Two Pointers]
category: 09-linked-list
order: 14
source: [Grind75]
platform: LeetCode
url: https://leetcode.com/problems/palindrome-linked-list/
status: ac-solo
note: ""
date_created: 2026-08-04
date_updated: 2026-09-20
---

# 234. Palindrome Linked List

## 題目說明

- 給一個單向 linked list 的 `head`，判斷它是否為回文（正向讀與反向讀的值序列相同）。
- 進階要求是把額外空間壓到 `O(1)`，也就是不能用容器存下半段的值。

## 心得

先用快慢指針走到中間，用 Stack 儲存前半段，再比較前半跟後半段；看最佳解改成 reverse 後半段，空間複雜度降到 `O(1)`。2026-09-20 在 LeetCode Mock Interview 裡獨立寫出反轉後半段版本，AC。

---

## 解法一：Stack 儲存左半段

### Intuition

回文比對本質上是「正向序列」對「反向序列」的逐一比較。Stack 是 LIFO，push 進去再 pop 出來天生就是反向；只要把前半段的值都 push 進 Stack，pop 出來的順序就自動是反向的前半段，可以直接跟後半段正向比較，不用另外寫反轉邏輯。

用快慢指針找中點是因為 linked list 不能像陣列一樣用 `length / 2` 算中間索引；`fast` 走兩步、`slow` 走一步，`fast` 到底時 `slow` 剛好在中點。

### Approach

1. `slow`、`fast` 都從 `head` 出發；`fast` 每次走兩步、`slow` 每次走一步之前，先把 `slow` 目前的值 push 進 Stack。
2. 迴圈在 `fast == null` 或 `fast.next == null` 時停止，此時 Stack 裡存的是前半段（不含中點）的值，`slow` 停在中點（奇數長度）或後半段第一個節點的前一個節點（偶數長度）。
3. 若 `fast != null`（代表奇數長度），把 `slow` 再往後移一格，跳過不需要配對的中間節點。
4. 從 `slow` 開始正向走訪，每走一步跟 Stack `pop` 出來的值比較；只要有一組不相等就回傳 `false`。
5. Stack 清空代表全部配對成功，回傳 `true`。

### Complexity

**Time complexity: `O(n)`**

找中點、比較兩段都只各走訪 linked list 一次，`n` 為節點數。

**Space complexity: `O(n)`**

Stack 存了大約前半段的節點值，`O(n / 2)` 等於 `O(n)`。

### Code

```java
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 234. Palindrome Linked List
 * Stack stores the first half, then compares it against the second half.
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
class Solution {

    public boolean isPalindrome(ListNode head) {
        Deque<Integer> stack = new ArrayDeque<>();

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            // Save the value before moving slow.
            stack.push(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }

        // For an odd-length list, slow is the middle node.
        // Skip it because the middle value does not need a pair.
        if (fast != null) {
            slow = slow.next;
        }

        // Compare the right half with the reversed left half in the stack.
        while (!stack.isEmpty()) {
            if (stack.pop() != slow.val) {
                return false;
            }

            slow = slow.next;
        }

        return true;
    }
}
```

### Code Review

- **Learning provenance**：`ac-solo`，早於 2026-09-20 那次 Mock Interview 的先前解法，provenance 未再細分。
- **Correctness / invariant**：Stack push 與後續走訪的節點數對稱（各約一半），奇數長度時用 `fast != null` 判斷並多跳一格排除中點，`while (!stack.isEmpty())` 只走跟 Stack 內容等長的節點，不會多比或少比。
- **Strength**：不用另外寫反轉 helper，邏輯直覺；原始 linked list 完全沒被修改。
- **Trade-off**：額外用了 `O(n)` 的 Stack，換來的是不動原本的指標結構——需要保留原 list 結構時這是合理選擇。
- **Edge cases**：單節點 list 時迴圈不執行，`slow` 保持在 `head`，Stack 是空的，直接回傳 `true`，正確。

---

## 解法二：反轉後半段原地比較

### Intuition

跟解法一同樣先用快慢指針找中點，但把「用額外容器換反向序列」換成「直接把後半段的指標方向反過來」，比較時兩個指標都只往前走，不需要 Stack，額外空間降到 `O(1)`。

奇偶長度的判斷方式跟解法一相同：`fast` 走到底之後看它是不是 `null`——`null` 代表偶數長度（`slow` 已經停在後半段第一個節點），非 `null` 代表奇數長度，此時中點是 `slow`，要再往後移一格跳過它，`slow` 才會落在真正要反轉的後半段起點。

### Approach

1. `slow`、`fast` 都從 `head` 出發，`fast` 每輪走兩步、`slow` 走一步，直到 `fast == null` 或 `fast.next == null`。
2. 若 `fast != null`（奇數長度），把 `slow` 再往後移一格，讓它指向後半段真正的起點（跳過中間節點）。
3. 呼叫 `reverse(slow)`，把從 `slow` 開始到尾端的子鏈反轉，回傳反轉後的頭節點 `reversed`。
4. 用 `one` 指向 `head`、`two` 指向 `reversed`，同步往前走並逐一比較 `val`；只要有一組不相等就回傳 `false`。
5. `two` 為 `null` 時代表後半段（也就是較短的那一半）已經全部比對完畢，回傳 `true`。

### Complexity

**Time complexity: `O(n)`**

找中點、反轉、比較各只走訪約一半的節點，三段相加仍是 `O(n)`。

**Space complexity: `O(1)`**

`reverse` 是迭代寫法，只用固定數量的指標變數，沒有額外容器也沒有遞迴呼叫堆疊。

### Code

```java
/**
 * 234. Palindrome Linked List
 * Reverse the second half in place and compare both halves with two pointers.
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class Solution {

    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // Odd length: skip the middle node, it needs no pair.
        if (fast != null) {
            slow = slow.next;
        }

        // slow now points to the start of the second half.
        ListNode reversed = reverse(slow);

        ListNode one = head;
        ListNode two = reversed;
        while (two != null) {
            if (one.val != two.val) {
                return false;
            }
            one = one.next;
            two = two.next;
        }

        // The first half may be one node longer for odd-length lists;
        // that extra node is skipped above and needs no comparison.
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        return prev;
    }
}
```

### Code Review

- **Learning provenance**：2026-09-20 在 LeetCode Mock Interview（LeetCode 官方的計時、無提示模擬面試功能）中獨立解出，`ac-solo`。
- **Correctness / invariant**：奇偶長度都靠同一組判斷處理——偶數長度時 `fast` 走到底剛好變成 `null`，`slow` 已停在後半段起點；奇數長度時 `fast` 停在最後一個非 `null` 節點，需要 `slow = slow.next` 多跳一格排除中點。比較迴圈用 `two != null` 收斂，只走訪跟後半段等長的節點數，中間被跳過的節點不會被比到，也不會漏比。
- **Trade-off（非 bug）**：`reverse` 只反轉從 `slow`（跳過中點後）開始的子鏈，`slow` 前一個節點（原本鏈的分界點）的 `.next` 完全沒被觸碰，仍然指向 `slow`；但 `reverse` 的第一輪會把 `slow.next` 設成 `null`。結果是：如果呼叫端在這次呼叫之後再從原始 `head` 走訪整條 list，會在分界點後突然斷掉，只走到 `slow` 就結束，看不到原本後半段其餘的節點——這是對輸入的實質破壞，只是 LeetCode 這題不檢查呼叫後的 list 結構所以能 AC。要復原的話，在回傳前對 `reversed` 再呼叫一次 `reverse`，分界點的 `.next` 因為從未被改過、仍指向 `slow`，重新反轉回正向順序後會自動接回原本位置，list 結構就會還原。
- **Strength**：兩段查找（找中點、比較）加一段原地反轉，三段各自獨立、每段的指標移動都對稱，沒有多餘的分支或特判。
- **Edge cases**：單節點 list 時，找中點迴圈不執行，`fast != null`（`fast` 還是 `head`）成立，`slow = slow.next` 把 `slow` 推成 `null`；`reverse(null)` 回傳 `null`，比較迴圈因為 `two == null` 直接不執行，回傳 `true`，沒有任何空指標存取，正確處理了這個邊界。

---

## 解法比較

| 解法 | Time | Space | 優點 | Trade-off | 使用時機 |
| --- | --- | --- | --- | --- | --- |
| 解法一：Stack | `O(n)` | `O(n)` | 邏輯直覺、原始 list 結構完全不變 | 多用約一半節點量的額外空間 | 需要保留原 list、或不在意額外空間時 |
| 解法二：反轉後半段 | `O(n)` | `O(1)` | 額外空間降到常數 | 反轉會讓分界點之後的原始連結斷開，需要時得自己反轉回來復原 | 空間有限制、且不需要保留呼叫後的原 list 結構 |

### Optimality

以空間為指標，解法二是最佳解：讀完整條 list 至少要 `O(n)` 時間無法再省，但額外空間可以壓到 `O(1)`，不需要 Stack 或遞迴堆疊。

替代法（學習價值）：把 `reverse` 換成遞迴寫法。邏輯相同，但遞迴呼叫堆疊會佔用 `O(n)` 額外空間，等於用可讀性換掉解法二原本省下來的空間優勢，因此只在偏好遞迴風格、且不在意空間退回 `O(n)` 時才考慮。

## 相關

- Linked List — 快慢指針找中點＋原地反轉後半段，是這題最省空間的組合手法
- Two Pointers — `one`／`two` 同步前進比較，`slow`／`fast` 同步前進找中點
- [206. Reverse Linked List](lc-206-reverse-linked-list.md) — 反轉 linked list 的指標基礎
- [LeetCode 刷題總覽](../../_moc.md)
