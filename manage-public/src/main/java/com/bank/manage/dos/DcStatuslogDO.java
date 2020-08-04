package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
@Builder
@TableName("z_dcstatuslog")
@AllArgsConstructor
@NoArgsConstructor
public class DcStatuslogDO implements Serializable {
    @TableId(value = "ID", type = IdType.AUTO)
    private String id;
    private String strTermNum;
    private String strVMName;
    private String strhdwstatus;
    private String modelName;
    private String modelStatusDesc;
    private LocalDateTime dtBegin;
    private LocalDateTime dtEnd;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;
    private String dtTime = getDtTime();

    private String getDtTime() {
        String s = null;
        if (dtBegin != null && dtEnd != null) {
            Duration d = Duration.between(dtBegin, dtEnd);
            long days = d.toDays();
            long hours = d.toHours();
            long minutes = d.toMinutes();
            long millis = d.toMillis();
            long nanos = d.toNanos();
            s = days + "天: " + hours + "时: " + minutes + "分";
        }
        return s;
    }
}
