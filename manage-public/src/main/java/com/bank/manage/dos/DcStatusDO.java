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
@TableName("z_dcstatus")
@AllArgsConstructor
@NoArgsConstructor
public class DcStatusDO implements Serializable {
    @TableId(value = "ID", type = IdType.AUTO)
    private String id;
    private String strTermNum;
    private String dcDesc;
    private String strVMName;
    private Integer ihdwStatus;
    private String strVMDetailedStatus;
    private String strVMDetailedStatus2;
    private String strVMDetailedStatus3;
    private LocalDateTime dtHDWStatusBegin;
}
