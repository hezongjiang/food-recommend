package com.tencent.food.recommend.response;

import lombok.Data;

@Data
public class SwapFoodDetailResponse {

    private String swapId;

    private String nickName;

    private String avatarUrl;

    private Long postTime;

    private String extInfo;

    private String fromId;

    private String from;

    private Integer quantityFrom;

    private Integer weightFrom;

    private String toId;

    private String to;

    private Integer quantityTo;

    private Integer weightTo;
}
