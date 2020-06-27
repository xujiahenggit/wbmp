package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Dictionary;

/**
 * @Author: Andy
 * @Date: 2020/5/9 17:24
 */
@ApiModel("数据字典")
@Data
@TableName("T_DICTIONARY")
public class DictionaryDO extends Model<DictionaryDO> {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @ApiModelProperty(value = "编号 - 更新时必填")
    @TableId(value = "DICT_ID", type = IdType.AUTO)
    private Integer dictId;

    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称")
    private String dictName;

    /**
     * 字典描述
     */
    @ApiModelProperty(value = "字典描述")
    private String dictDescribe;
}
