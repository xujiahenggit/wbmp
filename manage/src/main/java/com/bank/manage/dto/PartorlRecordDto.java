package com.bank.manage.dto;

import com.bank.manage.dos.PartorlRecordDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/6/23 10:31
 */
@ApiModel("大堂经理巡查记录")
@Data
public class PartorlRecordDto extends PartorlRecordDO implements Serializable {

    @ApiModelProperty("分行名称")
    private String branchName;
}
