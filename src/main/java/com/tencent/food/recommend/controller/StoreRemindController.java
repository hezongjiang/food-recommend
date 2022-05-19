package com.tencent.food.recommend.controller;

import com.tencent.food.recommend.common.ResultData;
import com.tencent.food.recommend.common.consts.WXConstant;
import com.tencent.food.recommend.common.enums.ReturnCode;
import com.tencent.food.recommend.persist.model.Person;
import com.tencent.food.recommend.persist.model.StoreRemind;
import com.tencent.food.recommend.response.StoreRemindResponse;
import com.tencent.food.recommend.service.StoreRemindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/storeRemind")
@Slf4j
public class StoreRemindController {

    @Autowired
    StoreRemindService storeRemindService;

    StoreRemind storeRemind;

    @PostMapping("/add")
    public ResultData addRemind (@RequestHeader(name = "openid") String openId,
                                 @RequestParam(value = "remindDate") Long remindDate,
                                 @RequestParam(value = "remarks") String remarks) {
        //先代替openid
        //String openId = "1";

        storeRemind = new StoreRemind();
        storeRemind.setCreatedDate(System.currentTimeMillis());
        storeRemind.setRemindDate(remindDate);
        storeRemind.setRemarks(remarks);
        log.info("此时的openid为：" + openId);
        int result = storeRemindService.add(openId,storeRemind);
        if (result == 1) {
            log.info("添加囤货提醒成功");
            return ResultData.success(storeRemind);
        } else {
            log.info("添加囤货提醒失败");
            return ResultData.fail(ReturnCode.RC999.getCode(), "插入失败");
        }


    }
    @GetMapping("/find")
    public ResultData findRemindByOpenId (@RequestHeader(name = WXConstant.OPEN_ID) String openId) {
        Person person= storeRemindService.Authorize(openId);
        if (person != null) {
            List<StoreRemind> result = storeRemindService.findAllRemind(openId);
            if (result != null ) {
                return ResultData.success(result);
            } else {
                return ResultData.fail(ReturnCode.RC999.getCode(), "查询失败");
            }
        } else {
            return ResultData.fail(ReturnCode.RC401.getCode(), "请登录重试");
        }


    }

}
