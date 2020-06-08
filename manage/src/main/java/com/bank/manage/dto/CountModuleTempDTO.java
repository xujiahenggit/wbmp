package com.bank.manage.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

/**
 * 统计模块从表 数据传输对象实体类
 *
 * @author zhaozhongyuan
 * @since 2020-05-22
 */
@Data
public class CountModuleTempDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * 二级模块
	 */
	@ApiModelProperty(value = "二级模块")
	private String twoModule;
	/**
	 * 分值
	 */
	@ApiModelProperty(value = "分值")
	private Integer twoModuleScore;

}
