---
title: "LRU Cache"
difficulty: Medium
topics: [Linked List, Hash Table, Design, Doubly-Linked List]
category: 09-linked-list
order: 15
source: [Grind75]
platform: LeetCode
url: https://leetcode.com/problems/lru-cache/
status: ac-assisted
note: "兩個解法都靠參考：解法一查 API、解法二看完解答才寫，仍提交失敗兩次才 AC；待不看解答重寫解法二"
date_created: 2026-10-09
date_updated: 2026-10-09
---

# 146. LRU Cache

## 題目說明

- 設計一個容量固定的 LRU（Least Recently Used）快取，支援 `get(key)` 與 `put(key, value)`，兩者平均時間都要 `O(1)`。
- `get` 找不到回傳 `-1`；`put` 讓 key 數量超過 `capacity` 時，淘汰最久沒被使用的 key。`get` 與 `put` 都算「使用」。

## 心得

解法一：去查 API 寫的，才發現有這方法可以用，我根本背不起來有這方法，而且我不知道 0.75f 怎麼來的。

解法二：看完解答寫的，仍然提交失敗兩次才 AC。這解靠 map 存值，node 來存位置。

這次補上的重點：

- `0.75f` 是 `HashMap` 的預設 load factor。`LinkedHashMap` 只有三參數建構子能開 `accessOrder`，所以必須傳 load factor，照抄預設值即可。
- 要記的是結構，不是 API 名字：需求是「依 key `O(1)` 找值」加「依使用順序 `O(1)` 更新與淘汰」，標準答案就是 HashMap + 雙向鏈表，`LinkedHashMap` 是 JDK 把這個組合包好的版本。

---

## 解法一：LinkedHashMap（accessOrder + removeEldestEntry）

### Intuition

題目有兩個要同時達到 `O(1)` 的需求：依 key 找值，以及依「最近使用」順序更新與淘汰。只用 HashMap 沒有順序可言；只用鏈表找 key 要 `O(n)`。標準做法是兩者合用：HashMap 由 key 直接拿到鏈表節點，鏈表維護使用順序，一端放最近用過的、另一端放最久沒用的。

鏈表必須是雙向。從 HashMap 拿到節點後要把它摘出來移到最前面，摘除需要接起前後兩個鄰居，單向鏈表得從頭找前驅，變成 `O(n)`。

`LinkedHashMap` 就是這個組合的現成實作：繼承 `HashMap`，並把所有 entry 另外串成一條雙向鏈表。預設照插入順序；`accessOrder = true` 時，每次 `get` 或對既有 key 的 `put` 都會把該 entry 移到鏈表尾端，所以鏈表頭永遠是最久沒被使用的。

淘汰靠 `removeEldestEntry(eldest)` 這個 hook：`put` 新增 entry 之後 `LinkedHashMap` 會呼叫它，回傳 `true` 就刪掉鏈表頭。覆寫成 `size() > capacity` 就是 LRU。這不用硬背，`LinkedHashMap` 的 Javadoc 本身就以 LRU 快取為例。

`0.75f` 是 `HashMap` 的預設 load factor：entry 數超過 table 大小的 75% 時，table 擴成兩倍。它是時間與空間的折衷，值低則碰撞少但吃空間，值高則省空間但鏈變長。這題對正確性沒有影響，三參數建構子是唯一能設 `accessOrder` 的建構子，所以必須傳一個值，傳預設的 `0.75f` 即可。

### Approach

1. 建構子建立 `LinkedHashMap`：`initialCapacity` 傳 `capacity`、load factor 傳 `0.75f`、`accessOrder` 傳 `true`。
2. 以匿名子類別覆寫 `removeEldestEntry`，回傳 `size() > capacity`。
3. `get`：key 不存在回傳 `-1`；存在就 `map.get(key)`，這個呼叫同時把 entry 移到鏈表尾端。
4. `put`：直接 `map.put(key, value)`。既有 key 會更新值並移到尾端；新 key 加入後若超過容量，hook 會淘汰鏈表頭。

### Complexity

**Time complexity: `O(1)`**

`get` 與 `put` 都是一次 hash 查找加上常數次鏈表指標調整，淘汰鏈表頭也是常數操作。這是平均複雜度，與 `HashMap` 一致。

**Space complexity: `O(capacity)`**

任何時刻最多存 `capacity` 個 entry（新增後短暫多一個，隨即被淘汰），每個 entry 帶有 hash 節點與前後指標。

### Code

