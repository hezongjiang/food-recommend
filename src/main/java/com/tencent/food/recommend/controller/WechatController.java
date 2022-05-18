package com.tencent.food.recommend.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.food.recommend.common.ResultData;
import com.tencent.food.recommend.common.utils.WechatUtil;
import com.tencent.food.recommend.response.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登陆
 */
@RestController
public class WechatController {

    @PostMapping("/wx/login")
    public ResultData<User> userLogin(@RequestParam(value = "code", required = false) String code,
                                      @RequestParam(value = "rawData", required = false) String rawData) {
        // 用户非敏感信息：rawData
        // 签名：signature
        JSONObject rawDataJson = JSON.parseObject(rawData);
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject sessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        // 3.接收微信接口服务 获取返回的参数
        String openid = sessionKeyOpenId.getString("openid");
        String sessionKey = sessionKeyOpenId.getString("session_key");

        // 5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；
        User user = null;
        if (user == null) {
            user = new User();
            // 用户信息入库
            if (rawDataJson != null) {
                String nickName = rawDataJson.getString("nickName");
                String avatarUrl = rawDataJson.getString("avatarUrl");
                user.setAvatar(avatarUrl);
                user.setNickName(nickName);
            }
            user.setOpenId(openid);
        }
        return ResultData.success(user);
    }

}
