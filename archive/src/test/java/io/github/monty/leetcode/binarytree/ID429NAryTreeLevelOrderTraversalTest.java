package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ID429NAryTreeLevelOrderTraversalTest {

    private final ID429NAryTreeLevelOrderTraversal solution = new ID429NAryTreeLevelOrderTraversal();

    @Test
    public void testLevelOrder() {
        // 建立 N-ary 樹的測試數據
        ID429NAryTreeLevelOrderTraversal.Node root = new ID429NAryTreeLevelOrderTraversal.Node(1);
        ID429NAryTreeLevelOrderTraversal.Node node3 = new ID429NAryTreeLevelOrderTraversal.Node(3);
        ID429NAryTreeLevelOrderTraversal.Node node2 = new ID429NAryTreeLevelOrderTraversal.Node(2);
        ID429NAryTreeLevelOrderTraversal.Node node4 = new ID429NAryTreeLevelOrderTraversal.Node(4);
        ID429NAryTreeLevelOrderTraversal.Node node5 = new ID429NAryTreeLevelOrderTraversal.Node(5);
        ID429NAryTreeLevelOrderTraversal.Node node6 = new ID429NAryTreeLevelOrderTraversal.Node(6);

        // 配置 N-ary 樹結構
        root.children = Arrays.asList(node3, node2, node4);
        node3.children = Arrays.asList(node5, node6);

        // 呼叫解法
        ID429NAryTreeLevelOrderTraversal solution = new ID429NAryTreeLevelOrderTraversal();
        List<List<Integer>> result = solution.levelOrder(root);

        // 預期結果: 層序遍歷每層的結果
        List<List<Integer>> expected = Arrays.asList(
                Collections.singletonList(1),
                Arrays.asList(3, 2, 4),
                Arrays.asList(5, 6)
        );

        // 驗證結果
        assertEquals(expected, result);
    }

    @Test
    public void testLevelOrderEmptyTree() {
        // 測試空樹
        List<List<Integer>> result = solution.levelOrder(null);

        // 預期結果為空列表
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testLevelOrderSingleID429NAryTreeLevelOrderTraversal() {
        // 測試只有一個節點的樹
        ID429NAryTreeLevelOrderTraversal.Node root = new ID429NAryTreeLevelOrderTraversal.Node(1);

        List<List<Integer>> result = solution.levelOrder(root);

        // 預期結果: 只有一層，包含單個節點
        List<List<Integer>> expected = Collections.singletonList(
                Collections.singletonList(1)
        );

        assertEquals(expected, result);
    }

    @Test
    public void testLevelOrderMultipleLevels() {
        // 測試多層的 N-ary 樹
        ID429NAryTreeLevelOrderTraversal.Node root = new ID429NAryTreeLevelOrderTraversal.Node(1);
        ID429NAryTreeLevelOrderTraversal.Node node3 = new ID429NAryTreeLevelOrderTraversal.Node(3);
        ID429NAryTreeLevelOrderTraversal.Node node2 = new ID429NAryTreeLevelOrderTraversal.Node(2);
        ID429NAryTreeLevelOrderTraversal.Node node4 = new ID429NAryTreeLevelOrderTraversal.Node(4);
        ID429NAryTreeLevelOrderTraversal.Node node5 = new ID429NAryTreeLevelOrderTraversal.Node(5);
        ID429NAryTreeLevelOrderTraversal.Node node6 = new ID429NAryTreeLevelOrderTraversal.Node(6);
        ID429NAryTreeLevelOrderTraversal.Node node7 = new ID429NAryTreeLevelOrderTraversal.Node(7);

        root.children = Arrays.asList(node3, node2, node4);
        node3.children = Arrays.asList(node5, node6);
        node4.children = Collections.singletonList(node7);

        List<List<Integer>> result = solution.levelOrder(root);

        // 預期結果：根據層序遍歷，應該依次為 [1], [3, 2, 4], [5, 6, 7]
        List<List<Integer>> expected = Arrays.asList(
                Collections.singletonList(1),
                Arrays.asList(3, 2, 4),
                Arrays.asList(5, 6, 7)
        );

        assertEquals(expected, result);
    }

}
