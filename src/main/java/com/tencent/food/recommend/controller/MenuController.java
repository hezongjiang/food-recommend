package com.tencent.food.recommend.controller;

import com.tencent.food.recommend.common.ResultData;
import com.tencent.food.recommend.common.consts.MeatConst;
import com.tencent.food.recommend.response.MenuResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜谱
 */
@RestController
public class MenuController {


    @GetMapping("menu")
    public ResultData<List<MenuResponse>> menu() {
        List<MenuResponse> result = new ArrayList<>(MeatConst.map.size());
        MeatConst.map.forEach((key, value) -> {
            MenuResponse menuResponse = new MenuResponse();
            menuResponse.setName(key);
            menuResponse.setDetail(value);
            result.add(menuResponse);
        });
        return ResultData.success(result);
    }
}
