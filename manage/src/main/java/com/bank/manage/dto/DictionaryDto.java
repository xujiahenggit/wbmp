package com.bank.manage.dto;

import com.bank.manage.dos.DictionaryDO;
import com.bank.manage.dos.DictionaryItemDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/9 18:02
 */
@Data
@ApiModel("字典 创建更新用")
public class DictionaryDto extends DictionaryDO implements Serializable {

    @ApiModelProperty("字典项目")
    List<DictionaryItemDO> listItem;
}
