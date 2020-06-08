package com.bank.log.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * @Author: Andy
 * @Date: 2020/4/1 9:23
 * 正常日志
 */
@Data
@TableName("T_LOG")
public class LogDO extends Model<LogDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 主键
     */
    @TableId(value = "LOG_ID", type = IdType.UUID)
    private String logId;

    /**
     * 操作模块 操作模块
     */
    private String optModual;

    /**
     * 操作类型 操作类型
     */
    private String optType;

    /**
     * 操作描述 操作描述
     */
    private String optDisc;

    /**
     * 请求类名 请求类名
     */
    private String optClass;

    /**
     * 请求方法 请求方法
     */
    private String optMethod;

    /**
     * 请求参数 请求参数
     */
    private String optParam;

    /**
     * 返回结果 返回结果
     */
    private String optResult;

    /**
     * 请求IP 请求IP
     */
    private String optIp;

    /**
     * 请求URL 请求URL
     */
    private String optUrl;

    /**
     * 请求时间 请求时间
     */
    private LocalDateTime optDate;

    /**
     * 请求用户编号 请求用户编号
     */
    private String optUserId;

    /**
     * 请求用户名 请求用户名
     */
    private String optUserName;

    /**
     * 版本号 版本号
     */
    private String optVersion;

    /**
     * 处理时长 处理时长
     */
    private Long optDura;
}
