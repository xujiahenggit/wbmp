package com.bank.core.utils;

import com.bank.core.enums.LoggerEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wpp.arthur
 * @Date 2020/02/29
 */
public class LoggerUtil {

    /**
     * 获取业务日志logger
     */
    public static Logger getBusinessLogger() {
        return LoggerFactory.getLogger(LoggerEnum.BUSINESS.getCategory());
    }

    /**
     * 获取平台日志logger
     */
    public static Logger getPlatformLogger() {
        return LoggerFactory.getLogger(LoggerEnum.PLATFORM.getCategory());
    }

    /**
     * 获取数据库日志logger
     */
    public static Logger getDbLogger() {
        return LoggerFactory.getLogger(LoggerEnum.DB.getCategory());
    }

    /**
     * 获取异常日志logger
     */
    public static Logger getExceptionLogger() {
        return LoggerFactory.getLogger(LoggerEnum.EXCEPTION.getCategory());
    }
}