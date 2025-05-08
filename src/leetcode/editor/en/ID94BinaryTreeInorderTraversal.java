//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    /**
     * 使用「迭代」實現中序遍歷
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        // stack 用來存放「訪問『過』的元素」
        Deque<TreeNode> stack = new ArrayDeque<>();
        // curr 是指針，用來訪問目前的元素
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            // 若 curr 不為空，一路向左
            if (curr != null) {
                stack.push(curr); // 將訪問過的節點 暫存在 stack 中
                curr = curr.left; // 左: 繼續深度向左
            } else { // curr 為空，沒左邊了，處理自己和右邊
                curr = stack.pop();
                result.add(curr.val); // 中
                curr = curr.right; // 右
            }
        }
        return result;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

