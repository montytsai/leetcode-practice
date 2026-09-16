package io.github.monty.leetcode.stackqueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * LeetCode #347:
 * <a href="https://leetcode.com/problems/top-k-frequent-elements">Top K Frequent Elements</a>
 * 給定一個非空的整數陣列，返回其中出現頻率前 k 高的元素。
 * 時間複雜度必須優於 O(n log n)
 *
 * @author Monty.Tsai
 * @since 2025-05-05 11:22:24
 */
public class ID347TopKFrequentElements {

    /**
     * 解法: 用 stream API 進行排序
     * 時間複雜度：O(n log n)
     * 空間複雜度：O(n)
     */
    public int[] topKFrequentZ(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        // sort() 是 O(n log n)
        return map.entrySet().stream().sorted(
                        (a, b) -> b.getValue().equals(a.getValue())
                                ? a.getKey() - b.getKey()
                                : b.getValue() - a.getValue())
                .limit(k)
                .mapToInt(Map.Entry::getKey)
                .toArray();
    }

    /**
     * 解法: 使用 min heap，維護大小為 k
     * 注意: 使用 Max Heap 為 O(n log n)，因為 heap 大小為 n
     * 時間複雜度：O(n log k)
     * 空間複雜度：O(n)
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(
                Map.Entry.comparingByValue()
        );

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            heap.offer(entry);

            if (heap.size() > k) {
                heap.poll(); // remove frequency minimum
            }
        }

        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = Objects.requireNonNull(heap.poll()).getKey();
        }
        return res;
    }

}