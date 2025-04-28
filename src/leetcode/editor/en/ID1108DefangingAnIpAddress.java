//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String defangIPaddr(String address) {
        // 1. 定義需替代的符號
        final char POINT = '.';

        // 2. replace
        StringBuilder sb = new StringBuilder();
        char[] arr = address.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (POINT == arr[i]) {
                sb.append("[.]");
            } else {
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

