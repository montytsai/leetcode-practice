package io.github.monty.leetcode.array;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ID27RemoveElementTest {

    @Test
    public void testExample1() {
        ID27RemoveElement test = new ID27RemoveElement();
        int[] nums = {3,2,2,3};
        int val = 3;
        assertEquals(2, test.removeElement(nums, val));
    }

    @Test
    public void testExample2() {
        ID27RemoveElement test = new ID27RemoveElement();
        int[] nums = {0,1,2,2,3,0,4,2};
        int val = 2;
        assertEquals(5, test.removeElement(nums, val));
    }

}