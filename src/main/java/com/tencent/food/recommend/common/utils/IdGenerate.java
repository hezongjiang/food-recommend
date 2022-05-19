package com.tencent.food.recommend.common.utils;

import org.springframework.util.StringUtils;

public class IdGenerate {

    /**
     * 简单生成一个ID
     * @param type id的类型
     * @return ID
     */
    public static String generate(String type) {
        if (!StringUtils.hasText(type)) {
            throw new RuntimeException("id error!");
        }
        return type + "FR" + System.currentTimeMillis();
    }
}
