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
 * @Date: 2020/4/30 16:51
 */
@Data
@ApiModel("机构维护项")
@TableName("T_ORG_DETAILINFO")
public class OrgDetailinfoDO extends Model<OrgDetailinfoDO> {
    private static final long serialVersionUID = 1L;

    /**
     * 机构编号
     */
    @ApiModelProperty(value = "机构编号")
    @TableId(value = "ORG_ID", type = IdType.UUID)
    private String orgId;

    /**
     * 地址信息
     */
    @ApiModelProperty(value = "地址信息")
    private String orgAddress;

    /**
     * 常用联系人
     */
    @ApiModelProperty(value = "常用联系人")
    private String orgContactMan;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String orgPhone;

    /**
     * 座机号码
     */
    @ApiModelProperty(value = "座机号码")
    private String orgTell;

}
