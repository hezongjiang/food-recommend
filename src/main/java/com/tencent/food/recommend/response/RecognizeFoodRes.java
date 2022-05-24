package com.tencent.food.recommend.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RecognizeFoodRes implements Serializable {
    private List<String> content;
}
