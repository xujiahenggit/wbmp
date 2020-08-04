package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 对账科目维护
 *
 * @author
 * @date 2020-7-14
 */
@Data
@ApiModel(description = "对账科目维护")
public class TaccountingSubjectDTO implements Serializable {

    private static final long serialVersionUID = 507432535723101520L;

    @ApiModelProperty(value = "主键自增ID")
    private Integer id;

    @ApiModelProperty(value = "科目号")
    private String subject;

    @ApiModelProperty(value = "科目名称")
    private String subjectname;

    @ApiModelProperty(value = "业务编号")
    private String busiid;

    @ApiModelProperty(value = "登记人")
    private String tellerInsert;

    @ApiModelProperty(value = "登记时间")
    private LocalDateTime timeInsert;

    @ApiModelProperty(value = "修改人")
    private String tellerUpdate;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime timeUpdate;

    @ApiModelProperty(value = "状态0-启用1-停用")
    private String status;
}
