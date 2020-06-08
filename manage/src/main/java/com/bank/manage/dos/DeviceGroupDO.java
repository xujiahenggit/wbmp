package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@TableName("T_DEVICE_GROUP")
public class DeviceGroupDO implements Serializable {

    private static final long serialVersionUID = 497889105886081126L;
    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 分组主键
     */
    private Integer groupId;

    /**
     * 设备主键
     */
    private Integer deviceId;

}
