package com.bank.core.sysConst;

/**
 * @Author: Andy
 * @Date: 2020/4/20 15:41
 * 系统常用状态 常量定义
 */
public class SysStatus {
    /**
     * 定时任务状态 发布
     */
    public static final String TASK_STATUS_TRUE="0";

    /**
     * 定时任务状态 暂停
     */
    public static final String TASK_STATUS_FALSE="1";

    /**
     * 删除状态 删除
     */
    public static final String FLAG_DELETE="1";

    /**
     * 删除状态 未删除
     */
    public static final String FLAG_NO_DELETE="0";

    /**
     * 审核查询状态 待办
     */
    public static final String QUERY_TYPE_WAIT="WAIT";
    /**
     * 审核查询状态 已办
     */
    public static final String QUERY_TYPE_AREADY="AREADY";

    /**
     * 工作日
     */
    public static final Integer WORK_TYPE_WORK=0;
    /**
     * 休息日
     */
    public static final Integer WORK_TYPE_REST=1;

    /**
     * 补卡申请
     */
    public static final String MESSAGE_TYPE_CARD="CARD_SUPPLE";
    /**
     * 加班申请
      */
    public static final String MESSAGE_TYPE_WORD="WORK_SUPPLE";
}
