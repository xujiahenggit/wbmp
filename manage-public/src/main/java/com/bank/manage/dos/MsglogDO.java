package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@TableName("z_smsmessagesentlog")
@AllArgsConstructor
@NoArgsConstructor
public class MsglogDO implements Serializable {
    @TableId(value = "ID", type = IdType.AUTO)
    private String id;
    private String strBankNum;
    private String strBranchNum;
    private String strSubBranchNum;
    private String strSsbNum;
    private String strTermNum;
    private String strCusName;
    private Integer sendSucc;
    private String strPhoneNum;
    private String content;
    private LocalDateTime sendDate;
    private LocalDateTime createDate;

}
