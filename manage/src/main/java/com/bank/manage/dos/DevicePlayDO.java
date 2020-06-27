package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("T_DEVICE_PLAY")
public class DevicePlayDO implements Serializable {

    private static final long serialVersionUID = -8610350755503944147L;
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
     * 播放信息
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

    private Integer programId;

    private String programName;

}
