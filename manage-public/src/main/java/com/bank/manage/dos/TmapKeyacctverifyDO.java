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
 * 指定重点账号对账表
 * </p>
 * @author
 * @since 2020-7-5
 */
@Data
@Builder
@TableName("t_map_keyacctverify")
    public class TmapKeyacctverifyDO implements Serializable {

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
     * 登记人
     */
    private String tellerInsert;

    /**
     * 登记时间
     */
    private LocalDateTime timeInsert;

    /**
     * 修改人
     */
    private String tellerUpdate;

    /**
     * 修改时间
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
