package com.bank.icop.dos;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * SOAP调用第三方接口日志实体类
 *
 * @author zhaozhongyuan
 * @since 2020-06-09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("T_SOAP_LOG")
@ApiModel(value = "SoapLog对象", description = "SOAP调用第三方接口日志")
public class SoapLogDO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 自增主键
     */
  @ApiModelProperty(value = "自增主键")
  @TableId(value = "ID", type = IdType.AUTO)
  private Integer id;
    /**
     * 接口调用人ID
     */
  @ApiModelProperty(value = "接口调用人ID")
  @TableField("USER_ID")
  private String userId;
    /**
     * 接口调用人名字
     */
  @ApiModelProperty(value = "接口调用人名字")
  @TableField("USER_NAME")
  private String userName;
    /**
     * 接口URI
     */
  @ApiModelProperty(value = "接口URI")
  @TableField("REQUEST_URI")
  private String requestUri;
    /**
     * 请求数据
     */
  @ApiModelProperty(value = "请求数据")
  @TableField("REQUEST_ARGS")
  private String requestArgs;
    /**
     * 返回数据
     */
  @ApiModelProperty(value = "返回数据")
  @TableField("RESPONSE")
  private String response;
    /**
     * 请求耗时（ms）
     */
  @ApiModelProperty(value = "请求耗时（ms）")
  @TableField("COST_TIME")
  private Long costTime;
    /**
     * 请求时间
     */
  @ApiModelProperty(value = "请求时间")
  @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createTime;


}
