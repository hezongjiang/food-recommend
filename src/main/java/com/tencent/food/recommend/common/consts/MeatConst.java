package com.tencent.food.recommend.common.consts;

import java.util.HashMap;
import java.util.Map;

public class MeatConst {

    public static final String DETAIL = "pork.html";

    public static final String DETAIL2 = "chicken_wing.html";

    public static final Map<String, String> map = new HashMap<>();

    static {
        map.put("红烧肉", DETAIL);
        map.put("可乐鸡翅", DETAIL2);
    }
}
