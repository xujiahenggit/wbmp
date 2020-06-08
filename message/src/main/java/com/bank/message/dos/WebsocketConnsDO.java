package com.bank.message.dos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体类
 *
 * @author zhaozhongyuan
 * @since 2020-05-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("T_WEBSOCKET_CONNS")
@ApiModel(value = "WebsocketConns对象", description = "WebsocketConns对象")
public class WebsocketConnsDO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 大屏端的mac地址
     */
  @ApiModelProperty(value = "大屏端的mac地址")
  @TableId("MAC")
  private String mac;
    /**
     * pad的设备号
     */
  @ApiModelProperty(value = "pad的设备号")
  @TableField("CLIENT_ID")
  private String clientId;
    /**
     * 1：大屏端；2：pad端
     */
  @ApiModelProperty(value = "1：大屏端；2：pad端")
  @TableField("ROLE_ID")
  private Integer roleId;
    /**
     * 大屏端连接的服务器Ip
     */
  @ApiModelProperty(value = "大屏端连接的服务器Ip")
  @TableField("SERVER_IP")
  private String serverIp;
    /**
     * 连接时间
     */
  @ApiModelProperty(value = "连接时间")
  @TableField("CREATE_TIME")
  private LocalDateTime createTime;


}
