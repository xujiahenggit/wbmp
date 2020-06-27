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
 * @Date: 2020/5/11 11:18
 */
@Data
@TableName("T_PARTORL_MODUAL")
@ApiModel("巡查模块信息")
public class PartorlModualDO extends Model<PartorlModualDO> {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @ApiModelProperty("编号")
    @TableId(value = "PARTORL_MODUAL_ID", type = IdType.AUTO)
    private Integer partorlModualId;

    /**
     * 巡查模块名称
     */
    @ApiModelProperty("巡查模块名称")
    private String partorlModualName;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer partorlModualSort;
}
