package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/5/12 17:03
 */
@Data
@ApiModel(value = "巡查记录 查询用")
public class PartorlRecordQueryVo implements Serializable {
    @ApiModelProperty("第几页：默认第一页")
    private Integer pageIndex = 1;

    @ApiModelProperty("分页大小：默认10行")
    private Integer pageSize = 10;

    /**
     * 填报开始时间
     */
    @ApiModelProperty("填报开始时间 yyyy-MM-dd")
    private String startTime;

    /**
     * 填报结束时间
     */
    @ApiModelProperty("填报结束时间 yyyy-MM-dd")
    private String endTime;

    /**
     * 填报人
     */
    @ApiModelProperty("填报人")
    private String partorlUser;

    @ApiModelProperty("是否正常 0：是；1：否 ")
    private String partorlNomal;

    @ApiModelProperty("是否超时 0：是；1：否")
    private String partorlOrvertime;

    @ApiModelProperty("机构编号")
    private String orgId;
}
