package com.bank.icop.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * @Author: Andy
 * @Date: 2020/6/4 15:42
 */
@Data
@ApiModel(description = "事项列表")
public class MatterListVo implements Serializable {

    @ApiModelProperty(value = "事件ID")
    private String eventID;

    @ApiModelProperty(value = "事件名称 操作名称，分行审批/号段审核/收货审核")
    private String eventName;

    @ApiModelProperty(value = "事件类型 1-待办；2-未办结；3-已办；4-消息")
    private String type;

    @ApiModelProperty(value = "创建时间", notes = "yyyyMMdd HH:mm:ss")
    private String createTime;

    @ApiModelProperty(value = "内容 机构名称+订单类型/凭证名称")
    private String content;

    @ApiModelProperty(value = "订单状态")
    private String orderType;

    @ApiModelProperty(value = "操作类型 ：0审批、1提交、2驳回、3：取消、4：号段审核、5：收货、6：核心记账入库(收货复核)")
    private String operationType;

    @ApiModelProperty(value = "操作对象 0:订单，1凭证")
    private String operationObject;

}
