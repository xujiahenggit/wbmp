package com.bank.user.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @Author: Andy
 * @Date: 2020/6/10 16:27
 */
@TableName("T_NFRT_ORG_TEMP")
@Data
public class NfrtOrgTempDO extends Model<NfrtOrgTempDO> {
    private static final long serialVersionUID = 1L;

    /**
     * 6为机构号
     */
    private String nfrtOrgId;

    /**
     * 柜组号
     */
    private String nfrtOrgNo;

    /**
     * 机构名称
     */
    private String nfrtOrgName;

    /**
     * 分支行代码
     */
    private String nfrtOrgBranchNo;

    /**
     * 分支行名称
     */
    private String nfrtOrgBranchName;

    /**
     * 机构状态 1可用0停用
     */
    private String nfrtOrgState;

    /**
     * 法人机构号
     */
    private String nfrtOrgLegalId;

    /**
     * 业务关系种类
     */
    private String nfrtOrgBusType;

    /**
     * 业务关系机构
     */
    private String nfrtOrgBusOrgid;

    /**
     * 机构级别
     */
    private String nfrtOrgLevel;

    /**
     * 机构类型
     */
    private String nfrtOrgType;
}
