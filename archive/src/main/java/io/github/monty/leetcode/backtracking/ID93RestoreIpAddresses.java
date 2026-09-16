package io.github.monty.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode #93:
 * <a href="https://leetcode.com/problems/restore-ip-addresses">
 * Restore IP Addresses
 * </a>
 * 給定一個只包含數字的字串，請將其復原為所有可能的合法 IPv4 地址。IPv4 地址由四個小於等於 255 的整數組成，以點分隔，每個區段不能有前導零（除了 "0" 本身）。
 *
 * @author Monty.Tsai
 * @since 2025-06-09 13:00:40
 */
public class ID93RestoreIpAddresses {

    /**
     * 解法: 回溯
     * 思路：
     * - 採回溯法將字串切成四段，每段為合法的 IP 區段（0~255），且不能有前導零。
     * - 使用 StringBuilder 組裝每段結果，切四次後如果剛好用完所有字元則加入結果。
     * - 剪枝：剩餘長度不可能組出合法 IP 時提前 return。
     * 複雜度：
     * - 時間複雜度：O(1)，最多只會有 3^4 = 81 種組合
     * - 空間複雜度：O(n)，StringBuilder + 最終答案 List<String>：O(n)（每段最長 n）
     *      - 回溯深度 stack 固定為 4（O(4) = O(1)）
     *      - 但每個段落最多切 3 個字元，總共處理最多 n 個字元來拼接結果，所以 path 長度與輸入長度有關。
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backtracking(s, 0, 1, new StringBuilder(), result);
        return result;
    }

    /**
     * 回溯函數
     *
     * @param s     字串
     * @param start 這層要切割的字串起始 index
     * @param level 這層的層數 (1-4層)
     * @param path  已切割好的合法 IP
     * @param res   所有合法 IP 結果集
     */
    private void backtracking(String s, int start, int level,
                              StringBuilder path, List<String> res) {
        if (level == 5 && start == s.length()) {
            res.add(path.substring(0, path.length() - 1)); // 去掉最後一個 "."
            return;
        }
        if (level >= 5 || start >= s.length()) return;

        for (int end = start; end < s.length(); end++) {
            String segment = s.substring(start, end + 1);

            // 違法: 如果數字非 0 個數，但起始是 0
            if (segment.length() > 1 && segment.startsWith("0")) break;

            // 違法: 如果數字不是介於 0 - 255
            int segmentVal = Integer.parseInt(segment);
            if (segmentVal < 0 || segmentVal > 255) break;

            // 剪枝: 剩餘長度不合（如果切割了這層，剩餘字串長度不夠湊成合法 IP）
            int remaining = s.length() - end - 1;
            int remainingSlots = 4 - level;
            if (remaining < remainingSlots || remaining > remainingSlots * 3) continue;

            int beforeAppendLen = path.length();
            path.append(segment).append(".");
            backtracking(s, end + 1, level + 1, path, res);
            path.setLength(beforeAppendLen);
        }
    }

}