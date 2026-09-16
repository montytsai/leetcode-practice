---
title: "Palindrome Linked List"
difficulty: Easy
topics: [Linked List, Two Pointers]
category: 09-linked-list
order: 14
source: [Grind75]
platform: LeetCode
url: https://leetcode.com/problems/palindrome-linked-list/
status: ac-unknown
note: ""
date_created: 2026-08-04
date_updated: 2026-08-04
---

## 心得

先用快慢指針走到中間，用 Stack 儲存前半段，再比較前半跟後半段；看最佳解改成 reverse 後半段，空間複雜度降到 `O(1)`。

## 解法一：Stack 儲存左半段

```java
import java.util.ArrayDeque;
import java.util.Deque;

class Solution {

    public boolean isPalindrome(ListNode head) {
        Deque<Integer> stack = new ArrayDeque<>();

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            // Save the value before moving slow.
            stack.push(slow.val);

            // KEY! slow has already moved to the next node here.
            // The stack still contains only the values before the new slow.
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

### Stack 解法的關鍵過程

以：

```text
1 -> 2 -> 3 -> 2 -> 1
```

慢指標走到 `3` 時：

```text
stack = [1, 2]
slow  = 3
```

`3` 沒有被 push，是因為每一輪是先 push 當前 `slow`，再移動 `slow`。奇數長度時，`fast != null`，所以把 `slow` 往右移一格跳過中間節點，再用 stack 的 `[2, 1]` 與右半段比較。

這裡 stack 只需要儲存大約一半的節點，但：

```text
O(n / 2) = O(n)
```

所以 Stack 解法是 `O(n)` 額外空間。

## 解法二：反轉後半段

```java
/**
 * 234. Palindrome Linked List
 * Reverse the second half iteratively and compare both parts.
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {

    public boolean isPalindrome(ListNode head) {
        // Find the middle node. O(n / 2)
        ListNode mid = findMid(head);

        // Reverse the list starting from mid. O(n / 2)
        ListNode right = reverse(mid);
        ListNode left = head;

        // Compare the left part with the reversed right part. O(n / 2)
        // The reversed list also contains mid as its final node.
        while (right != null) {
            if (left.val != right.val) {
                return false;
            }

            left = left.next;
            right = right.next;
        }

        return true;
    }

    private ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // Version A: iterative reversal.
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

### 反轉 helper 的遞迴版本

Palindrome 的 `isPalindrome` 判斷流程不變，只把反轉 helper 換成遞迴版本：

```java
// Call this version with reverse(slow.next, slow).
private ListNode reverse(ListNode node, ListNode prev) {
    if (node == null) {
        return prev;
    }

    ListNode next = node.next;
    node.next = prev;

    return reverse(next, node);
}
```

使用遞迴版本時，因為 `slow` 被當成 `prev` 傳入，反轉後可能仍然保留一段舊連結；因此需要在反轉後補上：

```java
slow.next = null;
```

迭代版本從 `mid` 開始、`prev` 初始為 `null`，第一輪就會讓 `mid.next = null`，所以這版不需要另外切斷。

## 指標與截斷的重點

### `mid` 在奇數與偶數長度的位置

- 奇數長度：`mid` 是中間節點。
- 偶數長度：`mid` 是右半段的第一個節點。

因此這版直接呼叫：

```java
ListNode right = reverse(mid);
```

不需要另外判斷奇數，也不需要先寫 `mid = mid.next`。

### 為什麼迭代反轉會截斷尾巴？

迭代反轉的第一輪就會執行：

```java
curr.next = prev;
```

而一開始：

```java
prev = null;
curr = mid;
```

所以第一輪會變成：

```text
mid.next = null
```

這會把原本 `mid` 後面的鏈結切斷。之後 `mid` 會成為反轉後鏈表的尾端，`right` 會一路走到 `mid` 結束。

這和某些遞迴寫法不同：如果遞迴 helper 把 `slow` 當成 `prev` 傳入，反轉後可能需要另外執行 `slow.next = null` 才能切斷舊連結。

### 複雜度

#### Stack 解法

- Time：`O(n)`，找中間與比較都只走訪 linked list 一次的量級。
- Space：`O(n)`，stack 保存約一半的節點值，而 `O(n / 2) = O(n)`。

#### 反轉後半段解法

- 找中間：`O(n / 2)`。
- 反轉後半段：`O(n / 2)`。
- 比較：`O(n / 2)`。
- 總時間：`O(n)`。
- 迭代反轉沒有遞迴 stack 或額外容器：`O(1)` 空間。
- 若使用遞迴反轉 helper，額外空間會變成 `O(n)`，因為需要遞迴 call stack。

這個方法會修改原本的 linked list，而且沒有復原；LeetCode 題目通常接受。如果題目要求保留原鏈表，需要再把後半段反轉一次恢復。

## 相關

- [206. Reverse Linked List](lc-206-reverse-linked-list.md) — 反轉 linked list 的指標基礎。
- [_moc](../../_moc.md)
