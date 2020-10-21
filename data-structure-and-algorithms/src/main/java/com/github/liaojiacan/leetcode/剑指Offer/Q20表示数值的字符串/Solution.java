package com.github.liaojiacan.leetcode.剑指Offer.Q20表示数值的字符串;

//请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"012
//3"都表示数值，但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
//
//
// Related Topics 数学


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isNumber(String s) {

        //有限状态自动机
        //1.起始状态为空字符串
        //2.符号位            +/-
        //3.整数部分          9
        //4.小数点(左侧无整数) .0999
        //5.小数点(左侧有整数) 9.000
        //6.小数部分          包括 4和5
        //7.字符E/e           .999E / 9.000E
        //8.字符E/e的符号位    .999E- / 9.000E-
        //9.字符E的指数部分    .999E-5 / 9.000E-5
        //10.尾部空格

        Map<State, Map<CharType, State>> transferStateMap = new HashMap<>();

        //1.起始状态为空字符串
        Map<CharType, State> initialMap = new HashMap<>(10);
        initialMap.put(CharType.CHAR_SPACE, State.STATE_INIT);
        initialMap.put(CharType.CHAR_SIGN, State.STATE_INTEGER_SIGN);
        initialMap.put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INTEGER);
        initialMap.put(CharType.CHAR_INTEGER, State.STATE_INTEGER);

        transferStateMap.put(State.STATE_INIT, initialMap);

        //2.符号位            +/-
        Map<CharType, State> integerSignMap = new HashMap<>();
        integerSignMap.put(CharType.CHAR_INTEGER, State.STATE_INTEGER);
        integerSignMap.put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INTEGER);
        transferStateMap.put(State.STATE_INTEGER_SIGN, integerSignMap);

        //3.整数部分          9
        Map<CharType, State> integerMap = new HashMap<>();
        integerMap.put(CharType.CHAR_INTEGER, State.STATE_INTEGER);
        integerMap.put(CharType.CHAR_POINT, State.STATE_POINT);
        integerMap.put(CharType.CHAR_EXP, State.STATE_EXP);
        integerMap.put(CharType.CHAR_SPACE, State.STATE_END);
        transferStateMap.put(State.STATE_INTEGER, integerMap);

        //4.小数点(左侧无整数) .0999
        Map<CharType, State> pointWithoutIntMap = new HashMap<>();
        pointWithoutIntMap.put(CharType.CHAR_INTEGER, State.STATE_FRACTION);
        transferStateMap.put(State.STATE_POINT_WITHOUT_INTEGER, pointWithoutIntMap);

        //5.小数点(左侧有整数) 9.000
        Map<CharType, State> pointMap = new HashMap<>();
        pointMap.put(CharType.CHAR_INTEGER, State.STATE_FRACTION);
        pointMap.put(CharType.CHAR_EXP, State.STATE_EXP);
        pointMap.put(CharType.CHAR_SPACE, State.STATE_END);
        transferStateMap.put(State.STATE_POINT, pointMap);

        //6.小数部分          包括 4和5
        Map<CharType, State> fractionMap = new HashMap<>();
        fractionMap.put(CharType.CHAR_INTEGER, State.STATE_FRACTION);
        fractionMap.put(CharType.CHAR_EXP, State.STATE_EXP);
        fractionMap.put(CharType.CHAR_SPACE, State.STATE_END);
        transferStateMap.put(State.STATE_FRACTION, fractionMap);

        //7.字符E/e           .999E / 9.000E
        Map<CharType, State> expMap = new HashMap<>();
        expMap.put(CharType.CHAR_SIGN, State.STATE_EXP_SIGN);
        expMap.put(CharType.CHAR_INTEGER, State.STATE_EXP_INTEGER);
        transferStateMap.put(State.STATE_EXP, expMap);

        //8.字符E/e的符号位    .999E- / 9.000E-
        Map<CharType, State> expSignMap = new HashMap<>();
        expSignMap.put(CharType.CHAR_INTEGER, State.STATE_EXP_INTEGER);
        transferStateMap.put(State.STATE_EXP_SIGN, expSignMap);

        //9.字符E的指数部分    .999E-5 / 9.000E-5
        Map<CharType, State> expIntegerMap = new HashMap<>();
        expIntegerMap.put(CharType.CHAR_INTEGER, State.STATE_EXP_INTEGER);
        expIntegerMap.put(CharType.CHAR_SPACE, State.STATE_END);
        transferStateMap.put(State.STATE_EXP_INTEGER, expIntegerMap);

        //10.尾部空格
        Map<CharType, State> endMap = new HashMap<>();
        endMap.put(CharType.CHAR_SPACE, State.STATE_END);
        transferStateMap.put(State.STATE_END, endMap);

        int length = s.length();
        State state = State.STATE_INIT;

        for (int i = 0; i < length; i++) {
            CharType type = CharType.convert(s.charAt(i));
            if (!transferStateMap.get(state).containsKey(type)) {
                return false;
            } else {
                state = transferStateMap.get(state).get(type);
            }
        }

        return state == State.STATE_END || state == State.STATE_INTEGER || state == State.STATE_FRACTION || state == State.STATE_POINT  || state == State.STATE_EXP_INTEGER;

    }

    public enum State {
        STATE_INIT,
        STATE_INTEGER_SIGN,
        STATE_INTEGER,
        STATE_POINT,
        STATE_POINT_WITHOUT_INTEGER,
        STATE_FRACTION,
        STATE_EXP,
        STATE_EXP_SIGN,
        STATE_EXP_INTEGER,
        STATE_END
    }

    public enum CharType {
        // 0-9
        CHAR_INTEGER,
        // +-
        CHAR_SIGN,
        // E e
        CHAR_EXP,
        // .
        CHAR_POINT,
        //
        CHAR_SPACE,
        CHAR_ILLEGAL;

        public static CharType convert(char c) {
            if (c >= '0' && c <= '9') {
                return CharType.CHAR_INTEGER;
            }
            if (c == '+' || c == '-') {
                return CharType.CHAR_SIGN;
            }
            if (c == 'e' || c == 'E') {
                return CharType.CHAR_EXP;
            }
            if (c == '.') {
                return CharType.CHAR_POINT;
            }
            if (c == ' ') {
                return CharType.CHAR_SPACE;
            }
            return CharType.CHAR_ILLEGAL;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isNumber("-1.e49046 "));
    }

}
//leetcode submit region end(Prohibit modification and deletion)
