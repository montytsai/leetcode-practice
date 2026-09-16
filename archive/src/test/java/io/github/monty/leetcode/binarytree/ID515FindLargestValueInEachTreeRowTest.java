package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ID515FindLargestValueInEachTreeRowTest {

    private final ID515FindLargestValueInEachTreeRow solution = new ID515FindLargestValueInEachTreeRow();

    @Test
    void testNullTree() {
        TreeNode root = null;
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, solution.largestValues(root), "空樹應回傳空列表");
    }

    @Test
    void testSingleNode() {
        TreeNode root = new TreeNode(5);
        List<Integer> expected = Collections.singletonList(5);
        assertEquals(expected, solution.largestValues(root), "單節點樹應回傳該節點值");
    }

    @Test
    void testCompleteBinaryTree() {
        /*
                1
               / \
              3   2
             / \   \
            5   3   9
         */
        TreeNode root = new TreeNode(1,
                new TreeNode(3, new TreeNode(5), new TreeNode(3)),
                new TreeNode(2, null, new TreeNode(9))
        );

        List<Integer> expected = Arrays.asList(1, 3, 9);
        assertEquals(expected, solution.largestValues(root), "每層應回傳最大值");
    }

    @Test
    void testUnbalancedTreeLeftHeavy() {
        /*
            4
           /
          7
         /
        1
         */
        TreeNode root = new TreeNode(4,
                new TreeNode(7,
                        new TreeNode(1),
                        null),
                null
        );

        List<Integer> expected = Arrays.asList(4, 7, 1);
        assertEquals(expected, solution.largestValues(root));
    }

    @Test
    void testTreeWithNegativeValues() {
        /*
                -1
               /  \
             -2   -3
             /
           -5
         */
        TreeNode root = new TreeNode(-1,
                new TreeNode(-2, new TreeNode(-5), null),
                new TreeNode(-3)
        );

        List<Integer> expected = Arrays.asList(-1, -2, -5);
        assertEquals(expected, solution.largestValues(root));
    }

}