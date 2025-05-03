//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String removeDuplicates(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c: s.toCharArray()) {
            if (stack.isEmpty() || stack.peek() != c) {
                stack.push(c);
            } else {
                stack.pop();
            }
        }

        // Stack 底為字串開頭，因此需反轉
        StringBuilder sb = new StringBuilder(stack.size());
        while(!stack.isEmpty()) {
            sb.append(stack.removeLast()); // Deque 為雙向佇列, removeLast() 運算為 O(1)
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)