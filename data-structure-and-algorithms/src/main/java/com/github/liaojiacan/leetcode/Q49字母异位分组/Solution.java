package com.github.liaojiacan.leetcode.Q49字母异位分组;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author liaojiacan
 * @date 2020/12/14
 * @desc
 */
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(str -> {
                    char[] chars = str.toCharArray();
                    Arrays.sort(chars);
                    return Arrays.toString(chars);
                }
        )).values());
    }
}
