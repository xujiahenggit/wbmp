package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel(value = "ExcelData对象", description = "Excel数据导入记录表 ")
public class ExcelDataDTO implements Serializable {
    private static final long serialVersionUID = 1303808570167556709L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;
    /**
     * 数据类型 0：考核原始数据；1：全国标杆网点统计数据；2：行内星级标准化网点统计数据
     */
    @ApiModelProperty(value = "数据类型 0：考核原始数据；1：全国标杆网点统计数据；2：行内星级标准化网点统计数据")
    private String dataType;
    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    private String excelName;
    /**
     * 文件路径
     */
    @ApiModelProperty(value = "文件路径")
    private String excelPath;
    /**
     * 文件大小
     */
    @ApiModelProperty(value = "文件大小")
    private String excelSize;
    /**
     * 年份 生效年份（前端传入），考核年份（默认当前年份
     */
    @ApiModelProperty(value = "年份 生效年份（前端传入），考核年份（默认当前年份")
    private String excelDate;
    /**
     * 季度 考核季度（前端传入1，2，3，4）数据类型不为考核原始数据时留空
     */
    @ApiModelProperty(value = "季度 考核季度（前端传入1，2，3，4）数据类型不为考核原始数据时留空")
    private String excelQuarter;
    /**
     * 是否对全行发布 0：是；1：否
     */
    @ApiModelProperty(value = "是否对全行发布 0：是；1：否")
    private String excelRelease;
    /**
     * 上传人
     */
    @ApiModelProperty(value = "上传人")
    private String createUser;
    /**
     * 上传时间
     */
    @ApiModelProperty(value = "上传时间")
    private LocalDateTime createTime;
}
