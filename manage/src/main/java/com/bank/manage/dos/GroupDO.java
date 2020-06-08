package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@TableName("T_GROUP")
public class GroupDO implements Serializable {

    private static final long serialVersionUID = -7743974906169265810L;
    /**
     * 分组主键
     */
    @TableId(value = "GROUP_ID", type = IdType.AUTO)
    private Integer groupId;

    /**
     * 分组名称
     */
    private String groupName;

    /**
     * 分组类型
     */
    private String groupType;

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
