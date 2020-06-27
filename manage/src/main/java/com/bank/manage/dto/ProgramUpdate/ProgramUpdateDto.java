package com.bank.manage.dto.ProgramUpdate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/28 11:07
 */
@Data
@ApiModel("更新节目回显用")
public class ProgramUpdateDto implements Serializable {

    @ApiModelProperty(value = "节目编号")
    private Integer programId;

    /**
     * 节目名称
     */
    @ApiModelProperty(value = "节目名称")
    private String programName;

    /**
     * 节目类型
     */
    @ApiModelProperty(value = "节目类型")
    private String programType;

    /**
     * 机构号
     */
    @ApiModelProperty(value = "机构号")
    private String orgId;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private String startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private String endTime;

    /**
     * 终端编号
     */
    @ApiModelProperty(value = "终端编号")
    private String terminalNum;
    /**
     * 样式
     */
    @ApiModelProperty("样式")
    private ProgramStyleDto styleArea;

    /**
     *素材列表
     */
    @ApiModelProperty("素材列表")
    private List<ProgramMaterialDto> listMaterial;
}
