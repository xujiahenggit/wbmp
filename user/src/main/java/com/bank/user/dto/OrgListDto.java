package com.bank.user.dto;

import com.bank.user.dos.OrgDetailinfoDO;
import com.bank.user.dos.OrganizationDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/7 16:26
 */
@ApiModel("组织机构列表用")
@Data
public class OrgListDto extends OrganizationDO implements Serializable {

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

    /**
     * 机构岗位人数列表
     */
    @ApiModelProperty(value = "机构岗位人数列表")
    private List<PositionPeopleNum> listPositonPeople;
}
