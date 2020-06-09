package com.bank.manage.vo.partorlRecord;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Andy
 * @Date: 2020/5/13 9:04
 */
@Data
@ApiModel("巡查内容 记录用")
public class PartorlContentVo implements Serializable {

    @ApiModelProperty("巡查内容编号")
    private Integer partorlContentId;

    /**
     * 巡查结果 0：是；1：否
     */
    @ApiModelProperty(value = "巡查结果 0：是；1：否")
    private String partorlResult;

    /**
     * 巡查备注
     */
    @ApiModelProperty(value = "巡查备注")
    private String partorlMark;

    @ApiModelProperty(value = "巡查记录证明文件列表")
    private List<PartorlProveVo> listProve;
}
