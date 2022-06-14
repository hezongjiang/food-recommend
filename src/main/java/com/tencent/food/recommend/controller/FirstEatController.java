package com.tencent.food.recommend.controller;


import com.tencent.food.recommend.common.ResultData;
import com.tencent.food.recommend.common.consts.WXConstant;
import com.tencent.food.recommend.common.enums.ReturnCode;
import com.tencent.food.recommend.persist.model.Food;
import com.tencent.food.recommend.persist.model.Person;
import com.tencent.food.recommend.response.FoodResponse;
import com.tencent.food.recommend.service.FirstEatService;
import com.tencent.food.recommend.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodStructure")
public class FirstEatController {

    @Autowired
    private FirstEatService firstEatService;

    @Autowired
    private PersonService personService;


    @DeleteMapping("/delete")
    public ResultData deleteFood(
            @RequestHeader(name = WXConstant.OPEN_ID) String openId,
            @RequestParam(value = "foodId") String foodId
    ){
//        先验证身份,获取person,必须有id;food必须有id
        Person person= personService.findPersonByOpenId(openId);
        if (person == null) {
            return ResultData.error(ReturnCode.USER_NOT_EXISTS);
        }
        firstEatService.deleteByFoodId(openId, foodId);
        return  ResultData.ok("删除成功");

    }

    @GetMapping("/allFood")
    public ResultData checkFood(
            @RequestHeader(name = WXConstant.OPEN_ID) String openId,
            @RequestParam(value = "foodId", required = false) String foodId,
            @RequestParam(value = "foodName", required = false) String foodName,
            @RequestParam(value = "createDate", required = false) String requestCreateDate,
            @RequestParam(value = "remindDate", required = false) String requestRemindDate,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "pageSize") Integer pageSize
    ){

        Food food = new Food();
        //先验证身份,获取User
        Person person= personService.findPersonByOpenId(openId);

        if (person == null) {
            return ResultData.error(ReturnCode.USER_NOT_EXISTS);
        }
        if (foodId != null) {
            food.setFoodId(foodId);
        }
        if (foodName != null) {
            food.setFoodName(foodName);
        }
        if (requestCreateDate != null)  {
            Long createDate=Long.parseLong(requestCreateDate);
            food.setCreateDate(createDate);
        }
        if (requestRemindDate!=null) {
            Long remindDate = Long.parseLong(requestRemindDate);
            food.setRemindDate(remindDate);
        }
        FoodResponse foodResponse = firstEatService.selectByPersonId(openId,food,page,pageSize);
        if (foodResponse != null) {
            return ResultData.success(foodResponse);
        } else {
            return ResultData.fail(ReturnCode.RC999.getCode(),ReturnCode.RC999.getMessage());
        }
    }
}
