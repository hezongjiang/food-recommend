package com.tencent.food.recommend.controller;

import com.tencent.food.recommend.common.ResultData;
import com.tencent.food.recommend.response.SwapFoodDetailResponse;
import com.tencent.food.recommend.response.SwapFoodListResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 附近
 */
@RestController
@RequestMapping("/swap_food")
public class SwapFoodController {

    /**
     * 列表
     */
    @GetMapping("list")
    public ResultData<List<SwapFoodListResponse>> list(@RequestParam(value = "page") Integer page,
                                                       @RequestParam(value = "pageSize") Integer pageSize) {
        return ResultData.success(null);
    }

    /**
     * 创建
     * @param openId openid
     * @param fromFoodId 用xx交换食物，xx为食物id
     * @param toFoodId 交换xx食物，xx为食物id
     * @param quantityFrom 交换数量
     * @param quantityTo 交换数量
     * @param extInfo 备注
     * @return
     */
    @PostMapping("create")
    public ResultData<Boolean> create(@RequestHeader(name = "openid") String openId,
                                      @RequestParam(value = "from") String fromFoodId,
                                      @RequestParam(value = "to") String toFoodId,
                                      @RequestParam(value = "quantity_from") Integer quantityFrom,
                                      @RequestParam(value = "quantity_to") Integer quantityTo,
                                      @RequestParam(value = "ext_info", required = false) String extInfo) {

        return ResultData.success(null);
    }

    /**
     * 详情
     */
    @GetMapping("detail")
    public ResultData<SwapFoodDetailResponse> detail(@RequestParam(value = "swap_id") String swapId) {
        return ResultData.success(null);
    }
}
