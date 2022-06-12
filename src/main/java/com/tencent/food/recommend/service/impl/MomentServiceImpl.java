package com.tencent.food.recommend.service.impl;

import com.tencent.food.recommend.persist.dao.MomentMapper;
import com.tencent.food.recommend.persist.model.Moment;
import com.tencent.food.recommend.response.MomentDetailResponse;
import com.tencent.food.recommend.response.MomentListResponse;
import com.tencent.food.recommend.service.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MomentServiceImpl implements MomentService {

    @Autowired
    MomentMapper momentMapper;


    @Override
    public int createMoment(Moment moment) {
        momentMapper.insertSelective(moment);
        return 1;
    }

    @Override
    public MomentListResponse selectAll(Integer page, Integer pageSize) {

        MomentListResponse momentListResponse = new MomentListResponse();

        List<Moment> list = new LinkedList<>();

        list = momentMapper.selectAll();

        momentListResponse.setPage(page);
        momentListResponse.setPageSize(pageSize);
        momentListResponse.setTotal(list.size());

        List<MomentDetailResponse> resultList = new LinkedList<>();
        for(int i = 0; i < list.size(); i ++) {
            Moment temp = list.get(i);
            MomentDetailResponse tempResponse = new MomentDetailResponse();
            tempResponse.setMomentId(temp.getMomentId());
            tempResponse.setTitle(temp.getTitle());
            tempResponse.setContent(temp.getContent());
            tempResponse.setType(temp.getType());
            tempResponse.setPictures(temp.getPictures());
            tempResponse.setPostTime(temp.getPostTime());
            if (temp.getType() != null) {
                if (temp.getType() == 1){
                    tempResponse.setNickName("囤菜攻略");
                } else if (temp.getType() == 2) {
                    tempResponse.setNickName("食材处理");
                } else if (temp.getType() == 3) {
                    tempResponse.setNickName("美食分享");
                } else {
                    tempResponse.setNickName("未设置类型");
                }
            }
            resultList.add(tempResponse);
        }
        momentListResponse.setMoments(resultList);
        try {
            momentListResponse.setPages((int) Math.ceil(resultList.size() / pageSize + 1));
        } catch (Exception e){

        }
        return momentListResponse;
    }
}
