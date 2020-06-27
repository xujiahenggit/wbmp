package com.bank.core.sysConst;

/**
 * @Author: Andy
 * @Date: 2020/4/21 15:41
 */
public class NewProcessStatusFile {
    /**
     * 审核状态-待审批
     */
    public static final String PROCESS_WAIT="10";
    /**
     * 审核状态-审核通过
     */
    public static final String PROCESS_PASS="20";

    /**
     * 审核状态-驳回
     */
    public static final String PROCESS_REJECT="30";

    /**
     * 审核状态-撤销
     */
    public static final String PROCESS_REVOKE = "40";


    /**
     * 审核操作类型-创建
     */
    public static final String OPERATE_TYPE_CREATE="创建";
    /**
     * 审核操作类型-更新
     */
    public static final String OPERATE_TYPE_UPDATE="更新";

    /**
     * 审核操作类型-删除
     */
    public static final String OPERATE_TYPE_DELETE="删除";


    /**
     * 审核操作类型-通过
     */
    public static final String OPERATE_TYPE_PASS="通过";


    /**
     * 审核操作类型-撤销
     */
    public static final String OPERATE_TYPE_REVOKE="撤销";

    /**
     * 审核操作类型-驳回
     */
    public static final String OPERATE_TYPE_REJECT="驳回";

    /**
     * 审核列表查询类型-待办
     */
    public static final String QUERY_TYPE_WAIT="WAIT";

    /**
     * 审核列表查询类型-已办
     */
    public static final String QUERY_TYPE_ALREADY="ALREADY";

    /**
     * 判断类型 -已审核
     */
    public static final String CHECK_TYPE_PASS="PASS";

    /**
     * 判断类型 -已撤销
     */
    public static final String CHECK_TYPE_REVOKE="REVOKE";

    /**
     * 判断类型 -已驳回
     */
    public static final String CHECK_TYPE_REJECT="REJECT";

}
