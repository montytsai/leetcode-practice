package io.github.monty.leetcode.backtracking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ID131PalindromePartitioningTest {

    private ID131PalindromePartitioning solution;

    @BeforeEach
    void setUp() {
        solution = new ID131PalindromePartitioning();
    }

    @Test
    void testExample1() {
        String input = "aab";
        List<List<String>> expected = Arrays.asList(
                Arrays.asList("a", "a", "b"),
                Arrays.asList("aa", "b")
        );
        assertEquals(expected, solution.partition(input));
    }

    @Test
    void testSingleChar() {
        String input = "c";
        List<List<String>> expected = Arrays.asList(
                Collections.singletonList("c")
        );
        assertEquals(expected, solution.partition(input));
    }

    @Test
    void testAllSameChar() {
        String input = "aaa";
        List<List<String>> expected = Arrays.asList(
                Arrays.asList("a", "a", "a"),
                Arrays.asList("a", "aa"),
                Arrays.asList("aa", "a"),
                Collections.singletonList("aaa")
        );
        assertEquals(expected, solution.partition(input));
    }

    @Test
    void testEmptyString() {
        String input = "";
        List<List<String>> expected = Collections.emptyList();
        assertEquals(expected, solution.partition(input));
    }

    @Test
    void testNoPalindromeCut() {
        String input = "abc";
        List<List<String>> expected = Collections.singletonList(
                Arrays.asList("a", "b", "c")
        );
        assertEquals(expected, solution.partition(input));
    }

    @Test
    void testLongString() {
        String input = "aabbaacca";
        List<List<String>> result = solution.partition(input);
        // 檢查所有切片是否為回文
        for (List<String> partition : result) {
            for (String part : partition) {
                assertEquals(new StringBuilder(part).reverse().toString(), part);
            }
        }
        // 粗略檢查可能組合數量範圍（非唯一標準）
        assert (result.size() >= 5);
    }

    @Test
    void testLengthFour() {
        String input = "abba";
        List<List<String>> expected = Arrays.asList(
                Arrays.asList("a", "b", "b", "a"),
                Arrays.asList("a", "bb", "a"),
                Collections.singletonList("abba")
        );
        assertEquals(expected, solution.partition(input));
    }

    // ASCII 樹圖說明（例："aab"）
    //
    // partition("aab")
    // ├── ["a"]
    // │   ├── ["a"]
    // │   │   └── ["b"] → ["a", "a", "b"]
    // │   └── ["ab"] (非回文)
    // └── ["aa"]
    //     └── ["b"] → ["aa", "b"]

}