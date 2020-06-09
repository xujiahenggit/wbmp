package com.bank.user.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/6/10 17:36
 */
@Data
@ApiModel("分支行/网点用")
public class OrgNftDto implements Serializable {
    /**
     * 机构号  分支行时 显示核心机构号  网点时 显示 人力资源机构号
     */
    private String orgId;

    /**
     * 机构名称
     */
    private String orgName;
}
