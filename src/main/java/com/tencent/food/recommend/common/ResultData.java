package com.tencent.food.recommend.common;

import com.tencent.food.recommend.common.enums.ReturnCode;
import lombok.Data;

/**
 * 统一返回值
 */
@Data
public class ResultData<T> {

    /**
     * 结果状态
     * @see ReturnCode
     */
    private Integer status;

    private String message;

    private T data;

    private long timestamp ;

    public ResultData (){
        this.timestamp = System.currentTimeMillis();
    }

    public ResultData (Integer status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(ReturnCode.RC100.getCode());
        resultData.setMessage(ReturnCode.RC100.getMessage());
        resultData.setData(data);
        return resultData;
    }

    public static <T> ResultData<T> fail(int code, String message) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(code);
        resultData.setMessage(message);
        return resultData;
    }

    public static ResultData ok () {
        return new ResultData(1, "success");
    }
    public static ResultData ok(String message) {
        return new ResultData(1, message);
    }

    public static ResultData error () {
        return new ResultData(ReturnCode.RC999.getCode(), ReturnCode.RC999.getMessage());
    }

    public static ResultData error (ReturnCode returnCode) {
        return new ResultData(returnCode.getCode(),returnCode.getMessage());
    }

}