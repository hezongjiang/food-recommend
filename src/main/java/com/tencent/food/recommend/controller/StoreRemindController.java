package com.tencent.food.recommend.controller;

import com.tencent.food.recommend.common.ResultData;
import com.tencent.food.recommend.common.consts.WXConstant;
import com.tencent.food.recommend.persist.model.StoreRemind;
import com.tencent.food.recommend.response.StoreRemindResponse;
import com.tencent.food.recommend.service.StoreRemindService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/storeRemind")
public class StoreRemindController {

    @Autowired
    StoreRemindService storeRemindService;
    StoreRemind storeRemind;
    StoreRemindResponse storeRemindResponse;

    @PostMapping("/add")
    public ResultData addRemind (//@CookieValue(name = WXConstant.OPEN_ID, required = true) String openId,
                                 @RequestParam(value = "remindDate") Long remindDate,
                                 @RequestParam(value = "remarks") String remarks) {
        //先代替openid
        String openId = "1";

        storeRemind = new StoreRemind();
        storeRemind.setCreatedDate(System.currentTimeMillis());
        storeRemind.setRemindDate(remindDate);
        storeRemind.setRemarks(remarks);
        int result = storeRemindService.add(openId,storeRemind);
        return ResultData.success(storeRemind);

    }
    @GetMapping("/find")
    public ResultData findRemindById (@RequestParam(value = "id") int id) {
        storeRemindResponse = storeRemindService.findRemindById(id);
        return ResultData.success(storeRemindResponse);
    }

}
