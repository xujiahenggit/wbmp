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
 * 对账科目维护表
 * </p>
 * @author
 * @since 2020-7-14
 */
@Data
@Builder
@TableName("t_accountingsubject")
    public class TaccountingSubjectDO implements Serializable {

    /**
     * 主键自增ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 科目号
     */
    private String subject;

    /**
     * 科目名称
     */
    private String subjectname;

    /**
     * 业务编号
     */
    private String busiid;

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
     * 状态0-启用1-停用
     */
    private String status;
}
