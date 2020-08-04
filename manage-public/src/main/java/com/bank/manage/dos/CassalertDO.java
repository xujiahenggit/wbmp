package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CassalertDO implements Serializable {
    @TableId(value = "ID", type = IdType.AUTO)
    private String id;
    private String strBankNum;
    private String strBranchNum;
    private String strSubBranchNum;
    private String strSsbNum;
    private String strTermNum;
    private String icdmalertvalue;
    private String icimalertvalue;
    private String idefalertvalue;
    private String ihoursforsugg;
}
