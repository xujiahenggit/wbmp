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
 * @Date: 2020/5/12 14:58
 */
@TableName("T_PARTORL_PROVE")
@Data
@ApiModel("巡查证明")
public class PartorlProveDO extends Model<PartorlProveDO> {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    @TableId(value = "PARTORL_PROVE_ID", type = IdType.AUTO)
    private Integer partorlProveId;

    /**
     * 巡查记录从表主键
     */
    @ApiModelProperty(value = "巡查内容记录ID")
    private Integer partorlRecordItemId;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private Integer partorlProveNum;

    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    private String partorlProveFileName;

    /**
     * 文件大小 单位为M
     */
    @ApiModelProperty(value = "文件大小 单位为KB")
    private Long partorlProveFileSize;

    /**
     * 文件路径
     */
    @ApiModelProperty(value = "文件路径")
    private String partorlProveFilePath;
}
