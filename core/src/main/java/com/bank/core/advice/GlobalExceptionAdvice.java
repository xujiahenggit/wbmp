package com.bank.core.advice;

import org.apache.shiro.ShiroException;
import org.slf4j.Logger;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bank.core.entity.AuthException;
import com.bank.core.entity.BizException;
import com.bank.core.entity.ResultBody;
import com.bank.core.enums.StatusEnum;
import com.bank.core.utils.LoggerUtil;

/**
 * 全局异常处理
 *
 * @author wpp.arthur
 * @Date 2020/02/29
 */
@RestControllerAdvice("com.bank")
public class GlobalExceptionAdvice {

    private static Logger loggerE = LoggerUtil.getExceptionLogger();

    private static Logger loggerB = LoggerUtil.getBusinessLogger();

    /**
     * 处理业务异常
     *
     * @param e 业务异常对象
     */
    @ExceptionHandler(value = BizException.class)
    public ResultBody<Object> bizExceptionHandler(BizException e) {
        loggerB.error(e.getErrorMsg());
        return new ResultBody<Object>().error(StatusEnum.BIZ_ERROR.getCode(), e.getErrorMsg(), e.getData());
    }

    /**
     * 参数异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultBody<Object> paramException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        loggerB.error("参数校验异常:{}({})", fieldError.getDefaultMessage(), fieldError.getField());
        return new ResultBody<Object>().error(StatusEnum.PARM_ERROR.getCode(), fieldError.getDefaultMessage());
    }

    /**
     * 用户无登录权限
     * @param e 异常信息
     * @return
     */
    @ExceptionHandler(value = AuthException.class)
    public ResultBody<Object> noAuthException(AuthException e) {
        loggerB.error(e.getErrorMsg());
        return new ResultBody<Object>().error(StatusEnum.USER_NO_AUTH.getCode(), e.getErrorMsg());
    }

    /**
     * shiro认证授权过程中异常拦截
     *
     * @param e 异常对象
     */
    @ExceptionHandler(ShiroException.class)
    public ResultBody<Object> authExceptionHandler(Exception e) {
        switch (e.getClass().getSimpleName()) {
            case "UnauthenticatedException":
                return new ResultBody<Object>().error(StatusEnum.HTTP_UNAUTHORIZED.getCode(), StatusEnum.HTTP_UNAUTHORIZED.getDesc());
            case "UnauthorizedException":
                return new ResultBody<Object>().error(StatusEnum.HTTP_FORBIDDEN.getCode(), StatusEnum.HTTP_FORBIDDEN.getDesc());
            case "UnknownAccountException":
            case "IncorrectCredentialsException":
                throw new BizException("用户名或密码不正确");
            case "LockedAccountException":
                throw new BizException("用户名被锁定不可用");
            case "AuthenticationException":
                throw new BizException("登录失败：" + e.getMessage());
            default:
                return new ResultBody<Object>().error("鉴权或授权过程出错");
        }
    }

    /**
     * 系统异常
     *
     * @param e 系统异常对象
     */
    @ExceptionHandler(value = { Exception.class })
    public ResultBody<Object> exceptionHandler(Exception e) {
        loggerE.error(e.getMessage());
        return new ResultBody<Object>().error(StatusEnum.HTTP_SERVER_ERROR.getCode(), StatusEnum.HTTP_SERVER_ERROR.getDesc());
    }

}
