package com.bank.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/4/26 14:53
 */
@Data
public class ProgramQueryByDeviceVo implements Serializable {
    @ApiModelProperty("第几页：默认第一页")
    private Integer pageIndex = 1;

    @ApiModelProperty("分页大小：默认10行")
    private Integer pageSize = 10;

    @ApiModelProperty("节目名称 模糊匹配")
    private String programName;

    @NotBlank(message = "设备编号不能为空")
    private String deviceId;

}
