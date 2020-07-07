package com.bank.icop.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "凭证待办列表")
@Data
public class VoucherWaitListVo extends VoucherListVo  implements Serializable {

        @ApiModelProperty(value = "名称")
        private String Name;

        @ApiModelProperty(value = "创建时间")
        private String date;

        @ApiModelProperty(value = "内容")
        private String content;
}
