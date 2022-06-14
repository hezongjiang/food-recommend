package com.tencent.food.recommend.controller;

import com.tencent.food.recommend.common.ResultData;
import com.tencent.food.recommend.common.enums.ReturnCode;
import com.tencent.food.recommend.common.utils.IdGenerate;
import com.tencent.food.recommend.persist.model.Moment;
import com.tencent.food.recommend.persist.model.Person;
import com.tencent.food.recommend.response.MomentDetailResponse;
import com.tencent.food.recommend.response.MomentListResponse;
import com.tencent.food.recommend.service.MomentService;
import com.tencent.food.recommend.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 圈子
 */
@RestController
@RequestMapping("/moment")
public class MomentController {


    @Autowired
    PersonService personService;

    @Autowired
    MomentService momentService;

    /**
     * 发布圈子
     *
     * @param openId   openid
     * @param title    标题
     * @param content  文本内容
     * @param type     分区类型：1-囤菜攻略、2-食材处理、3-美食分享
     * @param location 地点
     * @param pictures 图片列表，json格式，例如：["xxx", "yyy"]
     * @return 创建成功与否
     */
    @PostMapping("/create")
    public ResultData<Boolean> create(@RequestHeader(name = "openid") String openId,
                                      @RequestParam(value = "title") String title,
                                      @RequestParam(value = "content") String content,
                                      @RequestParam(value = "type") Integer type,
                                      @RequestParam(value = "location", required = false) String location,
                                      @RequestParam(value = "pictures", required = false) String pictures) {

        return ResultData.success(null);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    public ResultData<MomentListResponse> list(@RequestParam(value = "page") Integer page,
                                               @RequestParam(value = "pageSize") Integer pageSize) {
        MomentListResponse momentListResponse = momentService.selectAll(page, pageSize);
        return ResultData.success(momentListResponse);
    }

    /**
     * 详情
     * 根据动态momentId来查询某个动态的详细
     */
    @GetMapping("/detail")
    public ResultData<MomentDetailResponse> detail(@RequestParam(value = "momentId") String momentId) {

        MomentDetailResponse result = momentService.selectByMomentId(momentId);

        return ResultData.success(result);
    }

    /**
     * @param openId   用户唯一标识
     * @param title    标题 （必须）
     * @param content  文本
     * @param type     类型 1-囤菜攻略、2-食材处理、3-美食分享 （必须）
     * @param location 位置
     * @Author:NathanYu
     * @Description:
     * @Date: 2022/6/9 13:49
     */
    @PostMapping("/add")
    public ResultData add(@RequestHeader(name = "openid") String openId,
                          @RequestParam(value = "title") String title,
                          @RequestParam(value = "content") String content,
                          @RequestParam(value = "type") Integer type,
                          @RequestParam(value = "location", required = false) String location) {

        Person person = personService.findPersonByOpenId(openId);

        if (person == null) {
            //用户不存在
            return ResultData.error(ReturnCode.USER_NOT_EXISTS);
        }
        if (title == null) {
            return ResultData.error(ReturnCode.TITLE_NOT_NULL);
        }
        if (type == null) {
            return ResultData.error(ReturnCode.TYPE_NOT_NULL);
        }

        Moment moment = new Moment();
        moment.setMomentId(IdGenerate.generate("MOMENT_ID"));
        moment.setTitle(title);
        moment.setContent(content);
        moment.setOpenId(openId);
        moment.setType(type);
        moment.setLocation(location);
        moment.setPostTime(System.currentTimeMillis());

        momentService.createMoment(moment);

        return ResultData.success(true);
    }
}
