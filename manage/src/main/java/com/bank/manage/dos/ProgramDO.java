package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 节目发布内容表
 */
@Data
@Builder
@TableName("T_PROGRAM")
public class ProgramDO implements Serializable {

    private static final long serialVersionUID = -572104941560733167L;
    /**
     * 节目主键
     */
    @TableId(value = "PROGRAM_ID", type = IdType.AUTO)
    private Integer programId;

    /**
     * 节目名称
     */
    private String programName;

    /**
     * 节目类型
     */
    private String programType;

    /**
     * 机构号
     */
    private String orgId;

    /**
     * 创建人
     */
    private String createdUser;

    /**
     * 发布时间
     */
    private LocalDateTime createdTime;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 终端编号
     */
    private String terminalNum;
}
