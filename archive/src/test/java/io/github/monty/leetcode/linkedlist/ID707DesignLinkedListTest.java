package io.github.monty.leetcode.linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ID707DesignLinkedListTest {

    private MyLinkedList list;

    @BeforeEach
    void setUp() {
        list = new MyLinkedList();
    }

    @Test
    void testAddAtHeadAndGet() {
        list.addAtHead(10);
        assertEquals(10, list.get(0));
    }

    @Test
    void testAddAtTailAndGet() {
        list.addAtTail(20);
        assertEquals(20, list.get(0));
    }

    @Test
    void testAddAtIndexMiddle() {
        list.addAtHead(1);
        list.addAtTail(3);
        list.addAtIndex(1, 2);
        assertEquals(2, list.get(1));
    }

    @Test
    void testAddAtIndexInvalid() {
        list.addAtIndex(1, 100); // index > size
        assertEquals(-1, list.get(0));
    }

    @Test
    void testDeleteAtIndex() {
        list.addAtTail(1);
        list.addAtTail(2);
        list.addAtTail(3);
        list.deleteAtIndex(1);
        assertEquals(3, list.get(1));
    }

    @Test
    void testDeleteInvalidIndex() {
        list.deleteAtIndex(0); // no-op, list is empty
        assertEquals(-1, list.get(0));
    }

    @Test
    void testMultipleOperations() {
        list.addAtHead(1);
        list.addAtTail(3);
        list.addAtIndex(1, 2);
        assertEquals(2, list.get(1));
        list.deleteAtIndex(1);
        assertEquals(3, list.get(1));
    }

}
