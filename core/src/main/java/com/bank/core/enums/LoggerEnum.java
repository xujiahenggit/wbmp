package com.bank.core.enums;

/**
 * @author wpp.arthur
 * @Date 2020/02/29
 */
public enum LoggerEnum {

    // 业务日志标识
    BUSINESS("business"),
    // 平台日志标识
    PLATFORM("platform"),
    // 数据库日志标识
    DB("db"),
    // 异常日志标识
    EXCEPTION("exception"),
    ;

    private String category;

    LoggerEnum(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}