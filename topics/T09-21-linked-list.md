# Linked List

Linked List 題的核心限制是「沒有隨機存取」：不能像陣列一樣用索引直接跳到某個位置，所有操作都得靠指標一步一步走。這類題目的難點幾乎都不在演算法本身，而在指標怎麼移動、什麼時候該多留一個變數記住「前一個」或「下一個」。

什麼時候會想到本篇的手法？看到題目要**改變鏈表結構**（刪除、反轉、重排、合併）或**只靠指標找位置**（中點、環、倒數第 N 個、交點），而且不能用陣列/Stack 把值倒出來重排時，就是這裡的技巧。

## 解題技巧

### Dummy Node：頭節點可能被動到就先接一個假頭

刪除、合併、重排這類會影響 `head` 本身的操作，用一個 `dummy.next = head` 的假節點墊在最前面，回傳時再取 `dummy.next`，就不用另外判斷「刪的剛好是第一個節點」這種特例。[19](../solutions/09-linked-list/lc-19-remove-nth-node-from-end-of-list.md)、[203](../solutions/09-linked-list/lc-203-remove-linked-list-elements.md)、[21](../solutions/09-linked-list/lc-21-merge-two-sorted-lists.md)、[24](../solutions/09-linked-list/lc-24-swap-nodes-in-pairs.md) 都靠這招統一頭節點與其他節點的處理邏輯。

```java
ListNode dummy = new ListNode(0);
dummy.next = head;
ListNode cur = dummy;
// ... 用 cur 操作，結尾 return dummy.next;
```

### 反轉：三指標固定順序

反轉一段鏈表只需要 `prev`、`curr`、`temp` 三個指標，但順序錯一步就會斷鏈或造成環：

```java
ListNode prev = null;
ListNode curr = head;
while (curr != null) {
    ListNode temp = curr.next; // 1. 先存下一個，curr.next 馬上要被蓋掉
    curr.next = prev;          // 2. 反轉當前指標
    prev = curr;                // 3. prev 前進
    curr = temp;                 // 4. curr 前進
}
return prev; // curr 變成 null 時，prev 就是新頭
```

[206](../solutions/09-linked-list/lc-206-reverse-linked-list.md) 是這個手法的原型，遞迴版本做的是同一件事，只是「下一輪」交給呼叫堆疊保管。反轉不只用來解「反轉整條鏈表」——[234](../solutions/09-linked-list/lc-234-palindrome-linked-list.md) 把它跟快慢指針組合：找到中點後只反轉後半段，兩段原地比較，額外空間就從 Stack 的 `O(n)` 降到 `O(1)`。[2130](../solutions/09-linked-list/lc-2130-maximum-twin-sum-of-a-linked-list.md) 是同一組合的另一個應用：反轉後半段之後，頭指標與反轉後的頭指標同步前進，就把「首尾配對」轉成兩個指標的同步走訪。

### 快慢指針：找中點、判環、找環入口

`fast` 每次走兩步、`slow` 每次走一步，`fast` 到底時 `slow` 剛好在中點——這是因為鏈表不能用 `length / 2` 直接算索引，只能靠相對速度換位置。[876](../solutions/09-linked-list/lc-876-middle-of-the-linked-list.md)、[2095](../solutions/09-linked-list/lc-2095-delete-the-middle-node-of-a-linked-list.md) 是這招最直接的應用；奇偶長度的邊界處理見下方常見陷阱。

同一組指標拿來判環：如果鏈表有環，`fast` 一定會在環裡追上 `slow`；沒有環，`fast` 會先走到 `null`（[141](../solutions/09-linked-list/lc-141-linked-list-cycle.md)）。要找環的入口（[142](../solutions/09-linked-list/lc-142-linked-list-cycle-ii.md)），關鍵是相遇點到環入口的距離，恰好等於頭節點到環入口的距離——這組數學關係（Floyd's Tortoise and Hare）的完整推導留給 [Two Pointers](T02-21-two-pointers.md) 筆記，這裡只需要知道：相遇後把其中一個指標放回 `head`，兩個指標改成同速前進，再次相遇的節點就是環入口。

