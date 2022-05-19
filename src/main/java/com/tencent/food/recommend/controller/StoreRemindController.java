package com.tencent.food.recommend.controller;

import com.tencent.food.recommend.common.ResultData;
import com.tencent.food.recommend.common.consts.WXConstant;
import com.tencent.food.recommend.common.enums.ReturnCode;
import com.tencent.food.recommend.persist.model.StoreRemind;
import com.tencent.food.recommend.response.StoreRemindResponse;
import com.tencent.food.recommend.service.StoreRemindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/storeRemind")
@Slf4j
public class StoreRemindController {

    @Autowired
    StoreRemindService storeRemindService;
    StoreRemind storeRemind;
    StoreRemindResponse storeRemindResponse;

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
    public ResultData findRemindById (@RequestParam(value = "id") int id) {
        storeRemindResponse = storeRemindService.findRemindById(id);
        return ResultData.success(storeRemindResponse);
    }

}
