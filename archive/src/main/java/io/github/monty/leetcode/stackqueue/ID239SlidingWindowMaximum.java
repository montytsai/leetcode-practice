package io.github.monty.leetcode.stackqueue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * LeetCode #239:
 * <a href="https://leetcode.com/problems/sliding-window-maximum">Sliding Window Maximum</a>
 *
 * @author Monty.Tsai
 * @since 2025-05-05
 */
public class ID239SlidingWindowMaximum {

    /**
     * 解法: PriorityQueue (大根堆)
     * Time: O(n log k)，每個元素最多進出堆一次。
     * Space: O(k)，堆最多儲存 k 個元素。
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        int[] res = new int[nums.length - k + 1];

        // 使用 PriorityQueue 儲存資料，保證堆頂是最大(但注意內部元素順序未保證全排序)
        Queue<int[]> maxHeap = new PriorityQueue<>(new Comparator<int[]>() {
            // 重寫 Comparator: 值從大排到小，如果值相同，用 index 排序
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0]) return b[0] - a[0]; // 值大者優先 (回傳正數：a 排在 b 後)
                return b[1] - a[1];                   // 同值時，index 越大越前
            }
        });

        // 初始化 k 值前資料
        for (int i = 0; i < k; i++) {
            maxHeap.offer(new int[]{nums[i], i});
        }
        res[0] = maxHeap.peek()[0];

        // 開始移動視窗
        for (int i = k; i < nums.length; i++) {
            maxHeap.offer(new int[]{nums[i], i});
            // 移除視窗外的元素（index < i - k + 1）
            while (!maxHeap.isEmpty() && maxHeap.peek()[1] < i - k + 1) {
                maxHeap.poll();
            }
            res[i - k + 1] = maxHeap.peek()[0];
        }

        return res;
    }

    /**
     * 解法: 單調隊列（Monotonic Queue）
     * 時間複雜度：O(n)
     * 空間複雜度：O(k)
     */
    public int[] maxSlidingWindowDeque(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        int[] res = new int[nums.length - k + 1];
        int rIdx = 0;

        MyQueue myQueue = new MyQueue();

        // 初始化前 k 個元素
        for (int i = 0; i < k; i++) {
            myQueue.offer(nums[i]);
        }
        res[rIdx++] = myQueue.peek();

        // 開始移動視窗
        for (int i = k; i < nums.length; i++) {
            myQueue.poll(nums[i - k]);
            myQueue.offer(nums[i]);
            res[rIdx++] = myQueue.peek();
        }

        return res;
    }

    /**
     * 自定義單調遞減隊列
     * 假設 nums = [1,3,-1,-3,5,3,6,7], k = 3
     * 單調隊列變化過程：
     * i=0: [1]
     * i=1: [3]     // 1 被踢出，因為 3 > 1
     * i=2: [3, -1]
     * i=3: [3, -1, -3] -> 3 出界，被移除 -> [-1, -3]
     * i=4: [5]    // -1, -3 都比 5 小，全部被踢出
     */
    private static class MyQueue {

        private final Deque<Integer> queue = new ArrayDeque<>();

        // 若離開視窗的元素是最大值，從頭部移除
        void poll(int val) {
            if (!queue.isEmpty() && val == queue.peek()) {
                queue.poll();
            }
        }

        // 維持遞減單調隊列，從尾部移除小於新值的元素
        void offer(int val) {
            while (!queue.isEmpty() && queue.peekLast() < val) {
                queue.pollLast();
            }
            queue.offer(val);
        }

        // 返回頭(最大)元素
        int peek() {
            return queue.peek();
        }

        void print() {
            int[] print = new int[queue.size()];
            int i = 0;
            for (int q : queue) {
                print[i++] = q;
            }
            System.out.println(Arrays.toString(print));
        }

    }

}