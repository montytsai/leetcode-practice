//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            int count = 0;
            switch (token) {
                case "+":
                    count = stack.pop() + stack.pop();
                    break;
                case "-":
                    count = -stack.pop() + stack.pop();
                    break;
                case "*":
                    count = stack.pop() * stack.pop();
                    break;
                case "/":
                    int two = stack.pop();
                    int one = stack.pop();
                    count = one / two;
                    break;
                default:
                    count = Integer.parseInt(token);
                    break;
            }
            stack.push(count);
        }

        // stack 最後的數字便是運算結果
        return stack.pop();
    }

}
//leetcode submit region end(Prohibit modification and deletion)

