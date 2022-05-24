package com.tencent.food.recommend.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.food.recommend.common.ResultData;
import com.tencent.food.recommend.common.consts.WXConstant;
import com.tencent.food.recommend.common.enums.ReturnCode;
import com.tencent.food.recommend.common.utils.WechatUtil;
import com.tencent.food.recommend.persist.dao.PersonMapper;
import com.tencent.food.recommend.persist.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆
 */
@RestController
public class WechatController {

    @Autowired
    private PersonMapper personMapper;

    @PostMapping("/wx/login")
    public ResultData<Person> userLogin(@RequestParam(value = "code") String code,
                                      @RequestParam(value = "rawData", required = false) String rawData,
                                      HttpServletRequest request, HttpServletResponse response) {
        // 用户非敏感信息：rawData
        // 签名：signature
        JSONObject rawDataJson = JSON.parseObject(rawData);
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject sessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        // 3.接收微信接口服务 获取返回的参数
        String openid = sessionKeyOpenId.getString("openid");
        String sessionKey = sessionKeyOpenId.getString("session_key");

        if (!StringUtils.hasText(openid)) {
            return ResultData.fail(ReturnCode.RC999.getCode(), "登陆失败");
        }
        Person person = personMapper.selectByOpenId(openid);
        // 新用户
        if (person == null) {
            person = new Person();
            person.setOpenId(openid);
            // 用户信息入库
            if (rawDataJson != null) {
                String nickName = rawDataJson.getString("nickName");
                String avatarUrl = rawDataJson.getString("avatarUrl");
                person.setName(nickName);
            }
            personMapper.insertSelective(person);
        }
        Cookie cookie = new Cookie(WXConstant.OPEN_ID, openid);
        // 设置为当前项目下都携带这个cookie
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);

        return ResultData.success(person);
    }

}
