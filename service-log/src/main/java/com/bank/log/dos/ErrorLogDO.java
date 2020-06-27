package com.bank.log.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: Andy
 * @Date: 2020/4/1 10:36
 */
@Data
@TableName("T_ERROR_LOG")
public class ErrorLogDO extends Model<ErrorLogDO> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键编号
     */
    @TableId(value = "ERR_LOG_ID", type = IdType.UUID)
    private String errLogId;

    /**
     * 异常所在类
     */
    private String errClass;

    /**
     * 异常所在方法
     */
    private String errMethod;

    /**
     * 异常方法名
     */
    private String errMethodName;

    /**
     * 请求参数
     */
    private String errPara;

    /**
     * 异常名称
     */
    private String errName;

    /**
     * 异常信息
     */
    private String errInfo;

    /**
     * 操作URL
     */
    private String optUrl;

    /**
     * 操作用户ID
     */
    private String optUserId;

    /**
     * 操作用户名称
     */
    private String optUserName;

    /**
     * IP
     */
    private String optIp;

    /**
     * 版本号
     */
    private String errVersion;

    /**
     * 异常时间
     */
    private LocalDateTime errDate;
}
