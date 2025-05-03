package io.github.monty.leetcode.stackqueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ID20ValidParenthesesTest {

    private ID20ValidParentheses validator;

    @BeforeEach
    public void setUp() {
        validator = new ID20ValidParentheses();
    }

    @Test
    public void testEmptyString() {
        assertTrue(validator.isValid(""), "空字串應該是有效的");
    }

    @Test
    public void testSingleValidPairs() {
        assertTrue(validator.isValid("()"));
        assertTrue(validator.isValid("[]"));
        assertTrue(validator.isValid("{}"));
    }

    @Test
    public void testSingleInvalidPairs() {
        assertFalse(validator.isValid("(]"));
        assertFalse(validator.isValid("[}"));
        assertFalse(validator.isValid("{)"));
    }

    @Test
    public void testNestedValidPairs() {
        assertTrue(validator.isValid("([])"));
        assertTrue(validator.isValid("{[]}"));
        assertTrue(validator.isValid("({[]})"));
    }

    @Test
    public void testUnmatchedOpenings() {
        assertFalse(validator.isValid("("));
        assertFalse(validator.isValid("["));
        assertFalse(validator.isValid("{"));
    }

    @Test
    public void testUnmatchedClosings() {
        assertFalse(validator.isValid(")"));
        assertFalse(validator.isValid("]"));
        assertFalse(validator.isValid("}"));
    }

    @Test
    public void testWrongOrder() {
        assertFalse(validator.isValid("([)]"));
        assertFalse(validator.isValid("{[}]"));
    }

    @Test
    public void testMultipleValidGroups() {
        assertTrue(validator.isValid("()[]{}"));
        assertTrue(validator.isValid("{[]}()"));
    }

    @Test
    public void testComplexInvalid() {
        assertFalse(validator.isValid("({[})]"));
        assertFalse(validator.isValid("((({{{[[[]]]}}})))("));
    }

    @Test
    public void testLongValidString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("({[]})");
        }
        assertTrue(validator.isValid(sb.toString()));
    }

    @Test
    public void testLongInvalidString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 999; i++) {
            sb.append("({[]})");
        }
        sb.append("("); // 讓它不成對，測試失敗情況
        assertFalse(validator.isValid(sb.toString()));
    }

}
