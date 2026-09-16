package io.github.monty.leetcode.stackqueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ID150EvaluateReversePolishNotationTest {

    private ID150EvaluateReversePolishNotation solution;

    @BeforeEach
    public void setUp() {
        solution = new ID150EvaluateReversePolishNotation();
    }


    @Test
    @DisplayName("Basic Case 1: ['2','1','+','3','*'] => ((2 + 1) * 3) = 9")
    void testExample1() {
        String[] tokens = {"2", "1", "+", "3", "*"};
        assertEquals(9, solution.evalRPN(tokens));
    }

    @Test
    @DisplayName("Basic Case 2: ['4','13','5','/','+'] => (4 + (13 / 5)) = 6")
    void testExample2() {
        String[] tokens = {"4", "13", "5", "/", "+"};
        assertEquals(6, solution.evalRPN(tokens));
    }

    @Test
    @DisplayName("Negative Number Case: ['-4','2','/'] => (-4 / 2) = -2")
    void testNegativeDivision() {
        String[] tokens = {"-4", "2", "/"};
        assertEquals(-2, solution.evalRPN(tokens));
    }

    @Test
    @DisplayName("Multiple Operations: ['10','6','9','3','+','-11','*','/','*','17','+','5','+']")
    void testComplexExpression() {
        String[] tokens = {
                "10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"
        };
        assertEquals(22, solution.evalRPN(tokens));
    }

    @Test
    @DisplayName("Single Element Case: ['42'] => 42")
    void testSingleNumber() {
        String[] tokens = {"42"};
        assertEquals(42, solution.evalRPN(tokens));
    }

    @Test
    @DisplayName("Subtraction and Negative Result: ['3','4','-'] => (3 - 4) = -1")
    void testSubtractionNegative() {
        String[] tokens = {"3", "4", "-"};
        assertEquals(-1, solution.evalRPN(tokens));
    }

}