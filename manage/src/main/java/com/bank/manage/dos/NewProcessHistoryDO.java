package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 流程信息记录表
 * </p>
 * @author
 * @since 2020-04-01
 */
@Data
@Builder
@TableName("S_NEW_PROCESS_HISTORY")
public class NewProcessHistoryDO extends Model<NewProcessHistoryDO> {

    private static final long serialVersionUID = 1L;

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
