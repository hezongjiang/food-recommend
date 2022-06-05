package com.tencent.food.recommend.controller;

import com.tencent.food.recommend.common.ResultData;
import com.tencent.food.recommend.response.MomentDetailResponse;
import com.tencent.food.recommend.response.MomentListResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 圈子
 */
@RestController
@RequestMapping("/moment")
public class MomentController {

    /**
     * 发布圈子
     * @param openId openid
     * @param title 标题
     * @param content 文本内容
     * @param type 分区类型：1-囤菜攻略、2-食材处理、3-美食分享
     * @param location 地点
     * @param pictures 图片列表，json格式，例如：["xxx", "yyy"]
     * @return 创建成功与否
     */
    @PostMapping("create")
    public ResultData<Boolean> create(@RequestHeader(name = "openid") String openId,
                                      @RequestParam(value = "title") String title,
                                      @RequestParam(value = "content") String content,
                                      @RequestParam(value = "type") Integer type,
                                      @RequestParam(value = "location", required = false) String location,
                                      @RequestParam(value = "pictures", required = false) String pictures) {

        return ResultData.success(null);
    }

    @GetMapping("list")
    public ResultData<List<MomentListResponse>> list(@RequestParam(value = "page") Integer page,
                                                     @RequestParam(value = "pageSize") Integer pageSize) {
        return ResultData.success(null);
    }

    @GetMapping("detail")
    public ResultData<MomentDetailResponse> detail(@RequestParam(value = "momentId") String momentId) {
        return ResultData.success(null);
    }
}
