package com.tencent.food.recommend.response;

import lombok.Data;

@Data
public class MomentDetailResponse {

    private String momentId;

    private String title;

    private String content;

    private String nickName;

    private Integer type;

    private Long postTime;

    private String pictures;

    private String location;
}
