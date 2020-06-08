package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author: Andy
 * @Date: 2020/5/12 14:57
 */
@Data
@ApiModel("巡查记录")
@TableName("T_PARTORL_RECORD")
public class PartorlRecordDO extends Model<PartorlRecordDO> {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    @TableId(value = "PARTORL_RECORD_ID", type = IdType.AUTO)
    private Integer partorlRecordId;

    /**
     * 代办事项表主键
     */
    @ApiModelProperty(value = "流程ID")
    private Integer newprocessId;

    /**
     * 填报日期
     */
    @ApiModelProperty(value = "填报日期")
    private LocalDate partorlDate;

    /**
     * 填报人
     */
    @ApiModelProperty(value = "填报人")
    private String partorlUser;

    /**
     * 填报人用户ID
     */
    @ApiModelProperty(value = "填报人用户ID")
    private String partorlUserId;

    /**
     * 是否正常 0：是；1：否  巡查内容全部为是则为是否则为否
     */
    @ApiModelProperty(value = "是否正常 0：是；1：否  巡查内容全部为是则为是否则为否")
    private String partorlNomal;

    /**
     * 是否超时 0：是；1：否
     */
    @ApiModelProperty(value = "是否超时 0：是；1：否")
    private String partorlOrvertime;

    /**
     * 机构号
     */
    @ApiModelProperty(value = "机构号")
    private String orgId;

    /**
     * 机构名称
     */
    @ApiModelProperty(value = "机构名称")
    private String orgName;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createUser;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private String updateUser;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;
}
