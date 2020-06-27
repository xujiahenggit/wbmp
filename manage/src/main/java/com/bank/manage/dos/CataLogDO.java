package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@TableName("T_CATALOG")
public class CataLogDO implements Serializable {

    private static final long serialVersionUID = -4757259532048947015L;
    /**
     * 目录主键
     */
    @TableId(value = "CATALOG_ID", type = IdType.AUTO)
    private Integer catalogId;

    /**
     * 目录名称
     */
    private String catalogName;

    /**
     * 目录类型
     */
    private String catalogType;

    /**
     * 创建人
     */
    private String createdUser;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 父级ID
     */
    private Integer parentId;

}
