package com.bank.icop.advice;

import cn.hutool.json.JSONUtil;
import com.bank.core.entity.BizException;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.utils.JsonUtil;
import com.bank.icop.dos.SoapLogDO;
import com.bank.icop.service.SoapLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Slf4j
@Component
public class SoapLogAop {

    @Resource
    SoapLogService soapLogService;

    @Pointcut("execution (public * com.bank.icop.controller.CashVoucherController.*(..))")
    public void controllerPointCut() {
    }

    @Around("controllerPointCut()")
    public Object before(ProceedingJoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        TokenUserInfo currentUserInfo = getCurrentUserInfo(request);
        String requestURI = request.getRequestURI();

        Object resultData = null;
        long startTime = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
        try {
            resultData = joinPoint.proceed(args);
        } catch (Throwable e) {
            throw new BizException("获取接口返回结果信息出错", e.getMessage());
        }
        long endTime = System.currentTimeMillis();
        Long costTime = endTime - startTime;
        SoapLogDO soapLogDO = SoapLogDO.builder()
                .costTime(costTime)
                .requestUri(requestURI)
                .requestArgs(JSONUtil.toJsonStr(args))
                .response(JsonUtil.str2JsonStr(resultData))
                .userId(currentUserInfo==null?"":currentUserInfo.getUserId())
                .userName(currentUserInfo==null?"":currentUserInfo.getUserName()).build();
        soapLogService.save(soapLogDO);
        return resultData;
    }

    protected TokenUserInfo getCurrentUserInfo(HttpServletRequest request) {
        HttpServletRequest req = request;
        String token = req.getHeader("Authorization");
        if (token == null) {
            return null;
        }
        TokenUserInfo user = soapLogService.getUserInfo(token);
        if (user == null) {
            return null;
        }
        return user;
    }

}
package com.bank.icop.advice;

import com.bank.core.entity.BizException;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.utils.JsonUtil;
import com.bank.icop.dos.SoapLogDO;
import com.bank.icop.service.SoapLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Slf4j
@Component
public class SoapLogAop {

    @Resource
    SoapLogService soapLogService;

    @Pointcut("execution (public * com.bank.icop.controller.CashVoucherController.*(..))")
    public void controllerPointCut() {
    }

    @Around("controllerPointCut()")
    public Object before(ProceedingJoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        TokenUserInfo currentUserInfo = getCurrentUserInfo(request);
        String requestURI = request.getRequestURI();

        Object resultData = null;
        long startTime = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
        try {
            resultData = joinPoint.proceed(args);
        } catch (Throwable e) {
            throw new BizException("获取接口返回结果信息出错", e.getMessage());
        }
        long endTime = System.currentTimeMillis();
        Long costTime = endTime - startTime;
        SoapLogDO soapLogDO = SoapLogDO.builder()
                .costTime(costTime)
                .requestUri(requestURI)
                .requestArgs(JsonUtil.str2JsonStr(joinPoint.getArgs()))
                .response(JsonUtil.str2JsonStr(resultData))
                .userId(currentUserInfo==null?"":currentUserInfo.getUserId())
                .userName(currentUserInfo==null?"":currentUserInfo.getUserName()).build();
        soapLogService.save(soapLogDO);
        return resultData;
    }

    protected TokenUserInfo getCurrentUserInfo(HttpServletRequest request) {
        HttpServletRequest req = request;
        String token = req.getHeader("Authorization");
        if (token == null) {
            return null;
        }
        TokenUserInfo user = soapLogService.getUserInfo(token);
        if (user == null) {
            return null;
        }
        return user;
    }

}
