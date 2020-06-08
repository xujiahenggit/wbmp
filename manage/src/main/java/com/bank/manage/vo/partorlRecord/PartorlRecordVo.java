package com.bank.manage.vo.partorlRecord;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/13 9:02
 */
@Data
@ApiModel("巡查记录 保存 更新时用")
public class PartorlRecordVo implements Serializable {

    /**
     * 巡查记录ID
     */
    @ApiModelProperty("巡查记录ID 如果是新增 直接传0，如果是修改 必须传巡查记录ID")
    private Integer partorlRecordId;
    /**
     * 流程记录ID
     */
    @ApiModelProperty("流程记录ID")
    private Integer processId;
    /**
     * 巡查记录内容信息
     */
    @ApiModelProperty("巡查内容")
    private List<PartorlContentVo> listContent;
}
