package com.tencent.food.recommend.controller;


import com.tencent.food.recommend.common.ResultData;
import com.tencent.food.recommend.common.consts.WXConstant;
import com.tencent.food.recommend.common.enums.ReturnCode;
import com.tencent.food.recommend.persist.model.Food;
import com.tencent.food.recommend.persist.model.Person;
import com.tencent.food.recommend.response.FoodResponse;
import com.tencent.food.recommend.service.FirstEatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodStructure")
public class FirstEatController {

    @Autowired
    private FirstEatService firstEatService;


    @DeleteMapping("/delete")
    public ResultData<FoodResponse> deleteFood(
            @RequestHeader(name = WXConstant.OPEN_ID, required = true) String openId,
            @RequestParam(value = "foodId",required = true) String foodId
    ){
//        先验证身份,获取person,必须有id;food必须有id
        Person person= firstEatService.Authorize(openId);
        if (person!=null){
            //        再调用service处理
            int status = firstEatService.deleteByFoodId(person.getOpenId(),foodId);
//        鉴定处理结果并返回
//        出错处理比较简单，需要修改
            if(status==1){
                return ResultData.success(null);
            }else {
                return ResultData.fail(ReturnCode.RC999.getCode(),"删除失败");
            }
        }else {
            return ResultData.fail(ReturnCode.RC401.getCode(), "请登录重试");
        }

    }

    @GetMapping("/allFood")
    public ResultData<FoodResponse> checkFood(
            @RequestHeader(name = WXConstant.OPEN_ID, required = true) String openId,
            @RequestParam(value = "foodId", required = false) String foodId,
            @RequestParam(value = "foodName", required = false) String foodName,
            @RequestParam(value = "createDate", required = false) String create_Date,
            @RequestParam(value = "remindDate", required = false) String remind_Date,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "pageSize") Integer pageSize
    ){
        //        先验证身份,获取User
        Person person= firstEatService.Authorize(openId);
        if (person!=null) {
            //        再调用service处理
            FoodResponse foodResponse = new FoodResponse();
            foodResponse.setPage(page);
            foodResponse.setPageSize(pageSize);
            Food food = new Food();
            //        需调用函数实现有意义的foodId
            food.setFoodId(foodId);
            food.setFoodName(foodName);
            if (create_Date!=null){
                Long createDate=Long.parseLong(create_Date);
                food.setCreateDate(createDate);
            }
            if (remind_Date!=null){
                Long remindDate=Long.parseLong(remind_Date);
                food.setRemindDate(remindDate);
            }
            foodResponse = firstEatService.selectByPersonId(openId, food, page, pageSize, foodResponse);
            //        鉴定处理结果并返回
            if (foodResponse != null) {
                return ResultData.success(foodResponse);
            } else {
                return ResultData.fail(ReturnCode.RC999.getCode(), "查找失败");
            }
        }else {
            return ResultData.fail(ReturnCode.RC401.getCode(), "请登录重试");
        }
    }
}
