package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 设备信息
 *
 * @author
 * @date 2020-4-1
 */
@Data
@ApiModel(description = "设备信息")
public class DeviceDTO implements Serializable {

    private static final long serialVersionUID = 9093817758105133422L;

    @ApiModelProperty(value = "设备Id" )
    private Integer deviceId;

    @ApiModelProperty(value ="终端编号",required = true)
    private String terminalNum;

    @ApiModelProperty(value ="机构号",required = true)
    private String orgId;

    @ApiModelProperty(value ="机构名称",required = true)
    private String orgName;

    @ApiModelProperty(value ="设备类型",required = true)
    private String deviceType;

    @ApiModelProperty(value ="设备名称",required = true)
    private String deviceName;

    @ApiModelProperty(value ="设备尺寸",required = true)
    private String deviceSize;

    @ApiModelProperty(value ="设备系统")
    private String deviceSystem;

    @ApiModelProperty(value ="设备品牌")
    private String deviceBrand;

    @ApiModelProperty(value ="设备型号")
    private String deviceModel;

    @ApiModelProperty(value ="设备来源")
    private String deviceSource;

    @ApiModelProperty(value ="供货商")
    private Integer upplierId;

    @ApiModelProperty(value ="所在位置简称")
    private String addressAbbr;

    @ApiModelProperty(value ="安装方式")
    private String installWay;

    @ApiModelProperty(value ="网关",required = true)
    private String gateWay;

    @ApiModelProperty(value ="IP地址",required = true)
    private String ip;

    @ApiModelProperty(value ="设备MAC地址",required = true)
    private String mac;

    @ApiModelProperty(value ="设备状态,00-未启用，01-启用，02-报亭，03-退网，04-报废")
    private String status;

    @ApiModelProperty(value ="创建时间",hidden = true)
    private Date createTime;

    @ApiModelProperty(value ="创建人ID",hidden = true)
    private String createUser;

    @ApiModelProperty(value ="修改时间",hidden = true)
    private LocalDateTime updateTime;

    @ApiModelProperty(value ="修改人ID",hidden = true)
    private String updateUser;

    @ApiModelProperty(value ="备注")
    private String remark;

    @ApiModelProperty(value ="主题Id",hidden = true)
    private Integer itemId;

    @ApiModelProperty(value ="主题名称",hidden = true)
    private String itemName;

    @ApiModelProperty(value ="节目Id",hidden = true)
    private Integer programId;

    @ApiModelProperty(value ="节目名称",hidden = true)
    private String programName;

    @ApiModelProperty(value ="设备在线 00 在线 01 不在线")
    private String online;;

    /**
     * 样式编号
     */
    private Integer styleNum;

}
