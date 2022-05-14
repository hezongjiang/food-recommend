package com.tencent.food.recommend;

import com.tencent.food.recommend.persist.dao.PersonMapper;
import com.tencent.food.recommend.persist.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private PersonMapper personMapper;

    @GetMapping("test")
    public Person test() {
        return personMapper.getById(1);
    }
}
