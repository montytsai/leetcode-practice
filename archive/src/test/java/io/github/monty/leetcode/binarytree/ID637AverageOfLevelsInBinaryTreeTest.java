package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ID637AverageOfLevelsInBinaryTreeTest {

    private final ID637AverageOfLevelsInBinaryTree solution = new ID637AverageOfLevelsInBinaryTree();

    @Test
    void testExample1() {
        // 測資範例：
        //     3
        //    / \
        //   9  20
        //      / \
        //     15  7
        TreeNode root = TreeNode.of(3, 9, 20, null, null, 15, 7);

        List<Double> expected = Arrays.asList(3.0, 14.5, 11.0);
        List<Double> actual = solution.averageOfLevels(root);

        assertEquals(expected, actual);
    }

    @Test
    void testSingleNode() {
        // 單一節點
        TreeNode root = TreeNode.of(42);

        List<Double> expected = Collections.singletonList(42.0);
        List<Double> actual = solution.averageOfLevels(root);

        assertEquals(expected, actual);
    }

    @Test
    void testLeftSkewedTree() {
        // 左傾樹：
        //   1
        //  /
        // 2
        //  \
        //   3
        TreeNode root = TreeNode.of(1, 2, null, null, 3);

        List<Double> expected = Arrays.asList(1.0, 2.0, 3.0);
        List<Double> actual = solution.averageOfLevels(root);

        assertEquals(expected, actual);
    }

    @Test
    void testRightSkewedTree() {
        // 右傾樹：
        // 1
        //  \
        //   2
        //    \
        //     3
        TreeNode root = TreeNode.of(1, null, 2, null, 3);

        List<Double> expected = Arrays.asList(1.0, 2.0, 3.0);
        List<Double> actual = solution.averageOfLevels(root);

        assertEquals(expected, actual);
    }

    @Test
    void testNullRoot() {
        List<Double> expected = Collections.emptyList();
        List<Double> actual = solution.averageOfLevels(null); // 空樹

        assertEquals(expected, actual);
    }

}