package com.bank.icop.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "现场检查任务登记检查VO")
public class OnSiteInspectionTaskCheckVO extends OnSiteInspectionTaskItemVO {

    /**
     *
     */
    private static final long serialVersionUID = 6292316280910445814L;

    @ApiModelProperty(value = "检查过程")
    private CheckProcessVO checkProcessVO;

    @ApiModelProperty(value = "检查子项列表")
    private List<CheckItemVO> checkItemVOList;

    @ApiModelProperty(value = "检查问题列表")
    private List<CheckProblemVO> checkProblemVOList;

    @ApiModelProperty(value = "检查附件列表")
    private List<CheckAccessoryVO> checkAccessoryVOList;
}