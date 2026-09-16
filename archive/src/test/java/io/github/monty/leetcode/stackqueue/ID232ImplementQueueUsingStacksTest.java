package io.github.monty.leetcode.stackqueue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 測試 MyQueue 類別是否正確使用兩個 Stack 實作 Queue（FIFO 行為）
 */
class ID232ImplementQueueUsingStacksTest {

    private MyQueue queue;

    @BeforeEach
    void setUp() {
        queue = new MyQueue(); // 每個測試方法前初始化新的 Queue
    }

    @Test
    void testEmptyInitially() {
        // 初始狀態應為空
        assertTrue(queue.empty(), "Queue 應為空");
    }

    @Test
    void testPushAndPeek() {
        queue.push(1);
        assertFalse(queue.empty(), "加入一個元素後不應為空");
        assertEquals(1, queue.peek(), "peek() 應回傳第一個元素");
    }

    @Test
    void testPushMultipleAndPeek() {
        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertEquals(1, queue.peek(), "peek() 應回傳第一個 push 的元素");
    }

    @Test
    void testPopSingleElement() {
        queue.push(10);
        int popped = queue.pop();
        assertEquals(10, popped, "pop() 應回傳唯一的元素");
        assertTrue(queue.empty(), "pop 後應為空");
    }

    @Test
    void testPushPopSequence() {
        queue.push(5);
        queue.push(6);
        assertEquals(5, queue.pop(), "pop() 應回傳 5");
        queue.push(7);
        assertEquals(6, queue.pop(), "pop() 應回傳 6");
        assertEquals(7, queue.peek(), "peek() 應回傳 7");
        assertEquals(7, queue.pop(), "pop() 應回傳 7");
        assertTrue(queue.empty(), "所有元素移除後應為空");
    }

    @Test
    void testInterleavedPushPop() {
        queue.push(1);
        assertEquals(1, queue.pop());
        queue.push(2);
        queue.push(3);
        assertEquals(2, queue.pop());
        queue.push(4);
        assertEquals(3, queue.pop());
        assertEquals(4, queue.pop());
        assertTrue(queue.empty(), "最終應為空");
    }

    @Test
    void testPeekDoesNotRemove() {
        queue.push(100);
        assertEquals(100, queue.peek(), "第一次 peek 應回傳 100");
        assertEquals(100, queue.peek(), "第二次 peek 應仍為 100");
        assertFalse(queue.empty(), "peek 不應移除元素");
    }

}
