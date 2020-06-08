package com.bank.log.aspect;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.utils.ConfigFileReader;
import com.bank.core.utils.IPUtils;
import com.bank.core.utils.JwtUtil;
import com.bank.log.annotation.SystemLog;
import com.bank.log.dos.ErrorLogDO;
import com.bank.log.dos.LogDO;
import com.bank.log.service.ErrotLogService;
import com.bank.log.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Andy
 * @Date: 2020/4/1 10:28
 */
@Aspect
@Component
@Slf4j
public class SystemLogAspect {

    @Resource
    private ConfigFileReader configFileReader;

    @Autowired
    private LogService logService;

    @Autowired
    private ErrotLogService errotLogService;

    /**
     * 日志切入点 注解处进行日志记录
     */
    @Pointcut("@annotation(com.bank.log.annotation.SystemLog)")
    public void SystemLogPoincut() {

    }

    /**
     * 异常日志切入口 所有controller下的错误日志
     */
    @Pointcut("execution(* com.bank.*.controller..*.*(..))")
    public void ErrorLogPoincut() {

    }

    /**
     * 正常日志
     *
     * @param joinPoint 切入点
     * @param keys      返回信息
     */
    @AfterReturning(value = "SystemLogPoincut()", returning = "keys")
    public void saveSystemLog(JoinPoint joinPoint, Object keys) {
        long start = System.currentTimeMillis();
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);
        //获取登录的用户名和ID
        String userid="";
        String userName="";
        String token=request.getHeader("Authorization");
        if(token!=null){
            TokenUserInfo user = JwtUtil.getUserInfo(token);
            if(user!=null){
                userid=user.getUserId();
                userName=user.getUserName();
            }
        }
        //用户IP
        String userIp = IPUtils.getIpAddr(request);
        LogDO operlog = new LogDO();
        try {
            //设置主键
            operlog.setLogId(IdUtil.simpleUUID());
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取操作
            SystemLog opLog = method.getAnnotation(SystemLog.class);
            if (opLog != null) {
                String operModul = opLog.logModul();
                String operType = opLog.logType();
                String operDesc = opLog.logDesc();
                // 操作模块
                operlog.setOptModual(operModul);
                // 操作类型
                operlog.setOptType(operType);
                // 操作描述
                operlog.setOptDisc(operDesc);
            }
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            operlog.setOptClass(className);
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;
            // 请求方法
            operlog.setOptMethod(methodName);
//            // 请求的参数  注意 这种方式无法获取 @Requestbody 中的参数
//            Map<String, String> rtnMap = converMap(request.getParameterMap());
//            // 将参数所在的数组转换成json
//            String params = JSON.toJSONString(rtnMap);
            //请求的参数
            Object[] args = joinPoint.getArgs();
            try {
                if (args.length > 0) {
                    String params = JSON.toJSONString(args[0]);
                    // 请求参数
                    operlog.setOptParam(params);
                }
            } catch (Exception e) {
                log.error("序列化请求参数出错：" + e);
            }
            // 返回结果
            operlog.setOptResult(JSON.toJSONString(keys));
            // 请求用户ID
            operlog.setOptUserId(userid);
            // 请求用户名称
            operlog.setOptUserName(userName);
            // 请求IP
            operlog.setOptIp(userIp);
            // 请求URI
            operlog.setOptUrl(request.getRequestURI());
            // 创建时间
            operlog.setOptDate(LocalDateTime.now());
            // 操作版本
            operlog.setOptVersion(configFileReader.getLOG_VERSION());
            //操作耗时
            long duraTime = System.currentTimeMillis() - start;
            operlog.setOptDura(duraTime);
            logService.save(operlog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 异常日志记录信息
     *
     * @param joinPoint 切人点
     * @param throwable 异常信息
     */
    @AfterThrowing(pointcut = "ErrorLogPoincut()", throwing = "throwable")
    public void saveErrorLog(JoinPoint joinPoint, Throwable throwable) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);

        String userid="";
        String userName="";
        String token=request.getHeader("Authorization");
        if(token!=null){
            TokenUserInfo user = JwtUtil.getUserInfo(token);
            if(user!=null){
                userid=user.getUserId();
                userName=user.getUserName();
            }
        }
        // 从 request 对象中获取IP
        String userIp = IPUtils.getIpAddr(request);
        ErrorLogDO errorLog = new ErrorLogDO();
        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            errorLog.setErrLogId(IdUtil.simpleUUID());
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;
//            // 请求的参数  这种方式无法获取@Requestbody 中的参数
//            Map<String, String> rtnMap = converMap(request.getParameterMap());
//            // 将参数所在的数组转换成json
//            String params = JSON.toJSONString(rtnMap);

            Object[] args = joinPoint.getArgs();
            try {
                if (args.length > 0) {
                    String optparams = JSON.toJSONString(args[0]);
                    // 请求参数
                    errorLog.setErrPara(optparams);
                }
            } catch (Exception e) {
                log.error("序列化请求参数出错：" + e);
            }
            // 请求类名
            errorLog.setErrClass(className);
            // 请求方法名
            errorLog.setErrMethodName(methodName);
            // 方法
            errorLog.setErrMethod(methodName);
            // 异常名称
            errorLog.setErrName(throwable.getClass().getName());
            // 异常信息
            errorLog.setErrInfo(stackTraceToString(throwable.getClass().getName(), throwable.getMessage(), throwable.getStackTrace()));
            // 操作员ID
            errorLog.setOptUserId(userid);
            // 操作员名称
            errorLog.setOptUserName(userName);
            // 操作URI
            errorLog.setOptUrl(request.getRequestURI());
            // 操作员IP
            errorLog.setOptIp(userIp);
            // 操作版本号
            errorLog.setErrVersion(configFileReader.getLOG_VERSION());
            // 发生异常时间
            errorLog.setErrDate(LocalDateTime.now());
            errotLogService.save(errorLog);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /**
     * 转为 map 对象
     *
     * @param paramMap
     * @return
     */
    public Map<String, String> converMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<String, String>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
    }

    /**
     * 异常信息 转化
     *
     * @param exceptionName    异常名称
     * @param exceptionMessage 异常信息
     * @param elements         异常栈
     * @return
     */
    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer strbuff = new StringBuffer();
        for (StackTraceElement stet : elements) {
            strbuff.append(stet + "\n");
        }
        String message = exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
        return message;
    }
}
