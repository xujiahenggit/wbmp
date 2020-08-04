package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@AllArgsConstructor
@NoArgsConstructor
@TableName("z_svcstatuslog")
public class SvcStatuslogDO implements Serializable {
    @TableId(value = "ID", type = IdType.AUTO)
    private String id;
    private String strtermnum;
    private Integer svcstatus;
    private LocalDateTime dtbegin;
    private LocalDateTime dtend;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;
    @TableField(exist = false)
    private String dtTime = getDtTime();

    private String getDtTime() {
        String s = null;
        if (dtbegin != null && dtend != null) {
            Duration d = Duration.between(dtbegin, dtend);
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
