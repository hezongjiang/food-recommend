package com.tencent.food.recommend.service.impl;

import com.tencent.food.recommend.persist.dao.PersonMapper;
import com.tencent.food.recommend.persist.dao.PersonSwapFoodMapper;
import com.tencent.food.recommend.persist.dao.SwapFoodMapper;
import com.tencent.food.recommend.persist.model.Person;
import com.tencent.food.recommend.persist.model.PersonSwapFood;
import com.tencent.food.recommend.persist.model.SwapFood;
import com.tencent.food.recommend.response.SwapFoodDetailResponse;
import com.tencent.food.recommend.response.SwapFoodListResponse;
import com.tencent.food.recommend.service.SwapFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: gyt
 * @Date: 2022/6/8 15:33
 * @Description:
 */
@Service
public class SwapFoodServiceImpl implements SwapFoodService {

    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private SwapFoodMapper swapFoodMapper;
    @Autowired
    private PersonSwapFoodMapper personSwapFoodMapper;

    @Override
    public SwapFoodListResponse selectAll(SwapFoodListResponse swapFoodListResponse) {
        try {
            List<SwapFoodDetailResponse> moments=swapFoodMapper.selectAll();
            swapFoodListResponse.setMoments(moments);
            swapFoodListResponse.setTotal(moments.size());
            try{
                swapFoodListResponse.setPages((int) Math.ceil
                        (moments.size()/swapFoodListResponse.getPageSize()));

            }catch (Exception e){
//                不需要分页
            }
        }catch (Exception e){
            return null;
        }
        return swapFoodListResponse;
    }

    @Override
    public boolean create(SwapFood swapFood, String openid) {
        try {
            PersonSwapFood personSwapFood=new PersonSwapFood();
            personSwapFood.setFromOpenid(openid);
            personSwapFood.setSwapId(swapFood.getSwapId());
            personSwapFood.setState((byte)0);
            swapFoodMapper.insert(swapFood);
//             再插入person_swap_food表
            try{
                personSwapFoodMapper.insert(personSwapFood);
            }catch (Exception e){
                //            A插入不了B先插入成功，要撤销B
                swapFoodMapper.deleteById(swapFood.getSwapId());
                return false;
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * 根据swapid查找交易信息
     * @param record
     * @return
     */
    @Override
    public SwapFoodDetailResponse detail(SwapFoodDetailResponse record) {
        try {
            record=swapFoodMapper.selectOne(record.getSwapId());
        }catch (Exception e){
            return null;
        }
        return record;
    }

    @Override
    public Person Authorize(String openId) {
        Person person=new Person();
//        检验是否在数据库
        person=personMapper.selectByOpenId(openId);
        if (person!=null){
            person.setOpenId(openId);
            return person;
        }else {
            return null;
        }
    }
}
