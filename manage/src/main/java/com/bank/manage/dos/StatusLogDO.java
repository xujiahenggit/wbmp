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
@TableName("T_STATUS_LOG")
public class StatusLogDO implements Serializable {

    private static final long serialVersionUID = -6938032871491023362L;
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
     * 状态信息
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
