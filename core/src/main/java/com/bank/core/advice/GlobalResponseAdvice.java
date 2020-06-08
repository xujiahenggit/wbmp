package com.bank.core.advice;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bank.core.entity.ResultBody;

/**
 * 统一返回对象封装
 * 需指定basePackages，原因是该拦截器将Swagger2的请求一并拦截了
 *
 * @author wpp.arthur
 * @Date 2020/02/29
 */
@RestControllerAdvice("com.bank")
class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        String converterType = aClass.getName();
        if (!(body instanceof ResultBody)) {
            ResultBody<Object> resultBody = new ResultBody<Object>().success(body);
            //字符串返回适配
            String stringConverter = "org.springframework.http.converter.StringHttpMessageConverter";
            if (StringUtils.equals(stringConverter, converterType) || body instanceof String) {
                return JSON.toJSONString(resultBody, SerializerFeature.WriteMapNullValue);
            }
            return resultBody;
        }
        return body;
    }
}