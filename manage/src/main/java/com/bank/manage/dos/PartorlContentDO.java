package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Andy
 * @Date: 2020/5/11 11:20
 */
@Data
@TableName("T_PARTORL_CONTENT")
@ApiModel("巡查内容表")
public class PartorlContentDO extends Model<PartorlContentDO> {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @ApiModelProperty("编号")
    @TableId(value = "PARTORL_CONTENT_ID", type = IdType.AUTO)
    private Integer partorlContentId;

    /**
     * 巡查模块编号
     */
    @ApiModelProperty("巡查模块编号")
    private Integer partorlModualId;

    /**
     * 巡查内容
     */
    @ApiModelProperty("巡查内容")
    private String partorlContent;

    /**
     * 巡查内容排序
     */
    @ApiModelProperty("巡查内容排序")
    private Integer partorlContentSort;


}
