package com.bank.core.controller;

import javax.servlet.ServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bank.core.entity.ResultBody;
import com.bank.core.enums.StatusEnum;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class ExceptionController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/exception";
    }

    @RequestMapping(value = { "/exception" })
    @ResponseBody
    public Object exception(ServletRequest request) {
        Exception exception = (Exception) request.getAttribute("filter.exception");
        if (StringUtils.equals(exception.getCause().getClass().getSimpleName(), "AuthenticationException")) {
            return new ResultBody<Object>().error(StatusEnum.HTTP_UNAUTHORIZED.getCode(), exception.getCause().getMessage());
        }
        else {
            return new ResultBody<Object>().error(500, exception.getCause().getMessage());
        }
    }

}
