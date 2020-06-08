package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 引导员管理-接口实体类
 * ClassName: UsherDO
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/04/21 11:30:57
 */
@Data
@ApiModel(description = "网点引导员信息")
public class UsherDTO implements Serializable {

    private static final long serialVersionUID = -1189186526300608766L;

    @ApiModelProperty(value = "网点引导员主键")
    private Integer usherId;

    @ApiModelProperty(value = "引导员姓名", required = true)
    private String usherName;

    @ApiModelProperty(value = "所属公司名称", required = true)
    private String companyName;

    @ApiModelProperty(value = "所属机构编号", required = true)
    private String orgId;

    @ApiModelProperty(value = "所属机构名称", required = true)
    private String orgName;

    @ApiModelProperty(value = "电话号码", required = true)
    private String phoneNo;

    @ApiModelProperty(value = "身份证号", required = true)
    private String identityNo;

    @ApiModelProperty(value = "本月应出勤天数", required = true)
    private Integer workDays;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别")
    private Integer sex;

}
