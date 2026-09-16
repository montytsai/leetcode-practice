# Stack & Queue

Stack 與 Queue 都保存尚未完成的狀態，但取出順序不同：Stack 是 Last In, First Out，適合巢狀結構、撤銷與 postfix evaluation；Queue 是 First In, First Out，適合依到達順序處理事件。需要維護區間內的候選值時，可再考慮 Monotonic Deque。

## 解題技巧

- RPN evaluation 遇到 number 就 push；遇到 operator 時先 pop `right`、再 pop `left`，最後計算 `left operator right`。Subtraction 與 division 不可交換順序。
- 用兩個 Stack 模擬 Queue 時，一個負責輸入、一個負責輸出；只有輸出 Stack 為空時才整批搬移。
- 用 Queue 模擬 Stack 時，可在 push 後旋轉 Queue，讓最新元素移到最前面。
- Monotonic Deque 只保留仍可能成為答案的候選值，同時移除過期 index 與失去競爭力的尾端元素。
- 巢狀字串、括號與碰撞題通常把「尚未完成的局部狀態」留在 Stack，等配對條件出現再合併。
- pop 之前先確認 Stack 不為空，避免 Runtime Error。
- 需要在 `O(1)` 查詢某種聚合值（最小、最大）時，開第二個 Stack 與主 Stack 同步 push／pop，第 i 層存前 i 個元素的聚合結果。成立的前提是該值只由前 i 個元素決定，之後的操作不會回頭修改它。
- Stack 模擬最直覺，但會保存中間結果；題目要求 O(1) space 時，再找能不能用 pointer 從尾端反向跳過無效內容。
- Java 的 `Character` 是物件；比較字元值時先 unbox 成 `char` 或用 `.equals()`，不要依賴 `==`／`!=` 的 reference comparison。

## 已刷題目

- [20. Valid Parentheses](../solutions/06-stack/lc-20-valid-parentheses.md) Easy — 用 Stack 配對最近的左括號。
- [150. Evaluate Reverse Polish Notation](../solutions/06-stack/lc-150-evaluate-reverse-polish-notation.md) Medium — pop 右 operand 再 pop 左 operand，維持 operator 順序。
- [155. Min Stack](../solutions/06-stack/lc-155-min-stack.md) Medium — 輔助棧：第二個棧逐層對應主棧，把 O(n) 的查詢換成 O(1) 的 peek。
- [225. Implement Stack Using Queues](../solutions/06-stack/lc-225-implement-stack-using-queues.md) Easy — 旋轉 Queue 讓最新元素先被取出。
- [232. Implement Queue using Stacks](../solutions/06-stack/lc-232-implement-queue-using-stacks.md) Easy — 用兩個 Stack 反轉順序。
- [239. Sliding Window Maximum](../solutions/04-sliding-window/lc-239-sliding-window-maximum.md) Hard — 用 Monotonic Deque 維護視窗最大值候選。
- [347. Top K Frequent Elements](../solutions/01-arrays-hashing/lc-347-top-k-frequent-elements.md) Medium — 用 Priority Queue 維護 Top K。
- [394. Decode String](../solutions/06-stack/lc-394-decode-string.md) Medium — 用 Stack 保存巢狀字串與重複次數。
- [649. Dota2 Senate](../solutions/18-greedy/lc-649-dota2-senate.md) Medium — 用 Queue 模擬下一輪仍有效的 senate。
- [735. Asteroid Collision](../solutions/06-stack/lc-735-asteroid-collision.md) Medium — 用 Stack 維護尚未消除的小行星。
- [844. Backspace String Compare](../solutions/02-two-pointers/lc-844-backspace-string-compare.md) Easy — 用 Stack 直接模擬 backspace 後的結果。
- [933. Number of Recent Calls](../solutions/06-stack/lc-933-number-of-recent-calls.md) Easy — 用 Queue 移除時間窗外的 request。
- [1047. Remove All Adjacent Duplicates In String](../solutions/06-stack/lc-1047-remove-all-adjacent-duplicates-in-string.md) Easy — 相鄰相同字元用 Stack 互相抵消。
- [2390. Removing Stars From a String](../solutions/06-stack/lc-2390-removing-stars-from-a-string.md) Medium — 每個星號刪除 Stack 中最近的字元。

## 相關

- [_moc](../_moc.md)
- [Heap (Priority Queue)](T11-21-heap-priority-queue.md)
