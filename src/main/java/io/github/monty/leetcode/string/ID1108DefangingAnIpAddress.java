package io.github.monty.leetcode.string;

/**
 * LeetCode #1108:
 * <a href="https://leetcode.com/problems/defanging-an-ip-address">Defanging an IP Address</a>
 * 給一個有效的 IPv4 地址 address，返回這個 IP 地址的無效化版本。
 * 無效化 IP 地址：用 "[.]" 代替了每個 "."。
 *
 * @author Monty.Tsai
 * @since 2025-04-28 18:00:38
 */
public class ID1108DefangingAnIpAddress {

    /**
     * 解法: StringBuilder
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
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
