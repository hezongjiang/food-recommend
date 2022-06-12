package com.tencent.food.recommend.common.enums;

import lombok.Getter;

/**
 * 返回值状态枚举
 */
@Getter
public enum ReturnCode {
    /**操作成功**/
    RC100(200,"操作成功"),
    /**操作失败**/
    RC999(999,"操作失败"),
    /**access_denied**/
    RC403(403,"无访问权限,请联系管理员授予权限"),
    /**access_denied**/
    RC401(401,"匿名用户访问无权限资源时的异常"),
    /**服务异常**/
    RC500(500,"系统异常，请稍后重试"),

    INVALID_TOKEN(2001,"访问令牌不合法"),
    ACCESS_DENIED(2003,"没有权限访问该资源"),
    CLIENT_AUTHENTICATION_FAILED(1001,"客户端认证失败"),
    USERNAME_OR_PASSWORD_ERROR(1002,"用户名或密码错误"),
    UNSUPPORTED_GRANT_TYPE(1003, "不支持的认证模式"),


    USER_NOT_EXISTS(3001, "用户不存在"),
    REMIND_DATE_EMPTY(3002, "提醒时间不能为空"),
    TITLE_NOT_NULL(3003, "标题不能为空"),
    TYPE_NOT_NULL(3004, "类型不能为空");


    /**自定义状态码**/
    private final Integer code;
    /**自定义描述**/
    private final String message;

    ReturnCode(int code, String message){
        this.code = code;
        this.message = message;
    }
}