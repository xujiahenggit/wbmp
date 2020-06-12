package com.bank.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;

/**
 * 活动沙龙 视图实体类
 *
 * @author zhaozhongyuan
 * @since 2020-06-05
 */
@Data
@ApiModel(value = "ActivitieSalonVO对象", description = "活动沙龙 ")
public class ActivitieSalonVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键ID")
	private Integer id;
	/**
	 * 活动名称
	 */
	@ApiModelProperty(value = "活动名称")
	private String activitieName;
	/**
	 * 活动类型 0产品营销1品牌宣传2监管宣讲3其他
	 */
	@ApiModelProperty(value = "活动类型 0产品营销1品牌宣传2监管宣讲3其他")
	private String activitieType;
	/**
	 * 文件路径
	 */
	@ApiModelProperty(value = "文件路径")
	private String activitiePath;


	@ApiModelProperty(value = "文件名称")
	private String activitieFileName;

	/**
	 * 活动描述
	 */
	@ApiModelProperty(value = "活动描述")
	private String activitieDesc;
	/**
	 * 活动状态 0启用1停用
	 */
	@ApiModelProperty(value = "活动状态 0启用1停用")
	private String activitieStatus;
	/**
	 * 营销时长
	 */
	@ApiModelProperty(value = "营销时长",required = false)
	private BigDecimal activitieTime;
	/**
	 * 营销次数
	 */
	@ApiModelProperty(value = "营销次数",required = false)
	private Integer activitieTotal;
	/**
	 * 机构号
	 */
	@ApiModelProperty(value = "机构号")
	private String orgId;
	/**
	 * 机构名称
	 */
	@ApiModelProperty(value = "机构名称")
	private String orgName;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createdTime;
	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人")
	private String createdBy;
	/**
	 * 更新人
	 */
	@ApiModelProperty(value = "更新人")
	private String updatedBy;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updatedTime;

	@ApiModelProperty(value = "解析状态 0 解析成功 1 未解析成功")
	private Integer status;

	@ApiModelProperty(value = "解析状态 0 解析成功 1 未解析成功")
	private String statusName;

}
