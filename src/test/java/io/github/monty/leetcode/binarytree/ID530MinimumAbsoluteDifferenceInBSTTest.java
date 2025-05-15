package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ID530MinimumAbsoluteDifferenceInBSTTest {

    private ID530MinimumAbsoluteDifferenceInBST solution;

    @BeforeEach
    public void setUp() {
        solution = new ID530MinimumAbsoluteDifferenceInBST();
    }

    @Test
    public void testExampleCase1() {
        /*
            範例樹：
                  4
                 / \
                2   6
               /
              1

            - 排序後節點值：[1, 2, 4, 6]
            - 相鄰差值：[1, 2, 2]
            - 最小絕對差值 = 1（由 1 和 2 得到）
         */
        TreeNode root = TreeNode.of(4, 2, 6, 1);
        int result = solution.getMinimumDifference(root);
        assertEquals(1, result);
    }

    @Test
    public void testAllLeftSubtree() {
        /*
            全部左子樹：
                5
               /
              3
             /
            1

            - 排序後節點值：[1, 3, 5]
            - 差值：[2, 2]
            - 最小絕對差值 = 2
         */
        TreeNode root = TreeNode.of(5, 3, null, 1);
        int result = solution.getMinimumDifference(root);
        assertEquals(2, result);
    }

    @Test
    public void testAllRightSubtree() {
        /*
            全部右子樹：
            1
             \
              3
               \
                6

            - 排序後節點值：[1, 3, 6]
            - 差值：[2, 3]
            - 最小絕對差值 = 2
         */
        TreeNode root = TreeNode.of(1, null, 3, null, 6);
        int result = solution.getMinimumDifference(root);
        assertEquals(2, result);
    }

    @Test
    public void testOnlyTwoNodes() {
        /*
            僅兩個節點：
              2
             /
            1

            - 排序後節點值：[1, 2]
            - 差值 = 1
         */
        TreeNode root = TreeNode.of(2, 1);
        int result = solution.getMinimumDifference(root);
        assertEquals(1, result);
    }

    @Test
    public void testCloseValues() {
        /*
            節點數值差距非常小：
              100
              /  \
           99    101

            - 排序後節點值：[99, 100, 101]
            - 差值：[1, 1]
            - 最小絕對差值 = 1
         */
        TreeNode root = TreeNode.of(100, 99, 101);
        int result = solution.getMinimumDifference(root);
        assertEquals(1, result);
    }

    @Test
    public void testUnbalancedTree() {
        /*
            非平衡樹（左深）：
                10
               /
              5
             /
            2
             \
              3

            - 排序後節點值：[2, 3, 5, 10]
            - 差值：[1, 2, 5]
            - 最小絕對差值 = 1（由 2 和 3 得到）
         */
//        TreeNode root = TreeNode.of(10, 5, null, 2, null, null, null, null, 3);

        TreeNode root = new TreeNode(10);         // 層級 0
        root.left = new TreeNode(5);              // 層級 1 - 左
        root.left.left = new TreeNode(2);         // 層級 2 - 左的左
        root.left.left.right = new TreeNode(3);   // 層級 3 - 左的左的右

        int result = solution.getMinimumDifference(root);
        assertEquals(1, result);
    }

}