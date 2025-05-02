//leetcode submit region begin(Prohibit modification and deletion)
/**
 * 用 2 個 Stack(後進先出) 實現 Queue(先進先出)。
 * 限制:
 *  - 只能用 Stack 標準操作: push(x), peek(), pop(), size(), isEmpty()
 *
 * 時間複雜度: 都為O(1)。 pop和peek看起來像O(n)，實際上一個循環n會被使用n次，最後還是O(1)。
 * 空間複雜度: O(n)
 */
class MyQueue {

    private Stack<Integer> sIn;
    private Stack<Integer> sOut;

    public MyQueue() {
        this.sIn = new Stack<>();
        this.sOut = new Stack<>();
    }

    /**
     * 放元素 x 在 Queue 的最後面
     */
    public void push(int x) {
        sIn.push(x);
    }

    /**
     * 移除 Queue 最前面的元素，並回傳它
     */
    public int pop() {
        this.transferIfNeeded();
        return sOut.pop();
    }

    /**
     * 回傳 Queue 最前面的元素
     */
    public int peek() {
        this.transferIfNeeded();
        return sOut.peek();
    }

    /**
     * 回傳 Queue 是否為空
     */
    public boolean empty() {
        return sIn.isEmpty() && sOut.isEmpty();
    }

    /**
     * 運輸 sIn 元素到 sOut
     * - 使用兩個 Stack（sIn、sOut）倒灌實現 FIFO 行為
     * - 採用 lazy load（只有在 sOut 為空時才將 sIn 轉移），避免不必要的操作
     * - 應用於 pop() 與 peek()
     */
    private void transferIfNeeded() {
        if (sOut.isEmpty()) {
            while (!sIn.isEmpty()) {
                sOut.push(sIn.pop());
            }
        }
    }

}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
//leetcode submit region end(Prohibit modification and deletion)