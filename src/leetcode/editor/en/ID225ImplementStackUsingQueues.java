//leetcode submit region begin(Prohibit modification and deletion)
/**
 * 使用 2 個以下的 Queue 模擬 Stack (先進後出)
 * 僅能使用 Queue 的標準操作:
 *  - offer(x): 插入元素
 *  - peek(): 返回頭部(不刪除)，如果 queue 為空，返回 null
 *  - poll(): 移除並返回頭部，如果 queue 為空，返回 null
 *  - isEmpty()
 */
class MyStack {

    private final Queue<Integer> queue;

    public MyStack() {
        queue = new LinkedList<>();
    }

    /**
     * 把元素 x 放在 Stack 的最上面
     * Time: O(1), Space: O(n)
     */
    public void push(int x) {
        queue.offer(x);
    }

    /**
     * 移除 Stack 最上面的元素，並且回傳它
     * Time: O(n), Space: O(n)
     */
    public int pop() {
        return moveUntilOneLeft();
    }

    /**
     * 回傳 Stack 最上面的元素素（不移除）
     * Time: O(n), Space: O(n)
     */
    public int top() {
        int res = moveUntilOneLeft();
        queue.offer(res); // 放回 queue 以保留元素
        return res;
    }

    /**
     * 回傳 Stack 是否為空
     * Time: O(1), Space: O(1)
     */
    public boolean empty() {
        return queue.isEmpty();
    }

    /**
     * 將 queue 中的前 n-1 個元素依序移至尾端，保留最後一個元素並回傳
     * 模擬 Stack 的 pop 行為（最後進的先出）
     * ex. 1 → 2 → 3  => 2 → 3 → 1  => 3 → 1 → 2 (取 3 為答案)
     *
     * @return queue 中最後一個元素
     */
    private int moveUntilOneLeft() {
        // 操作會改變 queue.size()，在某些實作中可能迴圈提前終止，預先存起 size
        int size = queue.size();
        for (int i = 0; i < size - 1; i++) {
            queue.offer(queue.poll());
        }
        return queue.poll();
    }

}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
//leetcode submit region end(Prohibit modification and deletion)

