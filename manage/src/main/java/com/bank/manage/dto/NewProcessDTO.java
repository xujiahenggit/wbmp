package com.bank.manage.dto;

import com.bank.manage.dos.NewProcessDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 流程信息表
 *
 * @author
 * @date 2020-4-7
 */
@Data
@ApiModel(description = "流程信息")
public class NewProcessDTO implements Serializable {

    private static final long serialVersionUID = 9093817758105133422L;


    /**
     * 主键
     */
    @ApiModelProperty(value = "流程编号")
    private Integer processId;

    /**
     * 流程状态
     */
    @ApiModelProperty(value = "流程状态 10：待审核  20：审核通过 30：驳回")
    private String status;

    /**
     * 是否有效【 0-无效、 1-有效】
     */
    @ApiModelProperty(value = "是否有效【 0-无效、 1-有效】")
    private String active;

    /**
     * 创建人id
     */
    @ApiModelProperty(value = "创建人id")
    private String creatorId;

    /**
     * 创建人姓名
     */
    @ApiModelProperty(value = "创建人姓名")
    private String creatorName;

    /**
     * 创建人所属机构号
     */
    @ApiModelProperty(value = "创建人所属机构号")
    private String orgId;

    /**
     * 所属机构名称
     */
    @ApiModelProperty(value = "所属机构名称")
    private String orgName;
    /**
     * 业务id
     */
    @ApiModelProperty(value = "业务id")
    private String tradingId;

    /**
     * 业务名称
     */
    @ApiModelProperty(value = "业务名称")
    private String tradingName;

    /**
     * 业务类型
     */
    @ApiModelProperty(value = "业务类型")
    private String tradingType;

    /**
     * 业务模块
     */
    @ApiModelProperty(value = "业务模块")
    private String  tradingModule;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

}
