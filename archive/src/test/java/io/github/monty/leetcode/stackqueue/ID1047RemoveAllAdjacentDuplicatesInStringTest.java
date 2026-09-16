package io.github.monty.leetcode.stackqueue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ID1047RemoveAllAdjacentDuplicatesInStringTest {

    private ID1047RemoveAllAdjacentDuplicatesInString solution;

    @BeforeEach
    public void setUp() {
        solution = new ID1047RemoveAllAdjacentDuplicatesInString();
    }

    @Test
    void testExampleCases() {
        assertEquals("ca", solution.removeDuplicates("abbaca"));
        assertEquals("", solution.removeDuplicates("aabbccdd"));
        assertEquals("abc", solution.removeDuplicates("abc"));
        assertEquals("ay", solution.removeDuplicates("azxxzy"));
        assertEquals("", solution.removeDuplicates("aabbccddeeff"));
    }

    @Test
    void testEdgeCases() {
        assertEquals("", solution.removeDuplicates(""));
        assertEquals("a", solution.removeDuplicates("a"));
        assertEquals("", solution.removeDuplicates("aa"));
        assertEquals("d", solution.removeDuplicates("aabccbd"));
        assertEquals("abcdef", solution.removeDuplicates("abcdef"));
    }

    @Test
    void testLongInput() {
        String input = generateAlternating("ab", 50000);
        assertEquals(input, solution.removeDuplicates(input));

        String dupInput = repeat("aa", 50000); // 全部抵銷
        assertEquals("", solution.removeDuplicates(dupInput));
    }

    private String generateAlternating(String base, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(base);
        }
        return sb.toString();
    }

    private String repeat(String unit, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(unit);
        }
        return sb.toString();
    }

}
