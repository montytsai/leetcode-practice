package io.github.monty.leetcode.backtracking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ID93RestoreIPAddressesTest {

    private ID93RestoreIpAddresses solution;

    @BeforeEach
    void setUp() {
        solution = new ID93RestoreIpAddresses();
    }

    @Test
    void testRestoreIpAddresses_basic() {
        String s = "25525511135";
        List<String> expected = Arrays.asList("255.255.11.135", "255.255.111.35");
        List<String> result = solution.restoreIpAddresses(s);
        assertTrue(result.containsAll(expected));
        assertEquals(expected.size(), result.size());
    }

    @Test
    void testRestoreIpAddresses_zero() {
        String s = "0000";
        List<String> expected = Collections.singletonList("0.0.0.0");
        assertEquals(expected, solution.restoreIpAddresses(s));
    }

    @Test
    void testRestoreIpAddresses_invalid() {
        String s = "1111";
        List<String> expected = Collections.singletonList("1.1.1.1");
        assertEquals(expected, solution.restoreIpAddresses(s));
    }

    @Test
    void testRestoreIpAddresses_tooShort() {
        String s = "123";
        List<String> expected = Collections.emptyList();
        assertEquals(expected, solution.restoreIpAddresses(s));
    }

    /**
     * 	测试用例:"101023"
     * 	测试结果:["1.0.1.023","1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
     * 	期望结果:["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
     */
    @Test
    void testLCExample() {
        String s = "101023";
        List<String> expected = Arrays.asList("1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3");
        List<String> result = solution.restoreIpAddresses(s);
        assertTrue(result.containsAll(expected));
        assertEquals(expected.size(), result.size());
    }

}