package com.tencent.food.recommend.controller;

import com.tencent.food.recommend.common.ResultData;
import com.tencent.food.recommend.common.enums.ReturnCode;
import com.tencent.food.recommend.common.utils.IdGenerate;
import com.tencent.food.recommend.persist.model.Person;
import com.tencent.food.recommend.persist.model.PersonSwapFood;
import com.tencent.food.recommend.persist.model.SwapFood;
import com.tencent.food.recommend.response.FoodResponse;
import com.tencent.food.recommend.response.SwapFoodDetailResponse;
import com.tencent.food.recommend.response.SwapFoodListResponse;
import com.tencent.food.recommend.service.SwapFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 附近
 */
@RestController
@RequestMapping("/swap_food")
public class SwapFoodController {
    @Autowired
    private SwapFoodService swapFoodService;
    /**
     * 列表
     */
    @GetMapping("list")
    public ResultData<SwapFoodListResponse> list(
            @RequestHeader(name = "openid") String openId,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "pageSize") Integer pageSize) {
        //        先验证身份
        Person person= swapFoodService.Authorize(openId);
        if (person!=null) {
            SwapFoodListResponse swapFoodListResponse=new SwapFoodListResponse();
            swapFoodListResponse.setPage(page);
            swapFoodListResponse.setPageSize(pageSize);
//            查找所以动态按创建时间倒序返回
            swapFoodListResponse=swapFoodService.selectAll(swapFoodListResponse);
            if (swapFoodListResponse != null) {
                return ResultData.success(swapFoodListResponse);
            } else {
                return ResultData.fail(ReturnCode.RC999.getCode(), "查找失败");
            }
        }else {
            return ResultData.fail(ReturnCode.RC401.getCode(), "请登录重试");
        }
    }
    /**
     * 创建
     * @param openId openid
     * @param fromId 用xx交换食物，xx为食物id
     * @param to 交换xx食物，xx为食物id
     * @param quantityFrom 交换数量
     * @param quantityTo 交换数量
     * @param extInfo 备注
     * @return
     */
    @PostMapping("create")
    public ResultData<Boolean> create(@RequestHeader(name = "openid") String openId,
                                      @RequestParam(value = "fromId") String fromId,
                                      @RequestParam(value = "to") String to,
                                      @RequestParam(value = "quantity_from",required = false) Integer quantityFrom,
                                      @RequestParam(value = "weight_from",required = false) Integer weightFrom,
                                      @RequestParam(value = "quantity_to",required = false) Integer quantityTo,
                                      @RequestParam(value = "weight_to",required = false) Integer weightTo,
                                      @RequestParam(value = "ext_info", required = false) String extInfo) {

        Person person= swapFoodService.Authorize(openId);
        if (person!=null) {
            SwapFood swapFood=new SwapFood();
            if((weightFrom==null && quantityFrom==null)||(weightTo==null && quantityTo==null)){
//                参数不够
                return ResultData.fail(ReturnCode.RC999.getCode(), "请求参数非法");
            }
//            配置swapfood
            swapFood.setSwapId(IdGenerate.generate("SWAP_ID"));
            swapFood.setExtInfo(extInfo);
            swapFood.setFromId(fromId);
            swapFood.setQuantityFrom(quantityFrom);
            swapFood.setWeightFrom(weightFrom);
            swapFood.setTo(to);
            swapFood.setQuantityTo(quantityTo);
            swapFood.setWeightTo(weightTo);

            Boolean status = swapFoodService.create(swapFood,openId);
            if (status){
                return ResultData.success(true);
            }
        }
        return ResultData.fail(ReturnCode.RC401.getCode(), "请登录重试");

    }

    /**
     * 详情
     */
    @GetMapping("detail")
    public ResultData<SwapFoodDetailResponse> detail(
            @RequestHeader(name = "openid") String openId,
            @RequestParam(value = "swapId") String swapId) {
        Person person= swapFoodService.Authorize(openId);
        if (person!=null) {
            SwapFoodDetailResponse swapFoodDetailResponse=new SwapFoodDetailResponse();
            swapFoodDetailResponse.setSwapId(swapId);
            swapFoodDetailResponse=swapFoodService.detail(swapFoodDetailResponse);
            if (swapFoodDetailResponse != null) {
                return ResultData.success(swapFoodDetailResponse);
            } else {
                return ResultData.fail(ReturnCode.RC999.getCode(), "非法参数，操作失败");
            }
        }else {
            return ResultData.fail(ReturnCode.RC401.getCode(), "请登录重试");
        }
    }
}