```java
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 146. LRU Cache
 * LinkedHashMap in access order keeps the least recently used entry at the head.
 * Time Complexity: O(1) for get and put
 * Space Complexity: O(capacity)
 */
class LRUCache {

    private int capacity;
    private Map<Integer, Integer> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        // accessOrder = true: get and put move the entry to the tail.
        this.map = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                // Called after each insertion. Returning true removes the eldest entry.
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        return map.get(key);
    }

    public void put(int key, int value) {
        map.put(key, value);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```

### Code Review

- **Learning provenance**：`reference-assisted`，對應 `status: ac-assisted`。她自述是查 API 才寫出來，並說背不起這個用法；能否獨立重現未確認，依她的說法視為目前不能。
- **Correctness / invariant**：`containsKey` 不會改變順序，真正移動 entry 的是後面那次 `map.get`，所以「先判斷再取值」的寫法是對的。實測 `containsKey(1)` 後順序不變、`get` 後 1 移到尾端、對既有 key `put` 也會移到尾端。新增第 `capacity + 1` 個 key 時淘汰鏈表頭，行為符合題意。
- **Strength**：把「順序維護」和「淘汰」都交給 JDK，沒有手寫指標的出錯空間；`removeEldestEntry` 用 `size() > capacity` 判斷，邊界正確。
- **Style**：`get` 可以簡化成 `return map.getOrDefault(key, -1);`。`LinkedHashMap.getOrDefault` 在 access order 下同樣會移動 entry（實測 `getOrDefault(1, -1)` 後 1 移到尾端），並少一次查找。
- **Style**：匿名子類別裡的 `capacity` 取到的是建構子參數（它比欄位更近），所以欄位 `this.capacity` 在這份程式裡沒有被讀取，可以刪掉。
- **Trade-off**：匿名子類別加上菱形運算子 `<>` 需要 Java 9 以上；LeetCode 的 Java 版本沒問題，但在 Java 8 環境會編譯失敗，要寫成 `new LinkedHashMap<Integer, Integer>(...)`。
- **Trade-off**：這是呼叫現成函式庫。面試通常期待手刻 HashMap + 雙向鏈表，見下方最佳性判斷。
- **Edge cases**：`capacity = 1` 時每次新 key 都淘汰前一個，行為正確；`put` 既有 key 只更新值與順序，不觸發淘汰。

---

## 解法二：HashMap + 手刻雙向鏈表

### Intuition

解法一的 `LinkedHashMap` 內部做的事，這個解法自己寫出來：HashMap 負責「依 key 找到東西」，雙向鏈表負責「使用順序」。兩者分工，HashMap 的 value 不存數值，存的是鏈表節點，所以「值」從節點上讀，「位置」也由節點決定。

鏈表約定尾端（`latest` 前一個）是最近使用，頭端（`oldest` 後一個）是最久沒用。`get` 命中與 `put` 都是「把節點摘下、接到尾端」；超過容量時淘汰的永遠是 `oldest.next`。

鏈表頭尾各放一個 dummy 節點（`oldest`、`latest`），真正的節點永遠夾在兩者之間，摘除與插入因此不用判斷「是不是頭或尾」。dummy 的內容不會被讀取，只提供 `prev`／`next` 的錨點。

節點必須存 `key`。淘汰時手上只有鏈表上的節點，要從 HashMap 刪掉對應項目，只能靠節點裡的 `key` 反查。

### Approach

1. 建構子建立 HashMap 與兩個 dummy 節點，並讓 `oldest.next = latest`、`latest.prev = oldest`。
2. `get`：key 不存在回傳 `-1`；存在就從 HashMap 取出節點，先 `remove` 再 `add` 把它移到尾端，回傳 `val`。
3. `put`：key 已存在就先把舊節點從鏈表摘掉；接著建立新節點，放進 HashMap（會蓋掉舊的項目）並 `add` 到尾端。
4. `put` 之後若 `cache.size() > capacity`，取 `oldest.next` 這個最久沒用的節點，從鏈表摘除並用它的 `key` 從 HashMap 刪掉。
5. `add(node)` 把節點插在 `latest` 前面；`remove(node)` 讓前後兩個鄰居互相指向。兩者都只改固定數量的指標。

### Complexity

**Time complexity: `O(1)`**

HashMap 查找是平均 `O(1)`，`add`、`remove` 各只改固定數量的指標，淘汰也只動一個節點。

**Space complexity: `O(capacity)`**

HashMap 與鏈表各存最多 `capacity` 個項目（新增後短暫多一個，隨即淘汰），另加兩個 dummy 節點。

### Code

