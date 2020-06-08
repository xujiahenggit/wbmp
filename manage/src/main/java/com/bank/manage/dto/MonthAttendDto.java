package com.bank.manage.dto;

import com.bank.manage.dos.MonthAttendDO;
import com.bank.manage.dos.MonthAttendItemDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/30 16:36
 */
@ApiModel("月满考勤审批")
@Data
public class MonthAttendDto extends MonthAttendDO implements Serializable {

    @ApiModelProperty("行员列表")
    private List<MonthAttendItemDto> listItem;
}
