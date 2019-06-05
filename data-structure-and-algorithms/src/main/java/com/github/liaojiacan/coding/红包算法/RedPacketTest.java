package com.github.liaojiacan.coding.红包算法;

import com.sun.tools.javac.util.Assert;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author liaojiacan
 * @date 2019/6/5
 */
public class RedPacketTest {
    /**
     * 红包算法，输入红包金额和领取人数，返回每个人领取的红包金额,金额单位为分
     *
     * @param amount
     * @param number
     * @return
     */


    public Long[] allocate(Long amount, int number) {

        Assert.check(amount > 0, "amount must greater than 0");
        Assert.check(number > 0, "number must greater than 0");
        Assert.check(amount >= number, "amount must greater than or equals number");
        Long[] ans = new Long[number];
        for (int i = 0; i < number; i++) {
            long per = 1L + ThreadLocalRandom.current().nextLong(0, amount - (number - i));
            // 为了更加均衡，再random一次
            per = ThreadLocalRandom.current().nextLong(1, per + 1);
            ans[i] = per;
            amount -= per;
        }
        if (amount > 0) {
            ans[number - 1] += amount;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new RedPacketTest().allocate(1000L, 10)));
    }

}
