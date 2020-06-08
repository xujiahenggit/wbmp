package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 流程信息表
 * </p>
 * @author
 * @since 2020-04-01
 */
@Data
@Builder
@TableName("S_NEW_PROCESS")
public class NewProcessDO extends Model<NewProcessDO> {

    private static final long serialVersionUID = -5673049560230196229L;

    /**
     * 主键
     */
    @TableId(value = "PROCESS_ID", type = IdType.AUTO)
    private Integer processId;

    /**
     * 流程状态
     */
    private String status;

    /**
     * 是否有效【 0-无效、 1-有效】
     */
    private String active;

    /**
     * 创建人id
     */
    private String creatorId;

    /**
     * 创建人姓名
     */
    private String creatorName;

    /**
     * 创建人所属机构号
     */
    private String orgId;


    /**
     * 业务id
     */
    private String tradingId;

    /**
     * 业务名称
     */
    private String tradingName;

    /**
     * 业务类型
     */
    private String tradingType;

    /**
     * 业务模块
     */
    private String  tradingModule;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
