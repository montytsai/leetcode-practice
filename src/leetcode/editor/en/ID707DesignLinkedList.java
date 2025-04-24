//leetcode submit region begin(Prohibit modification and deletion)
/**
 * 設計實現一個 Linked List，可為:
 * 單鏈表(須含 next, val) or 雙鏈表(須含 prev, val, next)
 */
class MyLinkedList {

    private static class Node {
        /** 該節點的值 */
        int val;
        /** 該鏈表中的上一個節點 */
        Node prev;
        /** 該鏈表中的下一個節點 */
        Node next;

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node prev, Node next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }

    /** 頭節點 */
    private final Node head;
    /** 尾節點 */
    private final Node tail;
    /** 鏈表大小 */
    private int size;

    /** 初始化建構子 */
    public MyLinkedList() {
        this.head = new Node(0); // 初始化建立 dummy head
        this.tail = new Node(0); // 初始化建立 dummy tail
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    /**
     * 取得 第 index 個 node 的 val，若沒有該 index 則回傳 -1
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }

        // 找尋該 index 的原節點
        Node curr = getNode(index);

        return curr.val;
    }

    /**
     * 增加一個值為 val 的頭節點
     */
    public void addAtHead(int val) {
        this.addAtIndex(0, val);
    }

    /**
     * 增加一個值為 val 的節點，在鏈表的最後一個節點
     */
    public void addAtTail(int val) {
        this.addAtIndex(this.size, val);
    }

    /**
     * 在 index 的節點之前增加值為 val 的新節點
     * 若是 index = 鏈表大小，節點在最尾端
     * 若是 index 不在鏈表裡，這個節點不會被新增
     */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            return;
        }

        // 找尋該 index 的原節點
        Node curr = getNode(index);

        // 新增節點
        Node addNode = new Node(val, curr.prev, curr);
        curr.prev.next = addNode;
        curr.prev = addNode;

        // 更新鏈表大小
        this.size++;
    }

    /**
     * 刪除第 index 個節點
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= this.size) {
            return;
        }

        // 找尋該 index 的原節點
        Node curr = getNode(index);

        // 刪除節點
        Node prev = curr.prev;
        Node next = curr.next;
        prev.next = next;
        next.prev = prev;

        // 更新鏈表大小
        this.size--;
    }

    /**
     * 找尋位於 index 的節點
     */
    private Node getNode(int index) {
        Node curr;
        boolean isFromHead = index < size / 2; // 判斷從頭 or 從尾新增節點比較快
        if (isFromHead) { // 從頭找比較快
            curr = this.head;
            for (int i = 0; i <= index; i++) {
                curr = curr.next;
            }
        } else { // 從尾巴找比較快
            curr = this.tail;
            for (int i = 0; i < size - index; i++) {
                curr = curr.prev;
            }
        }
        return curr;
    }

}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
//leetcode submit region end(Prohibit modification and deletion)