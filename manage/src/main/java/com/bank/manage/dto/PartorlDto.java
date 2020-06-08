package com.bank.manage.dto;

import com.bank.manage.dos.PartorlContentDO;
import com.bank.manage.dos.PartorlModualDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/11 14:36
 */
@Data
@ApiModel("巡查内容")
public class PartorlDto implements Serializable {

    /**
     * 巡查记录编号
     */
    @ApiModelProperty("巡查记录编号")
    private Integer partorlRecordId;

    /**
     * 编号
     */
    @ApiModelProperty("编号")
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

    @ApiModelProperty("内容列表")
    private List<PartorlContentDto> listContent;
}
