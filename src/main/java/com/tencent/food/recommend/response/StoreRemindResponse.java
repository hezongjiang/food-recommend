package com.tencent.food.recommend.response;

import lombok.Data;

/**
 * 用于返回前端查询囤货提醒
 */
@Data
public class StoreRemindResponse {
    //剩余天数
    private Long day;
    //备注
    private String remark;
}
