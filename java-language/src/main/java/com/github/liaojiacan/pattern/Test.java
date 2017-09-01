package com.github.liaojiacan.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liaojiacan on 2017/8/25.
 */
public class Test {
    private final static Pattern PATTERN_AB_STRATEGY_VALUE_CHECK = Pattern.compile("^([1-9]{1,1}_{0,1}){1,6}$");

    public static void main(String[] args) {
        Matcher m = PATTERN_AB_STRATEGY_VALUE_CHECK.matcher("1_2_3_4_2_1");

        System.out.println(m.find());
    }
}
