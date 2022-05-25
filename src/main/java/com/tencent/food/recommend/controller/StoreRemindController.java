package com.tencent.food.recommend.controller;

import com.tencent.food.recommend.common.ResultData;
import com.tencent.food.recommend.common.consts.WXConstant;
import com.tencent.food.recommend.common.enums.ReturnCode;
import com.tencent.food.recommend.persist.model.Person;
import com.tencent.food.recommend.persist.model.StoreRemind;
import com.tencent.food.recommend.service.PersonService;
import com.tencent.food.recommend.service.PersonStoreRemindService;
import com.tencent.food.recommend.service.StoreRemindService;
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

    @Autowired
    PersonService personService;

    @Autowired
    PersonStoreRemindService personStoreRemindService;


    /**
     * 增加提醒
     * @param openId
     * @param remindDate
     * @param remarks
     * @return
     */
    @PostMapping("/add")
    public ResultData addRemind (@RequestHeader(name = "openid") String openId,
                                 @RequestParam(value = "remindDate") Long remindDate,
                                 @RequestParam(value = "remarks") String remarks) {
        //先代替openid
        //String openId = "1";

        //查询此用户是否存在
        Person person = personService.findPersonByOpenId(openId);

        if (person == null) {
            //用户不存在
            return ResultData.error(ReturnCode.USER_NOT_EXISTS);
        }

        if (remindDate == null) {
            //remindDate为空
            return ResultData.error(ReturnCode.REMIND_DATE_EMPTY);
        }
        StoreRemind storeRemind = new StoreRemind();
        storeRemind.setCreatedDate(System.currentTimeMillis());
        storeRemind.setRemindDate(remindDate);
        storeRemind.setRemarks(remarks);

        int storeRemindId = storeRemindService.addAndGetId(storeRemind);
        //更新peronStoreRemind表
        personStoreRemindService.insertPersonStoreRemind(openId, storeRemindId);
        //将添加的数据返回前端
        return ResultData.success(storeRemind);

    }


    /**
     * 查找所以提醒
     * @param openId
     * @return
     */
    @GetMapping("/find")
    public ResultData findRemindByOpenId (@RequestHeader(name = WXConstant.OPEN_ID) String openId) {
        Person person = personService.findPersonByOpenId(openId);
        if (person == null) {
            return ResultData.error(ReturnCode.USER_NOT_EXISTS);
        }
        List<StoreRemind> storeRemindList = storeRemindService.findAllRemind(openId);
        return ResultData.success(storeRemindList);


    }

    @DeleteMapping("/delete")
    public ResultData deleteById ( @RequestHeader(name = WXConstant.OPEN_ID) String openId,
                                   @RequestParam(value = "id", required = true) String remindId) {

        Person person = personService.findPersonByOpenId(openId);
        if (person == null) {
            return ResultData.error(ReturnCode.USER_NOT_EXISTS);
        }

        Integer id = null;
        if (remindId != null) {
            id = Integer.valueOf(remindId);
            storeRemindService.deleteById(id);
            return ResultData.ok("删除成功");
        } else {
            return ResultData.error();
        }

    }

}