```java
import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU Cache
 * HashMap finds a node by key; a doubly linked list keeps the usage order.
 * Time Complexity: O(1) for get and put
 * Space Complexity: O(capacity)
 */
class LRUCache {

    private int capacity;
    private Map<Integer, Node> cache;
    // Dummy nodes: real nodes always sit between oldest and latest.
    private Node oldest;
    private Node latest;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
        this.oldest = new Node(0, 0);
        this.latest = new Node(0, 0);
        this.oldest.next = latest;
        this.latest.prev = oldest;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        // Move the node to the most recently used end.
        Node node = cache.get(key);
        remove(node);
        add(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // Unlink the old node; the map entry is replaced below.
            remove(cache.get(key));
        }
        Node node = new Node(key, value);
        cache.put(key, node);
        add(node);

        if (cache.size() > capacity) {
            // The least recently used node is right after the oldest dummy.
            Node lru = oldest.next;
            remove(lru);
            // The node stores its key, so we can delete it from the map.
            cache.remove(lru.key);
        }
    }

    // Insert the node just before the latest dummy.
    private void add(Node newNode) {
        Node prev = latest.prev;
        prev.next = newNode;

        newNode.prev = prev;
        newNode.next = latest;

        latest.prev = newNode;
    }

    // Unlink the node by connecting its two neighbors.
    private void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

}

class Node {

    int key;
    int val;
    Node prev;
    Node next;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
        this.prev = null;
        this.next = null;
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```

### Code Review

- **Learning provenance**：`reference-assisted`，對應 `status: ac-assisted`。她自述是看完解答才寫，仍提交失敗兩次才 AC；失敗原因她沒說，記為未確認。能否不看解答重寫未確認。
- **Correctness / invariant**：不變式是「鏈表上的節點集合 = HashMap 的 value 集合，且順序由舊到新」。`get`、`put` 都以 `remove` 後 `add` 維持它；淘汰時鏈表與 HashMap 同步刪除，由節點存 `key` 保證。我拿它和解法一對 20000 組隨機操作序列（容量 1 到 5、key 0 到 7）比對，輸出全部一致。
- **Strength**：兩個 dummy 節點讓 `add`、`remove` 沒有任何邊界判斷；`oldest` 與 `latest` 的命名直接表達方向，比 `head`、`tail` 不容易搞混。
- **Trade-off**：`put` 既有 key 時是丟掉舊節點、重建新節點，而不是就地改 `val` 再移到尾端。結果正確，只是多配置一個物件；就地更新少一次配置，是同一解法的小改良。
- **Style**：`get` 先 `containsKey` 再 `get`，查了兩次 HashMap；改成 `Node node = cache.get(key); if (node == null) return -1;` 一次查找即可。`put` 同理。`Node` 的建構子裡把 `prev`、`next` 設成 `null` 是多餘的，欄位預設就是 `null`。
- **Edge cases**：`capacity = 1` 時每個新 key 淘汰前一個，淘汰的是 `oldest.next`，不會是剛加入的節點。題目保證 `capacity >= 1`，所以淘汰時鏈表上至少有一個真實節點，dummy 不會被當成 LRU 刪掉。

---

## 解法比較

| 解法 | Time | Space | 優點 | Trade-off | 使用時機 |
| --- | --- | --- | --- | --- | --- |
| 解法一：LinkedHashMap | `O(1)` | `O(capacity)` | 程式碼短，指標操作交給 JDK | 要記得 API 與 hook；面試常不被接受 | 時間緊、題目沒禁用函式庫 |
| 解法二：HashMap + 手刻雙向鏈表 | `O(1)` | `O(capacity)` | 完整掌握機制，可移植到沒有 `LinkedHashMap` 的語言 | 指標操作多，容易漏掉淘汰時同步刪 HashMap | 面試、要證明理解資料結構 |

## Optimality

以時間與空間為指標，兩個解法都是最佳：`get`、`put` 為 `O(1)`，空間 `O(capacity)`，已達題目要求，沒有更低的漸近複雜度。

以面試價值為指標，解法二是面試官預期的答案，解法一是知道函式庫時的捷徑。解法二的要點是 HashMap 的 value 存節點、節點存 `key`、dummy 頭尾，三者缺一都會讓淘汰或邊界出錯。既然兩個解法都已 AC，沒有再需要補的替代法；下一步是不看解答重寫解法二。

## 相關

- Linked List — 雙向鏈表搭配 HashMap，摘除與插入節點都是 `O(1)`
- Hash Table — 用 key 直接定位鏈表節點，省掉走訪鏈表找 key 的 `O(n)`
- Design — 把資料結構包成類別，題目要求每個操作的複雜度
- Doubly-Linked List — `LinkedHashMap` 內部以雙向鏈表維護 access order
- 主題筆記：[linked-list](../../topics/T09-21-linked-list.md)、[hash-table](../../topics/T01-22-hash-table.md)、[stack-queue](../../topics/T06-21-stack-queue.md)
- [LeetCode 刷題總覽](../../_moc.md)
