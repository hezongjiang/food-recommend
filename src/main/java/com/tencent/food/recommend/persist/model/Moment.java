package com.tencent.food.recommend.persist.model;

import lombok.Data;

@Data
public class Moment {
    private Integer id;

    private String momentId;

    private String title;

    private String content;

    private String pictures;

    private String openId;

    private Long postTime;

    private Integer type;

    private String location;


}
