package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *  统计模块主表数据传输对象实体类
 *
 */
@Data
@ApiModel(value = "CountModule对象", description = " 统计模块主表")
public class CountModuleDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	private Integer id;
	/**
	 * 年份
	 */
	@ApiModelProperty(value = "年份")
	private Integer moduleYear;
	/**
	 * 一级模块
	 */
	@ApiModelProperty(value = "一级模块")
	private String oneModule;
	/**
	 * 总分数
	 */
	@ApiModelProperty(value = "总分数")
	private Integer countScore;
	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人")
	private String createdUser;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createdTime;
	/**
	 * 修改人
	 */
	@ApiModelProperty(value = "修改人")
	private String updateUser;
	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private LocalDateTime updateTime;

}
