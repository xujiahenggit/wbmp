package com.bank.icop.dos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "处理记录保存接口10032")
public class UserListDo {
    @ApiModelProperty("用户编号")
    private String userNo;
    @ApiModelProperty("柜员号")
    private String userid;
    @ApiModelProperty("柜员名称")
    private String userna;
    @ApiModelProperty("条数")
    private String limit;
    @ApiModelProperty("取数")
    private String offset;
    @ApiModelProperty("机构KEY")
    private String ORGANKEY;
    @ApiModelProperty("机构名称")
    private String ORGANNAME;
}
