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
@TableName("z_settlecyclelog")
@AllArgsConstructor
@NoArgsConstructor
public class SettlecyclelogDO implements Serializable {
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    private String strTermNum;
    private Integer settleCycle;
    private LocalDateTime dtStart;
    private Integer cdmRefillCashAmt;
    private Integer cwdCount;
    private Double cwdAmount;
    private Integer depCount;
    private Double depAmount;
}
