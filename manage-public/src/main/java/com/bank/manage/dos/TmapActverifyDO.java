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
 * 帐号指定表
 * </p>
 * @author
 * @since 2020-7-4
 */
@Data
@Builder
@TableName("t_map_actverify")
    public class TmapActverifyDO implements Serializable {

    /**
     * 主键自增ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 帐号
     */
    private String acctno;

    /**
     * 录入柜员
     */
    private String tellerInsert;

    /**
     * 录入时间
     */
    private LocalDateTime timeInsert;

    /**
     * 操作人
     */
    private String tellerUpdate;

    /**
     * 操作时间
     */
    private LocalDateTime timeUpdate;

    /**
     * 机构号
     */
    private String branch;

    /**
     * 状态0-启用1-停用
     */
    private String status;

}