### 前後指針間距：倒數第 N 個節點

只能往前走的鏈表要找「倒數第 N 個」，靠兩個指標保持固定間距：快指針先走 `N` 步，之後兩個指針同速前進，快指針到底時慢指針正好停在倒數第 N 個。[19](../solutions/09-linked-list/lc-19-remove-nth-node-from-end-of-list.md) 用這招搭配 dummy node，讓慢指針最後停在「要刪除節點的前一個」，一次到位不用回頭。

### 兩條鏈對齊：長度差先走掉

兩條長度不同的鏈表要找交點（[160](../solutions/09-linked-list/lc-160-intersection-of-two-linked-lists.md)），單純同步前進對不上位置。讓其中一個指標走完自己那條後接到另一條的頭，兩個指標各自走的總距離都是「兩條長度之和」，走到交點時距離自然對齊；不相交時兩者會同時走到 `null`，不用另外特判。

### 奇偶／成對重排：指標重接的順序陷阱

[328](../solutions/09-linked-list/lc-328-odd-even-linked-list.md) 把奇數位置與偶數位置的節點各自串成一條子鏈，最後把偶數鏈接到奇數鏈尾端；[24](../solutions/09-linked-list/lc-24-swap-nodes-in-pairs.md) 兩兩交換節點。這類題目的地雷都在「重接順序」：改掉一個節點的 `next` 之前，如果還需要用到它原本指向誰，要先存下來，跟反轉的 `temp` 是同一個道理。

### 設計題：把鏈表包成一個類別

[707](../solutions/09-linked-list/lc-707-design-linked-list.md) 要求自己實作 `get`、`addAtHead`、`addAtTail`、`addAtIndex`、`deleteAtIndex`。用 dummy head（有時再加 dummy tail）統一頭尾操作，`size` 欄位另外維護以便直接判斷索引是否合法，`Node` 封裝成獨立類別，職責跟外層的鏈表操作邏輯分開。

### 樹上的 next 指標：把每一層當成一條鏈表接

[116](../solutions/08-binary-search/lc-116-populating-next-right-pointers-in-each-node.md)（完美二元樹）與 [117](../solutions/10-trees/lc-117-populating-next-right-pointers-in-each-node-ii.md)（一般二元樹）要求把同一層的節點用 `next` 指標串起來，等於是把 BFS 逐層走訪的順序，改成不用額外 Queue 就能重現：上一層的 `next` 指標建好之後，就可以拿它來走訪整層節點，一邊走一邊把下一層的節點串起來。完美二元樹每個節點的左右子節點必定都存在，串接邏輯很規律；一般二元樹的子節點可能缺一邊或兩邊都缺，需要一個額外指標記住「目前正在串的下一層鏈表尾端」（等同一個下一層的 dummy node），才能在子節點數量不固定時還串得對。

## 常見陷阱

- **null 檢查順序**：`fast.next.next` 這類連續兩步的存取，一定要先確認 `fast != null` 再確認 `fast.next != null`，順序反了會在奇數長度或空鏈表上丟出 NullPointerException。
- **改掉 `next` 前要先存**：任何時候要覆寫一個節點的 `next`，如果後面的邏輯還需要用到它原本指向誰，必須先用一個臨時變數存下來——反轉、成對交換、奇偶重排踩的都是同一個坑。
- **修改輸入鏈表的隱藏代價**：反轉、原地重排都會動到既有節點的指標，呼叫端如果之後還要用原本的鏈表結構，這類解法會造成無法預期的斷鏈（[234](../solutions/09-linked-list/lc-234-palindrome-linked-list.md) 解法二的反轉後半段就是一例）。需要保留原結構時，要嘛用額外容器換取不動指標，要嘛在回傳前把改過的部分反轉回去。
- **tail pointer 忘記前進**：合併、拼接類操作接上新節點後，如果忘記把尾端指標移到剛接上的節點，下一輪會一直覆寫同一個 `next`（[21](../solutions/09-linked-list/lc-21-merge-two-sorted-lists.md)）。
- **奇偶長度分開驗**：快慢指針找中點的迴圈結束條件在奇數與偶數長度下停的位置不一樣，任何用到中點的題目都要各驗一次奇偶兩種長度，不能只測一種就當作通過。

