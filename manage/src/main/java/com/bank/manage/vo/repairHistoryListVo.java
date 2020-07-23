package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 工单历史
 */
@Data
@ApiModel
public class repairHistoryListVo {


    @ApiModelProperty(value = "流水id")
    private String id;

    @ApiModelProperty(value = "工单id")
    private String wordOrderId;

    @ApiModelProperty(value = "操作类型")
    private String operationType;

    @ApiModelProperty(value = "操作类型名称")
    private String operationTypeName;

    @ApiModelProperty(value = "图片地址URL")
    private List<String> thumbs;

    @ApiModelProperty(value = "评价等级")
    private String evaluationLevel;

    @ApiModelProperty(value = "评价内容")
    private String evaluationComment;

    @ApiModelProperty(value = "退回原因")
    private String refuseReson;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "手机号")
    private String userPhone;

    @ApiModelProperty(value = "时间")
    private String operateTime;

}
