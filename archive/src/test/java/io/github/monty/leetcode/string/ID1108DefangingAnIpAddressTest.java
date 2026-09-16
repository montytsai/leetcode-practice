package io.github.monty.leetcode.string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ID1108DefangingAnIpAddressTest {

    @Test
    public void testDefangIPaddr() {
        ID1108DefangingAnIpAddress solution = new ID1108DefangingAnIpAddress();

        // Test case 1
        String input1 = "1.1.1.1";
        String expected1 = "1[.]1[.]1[.]1";
        assertEquals(expected1, solution.defangIPaddr(input1));

        // Test case 2
        String input2 = "255.100.50.0";
        String expected2 = "255[.]100[.]50[.]0";
        assertEquals(expected2, solution.defangIPaddr(input2));

        // Test case 3 (edge case with no dots)
        String input3 = "19216801";
        String expected3 = "19216801";
        assertEquals(expected3, solution.defangIPaddr(input3));

        // Test case 4 (edge case with multiple dots)
        String input4 = "1.0.0.1";
        String expected4 = "1[.]0[.]0[.]1";
        assertEquals(expected4, solution.defangIPaddr(input4));
    }

}