## 已刷題目

- [116. Populating Next Right Pointers in Each Node](../solutions/08-binary-search/lc-116-populating-next-right-pointers-in-each-node.md) Medium — 完美二元樹逐層接 next 指標，靠上一層已建好的 next 走訪整層
- [117. Populating Next Right Pointers in Each Node II](../solutions/10-trees/lc-117-populating-next-right-pointers-in-each-node-ii.md) Medium — 一般二元樹版，子節點數量不固定，需要額外指標記住下一層鏈表尾端
- [141. Linked List Cycle](../solutions/09-linked-list/lc-141-linked-list-cycle.md) Easy — 快慢指針判環的最小案例，只需回傳有沒有環
- [142. Linked List Cycle II](../solutions/09-linked-list/lc-142-linked-list-cycle-ii.md) Medium — 找環入口，靠相遇點到入口與頭節點到入口等距的關係
- [160. Intersection of Two Linked Lists](../solutions/09-linked-list/lc-160-intersection-of-two-linked-lists.md) Easy — 兩條不等長鏈表找交點，切換頭指標讓兩指標總走訪距離相等
- [19. Remove Nth Node From End of List](../solutions/09-linked-list/lc-19-remove-nth-node-from-end-of-list.md) Medium — 前後指針保持 N 步間距，搭配 dummy node 一次刪到倒數第 N 個
- [203. Remove Linked List Elements](../solutions/09-linked-list/lc-203-remove-linked-list-elements.md) Easy — 刪除指定值節點，dummy node 統一處理頭節點被刪的情況
- [206. Reverse Linked List](../solutions/09-linked-list/lc-206-reverse-linked-list.md) Easy — 反轉整條鏈表的迭代與遞迴兩種寫法，三指標固定順序
- [2095. Delete the Middle Node of a Linked List](../solutions/09-linked-list/lc-2095-delete-the-middle-node-of-a-linked-list.md) Medium — 快慢指針找中點的變形，刪除時要記錄中點前一個節點
- [21. Merge Two Sorted Lists](../solutions/09-linked-list/lc-21-merge-two-sorted-lists.md) Easy — dummy node ＋ two pointers 合併兩條已排序鏈表，尾端指標要記得前進
- [2130. Maximum Twin Sum of a Linked List](../solutions/09-linked-list/lc-2130-maximum-twin-sum-of-a-linked-list.md) Medium — 快慢指針找中點＋反轉後半段，把首尾配對問題轉成同步走訪
- [234. Palindrome Linked List](../solutions/09-linked-list/lc-234-palindrome-linked-list.md) Easy — 快慢指針找中點，Stack 與反轉後半段兩種手法比較回文，並點出反轉法會破壞原鏈表
- [24. Swap Nodes in Pairs](../solutions/09-linked-list/lc-24-swap-nodes-in-pairs.md) Medium — 兩兩成對交換節點，dummy node ＋ 指標重接順序陷阱
- [328. Odd Even Linked List](../solutions/09-linked-list/lc-328-odd-even-linked-list.md) Medium — 奇偶位置節點各自串成子鏈，最後接回一條
- [707. Design Linked List](../solutions/09-linked-list/lc-707-design-linked-list.md) Medium — 設計題，dummy head／tail 統一邊界，`Node` 封裝成獨立類別
- [876. Middle of the Linked List](../solutions/09-linked-list/lc-876-middle-of-the-linked-list.md) Easy — 快慢指針找中點的最基本案例

## 相關

- [_index](_index.md) — 主題筆記索引
- [_moc](../_moc.md) — 全題清單與完成狀態
- [Two Pointers](T02-21-two-pointers.md) — 快慢指針、對撞指針的通用判準與 pointer invariant
- [Binary Tree](T10-21-binary-tree.md) — lc-116／117 的走訪順序與層邊界處理，跟樹的其他層序題共用
- [Breadth-First Search](T10-13-breadth-first-search.md) — lc-116／117 是省掉 Queue 的逐層走訪，跟 BFS 是同一件事的兩種寫法
