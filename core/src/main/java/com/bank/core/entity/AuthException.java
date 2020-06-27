package com.bank.core.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Andy
 * @Date: 2020/5/6 16:11
 * 用户登录异常
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AuthException extends RuntimeException {
    private static final long serialVersionUID = -7148169437991097594L;

    /**
     * 登录异常信息
     */
    protected String errorMsg;

    public AuthException() {
        super();
    }

    public AuthException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public AuthException(String message, String errorMsg) {
        super(message);
        this.errorMsg = errorMsg;
    }

    public AuthException(String message, Throwable cause, String errorMsg) {
        super(message, cause);
        this.errorMsg = errorMsg;
    }

    public AuthException(Throwable cause, String errorMsg) {
        super(cause);
        this.errorMsg = errorMsg;
    }

    public AuthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorMsg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorMsg = errorMsg;
    }
}
