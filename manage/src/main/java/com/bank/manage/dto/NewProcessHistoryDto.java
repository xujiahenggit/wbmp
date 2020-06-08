package com.bank.manage.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author: Andy
 * @Date: 2020/4/22 14:58
 */
@Data
public class NewProcessHistoryDto implements Serializable {

    /**
     * 主键
     */
    @TableId(value = "PROCESS_HISTORY_ID", type = IdType.AUTO)
    private Integer processHistoryId;

    /**
     * 审批流程ID
     */
    private Integer processId;

    /**
     * 操作人ID
     */
    private String operatorId;

    /**
     * 操作人姓名
     */
    private String operatorName;

    /**
     * 操作人机构号
     */
    private String orgId;

    /**
     * 操作类型 create：新建 update：更新 delete：删除 pass：审核通过 reject：审核驳回
     */
    private String operateType;

    /**
     * 驳回原因
     */
    private String rejectReason;

    /**
     * 操作时间
     */
    private LocalDateTime createTime;
}
