package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Excel数据导入记录表 实体类
 */
@Data
@Builder
@TableName("T_EXCEL_DATA")
public class ExcelDataDO implements Serializable {

  private static final long serialVersionUID = 4117695126940018709L;
  /**
     * 主键
     */
  @TableId(value = "ID", type = IdType.AUTO)
  private Integer id;
    /**
     * 数据类型 0：考核原始数据；1：全国标杆网点统计数据；2：行内星级标准化网点统计数据
     */
  @TableField("DATA_TYPE")
  private String dataType;
    /**
     * 文件名称
     */
  @TableField("EXCEL_NAME")
  private String excelName;
    /**
     * 文件路径
     */
  @TableField("EXCEL_PATH")
  private String excelPath;
    /**
     * 文件大小
     */
  @TableField("EXCEL_SIZE")
  private String excelSize;
    /**
     * 年份 生效年份（前端传入），考核年份（默认当前年份
     */
  @TableField("EXCEL_DATE")
  private String excelDate;
    /**
     * 季度 考核季度（前端传入1，2，3，4）数据类型不为考核原始数据时留空
     */
  @TableField("EXCEL_QUARTER")
  private String excelQuarter;
    /**
     * 是否对全行发布 0：是；1：否
     */
  @TableField("EXCEL_RELEASE")
  private String excelRelease;
    /**
     * 上传人
     */
  @TableField("CREATE_USER")
  private String createUser;
    /**
     * 上传时间
     */
  @TableField("CREATE_TIME")
  private LocalDateTime createTime;


}
