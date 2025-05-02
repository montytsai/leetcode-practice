package io.github.monty.leetcode.stackqueue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ID225ImplementStackUsingQueuesTest {

    private MyStack stack;

    @BeforeEach
    void setUp() {
        stack = new MyStack();
    }

    @Test
    void testEmptyStack() {
        assertTrue(stack.empty(), "Stack 應為空");
    }

    @Test
    void testPushAndTop() {
        stack.push(10);
        assertFalse(stack.empty(), "Stack 不應為空");
        assertEquals(10, stack.top(), "top() 應回傳 10");

        stack.push(20);
        assertEquals(20, stack.top(), "top() 應回傳 20");

        stack.push(30);
        assertEquals(30, stack.top(), "top() 應回傳 30");
    }

    @Test
    void testPushAndPop() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop(), "pop() 應回傳 3");
        assertEquals(2, stack.pop(), "pop() 應回傳 2");
        assertEquals(1, stack.pop(), "pop() 應回傳 1");
        assertTrue(stack.empty(), "所有元素 pop 完後應為空");
    }

    @Test
    void testInterleavedOperations() {
        stack.push(5);
        assertEquals(5, stack.top());

        stack.push(10);
        assertEquals(10, stack.top());

        assertEquals(10, stack.pop());
        assertEquals(5, stack.top());

        stack.push(15);
        assertEquals(15, stack.top());
        assertEquals(15, stack.pop());
        assertEquals(5, stack.pop());
        assertTrue(stack.empty());
    }

}