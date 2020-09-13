package mj_airbnb;

import java.util.HashMap;
import java.util.Map;

public class Airbnb_1_Math {
    public static void main(String[] args) {
        T1_test();
    }


    /**
     * T1:
     * 题目是给你公式，比如偶数的话除 2，奇数的话就变成 3*n+1，对于任何一个正数，数学猜想是最 终他会变成 1。每变一步步数加 1，
     * 给一个上限，让找出范围内最长步数。
     * 比如 7，变换到 1 是如下顺序:7->22->11->34->17->52->26->13->40->20->10->5->16->8->4->2->1, 总 共需要 17 步。
     *
     * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=273149
     */

    public static void T1_test() {
        int num = 7;
        int res = T1_findLongestSteps(num);
        System.out.println("res = " + res);
    }

    public static int T1_findLongestSteps(int num) {
        if (num < 1) return 0;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= num; i++) {
            int s = T1_findSteps(i, map);
            if (!map.containsKey(i)) {
                map.put(i, s);
            } else {
                int max = Math.max(map.get(i), s);
                map.put(i, max);
            }
        }
        return map.get(num);
    }


    public static int T1_findSteps(int num, Map<Integer, Integer> map) {
        if (num <= 1) {
            return 1;
        }
        if (map.containsKey(num)) {
            return map.get(num);
        }

        if (num % 2 == 0) {
            return T1_findSteps(num / 2, map) + 1;
        } else {
            return T1_findSteps(num * 3 + 1, map) + 1;
        }
    }
}
