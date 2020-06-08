package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/9 17:26
 */
@ApiModel("字典项目")
@Data
@TableName("T_DICTIONARY_ITEM")
public class DictionaryItemDO extends Model<DictionaryItemDO> {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    @TableId(value = "DICT_ITEM_ID", type = IdType.AUTO)
    private Integer dictItemId;

    /**
     * 字典编号
     */
    @ApiModelProperty(value = "字典编号 更新时必填")
    private Integer dictId;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private Integer dictNum;

    /**
     * 字典项值
     */
    @ApiModelProperty(value = "字典项值")
    private String dictItemValue;

    /**
     * 字典项标签
     */
    @ApiModelProperty(value = "字典项标签")
    private String dictItemTitle;



}
