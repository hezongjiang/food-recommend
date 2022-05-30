package com.tencent.food.recommend.common.translate;

import lombok.Data;

import java.util.List;

@Data
public class TranslateResult {

    private String from;

    private String to;

    private List<TransResult> transResult;

    @Data
    public static class TransResult {
        private String src;

        private String dst;
    }
}
