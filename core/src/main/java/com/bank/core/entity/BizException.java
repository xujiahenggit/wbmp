package com.bank.core.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务异常对象
 *
 * @author wpp.arthur
 * @Date 2020/02/29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BizException extends RuntimeException {

    private static final long serialVersionUID = -7148169437991097594L;

    /**
     * 业务异常信息
     */
    protected String errorMsg;

    public BizException() {
        super();
    }

    public BizException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BizException(String message, String errorMsg) {
        super(message);
        this.errorMsg = errorMsg;
    }

    public BizException(String message, Throwable cause, String errorMsg) {
        super(message, cause);
        this.errorMsg = errorMsg;
    }

    public BizException(Throwable cause, String errorMsg) {
        super(cause);
        this.errorMsg = errorMsg;
    }

    public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorMsg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorMsg = errorMsg;
    }
}