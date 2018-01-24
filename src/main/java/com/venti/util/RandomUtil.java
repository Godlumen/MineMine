package com.venti.util;

public class RandomUtil {
    /**
     * 生成n位随机数
     *
     * @param n
     * @return
     */
    public static String getRandomNum(int n) {
        String randomStr = "";
        for (int i = 0; i < n; i++) {
            int randomNum = (int) (Math.random() * 10);
            randomStr += randomNum;
        }
        return randomStr;
    }
}
