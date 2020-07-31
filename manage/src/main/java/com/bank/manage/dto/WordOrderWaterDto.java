package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 *
 *
 * @author 陈强
 * @since
 */
@Data
@ApiModel(value = "工单流水")
public class WordOrderWaterDto {
    @ApiModelProperty(value = "工单编号")
    private String wordOrderCode;
    @ApiModelProperty(value = "处理人id")
    private String dealWithPeopleId;
    @ApiModelProperty(value = "处理人姓名")
    private String dealWithPeopleName;
    @ApiModelProperty(value = "处理人角色：1服务主管，2服务工程师，3分行管理员，4总行管理员，5设备厂商，6创建人")
    private String dealWithPeopleRole;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "操作类型0 :新建；1：评价；2：办结；3：分行确认；4：总行确认；5：厂商回复；6：总行知悉；7：分行知悉；8：退回；9：关闭投诉 10不关闭投诉  11分派，12处理 ，13分行取消，14总行取消")
    private String operationType;

}
