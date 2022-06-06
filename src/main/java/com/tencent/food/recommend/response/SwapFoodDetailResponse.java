package com.tencent.food.recommend.response;

import lombok.Data;

@Data
public class SwapFoodDetailResponse {

    private String swapId;

    private String nickName;

    private String avatarUrl;

    private Long postTime;

    private String extInfo;
}
