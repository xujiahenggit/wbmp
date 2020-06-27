package com.bank.manage.dto.ProgramUpdate;

import com.bank.manage.dto.MaterialDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/28 14:56
 */
@Data
@ApiModel("更新节目 回显信息")
public class ProgramMaterialDto implements Serializable {
    /**
     * 区域ID
     */
    @ApiModelProperty("区域编号")
    private String areaId;
    /**
     * 区域名称
     */
    @ApiModelProperty("区域名称")
    private String areaName;

    @ApiModelProperty("区域类型")
    private String areaType;

    @ApiModelProperty("素材列表")
    List<MaterialDTO> listMaterial;
}
