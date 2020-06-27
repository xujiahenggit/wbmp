package com.bank.core.controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bank.core.entity.ResultBody;
import com.bank.core.enums.StatusEnum;

import springfox.documentation.annotations.ApiIgnore;

/**
 * 404页面错误处理
 * 由于spring boot拦截器不能拦截404错误，所以需要通过实现ErrorController的方式实现
 *
 * @author wpp.arthur
 * @Date 2020/02/29
 */
@ApiIgnore
@Controller
public class NotFoundExceptionController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = { "/error" })
    @ResponseBody
    public Object error(ServletRequest request, ServletResponse response) {
        Exception exception = (Exception) request.getAttribute("jwtFilter.exception");
        if (exception == null) {
            return new ResultBody<Object>().error(StatusEnum.HTTP_NOT_FOUND.getCode(), StatusEnum.HTTP_NOT_FOUND.getDesc());
        }
        else {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
            httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return new ResultBody<Object>().error(StatusEnum.HTTP_UNAUTHORIZED.getCode(), exception.getMessage());
        }
    }
}
