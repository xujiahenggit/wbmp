package com.bank.user.dto;

import com.bank.user.dos.OrgDetailinfoDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/12 9:48
 */
@Data
@ApiModel("机构详细信息")
public class OrgDetailDto extends OrgDetailinfoDO implements Serializable {
    @ApiModelProperty("机构岗位人数")
    private List<PositionPeopleNum> listPositonPeople;
}
