package com.bank.core.enums;

/**
 * @author wpp.arthur
 * @Date 2020/02/29
 */
public enum StatusEnum {

    /**
     * HTTP状态码
     */
    // http状态码 - 请求成功
    HTTP_REQUEST_SUCCESS(200, "【请求成功】The request is successful"),
    // http状态码 - 用户未登陆
    HTTP_UNAUTHORIZED(401, "【用户未登陆】User is not login"),
    // http状态码 - 用户没有访问权限
    HTTP_FORBIDDEN(403, "【用户没有访问权限】The user has no access rights"),
    // http状态码 - 请求路径未找到
    HTTP_NOT_FOUND(404, "【请求路径未找到】The request path was not found"),
    // http状态码 - 未知服务器错误
    HTTP_SERVER_ERROR(500, "【未知服务器错误】Internal Server Error"),
    //业务处理异常码
    BIZ_ERROR(100, "【业务处理异常】Business Process handle Error"),
    //参数异常
    PARM_ERROR(300,"【参数异常】"),
    //用户无登录权限异常
    USER_NO_AUTH(1000,"【用户无登录权限】");

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态描述
     */
    private String desc;

    StatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
