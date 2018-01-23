package com.venti.util;

import java.util.Random;

/**
 * 主键生成工具包
 **/
public class KeyUtil {
    /**
     * 生成唯一主键
     * 格式：时间+六位随机数
     *
     * @return
     */
    public static synchronized String getUniqueKey() {
        Random random = new Random();
        Integer randomNum = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(randomNum);
    }
}
