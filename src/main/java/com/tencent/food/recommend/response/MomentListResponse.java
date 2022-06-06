package com.tencent.food.recommend.response;

import lombok.Data;

import java.util.List;

@Data
public class MomentListResponse {

    private Integer page;
    private Integer pageSize;
    private Integer total;
    private Integer pages;
    private List<MomentDetailResponse> moments;
}
