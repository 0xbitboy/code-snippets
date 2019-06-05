package com.github.liaojiacan.coding.收益分摊算法;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liaojiacan
 * @date 2019/6/5
 */
public class Solution {
    /**
     * 假定A和B金融机构联合开展贷款业务，并约定所得利息收益按照2:1分配，总计贷款给了1万个人，
     * 对于每个人利息收益如何分配，才能保证尽可能公平（收益分配单位精确到分）
     *
     * @param amount  需要分摊的金额(单个人)
     * @param rateMap key为机构编码, value为占的比例
     * @return key为机构编码, value为分得的收益
     */
    public static Map<String, Long> allocate(Long amount, Map<String, Long> rateMap) {

        // 根据比例 再 按 2:1 分配收益
        Map<String, Long> ans = new HashMap<>(2);
        rateMap.forEach((dp, rate) -> {
            double balance = rate * amount.doubleValue() / 100;

            switch (dp) {
                case "A":
                    balance = balance * 2 / 3;
                    break;
                case "B":
                    balance = balance / 3;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            // 这个精度的缺失，四舍五入是否合理？
            // 根据1万人的规模，最终的偏差可能比较大？
            // 如何处理？
            long departmentBalance = Math.round(balance);
            ans.put(dp, departmentBalance);
            ans.put(dp, amount - departmentBalance);
        });

        return ans;
    }

}