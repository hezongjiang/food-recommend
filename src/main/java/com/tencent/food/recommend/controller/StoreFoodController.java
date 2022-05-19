package com.tencent.food.recommend.controller;

import com.tencent.food.recommend.common.ResultData;
import com.tencent.food.recommend.common.consts.WXConstant;
import com.tencent.food.recommend.common.enums.ReturnCode;
import com.tencent.food.recommend.common.utils.IdGenerate;
import com.tencent.food.recommend.persist.model.Food;
import com.tencent.food.recommend.persist.model.Person;
import com.tencent.food.recommend.response.FoodResponse;
import com.tencent.food.recommend.service.StoreFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: gyt
 * @Date: 2022/5/18 10:02
 * @Description:
 */
@RestController
public class StoreFoodController {

    @Autowired
    private StoreFoodService storeFoodService;

    /**
     * 用户新增囤菜
     * @param authorization
     * @param foodName
     * @param quantity
     * @param weight
     * @param createDate
     * @param remindDate
     * @return
     */
    @PostMapping("/api/storeFood/create")
    public ResultData<FoodResponse> InsertFood(
//            @CookieValue(name = "Cookie", required = true) String authorization,
            @RequestParam(value = "foodName", required = true) String foodName,
            @RequestParam(value = "quantity", required = false) Integer quantity,
            @RequestParam(value = "weight", required = false) Integer weight,
            @RequestParam(value = "createDate", required = true) long createDate,
            @RequestParam(value = "remindDate", required = false) long remindDate
    ){
//        先验证身份,获取User
        String personId = "";
//        再调用service处理
        Food food=new Food();
//        需调用函数实现有意义的foodId
        food.setFoodId(IdGenerate.generate("FOOD_ID"));
        food.setFoodName(foodName);
        food.setCreateDate(createDate);
        food.setRemindDate(remindDate);
        food.setQuantity(quantity);
        food.setWeight(weight);

        int status = storeFoodService.InsertFood(personId,food);
//        鉴定处理结果并返回
//        出错处理比较简单，需要修改
        if(status==1){
            return ResultData.success(null);
        }else {
            return ResultData.fail(ReturnCode.RC999.getCode(),"插入失败");
        }

    }

    /**
     *用户删除某一项囤菜，一次只能删除一个
     * @param authorization
     * @param foodId
     * @return
     */
    @DeleteMapping("/api/storeFood/delete")
    public ResultData<FoodResponse> deleteFood(
//            @CookieValue(name = "Cookie", required = true) String authorization,
            @RequestParam(value = "foodId",required = true) Long foodId
    ){
//        先验证身份,获取person,必须有id;food必须有id
        Person person = new Person();
        person.setId(0);
//        再调用service处理
        FoodResponse foodResponse=new FoodResponse();
        int status = storeFoodService.deleteByFoodId(person.getId(),foodId);
//        鉴定处理结果并返回
//        出错处理比较简单，需要修改
        if(status==1){
            return ResultData.success(null);
        }else {
            return ResultData.fail(ReturnCode.RC999.getCode(),"删除失败");
        }
    }

    /**
     *用户修改囤菜信息 根据foodid确定
     * @param authorization
     * @param foodId
     * @param foodName
     * @param quantity
     * @param weight
     * @param createDate
     * @param remindDate
     * @return
     */
    @PutMapping("/api/storeFood/update")
    public ResultData<FoodResponse> updateFood(
//            @CookieValue(name = "Cookie", required = true) String authorization,
            @RequestParam(value = "foodId", required = true) String foodId,
            @RequestParam(value = "foodName", required = true) String foodName,
            @RequestParam(value = "quantity", required = false) Integer quantity,
            @RequestParam(value = "weight", required = false) Integer weight,
            @RequestParam(value = "createDate", required = true) long createDate,
            @RequestParam(value = "remindDate", required = false) long remindDate
    ){
//        先验证身份,获取User
        Integer personId;
//        若验证通过再调用service处理
        Food food=new Food();

        food.setFoodId(foodId);
        food.setFoodName(foodName);
        food.setCreateDate(createDate);
        food.setRemindDate(remindDate);
        food.setQuantity(quantity);
        food.setWeight(weight);
        int status = storeFoodService.updateByFoodId(food);
//        鉴定处理结果并返回
//        出错处理比较简单，需要修改
        if(status==1){
            return ResultData.success(null);
        }else {
            return ResultData.fail(ReturnCode.RC999.getCode(),"修改失败");
        }
    }

    /**
     *用户查看个人囤菜，目前不能筛选 筛选后期拓展
     * @param authorization
     * @param foodId
     * @param foodName
     * @param createDate
     * @param remindDate
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping ("/api/storeFood")
    public ResultData<FoodResponse> checkFood(
            @CookieValue(name = WXConstant.OPEN_ID, required = true) String openId,
            @RequestParam(value = "foodId", required = false) String foodId,
            @RequestParam(value = "foodName", required = false) String foodName,
            @RequestParam(value = "createDate", required = false) long createDate,
            @RequestParam(value = "remindDate", required = false) long remindDate,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "pageSize") Integer pageSize
    ){
//        先验证身份,获取User
        Integer personId = 1;
//        再调用service处理
        FoodResponse foodResponse=new FoodResponse();
        foodResponse.setPage(page);
        foodResponse.setPageSize(pageSize);
        Food food=new Food();
//        需调用函数实现有意义的foodId
        food.setFoodId(foodId);
        food.setFoodName(foodName);
        food.setCreateDate(createDate);
        food.setRemindDate(remindDate);
        foodResponse = storeFoodService.selectByPersonId(personId,food,page,pageSize,foodResponse);
//        鉴定处理结果并返回
        if(foodResponse!=null){
            return ResultData.success(foodResponse);
        }else {
            return ResultData.fail(ReturnCode.RC999.getCode(),"查找失败");
        }
    }
}
