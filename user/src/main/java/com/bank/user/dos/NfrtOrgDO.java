package com.bank.user.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Andy
 * @Date: 2020/6/3 15:09
 */
@TableName("T_NFRT_ORG")
@ApiModel("核心机构")
@Data
public class NfrtOrgDO extends Model<NfrtOrgDO> {
    private static final long serialVersionUID = 1L;

    /**
     * 6为机构号
     */
    @ApiModelProperty(value = "6为机构号")
    private String nfrtOrgId;

    /**
     * 柜组号
     */
    @ApiModelProperty(value = "柜组号")
    private String nfrtOrgNo;

    /**
     * 机构名称
     */
    @ApiModelProperty(value = "机构名称")
    private String nfrtOrgName;

    /**
     * 分支行代码
     */
    @ApiModelProperty(value = "分支行代码")
    private String nfrtOrgBranchNo;

    /**
     * 分支行名称
     */
    @ApiModelProperty(value = "分支行名称")
    private String nfrtOrgBranchName;

    /**
     * 机构状态 1可用0停用
     */
    @ApiModelProperty(value = "机构状态 1可用0停用")
    private String nfrtOrgState;

    /**
     * 法人机构号
     */
    @ApiModelProperty(value = "法人机构号")
    private String nfrtOrgLegalId;

    /**
     * 业务关系种类
     */
    @ApiModelProperty(value = "业务关系种类")
    private String nfrtOrgBusType;

    /**
     * 业务关系机构
     */
    @ApiModelProperty(value = "业务关系机构")
    private String nfrtOrgBusOrgid;

    /**
     * 机构级别
     */
    @ApiModelProperty(value = "机构级别")
    private String nfrtOrgLevel;

    /**
     * 机构类型
     */
    @ApiModelProperty(value = "机构类型")
    private String nfrtOrgType;
}
