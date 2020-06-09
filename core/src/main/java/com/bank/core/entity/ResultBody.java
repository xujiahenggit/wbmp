package com.bank.core.entity;

import com.bank.core.enums.StatusEnum;

import lombok.Data;

/**
 * 返回数据封装对象
 *
 * @author wpp.arthur
 * @Date 2020/02/29
 */
@Data
public class ResultBody<T> {

    /**
     * 接口请求状态
     */
    private Boolean status;

    /**
     * 信息码
     */
    private Integer code;

    /**
     * 错误信息说明
     */
    private String errorMsg;

    /**
     * 业务主体对象
     */
    private T data;

    /**
     * 返回成功对象
     *
     * @param data 业务数据
     */
    public ResultBody<T> success(T data) {
        return success(StatusEnum.HTTP_REQUEST_SUCCESS.getCode(), data);
    }

    /**
     * 返回成功对象
     *
     * @param data 业务数据
     */
    public ResultBody<T> success(Integer code, T data) {
        ResultBody<T> resultBody = new ResultBody<>();
        resultBody.setStatus(true);
        resultBody.setCode(code);
        resultBody.setErrorMsg(null);
        resultBody.setData(data);
        return resultBody;
    }

    /**
     * 返回失败对象
     *
     * @param errorMsg 错误消息
     */
    public ResultBody<T> error(String errorMsg) {
        return error(null, errorMsg);
    }

    /**
     * @param code     消息码
     * @param errorMsg errorMsg 错误消息
     */
    public ResultBody<T> error(Integer code, String errorMsg) {
        return error(code, errorMsg, null);
    }

    /**
     *
     * @param code 消息码
     * @param errorMsg 错误消息
     * @param data 数据
     * @return
     */
    public ResultBody<T> error(Integer code, String errorMsg, T data) {
        ResultBody<T> resultBody = new ResultBody<>();
        resultBody.setStatus(false);
        resultBody.setCode(code);
        resultBody.setErrorMsg(errorMsg);
        resultBody.setData(data);
        return resultBody;
    }
}
