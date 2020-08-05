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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("z_transfilter")
public class TransfilterDO implements Serializable {
    @TableId(value = "ID", type = IdType.AUTO)
    private int id;
    @TableField(value = "OPERATORNO")
    private int operatorNo;
    @TableField(value = "ATTR")
    private String attr;
    @TableField(value = "ATTRVALUE")
    private String attrValue;
    @TableField(value = "SCOPE")
    private String scope;
    @TableField(value = "ATTRNAME")
    private String attrName;
    @TableField(value = "SCOPENAME")
    private String scopeName;
}
