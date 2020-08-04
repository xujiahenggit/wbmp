package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 参数表
 * </p>
 * @author
 * @since 2020-7-4
 */
@Data
@Builder
@TableName("s_param")
    public class SparamDO implements Serializable {

    /**
     * 主键自增ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 参数英文名
     */
    private String ename;

    /**
     * 参数名称
     */
    private String cname;

    /**
     * 参数值
     */
    private String value;

    /**
     * 修改状态
     */
    private char statusUpdate;

    /**
     * 渠道号
     */
    private String chanlid;

    /**
     * 启用状态
     */
    private char status;

}
