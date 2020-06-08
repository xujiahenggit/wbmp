package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@TableName("T_DEVICE_ERROR_LOG")
public class DeviceErrorLogDO implements Serializable {

    private static final long serialVersionUID = -4053126028622181085L;
    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 设备编号
     */
    private String terminalNum;

    /**
     * 设备MAC地址
     */
    private String mac;

    /**
     * 错误信息
     */
    private String messageLog;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
