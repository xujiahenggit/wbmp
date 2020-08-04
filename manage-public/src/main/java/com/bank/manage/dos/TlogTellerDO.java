package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 柜员操作日志表
 * </p>
 * @author
 * @since 2020-7-8
 */
@Data
@Builder
@TableName("t_log_teller")
    public class TlogTellerDO implements Serializable {

    /**
     * 主键自增ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 机构号
     */
    private String branchCode;

    /**
     * 柜员编号
     */
    private String userCode;

    /**
     * 交易时间
     */
    private LocalDateTime modifyTime;

    /**
     * 节点编号
     */
    private String menuId;

    /**
     * 备注
     */
    private String memo;

    /**
     * 日志类型
     */
    private String czbz;
    }
