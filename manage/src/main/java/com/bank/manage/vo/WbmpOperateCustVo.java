package com.bank.manage.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: zhangfuqiang
 * @Date: 2020/7/01 19:51
 */
@ApiModel(description = "网点存款时点余额VO")
@Data
@Builder
public class WbmpOperateCustVo extends Model<WbmpOperateCustVo> {

    @ApiModelProperty(value = "机构号")
    private String orgId;

    @ApiModelProperty(value = "客群类型")
    private String indexNo;

    @ApiModelProperty(value = "当前客户数")
    private int indexVal;

    @ApiModelProperty(value = "比上日")
    private int compLastd;

    /**
     * 比上月
     */
    @ApiModelProperty(value = "比上月")
    private int compLastm;

    /**
     * 比上季
     */
    @ApiModelProperty(value = "比上季")
    private int compLastq;

    /**
     * 比年初
     */
    @ApiModelProperty(value = "比年初")
    private int compLasty;

    /**
     * 数据日期
     */
    @ApiModelProperty(value = "日期")
    private String dataDt;
}