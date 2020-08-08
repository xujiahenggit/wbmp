package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@TableName("z_sms_personnel")
@AllArgsConstructor
@NoArgsConstructor
public class PersonnelDO implements Serializable {
    @TableId(value = "ID", type = IdType.AUTO)
    private String id;
    @TableField(value = "STROPERATORNUM")
    private String strOperatorNum;
    @TableField(value = "STROPERATORNAME")
    private String strOperatorName;
    @TableField(value = "STROPERATORTEL")
    private String strOperatorTel;
    @TableField(value = "STRALERTTYPE")
    private String strAlertType;
    @TableField(value = "STRVALIDTIMESTART")
    private String strValidTimeStart;
    @TableField(value = "STRVALIDTIMEEND")
    private String strValidTimeEnd;
    @TableField(value = "STRBANKNUM")
    private String strBankNum;
    @TableField(value = "STRBRANCHNUM")
    private String strBranchNum;
    @TableField(value = "STRSUBBRANCHNUM")
    private String strSubBranchNum;
    @TableField(value = "STRSSBNUM")
    private String strSsbNum;
    @TableField(value = "CREATEDATE")
    private LocalDateTime createDate;

    private String strSsbName;
    private String strSubBranchName;
    private String strBranchName;
    private String strBankName;
    @TableField(exist = false)
    private String powerNum;
}
