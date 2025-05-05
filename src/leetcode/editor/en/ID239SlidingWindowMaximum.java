//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        int[] res = new int[nums.length - k + 1];
        int rIdx = 0;

        MyQueue queue = new MyQueue();

        // 初始化前 k 個元素
        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }
        res[rIdx++] = queue.peek();

        // 移動視窗: k ~ len-1
        for (int i = k; i < nums.length; i++) {
            queue.poll(nums[i - k]);
            queue.offer(nums[i]);
            res[rIdx++] = queue.peek();
        }

        return res;
    }

    /**
     * 自定義單調遞減 queue
     */
    private static class MyQueue {

        private final Deque<Integer> queue = new ArrayDeque();

        // 插入元素: for 維持遞減單調規則，從尾部移除小於新值的元素
        void offer(int val) {
            while (!queue.isEmpty() && queue.peekLast() < val) {
                queue.pollLast();
            }
            queue.offerLast(val);
        }

        // 移除並返回頭部: 若離開視窗的元素是最大值，移除
        void poll(int val) {
            if (!queue.isEmpty() && val == queue.peek()) {
                queue.poll();
            }
        }

        // 返回頭部: 單調遞減 queue，頭部必為最大值
        int peek() {
            return queue.peek();
        }

    }

}
//leetcode submit region end(Prohibit modification and deletion)

